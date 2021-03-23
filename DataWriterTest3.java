import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest3 {
	private Reviews reviews = Reviews.getInstance();;
	private ArrayList<Review> reviewList = reviews.getReviews();
	
	@BeforeEach
	public void setup() {
		Reviews.getInstance().getReviews().clear();
		DataWriter.saveReviews();
	}
	
	@AfterEach
	public void tearDown() {
		Reviews.getInstance().getReviews().clear();
		DataWriter.saveReviews();
	}
	
	
	@Test
	void testWritingZeroUsers() {
		reviewList = DataLoader.loadReviews();
		assertEquals(0, reviewList.size());
	}

	@Test
	void testWritingOneUser() {
		reviewList.add(new Review(789,456,123,5,"nice"));
		DataWriter.saveReviews();
		assertEquals("nice", DataLoader.loadReviews().get(0).getFeedback());
	}
	
	@Test
	void testWritingFiveUsers() {
		reviewList.add(new Review(111,222,333,5,"pretty"));
		reviewList.add(new Review(222,333,444,5,"beautiful"));
		reviewList.add(new Review(333,444,555,5,"fire"));
		reviewList.add(new Review(444,555,888,1,"not recommend"));
		reviewList.add(new Review(555,666,777,2,"not good"));
		DataWriter.saveReviews();
		assertEquals("not good", DataLoader.loadReviews().get(4).getFeedback());
	}
	
	@Test
	void testWritingEmptyUser() {
		reviewList.add(new Review(0,0,0,0,""));
		DataWriter.saveReviews();
		assertEquals("", DataLoader.loadReviews().get(0).getFeedback());
	}
	
	@Test
	void testWritingNullUser() {
		reviewList.add(new Review( 0,0,0,0,null));
		DataWriter.saveReviews();
		assertEquals(null, DataLoader.loadReviews().get(0).getFeedback());
	}
	
}