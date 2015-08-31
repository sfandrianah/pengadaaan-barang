package com.pengadaan.barang;

import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class karyawan extends javax.swing.JFrame {

    private Integer row;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable();
    private DefaultTableModel DfltTblMode;
    private TableColumn column;
    private String e,r,i,k,a,s,p,t;
    
    public karyawan() {
        initComponents();
        aplikasiInventory.konekkeDatabase();
        tampilDataKeTabel();
        enableBtn(false);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(false);
        jTxtFldNM_KRYWN2.setVisible(false);
        jTxtFldEmail2.setVisible(false);
        jPass.setEnabled(false);
    }
    
    private void clearTEXT() {
        jTxtFldNIK.setText("");
        jTxtFldNM_KRYWN.setText("");
        jCmbJBTN.setSelectedItem("");
        jTxtFldEmail.setText("");
        jPass.setText("");
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
        jTxtFldNIK.setEnabled(x);
        jTxtFldNM_KRYWN.setEnabled(x);
        jCmbJBTN.setEnabled(x);
        jTxtFldEmail.setEnabled(x);
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
       r = jTxtFldNIK.getText();
       i = jTxtFldNM_KRYWN.getText();
       k = jPass.getText();
       a = jTxtFldEmail.getText();
       
       try {
       if (r.equals("") || i.equals("") || jCmbJBTN.equals("") || a.equals("")) 
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
           clearTEXT();
           }
       else {    
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "insert into mst_user"+
                       "(NIK, NM_KRYWN, JABATAN, EMAIL, PASS) values ('"+ r +"','"+ i +"','"+ jCmbJBTN.getSelectedItem() +"','"+ a +"','"+ k +"')" );
                 
                 tampilDataKeTabel();      
                 JOptionPane.showMessageDialog(this,"Data berhasil disimpan");
               }
           }
                    catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Data gagal disimpan! : " +ex);
                    }
               }
    
    private void kondisiEdit() {
       e = jTxtFldNIK.getText();
       i = jTxtFldNM_KRYWN.getText();
       a = jTxtFldEmail.getText();
       s = jTxtFldNM_KRYWN2.getText();
       t = jTxtFldEmail2.getText();
       
       try {
       if (i.equals("") || a.equals("")) 
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
           clearTEXT();
           }
       else if (i.equals(s) && a.equals(t)) 
           {
           JOptionPane.showMessageDialog(null, "Tidak ada data yang diperbaharui!");
           }
       else {      
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "update mst_user set "+
                       "NM_KRYWN = "+"'"+ i +"', "+
                       "EMAIL = "+"'"+ a +"' "+
                       "where NIK = '"+ e +"'");
       
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
            Object[] field = {"No","NIK","Nama Karyawan","Jabatan","Email","Password"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);
           
            String sql = "Select * from mst_user";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);
                  
            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("NIK");
                String kolom3 = set.getString("NM_KRYWN");
                String kolom4 = set.getString("JABATAN");
                String kolom5 = set.getString("EMAIL");
                String kolom6 = set.getString("PASS");
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
            column.setPreferredWidth(150);
            column = jTabel.getColumnModel().getColumn(4);
            column.setPreferredWidth(150);         
            column = jTabel.getColumnModel().getColumn(5);
            column.setMinWidth(0);         
            column.setMaxWidth(0);
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
        jTxtFldNIK.setText(kolom2);
        jTxtFldNM_KRYWN.setText(kolom3);
        jTxtFldNM_KRYWN2.setText(kolom3);
        jCmbJBTN.setSelectedItem(kolom4);
        jTxtFldEmail.setText(kolom5);
        jTxtFldEmail2.setText(kolom5);
        jPass.setText(kolom6);
    }
    
    private void kondisiHapus() {
       e = jTxtFldNIK.getText();
            
       try {
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            st.executeUpdate(
            " delete from mst_user where NIK ='"+ e +"'");
            clearTEXT();
            tampilDataKeTabel();
            
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
       }
        catch (SQLException ex) {
              JOptionPane.showMessageDialog(this,"Data gagal dihapus: " +ex);
        }
    }
    
    private void cari(JTable jTabel){
    try {
            Object[] field = {"No","NIK","Nama Karyawan","Jabatan","Email"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);
            
            String sql = "Select * from mst_user where NIK like '%" + jTextField1.getText() + "%'" +
                         "or NM_KRYWN like '%" + jTextField1.getText() + "%'";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("NIK");
                String kolom3 = set.getString("NM_KRYWN");
                String kolom4 = set.getString("JABATAN");
                String kolom5 = set.getString("EMAIL");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5};
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
            column.setPreferredWidth(150);
            column = jTabel.getColumnModel().getColumn(4);
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

        jlblNIK = new javax.swing.JLabel();
        jlblNM_KRYWN = new javax.swing.JLabel();
        jlblJBTN = new javax.swing.JLabel();
        jlblEmail = new javax.swing.JLabel();
        jlblxNIK = new javax.swing.JLabel();
        jlblxNM_KRYWN = new javax.swing.JLabel();
        jlblxJBTN = new javax.swing.JLabel();
        jlblxEmail = new javax.swing.JLabel();
        jTxtFldNIK = new javax.swing.JTextField();
        jTxtFldNM_KRYWN = new javax.swing.JTextField();
        jTxtFldEmail = new javax.swing.JTextField();
        jBtnSave = new javax.swing.JButton();
        jBtnEdit = new javax.swing.JButton();
        jBtnDlt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBtnNew = new javax.swing.JButton();
        jBtnSave2 = new javax.swing.JButton();
        jTxtFldNM_KRYWN2 = new javax.swing.JTextField();
        jTxtFldEmail2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jBtnCari = new javax.swing.JButton();
        jCmbJBTN = new javax.swing.JComboBox();
        jlblPass = new javax.swing.JLabel();
        jlblxPass = new javax.swing.JLabel();
        jPass = new javax.swing.JPasswordField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMnKembali = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form Karyawan");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlblNIK.setText("NIK*");

        jlblNM_KRYWN.setText("Nama Karyawan*");

        jlblJBTN.setText("Jabatan*");

        jlblEmail.setText("Email*");

        jlblxNIK.setText(":");

        jlblxNM_KRYWN.setText(":");

        jlblxJBTN.setText(":");

        jlblxEmail.setText(":");

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

        jLabel1.setText("Cari Data Berdasarkan NIK / Nama Krywn :");

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

        jCmbJBTN.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Administrator", "Admin", "Super User", "User", "Teknisi" }));
        jCmbJBTN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCmbJBTNItemStateChanged(evt);
            }
        });

        jlblPass.setText("Password");

        jlblxPass.setText(":");

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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblNIK)
                                    .addComponent(jlblNM_KRYWN)
                                    .addComponent(jlblJBTN)
                                    .addComponent(jlblEmail)
                                    .addComponent(jlblPass))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlblxPass)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPass, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlblxNIK, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jlblxNM_KRYWN, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jlblxJBTN, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jlblxEmail, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTxtFldNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTxtFldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTxtFldEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTxtFldNM_KRYWN, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jCmbJBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTxtFldNM_KRYWN2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblNIK)
                    .addComponent(jlblxNIK)
                    .addComponent(jTxtFldNIK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldNM_KRYWN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblNM_KRYWN)
                    .addComponent(jlblxNM_KRYWN)
                    .addComponent(jTxtFldNM_KRYWN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlblJBTN)
                    .addComponent(jlblxJBTN)
                    .addComponent(jCmbJBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtFldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblEmail)
                    .addComponent(jlblxEmail)
                    .addComponent(jTxtFldEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlblxPass)
                    .addComponent(jlblPass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnSave, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnEdit)
                        .addComponent(jBtnNew)
                        .addComponent(jBtnSave2)
                        .addComponent(jBtnDlt)))
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
        // TODO add your handling code here:
       clearTEXT();      
       enableField(true);
       enableBtn(false);
       enviBtnSave(true);
       enviBtnSave2(false);
       enviBtnNew(false);
       jTxtFldNIK.requestFocus();
    }//GEN-LAST:event_jBtnNewActionPerformed

    private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaveActionPerformed
        // TODO add your handling code here:
       if (jCmbJBTN.getSelectedItem() == "Admin") {
        if(jPass.getText().length()<5) {
            JOptionPane.showMessageDialog(null, "Password tidak boleh kurang dari 5 digit !");
            jPass.setText("");
          }
          else if(jPass.getText().length()>15) {
            JOptionPane.showMessageDialog(null, "Password tidak boleh lebih dari 15 digit !");
            jPass.setText("");
          }
         else {
            kondisiSave();
            enableField(false);
            jPass.setEnabled(false);
            enableBtn(true);
            enviBtnSave(false);
            enviBtnSave2(false);
            enviBtnNew(true);
          }
       }
          else {
            kondisiSave();
            enableField(false);
            jPass.setEnabled(false);
            enableBtn(true);
            enviBtnSave(false);
            enviBtnSave2(false);
            enviBtnNew(true);
          }       
    }//GEN-LAST:event_jBtnSaveActionPerformed

    private void jBtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditActionPerformed
        // TODO add your handling code here:
       jTxtFldNM_KRYWN.setEnabled(true);
       jTxtFldEmail.setEnabled(true);
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
       klikTabel(jTabel);
       jPass.setEnabled(false);
       enableField(false);
       enableBtn(true);
       enviBtnSave(false);
       enviBtnSave2(false);
       enviBtnNew(true);
    }//GEN-LAST:event_jTable1MousePressed

    private void jBtnSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSave2ActionPerformed
        // TODO add your handling code here:
       if (jCmbJBTN.getSelectedItem() == "Admin") {
        if(jPass.getText().length()<5) {
            JOptionPane.showMessageDialog(null, "Password tidak boleh kurang dari 5 digit !");
            jPass.setText("");
          }
          else if(jPass.getText().length()>15) {
            JOptionPane.showMessageDialog(null, "Password tidak boleh lebih dari 15 digit !");
            jPass.setText("");
          }
          else {
            kondisiEdit();
            enableField(false);
            enableBtn(false);
            enviBtnSave(false);
            enviBtnSave2(false);
            enviBtnNew(true);
          }
       }
          else {
            kondisiEdit();
            enableField(false);
            enableBtn(false);
            enviBtnSave(false);
            enviBtnSave2(false);
            enviBtnNew(true);
          }
    }//GEN-LAST:event_jBtnSave2ActionPerformed

    private void jBtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCariActionPerformed
        // TODO add your handling code here:
        tampilDataKeTabelcari();
    }//GEN-LAST:event_jBtnCariActionPerformed

    private void jCmbJBTNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCmbJBTNItemStateChanged
        // TODO add your handling code here:
        if (jCmbJBTN.getSelectedItem() == "Admin" || jCmbJBTN.getSelectedItem() == "Administrator") {
           jPass.setEnabled(true);
        }
        else {
           jPass.setEnabled(false);
        }
    }//GEN-LAST:event_jCmbJBTNItemStateChanged

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new start().setVisible(true);
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
            java.util.logging.Logger.getLogger(karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new karyawan().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCari;
    private javax.swing.JButton jBtnDlt;
    private javax.swing.JButton jBtnEdit;
    private javax.swing.JButton jBtnNew;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JButton jBtnSave2;
    private javax.swing.JComboBox jCmbJBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JPasswordField jPass;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTxtFldEmail;
    private javax.swing.JTextField jTxtFldEmail2;
    private javax.swing.JTextField jTxtFldNIK;
    private javax.swing.JTextField jTxtFldNM_KRYWN;
    private javax.swing.JTextField jTxtFldNM_KRYWN2;
    private javax.swing.JLabel jlblEmail;
    private javax.swing.JLabel jlblJBTN;
    private javax.swing.JLabel jlblNIK;
    private javax.swing.JLabel jlblNM_KRYWN;
    private javax.swing.JLabel jlblPass;
    private javax.swing.JLabel jlblxEmail;
    private javax.swing.JLabel jlblxJBTN;
    private javax.swing.JLabel jlblxNIK;
    private javax.swing.JLabel jlblxNM_KRYWN;
    private javax.swing.JLabel jlblxPass;
    // End of variables declaration//GEN-END:variables
}
