
public class ItemOrder {

	Item item;
	int quantity;
	
	public ItemOrder(Item item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return this.item.priceFor(quantity);
	}
	
	public Item getItem() {
		return this.item;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOrder other = (ItemOrder) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}
	
	
	
}
