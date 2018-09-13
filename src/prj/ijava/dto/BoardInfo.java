package prj.ijava.dto;

public class BoardInfo {
	
	/*
	 * BOARD_ID     NOT NULL VARCHAR2(10) 
		BOARD_TITLE  NOT NULL VARCHAR2(30) 
		REC_PER_PAGE NOT NULL NUMBER       
		NAV_PER_PAGE NOT NULL NUMBER   
	 */
	
	private String boardId, boardTitle;
	private int recPerPage, navPerPage;
	
	public BoardInfo(String boardId, String boardTitle, int recPerPage, int navPerPage) {
		super();
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.recPerPage = recPerPage;
		this.navPerPage = navPerPage;
	}

	public BoardInfo() {
		super();
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public int getRecPerPage() {
		return recPerPage;
	}

	public void setRecPerPage(int recPerPage) {
		this.recPerPage = recPerPage;
	}

	public int getNavPerPage() {
		return navPerPage;
	}

	public void setNavPerPage(int navPerPage) {
		this.navPerPage = navPerPage;
	}
	
	
}
