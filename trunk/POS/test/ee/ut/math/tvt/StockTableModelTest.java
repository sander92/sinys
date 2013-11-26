package ee.ut.math.tvt;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	private StockTableModel model;
	
	@Before
	public void setUp() {
		// proovisin ka nii, et tekitasin siia salesdomaincontrolleri new SalesDomainControllerImpl(),
		// salessystemmodeli selle sdc'ga ning siis StockTableModeli salessystem.getWarehouseModel värgiga,
		// siis viskab hunniku log4j erroreid ette. praegu on viga selles ilmselt, et ta laeb seest tühja
		// stocktablemodeli
		model = new StockTableModel();
		
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
		assertEquals("", model.getItemById(100).getName());
	}
}
