package prj.ijava.dto;

public class NavInfo {
	
	private String symbol, link;
	private boolean isActive, isDisabled;
	
	public NavInfo() {
		super();
	}	

	public NavInfo(String symbol, String link) {
		super();
		this.symbol = symbol;
		this.link = link;
	}		

	public NavInfo(String symbol, String link, boolean isActive, boolean isDisabled) {
		super();
		this.symbol = symbol;
		this.link = link;
		this.isActive = isActive;
		this.isDisabled = isDisabled;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean getIsDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	@Override
	public String toString() {
		return "NavInfo [symbol=" + symbol + ", link=" + link + ", isActive=" + isActive + ", isDisabled=" + isDisabled
				+ "]";
	}	
	

}
