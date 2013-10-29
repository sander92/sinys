package ee.ut.math.tvt.sinys;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class PaymentWindow<T> extends JFrame {
	
	private String itemName;
	private JTextField item;
	private SalesSystemModel model;
	private List<T> rows;
	
	
	public PaymentWindow() {
		super();
		int width = 400;
		int height = 300;
		
		rows = (List<T>) model.getCurrentPurchaseTableModel().getTableRows();
		
		setTitle("Payment information");
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);
		setVisible(true);
	}
}
