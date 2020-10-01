
public class Item {

	String name;
	double price; 
	int bulkQty;
	double bulkPrice;
	
	public Item(String name, double price) {
		this(name, price, 1, price); //find out default values for bulkQty and bulkPrice
	}
	
	public Item(String name, double price, int bulkQty, double bulkPrice) {
		super();

		if (price < 0 || bulkPrice < 0) 
			throw new IllegalArgumentException("error");
		
		this.name = name;
		this.price = price;
		this.bulkQty = bulkQty;
		this.bulkPrice = bulkPrice;
	}
	
	public double priceFor(int quantity) {
		if(quantity < 0) throw new IllegalArgumentException("error");
		
		if(quantity >= bulkQty) return bulkPrice * quantity;
		else return price * quantity;
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public String toString(){
		String s = "";
		s += name + ", $" + price;
		if(this.bulkQty > 1) s += " (" + bulkQty + " for $" + bulkPrice + ")";
		
		return s;
	}

}
