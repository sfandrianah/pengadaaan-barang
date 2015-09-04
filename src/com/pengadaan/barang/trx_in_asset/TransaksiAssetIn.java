package com.pengadaan.barang.trx_in_asset;

import com.pengadaan.barang.transaksi_in.*;
import com.pengadaan.barang.util.DivisiDv;
import com.pengadaan.barang.util.CategoryBarangDv;
import com.pengadaan.barang.util.TypeBarangDv;
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
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;

public class TransaksiAssetIn extends javax.swing.JFrame {

    private Integer row, rows, row2, rows3;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable();
    private DefaultTableModel DfltTblMode;
    private TableColumn column;
    private String e, r, i, k, a, satuan, kd_lokasi,kd_barang,category_barang, desc, date, divisi
            ,nm_barang,no_reg,jml_barang,merk_barang,ukuran_barang,bahan_barang,tahun_barang
            ,no_pabrik,no_rangka,no_mesin,no_pol,no_bpkb,cr_prl,price,honor,total,type;
    private int idcategory;
    DefaultTableModel models = new DefaultTableModel();

    public TransaksiAssetIn() {
        initComponents();
        aplikasiInventory.konekkeDatabase();
//        tampilDataKeTabel();
//        columnTable();
        enableBtn(false);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(false);
//        jTxtFldKD_BARANG2.setVisible(false);
//        jTxtFldNM_BARANG2.setVisible(false);
        listDivisi();
        listCategoryBarang();
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
    
    private void keyEventAll2(KeyEvent key){
        String test = "";
        
        test = String.valueOf(key.getKeyChar());
        jTxtFldHRG_ST.setEnabled(true);
        String jtf1 = jTxtFldHRG_ST.getText();
        
        String test2 = jtf1+test;
        
        Double hasil = 0D;
        System.out.println(jtf1);
//        System.out.println(jTextField2.getText());
        hasil = Double.parseDouble(test2) * Double.parseDouble(jTxtFldJML_BRG.getText());
        jTxtFldTOTAL.setEnabled(true);
//        jLabel16.sete
        jTxtFldTOTAL.setText(hasil.toString());
        
    }
    
    private void listCategoryBarang(){
       
        try {
             String sql2 = "Select * from mst_category_barang where type_barang_id=1";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set;
            set = st.executeQuery(sql2);
            while (set.next()) {
//                System.out.println("tes"+set.getInt("id"));
                jCmbJBTN1.addItem(new CategoryBarangDv(set.getInt("id"), set.getString("nm_ctg")));

            }
//            jCmbJBTN.setSelectedIndex(2);
            
           // jCmbJBTN.getSelectedItem();
        } catch (SQLException ex) {
            Logger.getLogger(kategori.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    
    }
    
    private void clearDialog(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jComboBox1.removeAllItems();
        jDialog2.update(null);
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
            Logger.getLogger(TransaksiAssetIn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   
    

    private void clearTEXT() {
       // jTxtFldNO_TRANS.setText("");
//        jTxtFldNM_BARANG.setText("");
//        jCmbJBTN2.removeAllItems();
    }

    private void enableBtn(boolean x) {
     
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
        
    }

    private void enableField(boolean x) {
        jTxtFldKD_LKS.setEnabled(x);
//        jTxtFldNM_BARANG.setEnabled(x);
    }

  

    private void kondisiSave() {
        kd_lokasi = jTxtFldKD_LKS.getText();
        kd_barang = jTxtFldKD_BRG.getText();
        nm_barang = jTxtFldNM_BRG.getText();
        no_reg = jTxtFldNO_REG.getText();
        jml_barang = jTxtFldJML_BRG.getText();
        merk_barang = jTxtFldMERK.getText();
        bahan_barang = jTxtFldBAHAN.getText();
        tahun_barang = jTxtFldTAHUN.getText();
        ukuran_barang = jTxtFldUKURAN.getText();
        no_pabrik = jTxtFldNO_PBRK.getText();
        no_rangka = jTxtFldNO_RK.getText();
        no_mesin = jTxtFldNO_MSN.getText();
        no_pol = jTxtFldNO_POL.getText();
        no_bpkb = jTxtFldNO_BPKB.getText();
        cr_prl = jTxtFldCR_PR.getText();
        price = jTxtFldHRG_ST.getText();
        honor = jTxtFldHNR.getText();
        total = jTxtFldTOTAL.getText();
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dt1.format(jXDatePicker1.getDate()));
        date = dt1.format(jXDatePicker1.getDate());
        
        DivisiDv categoryDv = (DivisiDv) jCmbJBTN.getSelectedItem();
        divisi = String.valueOf(categoryDv.getId());
        
        CategoryBarangDv categoryBarangDv = (CategoryBarangDv) jCmbJBTN1.getSelectedItem();
        category_barang = String.valueOf(categoryBarangDv.getId());
        
        desc = jTextArea1.getText();
        
      // r = jTxtFldKD_BARANG.getText();
//       i = jTxtFldNM_BARANG.getText();

        
//       satuan = jCmbJBTN2.getSelectedItem().toString();
        Date dates = new Date();
         String strdate = dt1.format(dates);
        try {
            
            if (nm_barang.equals("") || jml_barang.equals("")) {
                JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
                clearTEXT();
            } else {
                
                
                Statement st = aplikasiInventory.config.getConnection().createStatement();
                st.executeUpdate(
                        "insert into trx_asset_item "
                        + "(trx_asset_item_date, trx_asset_item_code_lokasi, trx_asset_item_code_barang, "
                        + "divisi_id, category_barang_id, trx_asset_item_barang, trx_asset_item_no_reg, trx_asset_item_jumlah"
                        + ", trx_asset_item_merk, trx_asset_item_ukuran, trx_asset_item_bahan, trx_asset_item_tahun, trx_asset_item_no_pabrik, trx_asset_item_no_rangka"
                        + ",trx_asset_item_no_mesin,trx_asset_item_no_polisi, trx_asset_item_no_bpkb,trx_asset_item_cr_prl, trx_asset_item_price, trx_asset_item_honor"
                        + ", trx_asset_item_price_jumlah, trx_asset_item_ket,trx_asset_item_type, created_on) "
                        + " values ('" + date + "','" + kd_lokasi + "','" + kd_barang + "','"+divisi+"'"
                        + ",'" + category_barang + "','" + nm_barang + "','" + no_reg + "','" + jml_barang + "'"
                        + ",'" + merk_barang + "','" + ukuran_barang + "','" + bahan_barang + "','" + tahun_barang + "',"
                        + "'" + no_pabrik + "','" + no_rangka + "','" + no_mesin + "','" + no_pol + "',"
                        + "'" + no_bpkb + "','" + cr_prl + "','" + price + "','" + honor + "','" + total + "'"
                        + ",'" + desc + "',1,'" + strdate + "')");

                JOptionPane.showMessageDialog(this, "Data berhasil disimpan");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Data gagal disimpan! : " + ex);
        }
    }

    private void kondisiEdit() {
        r = jTxtFldKD_LKS.getText();
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

//                tampilDataKeTabel();
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
            Logger.getLogger(TransaksiAssetIn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 
    
    private void kondisiHapus() {
        e = jTxtFldKD_LKS.getText();

        try {
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            st.executeUpdate(
                    " delete from mst_barang where id ='" + e + "'");
            clearTEXT();
           

            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Data gagal dihapus: " + ex);
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
        jTxtFldKD_LKS = new javax.swing.JTextField();
        jBtnSave = new javax.swing.JButton();
        jBtnNew = new javax.swing.JButton();
        jCmbJBTN = new javax.swing.JComboBox();
        jlblJBTN = new javax.swing.JLabel();
        jlblxJBTN = new javax.swing.JLabel();
        jlblJBTN2 = new javax.swing.JLabel();
        jlblxJBTN1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jCmbJBTN1 = new javax.swing.JComboBox();
        jlblxJBTN2 = new javax.swing.JLabel();
        jlblJBTN1 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jlblNM_BARANG1 = new javax.swing.JLabel();
        jlblxNM_BARANG1 = new javax.swing.JLabel();
        jTxtFldKD_BRG = new javax.swing.JTextField();
        jlblNM_BARANG2 = new javax.swing.JLabel();
        jlblxJBTN3 = new javax.swing.JLabel();
        jTxtFldNO_REG = new javax.swing.JTextField();
        jlblNM_BARANG3 = new javax.swing.JLabel();
        jlblxJBTN4 = new javax.swing.JLabel();
        jTxtFldUKURAN = new javax.swing.JTextField();
        jlblNM_BARANG4 = new javax.swing.JLabel();
        jlblxJBTN5 = new javax.swing.JLabel();
        jTxtFldJML_BRG = new javax.swing.JTextField();
        jlblNM_BARANG5 = new javax.swing.JLabel();
        jlblxJBTN7 = new javax.swing.JLabel();
        jTxtFldMERK = new javax.swing.JTextField();
        jlblNM_BARANG6 = new javax.swing.JLabel();
        jlblxJBTN8 = new javax.swing.JLabel();
        jTxtFldTAHUN = new javax.swing.JTextField();
        jlblNM_BARANG7 = new javax.swing.JLabel();
        jlblxJBTN9 = new javax.swing.JLabel();
        jTxtFldBAHAN = new javax.swing.JTextField();
        jlblxNM_BARANG2 = new javax.swing.JLabel();
        jTxtFldNO_PBRK = new javax.swing.JTextField();
        jlblNM_BARANG10 = new javax.swing.JLabel();
        jlblNM_BARANG11 = new javax.swing.JLabel();
        jlblxNM_BARANG3 = new javax.swing.JLabel();
        jTxtFldNO_RK = new javax.swing.JTextField();
        jlblNM_BARANG12 = new javax.swing.JLabel();
        jlblxNM_BARANG4 = new javax.swing.JLabel();
        jTxtFldNO_MSN = new javax.swing.JTextField();
        jlblNM_BARANG13 = new javax.swing.JLabel();
        jlblxNM_BARANG5 = new javax.swing.JLabel();
        jTxtFldNO_POL = new javax.swing.JTextField();
        jlblNM_BARANG14 = new javax.swing.JLabel();
        jlblxNM_BARANG6 = new javax.swing.JLabel();
        jTxtFldNO_BPKB = new javax.swing.JTextField();
        jlblNM_BARANG15 = new javax.swing.JLabel();
        jlblxNM_BARANG7 = new javax.swing.JLabel();
        jTxtFldCR_PR = new javax.swing.JTextField();
        jlblNM_BARANG16 = new javax.swing.JLabel();
        jlblxNM_BARANG8 = new javax.swing.JLabel();
        jTxtFldHNR = new javax.swing.JTextField();
        jlblNM_BARANG17 = new javax.swing.JLabel();
        jlblxNM_BARANG9 = new javax.swing.JLabel();
        jTxtFldHRG_ST = new javax.swing.JTextField();
        jlblNM_BARANG18 = new javax.swing.JLabel();
        jlblxNM_BARANG10 = new javax.swing.JLabel();
        jTxtFldTOTAL = new javax.swing.JTextField();
        jTxtFldNM_BRG = new javax.swing.JTextField();
        jlblxNM_BARANG11 = new javax.swing.JLabel();
        jlblNM_BARANG8 = new javax.swing.JLabel();
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

        jlblKD_BARANG.setText("Date*");

        jlblNM_BARANG.setText("Kode Lokasi*");

        jlblxKD_BARANG.setText(":");

        jlblxNM_BARANG.setText(":");

        jTxtFldKD_LKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldKD_LKSActionPerformed(evt);
            }
        });

        jBtnSave.setText("Simpan");
        jBtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaveActionPerformed(evt);
            }
        });

        jBtnNew.setText("Data Baru");
        jBtnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNewActionPerformed(evt);
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

        jCmbJBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmbJBTN1ActionPerformed(evt);
            }
        });

