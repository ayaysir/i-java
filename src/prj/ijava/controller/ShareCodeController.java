package prj.ijava.controller;

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
import prj.ijava.dao.QnaDAO;
import prj.ijava.dao.ShareCodeDAO;
import prj.ijava.dto.BoardInfo;
import prj.ijava.dto.Code;
import prj.ijava.dto.Comment;
import prj.ijava.dto.NavInfo;
import prj.ijava.dto.Qna;
import prj.ijava.dto.Reply;


@WebServlet("*.share")
public class ShareCodeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	//	public static int REC_PER_PAGE = 12;
	//	public static int NAV_PER_PAGE = 5;

	public ShareCodeController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF8");
		// PrintWriter를 꺼내기 전에 response의 인코딩을 설정
		response.setCharacterEncoding("UTF8");	

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
			MemberAdvDAO mvdao = new MemberAdvDAO();
			BoardInfoDAO bidao = new BoardInfoDAO();
			QnaDAO qdao = new QnaDAO();
			ShareCodeDAO shdao = new ShareCodeDAO();

			String destination = null;

			if(command.equals("/list.share")) {
				String share = "share";

				int currentPage = 1;
				if(request.getParameter("page") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("page"));
				}	

				// 게시판 정보
				BoardInfo bi = bidao.getAnBoardInfo(share);
				int start = currentPage * bi.getRecPerPage() - (bi.getRecPerPage() - 1);
				int end = currentPage * bi.getRecPerPage();

				List<Code> list = shdao.getListByRange(start, end);

				// 내비게이터
				List<NavInfo> navList = shdao.getPageNavInfo(bi.getRecPerPage(), bi.getNavPerPage(), currentPage);

				// 코멘트 수
				List<Integer> commentCount = shdao.getCommentCounts(start, end);

				// 레벨 마크업
				List<String> levelMarkup1 = new ArrayList<>();
				for(int i = 0; i < list.size(); i++) {
					levelMarkup1.add(mvdao.getLevelMarkup(list.get(i).getWriterEmail()));
					System.out.println(mvdao.getLevelMarkup(list.get(i).getWriterEmail()));
				}		
				
				// 태그 쪼개기
				List<String> splitedTags = new ArrayList<>();				
				for(int i = 0; i < list.size(); i++) {
					
					System.out.println("@@@1: " + list.get(i).getTags());
					if(list.get(i).getTags() == null ) {
						splitedTags.add("");
						System.out.println("@@@@gggg@@@");
						continue;
					}
					
					String aTag = "";		
					
					System.out.println("@@@2: " + list.get(i).getTags().split(","));
					if( list.get(i).getTags().contains(",") ) {
						String[] temp =	list.get(i).getTags().split(",");
						System.out.println("@@@2: " + temp);						
						
						for(int j = 0; j < temp.length; j++)
						{
							aTag += ("#" + temp[j]);						
						}			
					} else {
						aTag = "#" + list.get(i).getTags();
					}
						
					splitedTags.add(aTag);
				}

				request.setAttribute("outputList", list);
				request.setAttribute("navi", navList);
				request.setAttribute("boardInfo", bi);
				request.setAttribute("commentCount", commentCount);
				request.setAttribute("levelMarkup1", levelMarkup1);
				request.setAttribute("splitedTags", splitedTags);

				isRedirect = false;
				destination = "share/share.jsp";
			}
			
			if(command.equals("/privateList.share")) {
				String share = "share_my";

				int currentPage = 1;
				if(request.getParameter("page") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("page"));
				}	

				// 게시판 정보
				BoardInfo bi = bidao.getAnBoardInfo(share);
				int start = currentPage * bi.getRecPerPage() - (bi.getRecPerPage() - 1);
				int end = currentPage * bi.getRecPerPage();

				List<Code> list = shdao.getListByRange(start, end, (String)request.getSession().getAttribute("currentLoginUser"));

				// 내비게이터
				List<NavInfo> navList = shdao.getPageNavInfo(bi.getRecPerPage(), bi.getNavPerPage(), currentPage, (String)request.getSession().getAttribute("currentLoginUser"));

				// 코멘트 수
				List<Integer> commentCount = shdao.getCommentCounts(start, end, (String)request.getSession().getAttribute("currentLoginUser"));

				// 레벨 마크업
