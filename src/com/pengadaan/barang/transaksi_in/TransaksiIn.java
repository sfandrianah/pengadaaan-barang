package com.pengadaan.barang.transaksi_in;

import com.pengadaan.barang.produk.*;
import com.pengadaan.barang.kategory.*;
import com.pengadaan.barang.PengadaanBarang;
import com.pengadaan.barang.start;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;

public class TransaksiIn extends javax.swing.JFrame {

    private Integer row, rows, row2, rows3;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable();
    private DefaultTableModel DfltTblMode;
    private TableColumn column;
    private String e, r, i, k, a, satuan, code, desc, date, divisi;
    private int idcategory;
    DefaultTableModel models = new DefaultTableModel();

    public TransaksiIn() {
        initComponents();
        aplikasiInventory.konekkeDatabase();
//        tampilDataKeTabel();
        columnTable();
        enableBtn(false);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(false);
//        jTxtFldKD_BARANG2.setVisible(false);
//        jTxtFldNM_BARANG2.setVisible(false);
        listDivisi();
        jXDatePicker1.setDate(Calendar.getInstance().getTime());
        jXDatePicker1.setFormats(new SimpleDateFormat("yyyy-MM-dd"));

       // panel.add(picker);
        // listSatuan();
    }
    
    private void keyEventAll(KeyEvent key){
        String test = "";
        
        test = String.valueOf(key.getKeyChar());
        jTextField1.setEnabled(true);
        String jtf1 = jTextField1.getText();
        
        String test2 = jtf1+test;
        
        Double hasil = 0D;
        System.out.println(jtf1);
//        System.out.println(jTextField2.getText());
        hasil = Double.parseDouble(test2) * Double.parseDouble(jTextField2.getText());
        jTextField5.setEnabled(true);
//        jLabel16.sete
        jTextField5.setText(hasil.toString());
        
    }