        jlblxJBTN2.setText(":");

        jlblJBTN1.setText("Category Barang*");

        jlblNM_BARANG1.setText("Kode Barang*");

        jlblxNM_BARANG1.setText(":");

        jTxtFldKD_BRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldKD_BRGActionPerformed(evt);
            }
        });

        jlblNM_BARANG2.setText("No. Registrasi");

        jlblxJBTN3.setText(":");

        jTxtFldNO_REG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldNO_REGActionPerformed(evt);
            }
        });

        jlblNM_BARANG3.setText("Jumlah Barang");

        jlblxJBTN4.setText(":");

        jTxtFldUKURAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldUKURANActionPerformed(evt);
            }
        });

        jlblNM_BARANG4.setText("Merk / Type");

        jlblxJBTN5.setText(":");

        jTxtFldJML_BRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldJML_BRGActionPerformed(evt);
            }
        });

        jlblNM_BARANG5.setText("Ukuran / CC");

        jlblxJBTN7.setText(":");

        jTxtFldMERK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldMERKActionPerformed(evt);
            }
        });

        jlblNM_BARANG6.setText("Bahan");

        jlblxJBTN8.setText(":");

        jTxtFldTAHUN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldTAHUNActionPerformed(evt);
            }
        });

        jlblNM_BARANG7.setText("Tahun");

        jlblxJBTN9.setText(":");

        jTxtFldBAHAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldBAHANActionPerformed(evt);
            }
        });

        jlblxNM_BARANG2.setText(":");

        jTxtFldNO_PBRK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldNO_PBRKActionPerformed(evt);
            }
        });

        jlblNM_BARANG10.setText("No. Pabrik");

        jlblNM_BARANG11.setText("No. Rangka");

        jlblxNM_BARANG3.setText(":");

        jTxtFldNO_RK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldNO_RKActionPerformed(evt);
            }
        });

        jlblNM_BARANG12.setText("No. Mesin");

        jlblxNM_BARANG4.setText(":");

        jTxtFldNO_MSN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldNO_MSNActionPerformed(evt);
            }
        });

        jlblNM_BARANG13.setText("No. Polisi");

        jlblxNM_BARANG5.setText(":");

        jTxtFldNO_POL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldNO_POLActionPerformed(evt);
            }
        });

        jlblNM_BARANG14.setText("No. BPKB");

        jlblxNM_BARANG6.setText(":");

        jTxtFldNO_BPKB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldNO_BPKBActionPerformed(evt);
            }
        });

        jlblNM_BARANG15.setText("Cara Perolehan");

        jlblxNM_BARANG7.setText(":");

        jTxtFldCR_PR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldCR_PRActionPerformed(evt);
            }
        });

        jlblNM_BARANG16.setText("Harga Satuan");

        jlblxNM_BARANG8.setText(":");

        jTxtFldHNR.setText("0");
        jTxtFldHNR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldHNRActionPerformed(evt);
            }
        });

        jlblNM_BARANG17.setText("Honor");

        jlblxNM_BARANG9.setText(":");

        jTxtFldHRG_ST.setText("0");
        jTxtFldHRG_ST.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTxtFldHRG_STKeyTyped(evt);
            }
        });

        jlblNM_BARANG18.setText("Total");

        jlblxNM_BARANG10.setText(":");

        jTxtFldTOTAL.setText("0");
        jTxtFldTOTAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldTOTALActionPerformed(evt);
            }
        });

        jTxtFldNM_BRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldNM_BRGActionPerformed(evt);
            }
        });

        jlblxNM_BARANG11.setText(":");

        jlblNM_BARANG8.setText("Nama Barang");

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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblNM_BARANG)
                                    .addComponent(jlblJBTN)
                                    .addComponent(jlblNM_BARANG1)
                                    .addComponent(jlblJBTN1)
                                    .addComponent(jlblNM_BARANG2)
                                    .addComponent(jlblNM_BARANG3)
                                    .addComponent(jlblNM_BARANG4)
                                    .addComponent(jlblNM_BARANG5)
                                    .addComponent(jlblNM_BARANG6)
                                    .addComponent(jlblNM_BARANG8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlblxJBTN5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldMERK, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlblxJBTN4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldJML_BRG, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlblxJBTN3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldNO_REG, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlblxJBTN7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldUKURAN, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jlblxJBTN9, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlblxJBTN8, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTxtFldTAHUN, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTxtFldBAHAN, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlblxJBTN1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jlblxJBTN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jlblxNM_BARANG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jlblxJBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jlblxKD_BARANG))
                                            .addComponent(jlblxNM_BARANG1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlblxNM_BARANG11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTxtFldKD_BRG, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTxtFldKD_LKS, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCmbJBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCmbJBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTxtFldNM_BRG, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jlblNM_BARANG7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlblNM_BARANG11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblNM_BARANG10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblNM_BARANG12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblNM_BARANG13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblNM_BARANG14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblNM_BARANG15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblNM_BARANG16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblNM_BARANG17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblNM_BARANG18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblxNM_BARANG2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldNO_PBRK, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblxNM_BARANG3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldNO_RK, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblxNM_BARANG4, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldNO_MSN, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblxNM_BARANG5, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldNO_POL, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblxNM_BARANG6, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldNO_BPKB, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblxNM_BARANG7, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldCR_PR, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlblxNM_BARANG8, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldHRG_ST, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlblxNM_BARANG10, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblxNM_BARANG9, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtFldHNR, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtFldTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(67, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtnNew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnSave))
                            .addComponent(jlblKD_BARANG)
                            .addComponent(jlblJBTN2))
                        .addGap(0, 557, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblxNM_BARANG2)
                        .addComponent(jTxtFldNO_PBRK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlblNM_BARANG10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblKD_BARANG)
                        .addComponent(jlblxKD_BARANG)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG11)
                            .addComponent(jlblxNM_BARANG3)
                            .addComponent(jTxtFldNO_RK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG12)
                            .addComponent(jlblxNM_BARANG4)
                            .addComponent(jTxtFldNO_MSN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG13)
                            .addComponent(jlblxNM_BARANG5)
                            .addComponent(jTxtFldNO_POL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG14)
                            .addComponent(jlblxNM_BARANG6)
                            .addComponent(jTxtFldNO_BPKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG15)
                            .addComponent(jlblxNM_BARANG7)
                            .addComponent(jTxtFldCR_PR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG16)
                            .addComponent(jlblxNM_BARANG8)
                            .addComponent(jTxtFldHRG_ST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG17)
                            .addComponent(jlblxNM_BARANG9)
                            .addComponent(jTxtFldHNR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblNM_BARANG18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblxNM_BARANG10)
                                .addComponent(jTxtFldTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblxNM_BARANG)
                            .addComponent(jlblNM_BARANG)
                            .addComponent(jTxtFldKD_LKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG1)
                            .addComponent(jlblxNM_BARANG1)
                            .addComponent(jTxtFldKD_BRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtFldNM_BRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblxNM_BARANG11)
                                .addComponent(jlblNM_BARANG8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblxJBTN)
                            .addComponent(jlblJBTN)
                            .addComponent(jCmbJBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblxJBTN2)
                                .addComponent(jCmbJBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlblJBTN1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblNM_BARANG2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblxJBTN3)
                                .addComponent(jTxtFldNO_REG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG3)
                            .addComponent(jlblxJBTN4)
                            .addComponent(jTxtFldJML_BRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG4)
                            .addComponent(jlblxJBTN5)
                            .addComponent(jTxtFldMERK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblNM_BARANG5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblxJBTN7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTxtFldUKURAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNM_BARANG6)
                            .addComponent(jlblxJBTN8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldBAHAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblNM_BARANG7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblxJBTN9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTxtFldTAHUN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblJBTN2)
                        .addComponent(jlblxJBTN1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSave)
                    .addComponent(jBtnNew))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMnKembaliMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnKembaliMousePressed
        // TODO add your handling code here:
        new start().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnKembaliMousePressed

    private void jBtnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNewActionPerformed
