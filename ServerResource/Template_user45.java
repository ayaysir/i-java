
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Template_user45 {	
	
	public static void main(String[] args) throws FileNotFoundException {		
		
		// 외부 라이브러리 사용 불가 (스캐너 포함)
		// 기초적인 수학 라이브러리 같은 것들만 사용 가능하게
		// 입력 시 세미콜론(;) 사용 안해도 되게 (기존 자바와 구분짓기 위함)
		
		String path = "D:/ServerResource/output.disp.user45.txt";
		print(path);
		outToFile(path);
		
		// ===================================
		/* 코드 작성자: 퍼스트 */
 
 
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

 /* 주의: 함수명은 현재 사용중인 모든 회원과 함수명을 공유합니다.
 * 예를 들어 다른 사람이 'cafe'라는 함수를 이미 사용하고 있다면 
 * 'cafe'라는 함수명을 사용하실 수 없습니다.
 * 이로 인해 에러가 발생할 수 있으니 함수명 중복을 피하기 위해
 * 중복 우려가 없는 고유한 패턴의 함수명을 사용해주세요. 예) ds1_cafe
 * 이 주석을 지워주세요. */

/* 함수(메소드) 작성자: 퍼스트 */
 
	}