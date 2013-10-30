package ee.ut.math.tvt.sinys;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

public class PaymentWindow extends JFrame  {
	
	private static final Logger log = Logger.getLogger(PurchaseTab.class);
	private JTextField paymentAmount;
	private SalesSystemModel model;
	private List<SoldItem> rows;
	private double sum;
	private double change;
	private Component comp = getContentPane();
	private JPanel textPanel;
	private JLabel changeValue;
	
	
	
	
	public PaymentWindow(SalesSystemModel model) {
		this.model = model;
		int width = 200;
		int height = 120;
		
		rows = model.getCurrentPurchaseTableModel().getTableRows();
		
		for (SoldItem item : rows) {
			sum += item.getSum();
		}
		
		textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		add(textPanel);
		
		JLabel sumLabel = new JLabel("Sum of the order:");
		textPanel.add(sumLabel);
		
		JLabel sumValue = new JLabel(String.valueOf(sum));
		textPanel.add(sumValue);
		
		paymentAmount = new JTextField(15);
		paymentAmount.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateChangeValue();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateChangeValue();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				updateChangeValue();
				
			}
		})
		;
		textPanel.add(paymentAmount);
		
		JLabel changeLabel = new JLabel("Change:");
		textPanel.add(changeLabel);
		
		changeValue = new JLabel(String.valueOf(change));
		
		setTitle("Payment information");
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);
		setVisible(true);
	}

	public void updateChangeValue() {
		try {
			double payment = Double.parseDouble(paymentAmount.getText());
			change = payment - sum;
		} catch(Exception e1) {
			e1.getMessage();
		}
		changeValue.setText(String.valueOf(change));
		textPanel.add(changeValue);
	}
}
