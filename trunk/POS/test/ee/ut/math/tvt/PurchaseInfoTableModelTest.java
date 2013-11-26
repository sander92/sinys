package ee.ut.math.tvt;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class PurchaseInfoTableModelTest {
	private SalesSystemModel ssm;
	private PurchaseInfoTableModel model;
	private SalesDomainController dc;
	private StockItem item3;
	private SoldItem item1;

	@Before
	public void setUp() {

		dc = new SalesDomainControllerImpl();
		ssm = new SalesSystemModel(dc);
		model = ssm.getCurrentPurchaseTableModel();
		item3 = ssm.getWarehouseTableModel().getItemById(3);
		item1 = new SoldItem(item3, 2);
	}

//	@Test
//	public void testGetForStockItem() {
//		model.addItem(item1);
//		SoldItem x = model.getForStockItem(item1.getId());
//		try {
//			assertEquals(item1.getId(), x.getId());
//			assertEquals(item1.getName(), x.getName());
//		} catch (NullPointerException npe) {
//			Assert.fail();
//		}
//
//	}

	@Test
	public void testAddItem() {
		ssm.getCurrentPurchaseTableModel().addItem(item1);
		
		SoldItem x = model.getItemById(item1.getId());
		try {
			assertEquals(item1.getId(), x.getId());
			assertEquals(item1.getName(), x.getName());
		} catch (NullPointerException npe) {
			Assert.fail();
		}

	}
	
	@Test
	public void testGetRowCount() {
		model.addItem(item1);
		assertEquals(1, model.getRowCount());
	}
	
	@Test
	public void testClear() {
		model.addItem(item1);
		assertEquals(1, model.getRowCount());
		model.clear();
		assertEquals(0, model.getRowCount());

	}
}
