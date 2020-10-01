import java.util.ArrayList;
import java.util.List;

public class Catalog {
	String name;
	List<Item> list;

	public Catalog(String name) {
		super();
		this.name = name;
		list = new ArrayList<Item>();
	}
	
	public void add(Item item) {
		list.add(item);
	}
	
	public int size() {
		return list.size();
	}
	
	public Item get(int index) {
		return list.get(index);
	}
	
	public String getName() {
		return this.name;
	}
	
}