//				List<String> levelMarkup1 = new ArrayList<>();
//				for(int i = 0; i < list.size(); i++) {
//					levelMarkup1.add(mvdao.getLevelMarkup(list.get(i).getWriterEmail()));
//					System.out.println(mvdao.getLevelMarkup(list.get(i).getWriterEmail()));
//				}		
				
				// 태그 쪼개기
				List<String> splitedTags = new ArrayList<>();				
				for(int i = 0; i < list.size(); i++) {
					
					System.out.println("@@@1: " + list.get(i).getTags());
					if(list.get(i).getTags() == null ) {
						splitedTags.add("");
						System.out.println("@@@@gggg@@@");
						continue;
					}
					
					String aTag = "";		
					
					System.out.println("@@@2: " + list.get(i).getTags().split(","));
					if( list.get(i).getTags().contains(",") ) {
						String[] temp =	list.get(i).getTags().split(",");
						System.out.println("@@@2: " + temp);						
						
						for(int j = 0; j < temp.length; j++)
						{
							aTag += ("#" + temp[j] + " ");						
						}			
					} else {
						aTag = "#" + list.get(i).getTags();
					}
						
					splitedTags.add(aTag);
				}

				request.setAttribute("outputList", list);
				request.setAttribute("navi", navList);
				request.setAttribute("boardInfo", bi);
				request.setAttribute("commentCount", commentCount);
				//request.setAttribute("levelMarkup1", levelMarkup1);
				request.setAttribute("splitedTags", splitedTags);

				isRedirect = false;
				destination = "share/share_private.jsp";
			}

			else if(command.equals("/read.share")) {
				long seq = Long.parseLong(request.getParameter("seq"));

				Code c = shdao.getOneCode(seq);	

				int count = c.getReadCount();
				int result = shdao.updateReadCount(seq, ++count);
				String photo64 = mvdao.getMemberPhoto(c.getWriterEmail());
				String gradeName = mvdao.getMemberGradeName(c.getWriterEmail());
				int memberLevel = mvdao.getMemberLevel(c.getWriterEmail());
				String levelMarkup2 = mvdao.getLevelMarkup(c.getWriterEmail(), "LV. ");
				boolean isAlreadyLike = shdao.isThereLike(seq, (String)request.getSession().getAttribute("currentLoginUser"));
				System.out.println("@gn: " + gradeName);

				System.out.println(result);

				List<Comment> commentList = shdao.getComments(seq);		
				List<String> nickList = new ArrayList<>();
				List<String> levelList = new ArrayList<>();
				List<String> gradeNameList = new ArrayList<>();
				List<Reply> replyList = qdao.getReplies(seq);


				for(int i = 0; i < commentList.size(); i++) {
					nickList.add(mvdao.getMemberNick(commentList.get(i).getWriterEmail()));
				}

				for(int i = 0; i < commentList.size(); i++) {
					levelList.add(mvdao.getLevelMarkup(commentList.get(i).getWriterEmail(), "LV. "));
				}

				for(int i = 0; i < commentList.size(); i++) {
					gradeNameList.add(mvdao.getMemberGradeName(commentList.get(i).getWriterEmail()));
				}
				
				String aTag = "";
				if(c.getTags() != null ) {
					if( c.getTags().contains(",") ) {
						String[] temp =	c.getTags().split(",");					
						
						for(int j = 0; j < temp.length; j++)
						{
							aTag += ("#" + temp[j] + " ");						
						}			
					} else {
						aTag = "#" + c.getTags();
					}
				} 
				
				

				request.setAttribute("outputCode", c);
				request.setAttribute("outputComments", commentList);
				request.setAttribute("outputNicks", nickList);
				request.setAttribute("photo64", photo64);
				request.setAttribute("gradeName", gradeName);
				request.setAttribute("memberLevel", memberLevel);
				request.setAttribute("levelList", levelList);
				request.setAttribute("gradeNameList", gradeNameList);
				request.setAttribute("levelMarkup2", levelMarkup2);
				request.setAttribute("replyList", replyList);
				request.setAttribute("isAlreadyLike", isAlreadyLike);
				request.setAttribute("aTag", aTag);

				isRedirect = false;
				destination = "share/read.jsp";

			}  
			else if (command.equals("/writeProc.share")) {	    

				Code c = new Code();
				c.setWriterEmail(request.getParameter("writerEmail"));
				c.setTitle(request.getParameter("title"));
				c.setCtMain(request.getParameter("shmain"));
				c.setCtFunction(request.getParameter("shfunc"));
				c.setCtResult(request.getParameter("shresult"));
				c.setCtEtc(request.getParameter("contents"));
				c.setTags(request.getParameter("tags"));
				c.setIsPublished(request.getParameter("isPublish"));

				int result = shdao.insertAnCode(c);

				isRedirect = false;
				destination = "list.share?page=1";

			} 

		

			else if (command.equals("/like.share")) {

				long seq = Long.parseLong(request.getParameter("seq"));
				String email = (String)request.getSession().getAttribute("currentLoginUser");

				boolean isAlreadyExist = shdao.isThereLike(seq, email);
				
				if(isAlreadyExist) {
					shdao.deleteAnLikeStatus(seq, email);
					shdao.updateLikeCount(seq, shdao.getOneCode(seq).getLikeCount() - 1);
				} else {
					shdao.insertAnLikeStatus(seq, email);
					shdao.updateLikeCount(seq, shdao.getOneCode(seq).getLikeCount() + 1);
				}
				
				isRedirect = false;
				destination = "read.share?seq=" + request.getParameter("seq") + "&page=" + request.getParameter("page");

			} 
		


			else if (command.equals("/dummywrjte.qna")) {		

				for(int i = 1; i <= 200; i++) {
					Qna q = new Qna();
					q.setCtEtc("a");
					q.setCtFunction("a");
					q.setCtMain("a");
					q.setCtResult("a");
					q.setLikeCount(1);
					q.setNickname("퍼스트");
					q.setReadCount(2);
					q.setTitle("제목: " + i);
					q.setWriterEmail("first@fist.com");

					try {
						qdao.insertAnQna(q);
						Thread.sleep(100);
					} catch (Exception e) {				
						e.printStackTrace();
					}
				}		        
			} 


			else if (command.equals("/test.board")) {
				List<NavInfo> list = bdao.getPageNavInfo("free", 12, 5, 7);
				for (NavInfo s : list) {
					System.out.println(s);
				}
			}

			else if (command.equals("/commentWriteProc.share")) {			        


				Comment c = new Comment();
				c.setBoardSeq(Long.parseLong(request.getParameter("no")));
				c.setContents(request.getParameter("comment"));
				c.setWriterEmail((String)request.getSession().getAttribute("currentLoginUser"));


				int result = shdao.insertAnComment(c);

				isRedirect = false;
				destination = "read.share?" + "page=" + request.getParameter("page") + "&seq=" + request.getParameter("no");

			}

			else if (command.equals("/deleteProc.share")) {		

				System.out.println("@req setc: " + request.getParameter("seq"));

				String clu = (String)request.getSession().getAttribute("currentLoginUser");
				int seq = Integer.parseInt(request.getParameter("seq").toString());

				int result = 0;

				if(clu != null) {
					result = shdao.deleteAnCode(seq, clu);
				}

				request.setAttribute("result", result);		

				isRedirect = false;
				destination = "share/delete_after.jsp";

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
