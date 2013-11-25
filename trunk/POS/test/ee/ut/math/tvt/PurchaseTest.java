package ee.ut.math.tvt;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseTest {
	
	private PurchaseInfoTableModel model = new PurchaseInfoTableModel();
	private List<SoldItem> rows;
	SoldItem item1;
	SoldItem item2;
	SoldItem item3;
	StockItem stockItem1;
	
	@Before
	public void setUp() {
		model.populateWithData(model.getTableRows());
		stockItem1 = new StockItem("Õunamahl", "jook", 0.90, 200);
		item1 = new SoldItem(stockItem1, 0);
		item2 = new SoldItem(stockItem1, 1);
		item3 = new SoldItem(stockItem1, 10);
	}
	
	@Test
	public void testAddSoldItem() {
		model.addItem(item1);
		assertEquals("Õunamahl", model.getColumnValue(item1, 1));
	}
	
	@Test
	public void testGetSumWithNoItems() {
		assertEquals(0, model.getColumnValue(item1, 4));
	}
	
	@Test
	public void testGetSumWithOneItem() {
		
	}
	
	@Test
	public void testGetSumWithMultipleItems() {
		
	}
}
