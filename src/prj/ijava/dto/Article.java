package prj.ijava.dto;

public class Article {
	
	/*
	 * BOARD_ID     NOT NULL VARCHAR2(10)   
		CATEGORY              VARCHAR2(20)   
		SEQ          NOT NULL NUMBER         
		TITLE        NOT NULL VARCHAR2(100)  
		CONTENTS              VARCHAR2(4000) 
		WRITER_EMAIL NOT NULL VARCHAR2(100)  
		WRITED_DATE  NOT NULL DATE           
		READ_COUNT            NUMBER      
	 */
	/*
	 * 추가: nickname
	 */
	
	private String boardId, category;
	private long seq;
	private String title, contents, writerEmail, writedDate;
	private long readCount;
	
	private String nickname;
	
	
	public Article() {
		super();
	}

	public Article(String boardId, String category, long seq, String title, String contents, String writerEmail,
			String writedDate, long readCount) {
		super();
		this.boardId = boardId;
		this.category = category;
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.writerEmail = writerEmail;
		this.writedDate = writedDate;
		this.readCount = readCount;
	}
	
	

	public Article(String boardId, String category, long seq, String title, String contents, String writerEmail,
			String writedDate, long readCount, String nickname) {
		super();
		this.boardId = boardId;
		this.category = category;
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.writerEmail = writerEmail;
		this.writedDate = writedDate;
		this.readCount = readCount;
		this.nickname = nickname;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public long getReadCount() {
		return readCount;
	}

	public void setReadCount(long readCount) {
		this.readCount = readCount;
	}
	
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


}
