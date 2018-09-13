package prj.ijava.dto;

public class MemberAdv extends Member {
	
	private String nickname;
	private String photoOriginalfn, photoSysfn;
	private String photoUrl, memberLevel;

	public MemberAdv() {
		super();
	
	}

	public MemberAdv(long seq, String email, String password, String tel, String zipcode, String address1,
			String address2, String birthday) {
		super(seq, email, password, tel, zipcode, address1, address2, birthday);
	}
	
	public MemberAdv(long seq, String email, String password, String tel, String zipcode, String address1,
			String address2, String birthday, String nickname, String photoOriginalfn) {
		super(seq, email, password, tel, zipcode, address1, address2, birthday);
		this.nickname = nickname;
		this.photoOriginalfn = photoOriginalfn;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhotoOriginalfn() {
		return photoOriginalfn;
	}

	public void setPhotoOriginalfn(String photoOriginalfn) {
		this.photoOriginalfn = photoOriginalfn;
	}

	public String getPhotoSysfn() {
		return photoSysfn;
	}

	public void setPhotoSysfn(String photoSysfn) {
		this.photoSysfn = photoSysfn;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}	
	
	

}
