package com.pengadaan.barang;

import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.util.Date;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


public class TRN_IN extends javax.swing.JFrame {

    private Integer row;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable(), jTabel2 = new JTable(), jTabel3 = new JTable(), jTabel4 = new JTable(), jTabel5 = new JTable();
    private DefaultTableModel DfltTblMode, DfltTblMode2;
    private TableColumn column; 
    private String e,r,i,k,a,s,p,t,x,y,z,b,c,d,f,hasil,fieldTemp;
    private int nil1,nil2,nil3,varVAL,nextVAL;
    private int vnNO;

    private JasperReport jasperReport;
    private JasperPrint jasperPrint;
    private Map<String, Object> param = new HashMap<String, Object>();
    private JasperDesign jasperDesign;
           
    
    public TRN_IN() {
        initComponents();
        setLocationRelativeTo(null);
        aplikasiInventory.konekkeDatabase();     
        tampilDataKeTabel();    
        enableBtn(false);
        enviBtnSave(true);
        enviBtnNew(false);
        jTxtFldKD_PRD.setEnabled(false);
        jTxtFldNM_PRD.setEnabled(false);
        jTxtFldKD_PRD2.setVisible(false);
        jTxtFldFilter.setVisible(false);
        jTxtFldAsal.setVisible(false);
        jTxtFldQTY_IN2.setVisible(false);
        jTxtFldNO_TRN_OUT2.setVisible(false);
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jBtnCari.setEnabled(false);
        jBtnCari1.setEnabled(false);
        jTxtFldNO_TRN_OUT.setEnabled(false);
        jBtnNO_TRN_OUT.setEnabled(false);
        buatnotrn();
        date();   
        vnNO = 0;
        Object[] field2 = {"No","No Transaksi","Tanggal Transaksi","Asal Barang","Filter Produk Berdasarkan","Kode Produk","Nama Produk","Qty Awal","Qty Masuk","Total Qty","Harga Pokok","Harga Jual","No Transaksi Out"};
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
        jTxtFldQTY_IN.setText("");
        jlblQTY_AWAL1.setText("");
        jlblHRG_PK1.setText("");
        jlblHRG_JL1.setText("");
        jCmbFilter.setSelectedItem("");
        jCmbAsal.setSelectedItem("");
        jTextField1.setText("");
        jTxtFldNO_TRN_OUT.setText("");
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
        jTxtFldQTY_IN.setEnabled(x);
        jCmbFilter.setEnabled(x);
        jCmbAsal.setEnabled(x);
        jBtnPRD.setEnabled(x);
        jBtnNO_TRN_OUT.setEnabled(x);
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
    
    private void tampilDataKeTabelcari() {
        jTabel2 = jTable1;
        cari(jTabel2);
    }
    
    private void tampilDataKeTabelambil() {
        jTabel5 = jTable1;
        if (!jCmbAsal.getSelectedItem().equals("") && !jCmbFilter.getSelectedItem().equals("") && !jTxtFldKD_PRD.getText().equals("") && !jTxtFldQTY_IN.getText().equals("")) 
        {
           vnNO = vnNO + 1;
           ambilData(vnNO, jTabel5);
        }
        else {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
        }
    }
    
    private void tampilDataKeTabel2() {
        jTabel3 = jTable2;
        modeljDialogPRD(jTabel3);
    }
    
    private void tampilDataKeTabel3() {
        jTabel4 = jTable3;
        modeljDialogTRN_OUT(jTabel4);
    }
    
    private void cari(JTable jTabel2){
    try {
            Object[] field = {"No","No Transaksi","Tanggal Transaksi","Asal Barang","Filter Produk Berdasarkan","Kode Produk","Nama Produk","Qty Awal","Qty Masuk","Total Qty","Harga Pokok","Harga Jual","No Transaksi Out"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel2.setModel(DfltTblMode);
            
            if (jcb1.isSelected()) {
            String sql = "Select * from transaksi where (NO_TRN like '%" + jTextField1.getText() + "%'" +
                         "or TGL_TRN like '%" + jTextField1.getText() + "%') "+
                         "and QTY_IN != ''";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);
            
            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("NO_TRN");
                String kolom3 = set.getString("TGL_TRN");
                String kolom4 = set.getString("ASAL_BRG");
                String kolom5 = set.getString("FILTER");
                String kolom6 = set.getString("KD_PRD");
                String kolom7 = set.getString("NM_PRD");
                String kolom8 = set.getString("QTY_AWAL");
                String kolom9 = set.getString("QTY_IN");
                String kolom10 = set.getString("QTY_AKHIR");
                String kolom11 = set.getString("HRG_PK");
                String kolom12 = set.getString("HRG_JL");
                String kolom13 = set.getString("NO_TRN_OUT");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10, kolom11, kolom12, kolom13};
                DfltTblMode.addRow(data);
            }
            }
            else{                
            String sql = "Select * from transaksi where (TGL_TRN between '"+ jTextField2.getText() +"' "+ 
                         "and '"+ jTextField3.getText() +"') "+
                         "and QTY_IN != ''";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);
            
            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("NO_TRN");
                String kolom3 = set.getString("TGL_TRN");
                String kolom4 = set.getString("ASAL_BRG");
                String kolom5 = set.getString("FILTER");
                String kolom6 = set.getString("KD_PRD");
                String kolom7 = set.getString("NM_PRD");
                String kolom8 = set.getString("QTY_AWAL");
                String kolom9 = set.getString("QTY_IN");
                String kolom10 = set.getString("QTY_AKHIR");
                String kolom11 = set.getString("HRG_PK");
                String kolom12 = set.getString("HRG_JL");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10, kolom11, kolom12};
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
    
