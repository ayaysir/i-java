package prj.ijava.dto;

public class Reply {
	
	/*
	 * REP_SEQ      NOT NULL NUMBER        
		QNA_SEQ      NOT NULL NUMBER        
		CT_MAIN               CLOB          
		CT_FUNCTION           CLOB          
		CT_RESULT             CLOB          
		CT_ETC                CLOB          
		WRITER_EMAIL NOT NULL VARCHAR2(100) 
		WRITED_DATE  NOT NULL DATE          
		LIKE_COUNT            NUMBER     
	 */
	
	long repSeq, qnaSeq;
	String ctMain, ctFunction, ctResult, ctEtc, writerEmail, writedDate;
	int likeCount;
	
	public Reply() {
		super();
	}

	public Reply(long repSeq, long qnaSeq, String ctMain, String ctFunction, String ctResult, String ctEtc,
			String writerEmail, String writedDate, int likeCount) {
		super();
		this.repSeq = repSeq;
		this.qnaSeq = qnaSeq;
		this.ctMain = ctMain;
		this.ctFunction = ctFunction;
		this.ctResult = ctResult;
		this.ctEtc = ctEtc;
		this.writerEmail = writerEmail;
		this.writedDate = writedDate;
		this.likeCount = likeCount;
	}

	public long getRepSeq() {
		return repSeq;
	}

	public void setRepSeq(long repSeq) {
		this.repSeq = repSeq;
	}

	public long getQnaSeq() {
		return qnaSeq;
	}

	public void setQnaSeq(long qnaSeq) {
		this.qnaSeq = qnaSeq;
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
	
}
