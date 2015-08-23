package com.pengadaan.barang;

import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class populasi extends javax.swing.JFrame {
    
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable(), jTabel2 = new JTable(), jTabel3 = new JTable(), jTabel4 = new JTable();
    private DefaultTableModel DfltTblMode;
    private TableColumn column; 
    private Integer row;
    private String e,r,i,k,a,s,p,t,x,y;    

    public populasi() {
        initComponents(); 
        aplikasiInventory.konekkeDatabase(); 
        tampilDataKeTabel(); 
        enableBtn(false);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(false);
        jTxtFldKD_BRNCH.setEnabled(false);
        jTxtFldKD_OTLT.setEnabled(false);
        jTxtFldKD_PRD.setEnabled(false);
        jTxtFldNM_BRNCH.setEnabled(false);
        jTxtFldNM_OTLT.setEnabled(false);
        jTxtFldNM_PRD.setEnabled(false);
        jTxtFldPIC.setEnabled(false);
        jTxtFldKD_BRNCH2.setVisible(false);
        jTxtFldKD_PRD2.setVisible(false);
        jTxtFldSTS.setVisible(false);
    }

    private void clearTEXT() {
        jTxtFldKD_BRNCH.setText("");
        jTxtFldNM_BRNCH.setText("");
        jTxtFldKD_OTLT.setText("");
        jTxtFldNM_OTLT.setText("");
        jTxtFldKD_PRD.setText("");
        jTxtFldNM_PRD.setText("");
        jCmbSTS.setSelectedItem("");
        jTxtFldPIC.setText("");        
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
        jCmbSTS.setEnabled(x);
        jBtnSrch.setEnabled(x);
        jBtnPRD.setEnabled(x);
    }
    
    private void tampilDataKeTabel() {
        jTabel2 = jTable1;
        tabelModel(jTabel2);
    }
    
    private void tampilDataKeTabel2() {
        jTabel = jTblBRNCH;
        modeljdialog(jTabel);
    }
    
    private void tampilDataKeTabel3() {
        jTabel4 = jTblPRD;
        modeljDialogPRD(jTabel4);
    }
    
    private void tampilDataKeTabelcari() {
        jTabel3 = jTable1;
        cari(jTabel3);
    }
    
    private void modeljdialog(JTable jTabel) {
        try {
            Object[] field = {"No","Kode Cabang","Nama Cabang","Kode Otlet","Nama Otlet","PIC"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);
            
            String sql = "Select * from outlet";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_BRNCH");
                String kolom3 = set.getString("NM_BRNCH");
                String kolom4 = set.getString("KD_OTLT");
                String kolom5 = set.getString("NM_OTLT");
                String kolom6 = set.getString("PIC");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6};
                DfltTblMode.addRow(data);
            }
            
            jTabel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(2);
            column.setPreferredWidth(150);
            column = jTabel.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(4);
            column.setPreferredWidth(150);
            column = jTabel.getColumnModel().getColumn(5);
            column.setPreferredWidth(150);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialog(JTable jTabel) {
        jTabel.setRowSelectionAllowed(true);
        row = jTabel.getSelectedRow();
        String kolom1 = jTabel.getValueAt(row,0).toString();
        String kolom2 = jTabel.getValueAt(row,1).toString();
        String kolom3 = jTabel.getValueAt(row,2).toString();
        String kolom4 = jTabel.getValueAt(row,3).toString();
        String kolom5 = jTabel.getValueAt(row,4).toString();
        String kolom6 = jTabel.getValueAt(row,5).toString();
        jTxtFldKD_BRNCH.setText(kolom2);
        jTxtFldNM_BRNCH.setText(kolom3); 
        jTxtFldKD_OTLT.setText(kolom4);
        jTxtFldNM_OTLT.setText(kolom5);
        jTxtFldPIC.setText(kolom6);
    }
    
    private void modeljDialogPRD(JTable jTabel4) {
        try {
            Object[] field = {"No","Kode Produk","Nama Produk","Kode Kategori","Nama Kategori","Kode SBS","Nama SBS ","Kode Suplier","Nama Suplier","Kode Otlet","Nama Otlet","Qty Awal","Harga Pokok","Harga Jual"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel4.setModel(DfltTblMode);
            
            String sql = "Select * from produk where NM_CTGRY='Alat'";
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
            
            jTabel4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel4.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel4.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(4);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(5);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(7);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(8);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(9);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(10);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(11);
            column.setPreferredWidth(70);
            column = jTabel4.getColumnModel().getColumn(12);
            column.setPreferredWidth(100);
            column = jTabel4.getColumnModel().getColumn(13);
            column.setPreferredWidth(100);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabeljDialogPRD(JTable jTabel4) {
        jTabel4.setRowSelectionAllowed(true);
        row = jTabel4.getSelectedRow();
        String kolom1 = jTabel4.getValueAt(row,0).toString();
        String kolom2 = jTabel4.getValueAt(row,1).toString();
        String kolom3 = jTabel4.getValueAt(row,2).toString();
        String kolom12 = jTabel4.getValueAt(row,11).toString();
        String kolom13 = jTabel4.getValueAt(row,12).toString();
        String kolom14 = jTabel4.getValueAt(row,13).toString();
        jTxtFldKD_PRD.setText(kolom2);
        jTxtFldNM_PRD.setText(kolom3);
    }
    
    private void kondisiSave() { 
       e = jTxtFldKD_BRNCH.getText();
       r = jTxtFldNM_BRNCH.getText();
       i = jTxtFldKD_OTLT.getText();
       k = jTxtFldNM_OTLT.getText();
       a = jTxtFldPIC.getText();
       s = jTxtFldKD_PRD.getText();
       p = jTxtFldNM_PRD.getText();
              
       try {
       if (e.equals("") || jCmbSTS.getSelectedItem().equals("") || s.equals("")) 
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
           clearTEXT();
           }
       else {    
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "insert into populasi"+
                       "(KD_BRNCH, NM_BRNCH, KD_OTLT, NM_OTLT, PIC, STS_ALAT, KD_PRD, NM_PRD) values "+
                         "('"+ e +"','"+ r +"','"+ i +"','"+ k +"','"+ a +"','"+ jCmbSTS.getSelectedItem() +"','"+ s +"','"+ p +"')" );
                 
                 tampilDataKeTabel();      
                 JOptionPane.showMessageDialog(this,"Data berhasil disimpan");
               }
           }
                    catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Data gagal disimpan! : " +ex);
                    }
               }
    
    private void kondisiEdit() { 
       e = jTxtFldKD_BRNCH.getText();
       r = jTxtFldNM_BRNCH.getText();
       i = jTxtFldKD_OTLT.getText();
       k = jTxtFldNM_OTLT.getText();
       a = jTxtFldPIC.getText();
       s = jTxtFldKD_BRNCH2.getText();
       p = jTxtFldSTS.getText();
       t = jTxtFldKD_PRD.getText();
       x = jTxtFldNM_PRD.getText();
       y = jTxtFldKD_PRD2.getText();
       
       try {
       if (e.equals("") || jCmbSTS.getSelectedItem().equals("") || t.equals("")) 
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
           clearTEXT();
           }
       else if (e.equals(s) && jCmbSTS.getSelectedItem().equals(p) && t.equals(y)) 
           {
           JOptionPane.showMessageDialog(null, "Tidak ada data yang diperbaharui!");
           }
       else {    
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "update populasi set "+
                       "KD_BRNCH = "+"'"+ e +"', "+
                       "NM_BRNCH = "+"'"+ r +"', "+
                       "KD_OTLT = "+"'"+ i +"', "+
                       "NM_OTLT = "+"'"+ k +"', "+
                       "PIC = "+"'"+ a +"', "+
                       "KD_PRD = "+"'"+ t +"', "+
                       "NM_PRD = "+"'"+ x +"', "+
                       "STS_ALAT = "+"'"+ jCmbSTS.getSelectedItem() +"' "+                       
                       "where KD_BRNCH = '"+ s +"'");
       
                 tampilDataKeTabel(); 
                 JOptionPane.showMessageDialog(this,"Data berhasil diperbaharui");
               }
           }
                    catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Data gagal diperbaharui! : " +ex);
                    }
               }
    
    private void kondisiHapus() {
       e = jTxtFldKD_BRNCH.getText();
            
       try {
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            st.executeUpdate(
            " delete from populasi where KD_BRNCH ='"+ e +"'");
            clearTEXT();
            tampilDataKeTabel();
            
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
       }
        catch (SQLException ex) {
              JOptionPane.showMessageDialog(this,"Data gagal dihapus: " +ex);
        }
    }  
    
    private void tabelModel(JTable jTabel2) {
        try {
            Object[] field = {"No","Kode Cabang","Nama Cabang","Kode Otlet","Nama Otlet","PIC","Kode Produk","Nama Produk","Status Alat"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel2.setModel(DfltTblMode);
           
            String sql = "Select * from populasi";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);
            
            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_BRNCH");
                String kolom3 = set.getString("NM_BRNCH");
                String kolom4 = set.getString("KD_OTLT");
                String kolom5 = set.getString("NM_OTLT");
                String kolom6 = set.getString("PIC");
                String kolom7 = set.getString("KD_PRD");
                String kolom8 = set.getString("NM_PRD");
                String kolom9 = set.getString("STS_ALAT");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9};
                DfltTblMode.addRow(data);
            }
            
            jTabel2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel2.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel2.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(2);
            column.setPreferredWidth(150);
            column = jTabel2.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(4);
            column.setPreferredWidth(150);
            column = jTabel2.getColumnModel().getColumn(5);
            column.setPreferredWidth(150);
            column = jTabel2.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(7);
            column.setPreferredWidth(150);
            column = jTabel2.getColumnModel().getColumn(8);
            column.setPreferredWidth(150);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabel(JTable jTabel2) {
        jTabel2.setRowSelectionAllowed(true);
        row = jTabel2.getSelectedRow();
        String kolom1 = jTabel2.getValueAt(row,0).toString();
        String kolom2 = jTabel2.getValueAt(row,1).toString();
        String kolom3 = jTabel2.getValueAt(row,2).toString();
        String kolom4 = jTabel2.getValueAt(row,3).toString();
        String kolom5 = jTabel2.getValueAt(row,4).toString();
        String kolom6 = jTabel2.getValueAt(row,5).toString();
        String kolom7 = jTabel2.getValueAt(row,6).toString();        
        String kolom8 = jTabel2.getValueAt(row,7).toString();
        String kolom9 = jTabel2.getValueAt(row,8).toString();        
        jTxtFldKD_BRNCH.setText(kolom2);
        jTxtFldNM_BRNCH.setText(kolom3); 
        jTxtFldKD_OTLT.setText(kolom4); 
        jTxtFldNM_OTLT.setText(kolom5);
        jTxtFldPIC.setText(kolom6);
        jTxtFldKD_PRD.setText(kolom7);
        jTxtFldNM_PRD.setText(kolom8); 
        jCmbSTS.setSelectedItem(kolom9);
        
        jTxtFldKD_BRNCH2.setText(kolom2);
        jTxtFldKD_PRD2.setText(kolom7);
        jTxtFldSTS.setText(kolom9);
    }
    
    private void cari(JTable jTabel3){
    try {
            Object[] field = {"No","Kode Cabang","Nama Cabang","Kode Otlet","Nama Otlet","PIC","Kode Produk","Nama Produk","Status Alat"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel3.setModel(DfltTblMode);
            
            String sql = "Select * from populasi where NM_BRNCH like '%" + jTextField1.getText() + "%'" +
                         "or STS_ALAT like '%" + jTextField1.getText() + "%'";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("KD_BRNCH");
                String kolom3 = set.getString("NM_BRNCH");
                String kolom4 = set.getString("KD_OTLT");
                String kolom5 = set.getString("NM_OTLT");
                String kolom6 = set.getString("PIC");
                String kolom7 = set.getString("KD_PRD");
                String kolom8 = set.getString("NM_PRD");
                String kolom9 = set.getString("STS_ALAT");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9};
                DfltTblMode.addRow(data);
            }
            
            jTabel3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel2.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel2.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(2);
            column.setPreferredWidth(150);
            column = jTabel2.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(4);
            column.setPreferredWidth(150);
            column = jTabel2.getColumnModel().getColumn(5);
            column.setPreferredWidth(150);
            column = jTabel2.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(7);
            column.setPreferredWidth(150);
            column = jTabel2.getColumnModel().getColumn(8);
            column.setPreferredWidth(150);
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
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

        jDialogBRNCH = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblBRNCH = new javax.swing.JTable();
        jBtnOk = new javax.swing.JButton();
        jBtnKembali = new javax.swing.JButton();
        jDialogPRD = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTblPRD = new javax.swing.JTable();
        jBtnOk1 = new javax.swing.JButton();
        jBtnKembali1 = new javax.swing.JButton();
        jlblBRNCH = new javax.swing.JLabel();
        jlblxBRNCH = new javax.swing.JLabel();
        jlblOTLT = new javax.swing.JLabel();
        jlblxOTLT = new javax.swing.JLabel();
        jlblPIC = new javax.swing.JLabel();
        jlblxPIC = new javax.swing.JLabel();
        jlblSTS = new javax.swing.JLabel();
        jlblxSTS = new javax.swing.JLabel();
        jTxtFldKD_BRNCH = new javax.swing.JTextField();
        jTxtFldKD_OTLT = new javax.swing.JTextField();
        jTxtFldPIC = new javax.swing.JTextField();
        jTxtFldNM_BRNCH = new javax.swing.JTextField();
        jTxtFldNM_OTLT = new javax.swing.JTextField();
        jCmbSTS = new javax.swing.JComboBox();
        jBtnSrch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBtnNew = new javax.swing.JButton();
        jBtnSave = new javax.swing.JButton();
        jBtnSave2 = new javax.swing.JButton();
        jBtnEdit = new javax.swing.JButton();
        jBtnDlt = new javax.swing.JButton();
        jTxtFldKD_BRNCH2 = new javax.swing.JTextField();
        jTxtFldSTS = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jBtnCari = new javax.swing.JButton();
        jlblPRD = new javax.swing.JLabel();
        jlblxPRD = new javax.swing.JLabel();
        jTxtFldKD_PRD = new javax.swing.JTextField();
        jTxtFldNM_PRD = new javax.swing.JTextField();
        jTxtFldKD_PRD2 = new javax.swing.JTextField();
        jBtnPRD = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMnKembali = new javax.swing.JMenu();

        jDialogBRNCH.setMinimumSize(new java.awt.Dimension(455, 400));
        jDialogBRNCH.setModal(true);
        jDialogBRNCH.setResizable(false);

        jTblBRNCH.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTblBRNCH);

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

        javax.swing.GroupLayout jDialogBRNCHLayout = new javax.swing.GroupLayout(jDialogBRNCH.getContentPane());
        jDialogBRNCH.getContentPane().setLayout(jDialogBRNCHLayout);
        jDialogBRNCHLayout.setHorizontalGroup(
            jDialogBRNCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogBRNCHLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOk)
                .addContainerGap())
        );
        jDialogBRNCHLayout.setVerticalGroup(
            jDialogBRNCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogBRNCHLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jDialogBRNCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOk)
                    .addComponent(jBtnKembali))
                .addContainerGap())
        );

        jDialogPRD.setMinimumSize(new java.awt.Dimension(455, 400));
        jDialogPRD.setModal(true);
        jDialogPRD.setResizable(false);

        jTblPRD.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTblPRD);

        jBtnOk1.setText("Ok");
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

        javax.swing.GroupLayout jDialogPRDLayout = new javax.swing.GroupLayout(jDialogPRD.getContentPane());
        jDialogPRD.getContentPane().setLayout(jDialogPRDLayout);
        jDialogPRDLayout.setHorizontalGroup(
            jDialogPRDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogPRDLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnKembali1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOk1)
                .addContainerGap())
        );
        jDialogPRDLayout.setVerticalGroup(
            jDialogPRDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogPRDLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jDialogPRDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOk1)
                    .addComponent(jBtnKembali1))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form Populasi");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlblBRNCH.setText("Cabang*");

        jlblxBRNCH.setText(":");

        jlblOTLT.setText("Outlet");

        jlblxOTLT.setText(":");

        jlblPIC.setText("PIC");

        jlblxPIC.setText(":");

        jlblSTS.setText("Status Alat*");

        jlblxSTS.setText(":");

        jCmbSTS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Jual", "KSO" }));

        jBtnSrch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnSrch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSrchActionPerformed(evt);
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
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jBtnNew.setText("Data Baru");
        jBtnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNewActionPerformed(evt);
            }
        });

        jBtnSave.setText("Simpan");
        jBtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaveActionPerformed(evt);
            }
        });

        jBtnSave2.setText("Simpan");
        jBtnSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSave2ActionPerformed(evt);
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

        jLabel1.setText("Cari Data Berdasarkan Nama Cabang / Status Alat :");

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

        jlblPRD.setText("Produk*");

        jlblxPRD.setText(":");

        jBtnPRD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnPRD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPRDActionPerformed(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(jBtnDlt))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblOTLT)
                                    .addComponent(jlblPIC)
                                    .addComponent(jlblPRD)
                                    .addComponent(jlblSTS)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jlblBRNCH)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlblxBRNCH)
                            .addComponent(jlblxOTLT)
                            .addComponent(jlblxPIC)
                            .addComponent(jlblxPRD)
                            .addComponent(jlblxSTS))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtFldKD_BRNCH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtFldKD_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtFldPIC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtFldNM_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtFldKD_BRNCH2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldKD_PRD2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtFldNM_BRNCH, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBtnSrch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTxtFldKD_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldNM_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnPRD, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCmbSTS, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtFldSTS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblBRNCH)
                            .addComponent(jlblxBRNCH)
                            .addComponent(jTxtFldKD_BRNCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldNM_BRNCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtFldKD_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblxOTLT)
                            .addComponent(jlblOTLT)
                            .addComponent(jTxtFldNM_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtFldPIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblPIC)
                            .addComponent(jlblxPIC)
                            .addComponent(jTxtFldKD_BRNCH2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldKD_PRD2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jBtnSrch, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnPRD, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtFldKD_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlblxPRD)
                        .addComponent(jlblPRD)
                        .addComponent(jTxtFldNM_PRD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCmbSTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblSTS)
                    .addComponent(jlblxSTS)
                    .addComponent(jTxtFldSTS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnSave)
                        .addComponent(jBtnNew))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnEdit)
                        .addComponent(jBtnSave2))
                    .addComponent(jBtnDlt, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jTxtFldKD_BRNCH.getAccessibleContext().setAccessibleName("");
        jTxtFldKD_OTLT.getAccessibleContext().setAccessibleName("");

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

    private void jBtnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliActionPerformed
        // TODO add your handling code here:
        jDialogBRNCH.dispose();
    }//GEN-LAST:event_jBtnKembaliActionPerformed

    private void jBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkActionPerformed
        // TODO add your handling code here:
        if (jTabel.getSelectedRow() != -1){
           klikTabeljDialog(jTabel);
           jDialogBRNCH.dispose();   
       }
       else {
           JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
       }         
    }//GEN-LAST:event_jBtnOkActionPerformed

    private void jBtnSrchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSrchActionPerformed
        // TODO add your handling code here:
        jDialogBRNCH.setLocationRelativeTo(null);
        tampilDataKeTabel2();
        jDialogBRNCH.setVisible(true); 
    }//GEN-LAST:event_jBtnSrchActionPerformed

    private void jBtnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNewActionPerformed
        // TODO add your handling code here:
        clearTEXT();
        enableField(true);
        enableBtn(false);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(false);
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
    }//GEN-LAST:event_jBtnDltActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
       klikTabel(jTabel2);
       enableField(false);
       enableBtn(true);
       enviBtnSave(false);
       enviBtnSave2(false);
       enviBtnNew(true);
    }//GEN-LAST:event_jTable1MousePressed

    private void jBtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCariActionPerformed
        // TODO add your handling code here:
        tampilDataKeTabelcari();
    }//GEN-LAST:event_jBtnCariActionPerformed

    private void jBtnPRDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPRDActionPerformed
        // TODO add your handling code here:
        jDialogPRD.setLocationRelativeTo(null);
        modeljDialogPRD(jTabel4);
        tampilDataKeTabel3();
        jDialogPRD.setVisible(true);
    }//GEN-LAST:event_jBtnPRDActionPerformed

    private void jBtnOk1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOk1ActionPerformed
        // TODO add your handling code here:
        if (jTabel4.getSelectedRow() != -1){
           klikTabeljDialogPRD(jTabel4);
           jDialogPRD.dispose();   
       }
       else {
           JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
       }    
    }//GEN-LAST:event_jBtnOk1ActionPerformed

    private void jBtnKembali1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembali1ActionPerformed
        // TODO add your handling code here:
        jDialogPRD.dispose();
    }//GEN-LAST:event_jBtnKembali1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        aplikasiInventory.Start.setVisible(true);
        aplikasiInventory.loadNIK(aplikasiInventory.Start.jlblstart_jpg);
        aplikasiInventory.vsNIK = aplikasiInventory.Start.jlblstart_jpg.getText();
        aplikasiInventory.HakAkses();
        dispose();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(populasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(populasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(populasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(populasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new populasi().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCari;
    private javax.swing.JButton jBtnDlt;
    private javax.swing.JButton jBtnEdit;
    private javax.swing.JButton jBtnKembali;
    private javax.swing.JButton jBtnKembali1;
    private javax.swing.JButton jBtnNew;
    private javax.swing.JButton jBtnOk;
    private javax.swing.JButton jBtnOk1;
    private javax.swing.JButton jBtnPRD;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JButton jBtnSave2;
    private javax.swing.JButton jBtnSrch;
    private javax.swing.JComboBox jCmbSTS;
    private javax.swing.JDialog jDialogBRNCH;
    private javax.swing.JDialog jDialogPRD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTblBRNCH;
    private javax.swing.JTable jTblPRD;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTxtFldKD_BRNCH;
    private javax.swing.JTextField jTxtFldKD_BRNCH2;
    private javax.swing.JTextField jTxtFldKD_OTLT;
    private javax.swing.JTextField jTxtFldKD_PRD;
    private javax.swing.JTextField jTxtFldKD_PRD2;
    private javax.swing.JTextField jTxtFldNM_BRNCH;
    private javax.swing.JTextField jTxtFldNM_OTLT;
    private javax.swing.JTextField jTxtFldNM_PRD;
    private javax.swing.JTextField jTxtFldPIC;
    private javax.swing.JTextField jTxtFldSTS;
    private javax.swing.JLabel jlblBRNCH;
    private javax.swing.JLabel jlblOTLT;
    private javax.swing.JLabel jlblPIC;
    private javax.swing.JLabel jlblPRD;
    private javax.swing.JLabel jlblSTS;
    private javax.swing.JLabel jlblxBRNCH;
    private javax.swing.JLabel jlblxOTLT;
    private javax.swing.JLabel jlblxPIC;
    private javax.swing.JLabel jlblxPRD;
    private javax.swing.JLabel jlblxSTS;
    // End of variables declaration//GEN-END:variables
}