//        // TODO add your handling code here:
//        clearTEXT();
//        enableField(true);
//        enableBtn(false);
//        enviBtnSave(true);
//        enviBtnSave2(false);
//        enviBtnNew(false);
//        jTxtFldNO_TRANS.requestFocus();
        new TransaksiAssetIn().setVisible(true);
        dispose();
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new start().setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jCmbJBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbJBTNActionPerformed
        // TODO add your handling code here:
        //        listSatuan();
    }//GEN-LAST:event_jCmbJBTNActionPerformed

    private void jBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkActionPerformed
        // TODO add your handling code here:
        if (jTable2.getSelectedRow() != -1) {
//            klikTabeljDialog(jTable2);
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

    private void jTxtFldKD_LKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldKD_LKSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldKD_LKSActionPerformed

    private void jBtnKembali2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembali2ActionPerformed
        // TODO add your handling code here:
        //        removeOneRow(models);
        models.removeRow(rows);
        jBtnNew.setVisible(false);
//        jDialog2.dispose();
    }//GEN-LAST:event_jBtnKembali2ActionPerformed

    private void jBtnOk2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOk2ActionPerformed
        // TODO add your handling code here:


        jBtnNew.setVisible(false);
//        jDialog2.dispose();
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
        jDialog2.setVisible(false);
