
public class PhoneBook implements IMap {

	int size;
	Entry[] entries;

	PhoneBook() {
		size = 0;
		entries = new Entry[47];
	}

	public PhoneNumber put(Person key, PhoneNumber value) {
		int hashCode = key.hashCode(entries.length);
		if (hashCode >= entries.length)
			doubleCapacity();

		Entry result = entries[hashCode];

		if (result == null) {
			entries[hashCode] = new Entry(key, value);
			this.size++;
			return null;
		}

		return putInList(key, value, result);
	}

	private PhoneNumber putInList(Person key, PhoneNumber value, Entry currentEntry) {
		if (currentEntry.getKey().equals(key)) {
			PhoneNumber prev = currentEntry.setValue(value);
			return prev;
		}

		if (currentEntry.next == null) {
			currentEntry.next = new Entry(key, value);
			this.size++;
			return null;
		}

		return putInList(key, value, currentEntry.next);
	}

	private void doubleCapacity() {
		// TODO
		//not needed for the purposes of this lab (would only be required for commercial implementation)
	}

	public PhoneNumber get(Person key) {
		int hashCode = key.hashCode(entries.length);
		if (hashCode >= entries.length)
			return null;

		Entry result = entries[hashCode];
		return recursiveGet(key, result);
	}

	private PhoneNumber recursiveGet(Person key, Entry currentEntry) {
		if (currentEntry == null)
			return null;

		if (currentEntry.getKey().equals(key))
			return currentEntry.getValue();

		return recursiveGet(key, currentEntry.next);
	}

	public PhoneNumber remove(Person key) {

		int hashCode = key.hashCode(entries.length);
		Entry result = entries[hashCode];

		if (result == null) {
			return null;
		}

		if (result.getKey().equals(key)) {
			PhoneNumber prev = result.getValue();
			entries[hashCode] = result.next;
			size--;
			return prev;
		}

		return recursiveRemove(key, result);
	}

	private PhoneNumber recursiveRemove(Person key, Entry currentEntry) {
		if (currentEntry == null)
			return null;

		if (currentEntry.next.getKey().equals(key)) {
			PhoneNumber prev = currentEntry.next.getValue();
			currentEntry.next = currentEntry.next.next;
			size--;
			return prev;
		}

		return recursiveRemove(key, currentEntry.next);
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < entries.length; i++) {
			s += i + "|" + entries[i] + "\n";
		}
		return s;
	}

	private class Entry {
		Entry next;
		Person person;
		PhoneNumber value;

		public Entry(Person person, PhoneNumber phoneNumber) {
			this.person = person;
			this.value = phoneNumber;
		}

		public Person getKey() {
			return person;
		}

		public PhoneNumber getValue() {
			return value;
		}

		public PhoneNumber setValue(PhoneNumber value) {
			PhoneNumber prev = this.value;
			this.value = value;
			return prev;
		}

		@Override
		public String toString() {
			String s = this.person.toString() + "=" + this.value.toString();
			Entry nextEntry = this.next;
			while (nextEntry != null) {
				s += ", " + next.toString();
				nextEntry = nextEntry.next;
			}
			return s;
		}
	}
}
