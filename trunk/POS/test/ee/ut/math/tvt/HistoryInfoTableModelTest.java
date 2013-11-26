package ee.ut.math.tvt;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.HistoryInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class HistoryInfoTableModelTest {
	private SalesSystemModel ssm;
	private HistoryInfoTableModel model;
	private SalesDomainController dc;
	private Order order;
	private StockItem item3;
	private SoldItem item1;

	@Before
	public void setUp() {

		dc = new SalesDomainControllerImpl();
		ssm = new SalesSystemModel(dc);
		model = ssm.getHistoryTableModel();

		order = new Order(0, 0, new ArrayList<SoldItem>());
		item3 = model.getItemById(19).getSolditems().get(0).getStockItem();

		item1 = new SoldItem(item3, 2000);

	}

	@Test
	public void testAddOneItem() {
		order.addItemToOrder(item1);
		model.addItem(order);
		Order x = model.getItemById(order.getId());
		assertEquals(order.getId(), x.getId());
		assertEquals(order.getName(), x.getName());
		assertEquals(order.getSum(), x.getSum(), 0.0001);
	}

	@Test
	public void testAddSeveralItem() {
		order.addItemToOrder(item1);
		model.addItem(order);
		Order x = model.getItemById(order.getId());
		assertEquals(order.getId(), x.getId());
		assertEquals(order.getName(), x.getName());
		assertEquals(order.getSum(), x.getSum(), 0.0001);
	}

}
