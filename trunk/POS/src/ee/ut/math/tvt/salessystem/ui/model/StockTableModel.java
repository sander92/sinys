package ee.ut.math.tvt.salessystem.ui.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Stock item table model.
 */
public class StockTableModel extends SalesSystemTableModel<StockItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StockTableModel.class);

	public StockTableModel() {
		super(new String[] { "Id", "Name", "Price", "Quantity" });
	}

	@Override
	protected Object getColumnValue(StockItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	/**
	 * Add new stock item to table. If there already is a stock item with same
	 * id, then existing item's quantity will be increased.
	 * 
	 * @param stockItem
	 */
	public void addItem(final StockItem stockItem) {
		Session s = HibernateUtil.currentSession();
		StockItem sItem = (StockItem) s.get(StockItem.class, stockItem.getId());
		Transaction tx = s.beginTransaction();

		if (sItem == null) {
			rows.add(stockItem);
			s.save(stockItem);
			log.debug("Added " + stockItem.getName() + " quantity of "
					+ stockItem.getQuantity());
		} else {
			StockItem thisItem = getItemById(stockItem.getId());
			sItem.setQuantity(thisItem.getQuantity() + stockItem.getQuantity());
			s.update(sItem);
			log.debug("Found existing item " + sItem.getName()
					+ " increased quantity by " + sItem.getQuantity());
		}
		s.flush();
		tx.commit();

		fireTableDataChanged();
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final StockItem stockItem : rows) {
			buffer.append(stockItem.getId() + "\t");
			buffer.append(stockItem.getName() + "\t");
			buffer.append(stockItem.getPrice() + "\t");
			buffer.append(stockItem.getQuantity() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public String[] getItemNames() {
		List<String> l = new ArrayList<String>();
		for (StockItem item : rows) {
			l.add(item.getName());
		}

		return l.toArray(new String[l.size()]);
	}

	public void decrementItemQuantityById(long id, int quantityToDecBy) {
		for (StockItem item : rows) {
			if (item.getId() == id) {
				if ((item.getQuantity() - quantityToDecBy) >= 0) {
					item.setQuantity(item.getQuantity() - quantityToDecBy);
				} else {
					log.error("Cannot make sale. Item quantity would be negative");
				}
			}

		}
		fireTableDataChanged();
	}
	
	public void incrementItemQuantityById(long id, int quantityToIncBy) {
		for (StockItem item : rows) {
			if (item.getId() == id) {
				if ((item.getQuantity() + quantityToIncBy) >= 0) {
					item.setQuantity(item.getQuantity() + quantityToIncBy);
				} else {
					log.error("Illegal move. Item quantity would be negative");
				}
			}

		}
		fireTableDataChanged();
	}
	
	
	public boolean hasEnoughInStock(StockItem item, int quantity) {
		for(StockItem i : this.rows) {
			if (i.getId().equals(item.getId())) {
				return (i.getQuantity() >= quantity);
			}
		}
		return false;
	}
	
	
	public boolean validateNameUniqueness(String newName) {
		for (StockItem item : rows) {
			log.debug(" === Comparing: " + newName + " vs. " + item.getName());
			
			if (newName.equals(item.getName())) {
				return false;
			}
		}
		return true;
	}
}