    private void listDivisi() {

        try {

            String sql = "Select * from mst_divisi";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set;
            set = st.executeQuery(sql);
            int no = 0;
            while (set.next()) {
                jCmbJBTN.addItem(new DivisiDv(set.getInt("id"), set.getString("name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiIn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void listSatuan(){
//        jCmbJBTN2.removeAllItems();
        
       
        
            
        try {
            CategoryBarangDv categoryDv = (CategoryBarangDv) jComboBox1.getSelectedItem();
            if (categoryDv.getId() != 0) {

                String sql = "Select * from mst_barang where id=" + categoryDv.getId();
                Statement st;

                st = aplikasiInventory.config.getConnection().createStatement();

                ResultSet set;
                set = st.executeQuery(sql);
                int no = 0;
                String text = "";
                String price = "";
                while (set.next()) {
                    text = set.getString("satuan_barang");
                    System.out.println("set " + set.getString("satuan_barang"));
                    price = String.valueOf(set.getDouble("hrg_jual_barang"));

                }
                jTextField4.setEnabled(true);
                jTextField4.setText(text);
                jTextField2.setEnabled(true);
                jTextField2.setText(price);

                String test = "";

//        test = String.valueOf(key.getKeyChar());
                jTextField1.setEnabled(true);
                String jtf1 = jTextField1.getText();

                String test2 = jtf1;

                Double hasil = 0D;
                System.out.println(jtf1);
//        System.out.println(jTextField2.getText());
                hasil = Double.parseDouble(test2) * Double.parseDouble(jTextField2.getText());
                jTextField5.setEnabled(true);
//        jLabel16.sete
                jTextField5.setText(hasil.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiIn.class.getName()).log(Level.SEVERE, null, ex);
        }


        
        
            
    }
    
    private void keyListBarang(KeyEvent key){
//        String sending;
//        CategoryBarangDv categoryDv = (CategoryBarangDv) jComboBox1.getSelectedItem();
//        String getNames = categoryDv.getName();
//        sending = getNames + String.valueOf(e.getKeyChar());
        
        JTextComponent text = (JTextComponent) this.jComboBox1.getEditor().getEditorComponent();
        String str = (String) text.getText();
        System.out.println(str);
        jComboBox1.removeAllItems();
        try {
            String sql = "Select * from mst_barang where nm_barang like '%"+str+"%'";
            Statement st;

            st = aplikasiInventory.config.getConnection().createStatement();

            ResultSet set;
            set = st.executeQuery(sql);
            int no = 0;
            jComboBox1.addItem(new CategoryBarangDv(0, ""));
            while (set.next()) {
                jComboBox1.addItem(new CategoryBarangDv(set.getInt("id"), set.getString("nm_barang")));
            }
            text.setText(str);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void listBarang() {
         jComboBox1.removeAllItems();
        try {

            String sql = "Select * from mst_barang";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set;
            set = st.executeQuery(sql);
            int no = 0;
            jComboBox1.addItem(new CategoryBarangDv(0, ""));
            while (set.next()) {
                jComboBox1.addItem(new CategoryBarangDv(set.getInt("id"), set.getString("nm_barang")));
            }
            
            jComboBox1.getEditor().getEditorComponent().addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
//                    keyListBarang(e);
                //    System.out.println("masuk 1");
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyPressed(KeyEvent e) {
//                    keyListBarang(e);
//                    System.out.println("masuk 2");
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    keyListBarang(e);
//                    System.out.println("masuk 3");
//                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiIn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clearTEXT() {
       // jTxtFldNO_TRANS.setText("");
//        jTxtFldNM_BARANG.setText("");
//        jCmbJBTN2.removeAllItems();
    }

    private void enableBtn(boolean x) {
        jBtnDlt.setEnabled(x);
        jBtnEdit.setEnabled(x);
    }

    private void enviBtnSave(boolean x) {
        jBtnSave.setEnabled(x);
        jBtnSave.setVisible(x);
    }

    private void enviBtnNew(boolean x) {
        jBtnNew.setEnabled(x);
        jBtnNew.setVisible(x);
    }

    private void enviBtnSave2(boolean x) {
        jBtnSave2.setEnabled(x);
        jBtnSave2.setVisible(x);
    }

    private void enableField(boolean x) {
        jTxtFldNO_TRANS.setEnabled(x);
//        jTxtFldNM_BARANG.setEnabled(x);
    }

    private void tampilDataKeTabel() {
        jTabel = jTable1;
        tabelModel(jTabel);
    }

    private void tampilDataKeTabelcari() {
        jTabel = jTable1;
        cari(jTabel);
    }

    private void kondisiSave() {
        code = jTxtFldNO_TRANS.getText();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dt1.format(jXDatePicker1.getDate()));
        date = dt1.format(jXDatePicker1.getDate());
        DivisiDv categoryDv = (DivisiDv) jCmbJBTN.getSelectedItem();
        divisi = String.valueOf(categoryDv.getId());
        desc = jTextArea1.getText();
        
      // r = jTxtFldKD_BARANG.getText();
//       i = jTxtFldNM_BARANG.getText();

        
//       satuan = jCmbJBTN2.getSelectedItem().toString();

        try {
            
            if (code.equals("") || desc.equals("")) {
                JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
                clearTEXT();
            } else {
                
                
                Statement st = aplikasiInventory.config.getConnection().createStatement();
                st.executeUpdate(
                        "insert into trx_pemasukan"
                        + "(trx_no_pemasukan, trx_date_pemasukan,divisi_id,trx_desc_pemasukan,trx_total_pemasukan) values ('" + code + "','" + date + "','"+divisi+"','" + desc + "',"+jLabel2.getText()+")");

//                tampilDataKeTabel();
                String sending = "";
                int i;
                for (i=0; i<jTable1.getRowCount(); i++){
                    int n = i + i;
                    
                    String kolom2 = jTable1.getValueAt(i, 1).toString();
                    String kolom3 = jTable1.getValueAt(i, 2).toString();
                    String kolom4 = jTable1.getValueAt(i, 3).toString();
                    String kolom5 = jTable1.getValueAt(i, 4).toString();
                    String kolom6 = jTable1.getValueAt(i, 5).toString();
                    String kolom7 = jTable1.getValueAt(i, 6).toString();
                    String kolom8 = jTable1.getValueAt(i, 7).toString();
                    
                    String coma = "";
                    if(n == jTable1.getRowCount()){
                        coma = "";
                    } else {
                        coma = ",";
                    }
                    sending = sending + "('"+kolom2+"','"+kolom3+"',"+kolom5+",'"+kolom6+"','"+kolom7+"','"+kolom8+"',LAST_INSERT_ID())"+coma;
                    
                    System.out.println("kolom 2 = "+kolom2+
                            "kolom 3 = "+kolom3+
                            "kolom 4 = "+kolom4+
                            "kolom 5 = "+kolom5);
                    
                }
                System.out.println("sending = "+sending);
                
                 st.executeUpdate(
                        "insert into trx_pemasukan_item"
                        + "(barang_id, barang_name,trx_qty_pemasukan_item,trx_price_pemasukan_item,"
                        + "trx_honor_pemasukan_item,trx_total_pemasukan_item,trx_pemasukan_id) "
                        + "values "+sending);
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Data gagal disimpan! : " + ex);
        }
    }

    private void kondisiEdit() {
        r = jTxtFldNO_TRANS.getText();
//       i = jTxtFldNM_BARANG.getText();
//        k = jTxtFldKD_BARANG2.getText();
//       a = jTxtFldNM_BARANG2.getText();

        try {
            if (r.equals("") || i.equals("")) {
                JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
                clearTEXT();
            } else if (r.equals(k) && i.equals(a)) {
                JOptionPane.showMessageDialog(null, "Tidak ada data yang diperbaharui!");
            } else {
                Statement st = aplikasiInventory.config.getConnection().createStatement();
                st.executeUpdate(
                        "update mst_category_barang set "
                        + "kd_ctg = " + "'" + r + "', "
                        + "nm_ctg = " + "'" + i + "' "
                        + "where kd_ctg = '" + k + "'");

                tampilDataKeTabel();
                JOptionPane.showMessageDialog(this, "Data berhasil diperbaharui");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Data gagal diperbaharui! : " + ex);
        }
    }

    private void listBarang(KeyEvent value, JComboBox jcomb, int columns, int rows, DefaultTableModel dtm) {
        int modifiersEx = value.getModifiersEx();
        String modString = "extended modifiers = " + modifiersEx;
        String tmpString = KeyEvent.getModifiersExText(modifiersEx);
        if (tmpString.length() > 0) {
            modString += " (" + tmpString + ")";
        } else {
            modString += " (no extended modifiers)";
        }
        System.out.println(tmpString);

        String values = null;
        values += value;
        System.out.println("values" + values);
        try {
            //jcomb.removeAllItems();
            String sql = "Select * from mst_barang where nm_barang like '%" + values + "'";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set;
            set = st.executeQuery(sql);
            int no = 0;
            while (set.next()) {

                jcomb.addItem(new DivisiDv(set.getInt("id"), set.getString("nm_barang")));
                dtm.setValueAt(jcomb, rows, columns);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiIn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void columnTable() {
        System.out.println("masuk sini");

        jTable1.setModel(models);
//        JTable table = new JTable(model);

// Create a couple of columns 
        models.addColumn("No");
        models.addColumn("");
        models.addColumn("Nama Barang");
        models.addColumn("Satuan");
        models.addColumn("Qty");
        models.addColumn("Harga");
        models.addColumn("Honor");
        models.addColumn("Jumlah");
     

    }

    private void modeljDialogPRD(JTable jTabel3, Integer rowss) {
        try {
            Object[] field = {"No", "", "Nama Produk", "Satuan", "Harga"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel3.setModel(DfltTblMode);

            row2 = rowss;
            String sql = "Select * from mst_barang";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("nm_barang");
                String kolom3 = set.getString("satuan_barang");
                String kolom4 = String.valueOf(set.getInt("id")).toString();
                String kolom5 = String.valueOf(set.getDouble("hrg_beli_barang")).toString();
                System.out.println("so " + kolom3);
                if (kolom3 == null) {
                    kolom3 = "";
                } else if (kolom2 == null) {
                    kolom2 = "";
                }
                String[] data = {kolom1, kolom4, kolom2, kolom3,kolom5};
                DfltTblMode.addRow(data);
            }

            jTabel3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel3.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);

            column = jTabel3.getColumnModel().getColumn(1);
            column.setPreferredWidth(10);
            column = jTabel3.getColumnModel().getColumn(2);
            column.setPreferredWidth(250);
            column = jTabel3.getColumnModel().getColumn(3);
            column.setPreferredWidth(75);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e);
        }
    }

    private void klikTabeljDialog(JTable jTabel) {
        jTabel.setRowSelectionAllowed(true);
        row2 = jTabel.getSelectedRow();
        String kolom1 = jTabel.getValueAt(row2, 0).toString();
        String kolom2 = jTabel.getValueAt(row2, 1).toString();
        String kolom3 = jTabel.getValueAt(row2, 2).toString();
        String kolom4 = jTabel.getValueAt(row2, 3).toString();
        String kolom6 = jTabel.getValueAt(row2, 4).toString();

//        System.out.println("os " + kolom3);
//        jTable1.setValueAt(kolom1, row, 0);
        jTable1.setValueAt(kolom2, rows, 1);
        jTable1.setValueAt(kolom3, rows, 2);
        jTable1.setValueAt(kolom4, rows, 3);
        jTable1.setValueAt(kolom6, rows, 5);
    }

    private void klikButtonItem(DefaultTableModel model){

        int no = model.getRowCount() + 1;
        CategoryBarangDv categoryDv = (CategoryBarangDv) jComboBox1.getSelectedItem();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
//        jTable1.(false);
        
        model.addRow(new Object[]{String.valueOf(no), String.valueOf(categoryDv.getId()), categoryDv.getName(), jTextField4.getText(), jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), jTextField5.getText()});
       
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = jTable1.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
       
        column = jTable1.getColumnModel().getColumn(1);
        column.setPreferredWidth(10);
        
        column = jTable1.getColumnModel().getColumn(2);
        column.setPreferredWidth(150);
        
        column = jTable1.getColumnModel().getColumn(3);
        column.setPreferredWidth(70);
        column = jTable1.getColumnModel().getColumn(4);
        column.setPreferredWidth(50);
        
        column = jTable1.getColumnModel().getColumn(5);
        column.setPreferredWidth(150);
        column = jTable1.getColumnModel().getColumn(6);
        column.setPreferredWidth(150);
        column = jTable1.getColumnModel().getColumn(7);
        column.setPreferredWidth(150);
    }
    
    
     private void klikUpdateItem(DefaultTableModel model){

        int no = model.getRowCount() + 1;
        CategoryBarangDv categoryDv = (CategoryBarangDv) jComboBox1.getSelectedItem();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
//        jTable1.(false);
        
        
        model.setValueAt(String.valueOf(categoryDv.getId()), rows, 1);
        model.setValueAt(categoryDv.getName(), rows,2);
        model.setValueAt(jTextField4.getText(), rows,3);
        model.setValueAt(jTextField1.getText(), rows,4);
        model.setValueAt(jTextField2.getText(), rows,5);
        model.setValueAt(jTextField3.getText(), rows,6);
        model.setValueAt(jTextField5.getText(), rows,7);
        
    }
    
    private void addItem(DefaultTableModel model) {
//        ItemDv itemDv = new ItemDv(0, 0,"tes",0, 0, 0, 0);

//        if (getcol == 2 || getcol == 1 || getcol == 3) {
//            System.out.println("tes1" + kolom2);
            jDialog2.setLocationRelativeTo(null);
//            modeljDialogPRD(jTable2, rowss);
            listBarang();
            jBtnOk2.setEnabled(true);
            jBtnKembali2.setEnabled(true);
             jBtnOk2.setVisible(false);
            jBtnKembali2.setVisible(false);
//            jBtnOk2.setEnabled(false);
            jDialog2.setVisible(true);
//        }
        
    
    }
    
   public void keyupTable(JTable jTabel, String test) {
        jTabel.setRowSelectionAllowed(true);
        rows = jTabel.getSelectedRow();
        String kolom1 = jTabel.getValueAt(rows, 0).toString();
        String kolom2 = jTabel.getValueAt(rows, 1).toString();
        int getcol = jTabel.getSelectedColumn();
        String kolom3 = jTabel.getValueAt(rows, 2).toString();
        String kolom6 = jTabel.getValueAt(rows, 5).toString();
        

        System.out.println("sout " + jTabel.getSelectedColumn());

//        jTabel.getSelectedColumn();
//        Integer rowss = rows3;
        Double productdb = Double.valueOf(kolom6);
        Double total = productdb * Double.valueOf(test);
//        jTabel.setValueAt(test, rows, 5);
        jTabel.setValueAt("0", rows, 6);
        jTabel.setValueAt(total, rows, 7);
        Double jumlahAll = 0D;
        for (int i=0; i<jTable1.getRowCount(); i++){
            jumlahAll += Double.valueOf(jTabel.getValueAt(i, 7).toString());
        }
        
        jLabel2.setText(jumlahAll.toString());
//        jTxtFldNO_TRANS.setText(kolom2);

    }

    private void tabelModel(JTable jTabel) {
        try {
            Object[] field = {"No", "Nama Barang", "Satuan", "QTY", "Harga", "Honor", "Jumlah"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);

            String namectg = null;

            System.out.println("id barang : " + namectg);
//                 CategoryBarangDv category = (CategoryBarangDv) jCmbJBTN.getName(set.getInt("ctg_barang_id"));
//                 namectg = jCmbJBTN.get
            String kolom1 = "";
            String kolom2 = "";
            String kolom3 = "";
            String kolom4 = "";
            String kolom5 = "";

            String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5};
            DfltTblMode.addRow(data);

            jTabel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel.getColumnModel().getColumn(1);
            column.setPreferredWidth(156);
            
            final int columns = jTabel.getSelectedColumn();
            final int rows = jTabel.getSelectedRow();

            final JComboBox comboBox = new JComboBox();
            String sqls = "Select * from mst_barang limit 10";
            Statement sts = aplikasiInventory.config.getConnection().createStatement();
            ResultSet sets = sts.executeQuery(sqls);
            while (sets.next()) {
                //namectg = sets.getString("nm_ctg");
                comboBox.addItem(new DivisiDv(sets.getInt("id"), sets.getString("nm_barang")));

            }
//            comboBox.addItem("Snowboarding");
           /*
             comboBox.setEditable(true);
             comboBox.getEditor().getEditorComponent().addKeyListener(new KeyListener() {

             @Override
             public void keyTyped(KeyEvent e) {
             String value = Character.toString(e.getKeyChar()).toUpperCase();
             listBarang(e, comboBox,columns,rows,DfltTblMode);
             //                      System.out.println("masuk sini1"+value);
             //                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }

             @Override
             public void keyPressed(KeyEvent e) {
             String value = Character.toString(e.getKeyChar()).toUpperCase();
                    
                    
             listBarang(e, comboBox,columns,rows,DfltTblMode);
             //                      System.out.println("masuk sini2"+value);
             //                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }

             @Override
             public void keyReleased(KeyEvent e) {
             String value = Character.toString(e.getKeyChar()).toUpperCase();
             listBarang(e, comboBox,columns,rows,DfltTblMode);
             //                      System.out.println("masuk sini3"+value);
             //                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
             });
             // select the completed part
             JTextComponent editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
             */
//            AutocompleteJComboBox
            column = jTabel.getColumnModel().getColumn(2);
            column.setCellEditor(new DefaultCellEditor(comboBox));
//            column.setCellEditor(new MyComboBoxEditor(comboBox));
//            column.setCellRenderer(new MyComboBoxRenderer(comboBox));
            column.setPreferredWidth(200);
            column = jTabel.getColumnModel().getColumn(3);
            column.setPreferredWidth(200);
            column = jTabel.getColumnModel().getColumn(4);
            column.setPreferredWidth(200);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e);
        }
    }

    private void klikTabel(JTable jTabel) {
        jDialog2.setLocationRelativeTo(null);
        jBtnKembali2.setVisible(true);
        jBtnOk2.setVisible(true);
        jBtnKembali1.setVisible(false);
        jBtnOk1.setVisible(false);
        jTabel.setRowSelectionAllowed(true);
        rows = jTabel.getSelectedRow();
        String kolom1 = jTabel.getValueAt(rows, 0).toString();
        String kolom2 = jTabel.getValueAt(rows, 1).toString();
        int getcol = jTabel.getSelectedColumn();
        String kolom3 = jTabel.getValueAt(rows, 2).toString();
        String kolom4 = jTabel.getValueAt(rows, 3).toString();
        String kolom5 = jTabel.getValueAt(rows, 4).toString();
        String kolom6 = jTabel.getValueAt(rows, 5).toString();
        String kolom7 = jTabel.getValueAt(rows, 6).toString();
        String kolom8 = jTabel.getValueAt(rows, 7).toString();
        
        jTextField4.setText(kolom4);
        jTextField1.setText(kolom5);
        jTextField2.setText(kolom6);
        jTextField3.setText(kolom7);
        jTextField5.setText(kolom8);
        
        
//      
//                column.setCellEditor(new DefaultCellEditor(comboBox));
//        System.out.println("tes1"+kolom1);jTable1.getColumnModel().getColumn(1)
        System.out.println("sout " + jTabel.getSelectedColumn());
        
        jTabel.getSelectedColumn();
        Integer rowss = rows3;
        
        jDialog2.setVisible(true);
       
        
//        System.out.println("sout a " +jTabel.getKeyListeners().toString());
    /*    if (getcol == 2 || getcol == 1 || getcol == 3) {
            System.out.println("tes1" + kolom2);
            jDialog1.setLocationRelativeTo(null);
            modeljDialogPRD(jTable2, rowss);
            jDialog1.setVisible(true);
        } */
//        jTxtFldNO_TRANS.setText(kolom2);
     //   jTxtFldKD_BARANG2.setText(kolom2);
//        jTxtFldNM_BARANG.setText(kolom3); 
        //   jTxtFldNM_BARANG2.setText(kolom3); 

    }

    private void kondisiHapus() {
        e = jTxtFldNO_TRANS.getText();

        try {
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            st.executeUpdate(
                    " delete from mst_barang where id ='" + e + "'");
            clearTEXT();
            tampilDataKeTabel();

            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Data gagal dihapus: " + ex);
        }
    }

    private void cari(JTable jTabel) {
        try {
            Object[] field = {"No", "Kode Barang", "Nama Barang", "Satuan Barang"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);
            String sql = "";
//            String sql = "Select * from mst_barang where kd_barang like '%" + jTextField1.getText() + "%'" +
//                         "or nm_barang like '%" + jTextField1.getText() + "%'";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("kd_barang");
                String kolom3 = set.getString("nm_barang");
                String kolom4 = set.getString("satuan_barang");
                String[] data = {kolom1, kolom2, kolom3, kolom4};
                DfltTblMode.addRow(data);

            }
            jTabel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel.getColumnModel().getColumn(1);
            column.setPreferredWidth(156);
            column = jTabel.getColumnModel().getColumn(2);
            column.setPreferredWidth(200);
            column = jTabel.getColumnModel().getColumn(3);
            column.setPreferredWidth(200);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jBtnOk = new javax.swing.JButton();
        jBtnKembali = new javax.swing.JButton();
        jDialog2 = new javax.swing.JDialog();
        jBtnOk1 = new javax.swing.JButton();
        jBtnKembali1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jBtnOk2 = new javax.swing.JButton();
        jBtnKembali2 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jlblKD_BARANG = new javax.swing.JLabel();
        jlblNM_BARANG = new javax.swing.JLabel();
        jlblxKD_BARANG = new javax.swing.JLabel();
        jlblxNM_BARANG = new javax.swing.JLabel();
        jTxtFldNO_TRANS = new javax.swing.JTextField();
        jBtnSave = new javax.swing.JButton();
        jBtnEdit = new javax.swing.JButton();
        jBtnDlt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBtnNew = new javax.swing.JButton();
        jBtnSave2 = new javax.swing.JButton();
        jCmbJBTN = new javax.swing.JComboBox();
        jlblJBTN = new javax.swing.JLabel();
        jlblxJBTN = new javax.swing.JLabel();
        jlblJBTN2 = new javax.swing.JLabel();
        jlblxJBTN1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMnKembali = new javax.swing.JMenu();

        jDialog1.setMinimumSize(new java.awt.Dimension(370, 600));
        jDialog1.setModal(true);
        jDialog1.setResizable(false);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setMinimumSize(new java.awt.Dimension(200, 64));
        jScrollPane3.setViewportView(jTable2);

        jBtnOk.setText("Ok");
        jBtnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkActionPerformed(evt);
            }
        });

        jBtnKembali.setText("Kembali");
        jBtnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOk)
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOk)
                    .addComponent(jBtnKembali))
                .addContainerGap())
        );

        jDialog2.setMinimumSize(new java.awt.Dimension(370, 600));
        jDialog2.setModal(true);
        jDialog2.setResizable(false);

        jBtnOk1.setText("Save");
        jBtnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOk1ActionPerformed(evt);
            }
        });

        jBtnKembali1.setText("Kembali");
        jBtnKembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembali1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Nama Barang");

        jLabel4.setText(":");

        jComboBox1.setEditable(true);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText(":");

        jLabel6.setText("Satuan");

        jLabel8.setText("Quantity");

        jLabel9.setText(":");

        jTextField1.setText("0");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel10.setText("Honor");

        jLabel11.setText(":");

        jTextField2.setText("0");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel12.setText("Harga");

        jTextField3.setText("0");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel15.setText("Jumlah");

        jTextField4.setEditable(false);

        jTextField5.setEditable(false);

        jBtnOk2.setText("Update");
        jBtnOk2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOk2ActionPerformed(evt);
            }
        });

        jBtnKembali2.setText("Hapus");
        jBtnKembali2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembali2ActionPerformed(evt);
            }
        });

        jLabel16.setText(":");

        jLabel17.setText(":");

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                        .addGap(0, 149, Short.MAX_VALUE)
                        .addComponent(jBtnKembali2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnKembali1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnOk1)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnOk2)
                        .addGap(8, 8, 8))
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialog2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialog2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialog2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialog2Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel16))
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOk1)
                    .addComponent(jBtnKembali1)
                    .addComponent(jBtnOk2)
                    .addComponent(jBtnKembali2))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form Pemasukan Barang");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlblKD_BARANG.setText("No Transaksi*");

        jlblNM_BARANG.setText("Date*");

        jlblxKD_BARANG.setText(":");

        jlblxNM_BARANG.setText(":");

        jTxtFldNO_TRANS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldNO_TRANSActionPerformed(evt);
            }
        });

        jBtnSave.setText("Simpan");
        jBtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaveActionPerformed(evt);
            }
        });

        jBtnEdit.setText("Ubah");
        jBtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditActionPerformed(evt);
            }
        });

        jBtnDlt.setText("Hapus");
        jBtnDlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDltActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "", null, null, null, null, null}
            },
            new String [] {
                "No", "Nama Barang", "Satuan", "Banyaknya", "Harga", "Honor", "Jumlah"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(30);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jBtnNew.setText("Data Baru");
        jBtnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNewActionPerformed(evt);
            }
        });

        jBtnSave2.setText("Simpan");
        jBtnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSave2ActionPerformed(evt);
            }
        });

        jCmbJBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmbJBTNActionPerformed(evt);
            }
        });

        jlblJBTN.setText("Divisi*");

        jlblxJBTN.setText(":");

        jlblJBTN2.setText("Keterangan*");

        jlblxJBTN1.setText(":");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton1.setText("Add Item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Total :");

        jLabel2.setText("0");

        jMenuBar1.setBackground(new java.awt.Color(236, 236, 236));
        jMenuBar1.setName(""); // NOI18N

        jMnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        jMnKembali.setToolTipText("Menu Utama");
        jMnKembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnKembaliMousePressed(evt);
            }
        });
        jMenuBar1.add(jMnKembali);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblKD_BARANG)
                                    .addComponent(jlblNM_BARANG)
                                    .addComponent(jlblJBTN)
                                    .addComponent(jlblJBTN2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlblxNM_BARANG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlblxKD_BARANG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlblxJBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlblxJBTN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtFldNO_TRANS, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCmbJBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtnNew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnSave2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnDlt))
                            .addComponent(jButton1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblxKD_BARANG)
                    .addComponent(jTxtFldNO_TRANS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblKD_BARANG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblxNM_BARANG)
                    .addComponent(jlblNM_BARANG)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblxJBTN)
                    .addComponent(jlblJBTN)
                    .addComponent(jCmbJBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblxJBTN1)
                        .addComponent(jlblJBTN2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnSave)
                        .addComponent(jBtnNew))
                    .addComponent(jBtnEdit, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnDlt)
                        .addComponent(jBtnSave2)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMnKembaliMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnKembaliMousePressed
        // TODO add your handling code here:
        new listTransaksiIn().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnKembaliMousePressed

    private void jBtnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNewActionPerformed
        // TODO add your handling code here:
        clearTEXT();
        enableField(true);
        enableBtn(false);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(false);
        jTxtFldNO_TRANS.requestFocus();
    }//GEN-LAST:event_jBtnNewActionPerformed

    private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaveActionPerformed
        // TODO add your handling code here:
        kondisiSave();
        enableField(true);
        enableBtn(true);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(true);
    }//GEN-LAST:event_jBtnSaveActionPerformed

    private void jBtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditActionPerformed
        // TODO add your handling code here:
        enableField(true);
        enableBtn(false);
        enviBtnSave(false);
        enviBtnSave2(true);
        enviBtnNew(false);
    }//GEN-LAST:event_jBtnEditActionPerformed

    private void jBtnDltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDltActionPerformed
        // TODO add your handling code here:
        kondisiHapus();
        enableField(true);
        enableBtn(false);
        enviBtnNew(false);
        enviBtnSave(true);
    }//GEN-LAST:event_jBtnDltActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        
        klikTabel(jTable1);
        enableField(true);
        enableBtn(true);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(true);
    }//GEN-LAST:event_jTable1MousePressed

    private void jBtnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSave2ActionPerformed
        // TODO add your handling code here:
        kondisiEdit();
        enableField(false);
        enableBtn(false);
        enviBtnSave(false);
        enviBtnSave2(false);
        enviBtnNew(true);
    }//GEN-LAST:event_jBtnSave2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new start().setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jCmbJBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbJBTNActionPerformed
        // TODO add your handling code here:
        //        listSatuan();
    }//GEN-LAST:event_jCmbJBTNActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        addItem(models);
        jBtnKembali2.setVisible(false);
        jBtnOk2.setVisible(false);
        jBtnKembali1.setVisible(true);
        jBtnOk1.setVisible(true);
        jBtnNew.setVisible(false);
        jBtnEdit.setVisible(false);
        jBtnDlt.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkActionPerformed
        // TODO add your handling code here:
        if (jTable2.getSelectedRow() != -1) {
            klikTabeljDialog(jTable2);
            jDialog1.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
        }
    }//GEN-LAST:event_jBtnOkActionPerformed

    private void jBtnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliActionPerformed
        // TODO add your handling code here:
        jDialog1.dispose();
    }//GEN-LAST:event_jBtnKembaliActionPerformed

    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
        // TODO add your handling code here:
