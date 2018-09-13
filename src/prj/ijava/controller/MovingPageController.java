package prj.ijava.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import prj.ijava.dao.MemberAdvDAO;
import prj.ijava.dao.MemberDAO;
import prj.ijava.dto.Article;
import prj.ijava.dto.Member;
import prj.ijava.dto.MemberAdv;
import prj.ijava.dto.NavInfo;
import prj.ijava.util.StringToHash;


@WebServlet("*.page")
public class MovingPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public MovingPageController() {
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

			String destination = null;

			if(command.equals("/main.page")) {				
				
				// ===== 옵션 =====
				isRedirect = false;
				destination = "floater/index.jsp";	

			} 
			
			else if(command.equals("/join.page")) {				
				
				// ===== 옵션 =====
				isRedirect = false;
				destination = "join/join.jsp";	

			} 
			
			else if(command.equals("/read.page")) {
				
			
			}
			
			else if(command.equals("/login.page")) {
				
				if(request.getParameter("redir") != null)
					request.setAttribute("redir", request.getParameter("redir"));
				else if (request.getAttribute("redir") != null) {
					request.setAttribute("redir", request.getAttribute("redir"));
				} else
					request.setAttribute("redir", request.getContextPath() + "/main.page");
				
				isRedirect = false;
				destination = "login/login.jsp";
				
			}
			
			else if(command.equals("/coding.page")) {
				
				// **로그인 필요**
				if(request.getSession().getAttribute("currentLoginUser") == null) {
					response.sendRedirect("login.page?redir=" + request.getContextPath() + "/coding.page");
				} 
				// ***********
				
				isRedirect = false;
				destination = "coding/coding.jsp";
				
			}
			
			else if (command.equals("/write.page")) {
				
				// **로그인 필요**
				if(request.getSession().getAttribute("currentLoginUser") == null) {
					response.sendRedirect("login.page?redir=" + request.getContextPath() + "/write.page?id=" + request.getParameter("id"));
				} 
				// ***********
				
				isRedirect = false;
				destination = "board/write.jsp?id=" + request.getParameter("id");
		        
			}
			
			else if (command.equals("/question.page")) {
				
				// **로그인 필요**
				if(request.getSession().getAttribute("currentLoginUser") == null) {
					response.sendRedirect("login.page?redir=" + request.getContextPath() + "/coding.page");
				} 
				// ***********
				
				isRedirect = false;
				destination = "qna/write.jsp";
		        
			}
			
			else if (command.equals("/writeReply.page")) {
				
				// **로그인 필요**
				if(request.getSession().getAttribute("currentLoginUser") == null) {
					response.sendRedirect("login.page?redir=" + request.getContextPath() + "/writeReply.page?seq=" + request.getParameter("seq"));
				} 
				// ***********
				
				isRedirect = false;
				destination = "qna/writeReply.jsp?seq=" + request.getParameter("seq") + "&page=" + request.getParameter("page");
		        
			}
			
			else if (command.equals("/shareCode.page")) {
				
				// **로그인 필요**
				if(request.getSession().getAttribute("currentLoginUser") == null) {
					response.sendRedirect("login.page?redir=" + request.getContextPath() + "/coding.page");
				} 
				// ***********
				
				isRedirect = false;
				destination = "share/write.jsp";
		        
			}
			
			else if (command.equals("/makeStage.page")) {
				
				// **로그인 필요**
				if(request.getSession().getAttribute("currentLoginUser") == null) {
					response.sendRedirect(request.getContextPath() + "/error.htm");
				} 
				// ***********				
				
				isRedirect = false;
				destination = "stage/write_stage.jsp";
		        
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
