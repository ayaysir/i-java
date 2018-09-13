package prj.ijava.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import prj.ijava.dao.MemberAdvDAO;
import prj.ijava.dao.MemberDAO;

@WebServlet("*.ajax")
public class AJAXController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");
		// PrintWriter를 꺼내기 전에 response의 인코딩을 설정
		response.setCharacterEncoding("UTF8");
		PrintWriter xout = response.getWriter();   	

		try {

			// URL 패턴 획득
			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();

			System.out.println("@RequestURI: " + requestURI);
			System.out.println("@ContextPath: " + contextPath);

			String command = requestURI.substring(contextPath.length());
			System.out.println("@Command: " + command);

			// 리퀘스트 안의 데이터를 살려야되는 경우: 포워딩
			// 그 외의 경우: 리다이렉팅

			// DAO: 공용 영역에 만들어 놓음
			MemberDAO mdao = new MemberDAO();
			MemberAdvDAO mvdao = new MemberAdvDAO();
			
			if(command.equals("/isEmailDuplicated.ajax")) {
				boolean isEmailDuplicated = mdao.isEmailDuplicated(request.getParameter("email"));
				System.out.println(isEmailDuplicated);

				xout.println(isEmailDuplicated);

				
			} 
			
			else if(command.equals("/isNicknameDuplicated.ajax")) {
				boolean isNicknameDuplicated = mvdao.isNicknameDuplicated(request.getParameter("nickname"));
				System.out.println(isNicknameDuplicated);

				xout.println(isNicknameDuplicated);
			}
			
			else if(command.equals("/updateCluSession.ajax")) {
				HttpSession session = request.getSession();
				String email = session.getAttribute("currentLoginUser").toString();
				session.setAttribute("cluNickname", mvdao.getMemberNick(email));
				session.setAttribute("cluPhoto", mvdao.getMemberPhoto(email));
				session.setAttribute("cluGrade", mvdao.getMemberGradeName(email));
				session.setAttribute("cluLevel", mvdao.getMemberLevel(email));
				session.setAttribute("cluPoint", mdao.getMemberPoint(email));
				System.out.println("@AJAX@: 세션 갱신됨");
			}

		} catch (Exception e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
