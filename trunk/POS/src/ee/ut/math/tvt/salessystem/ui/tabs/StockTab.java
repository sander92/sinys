package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.ui.panels.AddItemPanel;



public class StockTab {

  private JButton addItem;
  
  private JButton cancelItem;

  private SalesSystemModel model;
  
  private AddItemPanel addItemPanel;

  public StockTab(SalesSystemModel model) {
    this.model = model;
  }

  // warehouse stock tab - consists of a menu and a table
  public Component draw() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints gc = new GridBagConstraints();
    panel.setLayout(gb);
    
    panel.add(drawStockMenuPane(), getConstraintsForAddItemMenu());

    addItemPanel = new AddItemPanel(model);
    panel.add(addItemPanel, getConstraintsForAddItemPanel());

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;


    gc.weighty = 1.0;
    gc.fill = GridBagConstraints.BOTH;
    panel.add(drawStockMainPane(), gc);
    return panel;
  }

  // warehouse menu
  private Component drawStockMenuPane() {
    JPanel panel = new JPanel();

    GridBagConstraints gc = getConstraintsForButtons();
    GridBagLayout gb = new GridBagLayout();

    panel.setLayout(gb);


    addItem = createAddItemButton();
    cancelItem = createCancelAddButton();

    panel.add(addItem, gc);
    panel.add(cancelItem, gc);

    return panel;
  }


  // table of the wareshouse stock
  private Component drawStockMainPane() {
    JPanel panel = new JPanel();

    JTable table = new JTable(model.getWarehouseTableModel());

    JTableHeader header = table.getTableHeader();
    header.setReorderingAllowed(false);

    JScrollPane scrollPane = new JScrollPane(table);

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    gc.fill = GridBagConstraints.BOTH;
    gc.weightx = 1.0;
    gc.weighty = 1.0;

    panel.setLayout(gb);
    panel.add(scrollPane, gc);

    panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
    return panel;
  }
  
  
  // Creates the add and cancel buttons 
  
  
  private JButton createAddItemButton() {
    JButton b = new JButton("Add Item");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  newAddItemClicked();
      }
    });
    b.setEnabled(true);
    
    return b;
  }
  
  private JButton createCancelAddButton() {
	    JButton b = new JButton("Cancel");
	    b.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  newCancelAddClicked();
	      }
	    });
	    b.setEnabled(false);
	    
	    return b;
	  }
	  
  
  
  
  protected void newAddItemClicked() {
	  addItemPanel.setEnabled(true);
	  cancelItem.setEnabled(true);
  }

	  
	  protected void newCancelAddClicked() {
		  addItemPanel.reset();
		  addItemPanel.setEnabled(false);
		  cancelItem.setEnabled(false);
	  }
  
	  
	  
	  // Layout stuff
	  
	  
  private GridBagConstraints getConstraintsForAddItemMenu() {
	    GridBagConstraints gc = new GridBagConstraints();

	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.anchor = GridBagConstraints.NORTH;
	    gc.gridwidth = GridBagConstraints.REMAINDER;
	    gc.weightx = 1.0d;
	    gc.weighty = 0d;

	    return gc;
	  }

  private GridBagConstraints getConstraintsForAddItemPanel() {
	    GridBagConstraints gc = new GridBagConstraints();

	    gc.fill = GridBagConstraints.BOTH;
	    gc.anchor = GridBagConstraints.NORTH;
	    gc.gridwidth = GridBagConstraints.REMAINDER;
	    gc.weightx = 1.0d;
	    gc.weighty = 0.0d;

	    return gc;
	  }
  
  private GridBagConstraints getConstraintsForButtons() {
	    GridBagConstraints gc = new GridBagConstraints();

	    gc.weightx = 0;
	    gc.anchor = GridBagConstraints.CENTER;
	    gc.gridwidth = GridBagConstraints.RELATIVE;

	    return gc;
	  }
  
}
