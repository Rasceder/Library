
public interface ILibrary {
	void addItem(Item item);
	void removeItem(int index);
	String searchItem(int articleNumber);
	Object getItems();
	int compareTo(Object o);
	void registerItem();

}
