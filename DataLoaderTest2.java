import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataLoaderTest2 {
	private Properties properties = Properties.getInstance();
	private ArrayList<Property> propertyList = properties.getProperties();
	
	@BeforeEach
	public void setup() {
		propertyList.clear();
		propertyList.add(new House(378, "100 Main St", 15.5, 10, 99.9, 1, 3, 10, 11, 9.8));
		propertyList.add(new Apartment(873, "111 Main St", 19.9, 11, 77.7, 2, 9.8, 1, 3, true));
		DataWriter.saveProperties();
	}
	
	@AfterEach
	public void tearDown() {
		Properties.getInstance().getProperties().clear();
		DataWriter.saveProperties();
	}
	
	
	@Test
	void testGetPropertiesSize() {
		propertyList = DataLoader.loadProperties();
		assertEquals(2, propertyList.size());
	}

	@Test
	void testGetPropertiesSizeZero() {
		Properties.getInstance().getProperties().clear();
		DataWriter.saveProperties();
		assertEquals(0, propertyList.size());
	}
	
	@Test
	void testGetPropertyFirstAddress() {
		propertyList = DataLoader.loadProperties();
		assertEquals("100 Main St", propertyList.get(0).getAddress());
	}
}