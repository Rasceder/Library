
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
		return String.format("%s,%.2f,%s,%d,%d,%s,", pages, publisher, title, cost, status, articleNumber); 
	}
		
	public static String getBookCsvHeaderString() {
		return "pages,publisher";
	}

	public static Book parseBook(String csvRecord) {
		String[] values = csvRecord.split(",");
		String title = values[0];
		int pages = Integer.parseInt(values[1]);
		String publisher = values[2];
		float cost = Float.parseFloat(values[3]);
		String status = values[4];
		int articleNumber = Integer.parseInt(values[5]);
		return new Book(articleNumber, title, cost, pages, publisher, status);
	}
	
	@Override
	public String toString() {
		return String.format("%d: Pages %s: Publisher", pages, publisher);
	}
	 
}



