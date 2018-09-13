package prj.ijava.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prj.ijava.dao.BoardDAO;
import prj.ijava.dao.BoardInfoDAO;
import prj.ijava.dao.MemberAdvDAO;
import prj.ijava.dao.MemberDAO;
import prj.ijava.dto.Article;
import prj.ijava.dto.BoardInfo;
import prj.ijava.dto.Comment;
import prj.ijava.dto.NavInfo;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
//	public static int REC_PER_PAGE = 12;
//	public static int NAV_PER_PAGE = 5;

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF8");
		// PrintWriter를 꺼내기 전에 response의 인코딩을 설정
		response.setCharacterEncoding("UTF8");	
		
		// 포인트: givePoint
		int givePoint = 0;

		try {

			// URL 패턴 획득
			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();

			System.out.println("@RequestURI: " + requestURI);
			System.out.println("@ContextPath: " + contextPath);

			String command = requestURI.substring(contextPath.length());
			System.out.println("@Command: " + command);

			boolean isRedirect = true;

			// 리퀘스트 안의 데이터를 살려야되는 경우: 포워딩
			// 그 외의 경우: 리다이렉팅

			// DAO: 공용 영역에 만들어 놓음
			BoardDAO bdao = new BoardDAO();
			MemberDAO mdao = new MemberDAO();
			MemberAdvDAO mvdao = new MemberAdvDAO();
			BoardInfoDAO bidao = new BoardInfoDAO();

			String destination = null;
			
			if(command.equals("/list.board")) {
				String boardId = request.getParameter("id");
				
				int currentPage = 1;
				if(request.getParameter("page") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("page"));
				}
				
				if(boardId == null || boardId.equals("")) {
					System.out.println("id가 필요합니다.");
					
				} else {
					
					// 게시판 정보
					BoardInfo bi = bidao.getAnBoardInfo(boardId);
					int start = currentPage * bi.getRecPerPage() - (bi.getRecPerPage() - 1);
					int end = currentPage * bi.getRecPerPage();
					
					List<Article> list = bdao.getListByBoardId(boardId, start, end);
					
					// 내비게이터
					List<NavInfo> navList = bdao.getPageNavInfo(boardId, bi.getRecPerPage(), bi.getNavPerPage(), currentPage);
					
					// 코멘트 수
					List<Integer> commentCount = bdao.getCommentCountsByBoardId(boardId, start, end);					
				
					
					// 레벨 마크업
					List<String> levelMarkup1 = new ArrayList<>();
					for(int i = 0; i < list.size(); i++) {
						levelMarkup1.add(mvdao.getLevelMarkup(list.get(i).getWriterEmail()));
						System.out.println(mvdao.getLevelMarkup(list.get(i).getWriterEmail()));
					}					
					
					
					request.setAttribute("outputList", list);
					request.setAttribute("navi", navList);
					request.setAttribute("boardInfo", bi);
					request.setAttribute("commentCount", commentCount);
					request.setAttribute("levelMarkup1", levelMarkup1);
					
					isRedirect = false;
					destination = "board/board.jsp";
				}
				
			} else if(command.equals("/read.board")) {
				long seq = Long.parseLong(request.getParameter("seq"));
				
				String boardId = request.getParameter("id");
				BoardInfo bi = bidao.getAnBoardInfo(boardId);
				
				Article a = bdao.getOneArticle(seq);	
				
				long count = a.getReadCount();
				int result = bdao.updateReadCount(seq, ++count);
				String photo64 = mvdao.getMemberPhoto(a.getWriterEmail());
				String gradeName = mvdao.getMemberGradeName(a.getWriterEmail());
				int memberLevel = mvdao.getMemberLevel(a.getWriterEmail());
				String levelMarkup2 = mvdao.getLevelMarkup(a.getWriterEmail(), "LV. ");
				System.out.println("@gn: " + gradeName);
				
				System.out.println(result);
				
				List<Comment> commentList = bdao.getComments(seq);		
				List<String> nickList = new ArrayList<>();
				List<String> levelList = new ArrayList<>();
				List<String> gradeNameList = new ArrayList<>();
				
			
				for(int i = 0; i < commentList.size(); i++) {
					nickList.add(mvdao.getMemberNick(commentList.get(i).getWriterEmail()));
				}
				
				for(int i = 0; i < commentList.size(); i++) {
					levelList.add(mvdao.getLevelMarkup(commentList.get(i).getWriterEmail(), "LV. "));
				}
				
				for(int i = 0; i < commentList.size(); i++) {
					gradeNameList.add(mvdao.getMemberGradeName(commentList.get(i).getWriterEmail()));
				}
		
				request.setAttribute("outputArticle", a);
				request.setAttribute("outputComments", commentList);
				request.setAttribute("outputNicks", nickList);
				request.setAttribute("photo64", photo64);
				request.setAttribute("gradeName", gradeName);
				request.setAttribute("memberLevel", memberLevel);
				request.setAttribute("levelList", levelList);
				request.setAttribute("gradeNameList", gradeNameList);
				request.setAttribute("levelMarkup2", levelMarkup2);
				request.setAttribute("boardInfo", bi);
				
				isRedirect = false;
				destination = "board/read.jsp";
				
			}  
			else if (command.equals("/writeProc.board")) {	
		        
		        System.out.println(request.getParameter("boardId"));
		        System.out.println(request.getParameter("contents"));
		        System.out.println(request.getParameter("title"));
		        System.out.println(request.getParameter("writerEmail"));
		        System.out.println("==");
		        
		        Article a = new Article();
		        a.setBoardId(request.getParameter("boardId"));
		        a.setContents(request.getParameter("contents"));
		        a.setTitle(request.getParameter("title"));
		        a.setWriterEmail(request.getParameter("writerEmail"));
		        
		        int result = bdao.insertAnArticle(a);
		        if(result == 1)	givePoint = 200;
		        
		        isRedirect = false;
				destination = "list.board?id=" + request.getParameter("boardId") + "&page=1";
		        
			} else if (command.equals("/dummywrjte.board")) {		
				
				for(int i = 1; i <= 200; i++) {
					Article a = new Article();
					a.setBoardId("free");
					a.setCategory("");
					a.setContents("ㅗㅗ");
					a.setTitle("제목" + i);
					a.setWriterEmail("first@fist.com");
					
					try {
						bdao.insertAnArticle(a);
						Thread.sleep(100);
					} catch (Exception e) {				
						e.printStackTrace();
					}
				}		
				
				
		        
			} else if (command.equals("/test.board")) {
				List<NavInfo> list = bdao.getPageNavInfo("free", 12, 5, 7);
				for (NavInfo s : list) {
					System.out.println(s);
				}
			}
			
			else if (command.equals("/commentWriteProc.board")) {			        
	
		        
		        Comment c = new Comment();
		        c.setBoardSeq(Long.parseLong(request.getParameter("no")));
		        c.setContents(request.getParameter("comment"));
		        c.setWriterEmail((String)request.getSession().getAttribute("currentLoginUser"));
		        
		        
		        int result = bdao.insertAnComment(c);
		        if(result == 1)	givePoint = 50;
		        
		        isRedirect = false;
				destination = "read.board?id=" + request.getParameter("boardId") + "&page=" + request.getParameter("page") + "&seq=" + request.getParameter("no");
		        
			}
			
				else if (command.equals("/deleteProc.board")) {		
					
					System.out.println("@req setc: " + request.getParameter("seq"));
					
				String clu = (String)request.getSession().getAttribute("currentLoginUser");
				int seq = Integer.parseInt(request.getParameter("seq").toString());
				
				int result = 0;
				
				if(clu != null) {
					result = bdao.deleteAnArticle(seq, clu);
				}
				
				if(result == 1)	givePoint = -200;
				
				request.setAttribute("result", result);			
				request.setAttribute("id", request.getParameter("id"));		
		        
		        isRedirect = false;
				destination = "board/delete_after.jsp";
		        
			}
			
			// 포인트 주기
			if(request.getSession().getAttribute("currentLoginUser") != null) {
				String currentLoginUser = request.getSession().getAttribute("currentLoginUser").toString();
				mdao.updateMemberPoint(currentLoginUser, (int)mdao.getMemberPoint(currentLoginUser) + givePoint);
			}			

			// rd는 바깥으로 꺼냄

			if(isRedirect) {                
				response.sendRedirect(destination);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(destination);
				rd.forward(request, response);
			}

		} catch (Exception e) {

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
