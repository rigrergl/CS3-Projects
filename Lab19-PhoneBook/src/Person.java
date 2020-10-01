public class Person {
	String name;

	public Person(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Person))
			return false;

		Person other = (Person) obj;
		if (other.name == this.name)
			return true;
		else
			return false;

	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public int hashCode(int tableLength) {
		return sumChars() % tableLength; // TODO
	}
	
//	@Override
//	public int hashCode() {
//		return sumChars() % tableLength;
//	}

	private int sumChars() {
		int sum = 0;
		char[] chars = name.toCharArray();
		for (char c : chars) {
			sum += c;
		}
		return sum;
	}
}