    private void modeljDialogTRN_OUT(JTable jTabel4) {
        try {
            Object[] field = {"No","No Transaksi","Tanggal Transaksi","Status Keluar Barang","Filter Produk Berdasarkan","Kode Produk","Nama Produk","Total Qty","Harga Pokok","Harga Jual"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel4.setModel(DfltTblMode);
            
            String sql = "Select * from transaksi where STS_BRG = 'PINJAM'";
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
                String kolom8 = set.getString("QTY_AKHIR");
                String kolom9 = set.getString("HRG_PK");
                String kolom10 = set.getString("HRG_JL");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10};
                DfltTblMode.addRow(data);
            }
            
            jTabel4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel4.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel4.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(2);
            column.setPreferredWidth(150);
            column = jTabel4.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(4);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel4.getColumnModel().getColumn(5);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(6);
            column.setPreferredWidth(155);
            column = jTabel4.getColumnModel().getColumn(7);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel4.getColumnModel().getColumn(8);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel4.getColumnModel().getColumn(9);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialogTRN_OUT(JTable jTabel) {
        jTabel4.setRowSelectionAllowed(true);
        row = jTabel4.getSelectedRow();
        String kolom1 = jTabel4.getValueAt(row,0).toString();
        String kolom2 = jTabel4.getValueAt(row,1).toString();
        String kolom3 = jTabel4.getValueAt(row,4).toString();
        String kolom4 = jTabel4.getValueAt(row,5).toString();
        String kolom5 = jTabel4.getValueAt(row,6).toString();
        String kolom6 = jTabel4.getValueAt(row,7).toString();
        String kolom7 = jTabel4.getValueAt(row,8).toString();
        String kolom8 = jTabel4.getValueAt(row,9).toString();
        jTxtFldNO_TRN_OUT.setText(kolom2);
        jCmbFilter.setSelectedItem(kolom3);
        jTxtFldKD_PRD.setText(kolom4);
        jTxtFldNM_PRD.setText(kolom5);
        jlblQTY_AWAL1.setText(kolom6);
        jlblHRG_PK1.setText(kolom7);
        jlblHRG_JL1.setText(kolom8);
    }
    
    private void tabelModel(JTable jTabel) {
        try {
            Object[] field = {"No","No Transaksi","Tanggal Transaksi","Asal Barang","Filter Produk Berdasarkan","Kode Produk","Nama Produk","Qty Awal","Qty Masuk","Total Qty","Harga Pokok","Harga Jual","No Transaksi Out"};
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
        jlblNO_TRN1.setText(kolom2);
        jlblTGL_TRN.setText(kolom3);
        jCmbAsal.setSelectedItem(kolom4);
        jCmbFilter.setSelectedItem(kolom5);
        jTxtFldKD_PRD.setText(kolom6);
        jTxtFldNM_PRD.setText(kolom7); 
        jlblQTY_AWAL1.setText(kolom8);
        jTxtFldQTY_IN.setText(kolom9);
        jlblTTL_QTY1.setText(kolom10);
        jlblHRG_PK1.setText(kolom11);
        jlblHRG_JL1.setText(kolom12);
        jTxtFldNO_TRN_OUT.setText(kolom13);
        
        jTxtFldAsal.setText(kolom4);
        jTxtFldFilter.setText(kolom5);
        jTxtFldKD_PRD2.setText(kolom6);        
        jTxtFldQTY_IN2.setText(kolom9);
        jTxtFldNO_TRN_OUT2.setText(kolom13);
    }
        
    private void kondisiSave() {        
    int jmlBaris, jmlKolom;
    String vsQry = null;
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
                         data[3] + "','" +
                         data[7] + "','" +
                         data[6] + "','" +
                         data[8] + "','" +
                         data[9] + "','" +
                         data[10]+ "','','','"+nextVAL+"','0','" +
                         data[2] + "','','" +
                         data[11]+ "','','','','','',''" +
                         ")";
                 
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
    
    private void ambilData(int no, JTable jTabel5) {
    try {
            
            jTabel5.setModel(DfltTblMode2);            
                       
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = jlblNO_TRN1.getText();
                String kolom3 = jlblTGL_TRN.getText();
                String kolom4 = jCmbAsal.getSelectedItem().toString();
                String kolom5 = jCmbFilter.getSelectedItem().toString();
                String kolom6 = jTxtFldKD_PRD.getText();
                String kolom7 = jTxtFldNM_PRD.getText();
                String kolom8 = jlblQTY_AWAL1.getText();
                String kolom9 = jlblTTL_QTY1.getText();
                String kolom10 = jTxtFldQTY_IN.getText();
                String kolom11 = jlblHRG_PK1.getText();
                String kolom12 = jlblHRG_JL1.getText();
                String kolom13 = jTxtFldNO_TRN_OUT.getText();
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10, kolom11, kolom12, kolom13};
                DfltTblMode2.addRow(data);
                
            jTabel5.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel5.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel5.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel5.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            column = jTabel5.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel5.getColumnModel().getColumn(4);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            column = jTabel5.getColumnModel().getColumn(5);
            column.setPreferredWidth(80);
            column = jTabel5.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel5.getColumnModel().getColumn(7);
            column.setPreferredWidth(70);
            column = jTabel5.getColumnModel().getColumn(8);
            column.setPreferredWidth(70);
            column = jTabel5.getColumnModel().getColumn(9);
            column.setPreferredWidth(70);
            column = jTabel5.getColumnModel().getColumn(10);
            column.setPreferredWidth(100);
            column = jTabel5.getColumnModel().getColumn(11);
            column.setPreferredWidth(100);
            column = jTabel5.getColumnModel().getColumn(12);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
            
          }
          catch (Exception ex) {
              JOptionPane.showMessageDialog(this, "Data gagal disimpan! : " +ex);
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
            
    private void jumlah (){
        nil1 = Integer.valueOf(jTxtFldQTY_IN.getText());
        nil2 = Integer.valueOf(jlblQTY_AWAL1.getText());
        nil3 = nil1 + nil2 ;
        hasil= String.valueOf(nil3);
        jlblTTL_QTY1.setText(hasil);
    }
    
    private void buatnotrn(){       
        try{
           Statement st = aplikasiInventory.config.getConnection().createStatement();        
           String sql = "Select NO_TRN,NOTRNIN from transaksi";
           ResultSet set = st.executeQuery(sql);
           
           if (!set.next()) {
             jlblNO_TRN1.setText("IN1");  
             nextVAL = 1;
           }
           else
           {
             String sql2 = "Select max(NOTRNIN) as no_trn from transaksi";
             ResultSet set2 = st.executeQuery(sql2);
             
             if (set2.next()) {
                varVAL = set2.getInt("no_trn"); 
                nextVAL = varVAL + 1;
                jlblNO_TRN1.setText("IN"+Integer.toString(nextVAL));
             }
           }
        } catch (Exception ex){}
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
        jDialogNO_TRN_OUT = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jBtnKembali1 = new javax.swing.JButton();
        jBtnOk1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTxtFldKD_PRD2 = new javax.swing.JTextField();
        jTxtFldFilter = new javax.swing.JTextField();
        jlblFilter = new javax.swing.JLabel();
        jCmbFilter = new javax.swing.JComboBox();
        jlblTGL_TRN = new javax.swing.JLabel();
        jlblNO_TRN = new javax.swing.JLabel();
        jlblxNO_TRN_OUT = new javax.swing.JLabel();
        jlblNO_TRN_OUT = new javax.swing.JLabel();
        jTxtFldAsal = new javax.swing.JTextField();
        jlblxAsal = new javax.swing.JLabel();
        jCmbAsal = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jlblxTGL_TRN = new javax.swing.JLabel();
        jlblxFilter = new javax.swing.JLabel();
        jlblxNO_TRN = new javax.swing.JLabel();
        jTxtFldNO_TRN_OUT2 = new javax.swing.JTextField();
        jTxtFldNO_TRN_OUT = new javax.swing.JTextField();
        jBtnNO_TRN_OUT = new javax.swing.JButton();
        jlblNO_TRN1 = new javax.swing.JLabel();
        jlblAsal = new javax.swing.JLabel();
        jlblTTL_QTY1 = new javax.swing.JLabel();
        jTxtFldKD_PRD = new javax.swing.JTextField();
        jBtnPRD = new javax.swing.JButton();
        jlblQTY_IN = new javax.swing.JLabel();
        jlblQTY_AWAL = new javax.swing.JLabel();
        jTxtFldQTY_IN = new javax.swing.JTextField();
        jlblQTY_AWAL1 = new javax.swing.JLabel();
        jlblxQTY_AWAL = new javax.swing.JLabel();
        jlblxKD_PRD = new javax.swing.JLabel();
        jlblTTL_QTY = new javax.swing.JLabel();
        jlblxTTL_QTY = new javax.swing.JLabel();
        jlblxQTY_IN = new javax.swing.JLabel();
        jTxtFldNM_PRD = new javax.swing.JTextField();
        jTxtFldQTY_IN2 = new javax.swing.JTextField();
        jlblKD_PRD = new javax.swing.JLabel();
        jlblxHRG_PK = new javax.swing.JLabel();
        jlblHRG_PK1 = new javax.swing.JLabel();
        jlblHRG_PK = new javax.swing.JLabel();
        jlblHRG_JL1 = new javax.swing.JLabel();
        jlblHRG_JL = new javax.swing.JLabel();
        jlblxHRG_JL = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBtnAmbil = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jBtnCari = new javax.swing.JButton();
        jBtnCari1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jcb1 = new javax.swing.JCheckBox();
        jcb2 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBtnSave = new javax.swing.JButton();
        jBtnNew = new javax.swing.JButton();
        jBtnLap = new javax.swing.JButton();
        jBtnDlt1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOk)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOk)
                    .addComponent(jBtnKembali))
                .addContainerGap())
        );