//        jDialog2.dispose();
    }//GEN-LAST:event_jBtnKembali1ActionPerformed

    private void jBtnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOk1ActionPerformed
        // TODO add your handling code here:

        
        jBtnNew.setVisible(false);
        jDialog2.setVisible(false);
//        jDialog2.dispose();

    }//GEN-LAST:event_jBtnOk1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        jComboBox1.setEnabled(false);
        jComboBox1.setEnabled(true);
        jTextField3.setText("0");
        jComboBox1.setEditable(true);
//        listSatuan();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jCmbJBTN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbJBTN1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCmbJBTN1ActionPerformed

    private void jTxtFldKD_BRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldKD_BRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldKD_BRGActionPerformed

    private void jTxtFldNO_REGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldNO_REGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldNO_REGActionPerformed

    private void jTxtFldUKURANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldUKURANActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldUKURANActionPerformed

    private void jTxtFldJML_BRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldJML_BRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldJML_BRGActionPerformed

    private void jTxtFldMERKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldMERKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldMERKActionPerformed

    private void jTxtFldTAHUNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldTAHUNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldTAHUNActionPerformed

    private void jTxtFldBAHANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldBAHANActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldBAHANActionPerformed

    private void jTxtFldNO_PBRKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldNO_PBRKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldNO_PBRKActionPerformed

    private void jTxtFldNO_RKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldNO_RKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldNO_RKActionPerformed

    private void jTxtFldNO_MSNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldNO_MSNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldNO_MSNActionPerformed

    private void jTxtFldNO_POLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldNO_POLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldNO_POLActionPerformed

    private void jTxtFldNO_BPKBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldNO_BPKBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldNO_BPKBActionPerformed

    private void jTxtFldCR_PRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldCR_PRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldCR_PRActionPerformed

    private void jTxtFldHNRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldHNRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldHNRActionPerformed

    private void jTxtFldTOTALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldTOTALActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldTOTALActionPerformed

    private void jTxtFldNM_BRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldNM_BRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldNM_BRGActionPerformed

    private void jTxtFldHRG_STKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFldHRG_STKeyTyped
        // TODO add your handling code here:
        keyEventAll2(evt);
    }//GEN-LAST:event_jTxtFldHRG_STKeyTyped

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
            java.util.logging.Logger.getLogger(TransaksiAssetIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransaksiAssetIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransaksiAssetIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransaksiAssetIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TransaksiAssetIn().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnKembali;
    private javax.swing.JButton jBtnKembali1;
    private javax.swing.JButton jBtnKembali2;
    private javax.swing.JButton jBtnNew;
    private javax.swing.JButton jBtnOk;
    private javax.swing.JButton jBtnOk1;
    private javax.swing.JButton jBtnOk2;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JComboBox jCmbJBTN;
    private javax.swing.JComboBox jCmbJBTN1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTxtFldBAHAN;
    private javax.swing.JTextField jTxtFldCR_PR;
    private javax.swing.JTextField jTxtFldHNR;
    private javax.swing.JTextField jTxtFldHRG_ST;
    private javax.swing.JTextField jTxtFldJML_BRG;
    private javax.swing.JTextField jTxtFldKD_BRG;
    private javax.swing.JTextField jTxtFldKD_LKS;
    private javax.swing.JTextField jTxtFldMERK;
    private javax.swing.JTextField jTxtFldNM_BRG;
    private javax.swing.JTextField jTxtFldNO_BPKB;
    private javax.swing.JTextField jTxtFldNO_MSN;
    private javax.swing.JTextField jTxtFldNO_PBRK;
    private javax.swing.JTextField jTxtFldNO_POL;
    private javax.swing.JTextField jTxtFldNO_REG;
    private javax.swing.JTextField jTxtFldNO_RK;
    private javax.swing.JTextField jTxtFldTAHUN;
    private javax.swing.JTextField jTxtFldTOTAL;
    private javax.swing.JTextField jTxtFldUKURAN;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JLabel jlblJBTN;
    private javax.swing.JLabel jlblJBTN1;
    private javax.swing.JLabel jlblJBTN2;
    private javax.swing.JLabel jlblKD_BARANG;
    private javax.swing.JLabel jlblNM_BARANG;
    private javax.swing.JLabel jlblNM_BARANG1;
    private javax.swing.JLabel jlblNM_BARANG10;
    private javax.swing.JLabel jlblNM_BARANG11;
    private javax.swing.JLabel jlblNM_BARANG12;
    private javax.swing.JLabel jlblNM_BARANG13;
    private javax.swing.JLabel jlblNM_BARANG14;
    private javax.swing.JLabel jlblNM_BARANG15;
    private javax.swing.JLabel jlblNM_BARANG16;
    private javax.swing.JLabel jlblNM_BARANG17;
    private javax.swing.JLabel jlblNM_BARANG18;
    private javax.swing.JLabel jlblNM_BARANG2;
    private javax.swing.JLabel jlblNM_BARANG3;
    private javax.swing.JLabel jlblNM_BARANG4;
    private javax.swing.JLabel jlblNM_BARANG5;
    private javax.swing.JLabel jlblNM_BARANG6;
    private javax.swing.JLabel jlblNM_BARANG7;
    private javax.swing.JLabel jlblNM_BARANG8;
    private javax.swing.JLabel jlblxJBTN;
    private javax.swing.JLabel jlblxJBTN1;
    private javax.swing.JLabel jlblxJBTN2;
    private javax.swing.JLabel jlblxJBTN3;
    private javax.swing.JLabel jlblxJBTN4;
    private javax.swing.JLabel jlblxJBTN5;
    private javax.swing.JLabel jlblxJBTN7;
    private javax.swing.JLabel jlblxJBTN8;
    private javax.swing.JLabel jlblxJBTN9;
    private javax.swing.JLabel jlblxKD_BARANG;
    private javax.swing.JLabel jlblxNM_BARANG;
    private javax.swing.JLabel jlblxNM_BARANG1;
    private javax.swing.JLabel jlblxNM_BARANG10;
    private javax.swing.JLabel jlblxNM_BARANG11;
    private javax.swing.JLabel jlblxNM_BARANG2;
    private javax.swing.JLabel jlblxNM_BARANG3;
    private javax.swing.JLabel jlblxNM_BARANG4;
    private javax.swing.JLabel jlblxNM_BARANG5;
    private javax.swing.JLabel jlblxNM_BARANG6;
    private javax.swing.JLabel jlblxNM_BARANG7;
    private javax.swing.JLabel jlblxNM_BARANG8;
    private javax.swing.JLabel jlblxNM_BARANG9;
    // End of variables declaration//GEN-END:variables
}
