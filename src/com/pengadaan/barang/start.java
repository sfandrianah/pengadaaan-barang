package com.pengadaan.barang;

import com.pengadaan.barang.kategory.kategori;
import com.pengadaan.barang.kib.KIB;
import com.pengadaan.barang.produk.product;
import com.pengadaan.barang.report_kartu.ReportKas;
import com.pengadaan.barang.transaksi_in.TransaksiIn;
import com.pengadaan.barang.transaksi_in.listTransaksiIn;
import com.pengadaan.barang.transaksi_out.listTransaksiOut;
import com.pengadaan.barang.trx_in_asset.TransaksiAssetIn;
import javax.swing.JOptionPane;

public class start extends javax.swing.JFrame {
 
    private int ok;
    
    
    public start() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblstart_jpg = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMnFile = new javax.swing.JMenu();
        jMnItmubah = new javax.swing.JMenuItem();
        jMnItmInfo = new javax.swing.JMenuItem();
        jMnItmExit = new javax.swing.JMenuItem();
        jMnMaster = new javax.swing.JMenu();
        jMnItmCTGRY = new javax.swing.JMenuItem();
        jMnItmPRD = new javax.swing.JMenuItem();
        jMnTRN = new javax.swing.JMenu();
        jMnItmTRN_OUT = new javax.swing.JMenuItem();
        jMnItmTRN_IN = new javax.swing.JMenuItem();
        jMnItmTRN_AST = new javax.swing.JMenuItem();
        jMnPopulasi = new javax.swing.JMenu();
        jMnItmRPT_KAS = new javax.swing.JMenuItem();
        jMnItmKRT_INV_BRG = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SISTEM APLIKASI PENGADAAN BARANG");
        setBackground(new java.awt.Color(217, 245, 254));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlblstart_jpg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/halaman-awal.jpg"))); // NOI18N
        jlblstart_jpg.setMaximumSize(new java.awt.Dimension(500, 500));
        jlblstart_jpg.setMinimumSize(new java.awt.Dimension(500, 500));

        jMenuBar1.setBackground(new java.awt.Color(157, 189, 254));

        jMnFile.setText("File");

