
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Template_user25 {	
	
	public static void main(String[] args) throws FileNotFoundException {		
		
		// 외부 라이브러리 사용 불가 (스캐너 포함)
		// 기초적인 수학 라이브러리 같은 것들만 사용 가능하게
		// 입력 시 세미콜론(;) 사용 안해도 되게 (기존 자바와 구분짓기 위함)
		
		String path = "D:/ServerResource/output.disp.user25.txt";
		print(path);
		outToFile(path);
		
		// ===================================
		print("재밌는 예제 없나요?");
print(3+6);
print("주사위" + (Math.random() * 6 + 1));
for (int i = 1; i <= 10; i++)
{
    for (int j = 1; j <= i; j++) {
        System.out.print("*");
    }
   print("");
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