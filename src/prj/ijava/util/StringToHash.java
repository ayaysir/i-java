package prj.ijava.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringToHash {

	private String hashMethod;
	private String hash;
	
	public StringToHash() {
		this("SHA-256");
	}
	
	public StringToHash(String hashMethod) {
		this.hashMethod = hashMethod;
	}
	
	public StringToHash(String hashMethod, String str) {
		this.hashMethod = hashMethod;
		setHash(str);
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String str){

		try{
			MessageDigest md = MessageDigest.getInstance(hashMethod); 
			md.update(str.getBytes()); 
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}

			this.hash = sb.toString();

		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			this.hash = null; 
		}
	}

	public String toString() {
		return hash;
	}	

}
