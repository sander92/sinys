package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Order history details model.
 */
public class HistoryInfoTableModel extends SalesSystemTableModel<Order> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(HistoryInfoTableModel.class);
	
	public HistoryInfoTableModel() {
		super(new String[] { "Id", "Date", "Sum"});
	}

	@Override
	protected Object getColumnValue(Order item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getDate();
		case 2:
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

		for (final Order item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getDate() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
    /**
     * Add new Order to table.
     */
    public void addItem(final Order item) {
        rows.add(item);
        Session s=HibernateUtil.currentSession();
        Transaction tx=s.beginTransaction();
        s.save(item);
        s.flush();
        tx.commit();
        //s.getTransaction().commit();
        log.debug("Added " + item.getName());
        fireTableDataChanged();
    }
    
    
}
