package ee.ut.math.tvt;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	
	private SoldItem item1;
	private SoldItem item2;
	private StockItem item3;
	
	@Before
	public void setUp() {
		item3 = new StockItem("nisu", "vili", 2, 5000);
		item1 = new SoldItem(item3, 2000);
	}
	
	@Test
	public void testGetSum() {
		assertEquals(4000, item1.getSum(), 0.0001);
	}
	
	@Test
	public void testGetSumWithZeroQuantity() {
		item1.setQuantity(0);
		assertEquals(0, item1.getSum(), 0.0001);
	}
}
