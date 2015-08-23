package com.pengadaan.barang;

import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class outlet extends javax.swing.JFrame {

    private Integer row;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable(), jTabel2 = new JTable(), jTabel3 = new JTable();
    private DefaultTableModel DfltTblMode;
    private TableColumn column; 
    private String e,r,i,k,a,s,p,t,x,y,z,b,c,d,l,m,n;
    
    public outlet() {
        initComponents();
        aplikasiInventory.konekkeDatabase();
        tampilDataKeTabel();
        enableBtn(false);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(false);
        jTxtFldKD_BRNCH.setEnabled(false);
        jTxtFldNM_BRNCH.setEnabled(false);
        jTxtFldKD_OTLT2.setVisible(false);
        jTxtFldNM_OTLT2.setVisible(false);
        jTxtFldPIC2.setVisible(false);
        jTxtFldALMT2.setVisible(false);
        jTxtFldKOTA2.setVisible(false);
        jTxtFldPRVNS2.setVisible(false);
        jTxtFldTLP2.setVisible(false);
        jTxtFldKD_BRNCH2.setVisible(false);
    }

    private void clearTEXT() {
        jTxtFldKD_OTLT.setText("");
        jTxtFldNM_OTLT.setText("");
        jTxtFldPIC.setText("");
        jTxtFldALMT.setText("");
        jTxtFldKOTA.setText("");
        jTxtFldPRVNS.setText("");
        jTxtFldTLP.setText("");
        jTxtFldKD_BRNCH.setText("");
        jTxtFldNM_BRNCH.setText("");
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
        jTxtFldKD_OTLT.setEnabled(x);
        jTxtFldNM_OTLT.setEnabled(x);
        jTxtFldPIC.setEnabled(x);
        jTxtFldALMT.setEnabled(x);
        jTxtFldKOTA.setEnabled(x);
        jTxtFldPRVNS.setEnabled(x);
        jTxtFldTLP.setEnabled(x);
        jBtnBRNCH.setEnabled(x);
    }
    
    private void tampilDataKeTabel() {
        jTabel3 = jTable1;
        tabelModel(jTabel3);
    }
    
    private void tampilDataKeTabel2() {
        jTabel = jTable2;
        modeljdialog(jTabel);
    }
    
    private void tampilDataKeTabelcari() {
        jTabel2 = jTable1;
        cari(jTabel2);
    }
    
    private void modeljdialog(JTable jTabel) {
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
    
    private void klikTabeljDialog(JTable jTabel) {
        jTabel.setRowSelectionAllowed(true);
        row = jTabel.getSelectedRow();
        String kolom1 = jTabel.getValueAt(row,0).toString();
        String kolom2 = jTabel.getValueAt(row,1).toString();
        String kolom3 = jTabel.getValueAt(row,2).toString();
        jTxtFldKD_BRNCH.setText(kolom2);
        jTxtFldNM_BRNCH.setText(kolom3);           
    }
    
    private void cari(JTable jTabel2){
    try {
            Object[] field = {"No","Kode Otlet","Nama Otlet","PIC","Alamat","Kota","Provinsi","Telepon","Kode Cabang","Nama Cabang"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel2.setModel(DfltTblMode);
            
            String sql = "Select * from outlet where KD_OTLT like '%" + jTextField1.getText() + "%'" +
                         "or NM_OTLT like '%" + jTextField1.getText() + "%'";
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
            
            jTabel2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel2.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel2.getColumnModel().getColumn(1);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(3);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(4);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(5);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(7);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(8);
            column.setPreferredWidth(100);
            column = jTabel2.getColumnModel().getColumn(9);
            column.setPreferredWidth(100);
            
            }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void kondisiSave() { 
       e = jTxtFldKD_OTLT.getText();
       r = jTxtFldNM_OTLT.getText();
       i = jTxtFldPIC.getText();
       k = jTxtFldALMT.getText();
       a = jTxtFldKOTA.getText();
       s = jTxtFldPRVNS.getText();
       p = jTxtFldTLP.getText();
       t = jTxtFldKD_BRNCH.getText();
       x = jTxtFldNM_BRNCH.getText();
       
       try {
       if (e.equals("") || r.equals("") || i.equals("") || k.equals("") || a.equals("") || s.equals("") || t.equals("")) 
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
           clearTEXT();
           }
       else {    
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "insert into outlet"+
                       "(KD_OTLT, NM_OTLT, PIC, ALAMAT, KOTA, PROVINSI, TLP, KD_BRNCH, NM_BRNCH) values "+
                         "('"+ e +"','"+ r +"','"+ i +"','"+ k +"','"+ a +"','"+ s +"','"+ p +"','"+ t +"','"+ x +"')" );
                 
                 tampilDataKeTabel();      
                 JOptionPane.showMessageDialog(this,"Data berhasil disimpan");
               }
           }
                    catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Data gagal disimpan! : " +ex);
                    }
               }
   
    private void kondisiEdit() { 
       e = jTxtFldKD_OTLT.getText();
       r = jTxtFldNM_OTLT.getText();
       i = jTxtFldPIC.getText();
       k = jTxtFldALMT.getText();
       a = jTxtFldKOTA.getText();
       s = jTxtFldPRVNS.getText();
       p = jTxtFldTLP.getText();
       t = jTxtFldKD_BRNCH.getText();
       x = jTxtFldNM_BRNCH.getText();
       y = jTxtFldKD_OTLT2.getText();
       z = jTxtFldNM_OTLT2.getText();
       b = jTxtFldPIC2.getText();
       c = jTxtFldALMT2.getText();
       d = jTxtFldKOTA2.getText();
       l = jTxtFldPRVNS2.getText();
       m = jTxtFldTLP2.getText();
       n = jTxtFldKD_BRNCH2.getText();       
       
       try {
       if (e.equals("") || r.equals("") || i.equals("") || k.equals("") || a.equals("") || s.equals("") || t.equals(""))
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
           clearTEXT();
           }
       else if (e.equals(y) && r.equals(z) && i.equals(b) && k.equals(c) && a.equals(d) && s.equals(l) && p.equals(m) && t.equals(n)) 
           {
           JOptionPane.showMessageDialog(null, "Tidak ada data yang diperbaharui!");
           }
       else {    
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "update outlet set "+
                       "KD_OTLT = "+"'"+ e +"', "+
                       "NM_OTLT = "+"'"+ r +"', "+
                       "PIC = "+"'"+ i +"', "+
                       "ALAMAT = "+"'"+ k +"', "+
                       "KOTA = "+"'"+ a +"', "+
                       "PROVINSI = "+"'"+ s +"', "+
                       "TLP = "+"'"+ p +"', "+
                       "KD_BRNCH = "+"'"+ t +"', "+
                       "NM_BRNCH = "+"'"+ x +"' "+
                       "where KD_OTLT = '"+ y +"'");
       
                 tampilDataKeTabel(); 
                 JOptionPane.showMessageDialog(this,"Data berhasil diperbaharui");
               }
           }
                    catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Data gagal diperbaharui! : " +ex);
                    }
               }
   
    private void tabelModel(JTable jTabel3) {
        try {
            Object[] field = {"No","Kode Otlet","Nama Otlet","PIC","Alamat","Kota","Provinsi","Telepon","Kode Cabang","Nama Cabang"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel3.setModel(DfltTblMode);
            
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
          }
          catch (SQLException e) {
              JOptionPane.showMessageDialog(this, "Koneksi gagal: " +e);
          }
    }
    
    private void klikTabel(JTable jTabel3) {
        jTabel3.setRowSelectionAllowed(true);
        row = jTabel3.getSelectedRow();
        String kolom1 = jTabel3.getValueAt(row,0).toString();
        String kolom2 = jTabel3.getValueAt(row,1).toString();
        String kolom3 = jTabel3.getValueAt(row,2).toString();
        String kolom4 = jTabel3.getValueAt(row,3).toString();
        String kolom5 = jTabel3.getValueAt(row,4).toString();
        String kolom6 = jTabel3.getValueAt(row,5).toString();
        String kolom7 = jTabel3.getValueAt(row,6).toString();
        String kolom8 = jTabel3.getValueAt(row,7).toString();
        String kolom9 = jTabel3.getValueAt(row,8).toString();
        String kolom10 = jTabel3.getValueAt(row,9).toString();
        jTxtFldKD_OTLT.setText(kolom2);
        jTxtFldNM_OTLT.setText(kolom3); 
        jTxtFldPIC.setText(kolom4); 
        jTxtFldALMT.setText(kolom5); 
        jTxtFldKOTA.setText(kolom6); 
        jTxtFldPRVNS.setText(kolom7); 
        jTxtFldTLP.setText(kolom8);
        jTxtFldKD_BRNCH.setText(kolom9);
        jTxtFldNM_BRNCH.setText(kolom10);
        
        jTxtFldKD_OTLT2.setText(kolom2);
        jTxtFldNM_OTLT2.setText(kolom3);
        jTxtFldPIC2.setText(kolom4);
        jTxtFldALMT2.setText(kolom5);
        jTxtFldKOTA2.setText(kolom6);
        jTxtFldPRVNS2.setText(kolom7);
        jTxtFldTLP2.setText(kolom8);
        jTxtFldKD_BRNCH2.setText(kolom9);
    }
   
    private void kondisiHapus() {
       e = jTxtFldKD_OTLT.getText();
            
       try {
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            st.executeUpdate(
            " delete from outlet where KD_OTLT ='"+ e +"'");
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

        jDialog1 = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jBtnOk = new javax.swing.JButton();
        jBtnKembali = new javax.swing.JButton();
        jlblKD_OTLT = new javax.swing.JLabel();
        jlblNM_OTLT = new javax.swing.JLabel();
        jlblPIC = new javax.swing.JLabel();
        jlblALMT = new javax.swing.JLabel();
        jlblKOTA = new javax.swing.JLabel();
        jlblPRVNS = new javax.swing.JLabel();
        jlblTLP = new javax.swing.JLabel();
        jlblxKD_OTLT = new javax.swing.JLabel();
        jlblxNM_OTLT = new javax.swing.JLabel();
        jlblxPIC = new javax.swing.JLabel();
        jlblxALMT = new javax.swing.JLabel();
        jlblxKOTA = new javax.swing.JLabel();
        jlblxPRVNS = new javax.swing.JLabel();
        jlblxTLP = new javax.swing.JLabel();
        jTxtFldKD_OTLT = new javax.swing.JTextField();
        jTxtFldNM_OTLT = new javax.swing.JTextField();
        jTxtFldPIC = new javax.swing.JTextField();
        jTxtFldALMT = new javax.swing.JTextField();
        jTxtFldKOTA = new javax.swing.JTextField();
        jTxtFldPRVNS = new javax.swing.JTextField();
        jTxtFldTLP = new javax.swing.JTextField();
        jBtnSave = new javax.swing.JButton();
        jBtnEdit = new javax.swing.JButton();
        jBtnDlt = new javax.swing.JButton();
        jlblKD_BRNCH = new javax.swing.JLabel();
        jlblxKD_BRNCH = new javax.swing.JLabel();
        jTxtFldKD_BRNCH = new javax.swing.JTextField();
        jTxtFldNM_BRNCH = new javax.swing.JTextField();
        jBtnBRNCH = new javax.swing.JButton();
        jBtnNew = new javax.swing.JButton();
        jBtnSave2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jBtnCari = new javax.swing.JButton();
        jTxtFldKD_OTLT2 = new javax.swing.JTextField();
        jTxtFldNM_OTLT2 = new javax.swing.JTextField();
        jTxtFldPIC2 = new javax.swing.JTextField();
        jTxtFldALMT2 = new javax.swing.JTextField();
        jTxtFldKOTA2 = new javax.swing.JTextField();
        jTxtFldPRVNS2 = new javax.swing.JTextField();
        jTxtFldTLP2 = new javax.swing.JTextField();
        jTxtFldKD_BRNCH2 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMnKembali = new javax.swing.JMenu();

        jDialog1.setTitle("Tabel Cabang");
        jDialog1.setMinimumSize(new java.awt.Dimension(455, 400));
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
        jTable2.setSurrendersFocusOnKeystroke(true);
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
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(jBtnKembali)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnOk)
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnOk)
                    .addComponent(jBtnKembali)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form Outlet");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlblKD_OTLT.setText("Kode Outlet*");

        jlblNM_OTLT.setText("Nama Outlet*");

        jlblPIC.setText("Contact Person*");

        jlblALMT.setText("Alamat*");

        jlblKOTA.setText("Kota*");

        jlblPRVNS.setText("Provinsi*");

        jlblTLP.setText("Telepon");

        jlblxKD_OTLT.setText(":");

        jlblxNM_OTLT.setText(":");

        jlblxPIC.setText(":");

        jlblxALMT.setText(":");

        jlblxKOTA.setText(":");

        jlblxPRVNS.setText(":");

        jlblxTLP.setText(":");

        jTxtFldTLP.setToolTipText("");

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

        jlblKD_BRNCH.setText("Cabang*");

        jlblxKD_BRNCH.setText(":");

        jTxtFldKD_BRNCH.setToolTipText("");

        jTxtFldNM_BRNCH.setToolTipText("");

        jBtnBRNCH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnBRNCH.setToolTipText("");
        jBtnBRNCH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBRNCHActionPerformed(evt);
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
        jScrollPane3.setViewportView(jTable1);

        jLabel1.setText("Cari Data Berdasarkan Kode / Nama Outlet :");

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
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblKD_OTLT)
                                    .addComponent(jlblNM_OTLT)
                                    .addComponent(jlblPIC)
                                    .addComponent(jlblALMT)
                                    .addComponent(jlblKOTA)
                                    .addComponent(jlblPRVNS)
                                    .addComponent(jlblTLP)
                                    .addComponent(jlblKD_BRNCH))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlblxKD_BRNCH)
                                        .addGap(12, 12, 12)
                                        .addComponent(jTxtFldKD_BRNCH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldNM_BRNCH, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jBtnBRNCH, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jTxtFldTLP, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTxtFldTLP2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTxtFldKD_BRNCH2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jTxtFldPRVNS, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTxtFldPRVNS2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jTxtFldKOTA, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTxtFldKOTA2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jTxtFldALMT, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTxtFldALMT2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jTxtFldPIC, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTxtFldPIC2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jTxtFldNM_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTxtFldNM_OTLT2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jTxtFldKD_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTxtFldKD_OTLT2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(jlblxKD_OTLT)
                                            .addComponent(jlblxNM_OTLT)
                                            .addComponent(jlblxPIC)
                                            .addComponent(jlblxALMT)
                                            .addComponent(jlblxKOTA)
                                            .addComponent(jlblxPRVNS)
                                            .addComponent(jlblxTLP))
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldKD_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblKD_OTLT)
                    .addComponent(jlblxKD_OTLT)
                    .addComponent(jTxtFldKD_OTLT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldNM_OTLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblNM_OTLT)
                    .addComponent(jlblxNM_OTLT)
                    .addComponent(jTxtFldNM_OTLT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldPIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblxPIC)
                    .addComponent(jlblPIC)
                    .addComponent(jTxtFldPIC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldALMT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblxALMT)
                    .addComponent(jlblALMT)
                    .addComponent(jTxtFldALMT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldKOTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblxKOTA)
                    .addComponent(jlblKOTA)
                    .addComponent(jTxtFldKOTA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldPRVNS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblxPRVNS)
                    .addComponent(jlblPRVNS)
                    .addComponent(jTxtFldPRVNS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldTLP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblxTLP)
                    .addComponent(jlblTLP)
                    .addComponent(jTxtFldTLP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFldKD_BRNCH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlblKD_BRNCH)
                        .addComponent(jlblxKD_BRNCH)
                        .addComponent(jTxtFldNM_BRNCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTxtFldKD_BRNCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnBRNCH, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jBtnBRNCHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBRNCHActionPerformed
        // TODO add your handling code here:        
        jDialog1.setLocationRelativeTo(null);
        tampilDataKeTabel2();
        jDialog1.setVisible(true); 
    }//GEN-LAST:event_jBtnBRNCHActionPerformed

    private void jBtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCariActionPerformed
        // TODO add your handling code here:
        tampilDataKeTabelcari();
    }//GEN-LAST:event_jBtnCariActionPerformed

    private void jBtnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOkActionPerformed
        // TODO add your handling code here:
       if (jTabel.getSelectedRow() != -1){
           klikTabeljDialog(jTabel);
           jDialog1.dispose();   
       }
       else {
           JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih!");
       }
    }//GEN-LAST:event_jBtnOkActionPerformed

    private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaveActionPerformed
        // TODO add your handling code here:
       kondisiSave();
       enableField(false);
       enableBtn(true);
       enviBtnSave(false);
       enviBtnSave2(false);
       enviBtnNew(true);
    }//GEN-LAST:event_jBtnSaveActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
       klikTabel(jTabel3);
       enableField(false);
       enableBtn(true);
       enviBtnSave(false);
       enviBtnSave2(false);
       enviBtnNew(true);
    }//GEN-LAST:event_jTable1MousePressed

    private void jBtnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNewActionPerformed
        // TODO add your handling code here:
       clearTEXT(); 
       enableField(true);
       enableBtn(false);
       enviBtnSave(true);
       enviBtnSave2(false);
       enviBtnNew(false);
       jTxtFldKD_OTLT.requestFocus();
    }//GEN-LAST:event_jBtnNewActionPerformed

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

    private void jBtnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSave2ActionPerformed
        // TODO add your handling code here:
       kondisiEdit();
       enableField(false);
       enableBtn(false);
       enviBtnSave(false);
       enviBtnSave2(false);
       enviBtnNew(true);
    }//GEN-LAST:event_jBtnSave2ActionPerformed

    private void jBtnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnKembaliActionPerformed
        // TODO add your handling code here:
        jDialog1.dispose();
    }//GEN-LAST:event_jBtnKembaliActionPerformed

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
            java.util.logging.Logger.getLogger(outlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(outlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(outlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(outlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new outlet().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBRNCH;
    private javax.swing.JButton jBtnCari;
    private javax.swing.JButton jBtnDlt;
    private javax.swing.JButton jBtnEdit;
    private javax.swing.JButton jBtnKembali;
    private javax.swing.JButton jBtnNew;
    private javax.swing.JButton jBtnOk;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JButton jBtnSave2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTxtFldALMT;
    private javax.swing.JTextField jTxtFldALMT2;
    private javax.swing.JTextField jTxtFldKD_BRNCH;
    private javax.swing.JTextField jTxtFldKD_BRNCH2;
    private javax.swing.JTextField jTxtFldKD_OTLT;
    private javax.swing.JTextField jTxtFldKD_OTLT2;
    private javax.swing.JTextField jTxtFldKOTA;
    private javax.swing.JTextField jTxtFldKOTA2;
    private javax.swing.JTextField jTxtFldNM_BRNCH;
    private javax.swing.JTextField jTxtFldNM_OTLT;
    private javax.swing.JTextField jTxtFldNM_OTLT2;
    private javax.swing.JTextField jTxtFldPIC;
    private javax.swing.JTextField jTxtFldPIC2;
    private javax.swing.JTextField jTxtFldPRVNS;
    private javax.swing.JTextField jTxtFldPRVNS2;
    private javax.swing.JTextField jTxtFldTLP;
    private javax.swing.JTextField jTxtFldTLP2;
    private javax.swing.JLabel jlblALMT;
    private javax.swing.JLabel jlblKD_BRNCH;
    private javax.swing.JLabel jlblKD_OTLT;
    private javax.swing.JLabel jlblKOTA;
    private javax.swing.JLabel jlblNM_OTLT;
    private javax.swing.JLabel jlblPIC;
    private javax.swing.JLabel jlblPRVNS;
    private javax.swing.JLabel jlblTLP;
    private javax.swing.JLabel jlblxALMT;
    private javax.swing.JLabel jlblxKD_BRNCH;
    private javax.swing.JLabel jlblxKD_OTLT;
    private javax.swing.JLabel jlblxKOTA;
    private javax.swing.JLabel jlblxNM_OTLT;
    private javax.swing.JLabel jlblxPIC;
    private javax.swing.JLabel jlblxPRVNS;
    private javax.swing.JLabel jlblxTLP;
    // End of variables declaration//GEN-END:variables
}
