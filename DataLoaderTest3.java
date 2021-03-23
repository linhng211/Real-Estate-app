import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataLoaderTest3 {
	private Reviews reviews = Reviews.getInstance();;
	private ArrayList<Review> reviewList = reviews.getReviews();
	
	@BeforeEach
	public void setup() {
		reviewList.clear();
		reviewList.add(new Review(987, 765, 543, 5, "awesome"));
		reviewList.add(new Review(123, 345, 567, 1, "worst"));
		DataWriter.saveReviews();
	}
	
	@AfterEach
	public void tearDown() {
		Reviews.getInstance().getReviews().clear();
		DataWriter.saveReviews();
	}
	
	
	@Test
	void testGetUsersSize() {
		reviewList = DataLoader.loadReviews();
		assertEquals(2, reviewList.size());
	}

	@Test
	void testGetUsersSizeZero() {
		Reviews.getInstance().getReviews().clear();
		DataWriter.saveReviews();
		assertEquals(0, reviewList.size());
	}
	
	@Test
	void testGetUserFirstUserName() {
		reviewList = DataLoader.loadReviews();
		assertEquals("awesome", reviewList.get(0).getFeedback());
	}
}