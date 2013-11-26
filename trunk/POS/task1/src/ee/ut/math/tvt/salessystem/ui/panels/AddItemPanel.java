package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Purchase pane + shopping cart table UI.
 */
public class AddItemPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddItemPanel.class);

	// Text field on the dialogPane
	private JTextField itemDescription;
	private JTextField itemName;
	private JTextField itemPrice;
	private JTextField itemQuantity;

	private JButton addItemButton;

	// Warehouse model
	private SalesSystemModel model;

	/**
	 * Constructs new purchase item panel.
	 * 
	 * @param model
	 *            composite model of the warehouse and the shopping cart.
	 */
	public AddItemPanel(SalesSystemModel model) {
		this.model = model;

		setLayout(new GridBagLayout());

		add(drawDialogPane(), getDialogPaneConstraints());

		setEnabled(false);
	}

	// purchase dialog
	private JComponent drawDialogPane() {

		// Create the panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		panel.setBorder(BorderFactory.createTitledBorder("Add Item"));

		// Initialize the textfields
		itemDescription = new JTextField();
		itemName = new JTextField();
		itemPrice = new JTextField();
		itemQuantity = new JTextField();

		// Fill the dialog fields if the bar code text field loses focus

		itemDescription.setEditable(false);
		itemName.setEditable(false);
		itemPrice.setEditable(false);
		itemQuantity.setEditable(false);

		// == Add components to the panel

		// - item name
		panel.add(new JLabel("Item name:"));
		panel.add(itemName);

		// - item id
		panel.add(new JLabel("Item description:"));
		panel.add(itemDescription);

		// - item price
		panel.add(new JLabel("Item price:"));
		panel.add(itemPrice);

		// - item quantity
		panel.add(new JLabel("Item quantity:"));
		panel.add(itemQuantity);

		// Create and add the button
		addItemButton = new JButton("Add to warehouse");
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItemEventHandler();
			}
		});

		panel.add(addItemButton);

		return panel;
	}

	/**
	 * Add new item to the cart.
	 */
	public void addItemEventHandler() {
		// takes the information from inserted fields, creates a StockItem and
		// adds it to the StockItems list, logs and resets fields
		try {
			if (Integer.parseInt(itemQuantity.getText()) < 0
					|| itemName.getText().trim().equals("")
					|| Integer.parseInt(itemPrice.getText()) < 0) {
				JOptionPane.showMessageDialog(getRootPane(), "Illegal move",
						"STOP", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(getRootPane(), "Illegal move",
					"STOP", JOptionPane.ERROR_MESSAGE);
			return;
		}
		StockItem s1 = new StockItem(itemName.getText(),
				itemDescription.getText(), Double.parseDouble(itemPrice
						.getText()), Integer.parseInt(itemQuantity.getText()));
		List<StockItem> dataset1 = model.getWarehouseTableModel()
				.getTableRows();
		dataset1.add(s1);
		model.getDomainController().saveWarehouseState(dataset1);
		model.getWarehouseTableModel().fireTableDataChanged();
		model.updateWarehouseTableModel();
		log.info("New item added to warehouse");
		reset();
		setEnabled(false);

	}

	/**
	 * Sets whether or not this component is enabled.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		this.addItemButton.setEnabled(enabled);
		this.itemDescription.setEditable(enabled);
		this.itemName.setEditable(enabled);
		this.itemPrice.setEditable(enabled);
		this.itemQuantity.setEditable(enabled);
	}

	/**
	 * Reset dialog fields.
	 */
	public void reset() {
		itemDescription.setText("");
		itemName.setText("");
		itemPrice.setText("");
		itemQuantity.setText("");
	}

	/*
	 * === Ideally, UI's layout and behavior should be kept as separated as
	 * possible. If you work on the behavior of the application, you don't want
	 * the layout details to get on your way all the time, and vice versa. This
	 * separation leads to cleaner, more readable and better maintainable code.
	 * 
	 * In a Swing application, the layout is also defined as Java code and this
	 * separation is more difficult to make. One thing that can still be done is
	 * moving the layout-defining code out into separate methods, leaving the
	 * more important methods unburdened of the messy layout code. This is done
	 * in the following methods.
	 */

	// Formatting constraints for the dialogPane
	private GridBagConstraints getDialogPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.2;
		gc.weighty = 0d;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.NONE;

		return gc;
	}

}
