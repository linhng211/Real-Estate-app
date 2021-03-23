import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest2 {
	private Properties properties = Properties.getInstance();
	private ArrayList<Property> propertyList = properties.getProperties();
	
	@BeforeEach
	public void setup() {
		Properties.getInstance().getProperties().clear();
		DataWriter.saveAccounts();
	}
	
	@AfterEach
	public void tearDown() {
		Properties.getInstance().getProperties().clear();
		DataWriter.saveAccounts();
	}
	
	
	@Test
	void testWritingZeroUsers() {
		propertyList = DataLoader.loadProperties();
		assertEquals(5, propertyList.size());
	}

	@Test
	void testWritingOneUser() {
		propertyList.add(new House(123, "5 Main St", 1.1, 2, 3.3, 4, 5.5, 6, 7, 8.8));
		DataWriter.saveProperties();
		assertEquals(3.3, DataLoader.loadProperties().get(0).getPrice());
	}
	
	@Test
	void testWritingFiveUsers() {
		propertyList.add(new House(234, "7 Main St", 7.8, 4, 6.3, 2, 1.6, 9, 8, 7.7));
		propertyList.add(new House(345, "8 Main St", 9.8, 5, 4.5, 3, 7.8, 5, 4, 4.3));
		propertyList.add(new Townhouse(111, "9 Main St", 9.3, 2, 5.4, 4, 4.3, 10, 3, 2.7,9));
		propertyList.add(new Apartment(222, "10 Main St", 8.9, 3, 7.7, 1, 8.9, 1, 6, true));
		propertyList.add(new House(333, "11 Main St", 7.5, 1, 8.8, 5, 9.8, 2, 5, 2.2));
		DataWriter.saveProperties();
		assertEquals(8.8, DataLoader.loadProperties().get(4).getPrice());
	}
	
	@Test
	void testWritingEmptyUser() {
		propertyList.add(new House(0, "", 0, 0, 0, 0, 0, 0, 0, 0));
		DataWriter.saveProperties();
		assertEquals(0, DataLoader.loadProperties().get(0).getPrice());
	}
	
	@Test
	void testWritingNullUser() {
		propertyList.add(new House( 0,null, 0, 0, 0, 0, 0, 0, 0, 0));
		DataWriter.saveProperties();
		assertEquals(null, DataLoader.loadProperties().get(0).getAddress());
	}
	
}