package ee.ut.math.tvt;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	private SalesSystemModel ssm;
	private StockTableModel model;
	private SalesDomainController dc;
	@Before
	public void setUp() {
		dc=new SalesDomainControllerImpl();
		ssm = new SalesSystemModel(dc);
		model=ssm.getWarehouseTableModel();
	}
	
	@Test
	public void testValidateNameUniqueness() {
		assertEquals(false, model.validateNameUniqueness("Cookies"));
	}
	
	@Test
	public void testHasEnoughInStock() {
		assertEquals(false, model.hasEnoughInStock(model.getItemByName("Cookies"), 38));
		assertEquals(true, model.hasEnoughInStock(model.getItemByName("Cookies"), 12));
	}
	
	@Test
	public void testGetItemByIdWhenItemExists() {
		assertEquals("Cookies", model.getItemById(1).getName());
	}
	
	@Test
	public void testGetItemByIdWhenThrowsException() {
//		assertEquals("", model.getItemById(100).getName());
		try {
			model.getItemById(100).getName();
			 Assert.fail();
		  }
		  catch (NoSuchElementException ex) {
			  Assert.assertTrue(true);
		  }
	}
}
