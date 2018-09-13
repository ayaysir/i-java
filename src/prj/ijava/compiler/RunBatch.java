package prj.ijava.compiler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RunBatch {
	
	public void run(String path, String user) {		
		try {
			Runtime.getRuntime().exec(path + " " + user);
			Thread.sleep(1000);			
			
			File compErrFile = new File("D:/ServerResource/compres." + user + ".txt");		
			
			File outdisp = new File("D:/ServerResource/output.disp." + user + ".txt");
			outdisp.createNewFile();
			
			System.out.println(compErrFile.length());
			if(compErrFile.length() != 0) {
				// output.disp.txt 초기화 하기
				try(FileOutputStream fos = new FileOutputStream(outdisp);
		                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(fos, "MS949"))){            
		           
		            br.write("에러가 발생하였습니다.");
		            System.out.println("@@에러 프린트 완료");
		                
		        }catch(Exception e) {e.printStackTrace();}
				// -----------------------
			} 			
			  
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
