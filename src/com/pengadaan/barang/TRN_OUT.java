package com.pengadaan.barang;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.Date;
import java.awt.event.KeyEvent;
 
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


public class TRN_OUT extends javax.swing.JFrame {

    private Integer row;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable(), jTabel2 = new JTable(), jTabel3 = new JTable(), jTabel4 = new JTable(), jTabel5 = new JTable(), jTabel6 = new JTable(), jTabel7 = new JTable();
    private DefaultTableModel DfltTblMode, DfltTblMode2;
    private TableColumn column;
    private String e,r,i,k,a,s,p,t,x,y,z,b,c,d,f,g,h,j,l,m,n,o,hasil,fieldTemp;
    private int nil1,nil2,nil3,varVAL,nextVAL;
    private int vnNO;
    
    private JasperReport jasperReport;
    private JasperPrint jasperPrint;
    private Map<String, Object> param = new HashMap<String, Object>();
    private JasperDesign jasperDesign;
      
    
    public TRN_OUT() {
        initComponents();
        setLocationRelativeTo(null);    
        aplikasiInventory.konekkeDatabase(); 
        tampilDataKeTabel();
        enableBtn(false);
        enviBtnSave(true);
        enviBtnNew(false);
        jTxtFldKD_OTLT.setEnabled(false);
        jTxtFldNM_OTLT.setEnabled(false);
        jTxtFldKD_PRD.setEnabled(false);
        jTxtFldNM_PRD.setEnabled(false);
        jTxtFldKD_KRYWN.setEnabled(false);
        jTxtFldNM_KRYWN.setEnabled(false);
        jTxtFldKD_PRD2.setVisible(false);
        jTxtFldFilter.setVisible(false);
        jTxtFldSTS.setVisible(false);
        jTxtFldQTY_OUT2.setVisible(false);
        jTxtFldKD_OTLT2.setVisible(false);
        jTxtFldNIK.setVisible(false);
        jTxtFldKD_SBS.setEnabled(false);
        jTxtFldNM_SBS.setEnabled(false);
        jTxtFldKD_SBS2.setVisible(false);
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jBtnCari.setEnabled(false);
        jBtnCari1.setEnabled(false);  
        buatnotrn();
        date();
        vnNO = 0;
        Object[] field2 = {"No","No Transaksi","Tanggal Transaksi","Status Keluar Barang","Filter Produk Berdasarkan","Kode Produk","Nama Produk","Qty Awal","Qty Keluar","Total Qty","Harga Pokok","Harga Jual","Kode Otlet","Nama Otlet","Kode SBS","Nama SBS","NIK","Nama Karyawan"};
        DfltTblMode2 = new DefaultTableModel(null, field2);
    }
    
    private void date(){
        Date tgl = new Date();
        jlblTGL_TRN.setText("" + (String.format("%1$td %1$tb %1$tY",tgl)));
    }
    
    
    private void clearTEXT() {
        jTxtFldKD_PRD.setText("");
        jTxtFldNM_PRD.setText("");
        jlblNO_TRN1.setText("");
        jlblTTL_QTY1.setText("");
        jTxtFldQTY_OUT.setText("");
        jlblQTY_AWAL1.setText("");
        jlblHRG_PK1.setText("");
        jlblHRG_JL1.setText("");
        jTextField1.setText("");
        jCmbFilter.setSelectedItem("");
        jCmbSTS.setSelectedItem("");
        jTxtFldKD_OTLT.setText("");
        jTxtFldNM_OTLT.setText("");
        jTxtFldKD_SBS.setText("");
        jTxtFldNM_SBS.setText("");
        jTxtFldKD_KRYWN.setText("");
        jTxtFldNM_KRYWN.setText("");
        buatnotrn();
        date();
    }
    
    private void enableBtn(boolean x) {
        jBtnDlt1.setEnabled(x);
    }
    
    private void enviBtnSave(boolean x) {
        jBtnSave.setEnabled(x);
        jBtnSave.setVisible(x);
    }
    
    private void enviBtnNew(boolean x){
        jBtnNew.setEnabled(x);
        jBtnNew.setVisible(x);
    }
        
    private void enableField(boolean x) {
        jTxtFldQTY_OUT.setEnabled(x);
        jCmbFilter.setEnabled(x);
        jCmbSTS.setEnabled(x);
        jBtnPRD.setEnabled(x);
        jBtnOTLT.setEnabled(x);
        jBtnKRYWN.setEnabled(x);
        jBtnSBS.setEnabled(x);
    }
    
    private void enablejcb(boolean x){
        jTextField1.setEnabled(x);
        jTextField2.setEnabled(!x);
        jTextField3.setEnabled(!x);
        jcb1.setEnabled(x);
        jcb2.setEnabled(!x);
        jBtnCari.setEnabled(x);
        jBtnCari1.setEnabled(!x);  
    }
    
    private void tampilDataKeTabel() {
        jTabel = jTable1;
        tabelModel(jTabel);
    }
    
    private void tampilDataKeTabelambil() {
        jTabel7 = jTable1;
        
        if (!jCmbSTS.getSelectedItem().equals("") && !jCmbFilter.getSelectedItem().equals("") && !jTxtFldKD_PRD.getText().equals("") && !jTxtFldQTY_OUT.getText().equals("")) 
        {
           vnNO = vnNO + 1;
           ambilData(vnNO, jTabel7);
        }
        else {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
        }
    }
    
    private void tampilDataKeTabel2() {
        jTabel3 = jTable2;
        modeljDialogPRD(jTabel3);
    }
    
    private void tampilDataKeTabelcari() {
        jTabel2 = jTable1;
        cari(jTabel2);
    }
    
    private void tampilDataKeTabelOTLT() {
        jTabel4 = jTable3;
        modeljDialogOTLT(jTabel4);
    }
    
    private void tampilDataKeTabelKRYWN() {
        jTabel5 = jTable4;
        modeljDialogKRYWN(jTabel5);
    }
     
    private void tampilDataKeTabelSBS() {
        jTabel6 = jTblSBS;
        modeljDialogSBS(jTabel6);
    }
    
