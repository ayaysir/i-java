
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Template_user26 {	
	
	public static void main(String[] args) throws FileNotFoundException {		
		
		// 외부 라이브러리 사용 불가 (스캐너 포함)
		// 기초적인 수학 라이브러리 같은 것들만 사용 가능하게
		// 입력 시 세미콜론(;) 사용 안해도 되게 (기존 자바와 구분짓기 위함)
		
		String path = "D:/ServerResource/output.disp.user26.txt";
		print(path);
		outToFile(path);
		
		// ===================================
		print("xxx 아이디로 작성하였습니다");
print("오늘 목표는 이 결과을 자유게시판으로 올리는 것입니다.");
print("자유게시판에 올릴 수 있으면 다른 게시판에도 올릴 수 있고 외부에도 보낼 수 있죠");
for (int i = 1; i <=7; i++){
     System.out.print("하");
}
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
}