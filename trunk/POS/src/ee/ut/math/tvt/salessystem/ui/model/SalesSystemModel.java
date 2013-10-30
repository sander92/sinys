package ee.ut.math.tvt.salessystem.ui.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

/**
 * Main model. Holds all the other models.
 */
public class SalesSystemModel {

	private static final Logger log = Logger.getLogger(SalesSystemModel.class);

	// Warehouse model
	private StockTableModel warehouseTableModel;

	// Current shopping cart model
	private PurchaseInfoTableModel currentPurchaseTableModel;

	private HistoryInfoTableModel historyTableModel;

	private HistoryItemTableModel historyItemTableModel;

	public HistoryItemTableModel getHistoryItemTableModel() {
		return historyItemTableModel;
	}

	public HistoryInfoTableModel getHistoryTableModel() {
		return historyTableModel;
	}

	private final SalesDomainController domainController;

	/**
	 * Construct application model.
	 * 
	 * @param domainController
	 *            Sales domain controller.
	 */
	public SalesSystemModel(SalesDomainController domainController) {
		this.domainController = domainController;

		warehouseTableModel = new StockTableModel();
		currentPurchaseTableModel = new PurchaseInfoTableModel();
		historyTableModel = new HistoryInfoTableModel();
		historyItemTableModel = new HistoryItemTableModel();
		// populate stock model with data from the warehouse
		warehouseTableModel.populateWithData(domainController
				.loadWarehouseState());
	}

	public StockTableModel getWarehouseTableModel() {
		return warehouseTableModel;
	}

	public boolean saveWarehouseState(List<StockItem> dataset) {
		ObjectOutputStream oout = null;
		try {
			oout = new ObjectOutputStream(new FileOutputStream(
					"res/warehouse.data"));
			oout.writeObject(dataset);

		} catch (IOException e) {
			log.error(e.toString());
			return false;
		} finally {
			try {
				oout.close();
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return true;
	}

	public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
		return currentPurchaseTableModel;
	}

}