        jDialogNO_TRN_OUT.setMinimumSize(new java.awt.Dimension(655, 600));
        jDialogNO_TRN_OUT.setModal(true);
        jDialogNO_TRN_OUT.setResizable(false);

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

        jBtnKembali1.setText("Kembali");
        jBtnKembali1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnKembali1ActionPerformed(evt);
            }
        });

        jBtnOk1.setText("Ok");
        jBtnOk1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOk1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogNO_TRN_OUTLayout = new javax.swing.GroupLayout(jDialogNO_TRN_OUT.getContentPane());
        jDialogNO_TRN_OUT.getContentPane().setLayout(jDialogNO_TRN_OUTLayout);
        jDialogNO_TRN_OUTLayout.setHorizontalGroup(
            jDialogNO_TRN_OUTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogNO_TRN_OUTLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembali1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOk1)
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );
        jDialogNO_TRN_OUTLayout.setVerticalGroup(
            jDialogNO_TRN_OUTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNO_TRN_OUTLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jDialogNO_TRN_OUTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOk1)
                    .addComponent(jBtnKembali1))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form Transaksi In");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Transaksi In"));

        jlblFilter.setText("Filter Produk Berdasarkan*");

        jCmbFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Kategori", "SBS", "Suplier" }));
        jCmbFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbFilterItemStateChanged(evt);
            }
        });

        jlblNO_TRN.setText("No. Transaksi");

        jlblxNO_TRN_OUT.setText(":");

        jlblNO_TRN_OUT.setText("No Transaksi Out");

        jlblxAsal.setText(":");

        jCmbAsal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Beli", "Retur", "Pinjam" }));
        jCmbAsal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbAsalItemStateChanged(evt);
            }
        });

        jLabel1.setText(":");

        jlblxTGL_TRN.setText("Tgl Transaksi");

        jlblxFilter.setText(":");

        jlblxNO_TRN.setText(":");

        jBtnNO_TRN_OUT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnNO_TRN_OUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNO_TRN_OUTActionPerformed(evt);
            }
        });

        jlblAsal.setText("Asal Barang*");

        jBtnPRD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnPRD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPRDActionPerformed(evt);
            }
        });

        jlblQTY_IN.setText("Qty Masuk*");

        jlblQTY_AWAL.setText("Qty Awal");

        jTxtFldQTY_IN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtFldQTY_INKeyReleased(evt);
            }
        });

        jlblxQTY_AWAL.setText(":");
        jlblxQTY_AWAL.setToolTipText("");

        jlblxKD_PRD.setText(":");

        jlblTTL_QTY.setText("Total Qty");

        jlblxTTL_QTY.setText(":");

        jlblxQTY_IN.setText(":");

        jlblKD_PRD.setText("Produk*");

        jlblxHRG_PK.setText(":");

        jlblHRG_PK.setText("Harga Pokok");

        jlblHRG_JL.setText("Harga Jual");

        jlblxHRG_JL.setText(":");

        jLabel4.setText("Rp.");

        jLabel3.setText("Rp.");

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
                    .addComponent(jlblAsal)
                    .addComponent(jlblNO_TRN)
                    .addComponent(jlblFilter)
                    .addComponent(jlblNO_TRN_OUT)
                    .addComponent(jlblQTY_AWAL)
                    .addComponent(jlblQTY_IN)
                    .addComponent(jlblKD_PRD))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblxNO_TRN)
                    .addComponent(jlblxAsal)
                    .addComponent(jlblxNO_TRN_OUT)
                    .addComponent(jlblxFilter)
                    .addComponent(jlblxKD_PRD)
                    .addComponent(jlblxQTY_IN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlblNO_TRN1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCmbAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFldNO_TRN_OUT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCmbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFldKD_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFldQTY_IN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlblxTGL_TRN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblTGL_TRN, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtFldAsal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTxtFldFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldKD_PRD2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBtnNO_TRN_OUT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldNO_TRN_OUT2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTxtFldQTY_IN2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTxtFldNM_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnPRD, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnAmbil))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jlblHRG_PK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblxHRG_PK)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(8, 8, 8)
                                .addComponent(jlblHRG_PK1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jlblHRG_JL)
                                .addGap(18, 18, 18)
                                .addComponent(jlblxHRG_JL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(3, 3, 3)
                                .addComponent(jlblHRG_JL1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jlblxQTY_AWAL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblQTY_AWAL1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlblTTL_QTY)
                                .addGap(27, 27, 27)
                                .addComponent(jlblxTTL_QTY)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlblTTL_QTY1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblxTGL_TRN)
                            .addComponent(jLabel1))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblNO_TRN)
                            .addComponent(jlblxNO_TRN)))
                    .addComponent(jlblTGL_TRN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblNO_TRN1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCmbAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblxAsal)
                    .addComponent(jlblAsal)
                    .addComponent(jTxtFldAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtFldNO_TRN_OUT)
                        .addComponent(jlblxNO_TRN_OUT)
                        .addComponent(jlblNO_TRN_OUT))
                    .addComponent(jBtnNO_TRN_OUT, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFldNO_TRN_OUT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblFilter)
                    .addComponent(jCmbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblxFilter)
                    .addComponent(jTxtFldFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFldKD_PRD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblKD_PRD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlblQTY_IN)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlblxKD_PRD)
                                    .addComponent(jTxtFldKD_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtFldNM_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jlblxQTY_IN)
                                    .addComponent(jTxtFldQTY_IN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtFldQTY_IN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jBtnPRD, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblTTL_QTY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblxTTL_QTY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblQTY_AWAL1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblQTY_AWAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlblxQTY_AWAL))
                    .addComponent(jlblTTL_QTY1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlblHRG_PK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblxHRG_PK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblHRG_JL1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblHRG_JL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblxHRG_JL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlblHRG_PK1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnAmbil))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabel Transaksi In"));

        jLabel2.setText("Cari Data Berdasarkan No / Tgl Trn :");

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

        jBtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCari1ActionPerformed(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel5.setText("Cari Data Berdasarkan Periode :");

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jcb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb1ActionPerformed(evt);
            }
        });

        jcb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb2ActionPerformed(evt);
            }
        });

        jLabel6.setText("s.d");

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

        jBtnLap.setText("Laporan");
        jBtnLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLapActionPerformed(evt);
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBtnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnDlt1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnLap))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel6)
                                .addGap(6, 6, 6)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcb2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcb1)))))
                .addGap(0, 77, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcb1)
                    .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcb2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnCari1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnLap)
                            .addComponent(jBtnDlt1))
                        .addComponent(jBtnSave))
                    .addComponent(jBtnNew, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jBtnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNewActionPerformed
        // TODO add your handling code here:
       clearTEXT();
       buatnotrn();
       enableField(true);
       enableBtn(false);
       enviBtnSave(true);
       enviBtnNew(false); 
       jBtnNO_TRN_OUT.setEnabled(false);
    }//GEN-LAST:event_jBtnNewActionPerformed

    private void jBtnPRDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPRDActionPerformed
        // TODO add your handling code here:
        jDialog1.setLocationRelativeTo(null);
        tampilDataKeTabel2();
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jBtnPRDActionPerformed

    private void jBtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCariActionPerformed
        // TODO add your handling code here:
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

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
       klikTabel(jTabel);
       enableField(false);
       enableBtn(true);
       enviBtnSave(false);
       enviBtnNew(true);
    }//GEN-LAST:event_jTable1MousePressed

    private void jTxtFldQTY_INKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtFldQTY_INKeyReleased
        // TODO add your handling code here:
        if (!jTxtFldQTY_IN.getText().equals("")){
           jumlah();    
           evt.consume();
        }
        else {
           jlblTTL_QTY1.setText("");
        }
    }//GEN-LAST:event_jTxtFldQTY_INKeyReleased

    private void jCmbFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbFilterItemStateChanged
        // TODO add your handling code here:
        if (jCmbFilter.getSelectedItem() == "Kategori") {
           fieldTemp = "KD_CTGRY";
        }
        else if (jCmbFilter.getSelectedItem() == "Suplier") {
           fieldTemp = "KD_SPLR";
        }
        else if (jCmbFilter.getSelectedItem() == "SBS") {
           fieldTemp = "KD_SBS";
        }
        jTxtFldKD_PRD.setText("");
        jTxtFldNM_PRD.setText("");
        jlblTTL_QTY1.setText("");
        jlblQTY_AWAL1.setText("");
        jTxtFldQTY_IN.setText("");
        jlblHRG_PK1.setText("");
        jlblHRG_JL1.setText("");
    }//GEN-LAST:event_jCmbFilterItemStateChanged

    private void jBtnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliActionPerformed
        // TODO add your handling code here:
        jDialog1.dispose();
    }//GEN-LAST:event_jBtnKembaliActionPerformed

    private void jBtnLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLapActionPerformed
        // TODO add your handling code here:
        try {
            File file = new File("src/laporan/lapin.jrxml");
            jasperDesign = JRXmlLoader.load(file);            
            param.clear();
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, param, aplikasiInventory.config.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jBtnLapActionPerformed

    private void jBtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCari1ActionPerformed
        // TODO add your handling code here:
        tampilDataKeTabelcari();
    }//GEN-LAST:event_jBtnCari1ActionPerformed

    private void jcb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb1ActionPerformed
        // TODO add your handling code here:
        if (jcb1.isSelected()){
           enablejcb(true);
           jBtnDlt1.setVisible(false);
        }
        else {
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

    private void jcb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb2ActionPerformed
        // TODO add your handling code here:
        if (jcb2.isSelected()){
           enablejcb(false);
           jBtnDlt1.setVisible(false);
        }
        else {
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        aplikasiInventory.Start.setVisible(true);
        aplikasiInventory.loadNIK(aplikasiInventory.Start.jlblstart_jpg);
        aplikasiInventory.vsNIK = aplikasiInventory.Start.jlblstart_jpg.getText();
        aplikasiInventory.HakAkses();
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jBtnNO_TRN_OUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNO_TRN_OUTActionPerformed
        // TODO add your handling code here:
        jDialogNO_TRN_OUT.setLocationRelativeTo(null);
        tampilDataKeTabel3();
        jDialogNO_TRN_OUT.setVisible(true);
    }//GEN-LAST:event_jBtnNO_TRN_OUTActionPerformed

    private void jBtnKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembali1ActionPerformed
        // TODO add your handling code here:
        jDialogNO_TRN_OUT.dispose();     
    }//GEN-LAST:event_jBtnKembali1ActionPerformed

    private void jBtnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOk1ActionPerformed
        // TODO add your handling code here:
        if (jTabel4.getSelectedRow() != -1){
            klikTabeljDialogTRN_OUT(jTabel4);
            jDialogNO_TRN_OUT.dispose();     
         }
         else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
         }
    }//GEN-LAST:event_jBtnOk1ActionPerformed

    private void jCmbAsalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbAsalItemStateChanged
        // TODO add your handling code here:
        if (jCmbAsal.getSelectedItem() == "Pinjam") {
           jBtnNO_TRN_OUT.setEnabled(true);
           jBtnPRD.setEnabled(false);
           jCmbFilter.setEnabled(false);
        }
        else {
           jBtnNO_TRN_OUT.setEnabled(false);
           jBtnPRD.setEnabled(true);
           jCmbFilter.setEnabled(true);
           jTxtFldKD_PRD.setText("");
           jTxtFldNM_PRD.setText("");
           jlblTTL_QTY1.setText("");
           jlblQTY_AWAL1.setText("");
           jTxtFldQTY_IN.setText("");
           jlblHRG_PK1.setText("");
           jlblHRG_JL1.setText("");
           jTxtFldNO_TRN_OUT.setText("");
           jCmbFilter.setSelectedItem("");
        }
    }//GEN-LAST:event_jCmbAsalItemStateChanged

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar() == KeyEvent.VK_ENTER) {
          jBtnCari.doClick();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            jTextField3.requestFocus();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar() == KeyEvent.VK_ENTER) {
          jBtnCari1.doClick();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jBtnAmbilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAmbilActionPerformed
        // TODO add your handling code here:
        tampilDataKeTabelambil();
        jTxtFldKD_PRD.setText("");
        jTxtFldNM_PRD.setText("");
        jlblTTL_QTY1.setText("");
        jTxtFldQTY_IN.setText("");
        jlblQTY_AWAL1.setText("");
        jlblHRG_PK1.setText("");
        jlblHRG_JL1.setText("");
        jCmbFilter.setSelectedItem("");
        jCmbAsal.setSelectedItem("");
        jTextField1.setText("");
        jTxtFldNO_TRN_OUT.setText("");
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
            java.util.logging.Logger.getLogger(TRN_IN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TRN_IN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TRN_IN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TRN_IN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TRN_IN().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAmbil;
    private javax.swing.JButton jBtnCari;
    private javax.swing.JButton jBtnCari1;
    public javax.swing.JButton jBtnDlt1;
    private javax.swing.JButton jBtnKembali;
    private javax.swing.JButton jBtnKembali1;
    private javax.swing.JButton jBtnLap;
    private javax.swing.JButton jBtnNO_TRN_OUT;
    public javax.swing.JButton jBtnNew;
    private javax.swing.JButton jBtnOk;
    private javax.swing.JButton jBtnOk1;
    private javax.swing.JButton jBtnPRD;
    public javax.swing.JButton jBtnSave;
    private javax.swing.JComboBox jCmbAsal;
    private javax.swing.JComboBox jCmbFilter;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialogNO_TRN_OUT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTxtFldAsal;
    private javax.swing.JTextField jTxtFldFilter;
    private javax.swing.JTextField jTxtFldKD_PRD;
    private javax.swing.JTextField jTxtFldKD_PRD2;
    private javax.swing.JTextField jTxtFldNM_PRD;
    private javax.swing.JTextField jTxtFldNO_TRN_OUT;
    private javax.swing.JTextField jTxtFldNO_TRN_OUT2;
    private javax.swing.JTextField jTxtFldQTY_IN;
    private javax.swing.JTextField jTxtFldQTY_IN2;
    private javax.swing.JCheckBox jcb1;
    private javax.swing.JCheckBox jcb2;
    private javax.swing.JLabel jlblAsal;
    private javax.swing.JLabel jlblFilter;
    private javax.swing.JLabel jlblHRG_JL;
    private javax.swing.JLabel jlblHRG_JL1;
    private javax.swing.JLabel jlblHRG_PK;
    private javax.swing.JLabel jlblHRG_PK1;
    private javax.swing.JLabel jlblKD_PRD;
    private javax.swing.JLabel jlblNO_TRN;
    private javax.swing.JLabel jlblNO_TRN1;
    private javax.swing.JLabel jlblNO_TRN_OUT;
    private javax.swing.JLabel jlblQTY_AWAL;
    private javax.swing.JLabel jlblQTY_AWAL1;
    private javax.swing.JLabel jlblQTY_IN;
    private javax.swing.JLabel jlblTGL_TRN;
    private javax.swing.JLabel jlblTTL_QTY;
    private javax.swing.JLabel jlblTTL_QTY1;
    private javax.swing.JLabel jlblxAsal;
    private javax.swing.JLabel jlblxFilter;
    private javax.swing.JLabel jlblxHRG_JL;
    private javax.swing.JLabel jlblxHRG_PK;
    private javax.swing.JLabel jlblxKD_PRD;
    private javax.swing.JLabel jlblxNO_TRN;
    private javax.swing.JLabel jlblxNO_TRN_OUT;
    private javax.swing.JLabel jlblxQTY_AWAL;
    private javax.swing.JLabel jlblxQTY_IN;
    private javax.swing.JLabel jlblxTGL_TRN;
    private javax.swing.JLabel jlblxTTL_QTY;
    // End of variables declaration//GEN-END:variables
}
