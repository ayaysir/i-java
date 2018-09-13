
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Template_user51 {	
	
	public static void main(String[] args) throws FileNotFoundException {		
		
		// 외부 라이브러리 사용 불가 (스캐너 포함)
		// 기초적인 수학 라이브러리 같은 것들만 사용 가능하게
		// 입력 시 세미콜론(;) 사용 안해도 되게 (기존 자바와 구분짓기 위함)
		
		String path = "D:/ServerResource/output.disp.user51.txt";
		print(path);
		outToFile(path);
		
		// ===================================
		/* 코드 작성자: 닉네임 */
String plus = "+";
String minus = "-";
String mul = "*";
String div = "/";
 
print( calc1( 

	20	,    
	50	 ,    
	minus
  ) );


 
// ===================================
		
	}
	
	// 단축 코드
	public static void outToFile(String outputFileName) throws FileNotFoundException{
		File file = new File(outputFileName);
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
	}	
	
	public static void print(Object o){
		System.out.println(o);
	}
	
	public static void print() {
		System.out.println();
	}
	
	public static void prin(Object o) {
		System.out.print(o);
	}
	
	public static void printFor(int start, int end, int step, Object o) {
		for(int i = start; i <= end; i += step ) {
			System.out.println(o);
		}
	}
	
	public static void prinFor(int start, int end, int step, Object o) {
		for(int i = start; i <= end; i += step ) {
			System.out.print(o);
		}
	}
/* 함수(메소드) 작성자: 닉네임 */
 
public static int calc1(int num1, int num2, String oper) {
int result = 0;
	switch(oper) {
		case "+":
		result = (num1 + num2);
		break;
		case "-":
		result =  (num1 - num2);
		break;
		case "*":
		result = (num1 * num2);
		break;
		case "/":
		result =  (num1 / num2);
		break;
	}
	return result;
} 

	}