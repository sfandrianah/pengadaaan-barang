package com.pengadaan.barang;

import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class produk extends javax.swing.JFrame {

    private Integer row;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable(), jTabel2 = new JTable(), jTabel4 = new JTable(), jTabel5 = new JTable(), jTabel6 = new JTable(), jTabel7 = new JTable(), jTabel8 = new JTable();
    private DefaultTableModel DfltTblMode;
    private TableColumn column;
    private String e,r,i,k,a,s,p,t,x,y,z,b,c,d,l,m,n,o,q,f,g,h,j,u,v;
    
    
    public produk() {
        initComponents();
        aplikasiInventory.konekkeDatabase();
        tampilDataKeTabel();
        enableBtn(false);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(false);
        jTxtFldKD_SPLR.setEnabled(false);
        jTxtFldNM_SPLR.setEnabled(false);
        jTxtFldKD_CTGRY.setEnabled(false);
        jTxtFldNM_CTGRY.setEnabled(false);
        jTxtFldKD_SBS.setEnabled(false);
        jTxtFldNM_SBS.setEnabled(false);
        jTxtFldKD_OTLT.setEnabled(false);
        jTxtFldNM_OTLT.setEnabled(false);
        jTxtFldKD_DEPT.setEnabled(false);
        jTxtFldNM_DEPT.setEnabled(false);
        jTxtFldKD_PRD2.setVisible(false);
        jTxtFldNM_PRD2.setVisible(false);
        jTxtFldKD_DEPT2.setVisible(false);
        jTxtFldKD_CTGRY2.setVisible(false);
        jTxtFldKD_SBS2.setVisible(false);
        jTxtFldKD_SPLR2.setVisible(false);
        jTxtFldKD_OTLT2.setVisible(false);
        jTxtFldUKURAN.setVisible(false);
        jTxtFldHRG_PK2.setVisible(false);
        jTxtFldHRG_JL2.setVisible(false);    
      }

    private void clearTEXT() {
        jTxtFldKD_PRD.setText("");
        jTxtFldNM_PRD.setText("");
        jTxtFldKD_SPLR.setText("");
        jTxtFldNM_SPLR.setText("");
        jTxtFldKD_CTGRY.setText("");
        jTxtFldNM_CTGRY.setText("");
        jTxtFldKD_SBS.setText("");
        jTxtFldNM_SBS.setText("");
        jTxtFldKD_OTLT.setText("");
        jTxtFldNM_OTLT.setText("");
        jTxtFldHRG_PK.setText("");
        jTxtFldHRG_JL.setText("");
        jCmbUKRN.setSelectedItem("");
        jTxtFldKD_DEPT.setText("");
        jTxtFldNM_DEPT.setText("");
    }
    
    private void enableBtn(boolean x) {
        jBtnDlt.setEnabled(x);
        jBtnEdit.setEnabled(x);
    }
    
    private void enviBtnSave(boolean x) {
        jBtnSave.setEnabled(x);
        jBtnSave.setVisible(x);
    }
    
    private void enviBtnNew(boolean x){
        jBtnNew.setEnabled(x);
        jBtnNew.setVisible(x);
    }
    
   private void enviBtnSave2(boolean x) {
        jBtnSave2.setEnabled(x);
        jBtnSave2.setVisible(x);
    }
    
    private void enableField(boolean x) {
        jTxtFldKD_PRD.setEnabled(x);
        jTxtFldNM_PRD.setEnabled(x);
        jTxtFldHRG_PK.setEnabled(x);
        jTxtFldHRG_JL.setEnabled(x);
        jCmbUKRN.setEnabled(x);
        jBtnCTGRY.setEnabled(x);
        jBtnSBS.setEnabled(x);
        jBtnOTLT.setEnabled(x);
        jBtnSPLR.setEnabled(x);
        jBtnDEPT.setEnabled(x);
    }
    
    private void tampilDataKeTabel() {
        jTabel = jTable1;
        tabelModel(jTabel);
    }
    
    private void tampilDataKeTabelcari() {
        jTabel2 = jTable1;
        cari(jTabel2);
    }
    
    
    private void tampilDataKeTabelCTGRY() {
        jTabel4 = jTblCTGRY;
        modeljdialogCTGRY(jTabel4);
    }
    
    private void tampilDataKeTabelSBS() {
        jTabel5 = jTblSBS;
        modeljDialogSBS(jTabel5);
    }
    
    private void tampilDataKeTabelSPLR() {
        jTabel6 = jTblSPLR;
        modeljDialogSPLR(jTabel6);
    }
    
    private void tampilDataKeTabelOTLT() {
        jTabel7 = jTblOTLT;
        modeljDialogOTLT(jTabel7);
    }
    
    private void tampilDataKeTabelDEPT() {
        jTabel8 = jTblDEPT;
        modeljDialogDEPT(jTabel8);
    }
    
    private void modeljdialogBRNCH(JTable jTabel) {
        try {
            Object[] field = {"No","Kode Cabang","Nama Cabang"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);
            
            String sql = "Select * from cabang";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_BRNCH");
                String kolom3 = set.getString("NM_BRNCH");
                String[] data = {kolom1, kolom2, kolom3};
                DfltTblMode.addRow(data);
            }
            
            jTabel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel.getColumnModel().getColumn(1);
            column.setPreferredWidth(160);
            column = jTabel.getColumnModel().getColumn(2);
            column.setPreferredWidth(250);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void modeljdialogCTGRY(JTable jTabel4) {
        try {
            Object[] field = {"No","Kode Kategori","Nama Kategori"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel4.setModel(DfltTblMode);
            
            String sql = "Select * from kategori";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_CTGRY");
                String kolom3 = set.getString("NM_CTGRY");
                String[] data = {kolom1, kolom2, kolom3};
                DfltTblMode.addRow(data);
            }
            
            jTabel4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel4.getColumnModel().getColumn(0);
            column.setPreferredWidth(41);
            column = jTabel4.getColumnModel().getColumn(1);
            column.setPreferredWidth(160);
            column = jTabel4.getColumnModel().getColumn(2);
            column.setPreferredWidth(250);
            
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialogCTGRY(JTable jTabel) {
        jTabel4.setRowSelectionAllowed(true);
        row = jTabel4.getSelectedRow();
        String kolom1 = jTabel4.getValueAt(row,0).toString();
        String kolom2 = jTabel4.getValueAt(row,1).toString();
        String kolom3 = jTabel4.getValueAt(row,2).toString();
        jTxtFldKD_CTGRY.setText(kolom2);
        jTxtFldNM_CTGRY.setText(kolom3); 
    }
    
    private void modeljDialogSBS(JTable jTabel5) {
        try {
            Object[] field = {"No","Kode SBS","Nama SBS"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel5.setModel(DfltTblMode);
            
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
            column = jTabel5.getColumnModel().getColumn(0);
            column.setPreferredWidth(41);
            column = jTabel5.getColumnModel().getColumn(1);
            column.setPreferredWidth(160);
            column = jTabel5.getColumnModel().getColumn(2);
            column.setPreferredWidth(250);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialogSBS(JTable jTabel) {
        jTabel5.setRowSelectionAllowed(true);
        row = jTabel5.getSelectedRow();
        String kolom1 = jTabel5.getValueAt(row,0).toString();
        String kolom2 = jTabel5.getValueAt(row,1).toString();
        String kolom3 = jTabel5.getValueAt(row,2).toString();
        jTxtFldKD_SBS.setText(kolom2);
        jTxtFldNM_SBS.setText(kolom3);
    }
    
     private void modeljDialogSPLR(JTable jTabel6) {
        try {
            Object[] field = {"No","Kode Suplier","Nama Suplier"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel6.setModel(DfltTblMode);
            
            String sql = "Select * from supplier";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_SPLR");
                String kolom3 = set.getString("NM_SPLR");
                String[] data = {kolom1, kolom2, kolom3};
                DfltTblMode.addRow(data);
            }
      
            jTabel6.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel6.getColumnModel().getColumn(0);
            column.setPreferredWidth(41);
            column = jTabel6.getColumnModel().getColumn(1);
            column.setPreferredWidth(160);
            column = jTabel6.getColumnModel().getColumn(2);
            column.setPreferredWidth(250);
 
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialogSPLR(JTable jTabel) {
        jTabel6.setRowSelectionAllowed(true);
        row = jTabel6.getSelectedRow();
        String kolom1 = jTabel6.getValueAt(row,0).toString();
        String kolom2 = jTabel6.getValueAt(row,1).toString();
        String kolom3 = jTabel6.getValueAt(row,2).toString();
        jTxtFldKD_SPLR.setText(kolom2);
        jTxtFldNM_SPLR.setText(kolom3);
    }
    
    private void modeljDialogOTLT(JTable jTabel7) {
        try {
            Object[] field = {"No","Kode Otlet","Nama Otlet","PIC","Alamat","Kota","Provinsi","Telepon","Kode Cabang","Nama Cabang"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel7.setModel(DfltTblMode);
            
            String sql = "Select * from outlet";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_OTLT");
                String kolom3 = set.getString("NM_OTLT");
                String kolom4 = set.getString("PIC");
                String kolom5 = set.getString("ALAMAT");
                String kolom6 = set.getString("KOTA");
                String kolom7 = set.getString("PROVINSI");
                String kolom8 = set.getString("TLP");
                String kolom9 = set.getString("KD_BRNCH");
                String kolom10 = set.getString("NM_BRNCH");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10};
                DfltTblMode.addRow(data);
            }
            
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
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(5);
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(7);
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(8);
            column.setPreferredWidth(100);
            column = jTabel7.getColumnModel().getColumn(9);
            column.setPreferredWidth(100);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialogOTLT(JTable jTabel) {
        jTabel7.setRowSelectionAllowed(true);
        row = jTabel7.getSelectedRow();
        String kolom1 = jTabel7.getValueAt(row,0).toString();
        String kolom2 = jTabel7.getValueAt(row,1).toString();
        String kolom3 = jTabel7.getValueAt(row,2).toString();
        jTxtFldKD_OTLT.setText(kolom2);
        jTxtFldNM_OTLT.setText(kolom3); 
    }
    
     private void modeljDialogDEPT(JTable jTabel8) {
        try {
            Object[] field = {"No","Kode Departemen","Nama Departemen"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel8.setModel(DfltTblMode);
            
            String sql = "Select * from departemen";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_DEPT");
                String kolom3 = set.getString("NM_DEPT");
                String[] data = {kolom1, kolom2, kolom3};
                DfltTblMode.addRow(data);
            }
            
            jTabel8.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel8.getColumnModel().getColumn(0);
            column.setPreferredWidth(41);
            column = jTabel8.getColumnModel().getColumn(1);
            column.setPreferredWidth(160);
            column = jTabel8.getColumnModel().getColumn(2);
            column.setPreferredWidth(250);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
   private void klikTabeljDialogDEPT(JTable jTabel) {
        jTabel8.setRowSelectionAllowed(true);
        row = jTabel8.getSelectedRow();
        String kolom1 = jTabel8.getValueAt(row,0).toString();
        String kolom2 = jTabel8.getValueAt(row,1).toString();
        String kolom3 = jTabel8.getValueAt(row,2).toString();
        jTxtFldKD_DEPT.setText(kolom2);
        jTxtFldNM_DEPT.setText(kolom3);
    }
     
    private void cari(JTable jTabel2){
    try {
            Object[] field = {"No","Kode Produk","Nama Produk","Kode Departemen","Nama Departemen","Kode Kategori","Nama Kategori","Kode SBS","Nama SBS ","Kode Suplier","Nama Suplier","Kode Otlet","Nama Otlet","Ukuran","Harga Pokok","Harga Jual"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel2.setModel(DfltTblMode);
            
            String sql = "Select * from produk where KD_PRD like '%" + jTextField1.getText() + "%'" +
                         "or NM_PRD like '%" + jTextField1.getText() + "%'";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_PRD");
                String kolom3 = set.getString("NM_PRD");
                String kolom4 = set.getString("KD_DEPT");
                String kolom5 = set.getString("NM_DEPT");
                String kolom6 = set.getString("KD_CTGRY");
                String kolom7 = set.getString("NM_CTGRY");
                String kolom8 = set.getString("KD_SBS");
                String kolom9 = set.getString("NM_SBS");
                String kolom10 = set.getString("KD_SPLR");
                String kolom11 = set.getString("NM_SPLR");
                String kolom12 = set.getString("KD_OTLT");
                String kolom13 = set.getString("NM_OTLT");
                String kolom14 = set.getString("UKURAN");
                String kolom15 = set.getString("HRG_PK");
                String kolom16 = set.getString("HRG_JL");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10, kolom11, kolom12, kolom13, kolom14, kolom15, kolom16};
                DfltTblMode.addRow(data);
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
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(5);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(7);
            column.setMinWidth(0);
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(8);
            column.setMinWidth(0);
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(9);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(10);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(11);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(12);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(13);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(14);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(15);
            column.setPreferredWidth(100);
            }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void kondisiSave() { 
       e = jTxtFldKD_PRD.getText();
       r = jTxtFldNM_PRD.getText();
       i = jTxtFldKD_CTGRY.getText();
       k = jTxtFldNM_CTGRY.getText();
       a = jTxtFldKD_SBS.getText();
       s = jTxtFldNM_SBS.getText();
       p = jTxtFldKD_SPLR.getText();
       t = jTxtFldNM_SPLR.getText();
       x = jTxtFldKD_OTLT.getText();
       y = jTxtFldNM_OTLT.getText();
       b = jTxtFldHRG_PK.getText();
       c = jTxtFldHRG_JL.getText();
       j = jTxtFldKD_DEPT.getText();
       u = jTxtFldNM_DEPT.getText();     
               
       try {  
       if (e.equals("") || r.equals("") || i.equals("") || p.equals("") || b.equals("") || c.equals("") || j.equals("") || jCmbUKRN.getSelectedItem().equals("")) 
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua, kecuali kode outlet (bersifat optional)!");
           clearTEXT();
           }
       else {
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "insert into produk"+
                       "(KD_PRD, NM_PRD, KD_CTGRY, NM_CTGRY, KD_SBS, NM_SBS, KD_SPLR, NM_SPLR, KD_OTLT, NM_OTLT, QTY_AWAL, HRG_PK, HRG_JL, KD_DEPT, NM_DEPT, UKURAN) values "+
                       "('"+ e +"','"+ r +"','"+ i +"','"+ k +"','"+ a +"','"+ s +"','"+ p +"','"+ t +"','"+ x +"','"+ y +"','0','"+ b +"','"+ c +"','"+ j +"','"+ u +"','"+ jCmbUKRN.getSelectedItem() +"')");
                 
                 tampilDataKeTabel();      
                 JOptionPane.showMessageDialog(this,"Data berhasil disimpan");
            } 
           }
                    catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Data gagal disimpan! : " +ex);
                    }
               }
    
    private void kondisiEdit() { 
       e = jTxtFldKD_PRD.getText();
       r = jTxtFldNM_PRD.getText();
       i = jTxtFldKD_CTGRY.getText();
       k = jTxtFldNM_CTGRY.getText();
       a = jTxtFldKD_SBS.getText();
       s = jTxtFldNM_SBS.getText();
       p = jTxtFldKD_SPLR.getText();
       t = jTxtFldNM_SPLR.getText();
       x = jTxtFldKD_OTLT.getText();
       y = jTxtFldNM_OTLT.getText();
       b = jTxtFldHRG_PK.getText();
       c = jTxtFldHRG_JL.getText();
       d = jTxtFldKD_PRD2.getText();
       l = jTxtFldNM_PRD2.getText();
       m = jTxtFldKD_CTGRY2.getText();
       n = jTxtFldKD_SBS2.getText();
       o = jTxtFldKD_SPLR2.getText();
       q = jTxtFldKD_OTLT2.getText();
       f = jTxtFldUKURAN.getText();
       g = jTxtFldHRG_PK2.getText();
       h = jTxtFldHRG_JL2.getText();
       j = jTxtFldKD_DEPT.getText();
       u = jTxtFldNM_DEPT.getText();  
       v = jTxtFldKD_DEPT2.getText();
       
       try {
       if (e.equals("") || r.equals("") || i.equals("") || p.equals("") || b.equals("") || c.equals("") || j.equals("") || jCmbUKRN.getSelectedItem().equals("")) 
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua, kecuali kode outlet (bersifat optional)!");
           clearTEXT();
           }
       else if (e.equals(d) && r.equals(l) && i.equals(m) && a.equals(n) && p.equals(o) && x.equals(q) && jCmbUKRN.getSelectedItem().equals(f) && b.equals(g) && c.equals(h) && j.equals(v)) 
           {
           JOptionPane.showMessageDialog(null, "Tidak ada data yang diperbaharui!");
           }
       else {    
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "update produk set "+
                       "KD_PRD = "+"'"+ e +"', "+
                       "NM_PRD = "+"'"+ r +"', "+
                       "KD_CTGRY = "+"'"+ i +"', "+
                       "NM_CTGRY = "+"'"+ k +"', "+
                       "KD_SBS = "+"'"+ a +"', "+
                       "NM_SBS = "+"'"+ s +"', "+
                       "KD_SPLR = "+"'"+ p +"', "+
                       "NM_SPLR = "+"'"+ t +"', "+
                       "KD_OTLT = "+"'"+ x +"', "+
                       "NM_OTLT = "+"'"+ y +"', "+
                       "UKURAN = "+"'"+ jCmbUKRN.getSelectedItem() +"', "+
                       "HRG_PK = "+"'"+ b +"', "+
                       "HRG_JL = "+"'"+ c +"', "+
                       "KD_DEPT = "+"'"+ j +"', "+
                       "NM_DEPT = "+"'"+ u +"' "+
                       "where KD_PRD = '"+ d +"'");
       
                 tampilDataKeTabel(); 
                 JOptionPane.showMessageDialog(this,"Data berhasil diperbaharui");
               }
           }
                    catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Data gagal diperbaharui! : " +ex);
                    }
               }
    
    private void tabelModel(JTable jTabel) {
        try {
            Object[] field = {"No","Kode Produk","Nama Produk","Kode Departemen","Nama Departemen","Kode Kategori","Nama Kategori","Kode SBS","Nama SBS ","Kode Suplier","Nama Suplier","Kode Otlet","Nama Otlet","Ukuran","Harga Pokok","Harga Jual"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);
            
            String sql = "Select * from produk";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_PRD");
                String kolom3 = set.getString("NM_PRD");
                String kolom4 = set.getString("KD_DEPT");
                String kolom5 = set.getString("NM_DEPT");
                String kolom6 = set.getString("KD_CTGRY");
                String kolom7 = set.getString("NM_CTGRY");
                String kolom8 = set.getString("KD_SBS");
                String kolom9 = set.getString("NM_SBS");
                String kolom10 = set.getString("KD_SPLR");
                String kolom11 = set.getString("NM_SPLR");
                String kolom12 = set.getString("KD_OTLT");
                String kolom13 = set.getString("NM_OTLT");
                String kolom14 = set.getString("UKURAN");
                String kolom15 = set.getString("HRG_PK");
                String kolom16 = set.getString("HRG_JL");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10, kolom11, kolom12, kolom13, kolom14, kolom15, kolom16};
                DfltTblMode.addRow(data);
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
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(5);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(7);
            column.setMinWidth(0);
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(8);
            column.setMinWidth(0);
            column.setMaxWidth(0);
            column = jTabel.getColumnModel().getColumn(9);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(10);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(11);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(12);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(13);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(14);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(15);
            column.setPreferredWidth(100);
          }
          catch (SQLException e) {
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
        jTxtFldKD_PRD.setText(kolom2);
        jTxtFldNM_PRD.setText(kolom3);
        jTxtFldKD_DEPT.setText(kolom4); 
        jTxtFldNM_DEPT.setText(kolom5); 
        jTxtFldKD_CTGRY.setText(kolom6); 
        jTxtFldNM_CTGRY.setText(kolom7); 
        jTxtFldKD_SBS.setText(kolom8);
        jTxtFldNM_SBS.setText(kolom9);
        jTxtFldKD_SPLR.setText(kolom10);
        jTxtFldNM_SPLR.setText(kolom11);
        jTxtFldKD_OTLT.setText(kolom12);
        jTxtFldNM_OTLT.setText(kolom13);
        jCmbUKRN.setSelectedItem(kolom14);
        jTxtFldHRG_PK.setText(kolom15);
        jTxtFldHRG_JL.setText(kolom16);
        
        jTxtFldKD_PRD2.setText(kolom2);
        jTxtFldNM_PRD2.setText(kolom3);
        jTxtFldKD_DEPT2.setText(kolom4);
        jTxtFldKD_CTGRY2.setText(kolom6);
        jTxtFldKD_SBS2.setText(kolom8);
        jTxtFldKD_SPLR2.setText(kolom10);
        jTxtFldKD_OTLT2.setText(kolom12);
        jTxtFldUKURAN.setText(kolom14);
        jTxtFldHRG_PK2.setText(kolom15);
        jTxtFldHRG_JL2.setText(kolom16);
    }
    
    private void kondisiHapus() {
       e = jTxtFldKD_PRD.getText();
            
       try {
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            st.executeUpdate(
            " delete from produk where KD_PRD ='"+ e +"'");
            clearTEXT();
            tampilDataKeTabel();
            
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
       }
        catch (SQLException ex) {
              JOptionPane.showMessageDialog(this,"Data gagal dihapus: " +ex);
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

        jDialogCTGRY = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTblCTGRY = new javax.swing.JTable();
        jBtnOkCTGRY = new javax.swing.JButton();
        jBtnKembaliCTGRY = new javax.swing.JButton();
        jDialogSBS = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTblSBS = new javax.swing.JTable();
        jBtnOkSBS = new javax.swing.JButton();
        jBtnKembaliSBS = new javax.swing.JButton();
        jDialogSPLR = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTblSPLR = new javax.swing.JTable();
        jBtnOkSPLR = new javax.swing.JButton();
        jBtnKembaliSPLR = new javax.swing.JButton();
        jDialogOTLT = new javax.swing.JDialog();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTblOTLT = new javax.swing.JTable();
        jBtnOkOTLT = new javax.swing.JButton();
        jBtnKembaliOTLT = new javax.swing.JButton();
        jDialogDEPT = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblDEPT = new javax.swing.JTable();
        jBtnKembaliDEPT = new javax.swing.JButton();
        jBtnOkDEPT = new javax.swing.JButton();
        jlblKD_PRD = new javax.swing.JLabel();
        jlblNM_PRD = new javax.swing.JLabel();
        jlblxKD_PRD = new javax.swing.JLabel();
        jlblxNM_PRD = new javax.swing.JLabel();
        jTxtFldKD_PRD = new javax.swing.JTextField();
        jTxtFldNM_PRD = new javax.swing.JTextField();
        jBtnSave = new javax.swing.JButton();
        jBtnEdit = new javax.swing.JButton();
        jBtnDlt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jlblKD_SPLR = new javax.swing.JLabel();
        jlblKD_SBS = new javax.swing.JLabel();
        jlblKD_CTGRY = new javax.swing.JLabel();
        jlblKD_OTLT = new javax.swing.JLabel();
        jlblxKD_CTGRY = new javax.swing.JLabel();
        jlblxKD_SBS = new javax.swing.JLabel();
        jlblxNIK = new javax.swing.JLabel();
        jlblxKD_OTLT = new javax.swing.JLabel();
        jTxtFldKD_SPLR = new javax.swing.JTextField();
        jTxtFldKD_CTGRY = new javax.swing.JTextField();
        jTxtFldKD_SBS = new javax.swing.JTextField();
        jTxtFldKD_OTLT = new javax.swing.JTextField();
        jTxtFldNM_SPLR = new javax.swing.JTextField();
        jTxtFldNM_CTGRY = new javax.swing.JTextField();
        jTxtFldNM_SBS = new javax.swing.JTextField();
        jTxtFldNM_OTLT = new javax.swing.JTextField();
        jBtnSPLR = new javax.swing.JButton();
        jBtnCTGRY = new javax.swing.JButton();
        jBtnSBS = new javax.swing.JButton();
        jBtnOTLT = new javax.swing.JButton();
        jBtnNew = new javax.swing.JButton();
        jBtnSave2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jBtnCari = new javax.swing.JButton();
        jTxtFldKD_PRD2 = new javax.swing.JTextField();
        jTxtFldNM_PRD2 = new javax.swing.JTextField();
        jTxtFldKD_CTGRY2 = new javax.swing.JTextField();
        jTxtFldKD_SBS2 = new javax.swing.JTextField();
        jTxtFldKD_SPLR2 = new javax.swing.JTextField();
        jTxtFldKD_OTLT2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jlblUKURAN = new javax.swing.JLabel();
        jlblxUKURAN = new javax.swing.JLabel();
        jlblxHRG_PK = new javax.swing.JLabel();
        jTxtFldHRG_PK = new javax.swing.JTextField();
        jlblHRG_JL = new javax.swing.JLabel();
        jlblxHRG_JL = new javax.swing.JLabel();
        jTxtFldHRG_JL = new javax.swing.JTextField();
        jTxtFldUKURAN = new javax.swing.JTextField();
        jTxtFldHRG_PK2 = new javax.swing.JTextField();
        jTxtFldHRG_JL2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTxtFldNM_DEPT = new javax.swing.JTextField();
        jBtnDEPT = new javax.swing.JButton();
        jlblDEPT = new javax.swing.JLabel();
        jlblxDEPT = new javax.swing.JLabel();
        jTxtFldKD_DEPT = new javax.swing.JTextField();
        jTxtFldKD_DEPT2 = new javax.swing.JTextField();
        jCmbUKRN = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMnKembali = new javax.swing.JMenu();

        jDialogCTGRY.setMinimumSize(new java.awt.Dimension(455, 400));
        jDialogCTGRY.setModal(true);
        jDialogCTGRY.setResizable(false);

        jTblCTGRY.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTblCTGRY);

        jBtnOkCTGRY.setText("Ok");
        jBtnOkCTGRY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkCTGRYActionPerformed(evt);
            }
        });

        jBtnKembaliCTGRY.setText("Kembali");
        jBtnKembaliCTGRY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembaliCTGRYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogCTGRYLayout = new javax.swing.GroupLayout(jDialogCTGRY.getContentPane());
        jDialogCTGRY.getContentPane().setLayout(jDialogCTGRYLayout);
        jDialogCTGRYLayout.setHorizontalGroup(
            jDialogCTGRYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
            .addGroup(jDialogCTGRYLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembaliCTGRY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOkCTGRY)
                .addContainerGap())
        );
        jDialogCTGRYLayout.setVerticalGroup(
            jDialogCTGRYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogCTGRYLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialogCTGRYLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOkCTGRY)
                    .addComponent(jBtnKembaliCTGRY))
                .addContainerGap())
        );

        jDialogSBS.setMinimumSize(new java.awt.Dimension(455, 400));
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
        jScrollPane4.setViewportView(jTblSBS);

        jBtnOkSBS.setText("Ok");
        jBtnOkSBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkSBSActionPerformed(evt);
            }
        });

        jBtnKembaliSBS.setText("Kembali");
        jBtnKembaliSBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembaliSBSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogSBSLayout = new javax.swing.GroupLayout(jDialogSBS.getContentPane());
        jDialogSBS.getContentPane().setLayout(jDialogSBSLayout);
        jDialogSBSLayout.setHorizontalGroup(
            jDialogSBSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogSBSLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBtnKembaliSBS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOkSBS)
                .addContainerGap())
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
        );
        jDialogSBSLayout.setVerticalGroup(
            jDialogSBSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogSBSLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialogSBSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOkSBS)
                    .addComponent(jBtnKembaliSBS))
                .addContainerGap())
        );

        jDialogSPLR.setMinimumSize(new java.awt.Dimension(455, 400));
        jDialogSPLR.setResizable(false);

        jTblSPLR.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTblSPLR);

        jBtnOkSPLR.setText("Ok");
        jBtnOkSPLR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkSPLRActionPerformed(evt);
            }
        });

        jBtnKembaliSPLR.setText("Kembali");
        jBtnKembaliSPLR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembaliSPLRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogSPLRLayout = new javax.swing.GroupLayout(jDialogSPLR.getContentPane());
        jDialogSPLR.getContentPane().setLayout(jDialogSPLRLayout);
        jDialogSPLRLayout.setHorizontalGroup(
            jDialogSPLRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogSPLRLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembaliSPLR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOkSPLR)
                .addContainerGap())
        );
        jDialogSPLRLayout.setVerticalGroup(
            jDialogSPLRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogSPLRLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jDialogSPLRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOkSPLR)
                    .addComponent(jBtnKembaliSPLR))
                .addContainerGap())
        );

        jDialogOTLT.setMinimumSize(new java.awt.Dimension(455, 400));

        jTblOTLT.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTblOTLT);

        jBtnOkOTLT.setText("Ok");
        jBtnOkOTLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkOTLTActionPerformed(evt);
            }
        });

        jBtnKembaliOTLT.setText("Kembali");
        jBtnKembaliOTLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembaliOTLTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogOTLTLayout = new javax.swing.GroupLayout(jDialogOTLT.getContentPane());
        jDialogOTLT.getContentPane().setLayout(jDialogOTLTLayout);
        jDialogOTLTLayout.setHorizontalGroup(
            jDialogOTLTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
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
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jDialogOTLTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOkOTLT)
                    .addComponent(jBtnKembaliOTLT))
                .addContainerGap())
        );

        jDialogDEPT.setMinimumSize(new java.awt.Dimension(455, 400));

        jTblDEPT.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTblDEPT);

        jBtnKembaliDEPT.setText("Kembali");
        jBtnKembaliDEPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembaliDEPTActionPerformed(evt);
            }
        });

        jBtnOkDEPT.setText("Ok");
        jBtnOkDEPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOkDEPTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogDEPTLayout = new javax.swing.GroupLayout(jDialogDEPT.getContentPane());
        jDialogDEPT.getContentPane().setLayout(jDialogDEPTLayout);
        jDialogDEPTLayout.setHorizontalGroup(
            jDialogDEPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogDEPTLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembaliDEPT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOkDEPT)
                .addContainerGap())
        );
        jDialogDEPTLayout.setVerticalGroup(
            jDialogDEPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogDEPTLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jDialogDEPTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOkDEPT)
                    .addComponent(jBtnKembaliDEPT))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form Produk");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlblKD_PRD.setText("Kode Produk*");

        jlblNM_PRD.setText("Nama Produk*");

        jlblxKD_PRD.setText(":");

        jlblxNM_PRD.setText(":");

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

        jlblKD_SPLR.setText("Suplier*");

        jlblKD_SBS.setText("SBS");

        jlblKD_CTGRY.setText("Kategori*");

        jlblKD_OTLT.setText("Outlet");

        jlblxKD_CTGRY.setText(":");

        jlblxKD_SBS.setText(":");

        jlblxNIK.setText(":");

        jlblxKD_OTLT.setText(":");

        jBtnSPLR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnSPLR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSPLRActionPerformed(evt);
            }
        });

        jBtnCTGRY.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnCTGRY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCTGRYActionPerformed(evt);
            }
        });

        jBtnSBS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnSBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSBSActionPerformed(evt);
            }
        });

        jBtnOTLT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnOTLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOTLTActionPerformed(evt);
            }
        });

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

        jLabel1.setText("Cari Data Berdasarkan Kode / Nama Produk :");

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

        jLabel2.setText("Harga Pokok*");

        jlblUKURAN.setText("Ukuran*");

        jlblxUKURAN.setText(":");

        jlblxHRG_PK.setText(":");

        jlblHRG_JL.setText("Harga Jual*");

        jlblxHRG_JL.setText(":");

        jLabel4.setText("Rp.");

        jLabel5.setText("Rp.");

        jBtnDEPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnDEPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDEPTActionPerformed(evt);
            }
        });

        jlblDEPT.setText("Departemen*");

        jlblxDEPT.setText(":");

        jCmbUKRN.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "pcs", "box", "unit" }));

        jMenuBar1.setBackground(new java.awt.Color(236, 236, 236));

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jBtnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnSave2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnDlt)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblKD_PRD)
                            .addComponent(jlblNM_PRD)
                            .addComponent(jlblKD_CTGRY)
                            .addComponent(jlblKD_SBS)
                            .addComponent(jlblKD_SPLR)
                            .addComponent(jlblKD_OTLT)
                            .addComponent(jLabel2)
                            .addComponent(jlblDEPT)
                            .addComponent(jlblHRG_JL)
                            .addComponent(jlblUKURAN))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblxKD_PRD)
                            .addComponent(jlblxNM_PRD)
                            .addComponent(jlblxDEPT)
                            .addComponent(jlblxKD_CTGRY)
                            .addComponent(jlblxKD_SBS)
                            .addComponent(jlblxNIK)
                            .addComponent(jlblxKD_OTLT)
                            .addComponent(jlblxUKURAN)
                            .addComponent(jlblxHRG_PK)
                            .addComponent(jlblxHRG_JL))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtFldKD_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtFldNM_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtFldKD_SBS2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldKD_SPLR2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldKD_OTLT2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtFldKD_PRD2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldNM_PRD2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldKD_CTGRY2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTxtFldHRG_PK, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTxtFldHRG_JL, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTxtFldHRG_PK2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTxtFldHRG_JL2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtFldKD_CTGRY, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldNM_CTGRY, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBtnCTGRY, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtFldKD_DEPT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldNM_DEPT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBtnDEPT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jCmbUKRN, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTxtFldKD_SBS, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(jTxtFldKD_SPLR, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(jTxtFldKD_OTLT, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTxtFldNM_SBS, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTxtFldNM_SPLR, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTxtFldNM_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jBtnOTLT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jBtnSBS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jBtnSPLR, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTxtFldUKURAN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTxtFldKD_DEPT2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(14, 14, 14))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtFldKD_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblxKD_PRD)
                            .addComponent(jlblKD_PRD))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtFldNM_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblNM_PRD)
                            .addComponent(jlblxNM_PRD)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtFldKD_PRD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldNM_PRD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldKD_CTGRY2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtFldKD_SBS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldKD_SPLR2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldKD_OTLT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtFldKD_DEPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTxtFldNM_DEPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlblDEPT)
                        .addComponent(jlblxDEPT))
                    .addComponent(jBtnDEPT, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtFldKD_CTGRY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTxtFldNM_CTGRY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlblKD_CTGRY)
                        .addComponent(jlblxKD_CTGRY))
                    .addComponent(jBtnCTGRY, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtFldKD_SBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTxtFldNM_SBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlblKD_SBS)
                        .addComponent(jlblxKD_SBS))
                    .addComponent(jBtnSBS, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtFldKD_SPLR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldNM_SPLR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblKD_SPLR)
                            .addComponent(jlblxNIK))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnOTLT, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTxtFldKD_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTxtFldNM_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jlblxKD_OTLT)
                                .addComponent(jlblKD_OTLT)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtFldUKURAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldKD_DEPT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCmbUKRN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblxUKURAN)
                            .addComponent(jlblUKURAN)))
                    .addComponent(jBtnSPLR, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldHRG_PK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jlblxHRG_PK)
                    .addComponent(jLabel2)
                    .addComponent(jTxtFldHRG_PK2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldHRG_JL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jlblxHRG_JL)
                    .addComponent(jlblHRG_JL)
                    .addComponent(jTxtFldHRG_JL2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnSave, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnEdit)
                        .addComponent(jBtnNew)
                        .addComponent(jBtnSave2))
                    .addComponent(jBtnDlt, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
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

    private void jBtnCTGRYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCTGRYActionPerformed
        // TODO add your handling code here:
        jDialogCTGRY.setLocationRelativeTo(null);
        tampilDataKeTabelCTGRY();
        jDialogCTGRY.setVisible(true);
    }//GEN-LAST:event_jBtnCTGRYActionPerformed

    private void jBtnSBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSBSActionPerformed
        // TODO add your handling code here:
        jDialogSBS.setLocationRelativeTo(null);
        tampilDataKeTabelSBS();
        jDialogSBS.setVisible(true);
    }//GEN-LAST:event_jBtnSBSActionPerformed

    private void jBtnSPLRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSPLRActionPerformed
        // TODO add your handling code here:
        jDialogSPLR.setLocationRelativeTo(null);
        tampilDataKeTabelSPLR();
        jDialogSPLR.setVisible(true);
    }//GEN-LAST:event_jBtnSPLRActionPerformed

    private void jBtnOTLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOTLTActionPerformed
        // TODO add your handling code here:
        jDialogOTLT.setLocationRelativeTo(null);
        tampilDataKeTabelOTLT();
        jDialogOTLT.setVisible(true);
    }//GEN-LAST:event_jBtnOTLTActionPerformed

    private void jBtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCariActionPerformed
        // TODO add your handling code here:
        tampilDataKeTabelcari();
    }//GEN-LAST:event_jBtnCariActionPerformed

    private void jBtnOkCTGRYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkCTGRYActionPerformed
        // TODO add your handling code here:
        if (jTabel4.getSelectedRow() != -1) {
            klikTabeljDialogCTGRY(jTabel);
            jDialogCTGRY.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
        }
    }//GEN-LAST:event_jBtnOkCTGRYActionPerformed

    private void jBtnOkSBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkSBSActionPerformed
        // TODO add your handling code here:
        if (jTabel5.getSelectedRow() != -1) {
            klikTabeljDialogSBS(jTabel);
            jDialogSBS.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
        }
    }//GEN-LAST:event_jBtnOkSBSActionPerformed

    private void jBtnOkSPLRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkSPLRActionPerformed
        // TODO add your handling code here:
        if (jTabel6.getSelectedRow() != -1) {
            klikTabeljDialogSPLR(jTabel);
            jDialogSPLR.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
        }        
    }//GEN-LAST:event_jBtnOkSPLRActionPerformed

    private void jBtnOkOTLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkOTLTActionPerformed
        // TODO add your handling code here:
        if (jTabel7.getSelectedRow() != -1) {
            klikTabeljDialogOTLT(jTabel);
            jDialogOTLT.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
        }
    }//GEN-LAST:event_jBtnOkOTLTActionPerformed

    private void jBtnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNewActionPerformed
        // TODO add your handling code here:
       clearTEXT(); 
       enableField(true);
       enableBtn(false);
       enviBtnSave(true);
       enviBtnSave2(false);
       enviBtnNew(false);
       jTxtFldKD_PRD.requestFocus();
    }//GEN-LAST:event_jBtnNewActionPerformed

    private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaveActionPerformed
        // TODO add your handling code here:
       kondisiSave();
       enableField(false);
       enableBtn(true);
       enviBtnSave(false);
       enviBtnSave2(false);
       enviBtnNew(true);
    }//GEN-LAST:event_jBtnSaveActionPerformed

    private void jBtnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSave2ActionPerformed
        // TODO add your handling code here:
       kondisiEdit();
       enableField(false);
       enableBtn(false);
       enviBtnSave(false);
       enviBtnSave2(false);
       enviBtnNew(true);
    }//GEN-LAST:event_jBtnSave2ActionPerformed

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
        jTextField1.setText("");
    }//GEN-LAST:event_jBtnDltActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
       klikTabel(jTabel);
       enableField(false);
       enableBtn(true);
       enviBtnSave(false);
       enviBtnSave2(false);
       enviBtnNew(true);
    }//GEN-LAST:event_jTable1MousePressed

    private void jBtnKembaliCTGRYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliCTGRYActionPerformed
        // TODO add your handling code here:
        jDialogCTGRY.dispose();
    }//GEN-LAST:event_jBtnKembaliCTGRYActionPerformed

    private void jBtnKembaliSBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliSBSActionPerformed
        // TODO add your handling code here:
        jDialogSBS.dispose();
    }//GEN-LAST:event_jBtnKembaliSBSActionPerformed

    private void jBtnKembaliSPLRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliSPLRActionPerformed
        // TODO add your handling code here:
        jDialogSPLR.dispose();
    }//GEN-LAST:event_jBtnKembaliSPLRActionPerformed

    private void jBtnKembaliOTLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliOTLTActionPerformed
        // TODO add your handling code here:
        jDialogOTLT.dispose();
    }//GEN-LAST:event_jBtnKembaliOTLTActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        aplikasiInventory.Start.setVisible(true);
        aplikasiInventory.loadNIK(aplikasiInventory.Start.jlblstart_jpg);
        aplikasiInventory.vsNIK = aplikasiInventory.Start.jlblstart_jpg.getText();
        aplikasiInventory.HakAkses();
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jBtnDEPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDEPTActionPerformed
        // TODO add your handling code here:
        jDialogDEPT.setLocationRelativeTo(null);
        tampilDataKeTabelDEPT();
        jDialogDEPT.setVisible(true); 
    }//GEN-LAST:event_jBtnDEPTActionPerformed

    private void jBtnKembaliDEPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliDEPTActionPerformed
        // TODO add your handling code here:
        jDialogDEPT.dispose();
    }//GEN-LAST:event_jBtnKembaliDEPTActionPerformed

    private void jBtnOkDEPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkDEPTActionPerformed
        // TODO add your handling code here:
        if (jTabel8.getSelectedRow() != -1) {
            klikTabeljDialogDEPT(jTabel);
            jDialogDEPT.dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
        }     
    }//GEN-LAST:event_jBtnOkDEPTActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jBtnCari.doClick();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

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
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(produk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new produk().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCTGRY;
    private javax.swing.JButton jBtnCari;
    private javax.swing.JButton jBtnDEPT;
    private javax.swing.JButton jBtnDlt;
    private javax.swing.JButton jBtnEdit;
    private javax.swing.JButton jBtnKembaliCTGRY;
    private javax.swing.JButton jBtnKembaliDEPT;
    private javax.swing.JButton jBtnKembaliOTLT;
    private javax.swing.JButton jBtnKembaliSBS;
    private javax.swing.JButton jBtnKembaliSPLR;
    private javax.swing.JButton jBtnNew;
    private javax.swing.JButton jBtnOTLT;
    private javax.swing.JButton jBtnOkCTGRY;
    private javax.swing.JButton jBtnOkDEPT;
    private javax.swing.JButton jBtnOkOTLT;
    private javax.swing.JButton jBtnOkSBS;
    private javax.swing.JButton jBtnOkSPLR;
    private javax.swing.JButton jBtnSBS;
    private javax.swing.JButton jBtnSPLR;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JButton jBtnSave2;
    private javax.swing.JComboBox jCmbUKRN;
    private javax.swing.JDialog jDialogCTGRY;
    private javax.swing.JDialog jDialogDEPT;
    private javax.swing.JDialog jDialogOTLT;
    private javax.swing.JDialog jDialogSBS;
    private javax.swing.JDialog jDialogSPLR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTblCTGRY;
    private javax.swing.JTable jTblDEPT;
    private javax.swing.JTable jTblOTLT;
    private javax.swing.JTable jTblSBS;
    private javax.swing.JTable jTblSPLR;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTxtFldHRG_JL;
    private javax.swing.JTextField jTxtFldHRG_JL2;
    private javax.swing.JTextField jTxtFldHRG_PK;
    private javax.swing.JTextField jTxtFldHRG_PK2;
    private javax.swing.JTextField jTxtFldKD_CTGRY;
    private javax.swing.JTextField jTxtFldKD_CTGRY2;
    private javax.swing.JTextField jTxtFldKD_DEPT;
    private javax.swing.JTextField jTxtFldKD_DEPT2;
    private javax.swing.JTextField jTxtFldKD_OTLT;
    private javax.swing.JTextField jTxtFldKD_OTLT2;
    private javax.swing.JTextField jTxtFldKD_PRD;
    private javax.swing.JTextField jTxtFldKD_PRD2;
    private javax.swing.JTextField jTxtFldKD_SBS;
    private javax.swing.JTextField jTxtFldKD_SBS2;
    private javax.swing.JTextField jTxtFldKD_SPLR;
    private javax.swing.JTextField jTxtFldKD_SPLR2;
    private javax.swing.JTextField jTxtFldNM_CTGRY;
    private javax.swing.JTextField jTxtFldNM_DEPT;
    private javax.swing.JTextField jTxtFldNM_OTLT;
    private javax.swing.JTextField jTxtFldNM_PRD;
    private javax.swing.JTextField jTxtFldNM_PRD2;
    private javax.swing.JTextField jTxtFldNM_SBS;
    private javax.swing.JTextField jTxtFldNM_SPLR;
    private javax.swing.JTextField jTxtFldUKURAN;
    private javax.swing.JLabel jlblDEPT;
    private javax.swing.JLabel jlblHRG_JL;
    private javax.swing.JLabel jlblKD_CTGRY;
    private javax.swing.JLabel jlblKD_OTLT;
    private javax.swing.JLabel jlblKD_PRD;
    private javax.swing.JLabel jlblKD_SBS;
    private javax.swing.JLabel jlblKD_SPLR;
    private javax.swing.JLabel jlblNM_PRD;
    private javax.swing.JLabel jlblUKURAN;
    private javax.swing.JLabel jlblxDEPT;
    private javax.swing.JLabel jlblxHRG_JL;
    private javax.swing.JLabel jlblxHRG_PK;
    private javax.swing.JLabel jlblxKD_CTGRY;
    private javax.swing.JLabel jlblxKD_OTLT;
    private javax.swing.JLabel jlblxKD_PRD;
    private javax.swing.JLabel jlblxKD_SBS;
    private javax.swing.JLabel jlblxNIK;
    private javax.swing.JLabel jlblxNM_PRD;
    private javax.swing.JLabel jlblxUKURAN;
    // End of variables declaration//GEN-END:variables
}
