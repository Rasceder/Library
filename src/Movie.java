
public class Movie extends Item implements Comparable<Item>{
	
	private int runtime;
	private String rating;	
	
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}


	public int getRuntime() {
		return runtime;
	}

	public Movie(int articleNumber, String title, int cost, int runtime, String rating, String status) {
		super(articleNumber, title, cost, status);
		this.runtime = runtime;
		this.rating = rating;
	}
	
	public String CsvRecord() {
		return String.format("%d,%s,%d,%d,%s,%s", articleNumber, title, cost, runtime, rating, status);
	}

	public static String getCsvHeaderString() {
		return "article number,title,cost,runtime,rating,status";
	}

	public static Movie parseMovie(String csvRecord) {
		String[] values = csvRecord.split(",");
		int articleNumber = Integer.parseInt(values[0]);
		String title = values[1];
		int cost = Integer.parseInt(values[2]);
		int runtime = Integer.parseInt(values[3]);
		String rating = values[4];
		String status = values[5];

		return new Movie(articleNumber, title, cost, runtime, rating, status);
	}
	
	@Override
	public String toString() {
		return String.format("%d (Movie): %s\n      Status: %s,", articleNumber, title, status);
	}
	
	@Override
	public String toString(int searchArticleNumber) {
		return String.format("(Movie) %s, Value: %.dkr, Runtime: %d, Rating: %s", title, cost, runtime, rating);
	}

	@Override
	 public int compareTo(Item compareItem) {
	     int compareNum=((Item)compareItem).getArticleNumber();
	     
	     return this.articleNumber-compareNum;
	 }
	
}