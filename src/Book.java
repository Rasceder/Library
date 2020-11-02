
public class Book extends Item{
	
	private int pages;
	private String publisher;

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPages() {
		return pages;
	}

	public Book(String title, int pages, String publisher, float cost, String status, int articleNumber) {
		super(title, cost, status, articleNumber);
		this.pages = pages;
		this.publisher = publisher;
	}
	
	@Override
	public String itemCsvRecord() {
		super.itemCsvRecord();
		return String.format("%d,%s,", pages, publisher);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	}

	@Override
	public String getCsvHeaderString() {
		super.getCsvHeaderString();
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
		return new Book(title, pages, publisher, cost, status, articleNumber);
	}
	
	@Override
	public String toString() {
		return String.format("%d: Pages %s: Publisher", pages, publisher);
	}
	 
}



