package com.pengadaan.barang;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ubah extends javax.swing.JFrame {

    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private login Login = new login();
    private JLabel Label1 = new JLabel();
    private int ok;
    private String a,b;
    
    public ubah() {
        initComponents();
        aplikasiInventory.konekkeDatabase();
        Label1 = jlblNIKU;
        aplikasiInventory.loadNIK(Label1); 
        visibleBtn(false);
        jLabel4.setVisible(false);
        jBtnConfirm.setVisible(false);
        jlbltampung.setVisible(false);
    }
    
    private void visibleBtn(boolean x) {
        jBtnVerifikasi.setVisible(!x);
        jBtnChange.setVisible(x);
        jLabel2.setVisible(!x);
        jLabel3.setVisible(x);
        jlblKet.setVisible(x);
        jlblKet1.setVisible(x);
    }

    private void verifikasiPassLama() {
        try{
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from mst_user where NIK = '"+
                    jlblNIKU.getText()+"' and PASS = '"+jTxtFldPass.getText()+"'");

            if(rs.next()) {
                jTxtFldPass.setText("");               
                visibleBtn(true);
            }
            else  {
                    jTxtFldPass.setText("");
                    JOptionPane.showMessageDialog(null, "Password lama yang anda masukkan salah!");
                    visibleBtn(false);
                    jTxtFldPass.requestFocus();
                }
            }
       
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Koneksi gagal : "+ex);
        }
    }
    
    private void GantiPass() {
        try {
          Statement st = aplikasiInventory.config.getConnection().createStatement();
          st.executeUpdate(
                "update mst_user set "+
                "PASS = "+"'"+jTxtFldPass.getText()+"' "+
                "where NIK = '"+jlblNIKU.getText()+"'");
                    
          JOptionPane.showMessageDialog(null, "Anda berhasil mengganti password.");
          jTxtFldPass.setText("");
          visibleBtn(false);
         
        }
        catch (SQLException ex) {
           JOptionPane.showMessageDialog(this,"Gagal mengubah password: " +ex);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jlblNIK = new javax.swing.JLabel();
        jlblxNIK = new javax.swing.JLabel();
        jlblpass = new javax.swing.JLabel();
        jlblxpass = new javax.swing.JLabel();
        jBtnChange = new javax.swing.JButton();
        jTxtFldPass = new javax.swing.JPasswordField();
        jBtnVerifikasi = new javax.swing.JButton();
        jlblNIKU = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlblKet = new javax.swing.JLabel();
        jlblKet1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlbltampung = new javax.swing.JLabel();
        jBtnConfirm = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMnKembali = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form Ubah Password");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(158, 241, 101));

        jLabel1.setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(1, 0, 226));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/login1.png"))); // NOI18N
        jLabel1.setText("Change Password");

        jlblNIK.setText("NIK");

        jlblxNIK.setText(":");

        jlblpass.setText("Password");

        jlblxpass.setText(":");

        jBtnChange.setForeground(new java.awt.Color(35, 37, 253));
        jBtnChange.setText("OK");
        jBtnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnChangeActionPerformed(evt);
            }
        });

        jBtnVerifikasi.setForeground(new java.awt.Color(35, 37, 253));
        jBtnVerifikasi.setText("OK");
        jBtnVerifikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVerifikasiActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(254, 65, 42));
        jLabel2.setText("Masukkan Password Lama Anda");

        jLabel3.setForeground(new java.awt.Color(254, 65, 42));
        jLabel3.setText("Masukkan Password Baru Anda");

        jlblKet.setForeground(new java.awt.Color(254, 65, 42));
        jlblKet.setText("Panjang karakter password tidak boleh kurang dari 5 digit");

        jlblKet1.setForeground(new java.awt.Color(254, 65, 42));
        jlblKet1.setText("dan tidak boleh lebih dari 15 digit !");

        jLabel4.setForeground(new java.awt.Color(254, 65, 42));
        jLabel4.setText("Masukkan Kembali Password Baru Anda");

        jBtnConfirm.setForeground(new java.awt.Color(35, 37, 253));
        jBtnConfirm.setText("OK");
        jBtnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(71, 71, 71))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlbltampung, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnConfirm)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnChange)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnVerifikasi))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlblKet))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlblKet1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlblNIK)
                                    .addComponent(jlblpass))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jlblxNIK)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlblNIKU, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jlblxpass)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldPass, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlblNIK)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblxpass)
                                .addComponent(jTxtFldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlblpass)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlblNIKU, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblxNIK))))
                .addGap(18, 18, 18)
                .addComponent(jlblKet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlblKet1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnChange)
                    .addComponent(jBtnVerifikasi)
                    .addComponent(jlbltampung, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnConfirm))
                .addContainerGap())
        );

        jMenuBar1.setBackground(new java.awt.Color(158, 241, 101));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jBtnVerifikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVerifikasiActionPerformed
        // TODO add your handling code here:
        verifikasiPassLama();
    }//GEN-LAST:event_jBtnVerifikasiActionPerformed

    private void jBtnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnChangeActionPerformed
        // TODO add your handling code here:
        if(jTxtFldPass.getText().length()<5) {
            JOptionPane.showMessageDialog(null, "Password tidak boleh kurang dari 5 digit !");
            jTxtFldPass.setText("");
            visibleBtn(true);
            jTxtFldPass.requestFocus();
          }
          else if(jTxtFldPass.getText().length()>15) {
            JOptionPane.showMessageDialog(null, "Password tidak boleh lebih dari 15 digit !");
            jTxtFldPass.setText("");
            visibleBtn(true);
            jTxtFldPass.requestFocus();
          }
          else {
            b = jTxtFldPass.getText();
            a = b;
            jlbltampung.setText(a);
            jTxtFldPass.setText("");
            jLabel4.setVisible(true);
            jBtnConfirm.setVisible(true);
                        
            visibleBtn(false);
            jBtnVerifikasi.setVisible(false);
            jLabel2.setVisible(false);            
          }
    }//GEN-LAST:event_jBtnChangeActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        aplikasiInventory.Start.setVisible(true);
        aplikasiInventory.loadNIK(aplikasiInventory.Start.jlblstart_jpg);
        aplikasiInventory.vsNIK = aplikasiInventory.Start.jlblstart_jpg.getText();
        aplikasiInventory.HakAkses();
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jBtnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmActionPerformed
        // TODO add your handling code here:
        a = jlbltampung.getText();
        b = jTxtFldPass.getText();
        
        if (a.equals(b)) {
          ok = JOptionPane.showConfirmDialog(null, "Anda yakin ingin merubah password?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);
          if (ok == 0){
            GantiPass(); 
            jLabel4.setVisible(false);
            jBtnConfirm.setVisible(false);
          }
          else
          {  visibleBtn(false); 
             jTxtFldPass.setText("");
             jLabel4.setVisible(false);
             jBtnConfirm.setVisible(false);
          }  
        }
         else 
        {
           JOptionPane.showMessageDialog(null, "Password baru yang anda masukkan tidak cocok!");
           jTxtFldPass.setText("");
           jTxtFldPass.requestFocus();
        }
    }//GEN-LAST:event_jBtnConfirmActionPerformed

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
            java.util.logging.Logger.getLogger(ubah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ubah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ubah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ubah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ubah().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnChange;
    private javax.swing.JButton jBtnConfirm;
    private javax.swing.JButton jBtnVerifikasi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jTxtFldPass;
    private javax.swing.JLabel jlblKet;
    private javax.swing.JLabel jlblKet1;
    private javax.swing.JLabel jlblNIK;
    private javax.swing.JLabel jlblNIKU;
    private javax.swing.JLabel jlblpass;
    private javax.swing.JLabel jlbltampung;
    private javax.swing.JLabel jlblxNIK;
    private javax.swing.JLabel jlblxpass;
    // End of variables declaration//GEN-END:variables
}
