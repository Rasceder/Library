
public class Book extends Item{
	
	protected int pages;
	protected String publisher;

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPages() {
		return pages;
	}

	public Book(int articleNumber, String title, float cost, int pages, String publisher, String status) {
		super(articleNumber, title, cost, status);
		this.pages = pages;
		this.publisher = publisher;
	}
	
	
	public String bookCsvRecord() {
		return String.format("%d,%s,%.2f,%d,%s,%s,", articleNumber, title, cost, pages, publisher, status); 
	}
		
	public static String getBookCsvHeaderString() {
		return "article number,title,cost,pages,publisher,status";
	}

	public static Book parseBook(String csvRecord) {
		String[] values = csvRecord.split(",");
		int articleNumber = Integer.parseInt(values[0]);
		String title = values[1];
		float cost = Float.parseFloat(values[2]);
		int pages = Integer.parseInt(values[3]);
		String publisher = values[4];
		String status = values[5];
		return new Book(articleNumber, title, cost, pages, publisher, status);
	}
	
	@Override
	public String toString() {
		return String.format("Article Number: %d, Title: %s, Price: %.2f, Pages: %d, Publisher: %s, Status: %s,", articleNumber, title, cost, pages, publisher, status);
	}
	 
}



