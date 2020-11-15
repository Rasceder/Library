
public interface ILibrary {
	void addItem(Item item);
	void removeItem(int index);
	Item searchItem(int articleNumber);
	Object getItems();
	int compareTo(Object o);
	void registerItem();
	void changeItemStatus(int articleNumber, String name, String phone);

}
