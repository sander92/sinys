package ee.ut.math.tvt;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {
	
	private StockItem item1;
	private StockItem item2;
	private StockItem item3;
	
	
	/**
    * Methods with @Before annotations will be invoked before each test is run.
	*/
	@Before
	public void setUp() {
		item1 = new StockItem("õlu", "jook", 0.69, 200);
		item2 = new StockItem("krõps", "snack", 0.99, 150);
	}
	
	@Test
	public void testClone() {
		item3 = (StockItem) item1.clone();
		assertEquals(item1.getId(), item3.getId());
		assertEquals(item1.getName(), item3.getName());
		assertEquals(item1.getPrice(), item3.getPrice(),0.0001);
		assertEquals(item1.getQuantity(), item3.getQuantity(),0.0001);

	}
	
	@Test
	public void testGetColumn() {
		assertEquals("õlu", item1.getColumn(1));
	}
}
