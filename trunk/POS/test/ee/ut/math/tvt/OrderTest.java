package ee.ut.math.tvt;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class OrderTest {

	SoldItem item1;
	SoldItem item2;
	SoldItem item3;
	StockItem stockItem1;
	Order order;

	@Before
	public void setUp() {
		stockItem1 = new StockItem("õunamahl", "jook", 0.90, 200);
		item1 = new SoldItem(stockItem1, 0);
		item2 = new SoldItem(stockItem1, 1);
		item3 = new SoldItem(stockItem1, 10);
		order = new Order(0, 0, new ArrayList<SoldItem>());
	}

	@Test
	public void testAddSoldItem() {
		order.addItemToOrder(item1);

		assertEquals("õunamahl", order.getSolditems().get(0).getName());
		assertEquals(1, order.getSolditems().size());

	}

	@Test
	public void testGetSumWithNoItems() {
		assertEquals(0, order.getSum(), 0.0001);
	}

	@Test
	public void testGetSumWithOneItem() {
		order.addItemToOrder(item1);

		assertEquals(item1.getSum(), order.getSum(), 0.0001);

	}

	@Test
	public void testGetSumWithMultipleItems() {
		order.addItemToOrder(item1);
		order.addItemToOrder(item2);
		order.addItemToOrder(item3);
		assertEquals(item1.getSum() + item2.getSum() + item3.getSum(),
				order.getSum(), 0.0001);

	}
}
