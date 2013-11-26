package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;

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
		currentPurchaseTableModel = new PurchaseInfoTableModel(this);
		historyTableModel = new HistoryInfoTableModel();
		historyItemTableModel = new HistoryItemTableModel();
		// populate stock model with data from the warehouse
		warehouseTableModel.populateWithData(domainController
				.loadWarehouseState());

		historyTableModel.populateWithData(domainController.loadHistoryState());
	}

	public StockTableModel getWarehouseTableModel() {
		return warehouseTableModel;
	}

	public void updateWarehouseTableModel() {
		warehouseTableModel.populateWithData(domainController.loadWarehouseState());
	}
	
	public PurchaseInfoTableModel getCurrentPurchaseTableModel() {
		return currentPurchaseTableModel;
	}

	public SalesDomainController getDomainController() {
		return domainController;
	}

}
