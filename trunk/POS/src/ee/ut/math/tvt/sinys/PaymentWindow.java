package ee.ut.math.tvt.sinys;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

public class PaymentWindow extends JFrame {

	private static final Logger log = Logger.getLogger(PurchaseTab.class);
	private JTextField paymentAmount;
	private SalesDomainController domainController;
	private final SalesSystemModel model;
	private List<SoldItem> rows;
	private double sum;
	private double change = 0.0;
	private JPanel textPanel;
	private JLabel changeValue;
	private JButton acceptButton;
	private JButton cancelButton;

	public PaymentWindow(final SalesSystemModel model) {
		this.model = model;
		int width = 200;
		int height = 150;

		rows = model.getCurrentPurchaseTableModel().getTableRows();

		for (SoldItem item : rows) {
			sum += item.getSum();
		}

		textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(4, 3));
		add(textPanel);

		JLabel sumLabel = new JLabel("Order sum:  ");
		textPanel.add(sumLabel);

		JLabel sumValue = new JLabel(String.valueOf(sum));
		textPanel.add(sumValue);

		JLabel changeLabel = new JLabel("Change:");
		textPanel.add(changeLabel);

		changeValue = new JLabel(String.valueOf(change), 10);
		textPanel.add(changeValue);

		paymentAmount = new JTextField(15);
		paymentAmount.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateChangeValue();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateChangeValue();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateChangeValue();

			}
		});
		textPanel.add(paymentAmount);

		JLabel emptyLabel = new JLabel("");
		textPanel.add(emptyLabel);

		acceptButton = new JButton("Accept");
		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				submitCurrentPurchase(model.getCurrentPurchaseTableModel()
						.getTableRows());
			}
		});
		textPanel.add(acceptButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelCurrentPurchase();
			}
		});
		textPanel.add(cancelButton);

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
		} catch (Exception e1) {
			e1.getMessage();
		}
		changeValue.setText(String.valueOf(change));
	}

	public void submitCurrentPurchase(List<SoldItem> goods) {
		float price = 0;
		System.out.println(goods.size());
		
		for (int i = 0; i < goods.size(); i++) {
			price += goods.get(i).getPrice();
		}
		
		Order o = new Order(model.getHistoryTableModel().getRowCount() + 1,
				price, goods);
		model.getHistoryTableModel().addItem(o);
		

	}
	public void reset(){
		paymentAmount.setText("");
		changeValue.setText("");
		change=0;
	}
	
	public void cancelCurrentPurchase() {
		this.setVisible(false);
		reset();
	}

}
