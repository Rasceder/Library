public class Movie extends Item{
	
	private int runtime;
	private float rating;	
	
	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}


	public int getRuntime() {
		return runtime;
	}

	public Movie(int articleNumber, String title, float cost, int runtime, float rating, String status) {
		super(articleNumber, title, cost, status);
		this.runtime = runtime;
		this.rating = rating;
	}
	
	public String movieCsvRecord() {
		return String.format("%d,%s,%.2f,%d,%.1f,%s,", articleNumber, title, cost, runtime, rating, status);
	}

	public static String getCsvHeaderString() {
		return "article number,title,cost,runtime,rating,status";
	}

	public static Movie parseMovie(String csvRecord) {
		String[] values = csvRecord.split(",");
		int articleNumber = Integer.parseInt(values[0]);
		String title = values[1];
		float cost = Float.parseFloat(values[2]);
		int runtime = Integer.parseInt(values[3]);
		float rating = Float.parseFloat(values[4]);
		String status = values[5];

		return new Movie(articleNumber, title, cost, runtime, rating, status);
	}
	
	@Override
	public String toString() {
		return String.format("Article Number: %d, Title: %s, Price: %.2f, Runtime: %d, Rating: %.1f, Status: %s,", articleNumber, title, cost, runtime, rating, status);
	}
	
}