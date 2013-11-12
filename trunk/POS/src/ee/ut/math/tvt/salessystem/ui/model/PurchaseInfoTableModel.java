package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Purchase history details model.
 */
public class PurchaseInfoTableModel extends SalesSystemTableModel<SoldItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger(PurchaseInfoTableModel.class);

	public PurchaseInfoTableModel() {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum" });
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SoldItem item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

	/**
	 * Add new StockItem to table.
	 */
	public void addItem(final SoldItem item) {
		/**
		 * XXX In case such stockItem already exists increase the quantity of
		 * the existing stock.
		 */

		SoldItem sItem = null;
		try {
			sItem = getItemById(item.getId());
		} catch (NoSuchElementException nsee) {

		}
		if (sItem == null) {
			rows.add(item);
			log.debug("Added " + item.getName() + " quantity of "
					+ item.getQuantity());
		} else {
			SoldItem thisItem = getItemById(item.getId());
			sItem.setQuantity(thisItem.getQuantity() + item.getQuantity());
			log.debug("Found existing item " + sItem.getName()
					+ " increased quantity by " + sItem.getQuantity());
		}

		fireTableDataChanged();

		/*
		 * rows.add(item); log.debug("Added " + item.getName() + " quantity of "
		 * + item.getQuantity()); fireTableDataChanged();
		 */
	}

	public void addItemsToDB() {
		Session s = HibernateUtil.currentSession();

		for (SoldItem item : rows) {
			Transaction tx = s.beginTransaction();
			s.save(item);
			s.flush();
			tx.commit();
		}

	}
}
