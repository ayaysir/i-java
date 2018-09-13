package prj.ijava.dto;

public class Qna {
	
/*
 * SEQ          NOT NULL NUMBER        
TITLE        NOT NULL VARCHAR2(100) 
CT_MAIN               CLOB          
CT_FUNCTION           CLOB          
CT_RESULT             CLOB          
CT_ETC                CLOB          
WRITER_EMAIL NOT NULL VARCHAR2(100) 
WRITED_DATE  NOT NULL DATE          
LIKE_COUNT            NUMBER        
READ_COUNT            NUMBER        
 */
	
	private long seq;
	private String title, ctMain, ctFunction, ctResult, ctEtc, writerEmail, writedDate;
	private int likeCount, readCount;
	// 옵션
	private String nickname, levelMarkup1, levelMarkup2, gradeName, commentCount, replyCount;
	private long rep_seq;
	
	public Qna() {
		super();
	}



	public Qna(long seq, String title, String ctMain, String ctFunction, String ctResult, String ctEtc,
			String writerEmail, String writedDate, int likeCount, int readCount, String nickname) {
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLevelMarkup1() {
		return levelMarkup1;
	}

	public void setLevelMarkup1(String levelMarkup1) {
		this.levelMarkup1 = levelMarkup1;
	}

	public String getLevelMarkup2() {
		return levelMarkup2;
	}

	public void setLevelMarkup2(String levelMarkup2) {
		this.levelMarkup2 = levelMarkup2;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(String replyCount) {
		this.replyCount = replyCount;
	}

	public long getRep_seq() {
		return rep_seq;
	}

	public void setRep_seq(long rep_seq) {
		this.rep_seq = rep_seq;
	}

	public String getCtFunction() {
		return ctFunction;
	}

	public void setCtFunction(String ctFunction) {
		this.ctFunction = ctFunction;
	}
	
	

}
