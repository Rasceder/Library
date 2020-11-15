import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Library implements ILibrary, Comparable<Object>{
	
	private static ArrayList<Item> items;
	private String itemsPath;
	private String ArticleNumber;
	
	public String getArticleNumber() {
		return this.ArticleNumber;
	}

	public boolean setArticleNumber(String itemType) {
		String itemId = null;
		int userInput = 0;
		boolean isRunning = true;
		Scanner scanner = new Scanner(System.in);
		
		if  (itemType.equals("m")) {
			itemId = "2";
		} else if (itemType.equals("b")) {
			itemId = "1";
		} else {
			registerItem();
		}
		while (isRunning) {
		System.out.println("Enter four digit product ID:"); 
	    System.out.print("\n> ");   
	    try {
	    userInput = scanner.nextInt();
	    } catch (Exception e) {
	    	System.out.println("Please enter integers when registering ID.");
	    }  
	    
        if(Integer.toString(userInput).length() == 4 && Integer.toString(userInput) != null){
        	
        	itemId += Integer.toString(userInput);
        	try {
        		
        	searchItem(Integer.parseInt(itemId)); 
        		this.ArticleNumber = itemId;
        		return true;
        	} catch (NullPointerException e) {
        		System.out.println("Error: ID " + itemId + " is already registered"); 
        		isRunning = false;
        	}
        	
        } else {
        	System.out.println("Error: only four digits allowed.");   
        	isRunning = false;
        }       
		}

		return false;
	}

	public Library(String itemsPath) throws FileNotFoundException {
		this.itemsPath = itemsPath;
		items = parseItems(itemsPath);
		Collections.sort(items);
	}
	
	public Item searchItem(int articleNumber) {
		for (Item item : items) {
			if(item.getArticleNumber() == articleNumber) {
				return item;
			} 
		}
		return null;
	}
	
	public void addItem(Item item) {
		items.add(item);
		Collections.sort(items);
		writeItems();
	}
	
	public void removeItem(int articleNumber) {
		
		Iterator<Item> itr = items.iterator(); 
		while (itr.hasNext()) 
        { 
            Item item = (Item)itr.next(); 
            if (item.getArticleNumber() == articleNumber) {
                itr.remove(); 
                System.out.println(item.getArticleNumber()+ " " + "\"" + item.getTitle() + "\"" + " has been deregistered.");
            } 
        } 
		Collections.sort(items);
		writeItems();
	}
	
	public void changeItemStatus(int articleNumber, String name, String phone) {
		
		Iterator<Item> itr = items.iterator(); 
		while (itr.hasNext()) 
        { 
            Item item = (Item)itr.next(); 
            if (item.getArticleNumber() == articleNumber) {
            	String status = "Borrowed by " + name + " " + phone + ".";
            	item.setStatus(status);
            	System.out.println("Successfully lended " + item.getTitle() + " to " + name + ".");
            }
        } 
		Collections.sort(items);
		writeItems();
	}
	
	private void writeItems() {
		
		try {
			PrintWriter printWriter = new PrintWriter(itemsPath);
			printWriter.println(Item.getCsvHeaderString());
			for (Item item : items) {
				String csvRecord = item.CsvRecord();
				printWriter.println(csvRecord);
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	public void registerItem() {
		Scanner scanner = new Scanner(System.in);
		
		String articleNumber = "0"; // Movies article number must start with a number 2. All article number are five digits long.
		int cost;
		String status;
		String title;
		int runtime;
		String rating;
		int pages;
		String publisher;
		String userInput;
		char itemSort = '0';
		boolean isRunning = true;
		
		do {
		System.out.println("What are you registering? Book (b), Movie (m)");
		System.out.print("\n> ");
		setArticleNumber(scanner.nextLine());
		articleNumber = getArticleNumber();
		System.out.println("Product ID has been set to " + articleNumber);
		
		System.out.println("Enter Title:"); 
		System.out.print("\n> ");
		userInput = scanner.nextLine();
		title = userInput;
		
		System.out.println("Enter value:"); 
		System.out.print("\n> ");
		userInput = scanner.nextLine();
		try {
		cost = Integer.parseInt(userInput);
		} catch (Exception e) {
			System.out.println("Failed to parse value attributes from arguments.");
			return;
		}
		status = "(In stock)";
		
		itemSort = articleNumber.charAt(0);
		
		
		while(itemSort == '2' && isRunning) {
			
			System.out.println("Enter runtime in minutes:"); 
			System.out.print("\n> ");
			userInput = scanner.nextLine();
			try {
			runtime = Integer.parseInt(userInput);
			} catch (Exception e) {
				System.out.println("Failed to parse attributes from arguments.");
				return;
			}
			
    		System.out.println("Enter IMDB rating:"); 
    		System.out.print("\n> ");
	        userInput = scanner.nextLine();
	        try {
	        rating = userInput;
			} catch (Exception e) {
				System.out.println("Failed to parse attributes from arguments.");
				return;
			}
	        
			Movie movie = new Movie(Integer.parseInt(articleNumber), title, cost, runtime, rating, status);
			addItem(movie);
			System.out.println(movie.getTitle() + " has been succesfully registered");	
			isRunning = false;
		}
		
		while(itemSort == '1' && isRunning) {
			
			System.out.println("Enter number of pages:"); 
			System.out.print("\n> ");
			userInput = scanner.nextLine();
			try {
			pages = Integer.parseInt(userInput);
			} catch (Exception e) {
				System.out.println("Failed to parse attributes from arguments.");
				return;
			}
			
			System.out.println("Enter publisher:"); 
			System.out.print("\n> ");
			userInput = scanner.next();
			publisher = userInput;
			
			Book book = new Book(Integer.parseInt(articleNumber), title, cost, pages, publisher, status);
			addItem(book);
			System.out.println(book.getTitle() + " has been succesfully registered");
			isRunning = false;
		}

	}
		while (isRunning == true) ;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	private ArrayList<Item> parseItems(String itemsPath) throws FileNotFoundException {
		
		FileReader reader = new FileReader(itemsPath);
		Scanner scanner = new Scanner(reader);
		
		// Read the movie from CSV
		ArrayList<Item> items = new ArrayList<>();
		scanner.nextLine(); // skip header line
		while (scanner.hasNextLine()) {
			String csvRecord = scanner.nextLine();
			String[] values = csvRecord.split(",");
			if (values[0].charAt(0) == '2') {
				items.add(Movie.parseMovie(csvRecord));
			} 
			else if (values[0].charAt(0) == '1') {
				items.add(Book.parseBook(csvRecord));
			}		
		}
		
		scanner.close();
		
		return items;

	}
	
	@Override
	public String toString() {
		String s = "";
		for (Item item : items) {
			s += item.toString().replaceAll(",$", "") + "\n";
		}
		return s;
	}


	public int compareTo(Object o) {
		int compareNum=((Item)o).getArticleNumber();
		return 0;
	}



}