        jMnItmubah.setText("Ubah Password");
        jMnItmubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnItmubahMousePressed(evt);
            }
        });
        jMnFile.add(jMnItmubah);

        jMnItmInfo.setText("Help");
        jMnItmInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnItmInfoMousePressed(evt);
            }
        });
        jMnFile.add(jMnItmInfo);

        jMnItmExit.setText("Keluar");
        jMnItmExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnItmExitMousePressed(evt);
            }
        });
        jMnFile.add(jMnItmExit);

        jMenuBar1.add(jMnFile);

        jMnMaster.setText("Master");

        jMnItmCTGRY.setText("Kategori");
        jMnItmCTGRY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnItmCTGRYMousePressed(evt);
            }
        });
        jMnMaster.add(jMnItmCTGRY);

        jMnItmPRD.setText("Barang");
        jMnItmPRD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnItmPRDMousePressed(evt);
            }
        });
        jMnItmPRD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnItmPRDActionPerformed(evt);
            }
        });
        jMnMaster.add(jMnItmPRD);

        jMenuBar1.add(jMnMaster);

        jMnTRN.setText("Transaksi");

        jMnItmTRN_OUT.setText("Transaksi Out");
        jMnItmTRN_OUT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnItmTRN_OUTMousePressed(evt);
            }
        });
        jMnItmTRN_OUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnItmTRN_OUTActionPerformed(evt);
            }
        });
        jMnTRN.add(jMnItmTRN_OUT);

        jMnItmTRN_IN.setText("Transaksi In");
        jMnItmTRN_IN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnItmTRN_INMousePressed(evt);
            }
        });
        jMnTRN.add(jMnItmTRN_IN);

        jMnItmTRN_AST.setText("Transaksi Asset");
        jMnItmTRN_AST.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnItmTRN_ASTMousePressed(evt);
            }
        });
        jMnItmTRN_AST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnItmTRN_ASTActionPerformed(evt);
            }
        });
        jMnTRN.add(jMnItmTRN_AST);

        jMenuBar1.add(jMnTRN);

        jMnPopulasi.setText("Report");
        jMnPopulasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnPopulasiMousePressed(evt);
            }
        });

        jMnItmRPT_KAS.setText("Pengadaan Barang");
        jMnItmRPT_KAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnItmRPT_KASMousePressed(evt);
            }
        });
        jMnItmRPT_KAS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnItmRPT_KASActionPerformed(evt);
            }
        });
        jMnPopulasi.add(jMnItmRPT_KAS);

        jMnItmKRT_INV_BRG.setText("Kartu Inventaris Barang");
        jMnItmKRT_INV_BRG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMnItmKRT_INV_BRGMousePressed(evt);
            }
        });
        jMnItmKRT_INV_BRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMnItmKRT_INV_BRGActionPerformed(evt);
            }
        });
        jMnPopulasi.add(jMnItmKRT_INV_BRG);

        jMenuBar1.add(jMnPopulasi);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblstart_jpg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlblstart_jpg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMnItmPRDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnItmPRDMousePressed
        // TODO add your handling code here:
        new product().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnItmPRDMousePressed

    private void jMnItmCTGRYMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnItmCTGRYMousePressed
        // TODO add your handling code here:
        new kategori().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnItmCTGRYMousePressed

    private void jMnItmTRN_INMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnItmTRN_INMousePressed
        // TODO add your handling code here:        
        new listTransaksiIn().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnItmTRN_INMousePressed

    private void jMnItmTRN_OUTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnItmTRN_OUTMousePressed
        // TODO add your handling code here:
        new listTransaksiOut().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnItmTRN_OUTMousePressed

    private void jMnItmInfoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnItmInfoMousePressed
        // TODO add your handling code here:
        new info().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnItmInfoMousePressed

    private void jMnItmExitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnItmExitMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMnItmExitMousePressed

    private void jMnItmubahMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnItmubahMousePressed
        // TODO add your handling code here:
        new ubah().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnItmubahMousePressed

    private void jMnPopulasiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnPopulasiMousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMnPopulasiMousePressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        ok = JOptionPane.showConfirmDialog(null, "Anda yakin ingin keluar dari aplikasi?", "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);
        if (ok == 0){
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMnItmPRDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnItmPRDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMnItmPRDActionPerformed

    private void jMnItmRPT_KASMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnItmRPT_KASMousePressed
        // TODO add your handling code here:
        new ReportKas().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnItmRPT_KASMousePressed

    private void jMnItmRPT_KASActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnItmRPT_KASActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMnItmRPT_KASActionPerformed

    private void jMnItmTRN_OUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnItmTRN_OUTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMnItmTRN_OUTActionPerformed

    private void jMnItmTRN_ASTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnItmTRN_ASTMousePressed
        // TODO add your handling code here:
        new TransaksiAssetIn().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnItmTRN_ASTMousePressed

    private void jMnItmTRN_ASTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnItmTRN_ASTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMnItmTRN_ASTActionPerformed

    private void jMnItmKRT_INV_BRGMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMnItmKRT_INV_BRGMousePressed
        // TODO add your handling code here:
        new KIB().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMnItmKRT_INV_BRGMousePressed

    private void jMnItmKRT_INV_BRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMnItmKRT_INV_BRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMnItmKRT_INV_BRGActionPerformed

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
            java.util.logging.Logger.getLogger(start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new start().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnFile;
    public javax.swing.JMenuItem jMnItmCTGRY;
    private javax.swing.JMenuItem jMnItmExit;
    private javax.swing.JMenuItem jMnItmInfo;
    public javax.swing.JMenuItem jMnItmKRT_INV_BRG;
    private javax.swing.JMenuItem jMnItmPRD;
    public javax.swing.JMenuItem jMnItmRPT_KAS;
    public javax.swing.JMenuItem jMnItmTRN_AST;
    public javax.swing.JMenuItem jMnItmTRN_IN;
    public javax.swing.JMenuItem jMnItmTRN_OUT;
    private javax.swing.JMenuItem jMnItmubah;
    public javax.swing.JMenu jMnMaster;
    public javax.swing.JMenu jMnPopulasi;
    public javax.swing.JMenu jMnTRN;
    public javax.swing.JLabel jlblstart_jpg;
    // End of variables declaration//GEN-END:variables
}
