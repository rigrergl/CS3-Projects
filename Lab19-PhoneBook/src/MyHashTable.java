
public class MyHashTable<K, V> {

	int size;
	Entry<K, V>[] entries;

	MyHashTable() {
		size = 0;
		entries = new Entry[47];
	}

	public V put(K key, V value) {
		int hashCode = key.hashCode() % entries.length;
		if (hashCode >= entries.length)
			doubleCapacity();

		Entry<K, V> result = entries[hashCode];

		if (result == null) {
			entries[hashCode] = new Entry(key, value);
			this.size++;
			return null;
		}

		return putInList(key, value, result);
	}

	private V putInList(K key, V value, Entry<K,V> currentEntry) {
		if (currentEntry.getKey().equals(key)) {
			V prev = currentEntry.setValue(value);
			return prev;
		}

		if (currentEntry.next == null) {
			currentEntry.next = new Entry<K,V>(key, value);
			this.size++;
			return null;
		}

		return putInList(key, value, currentEntry.next);
	}

	private void doubleCapacity() {
		// TODO
		//not needed for the purposes of this lab (would only be required for commercial implementation)
	}

	public V get(K key) {
		int hashCode = key.hashCode() % entries.length;
		if (hashCode >= entries.length)
			return null;

		Entry<K,V> result = entries[hashCode];
		return recursiveGet(key, result);
	}

	private V recursiveGet(K key, Entry<K,V> currentEntry) {
		if (currentEntry == null)
			return null;

		if (currentEntry.getKey().equals(key))
			return currentEntry.getValue();

		return recursiveGet(key, currentEntry.next);
	}

	public V remove(K key) {

		int hashCode = key.hashCode() % entries.length;
		Entry<K,V> result = entries[hashCode];

		if (result == null) {
			return null;
		}

		if (result.getKey().equals(key)) {
			V prev = result.getValue();
			entries[hashCode] = result.next;
			size--;
			return prev;
		}

		return recursiveRemove(key, result);
	}

	private V recursiveRemove(K key, Entry<K, V> currentEntry) {
		if (currentEntry == null)
			return null;

		if (currentEntry.next.getKey().equals(key)) {
			V prev = currentEntry.next.getValue();
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

	private class Entry<K, V> {
		Entry<K, V> next;
		K key;
		V value;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V prev = this.value;
			this.value = value;
			return prev;
		}

		@Override
		public String toString() {
			String s = this.key.toString() + "=" + this.value.toString();
			Entry<K,V> nextEntry = this.next;
			while (nextEntry != null) {
				s += ", " + next.toString();
				nextEntry = nextEntry.next;
			}
			return s;
		}
	}
}
