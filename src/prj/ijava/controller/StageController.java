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
import prj.ijava.dao.MemberDAO;
import prj.ijava.dao.QnaDAO;
import prj.ijava.dao.ShareCodeDAO;
import prj.ijava.dao.StageDAO;
import prj.ijava.dto.Comment;
import prj.ijava.dto.NavInfo;
import prj.ijava.dto.Qna;
import prj.ijava.dto.Stage;


@WebServlet("*.stage")
public class StageController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public StageController() {
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
			MemberDAO mdao = new MemberDAO();
			QnaDAO qdao = new QnaDAO();
			ShareCodeDAO shdao = new ShareCodeDAO();
			StageDAO stdao = new StageDAO();

			String destination = null;

			if(command.equals("/listForAdmin.stage")) {		

				
				List<Stage> list = stdao.getAllList();	
				
				List<String> colorMarkups = new ArrayList<>();
				for(int i = 0; i < list.size(); i++) {
					colorMarkups.add(stdao.getAnColorMarkup(list.get(i).getSeq()));
				}

				request.setAttribute("outputList", list);
				request.setAttribute("colorMarkups", colorMarkups);

				isRedirect = false;
				destination = "stage/stage_list_admin.jsp";
			}
			
			else if (command.equals("/isWriteRight.stage")) {	 			

				isRedirect = false;
				destination = "stage/write_isright.jsp";

			}
			
			else if (command.equals("/view.stage")) {
				
				// **로그인 필요**
				if(request.getSession().getAttribute("currentLoginUser") == null) {
					response.sendRedirect("login.page?redir=" + request.getContextPath() + "/view.stage");
				} 
				// ***********	
				
				int stNo = 1;
				String currentLoginUser = request.getSession().getAttribute("currentLoginUser").toString();
				
				// ============ 포인트 + 진행 상황 ============ 
				// stage_progress_status에 아무 정보도 없다면 일단 하나 생성한다.
				if(!stdao.isStageStatusExist(currentLoginUser)) {
					stdao.insertAnStageStatus(currentLoginUser, 1);
					System.out.println("@XX@: 아무 정보도 없음");
				} else {
					if(stdao.getAnStageStatus(currentLoginUser) < stdao.getStageCount()) {
						stNo = stdao.getAnStageStatus(currentLoginUser);
						System.out.println("@XX@: 정보가 있음, 스테이지 완료 안함");
					}	else {
						System.out.println("@XX@: 정보가 있음, 스테이지 완료됨");
					}
					
				}				
				
				if(request.getParameter("stn") != null) {	
						stNo = Integer.parseInt((String)request.getParameter("stn"));					
				}						
			
				Stage st = stdao.getAnStage(stNo);
				System.out.println(st.getTitle());
				
				request.setAttribute("st", st);
				request.setAttribute("stn", stNo);

				isRedirect = false;
				destination = "stage/stage_view.jsp";

			} 
			
			else if (command.equals("/check.stage")) {	 
				
				System.out.println("@XX@: " + request.getParameter("stn"));
				
				Stage st = stdao.getAnStage(Integer.parseInt(request.getParameter("stn")));
				String currentLoginUser = request.getSession().getAttribute("currentLoginUser").toString();
				
				boolean isCorrect = false;
				
				if(st.getAnsField() == 1) {
					System.out.println("메인만 검사");
					String inputCode = request.getParameter("stgMain");
					String inputResult = request.getParameter("stgResult");
					System.out.println(inputCode);
					
					System.out.println("@StgResut: " + inputResult);
					
					
					if(st.getAnsType() == 1) {
						if(st.getAnsMain().replace(" ","").equalsIgnoreCase(inputCode.replace(" ", "")))	isCorrect = true;
					} else if(st.getAnsType() == 3) {
						if(st.getAnsResult().replace(" ","").equalsIgnoreCase(inputResult.replace(" ", "")))	isCorrect = true;
					}
					
				}
				else if(st.getAnsField() == 4) {
					System.out.println("답 체크 안하고 그냥 통과시켜줌");
					isCorrect = true;
				}
				
				boolean isFinale = false;
				if(st.getAbsOrder() == stdao.getStageCount())	isFinale = true;
				
				boolean isFirstCorrect = false;
				// 포인트 주기 + 진행상황 1 증가
				if(isCorrect) {
					if(stdao.getAnStageStatus(currentLoginUser) < Integer.parseInt(request.getParameter("stn"))){
						mdao.updateMemberPoint(currentLoginUser, (int)mdao.getMemberPoint(currentLoginUser) + st.getGivePoint());						
						stdao.updateAnStageStatus(currentLoginUser, stdao.getAnStageStatus(currentLoginUser) + 1);
						isFirstCorrect = true;
					} 
					
				}				
				
				// ============
				
				
				
				request.setAttribute("isCorrect", isCorrect);		
				request.setAttribute("isFirstCorrect", isFirstCorrect);
				request.setAttribute("st", st);
				request.setAttribute("isFinale", isFinale);
				request.setAttribute("stn", Integer.parseInt(request.getParameter("stn")));
				
				isRedirect = false;
				destination = "stage/stage_after.jsp";

			} 
			
			
			
			else if (command.equals("/writeProc.stage")) {		
				
	
				
				/*	SEQ          NOT NULL NUMBER        
				ABS_ORDER             NUMBER        
				TITLE        NOT NULL VARCHAR2(100) 
				DESCRIPTION           CLOB          
				ANS_MAIN              CLOB          
				ANS_FUNCTION          CLOB          
				ANS_RESULT            CLOB          
				ANSFIELD              NUMBER        
				ANSTYPE               NUMBER        
				GIVE_POINT            NUMBER        
				STAGE_COLOR  NOT NULL NUMBER    
			 */
				
				Stage st = new Stage(0, 0, 
						request.getParameter("title") ,
						request.getParameter("stgDesc") ,
						request.getParameter("stgMain") ,
						request.getParameter("stgFunc") ,
						request.getParameter("stgResult") ,
						Integer.parseInt(request.getParameter("fieldRadio")) ,
						Integer.parseInt(request.getParameter("typeRadio")) ,
						Integer.parseInt(request.getParameter("point")) ,
						Integer.parseInt(request.getParameter("color")) );
				
				int result = stdao.insertAnStage(st);
				

				isRedirect = false;
				destination = "";

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

			else if (command.equals("/deleteProc.board")) {		

				System.out.println("@req setc: " + request.getParameter("seq"));

				String clu = (String)request.getSession().getAttribute("currentLoginUser");
				int seq = Integer.parseInt(request.getParameter("seq").toString());

				int result = 0;

				if(clu != null) {
					result = bdao.deleteAnArticle(seq, clu);
				}

				request.setAttribute("result", result);			
				request.setAttribute("id", request.getParameter("id"));		

				isRedirect = false;
				destination = "board/delete_after.jsp";

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
