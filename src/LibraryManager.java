import java.io.FileNotFoundException;
import java.util.Scanner;

public class LibraryManager {
	
	private ILibrary library;
	
	private enum Command {
		LIST,
		CHECKOUT,
		CHECKIN,
		REGISTER,
		DEREGISTER,
		INFO,
		QUIT,
		UNKNOWN
	}
	
	public LibraryManager(String itemsPath) {
		try {
			library = new Library(itemsPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("No file found at " + itemsPath);
			System.out.println("Exiting");
			System.exit(0);
		}
	}
	
	public void start() {
		
		boolean running = true;
		Scanner scanner = new Scanner(System.in); 
		
		while (running) {
			
			String userInput = scanner.nextLine();
			Command command = parseCommand(userInput);
			
			if (command == Command.QUIT) {
				running = false;
				System.out.println("Exiting.");
				continue;
			} else if (command == Command.UNKNOWN) {
				System.out.println("Unknown command.");
				continue;
			}
			
			String[] arguments = parseArguments(userInput);
			
			switch (command) {
			case REGISTER:
				registerCommand(arguments);
				break;
			case DEREGISTER:
				deregisterCommand(arguments);
				break;
			case LIST:
				listCommand();
				break;
			}
			
		}
		
		scanner.close();
		
	}
	
	private void registerCommand(String[] arguments) {
		if (arguments[0].charAt(0) == 0) {
			registerMovie(arguments);
		} else if (arguments[0].charAt(0) == 1) {
			registerBook(arguments);
		}
	}

	private void registerMovie(String[] arguments) {
		int articleNumber;
		float cost;
		String status;
		String title;
		int runtime;
		float rating;
		try {
			articleNumber = Integer.parseInt(arguments[0]);
			title = arguments[1];
			runtime = Integer.parseInt(arguments[2]);
			rating = Float.parseFloat(arguments[3]);
			cost = Float.parseFloat(arguments[4]);
			status = arguments[5];
		} catch (Exception e) {
			System.out.println("Failed to parse movie attributes from arguments.");
			return;
		}
		//Movie movie = new Movie(title, runtime, rating);
		//library.addItem(movie);
	}
	
	private void registerBook(String[] arguments) {
		int articleNumber;
		float cost;
		String status;
		String title;
		int pages;
		String publisher;
		try {
			articleNumber = Integer.parseInt(arguments[0]);
			title = arguments[1];
			cost = Float.parseFloat(arguments[2]);
			pages = Integer.parseInt(arguments[3]);
			publisher = arguments[4];
			status = arguments[5];
		} catch (Exception e) {
			System.out.println("Failed to parse movie attributes from arguments.");
			return;
		}
		Book book = new Book(articleNumber, title, cost, pages, publisher, status);
		library.addItem(book);
	}
	

	private void deregisterCommand(String[] arguments) {
		int index;
		try {
			index = Integer.parseInt(arguments[0]);
		} catch (Exception e) {
			System.out.println("Failed to parse index from arguments.");
			return;
		}
		library.removeItem(index);
	}
	
	private void listCommand() {
		System.out.println(library);
	}

    private Command parseCommand(String userInput) {
    	String commandString = userInput.split(" ")[0];
    	switch(commandString) {
    		case "register":
				return Command.REGISTER;
    		case "deregister":
    			return Command.DEREGISTER;
    		case "list":
				return Command.LIST;
    		case "quit":
    		case "exit":
    			return Command.QUIT;
			default:
				return Command.UNKNOWN;
    	}
    }
    
    private String[] parseArguments(String userInput) {
        String[] commandAndArguments = userInput.split(" ");
        String[] arguments = new String[commandAndArguments.length - 1];
        for (int i=1; i<commandAndArguments.length; i++) {
            arguments[i-1] = commandAndArguments[i];
        }
        return arguments; // contains only the arguments
    }
	
	public static void main(String[] args) {
		LibraryManager manager = new LibraryManager("movies.csv");
		manager.start();
	}


}