    private void cari(JTable jTabel2){
    try {
            Object[] field = {"No","No Transaksi","Tanggal Transaksi","Status Keluar Barang","Filter Produk Berdasarkan","Kode Produk","Nama Produk","Qty Awal","Qty Keluar","Total Qty","Harga Pokok","Harga Jual","Kode Otlet","Nama Otlet","Kode SBS","Nama SBS","NIK","Nama Karyawan"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel2.setModel(DfltTblMode);
            
            if (jcb1.isSelected()){
            String sql = "Select * from transaksi where (NO_TRN like '%" + jTextField1.getText() + "%'" +
                         "or TGL_TRN like '%" + jTextField1.getText() + "%') "+
                         "and QTY_OUT != ''";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("NO_TRN");
                String kolom3 = set.getString("TGL_TRN");
                String kolom4 = set.getString("STS_BRG");
                String kolom5 = set.getString("FILTER");
                String kolom6 = set.getString("KD_PRD");
                String kolom7 = set.getString("NM_PRD");
                String kolom8 = set.getString("QTY_AWAL");
                String kolom9 = set.getString("QTY_OUT");
                String kolom10 = set.getString("QTY_AKHIR");
                String kolom11 = set.getString("HRG_PK");
                String kolom12 = set.getString("HRG_JL");
                String kolom13 = set.getString("KD_OTLT");
                String kolom14 = set.getString("NM_OTLT");
                String kolom15 = set.getString("KD_SBS");
                String kolom16 = set.getString("NM_SBS");
                String kolom17 = set.getString("NIK");
                String kolom18 = set.getString("NM_KRYWN");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10, kolom11, kolom12, kolom13, kolom14, kolom15, kolom16, kolom17, kolom18};
                DfltTblMode.addRow(data);
               }
            }
            else{
                String sql = "Select * from transaksi where (TGL_TRN between '"+ jTextField2.getText() +"' "+
                             "and '"+ jTextField3.getText() +"') "+
                             "and QTY_OUT != ''";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("NO_TRN");
                String kolom3 = set.getString("TGL_TRN");
                String kolom4 = set.getString("STS_BRG");
                String kolom5 = set.getString("FILTER");
                String kolom6 = set.getString("KD_PRD");
                String kolom7 = set.getString("NM_PRD");
                String kolom8 = set.getString("QTY_AWAL");
                String kolom9 = set.getString("QTY_OUT");
                String kolom10 = set.getString("QTY_AKHIR");
                String kolom11 = set.getString("HRG_PK");
                String kolom12 = set.getString("HRG_JL");
                String kolom13 = set.getString("KD_OTLT");
                String kolom14 = set.getString("NM_OTLT");
                String kolom15 = set.getString("KD_SBS");
                String kolom16 = set.getString("NM_SBS");
                String kolom17 = set.getString("NIK");
                String kolom18 = set.getString("NM_KRYWN");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10, kolom11, kolom12, kolom13, kolom14, kolom15, kolom16, kolom17, kolom18};
                DfltTblMode.addRow(data);
              }
            }
            
            jTabel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(4);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(5);
            column.setPreferredWidth(80);
            column = jTabel.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(7);
            column.setPreferredWidth(70);
            column = jTabel.getColumnModel().getColumn(8);
            column.setPreferredWidth(70);
            column = jTabel.getColumnModel().getColumn(9);
            column.setPreferredWidth(70);
            column = jTabel.getColumnModel().getColumn(10);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(11);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(12);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(13);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(14);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(15);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(16);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(17);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void modeljDialogPRD(JTable jTabel3) {
        try {
           Object[] field = {"No","Kode Produk","Nama Produk","Kode Kategori","Nama Kategori","Kode SBS","Nama SBS ","Kode Suplier","Nama Suplier","Kode Otlet","Nama Otlet","Qty Awal","Harga Pokok","Harga Jual"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel3.setModel(DfltTblMode);
            
            String sql = "Select * from produk where " + fieldTemp + "!= ''";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_PRD");
                String kolom3 = set.getString("NM_PRD");
                String kolom4 = set.getString("KD_CTGRY");
                String kolom5 = set.getString("NM_CTGRY");
                String kolom6 = set.getString("KD_SBS");
                String kolom7 = set.getString("NM_SBS");
                String kolom8 = set.getString("KD_SPLR");
                String kolom9 = set.getString("NM_SPLR");
                String kolom10 = set.getString("KD_OTLT");
                String kolom11 = set.getString("NM_OTLT");
                String kolom12 = set.getString("QTY_AWAL");
                String kolom13 = set.getString("HRG_PK");
                String kolom14 = set.getString("HRG_JL");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10, kolom11, kolom12, kolom13, kolom14};
                DfltTblMode.addRow(data);
            }
            
            jTabel3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel3.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel3.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(4);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(5);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(7);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(8);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(9);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(10);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(11);
            column.setPreferredWidth(70);
            column = jTabel3.getColumnModel().getColumn(12);
            column.setPreferredWidth(100);
            column = jTabel3.getColumnModel().getColumn(13);
            column.setPreferredWidth(100);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialog(JTable jTabel) {   
        jTabel3.setRowSelectionAllowed(true);
        row = jTabel3.getSelectedRow();
        String kolom1 = jTabel3.getValueAt(row,0).toString();
        String kolom2 = jTabel3.getValueAt(row,1).toString();
        String kolom3 = jTabel3.getValueAt(row,2).toString();
        String kolom12 = jTabel3.getValueAt(row,11).toString();
        String kolom13 = jTabel3.getValueAt(row,12).toString();
        String kolom14 = jTabel3.getValueAt(row,13).toString();
        jTxtFldKD_PRD.setText(kolom2);
        jTxtFldNM_PRD.setText(kolom3);
        jlblQTY_AWAL1.setText(kolom12);
        jlblHRG_PK1.setText(kolom13);
        jlblHRG_JL1.setText(kolom14);
    }
    
    private void modeljDialogOTLT(JTable jTabel4) {
        try {
           Object[] field = {"No","Kode Outlet","Nama Outlet"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel4.setModel(DfltTblMode);
            
            String sql = "Select * from populasi";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_OTLT");
                String kolom3 = set.getString("NM_OTLT");
                String[] data = {kolom1, kolom2, kolom3};
                DfltTblMode.addRow(data);
            }
            
            jTabel4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel4.getColumnModel().getColumn(0);
            column.setPreferredWidth(50);
            column = jTabel4.getColumnModel().getColumn(1);
            column.setPreferredWidth(150);
            column = jTabel4.getColumnModel().getColumn(2);
            column.setPreferredWidth(250);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialogOTLT(JTable jTabel) {   
        jTabel4.setRowSelectionAllowed(true);
        row = jTabel4.getSelectedRow();
        String kolom1 = jTabel4.getValueAt(row,0).toString();
        String kolom2 = jTabel4.getValueAt(row,1).toString();
        String kolom3 = jTabel4.getValueAt(row,2).toString();
        jTxtFldKD_OTLT.setText(kolom2);
        jTxtFldNM_OTLT.setText(kolom3);
    }
    
    private void modeljDialogKRYWN(JTable jTabel5) {
        try {
           Object[] field = {"No","NIK","Nama Karyawan"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel5.setModel(DfltTblMode);
            
            String sql = "Select * from karyawan where JABATAN = 'Teknisi'";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("NIK");
                String kolom3 = set.getString("NM_KRYWN");
                String[] data = {kolom1, kolom2, kolom3};
                DfltTblMode.addRow(data);
            }
            
            jTabel5.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel5.getColumnModel().getColumn(0);
            column.setPreferredWidth(50);
            column = jTabel5.getColumnModel().getColumn(1);
            column.setPreferredWidth(150);
            column = jTabel5.getColumnModel().getColumn(2);
            column.setPreferredWidth(250);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialogKRYWN(JTable jTabel) {   
        jTabel5.setRowSelectionAllowed(true);
        row = jTabel5.getSelectedRow();
        String kolom1 = jTabel5.getValueAt(row,0).toString();
        String kolom2 = jTabel5.getValueAt(row,1).toString();
        String kolom3 = jTabel5.getValueAt(row,2).toString();
        jTxtFldKD_KRYWN.setText(kolom2);
        jTxtFldNM_KRYWN.setText(kolom3);
    }
    
    private void modeljDialogSBS(JTable jTabel6) {
        try {
            Object[] field = {"No","Kode SBS","Nama SBS"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel6.setModel(DfltTblMode);
            
            String sql = "Select * from sbs";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_SBS");
                String kolom3 = set.getString("NM_SBS");
                String[] data = {kolom1, kolom2, kolom3};
                DfltTblMode.addRow(data);
            }
            
            jTabel5.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel6.getColumnModel().getColumn(0);
            column.setPreferredWidth(50);
            column = jTabel6.getColumnModel().getColumn(1);
            column.setPreferredWidth(150);
            column = jTabel6.getColumnModel().getColumn(2);
            column.setPreferredWidth(250);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialogSBS(JTable jTabel) {
        jTabel6.setRowSelectionAllowed(true);
        row = jTabel6.getSelectedRow();
        String kolom1 = jTabel6.getValueAt(row,0).toString();
        String kolom2 = jTabel6.getValueAt(row,1).toString();
        String kolom3 = jTabel6.getValueAt(row,2).toString();
        jTxtFldKD_SBS.setText(kolom2);
        jTxtFldNM_SBS.setText(kolom3);
    }
    
    private void tabelModel(JTable jTabel) {
        try {
            Object[] field = {"No","No Transaksi","Tanggal Transaksi","Status Keluar Barang","Filter Produk Berdasarkan","Kode Produk","Nama Produk","Qty Awal","Qty Keluar","Total Qty","Harga Pokok","Harga Jual","Kode Otlet","Nama Otlet","Kode SBS","Nama SBS","NIK","Nama Karyawan"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);
                        
            jTabel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(4);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(5);
            column.setPreferredWidth(80);
            column = jTabel.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(7);
            column.setPreferredWidth(70);
            column = jTabel.getColumnModel().getColumn(8);
            column.setPreferredWidth(70);
            column = jTabel.getColumnModel().getColumn(9);
            column.setPreferredWidth(70);
            column = jTabel.getColumnModel().getColumn(10);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(11);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(12);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(13);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(14);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(15);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(16);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(17);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
          }
          catch (Exception e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabel(JTable jTabel) {
        jTabel.setRowSelectionAllowed(true);
        row = jTabel.getSelectedRow();
        String kolom1 = jTabel.getValueAt(row,0).toString();
        String kolom2 = jTabel.getValueAt(row,1).toString();
        String kolom3 = jTabel.getValueAt(row,2).toString();
        String kolom4 = jTabel.getValueAt(row,3).toString();
        String kolom5 = jTabel.getValueAt(row,4).toString();
        String kolom6 = jTabel.getValueAt(row,5).toString();
        String kolom7 = jTabel.getValueAt(row,6).toString();
        String kolom8 = jTabel.getValueAt(row,7).toString();
        String kolom9 = jTabel.getValueAt(row,8).toString();
        String kolom10 = jTabel.getValueAt(row,9).toString();
        String kolom11 = jTabel.getValueAt(row,10).toString();
        String kolom12 = jTabel.getValueAt(row,11).toString();
        String kolom13 = jTabel.getValueAt(row,12).toString();
        String kolom14 = jTabel.getValueAt(row,13).toString();
        String kolom15 = jTabel.getValueAt(row,14).toString();
        String kolom16 = jTabel.getValueAt(row,15).toString();
        String kolom17 = jTabel.getValueAt(row,16).toString();
        String kolom18 = jTabel.getValueAt(row,17).toString();
        jlblNO_TRN1.setText(kolom2);
        jlblTGL_TRN.setText(kolom3);
        jCmbSTS.setSelectedItem(kolom4);
        jCmbFilter.setSelectedItem(kolom5);
        jTxtFldKD_PRD.setText(kolom6);
        jTxtFldNM_PRD.setText(kolom7); 
        jlblQTY_AWAL1.setText(kolom8);
        jTxtFldQTY_OUT.setText(kolom9);
        jlblTTL_QTY1.setText(kolom10);
        jlblHRG_PK1.setText(kolom11);
        jlblHRG_JL1.setText(kolom12);
        jTxtFldKD_OTLT.setText(kolom13);
        jTxtFldNM_OTLT.setText(kolom14);
        jTxtFldKD_SBS.setText(kolom15);
        jTxtFldNM_SBS.setText(kolom16);
        jTxtFldKD_KRYWN.setText(kolom17);
        jTxtFldNM_KRYWN.setText(kolom18);       
        
        jTxtFldSTS.setText(kolom4);
        jTxtFldFilter.setText(kolom5);
        jTxtFldKD_PRD2.setText(kolom6);        
        jTxtFldQTY_OUT2.setText(kolom9);
        jTxtFldKD_OTLT2.setText(kolom13);
        jTxtFldNIK.setText(kolom17);
        jTxtFldKD_SBS2.setText(kolom15);
    }
            
    private void kondisiSave() {        
    int jmlBaris, jmlKolom;
    String vsQry;
    jmlBaris = DfltTblMode2.getRowCount();
    jmlKolom = DfltTblMode2.getColumnCount()-1;
    
    String Qry1 = "insert into transaksi ";
    String Qry2 = Qry1 + "(NO_TRN, TGL_TRN, KD_PRD, NM_PRD, FILTER, QTY_IN, QTY_AWAL, QTY_AKHIR, HRG_PK, HRG_JL, QTY_OUT, KET, NOTRNIN, NOTRNOUT, ASAL_BRG, STS_BRG, NO_TRN_OUT, KD_OTLT, NM_OTLT, NIK, NM_KRYWN, KD_SBS, NM_SBS) ";
    String Qry3 = Qry2 + "values ('"; 

    String[] data = new String[jmlKolom];
    
    int ok = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menyimpan transaksi?", "Konfirmasi",
                JOptionPane.OK_CANCEL_OPTION);
        if(ok==0) {
           try{               
              for(int baris = 0; baris < jmlBaris; baris++) {
                Statement st = aplikasiInventory.config.getConnection().createStatement(); 
                 for(int kolom = 0; kolom < jmlKolom; kolom++) {
                    data[kolom] = jTable1.getValueAt(baris,kolom+1).toString().trim();
                 }
                 
                 
                 vsQry = Qry3 + 
                         data[0] + "','" +
                         data[1] + "','" +
                         data[4] + "','" +
                         data[5] + "','" +
                         data[3] + "','','" +
                         data[6] + "','" +
                         data[8] + "','" +
                         data[9] + "','" +
                         data[10]+ "','" +
                         data[7]+ "','','0','"+nextVAL+"','','" +
                         data[2] + "','','" +
                         data[11]+ "','" +
                         data[12] + "','" +
                         data[15] + "','" +
                         data[16] + "','" +
                         data[13] + "','" +
                         data[14] + "')";
                 
                 st.executeUpdate(vsQry.toString());
                 st.close();
                 vsQry.equals(""); 
                 
              } 
           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(this, "Transaksi gagal disimpan! : " +ex);
             }
           clearTEXT();
           tampilDataKeTabel();
           JOptionPane.showMessageDialog(this, "Transaksi sudah disimpan");
           
        }  
    }
        
    private void hapusDataDariTabel() {
      try {
        int barisVal;
        String kolom1Val;
        int jmlBaris = jTable1.getRowCount();
        jTable1.setRowSelectionAllowed(true); 
        row = jTable1.getSelectedRow();
        DfltTblMode2.removeRow(row);
        vnNO = vnNO - 1;
        
        for (int baris = 0; baris < jmlBaris; baris++) {
           barisVal = baris + 1;
           kolom1Val = String.valueOf(barisVal).toString(); 
           DfltTblMode2.setValueAt(kolom1Val, baris, 0);
        }
        enableField(false);
      }
          catch (Exception ex) {}   
    }
    
    private void updproduk(){
       e = jlblTTL_QTY1.getText();
       r = jTxtFldKD_PRD.getText();
                      
       try {  
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "update produk set "+
                       "QTY_AWAL = "+"'"+ e +"' "+
                       "where KD_PRD = '"+ r +"'");
           }
                    catch (SQLException ex) {
                    }
    }
    
    private void kurang(){
        nil1 = Integer.valueOf(jlblQTY_AWAL1.getText());
        nil2 = Integer.valueOf(jTxtFldQTY_OUT.getText());
        nil3 = nil1 - nil2 ;
        hasil= String.valueOf(nil3);
        jlblTTL_QTY1.setText(hasil);
    }
    
    private void buatnotrn(){       
        try{
           Statement st = aplikasiInventory.config.getConnection().createStatement();        
           String sql = "Select NO_TRN,NOTRNOUT from transaksi";
           ResultSet set = st.executeQuery(sql);
           
           if (!set.next()) {
             jlblNO_TRN1.setText("OUT1");
             nextVAL = 1;
           }
           else
           {
             String sql2 = "Select max(NOTRNOUT) as no_trn from transaksi";
             ResultSet set2 = st.executeQuery(sql2);
             
             if (set2.next()) {
                varVAL = set2.getInt("no_trn"); 
                nextVAL = varVAL + 1;
                jlblNO_TRN1.setText("OUT"+Integer.toString(nextVAL));
             }
           }
        } catch (Exception ex){}
    }

    private void ambilData(int no, JTable jTabel5) {
    try {
            
            jTabel7.setModel(DfltTblMode2);       
            
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = jlblNO_TRN1.getText();
                String kolom3 = jlblTGL_TRN.getText();
                String kolom4 = jCmbSTS.getSelectedItem().toString();
                String kolom5 = jCmbFilter.getSelectedItem().toString();                      
                String kolom6 = jTxtFldKD_PRD.getText();
                String kolom7 = jTxtFldNM_PRD.getText();
                String kolom8 = jlblQTY_AWAL1.getText();
                String kolom9 = jTxtFldQTY_OUT.getText();
                String kolom10 = jlblTTL_QTY1.getText();
                String kolom11 = jlblHRG_PK1.getText();
                String kolom12 = jlblHRG_JL1.getText();
                String kolom13 = jTxtFldKD_OTLT.getText();
                String kolom14 = jTxtFldNM_OTLT.getText();
                String kolom15 = jTxtFldKD_SBS.getText();
                String kolom16 = jTxtFldNM_SBS.getText();
                String kolom17 = jTxtFldKD_KRYWN.getText();
                String kolom18 = jTxtFldNM_KRYWN.getText();                
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10, kolom11, kolom12, kolom13, kolom14, kolom15, kolom16, kolom17, kolom18};
                DfltTblMode2.addRow(data);
                
            jTabel7.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel7.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel7.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(4);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel7.getColumnModel().getColumn(5);
            column.setPreferredWidth(80);
            column = jTabel7.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(7);
            column.setPreferredWidth(70);
            column = jTabel7.getColumnModel().getColumn(8);
            column.setPreferredWidth(70);
            column = jTabel7.getColumnModel().getColumn(9);
            column.setPreferredWidth(70);
            column = jTabel7.getColumnModel().getColumn(10);
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(11);
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(12);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel7.getColumnModel().getColumn(13);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel7.getColumnModel().getColumn(14);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel7.getColumnModel().getColumn(15);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel7.getColumnModel().getColumn(16);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel7.getColumnModel().getColumn(17);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
          }
          catch (Exception ex) {
              JOptionPane.showMessageDialog(this, "Data gagal disimpan! : " +ex);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jBtnOk = new javax.swing.JButton();
        jBtnKembali = new javax.swing.JButton();
        jDialogOTLT = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jBtnKembaliOTLT = new javax.swing.JButton();
        jBtnOkOTLT = new javax.swing.JButton();
        jDialogKRYWN = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jBtnKembaliKRYWN = new javax.swing.JButton();
        jBtnOkKRYWN = new javax.swing.JButton();
        jDialogSBS = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTblSBS = new javax.swing.JTable();
        jBtnKembaliSBS = new javax.swing.JButton();
        jBtnOkSBS = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jlblQTY_AWAL = new javax.swing.JLabel();
        jlblNO_TRN = new javax.swing.JLabel();
        jlblTTL_QTY = new javax.swing.JLabel();
        jlblxTTL_QTY = new javax.swing.JLabel();
        jlblTTL_QTY1 = new javax.swing.JLabel();
        jlblQTY_AWAL1 = new javax.swing.JLabel();
        jlblxHRG_PK = new javax.swing.JLabel();
        jlblHRG_PK = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlblHRG_JL = new javax.swing.JLabel();
        jTxtFldKD_SBS2 = new javax.swing.JTextField();
        jTxtFldNM_SBS = new javax.swing.JTextField();
        jBtnSBS = new javax.swing.JButton();
        jlblQTY_IN = new javax.swing.JLabel();
        jlblxNO_TRN = new javax.swing.JLabel();
        jlblKD_PRD = new javax.swing.JLabel();
        jlblFilter = new javax.swing.JLabel();
        jlblxQTY_OUT = new javax.swing.JLabel();
        jCmbFilter = new javax.swing.JComboBox();
        jlblxKD_PRD = new javax.swing.JLabel();
        jlblxFilter = new javax.swing.JLabel();
        jlblHRG_JL1 = new javax.swing.JLabel();
        jlblNO_TRN1 = new javax.swing.JLabel();
        jlblxHRG_JL = new javax.swing.JLabel();
        jTxtFldSTS = new javax.swing.JTextField();
        jlblFilter1 = new javax.swing.JLabel();
        jlblSTS = new javax.swing.JLabel();
        jlblxSTS = new javax.swing.JLabel();
        jlblKRYWN = new javax.swing.JLabel();
        jTxtFldNM_KRYWN = new javax.swing.JTextField();
        jlblxFilter1 = new javax.swing.JLabel();
        jTxtFldKD_OTLT = new javax.swing.JTextField();
        jlblHRG_PK1 = new javax.swing.JLabel();
        jlblxQTY_AWAL = new javax.swing.JLabel();
        jTxtFldQTY_OUT2 = new javax.swing.JTextField();
        jTxtFldKD_PRD2 = new javax.swing.JTextField();
        jTxtFldFilter = new javax.swing.JTextField();
        jlblTGL_TRN = new javax.swing.JLabel();
        jCmbSTS = new javax.swing.JComboBox();
        jBtnKRYWN = new javax.swing.JButton();
        jBtnPRD = new javax.swing.JButton();
        jTxtFldNM_OTLT = new javax.swing.JTextField();
        jTxtFldKD_OTLT2 = new javax.swing.JTextField();
        jTxtFldNIK = new javax.swing.JTextField();
        jlblxKD_SBS = new javax.swing.JLabel();
        jTxtFldKD_SBS = new javax.swing.JTextField();
        jlblxTGL_TRN = new javax.swing.JLabel();
        lblTGL_TRN = new javax.swing.JLabel();
        jTxtFldKD_PRD = new javax.swing.JTextField();
        jTxtFldKD_KRYWN = new javax.swing.JTextField();
        jBtnOTLT = new javax.swing.JButton();
        jTxtFldQTY_OUT = new javax.swing.JTextField();
        jlblxKRYWN = new javax.swing.JLabel();
        jTxtFldNM_PRD = new javax.swing.JTextField();
        jlblKD_SBS = new javax.swing.JLabel();
        jBtnAmbil = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jBtnLap = new javax.swing.JButton();
        jBtnCari1 = new javax.swing.JButton();
        jcb2 = new javax.swing.JCheckBox();
        jBtnSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBtnNew = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jBtnCari = new javax.swing.JButton();
        jcb1 = new javax.swing.JCheckBox();
        jBtnDlt1 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMnKembali = new javax.swing.JMenu();

        jDialog1.setMinimumSize(new java.awt.Dimension(655, 600));
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
        jScrollPane2.setViewportView(jTable2);

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOk)
                    .addComponent(jBtnKembali))
                .addContainerGap())
        );

        jDialogOTLT.setMinimumSize(new java.awt.Dimension(455, 455));
        jDialogOTLT.setResizable(false);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        jBtnKembaliOTLT.setText("Kembali");
        jBtnKembaliOTLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembaliOTLTActionPerformed(evt);
            }
        });

        jBtnOkOTLT.setText("Ok");
        jBtnOkOTLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkOTLTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogOTLTLayout = new javax.swing.GroupLayout(jDialogOTLT.getContentPane());
        jDialogOTLT.getContentPane().setLayout(jDialogOTLTLayout);
        jDialogOTLTLayout.setHorizontalGroup(
            jDialogOTLTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogOTLTLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembaliOTLT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOkOTLT)
                .addContainerGap())
        );
        jDialogOTLTLayout.setVerticalGroup(
            jDialogOTLTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogOTLTLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jDialogOTLTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOkOTLT)
                    .addComponent(jBtnKembaliOTLT))
                .addContainerGap())
        );

        jDialogKRYWN.setMinimumSize(new java.awt.Dimension(455, 455));
        jDialogKRYWN.setResizable(false);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable4);

        jBtnKembaliKRYWN.setText("Kembali");
        jBtnKembaliKRYWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembaliKRYWNActionPerformed(evt);
            }
        });

        jBtnOkKRYWN.setText("Ok");
        jBtnOkKRYWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkKRYWNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogKRYWNLayout = new javax.swing.GroupLayout(jDialogKRYWN.getContentPane());
        jDialogKRYWN.getContentPane().setLayout(jDialogKRYWNLayout);
        jDialogKRYWNLayout.setHorizontalGroup(
            jDialogKRYWNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogKRYWNLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembaliKRYWN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOkKRYWN)
                .addContainerGap())
        );
        jDialogKRYWNLayout.setVerticalGroup(
            jDialogKRYWNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogKRYWNLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jDialogKRYWNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOkKRYWN)
                    .addComponent(jBtnKembaliKRYWN))
                .addContainerGap())
        );

        jDialogSBS.setMinimumSize(new java.awt.Dimension(655, 600));
        jDialogSBS.setModal(true);
        jDialogSBS.setResizable(false);

        jTblSBS.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTblSBS);

        jBtnKembaliSBS.setText("Kembali");
        jBtnKembaliSBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembaliSBSActionPerformed(evt);
            }
        });

        jBtnOkSBS.setText("Ok");
        jBtnOkSBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkSBSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogSBSLayout = new javax.swing.GroupLayout(jDialogSBS.getContentPane());
        jDialogSBS.getContentPane().setLayout(jDialogSBSLayout);
        jDialogSBSLayout.setHorizontalGroup(
            jDialogSBSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogSBSLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembaliSBS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOkSBS)
                .addContainerGap())
        );
        jDialogSBSLayout.setVerticalGroup(
            jDialogSBSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogSBSLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jDialogSBSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOkSBS)
                    .addComponent(jBtnKembaliSBS))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form Transaksi Out");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Transaksi Out"));

        jLabel4.setText("Rp.");

        jlblQTY_AWAL.setText("Qty Awal");

        jlblNO_TRN.setText("No. Transaksi");

        jlblTTL_QTY.setText("Total Qty");

        jlblxTTL_QTY.setText(":");

        jlblxHRG_PK.setText(":");

        jlblHRG_PK.setText("Harga Pokok");

        jLabel3.setText("Rp.");

        jlblHRG_JL.setText("Harga Jual");

        jBtnSBS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnSBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSBSActionPerformed(evt);
            }
        });

        jlblQTY_IN.setText("Qty Keluar*");

        jlblxNO_TRN.setText(":");

        jlblKD_PRD.setText("Produk*");

        jlblFilter.setText("Filter Produk Berdasarkan*");

        jlblxQTY_OUT.setText(":");

        jCmbFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Kategori", "SBS", "Outlet" }));
        jCmbFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbFilterItemStateChanged(evt);
            }
        });

        jlblxKD_PRD.setText(":");

        jlblxFilter.setText(":");

        jlblxHRG_JL.setText(":");

        jlblFilter1.setText("Outlet*");

        jlblSTS.setText("Status Keluar Barang*");

        jlblxSTS.setText(":");

        jlblKRYWN.setText("Karyawan*");

        jlblxFilter1.setText(":");

        jlblxQTY_AWAL.setText(":");
        jlblxQTY_AWAL.setToolTipText("");

        jCmbSTS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Jual", "Pinjam" }));

        jBtnKRYWN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnKRYWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKRYWNActionPerformed(evt);
            }
        });

        jBtnPRD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnPRD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPRDActionPerformed(evt);
            }
        });

        jlblxKD_SBS.setText(":");

        jlblxTGL_TRN.setText(":");

        lblTGL_TRN.setText("Tgl Transaksi");

        jBtnOTLT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnOTLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOTLTActionPerformed(evt);
            }
        });

        jTxtFldQTY_OUT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtFldQTY_OUTKeyReleased(evt);
            }
        });

        jlblxKRYWN.setText(":");

        jlblKD_SBS.setText("SBS*");

        jBtnAmbil.setText("Ambil Data");
        jBtnAmbil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAmbilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblNO_TRN)
                    .addComponent(jlblFilter)
                    .addComponent(jlblSTS)
                    .addComponent(jlblKRYWN)
                    .addComponent(jlblKD_PRD)
                    .addComponent(jlblFilter1)
                    .addComponent(jlblKD_SBS))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblxNO_TRN)
                    .addComponent(jlblxSTS)
                    .addComponent(jlblxFilter)
                    .addComponent(jlblxKD_PRD)
                    .addComponent(jlblxFilter1)
                    .addComponent(jlblxKD_SBS)
                    .addComponent(jlblxKRYWN)
                    .addComponent(jlblxQTY_OUT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCmbSTS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCmbFilter, 0, 1, Short.MAX_VALUE)
                    .addComponent(jTxtFldKD_PRD)
                    .addComponent(jTxtFldKD_OTLT)
                    .addComponent(jTxtFldKD_SBS)
                    .addComponent(jTxtFldKD_KRYWN)
                    .addComponent(jTxtFldQTY_OUT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTxtFldNM_PRD)
                    .addComponent(jTxtFldNM_OTLT)
                    .addComponent(jTxtFldNM_SBS)
                    .addComponent(jTxtFldNM_KRYWN, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnKRYWN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSBS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnOTLT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnPRD, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtFldNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFldKD_OTLT2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFldKD_SBS2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFldKD_PRD2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jlblNO_TRN1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTGL_TRN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblxTGL_TRN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblTGL_TRN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblQTY_AWAL)
                    .addComponent(jlblQTY_IN)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtFldSTS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldQTY_OUT2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jlblHRG_PK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblxQTY_AWAL)
                    .addComponent(jlblxHRG_PK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblQTY_AWAL1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblTTL_QTY)
                        .addGap(27, 27, 27)
                        .addComponent(jlblxTTL_QTY)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblTTL_QTY1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(8, 8, 8)
                        .addComponent(jlblHRG_PK1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jlblHRG_JL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlblxHRG_JL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(3, 3, 3)
                        .addComponent(jlblHRG_JL1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnAmbil))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlblNO_TRN, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jlblxNO_TRN, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jlblNO_TRN1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTGL_TRN, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jlblxTGL_TRN, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jlblTGL_TRN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblSTS)
                    .addComponent(jCmbSTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblxSTS)
                    .addComponent(jTxtFldSTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCmbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblxFilter)
                    .addComponent(jlblFilter)
                    .addComponent(jTxtFldFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblxKD_PRD)
                                .addComponent(jTxtFldNM_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTxtFldKD_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlblKD_PRD))
                            .addComponent(jBtnPRD, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblxFilter1)
                                .addComponent(jTxtFldKD_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTxtFldNM_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlblFilter1))
                            .addComponent(jBtnOTLT, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnSBS, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTxtFldKD_SBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTxtFldNM_SBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlblxKD_SBS)
                                .addComponent(jlblKD_SBS)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnKRYWN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlblxKRYWN)
                                    .addComponent(jlblKRYWN)
                                    .addComponent(jTxtFldNM_KRYWN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtFldKD_KRYWN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTxtFldQTY_OUT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblxQTY_OUT)
                                    .addComponent(jlblQTY_IN)
                                    .addComponent(jTxtFldQTY_OUT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jTxtFldKD_OTLT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTxtFldKD_PRD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtFldKD_SBS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTxtFldNIK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlblTTL_QTY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblxTTL_QTY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlblQTY_AWAL1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblQTY_AWAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlblxQTY_AWAL))
                            .addComponent(jlblTTL_QTY1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jlblHRG_JL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlblHRG_PK1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlblHRG_PK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlblxHRG_PK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jlblxHRG_JL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlblHRG_JL1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnAmbil))))
        );

        jTxtFldKD_KRYWN.getAccessibleContext().setAccessibleName("");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Transaksi Out"));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel6.setText("s.d");

        jLabel5.setText("Cari Data Berdasarkan Periode :");

        jBtnLap.setText("Laporan");
        jBtnLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLapActionPerformed(evt);
            }
        });

        jBtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCari1ActionPerformed(evt);
            }
        });

        jcb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb2ActionPerformed(evt);
            }
        });

        jBtnSave.setText("Simpan");
        jBtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaveActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jBtnNew.setText("Data Baru");
        jBtnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNewActionPerformed(evt);
            }
        });

        jLabel2.setText("Cari Data Berdasarkan No / Tgl Transaksi :");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jBtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCariActionPerformed(evt);
            }
        });

        jcb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb1ActionPerformed(evt);
            }
        });

        jBtnDlt1.setText("Hapus");
        jBtnDlt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDlt1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcb1))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jBtnNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnDlt1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLap))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(82, 82, 82)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcb2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnLap)
                            .addComponent(jBtnDlt1))
                        .addComponent(jBtnSave))
                    .addComponent(jBtnNew, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        jMenuBar2.setBackground(new java.awt.Color(236, 236, 236));

        jMnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        jMnKembali.setToolTipText("Menu Utama");
        jMnKembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnKembaliMousePressed(evt);
            }
        });
        jMenuBar2.add(jMnKembali);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMnKembaliMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnKembaliMousePressed
        // TODO add your handling code here:
        aplikasiInventory.Start.setVisible(true);
        aplikasiInventory.loadNIK(aplikasiInventory.Start.jlblstart_jpg);
        aplikasiInventory.vsNIK = aplikasiInventory.Start.jlblstart_jpg.getText();
        aplikasiInventory.HakAkses();
        dispose();
    }//GEN-LAST:event_jMnKembaliMousePressed

    private void jBtnPRDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPRDActionPerformed
        // TODO add your handling code here:
        jDialog1.setLocationRelativeTo(null);
        tampilDataKeTabel2();
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jBtnPRDActionPerformed

    private void jBtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCariActionPerformed
        // TODO add your handling code here:
          cari(jTabel2); 
          tampilDataKeTabelcari();
    }//GEN-LAST:event_jBtnCariActionPerformed

    private void jBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkActionPerformed
        // TODO add your handling code here:
        if (jTabel3.getSelectedRow() != -1){
            klikTabeljDialog(jTabel);
            jDialog1.dispose();    
        }
        else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
        }
    }//GEN-LAST:event_jBtnOkActionPerformed

    private void jTxtFldQTY_OUTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFldQTY_OUTKeyReleased
        // TODO add your handling code here:
        if (!jTxtFldQTY_OUT.getText().equals("")){
            kurang();
            evt.consume();
        }
        else{
            jlblTTL_QTY1.setText("");
        }
    }//GEN-LAST:event_jTxtFldQTY_OUTKeyReleased

    private void jCmbFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbFilterItemStateChanged
        // TODO add your handling code here:
        if (jCmbFilter.getSelectedItem() == "Kategori") {
           fieldTemp = "KD_CTGRY";
        }
        else if (jCmbFilter.getSelectedItem() == "Outlet") {
           fieldTemp = "KD_OTLT";
        }
        else if (jCmbFilter.getSelectedItem() == "SBS") {
           fieldTemp = "KD_SBS";
        }
        jTxtFldKD_PRD.setText("");
        jTxtFldNM_PRD.setText("");
        jlblTTL_QTY1.setText("");
        jlblQTY_AWAL1.setText("");
        jTxtFldQTY_OUT.setText("");
        jlblHRG_PK1.setText("");
        jlblHRG_JL1.setText("");
    }//GEN-LAST:event_jCmbFilterItemStateChanged

    private void jBtnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliActionPerformed
        // TODO add your handling code here:
        jDialog1.dispose();
    }//GEN-LAST:event_jBtnKembaliActionPerformed

    private void jcb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb2ActionPerformed
        // TODO add your handling code here:
        if (jcb2.isSelected()) {
            enablejcb(false);
        } else {
            jcb1.setEnabled(true);
            jcb2.setEnabled(true);
            jTextField2.setEnabled(false);
            jTextField3.setEnabled(false);
            jTextField2.setText("");
            jTextField3.setText("");
            jBtnCari1.setEnabled(false);
            jBtnDlt1.setVisible(true);
            tampilDataKeTabel();
            clearTEXT();
        }
    }//GEN-LAST:event_jcb2ActionPerformed

    private void jcb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb1ActionPerformed
        // TODO add your handling code here:
        if (jcb1.isSelected()) {
            enablejcb(true);
        } else {
            jcb1.setEnabled(true);
            jcb2.setEnabled(true);
            jTextField1.setEnabled(false);
            jTextField1.setText("");
            jBtnCari.setEnabled(false);
            jBtnDlt1.setVisible(true);
            tampilDataKeTabel();
            clearTEXT();
        }
    }//GEN-LAST:event_jcb1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        aplikasiInventory.Start.setVisible(true);
        aplikasiInventory.loadNIK(aplikasiInventory.Start.jlblstart_jpg);
        aplikasiInventory.vsNIK = aplikasiInventory.Start.jlblstart_jpg.getText();
        aplikasiInventory.HakAkses();
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jBtnOTLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOTLTActionPerformed
        // TODO add your handling code here:
        jDialogOTLT.setLocationRelativeTo(null);
        tampilDataKeTabelOTLT();
        jDialogOTLT.setVisible(true);
    }//GEN-LAST:event_jBtnOTLTActionPerformed

    private void jBtnKRYWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKRYWNActionPerformed
        // TODO add your handling code here:
        jDialogKRYWN.setLocationRelativeTo(null);
        tampilDataKeTabelKRYWN();
        jDialogKRYWN.setVisible(true);
    }//GEN-LAST:event_jBtnKRYWNActionPerformed

    private void jBtnKembaliOTLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliOTLTActionPerformed
        // TODO add your handling code here:
        jDialogOTLT.dispose();
    }//GEN-LAST:event_jBtnKembaliOTLTActionPerformed

    private void jBtnOkOTLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkOTLTActionPerformed
        // TODO add your handling code here:
        if (jTabel4.getSelectedRow() != -1){
            klikTabeljDialogOTLT(jTabel);
            jDialogOTLT.dispose();    
        }
        else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
        }
    }//GEN-LAST:event_jBtnOkOTLTActionPerformed

    private void jBtnKembaliKRYWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliKRYWNActionPerformed
        // TODO add your handling code here:
        jDialogKRYWN.dispose();
    }//GEN-LAST:event_jBtnKembaliKRYWNActionPerformed

    private void jBtnOkKRYWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkKRYWNActionPerformed
        // TODO add your handling code here:
        if (jTabel5.getSelectedRow() != -1){
            klikTabeljDialogKRYWN(jTabel);
            jDialogKRYWN.dispose();    
        }
        else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
        }
    }//GEN-LAST:event_jBtnOkKRYWNActionPerformed

    private void jBtnSBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSBSActionPerformed
        // TODO add your handling code here:
        jDialogSBS.setLocationRelativeTo(null);
        tampilDataKeTabelSBS();
        jDialogSBS.setVisible(true);
    }//GEN-LAST:event_jBtnSBSActionPerformed

    private void jBtnKembaliSBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliSBSActionPerformed
        // TODO add your handling code here:
        jDialogSBS.dispose();
    }//GEN-LAST:event_jBtnKembaliSBSActionPerformed

    private void jBtnOkSBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkSBSActionPerformed
        // TODO add your handling code here:
        if (jTabel6.getSelectedRow() != -1){
            klikTabeljDialogSBS(jTabel);
            jDialogSBS.dispose();    
        }
        else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
        }
    }//GEN-LAST:event_jBtnOkSBSActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar() == KeyEvent.VK_ENTER) {
          jBtnCari.doClick();  
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jBtnCari1.doClick();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jTextField3.requestFocus();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jBtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCari1ActionPerformed
        // TODO add your handling code here:
        tampilDataKeTabelcari();
    }//GEN-LAST:event_jBtnCari1ActionPerformed

    private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaveActionPerformed
        // TODO add your handling code here:
       updproduk();
       kondisiSave();
       buatnotrn();
       enableField(false);
       enableBtn(true);
       enviBtnSave(false);
       enviBtnNew(true);
    }//GEN-LAST:event_jBtnSaveActionPerformed

    private void jBtnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNewActionPerformed
        // TODO add your handling code here:
        clearTEXT();
        buatnotrn();
        enableField(true);
        enableBtn(false);
        enviBtnSave(true);
        enviBtnNew(false);
    }//GEN-LAST:event_jBtnNewActionPerformed

    private void jBtnLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLapActionPerformed
        // TODO add your handling code here:
        try {
            File file = new File("src/laporan/lapout.jrxml");
            jasperDesign = JRXmlLoader.load(file);
            param.clear();
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, param, aplikasiInventory.config.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jBtnLapActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        klikTabel(jTabel);
        enableField(false);
        enableBtn(true);
        enviBtnSave(false);
        enviBtnNew(true);
    }//GEN-LAST:event_jTable1MousePressed

    private void jBtnAmbilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAmbilActionPerformed
        // TODO add your handling code here:
        tampilDataKeTabelambil();
        jTxtFldKD_PRD.setText("");
        jTxtFldNM_PRD.setText("");
        jlblTTL_QTY1.setText("");
        jTxtFldQTY_OUT.setText("");
        jlblQTY_AWAL1.setText("");
        jlblHRG_PK1.setText("");
        jlblHRG_JL1.setText("");
        jTextField1.setText("");
        jCmbFilter.setSelectedItem("");
        jCmbSTS.setSelectedItem("");
        jTxtFldKD_OTLT.setText("");
        jTxtFldNM_OTLT.setText("");
        jTxtFldKD_SBS.setText("");
        jTxtFldNM_SBS.setText("");
        jTxtFldKD_KRYWN.setText("");
        jTxtFldNM_KRYWN.setText("");
    }//GEN-LAST:event_jBtnAmbilActionPerformed

    private void jBtnDlt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDlt1ActionPerformed
        // TODO add your handling code here:
        hapusDataDariTabel(); 
        clearTEXT(); 
    }//GEN-LAST:event_jBtnDlt1ActionPerformed

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
            java.util.logging.Logger.getLogger(TRN_OUT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TRN_OUT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TRN_OUT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TRN_OUT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TRN_OUT().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAmbil;
    private javax.swing.JButton jBtnCari;
    private javax.swing.JButton jBtnCari1;
    private javax.swing.JButton jBtnDlt1;
    private javax.swing.JButton jBtnKRYWN;
    private javax.swing.JButton jBtnKembali;
    private javax.swing.JButton jBtnKembaliKRYWN;
    private javax.swing.JButton jBtnKembaliOTLT;
    private javax.swing.JButton jBtnKembaliSBS;
    private javax.swing.JButton jBtnLap;
    private javax.swing.JButton jBtnNew;
    private javax.swing.JButton jBtnOTLT;
    private javax.swing.JButton jBtnOk;
    private javax.swing.JButton jBtnOkKRYWN;
    private javax.swing.JButton jBtnOkOTLT;
    private javax.swing.JButton jBtnOkSBS;
    private javax.swing.JButton jBtnPRD;
    private javax.swing.JButton jBtnSBS;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JComboBox jCmbFilter;
    private javax.swing.JComboBox jCmbSTS;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialogKRYWN;
    private javax.swing.JDialog jDialogOTLT;
    private javax.swing.JDialog jDialogSBS;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTblSBS;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTxtFldFilter;
    private javax.swing.JTextField jTxtFldKD_KRYWN;
    private javax.swing.JTextField jTxtFldKD_OTLT;
    private javax.swing.JTextField jTxtFldKD_OTLT2;
    private javax.swing.JTextField jTxtFldKD_PRD;
    private javax.swing.JTextField jTxtFldKD_PRD2;
    private javax.swing.JTextField jTxtFldKD_SBS;
    private javax.swing.JTextField jTxtFldKD_SBS2;
    private javax.swing.JTextField jTxtFldNIK;
    private javax.swing.JTextField jTxtFldNM_KRYWN;
    private javax.swing.JTextField jTxtFldNM_OTLT;
    private javax.swing.JTextField jTxtFldNM_PRD;
    private javax.swing.JTextField jTxtFldNM_SBS;
    private javax.swing.JTextField jTxtFldQTY_OUT;
    private javax.swing.JTextField jTxtFldQTY_OUT2;
    private javax.swing.JTextField jTxtFldSTS;
    private javax.swing.JCheckBox jcb1;
    private javax.swing.JCheckBox jcb2;
    private javax.swing.JLabel jlblFilter;
    private javax.swing.JLabel jlblFilter1;
    private javax.swing.JLabel jlblHRG_JL;
    private javax.swing.JLabel jlblHRG_JL1;
    private javax.swing.JLabel jlblHRG_PK;
    private javax.swing.JLabel jlblHRG_PK1;
    private javax.swing.JLabel jlblKD_PRD;
    private javax.swing.JLabel jlblKD_SBS;
    private javax.swing.JLabel jlblKRYWN;
    private javax.swing.JLabel jlblNO_TRN;
    private javax.swing.JLabel jlblNO_TRN1;
    private javax.swing.JLabel jlblQTY_AWAL;
    private javax.swing.JLabel jlblQTY_AWAL1;
    private javax.swing.JLabel jlblQTY_IN;
    private javax.swing.JLabel jlblSTS;
    private javax.swing.JLabel jlblTGL_TRN;
    private javax.swing.JLabel jlblTTL_QTY;
    private javax.swing.JLabel jlblTTL_QTY1;
    private javax.swing.JLabel jlblxFilter;
    private javax.swing.JLabel jlblxFilter1;
    private javax.swing.JLabel jlblxHRG_JL;
    private javax.swing.JLabel jlblxHRG_PK;
    private javax.swing.JLabel jlblxKD_PRD;
    private javax.swing.JLabel jlblxKD_SBS;
    private javax.swing.JLabel jlblxKRYWN;
    private javax.swing.JLabel jlblxNO_TRN;
    private javax.swing.JLabel jlblxQTY_AWAL;
    private javax.swing.JLabel jlblxQTY_OUT;
    private javax.swing.JLabel jlblxSTS;
    private javax.swing.JLabel jlblxTGL_TRN;
    private javax.swing.JLabel jlblxTTL_QTY;
    private javax.swing.JLabel lblTGL_TRN;
    // End of variables declaration//GEN-END:variables
}