//        System.out.println("tes date "+jXDatePicker1.getFormats());
        
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dt1.format(jXDatePicker1.getDate()));
    }//GEN-LAST:event_jXDatePicker1ActionPerformed

    private void jTxtFldNO_TRANSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldNO_TRANSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldNO_TRANSActionPerformed

    private void jBtnKembali2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembali2ActionPerformed
        // TODO add your handling code here:
        //        removeOneRow(models);
        models.removeRow(rows);
        jBtnNew.setVisible(false);
        jBtnEdit.setVisible(false);
        jBtnDlt.setVisible(false);
        jDialog2.dispose();
    }//GEN-LAST:event_jBtnKembali2ActionPerformed

    private void jBtnOk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOk2ActionPerformed
        // TODO add your handling code here:
        klikUpdateItem(models);

        jBtnNew.setVisible(false);
        jBtnEdit.setVisible(false);
        jBtnDlt.setVisible(false);
        jDialog2.dispose();
    }//GEN-LAST:event_jBtnOk2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        keyEventAll(evt);
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jBtnKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembali1ActionPerformed
        // TODO add your handling code here:
        jBtnNew.setVisible(false);
        jBtnEdit.setVisible(false);
        jBtnDlt.setVisible(false);
        jDialog2.dispose();
    }//GEN-LAST:event_jBtnKembali1ActionPerformed

    private void jBtnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOk1ActionPerformed
        // TODO add your handling code here:
        klikButtonItem(models);
        jDialog2.dispose();
        jBtnNew.setVisible(false);
        jBtnEdit.setVisible(false);
        jBtnDlt.setVisible(false);

    }//GEN-LAST:event_jBtnOk1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        jComboBox1.setEnabled(true);
        jComboBox1.setEditable(true);
        listSatuan();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TransaksiIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TransaksiIn().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDlt;
    private javax.swing.JButton jBtnEdit;
    private javax.swing.JButton jBtnKembali;
    private javax.swing.JButton jBtnKembali1;
    private javax.swing.JButton jBtnKembali2;
    private javax.swing.JButton jBtnNew;
    private javax.swing.JButton jBtnOk;
    private javax.swing.JButton jBtnOk1;
    private javax.swing.JButton jBtnOk2;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JButton jBtnSave2;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jCmbJBTN;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTxtFldNO_TRANS;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JLabel jlblJBTN;
    private javax.swing.JLabel jlblJBTN2;
    private javax.swing.JLabel jlblKD_BARANG;
    private javax.swing.JLabel jlblNM_BARANG;
    private javax.swing.JLabel jlblxJBTN;
    private javax.swing.JLabel jlblxJBTN1;
    private javax.swing.JLabel jlblxKD_BARANG;
    private javax.swing.JLabel jlblxNM_BARANG;
    // End of variables declaration//GEN-END:variables
}
