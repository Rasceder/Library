import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Library implements ILibrary{
	
	private ArrayList<Item> items;
	private String itemsPath;
	
	public Library(String itemsPath) throws FileNotFoundException {
		this.itemsPath = itemsPath;
		items = parseItems(itemsPath);
	}
	
	public void addItem(Item item) {
		items.add(item);
		writeItems();
	}
	
	public void removeItem(int index) {
		items.remove(index);
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
			items.add(Item.parseItem(csvRecord));
		}
		scanner.close();
				
		return items;

	}
	
	@Override
	public String toString() {
		String s = "";
		int index = 0;
		for (Item item : items) {
			s += index + ": " + item.toString() + "\n";
			index++;
		}
		return s;
	}

}
