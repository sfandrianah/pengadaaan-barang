/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengadaan.barang.transaksi_in;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author sfandrianah
 */
public class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
  public MyComboBoxRenderer(JComboBox comboBox) {
//    super(items);
      comboBox.addItem("Snowboarding");
            comboBox.addItem("Rowing");
            comboBox.addItem("Chasing toddlers");
            comboBox.addItem("Speed reading");
            comboBox.addItem("Teaching high school");
            comboBox.addItem("None");
//            AutocompleteJComboBox
  }

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
      boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      super.setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(table.getBackground());
    }
    setSelectedItem(value);
    return this;
  }
}
