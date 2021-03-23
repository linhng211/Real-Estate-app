import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest {
	private Accounts accounts = Accounts.getInstance();
	private ArrayList<Account> accountList = accounts.getAccounts();
	
	@BeforeEach
	public void setup() {
		Accounts.getInstance().getAccounts().clear();
		DataWriter.saveAccounts();
	}
	
	@AfterEach
	public void tearDown() {
		Accounts.getInstance().getAccounts().clear();
		DataWriter.saveAccounts();
	}
	
	
	@Test
	void testWritingZeroUsers() {
		accountList = DataLoader.loadAccounts();
		assertEquals(0, accountList.size());
	}

	@Test
	void testWritingOneUser() {
		accountList.add(new Student(7, "Rose", "Lisa", "01/02/2000", "fish", "fishh", "r@email.sc.edu", "8032666789", "9876"));
		DataWriter.saveAccounts();
		assertEquals("fish", DataLoader.loadAccounts().get(0).getUsername());
	}
	
	@Test
	void testWritingFiveUsers() {
		accountList.add(new Agent(1, "Nick", "Jones", "11/20/2000", "cat", "catt", "c@email.sc.edu", "8032665050", "Fire"));
		accountList.add(new Student(2, "Josh", "Smith", "10/20/2000", "dog", "dogg", "d@email.sc.edu", "8032669090", "4567"));
		accountList.add(new Student(3, "Emma", "Brown", "9/20/2000", "lion", "lionn", "l@email.sc.edu", "8032662020", "6789"));
		accountList.add(new Agent(4, "John", "Davis", "8/20/2000", "tiger", "tigerr", "t@email.sc.edu", "8032661010", "Ice"));
		accountList.add(new Student(5, "Jack", "Anderson", "7/20/2000", "monkey", "monkeyy", "m@email.sc.edu", "8032668989", "2345"));
		DataWriter.saveAccounts();
		assertEquals("monkey", DataLoader.loadAccounts().get(4).getUsername());
	}
	
	@Test
	void testWritingEmptyUser() {
		accountList.add(new Student(0, "", "", "", "", "", "", "", ""));
		DataWriter.saveAccounts();
		assertEquals("", DataLoader.loadAccounts().get(0).getUsername());
	}
	
	@Test
	void testWritingNullUser() {
		accountList.add(new Student( 0, null, null, null, null, null, null, null, null));
		DataWriter.saveAccounts();
		assertEquals(null, DataLoader.loadAccounts().get(0).getUsername());
	}
	
}