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
import prj.ijava.dto.Article;
import prj.ijava.dto.BoardInfo;
import prj.ijava.dto.Comment;
import prj.ijava.dto.NavInfo;
import prj.ijava.dto.Qna;
import prj.ijava.dto.Reply;


@WebServlet("*.qna")
public class QnaController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	//	public static int REC_PER_PAGE = 12;
	//	public static int NAV_PER_PAGE = 5;

	public QnaController() {
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

			String destination = null;

			if(command.equals("/list.qna")) {
				String qna = "qna";

				int currentPage = 1;
				if(request.getParameter("page") == null) {
					currentPage = 1;
				} else {
					currentPage = Integer.parseInt(request.getParameter("page"));
				}	

				// 게시판 정보
				BoardInfo bi = bidao.getAnBoardInfo(qna);
				int start = currentPage * bi.getRecPerPage() - (bi.getRecPerPage() - 1);
				int end = currentPage * bi.getRecPerPage();

				List<Qna> list = qdao.getListByRange(start, end);

				// 내비게이터
				List<NavInfo> navList = qdao.getPageNavInfo(bi.getRecPerPage(), bi.getNavPerPage(), currentPage);

				// 코멘트 수
				List<Integer> commentCount = qdao.getCommentCounts(start, end);

				// 레벨
				//List<Integer> levels = bdao.getLevelsByBoardId(boardId, start, end);

				// 레벨 마크업
				List<String> levelMarkup1 = new ArrayList<>();
				for(int i = 0; i < list.size(); i++) {
					levelMarkup1.add(mvdao.getLevelMarkup(list.get(i).getWriterEmail()));
					System.out.println(mvdao.getLevelMarkup(list.get(i).getWriterEmail()));
				}				

				// 답변 수
				List<Integer> replyCount = new ArrayList<>();
				for(int i = 0; i < list.size(); i++) {
					replyCount.add(qdao.getReplyCount(list.get(i).getSeq()));
				}



				request.setAttribute("outputList", list);
				request.setAttribute("navi", navList);
				request.setAttribute("boardInfo", bi);
				request.setAttribute("commentCount", commentCount);
				request.setAttribute("levelMarkup1", levelMarkup1);
				request.setAttribute("replyCount", replyCount);

				isRedirect = false;
				destination = "qna/qna.jsp";
			}

			else if(command.equals("/read.qna")) {
				long seq = Long.parseLong(request.getParameter("seq"));

				Qna q = qdao.getOneQna(seq);	

				int count = q.getReadCount();
				int result = qdao.updateReadCount(seq, ++count);
				String photo64 = mvdao.getMemberPhoto(q.getWriterEmail());
				String gradeName = mvdao.getMemberGradeName(q.getWriterEmail());
				int memberLevel = mvdao.getMemberLevel(q.getWriterEmail());
				String levelMarkup2 = mvdao.getLevelMarkup(q.getWriterEmail(), "LV. ");
				boolean isAlreadyLike = qdao.isThereLike(seq, (String)request.getSession().getAttribute("currentLoginUser"));
				System.out.println("@gn: " + gradeName);

				System.out.println(result);

				List<Comment> commentList = qdao.getComments(seq);		
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

				request.setAttribute("outputQna", q);
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


				// 답글 관련 부분

				List<String> replyNickList = new ArrayList<>();
				for(int i = 0; i < replyList.size(); i++) {
					replyNickList.add(mvdao.getMemberNick(replyList.get(i).getWriterEmail()));
				}
				request.setAttribute("replyNickList", replyNickList);

				List<String> replyLevelList = new ArrayList<>();
				for(int i = 0; i < replyList.size(); i++) {
					replyLevelList.add(mvdao.getLevelMarkup(replyList.get(i).getWriterEmail(), "LV. "));
				}
				request.setAttribute("replyLevelList", replyLevelList);

				List<String> replyPhotoList = new ArrayList<>();
				for(int i = 0; i < replyList.size(); i++) {
					replyPhotoList.add(mvdao.getMemberPhoto(replyList.get(i).getWriterEmail()));
				}
				request.setAttribute("replyPhotoList", replyPhotoList);
				
				List<Boolean> replyLikeList = new ArrayList<>();
				for(int i = 0; i < replyList.size(); i++) {
					replyLikeList.add(qdao.isThereReplyLike(replyList.get(i).getRepSeq(), (String)request.getSession().getAttribute("currentLoginUser")));
				}
				request.setAttribute("replyLikeList", replyLikeList);

				isRedirect = false;
				destination = "qna/read.jsp";

			}  
			else if (command.equals("/writeProc.qna")) {

				String path = request.getServletContext().getRealPath("/file/lib/");			    

				Qna q = new Qna();
				q.setWriterEmail(request.getParameter("writerEmail"));
				q.setTitle(request.getParameter("title"));
				q.setCtMain(request.getParameter("qmain"));
				q.setCtFunction(request.getParameter("qfunc"));
				q.setCtResult(request.getParameter("qresult"));
				q.setCtEtc(request.getParameter("contents"));

				int result = qdao.insertAnQna(q);

				isRedirect = false;
				destination = "list.qna?page=1";

			} 

			else if (command.equals("/replyWriteProc.qna")) {

				Reply r = new Reply();
				r.setWriterEmail(request.getParameter("writerEmail"));
				r.setQnaSeq(Long.parseLong(request.getParameter("seq")));
				r.setCtMain(request.getParameter("qmain"));
				r.setCtFunction(request.getParameter("qfunc"));
				r.setCtResult(request.getParameter("qresult"));
				r.setCtEtc(request.getParameter("qetc"));

				int result = qdao.insertAnReply(r);

				isRedirect = false;
				destination = "read.qna?seq=" + request.getParameter("seq");

			} 

			else if (command.equals("/like.qna")) {
				
				long seq = Long.parseLong(request.getParameter("seq"));
				String email = (String)request.getSession().getAttribute("currentLoginUser");
				
				// **로그인 필요**
				if(request.getSession().getAttribute("currentLoginUser") == null) {
					String url = request.getContextPath() + "/read.qna?page=" + request.getParameter("page") + "&amp;seq=" + request.getParameter("seq");
					System.out.println("@@url: " + url);
					request.setAttribute("redir", url);
					//response.sendRedirect("login.page?redir=" + url);
					RequestDispatcher rd = request.getRequestDispatcher("login.page");
					rd.forward(request, response);
					return;
				} 
				// ***********

				
				boolean isAlreadyExist = qdao.isThereLike(seq, email);
				
				if(isAlreadyExist) {
					qdao.deleteAnLikeStatus(seq, email);
					qdao.updateLikeCount(seq, qdao.getOneQna(seq).getLikeCount() - 1);
				} else {
					qdao.insertAnLikeStatus(seq, email);
					qdao.updateLikeCount(seq, qdao.getOneQna(seq).getLikeCount() + 1);
				}
				
				isRedirect = false;
				destination = "read.qna?seq=" + request.getParameter("seq") + "&page=" + request.getParameter("page");

			} 
			
			else if (command.equals("/replyLike.qna")) {

				long seq = Long.parseLong(request.getParameter("repseq"));
				String email = (String)request.getSession().getAttribute("currentLoginUser");
				
				// **로그인 필요**
				if(request.getSession().getAttribute("currentLoginUser") == null) {
					String url = request.getContextPath() + "/read.qna?repseq=" + request.getParameter("repseq") + "&amp;page=" + request.getParameter("page") + "&amp;seq=" + request.getParameter("seq");
					System.out.println("@@url: " + url);
					request.setAttribute("redir", url);
					//response.sendRedirect("login.page?redir=" + url);
					RequestDispatcher rd = request.getRequestDispatcher("login.page");
					rd.forward(request, response);
					return;
				} 
				// ***********

				boolean isAlreadyExist = qdao.isThereReplyLike(seq, email);
				System.out.println("@@gasfdsfas@@");
				
				if(isAlreadyExist) {
					qdao.deleteAnReplyLikeStatus(seq, email);
					qdao.updateReplyLikeCount(seq, qdao.getReplyLikeCount(seq) - 1);
	
				} else {
					qdao.insertAnReplyLikeStatus(seq, email);
					qdao.updateReplyLikeCount(seq, qdao.getReplyLikeCount(seq) + 1);
				}
				
				isRedirect = false;
				destination = "read.qna?seq=" + request.getParameter("seq") + "&page=" + request.getParameter("page");

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

			else if (command.equals("/commentWriteProc.qna")) {			        


				Comment c = new Comment();
				c.setBoardSeq(Long.parseLong(request.getParameter("no")));
				c.setContents(request.getParameter("comment"));
				c.setWriterEmail((String)request.getSession().getAttribute("currentLoginUser"));


				int result = qdao.insertAnComment(c);

				isRedirect = false;
				destination = "read.qna?" + "page=" + request.getParameter("page") + "&seq=" + request.getParameter("no");

			}

			else if (command.equals("/deleteProc.qna")) {		

				System.out.println("@req setc: " + request.getParameter("seq"));

				String clu = (String)request.getSession().getAttribute("currentLoginUser");
				int seq = Integer.parseInt(request.getParameter("seq").toString());

				int result = 0;

				if(clu != null) {
					result = qdao.deleteAnQna(seq, clu);
				}

				request.setAttribute("result", result);	

				isRedirect = false;
				destination = "qna/delete_after.jsp";

			}
			
			else if (command.equals("/deleteReplyProc.qna")) {		

				System.out.println("@req seq: " + request.getParameter("seq"));
				System.out.println("@req repseq: " + request.getParameter("repseq"));
				System.out.println("@req page: " + request.getParameter("page"));

				String clu = (String)request.getSession().getAttribute("currentLoginUser");
				int seq = Integer.parseInt(request.getParameter("repseq").toString());

				int result = 0;

				if(clu != null) {
					result = qdao.deleteAnReply(seq, clu);
				}

				request.setAttribute("result", result);	

				isRedirect = false;
				destination = "qna/delete_reply_after.jsp?seq=" + request.getParameter("seq") + "&page=" + request.getParameter("page");

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
