package prj.ijava.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prj.ijava.compiler.RunBatch;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/CodingAjaxController")
public class CodingAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF8");
		// PrintWriter를 꺼내기 전에 response의 인코딩을 설정
		response.setCharacterEncoding("UTF8");
		PrintWriter xout = response.getWriter();   	
		
		String user = "guest" + (int)( Math.random() * 1000 + 1);
		if(request.getSession().getAttribute("cluSeq") != null) {
			user = "user" + request.getSession().getAttribute("cluSeq");
		}		
		
		StringBuilder sb = new StringBuilder();
		sb.append(
				"\r\n" + 
				"import java.io.File;\r\n" + 
				"import java.io.FileNotFoundException;\r\n" + 
				"import java.io.FileOutputStream;\r\n" + 
				"import java.io.PrintStream;\r\n" + 
				"\r\n" + 
				"public class Template_"+ user  + " {	\r\n" + 
				"	\r\n" + 
				"	public static void main(String[] args) throws FileNotFoundException {		\r\n" + 
				"		\r\n" + 
				"		// 외부 라이브러리 사용 불가 (스캐너 포함)\r\n" + 
				"		// 기초적인 수학 라이브러리 같은 것들만 사용 가능하게\r\n" + 
				"		// 입력 시 세미콜론(;) 사용 안해도 되게 (기존 자바와 구분짓기 위함)\r\n" + 
				"		\r\n" + 
				"		String path = \"D:/ServerResource/output.disp."+ user +".txt\";\r\n" + 
				"		print(path);\r\n" + 
				"		outToFile(path);\r\n" + 
				"		\r\n" + 
				"		// ===================================\r\n" + 
				"		");

		sb.append((String)request.getParameter("codeMainArea").replace("Runtime.exec","").replace("class",""));


		sb.append("\r\n// ===================================\r\n" + 
				"		\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	// 단축 코드\r\n" + 
				"	public static void outToFile(String outputFileName) throws FileNotFoundException{\r\n" + 
				"		File file = new File(outputFileName);\r\n" + 
				"		FileOutputStream fos = new FileOutputStream(file);\r\n" + 
				"		PrintStream ps = new PrintStream(fos);\r\n" + 
				"		System.setOut(ps);\r\n" + 
				"	}	\r\n" + 
				"	\r\n" + 
				"	public static void print(Object o){\r\n" + 
				"		System.out.println(o);\r\n" + 
				"	}\r\n" +  
				 
				"	\r\n" + 
				"	public static void print() {\r\n" + 
				"		System.out.println();\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	public static void prin(Object o) {\r\n" + 
				"		System.out.print(o);\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	public static void printFor(int start, int end, int step, Object o) {\r\n" + 
				"		for(int i = start; i <= end; i += step ) {\r\n" + 
				"			System.out.println(o);\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	public static void prinFor(int start, int end, int step, Object o) {\r\n" + 
				"		for(int i = start; i <= end; i += step ) {\r\n" + 
				"			System.out.print(o);\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				
				(String)request.getParameter("codeFunctionArea").replace("$fn", "public static") +
				
				"	" +
				"}");

		System.out.println(CodingAjaxController.class.getResource("").getPath());
		Properties props = new Properties();
		props.load(new java.io.BufferedInputStream(getServletContext().getResourceAsStream("/WEB-INF/path.properties")));
       
		System.out.println("@dmj: " + props.getProperty("dummyJavaPath"));
		File file = new File(props.getProperty("dummyJavaPath") +"Template_" + user + ".java");
		System.out.println(file);
		try(FileWriter fw = new FileWriter(file);
				BufferedWriter br = new BufferedWriter(fw)){

			br.write(sb.toString());

		}catch(Exception e) {}


		// System.out.println(sb.toString());
		new RunBatch().run(props.getProperty("dummyJavaPath") + "auto.bat", user);
		
		String out = "";
	    try (    FileInputStream fis = new FileInputStream("D:/ServerResource/output.disp." + user + ".txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, "MS949"));)
        {            
            String line;
            
            while ( ( line = br.readLine() ) != null ) {
            	 System.out.println(line);
            	 out += (line + "\r\n");
            }        
            
            
        }
        catch(Exception e)    {} 
	    
	    System.out.println(out);
	    request.setAttribute("outdisp", out);  
	    xout.print(out);
	
	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
