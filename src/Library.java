import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Library implements ILibrary, Comparable<Object>{
	
	private static ArrayList<Item> items;
	private String itemsPath;
	private int ArticleNumber;
	
	public int getMovieArticleNumber() {
		return this.ArticleNumber;
	}

	public boolean setMovieArticleNumber() {
		int movieArticleNumber;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter product ID:"); 
	    System.out.println("> ");
	    
	    movieArticleNumber = scanner.nextInt();
	    
	    
        if(movieArticleNumber > 19999 && movieArticleNumber > 30000){
        	return false;
           // throw new IllegalArgumentException("Please choose an available ID between 20000 and 29999");

        }else{
            this.ArticleNumber = movieArticleNumber;
            return true;
        }
	}

	public Library(String itemsPath) throws FileNotFoundException {
		this.itemsPath = itemsPath;
		items = parseItems(itemsPath);
		Collections.sort(items);
	}
	
	public String searchItem(int articleNumber) {
		for (Item item : items) {
			if(item.getArticleNumber() == articleNumber) {
				return item.toString(articleNumber);
			} 
		}
		return "null";
	}
	
	public void addItem(Item item) {
		items.add(item);
		Collections.sort(items);
		writeItems();
	}
	
	public void removeItem(int articleNumber) {
		Item[] newItem = new Item[items.size()-1];
		int index = 0;
		for (Item item : items) {
			if(item.getArticleNumber() != articleNumber) {
				newItem[index] = item;
				index++;
			}
		}
		items = new ArrayList<Item>(Arrays.asList(newItem));
		Collections.sort(items);
		writeItems();
	}
	
	private void writeItems() {
		
		try {
			PrintWriter printWriter = new PrintWriter(itemsPath);
			printWriter.println(Item.getCsvHeaderString());
			for (Item item : items) {
				String csvRecord = item.itemCsvRecord();
				printWriter.println(csvRecord);
			}
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	public void registerItem() {
		Scanner scanner = new Scanner(System.in);
		
		int articleNumber; // Movies article number must start with a number 2. All article number are five digits long.
		float cost;
		String status;
		String title;
		int runtime;
		float rating;
		int pages;
		String publisher;
		String userInput;
		int itemSort = 0;
		
			
		System.out.println("What are you registering? Book (b), Movie (m)");
		System.out.print("\n> ");
		userInput = scanner.nextLine();
		
		if  (userInput.equals("m")) {
			itemSort = 2;
		} else if (userInput.equals("b")) {
			itemSort = 1;
		} else {
			System.out.println("Please choose between: Book (b), Movie (m)");
		}
		
			if (itemSort == 2) {
				setMovieArticleNumber();
				while(setMovieArticleNumber()) {
					
	            if(searchItem(getMovieArticleNumber()).equals("null"))

	            	try {
	            		    
	            			articleNumber = getMovieArticleNumber();
	            				            		
					 } catch (NumberFormatException e) {	
						 System.out.println("Please enter integers when registering ID.");
						 return;
						 }
				}
	             
	            } else {
				System.out.println("The ID you have entered is unavailable.");
				System.out.println("Please choose an available ID between 20000 and 29999");
				return;
				}
	            
			    if (itemSort == 1) {
				System.out.println("Enter product ID:"); 
			    System.out.println("> ");
	            userInput = scanner.nextLine();
	            if (Integer.parseInt(userInput) > 9999 && Integer.parseInt(userInput) < 20000) {
	            	try {
	            		    searchItem(Integer.parseInt(userInput)).equals(null);
	            			articleNumber = Integer.parseInt(userInput);
					 } catch (NumberFormatException e) {	
						 System.out.println("Please enter integers when registering ID.");
						 return;
						 }
	            } else {
	            	System.out.println("The ID you have entered is unavailable.");
	            	System.out.println("Please choose an available ID between 9999 and 20000");
	            	return;
				}
							
			System.out.println("Enter Title:"); 
			System.out.println("> ");
			userInput = scanner.nextLine();
			title = userInput;
			
			System.out.println("Enter value:"); 
			System.out.println("> ");
			userInput = scanner.nextLine();
			try {
			cost = Float.parseFloat(userInput);
			} catch (Exception e) {
				System.out.println("Failed to parse value attributes from arguments.");
				return;
			}
			
			
				if (itemSort == 2) {
				System.out.println("Enter runtime in minutes:"); 
				System.out.println("> ");
				userInput = scanner.nextLine();
				try {
				runtime = Integer.parseInt(userInput);
				} catch (Exception e) {
					System.out.println("Failed to parse attributes from arguments.");
					return;
				}
				
	    		System.out.println("Enter IMDB rating:"); 
	    	    System.out.println("> ");
		        userInput = scanner.nextLine();
		        try {
		        rating = Float.parseFloat(userInput);
				} catch (Exception e) {
					System.out.println("Failed to parse attributes from arguments.");
					return;
				}
	    
			status = "(In stock)";
			
		Movie movie = new Movie(articleNumber, title, cost, runtime, rating, status);
		addItem(movie);
		System.out.println(movie.getTitle() + " has been succesfully registered");
		
		
		} else if (itemSort == 1) {
				
			    
				System.out.println("Enter number of pages:"); 
				System.out.println("> ");
				userInput = scanner.nextLine();
				try {
				pages = Integer.parseInt(userInput);
				} catch (Exception e) {
					System.out.println("Failed to parse attributes from arguments.");
					return;
				    }
			    
			    
				System.out.println("Enter publisher:"); 
				System.out.println("> ");
				userInput = scanner.next();
				publisher = userInput;
				
				status = "(In stock)";
				
				Book book = new Book(articleNumber, title, cost, pages, publisher, status);
				addItem(book);
				System.out.println(book.getTitle() + " has been succesfully registered");
			    }
			    }
			}
				

				
		
	
//	private void registerBook() {
//		Scanner scanner = new Scanner(System.in);
//		
//		int articleNumber = 0; // Movies article number must start with a number 2. All article number are five digits long.
//		float cost;
//		String status;
//		String title;
//		int pages;
//		String publisher;
//		String userInput;
//		boolean isRunning = true;
//		
//		while(isRunning) {
//	    System.out.println("Enter product ID:"); 
//	    System.out.println("> ");
//	    userInput = scanner.nextLine();
//	    
//		if (Integer.parseInt(userInput) > 9999 || Integer.parseInt(userInput) < 20000 ) {
//			try {
//				if(searchItem(Integer.parseInt(userInput)).equals(null)){
//				articleNumber = Integer.parseInt(userInput);
//				} 
//			} catch (NumberFormatException e) {	
//				System.out.println("Please enter integers when registering ID.");
//				return;
//			}
//		} else {
//			System.out.println("The ID you have entered is unavailable.");
//			System.out.println("Please choose an available ID between 20000 and 29999");
//			return;
//			}
//
//		try {
//			articleNumber = Integer.parseInt(arguments[0]);
//			title = arguments[1];
//			cost = Float.parseFloat(arguments[2]);
//			pages = Integer.parseInt(arguments[3]);
//			publisher = arguments[4];
//			status = arguments[5];
//		} catch (Exception e) {
//			System.out.println("Failed to parse movie attributes from arguments.");
//			return;
//		}
//		Book book = new Book(articleNumber, title, cost, pages, publisher, status);
//		library.addItem(book);
//	}

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
