import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Library implements ILibrary{
	
	private ArrayList<Item> items;
	private String itemsPath;
	
	public Library(String itemsPath) throws FileNotFoundException {
		this.itemsPath = itemsPath;
		items = parseItems(itemsPath);
		Collections.sort(items);
	}
	
	public void searchItem(int articleNumber) throws Exception {
		for (Item item : items) {
			if(item.getArticleNumber() == articleNumber) {
				System.out.println(item.toString(articleNumber));
			} 
		}	
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
	
//	public void sortLibrary() {
//		   Collections.sort(items);
//		   
//		   for(Item item: items){
//				System.out.println(item);
//		   }
//	}
	
	@Override
	public String toString() {
		String s = "";
		for (Item item : items) {
			s += item.toString() + "\n";
		}
		return s;
	}

}
