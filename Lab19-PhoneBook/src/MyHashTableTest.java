import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyHashTableTest {

	MyHashTable<Person, PhoneNumber> pb = new MyHashTable();
	
	Person gerry = new Person("Gerry Man");
	Person rigre = new Person("Rigre Garciandia");
	Person eleanor = new Person("Eleanor Girl");
	Person leo = new Person("Leo Man");
	
	PhoneNumber n1 = new PhoneNumber("1");
	PhoneNumber n2 = new PhoneNumber("2");
	PhoneNumber n3 = new PhoneNumber("3");
	PhoneNumber n4 = new PhoneNumber("4");
	
	Person gerryNam = new Person("Gerry naM");
	PhoneNumber n5 = new PhoneNumber("5");
	
//	pb.put(gerry, n1); pb.put(rigre, n2); pb.put(eleanor, n3); pb.put(leo, n4);
	
	@Test
	void testPutAndGet() {
		pb.put(gerry, n1); pb.put(rigre, n2); pb.put(eleanor, n3); pb.put(leo, n4);
		
		assertEquals(pb.get(gerry), n1);
		assertEquals(pb.get(rigre), n2);
		assertEquals(pb.get(eleanor), n3);
		assertEquals(pb.get(leo), n4);
		
		System.out.println(pb.remove(gerry));
		System.out.println(pb.remove(leo));
	}
	
	@Test
	void testRemove() {
		pb.put(gerry, n1); pb.put(rigre, n2); pb.put(eleanor, n3); pb.put(leo, n4);
		
		System.out.println(pb.remove(gerry));
		System.out.println(pb.remove(leo));
		
		assertEquals(pb.get(gerry), null);
		assertEquals(pb.get(leo), null);
	}
	
	@Test
	void testToString() {
		pb.put(gerry, n1); pb.put(rigre, n2); pb.put(eleanor, n3); pb.put(leo, n4);
		pb.put(gerryNam, n5);
		
		System.out.println(pb.toString());
	}

}
