
public class Book {
	

	private String title; // The title of the movie
	private int pages;
	private String publisher;
	private float cost;
	private String status;
	private int articleNumber;
	
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public int getPages() {
		return pages;
	}

	public Book(String title, int pages, String publisher, float cost, String status, int articleNumber) {
		super();
		this.title = title;
		this.pages = pages;
		this.publisher = publisher;
		this.articleNumber = articleNumber;
		this.status = status;
		this.cost = cost;
	}
	
	public String bookCsvRecord() {
		return String.format("%s,%d,%s,%.2f,%s,%d", title, pages, publisher, cost, status, articleNumber);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	}

	public static String getCsvHeaderString() {
		return "article number,title,pages,publisher,cost,status";
	}

	public static Book parseBook(String csvRecord) {
		String[] values = csvRecord.split(",");
		String title = values[0];
		int pages = Integer.parseInt(values[1]);
		String publisher = values[2];
		float cost = Float.parseFloat(values[3]);
		String status = values[4];
		int articleNumber = Integer.parseInt(values[5]);
		return new Book(title, pages, publisher, cost, status, articleNumber);
	}
	
	@Override
	public String toString() {
		return String.format("%s: Runtime %dm, Rating %.2f", title, runtime, rating);
	}
	 
}


}
