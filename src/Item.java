
public abstract class Item {
	
	protected String title;
	protected float cost;
	protected String status;
	protected int articleNumber;
	
	public Item(String title, float cost, String status, int articleNumber) {
		this.title = title;
		this.articleNumber = articleNumber;
		this.status = status;
		this.cost = cost;
	}
	
	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getArticleNumber() {
		return articleNumber;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String itemCsvRecord() {
		return String.format("%s,%.2f,%s,%d", title, cost, status, articleNumber);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	}
	
	public String getCsvHeaderString() {
		return "article number,title,cost,status";
	}
	
	@Override
	public String toString() {
		super.toString();
		return String.format("%s: Title %.2f: Price %s: Status %d Article Number", title, cost, status, articleNumber);
	}

}
