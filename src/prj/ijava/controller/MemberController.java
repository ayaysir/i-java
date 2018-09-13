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
import javax.servlet.http.HttpSession;

import prj.ijava.dao.MemberAdvDAO;
import prj.ijava.dao.MemberDAO;
import prj.ijava.dto.Member;
import prj.ijava.dto.MemberAdv;
import prj.ijava.util.StringToHash;


@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public MemberController() {
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
			MemberDAO mdao = new MemberDAO();
			MemberAdvDAO mvdao = new MemberAdvDAO();

			String destination = null;
			StringToHash sth = new StringToHash();
			
			if(command.equals("/joinProcess.member")) {
				
				String email = request.getParameter("email");
				
				sth.setHash(request.getParameter("password"));
				String password = sth.toString();
				
				String tel = request.getParameter("tel");
				String zipcode = request.getParameter("zipcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				String birthday = request.getParameter("birthday");

				System.out.println("@plain: " + request.getParameter("password"));
				System.out.println(email + ":" + password + ":" + tel + ":" + zipcode + ":" + address1 + ":" + address2 + ":" + birthday );

				int result = mdao.insertAnMember(new Member(1L, email, password, tel, zipcode, address1, address2, birthday));	 
				int resultAdv = 0;
				if (result == 1) {
					resultAdv = mvdao.insertAnAdvDummyInfo(email);
				}
				
				request.setAttribute("result", result);
				request.setAttribute("resultAdv", resultAdv);
				request.setAttribute("currentEmail", email);
				isRedirect = false;
				destination = "join/join_after.jsp";
				
			} 
			
			else if(command.equals("/loginProcess.member")) {
				
				String email = request.getParameter("email");
				
				sth = new StringToHash();
				sth.setHash(request.getParameter("password"));
				
				String password = sth.toString();
				// System.out.println("@login:Plainpwd:" + request.getParameter("password"));
				System.out.println("@login:Cipherpwd:" + password);
				int result = mdao.login(email, password); 
				System.out.println("@result: " + result);
				
				HttpSession session = request.getSession();
				if(result == 1) {
					// 세션 설정
					System.out.println("@닉네임: " + mvdao.getMemberNick(email) + ":" + email);
					session.setAttribute("currentLoginUser", email);


						// session.setAttribute("currentLoginUserInfo", mdao.getMember(email, password));
						session.setAttribute("cluNickname", mvdao.getMemberNick(email));
						session.setAttribute("cluSeq", mdao.getMember(email, password).getSeq());
						session.setAttribute("cluPhoto", mvdao.getMemberPhoto(email));
						session.setAttribute("cluGrade", mvdao.getMemberGradeName(email));
						session.setAttribute("cluLevel", mvdao.getMemberLevel(email));
						session.setAttribute("cluPoint", mdao.getMemberPoint(email));
				
				
					
				}
				
				givePoint = 100;

				request.setAttribute("result", result);
				request.setAttribute("redir", request.getParameter("redir"));
				System.out.println("@@redir: "  + request.getParameter("redir"));
				isRedirect = false;
				destination = "/login/login_after.jsp";			
				
			} 
			
			else if(command.equals("/logoutProcess.member")) {
				HttpSession session = request.getSession();
				session.invalidate();
				
				isRedirect = true;
				destination = "main.page";
			}
			
			else if(command.equals("/insertAdvInfoProcess.member")) {
				
				String nickname = request.getParameter("nickname");
				MemberAdv memberAdv = new MemberAdv();
				memberAdv.setEmail(request.getParameter("currentEmail"));
				memberAdv.setNickname(nickname);
				memberAdv.setPhotoOriginalfn("");
				memberAdv.setPhotoSysfn("");
				memberAdv.setPhotoUrl(request.getParameter("photoString"));
				
				int result = mvdao.updateAnAdvInfo(memberAdv);
				System.out.println("@advresult: " + result);
				
				request.setAttribute("result", result);
				isRedirect = false;
				destination = "join/join_steptwo_after.jsp";
				
			}
			
			else if(command.equals("/ranking.member")) {			
				
				List<MemberAdv> list = mdao.getMemberPointRanking100();		
				
				// 레벨 마크업
				List<String> lvmu = new ArrayList<>();
				for(int i = 0; i < list.size(); i++) {
					lvmu.add(mvdao.getLevelMarkup(list.get(i).getEmail(),  "LV. "));
				}	
				
				request.setAttribute("ranklist", list);
				request.setAttribute("lvmu", lvmu);
				isRedirect = false;
				destination = "floater/ranking.jsp";
				
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
