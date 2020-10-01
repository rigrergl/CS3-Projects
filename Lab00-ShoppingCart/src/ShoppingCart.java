import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	List<ItemOrder> itemOrders;
	
	public ShoppingCart() {
		itemOrders = new ArrayList<ItemOrder>();
	}
	
	public void add(ItemOrder newOrder) {
		int indexOfDuplicate = itemOrders.indexOf(newOrder);
		if(indexOfDuplicate != -1) itemOrders.remove(indexOfDuplicate);
		
		itemOrders.add(newOrder);
	}
	
	public double getTotal() {
		double total = 0;
		for(int i=0; i<itemOrders.size(); i++) {
			total += itemOrders.get(i).getPrice();
		}
		return total;
	}
	
}
