package prj.ijava.dto;

public class Code {
	
	/* SEQ          NOT NULL NUMBER        
	TITLE        NOT NULL VARCHAR2(100) 
	CT_MAIN               CLOB          
	CT_FUNCTION           CLOB          
	CT_RESULT             CLOB          
	CT_ETC                CLOB          
	WRITER_EMAIL NOT NULL VARCHAR2(100) 
	WRITED_DATE  NOT NULL DATE          
	LIKE_COUNT            NUMBER        
	READ_COUNT            NUMBER        
	TAGS                  VARCHAR2(500) 
	IS_PUBLISHED NOT NULL CHAR(2) */
	
	private long seq;
	private String title, ctMain, ctFunction, ctResult, ctEtc, writerEmail, writedDate;
	private int likeCount, readCount;
	private String tags, isPublished;
	private String nickname;
	
	public Code() {
		super();
	}

	public Code(long seq, String title, String ctMain, String ctFunction, String ctResult, String ctEtc,
			String writerEmail, String writedDate, int likeCount, int readCount, String tags, String isPublished,
			String nickname) {
		super();
		this.seq = seq;
		this.title = title;
		this.ctMain = ctMain;
		this.ctFunction = ctFunction;
		this.ctResult = ctResult;
		this.ctEtc = ctEtc;
		this.writerEmail = writerEmail;
		this.writedDate = writedDate;
		this.likeCount = likeCount;
		this.readCount = readCount;
		this.tags = tags;
		this.isPublished = isPublished;
		this.nickname = nickname;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCtMain() {
		return ctMain;
	}

	public void setCtMain(String ctMain) {
		this.ctMain = ctMain;
	}

	public String getCtFunction() {
		return ctFunction;
	}

	public void setCtFunction(String ctFunction) {
		this.ctFunction = ctFunction;
	}

	public String getCtResult() {
		return ctResult;
	}

	public void setCtResult(String ctResult) {
		this.ctResult = ctResult;
	}

	public String getCtEtc() {
		return ctEtc;
	}

	public void setCtEtc(String ctEtc) {
		this.ctEtc = ctEtc;
	}

	public String getWriterEmail() {
		return writerEmail;
	}

	public void setWriterEmail(String writerEmail) {
		this.writerEmail = writerEmail;
	}

	public String getWritedDate() {
		return writedDate;
	}

	public void setWritedDate(String writedDate) {
		this.writedDate = writedDate;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	

	
	
	
	
	

}
