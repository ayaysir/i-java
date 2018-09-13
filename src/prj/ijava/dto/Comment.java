package prj.ijava.dto;

public class Comment {
	
	/*
	 * create table board_comment(    
    comment_seq number primary key,
    board_seq number not null,
    contents varchar(400),
    writer_email varchar(100) not null,
    writed_date date not null
);
	 */
	
	private long commentSeq, boardSeq;
	private String contents, writerEmail, writedDate;	
	
	public Comment() {
		super();		
	}	
	
	public Comment(long commentSeq, long boardSeq, String contents, String writerEmail, String writedDate) {
		super();
		this.commentSeq = commentSeq;
		this.boardSeq = boardSeq;
		this.contents = contents;
		this.writerEmail = writerEmail;
		this.writedDate = writedDate;
	}

	public long getCommentSeq() {
		return commentSeq;
	}
	public void setCommentSeq(long commentSeq) {
		this.commentSeq = commentSeq;
	}
	public long getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(long boardSeq) {
		this.boardSeq = boardSeq;
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
	

}
