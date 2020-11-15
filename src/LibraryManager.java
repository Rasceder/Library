import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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
		
		System.out.println("R & O LibrarySystem \n");
		System.out.println("Current inventory:");
		listCommand();
		
		while (running) {
			System.out.println("\n\n----------------| Library main menu |----------------");
			System.out.print("\n\n> ");
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
				registerCommand();
				break;
			case DEREGISTER:
				deregisterCommand(arguments);
				break;
			case LIST:
				listCommand();
				break;
			case INFO:
				infoCommand(arguments);
				break;
			case CHECKIN:
				checkinCommand(arguments);
				break;
			case CHECKOUT:
				checkoutCommand(arguments);
			}
			
		}	
		scanner.close();
	}
	
	private void infoCommand(String[] arguments) {
	
			try { 
				if (library.searchItem(Integer.parseInt(arguments[0])) != null) {
				try {				
						System.out.println(library.searchItem(Integer.parseInt(arguments[0])).toString(Integer.parseInt(arguments[0])));
					
				} catch (Exception e) {
					System.out.println("Failed to parse index from arguments.");
					return;
				}
	        } else {
				System.out.println("Error: No item with id " + arguments[0] + " registered.");
				return;
	        }
			} catch (Exception e) {
				System.out.println("Failed to parse index from arguments.");
				return;
			}
}

	private void registerCommand() {
		library.registerItem();

	}
	
	private void deregisterCommand(String[] arguments) {
		int articleNumber;
		try {
			articleNumber = Integer.parseInt(arguments[0]);
		} catch (Exception e) {
			System.out.println("Failed to parse index from arguments.");
			return;
		}
		try { 
			library.searchItem(articleNumber); 
			library.removeItem(articleNumber);
		} catch (NullPointerException e) {
			System.out.println("Error: No item with id " + arguments[0] + " registered.");
			return;
		}
	}
	
	private void checkoutCommand(String[] arguments) {
		Scanner scanner = new Scanner(System.in);
		int articleNumber;
		try {
			articleNumber = Integer.parseInt(arguments[0]);
		} catch (Exception e) {
			System.out.println("Failed to parse index from arguments.");
			return;
		} try {
		if (!library.searchItem(articleNumber).getStatus().equals("(In stock)")) {
			System.out.println("Error: item with id " + arguments[0] + " is not available.");	
			return;
		} else {
			System.out.println("Enter customer name:");
			System.out.print("\n> ");
			String name = scanner.nextLine();
			System.out.println("Enter customer phone number:");
			System.out.print("\n> ");
			String phone = scanner.nextLine();
			library.changeItemStatus(articleNumber, name, phone);
		}
	} catch (NullPointerException e) {
		System.out.println("Error: No item with id " + arguments[0] + " registered.");
		return;
	}
	}
	
	private void checkinCommand(String[] arguments) {
		int articleNumber;
		Item item;
		try {
			articleNumber = Integer.parseInt(arguments[0]);
		} catch (Exception e) {
			System.out.println("Failed to parse index from arguments.");
			return;
		} try { 

		if(library.searchItem(articleNumber).getStatus().equals("(In stock)")) {
			System.out.println("Error: item with id " + arguments[0] + " is already available.");	
			return;
		} else {
			item = library.searchItem(articleNumber);
			item.setStatus("(In stock)");
			System.out.println("Successfully returned " + item.getTitle() + ".");
		}
	} catch (NullPointerException e) {
		System.out.println("Error: No item with id " + arguments[0] + " registered.");
		return;}
	   
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
    		case "info":
    			return Command.INFO;
    		case "checkin":
    			return Command.CHECKIN;
    		case "checkout":
    			return Command.CHECKOUT;
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
	    File log = new File("Library3.csv");
	    	    try{
	    	    if(log.exists()==false){
	    	            System.out.println("We had to make a new file.");
	    	            log.createNewFile();
	    	    	    PrintWriter out = new PrintWriter(log);
	    	    	    out.append("article number,title,cost,pages/runtime,publisher/rating,status");
	    	    	    out.close();
	    	    }
	    	    }catch(IOException e){
	    	        System.out.println("COULD NOT LOG!!");
	    	    }
		LibraryManager manager = new LibraryManager("Library3.csv");
		manager.start();	
		
	}	
}