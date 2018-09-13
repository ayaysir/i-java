package prj.ijava.dto;

public class Stage {
	
	/*
SEQ          NOT NULL NUMBER        
ABS_ORDER             NUMBER        
TITLE        NOT NULL VARCHAR2(100) 
DESCRIPTION           CLOB          
ANS_MAIN              CLOB          
ANS_FUNCTION          CLOB          
ANS_RESULT            CLOB          
ANSFIELD              NUMBER        
ANSTYPE               NUMBER        
GIVE_POINT            NUMBER        
STAGE_COLOR  NOT NULL NUMBER    
	 */

	int seq, absOrder;
	String title, description, ansMain, ansFunction, ansResult;
	int ansField, ansType, givePoint, stageColor;
	
	public Stage() {
		super();
	}

	public Stage(int seq, int absOrder, String title, String description, String ansMain, String ansFunction,
			String ansResult, int ansField, int ansType, int givePoint, int stageColor) {
		super();
		this.seq = seq;
		this.absOrder = absOrder;
		this.title = title;
		this.description = description;
		this.ansMain = ansMain;
		this.ansFunction = ansFunction;
		this.ansResult = ansResult;
		this.ansField = ansField;
		this.ansType = ansType;
		this.givePoint = givePoint;
		this.stageColor = stageColor;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getAbsOrder() {
		return absOrder;
	}

	public void setAbsOrder(int absOrder) {
		this.absOrder = absOrder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAnsMain() {
		return ansMain;
	}

	public void setAnsMain(String ansMain) {
		this.ansMain = ansMain;
	}

	public String getAnsFunction() {
		return ansFunction;
	}

	public void setAnsFunction(String ansFunction) {
		this.ansFunction = ansFunction;
	}

	public String getAnsResult() {
		return ansResult;
	}

	public void setAnsResult(String ansResult) {
		this.ansResult = ansResult;
	}

	public int getAnsField() {
		return ansField;
	}

	public void setAnsField(int ansField) {
		this.ansField = ansField;
	}

	public int getAnsType() {
		return ansType;
	}

	public void setAnsType(int ansType) {
		this.ansType = ansType;
	}

	public int getGivePoint() {
		return givePoint;
	}

	public void setGivePoint(int givePoint) {
		this.givePoint = givePoint;
	}

	public int getStageColor() {
		return stageColor;
	}

	public void setStageColor(int stageColor) {
		this.stageColor = stageColor;
	}

	
}
