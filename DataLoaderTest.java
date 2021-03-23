import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataLoaderTest {
	private Accounts accounts = Accounts.getInstance();
	private ArrayList<Account> accountList = accounts.getAccounts();
	
	@BeforeEach
	public void setup() {
		accountList.clear();
		accountList.add(new Student(9, "Owen", "Johnson", "10/02/2000", "whale", "whalee", "w@email.sc.edu", "8032661234", "0909"));
		accountList.add(new Agent(10, "Liam", "Miller", "11/02/2000", "shark", "sharkk", "s@email.sc.edu", "8032661111", "Zoo"));
		DataWriter.saveAccounts();
	}
	
	@AfterEach
	public void tearDown() {
		Accounts.getInstance().getAccounts().clear();
		DataWriter.saveAccounts();
	}
	
	
	@Test
	void testGetUsersSize() {
		accountList = DataLoader.loadAccounts();
		assertEquals(2, accountList.size());
	}

	@Test
	void testGetUsersSizeZero() {
		Accounts.getInstance().getAccounts().clear();
		DataWriter.saveAccounts();
		assertEquals(0, accountList.size());
	}
	
	@Test
	void testGetUserFirstUserName() {
		accountList = DataLoader.loadAccounts();
		assertEquals("whale", accountList.get(0).getUsername());
	}
}