package prj.ijava.dto;

public class Member {
	
	/*
	 * SEQ      NOT NULL NUMBER        
		EMAIL             VARCHAR2(100) 
		PASSWORD NOT NULL VARCHAR2(64)  
		TEL               VARCHAR2(20)  
		ZIPCODE           VARCHAR2(10)  
		ADDRESS1          VARCHAR2(200) 
		ADDRESS2          VARCHAR2(200) 
		BIRTHDAY          DATE       
	 */
	
	/*
	 * 포인트(long): point
	 * 
	 */
	
	long seq;
	String email, password, tel, zipcode, address1, address2, birthday;
	long point;
	
	public Member() {
		super();
	}

	public Member(long seq, String email, String password, String tel, String zipcode, String address1, String address2,
			String birthday) {
		super();
		this.seq = seq;
		this.email = email;
		this.password = password;
		this.tel = tel;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		this.birthday = birthday;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public long getPoint() {
		return point;
	}

	public void setPoint(long point) {
		this.point = point;
	}
	
	
	
	

}
