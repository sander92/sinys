package ee.ut.math.tvt;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	private SalesDomainController model;
	private List<StockItem> warehouse;
	
	@Before
	public void setUp() {
		warehouse = model.loadWarehouseState();
	}
	
	@Test
	public void testValidateNameUniqueness() {
		//TODO
	}
	
	@Test
	public void testHasEnoughInStock() {
		//TODO
	}
	
	@Test
	public void testGetItemByIdWhenItemExists() {
		//TODO
	}
	
	@Test
	public void testGetItemByIdWhenThrowsException() {
		//TODO
	}
}
