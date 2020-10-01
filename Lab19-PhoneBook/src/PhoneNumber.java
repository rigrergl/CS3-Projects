
public class PhoneNumber {
	String number;
	
	public PhoneNumber(String number) {
		this.number = number;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof PhoneNumber))
			return false;

		PhoneNumber other = (PhoneNumber) obj;
		if (other.number == this.number)
			return true;
		else
			return false;

	}
	
	@Override
	public int hashCode() {
		return -1; //TODO
	}
	
	@Override
	public String toString() {
		return this.number;
	}
	
}
