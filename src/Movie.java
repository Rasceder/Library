public class Movie {
	
	private String title;
	private int runtime;
	private float rating;
	private int isbn;
	private double price;
	
	
	
	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getTitle() {
		return title;
	}

	public int getRuntime() {
		return runtime;
	}
	
	public int getISBN() {
		return isbn;
	}
	
	public double getPrice() {
		return price;
		
	}

	public Movie(String title, int runtime, float rating, int isbn, double price) {
		super();
		this.title = title;
		this.runtime = runtime;
		this.rating = rating;
		this.isbn = isbn;
		this.price = price;
	}
	
	public String movieCsvRecord() {
		return String.format("%s,%d,%.2f,%d,%lf", title, runtime, rating, isbn, price);
	}

	public static String getCsvHeaderString() {
		return "title,runtime (minutes),imdb_rating, isbn(articlenumber), price";
	}

	public static Movie parseMovie(String csvRecord) {
		String[] values = csvRecord.split(",");
		String title = values[0];
		int runtime = Integer.parseInt(values[1]);
		float rating = Float.parseFloat(values[2]);
		int isbn = Integer.parseInt (values[3]);
		double price = Double.parseDouble (values[4]);
		return new Movie(title, runtime, rating, isbn, price);
	}
	
	@Override
	public String toString() {
		return String.format("%s: Runtime %dm, Rating %.2f, ISBN %dm, Price %lf", title, runtime, rating, isbn, price);
	}
	
}