package com.pengadaan.barang.produk;

import Util.CategoryBarangDv;
import com.pengadaan.barang.kategory.*;
import com.pengadaan.barang.PengadaanBarang;
import com.pengadaan.barang.start;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class product extends javax.swing.JFrame {

    private Integer row;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable();
    private DefaultTableModel DfltTblMode;
    private TableColumn column;   
    private String e,r,i,k,a,satuan;
    private int idcategory;
    private Double hrg_jual,hrg_beli;
    private String t;
    private String j;
    private String l;
    private String b;
    private String o;
    
    public product() {
        initComponents();
        aplikasiInventory.konekkeDatabase();
        tampilDataKeTabel();
        enableBtn(false);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(false);
        jTxtFldKD_BARANG2.setVisible(false);
        jTxtFldNM_BARANG2.setVisible(false);
        listCategoryBarang();
       // listSatuan();
    }

    private void listSatuan(){
        jCmbJBTN2.removeAllItems();
        
        try {
            CategoryBarangDv categoryDv = (CategoryBarangDv) jCmbJBTN.getSelectedItem();
            String sql = "Select * from mst_satuan where category_barang_id="+categoryDv.getId();
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set;
            set = st.executeQuery(sql);
            int no = 0;
            while (set.next()) {
                jCmbJBTN2.addItem(new CategoryBarangDv(set.getInt("id"),set.getString("name")));
              }
        } catch (Exception ex) {
            System.out.println("mask gagal "+ex.getMessage());
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }
    
    private void listCategoryBarang(){
        
        try {
            
        String sql = "Select * from mst_category_barang";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set;
            set = st.executeQuery(sql);
            int no = 0;
            while (set.next()) {
                jCmbJBTN.addItem(new CategoryBarangDv(set.getInt("id"),set.getString("nm_ctg")));
              }
        } catch (SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }
    
    private void clearTEXT() {
        jTxtFldKD_BARANG.setText("");
        jTxtFldNM_BARANG.setText("");
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
    
    private void enviBtnNew(boolean x){
        jBtnNew.setEnabled(x);
        jBtnNew.setVisible(x);
    }
    
    private void enviBtnSave2(boolean x) {
        jBtnSave2.setEnabled(x);
        jBtnSave2.setVisible(x);
    }
    
    private void enableField(boolean x) {
        jTxtFldKD_BARANG.setEnabled(x);
        jTxtFldNM_BARANG.setEnabled(x);
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
       r = jTxtFldKD_BARANG.getText();
       i = jTxtFldNM_BARANG.getText();
       
       CategoryBarangDv category = (CategoryBarangDv) jCmbJBTN.getSelectedItem();
       idcategory = category.getId();
       satuan = jCmbJBTN2.getSelectedItem().toString();
       hrg_beli = Double.valueOf(jTxtFldHRG_BELI.getText());
       hrg_jual = Double.valueOf(jTxtFldHRG_JUAL.getText());
       
       
       try {
       if (r.equals("") || i.equals("")) 
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
           clearTEXT();
           }
       else {    
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "insert into mst_barang"+
                       "(kd_barang, nm_barang,status,satuan_barang,ctg_barang_id,hrg_jual_barang,hrg_beli_barang) values ('"+ r +"','"+ i +"','A','"+satuan+"','"+idcategory+"',"+hrg_jual+","+hrg_beli+")" );
                 
                 tampilDataKeTabel();      
                 JOptionPane.showMessageDialog(this,"Data berhasil disimpan");
               }
           }
                    catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Data gagal disimpan! : " +ex);
                    }
               }
    
    private void kondisiEdit() { 
       r = jTxtFldKD_BARANG.getText();
       i = jTxtFldNM_BARANG.getText();
       t = jTxtFldHRG_JUAL.getText();
       e = jTxtFldHRG_BELI.getText();
//       m = jCmbJBTN.getSelectedItem();
//       n = jCmbJBTN.getSelectedItem();
       k = jTxtFldKD_BARANG2.getText();
       a = jTxtFldNM_BARANG2.getText();
       j = jTxtFldHRG_JUAL2.getText();
       o = jTxtFldHRG_BELI2.getText();
       CategoryBarangDv category = (CategoryBarangDv) jCmbJBTN.getSelectedItem();
       idcategory = category.getId();
       satuan = jCmbJBTN2.getSelectedItem().toString();
       
       try {
       if (r.equals("") || i.equals("") || t.equals("") || e.equals("")) 
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
           clearTEXT();
           }
       else if (r.equals(k) && i.equals(a) && t.equals(j) && e.equals(o)) 
           {
           JOptionPane.showMessageDialog(null, "Tidak ada data yang diperbaharui!");
           }
       else {    
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "update mst_barang set "+
                       "kd_barang       = "+"'"+ r +"', "+
                       "nm_barang       = "+"'"+ i +"', "+
                       "ctg_barang_id = "+"'"+ idcategory +"',"+
                       "satuan_barang = "+"'"+ satuan +"',"+ 
                       "hrg_jual_barang = "+"'"+ t +"',"+ 
                       "hrg_beli_barang = "+"'"+ e +"' "+
                               
                       "where kd_barang = '"+ r +"'");
       
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
            final Object[] field = {"No","Kode Barang","Nama Barang","Category Barang", "Satuan Barang", "Harga Beli", "Harga Jual","id Barang"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);
        
                   
            String sql = "Select * from mst_barang";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);
//            jTabel.re
            int no = 0;
            String sending = "";
            Object[] sendings = null;
            while (set.next()) {
                no++;
                System.out.println("id barang : "+set.getInt("ctg_barang_id"));
                String namectg = null;
                Integer idctg = null;
                String sqls = "Select * from mst_category_barang where id="+set.getInt("ctg_barang_id");
                Statement sts = aplikasiInventory.config.getConnection().createStatement();
                ResultSet sets = sts.executeQuery(sqls);
                while (sets.next()) {
                    namectg = sets.getString("nm_ctg");
                    idctg = sets.getInt("id");
                }
                System.out.println("id barang : "+namectg);
//                 CategoryBarangDv category = (CategoryBarangDv) jCmbJBTN.getName(set.getInt("ctg_barang_id"));
//                 namectg = jCmbJBTN.get
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("kd_barang");
                String kolom3 = set.getString("nm_barang");
                String kolom4 = namectg;
                Integer kolom41 = idctg;
                String kolom5 = set.getString("satuan_barang");
                String kolom6 = String.valueOf(set.getDouble("hrg_jual_barang"));
                String kolom7 = String.valueOf(set.getDouble("hrg_beli_barang"));
                 String[] datan = {kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom41.toString()};
//                sending = sending + "{"+kolom1+","+kolom2+","+kolom3+","+kolom4+","+kolom5+","+
//                        kolom6+","+kolom7+","+kolom41+"}";
                DfltTblMode.addRow(datan);
               /*sendings = new Object[8];
               sendings[0] = kolom1;
               sendings[1] = kolom2;
               sendings[2] = kolom3;
              sendings[3] = kolom4;
              sendings[4] = kolom5;
              sendings[5] = kolom6;
              sendings[6] = kolom7;
              sendings[7] = kolom41;
                */
            }
//            Object[] datas = {sending};
            /*final Object[][] data = {sendings};
            TableModel dataModel = new AbstractTableModel() {

                @Override
                public int getRowCount() { return field.length;}

                @Override
                public int getColumnCount() { return data.length; }

                @Override
                public Object getValueAt(int row, int col) {
                   return data[row][col];
                }
                public String getColumName(int column){return (String) field[column];}
                public Class getColumnClass(int col){
                    return getValueAt(0,col).getClass();
                }
                public void setValueAt(Object aValue,int row, int column){
                    data[row][column] = aValue;
                }
            };
            jTabel.setModel(dataModel);
            */
            jTabel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            jTabel.getColumnModel().getColumn(7).setMaxWidth(0);
            jTabel.getColumnModel().getColumn(7).setMinWidth(0);
            column = jTabel.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel.getColumnModel().getColumn(1);
            column.setPreferredWidth(156);
            column = jTabel.getColumnModel().getColumn(2);
            column.setPreferredWidth(200);
            column = jTabel.getColumnModel().getColumn(3);
            column.setPreferredWidth(200);
            column = jTabel.getColumnModel().getColumn(4);
            column.setPreferredWidth(200);
            
            
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
//        String kolom5 = jTabel.getValueAt(row,4).toString();
        String kolom6 = jTabel.getValueAt(row,5).toString();
        String kolom7 = jTabel.getValueAt(row,6).toString();
        String kolom8 = jTabel.getValueAt(row,7).toString();
       
        jTxtFldKD_BARANG.setText(kolom2);
     //   jTxtFldKD_BARANG2.setText(kolom2);
        jTxtFldNM_BARANG.setText(kolom3); 
     //   jTxtFldNM_BARANG2.setText(kolom3);
        System.out.println(kolom4);
        jCmbJBTN.setEditable(true);
        jCmbJBTN2.setEditable(true);
        
        
//        jCmbJBTN.addItem(new CategoryBarangDv(Integer.parseInt(kolom8),kolom4));
//        CategoryBarangDv categ = new CategoryBarangDv(Integer.parseInt(kolom8),kolom4);
        
        //CategoryBarangDv categoryDv = jCmbJBTN.setSelectedItem(categ);
        
        jCmbJBTN.setSelectedItem(new CategoryBarangDv(Integer.parseInt(kolom8),kolom4));
       // jCmbJBTN.setSelectedItem(kolom8);
//        jCmbJBTN2.setSelectedItem(kolom5.toString());
        jCmbJBTN.setEditable(false);
        jCmbJBTN2.setEditable(false);
        
      jTxtFldHRG_JUAL.setText(kolom6);
      jTxtFldHRG_BELI.setText(kolom7);
    }
    
    private void kondisiHapus() {
       e = jTxtFldKD_BARANG.getText();
            
       try {
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            st.executeUpdate(
            " delete from mst_barang where kd_barang ='"+ e +"'");
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
            Object[] field = {"No","Kode Barang","Nama Barang","Satuan Barang"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);
            
            String sql = "Select * from mst_barang where kd_barang like '%" + jTextField1.getText() + "%'" +
                         "or nm_barang like '%" + jTextField1.getText() + "%'";
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

        jlblNM_BARANG = new javax.swing.JLabel();
        jlblxKD_BARANG = new javax.swing.JLabel();
        jlblxNM_BARANG = new javax.swing.JLabel();
        jTxtFldKD_BARANG = new javax.swing.JTextField();
        jTxtFldNM_BARANG = new javax.swing.JTextField();
        jBtnSave = new javax.swing.JButton();
        jBtnEdit = new javax.swing.JButton();
        jBtnDlt = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBtnNew = new javax.swing.JButton();
        jTxtFldKD_BARANG2 = new javax.swing.JTextField();
        jBtnSave2 = new javax.swing.JButton();
        jTxtFldNM_BARANG2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jBtnCari = new javax.swing.JButton();
        jCmbJBTN = new javax.swing.JComboBox();
        jlblJBTN = new javax.swing.JLabel();
        jlblxJBTN = new javax.swing.JLabel();
        jlblJBTN2 = new javax.swing.JLabel();
        jCmbJBTN2 = new javax.swing.JComboBox();
        jlblxJBTN1 = new javax.swing.JLabel();
        jlblJBTN1 = new javax.swing.JLabel();
        jlblxNM_BARANG1 = new javax.swing.JLabel();
        jTxtFldHRG_JUAL = new javax.swing.JTextField();
        jlblxNM_BARANG2 = new javax.swing.JLabel();
        jTxtFldHRG_BELI = new javax.swing.JTextField();
        jlblJBTN4 = new javax.swing.JLabel();
        jlblJBTN3 = new javax.swing.JLabel();
        jTxtFldHRG_BELI2 = new javax.swing.JTextField();
        jTxtFldHRG_JUAL2 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMnKembali = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form Barang");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlblNM_BARANG.setText("Nama Barang*");

        jlblxKD_BARANG.setText(":");

        jlblxNM_BARANG.setText(":");

        jTxtFldNM_BARANG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldNM_BARANGActionPerformed(evt);
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Kode Barang", "Nama Barang", "Category Barang", "Satuan Barang"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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

        jLabel1.setText("Cari Data Berdasarkan Kode / Nama Barang :");

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

        jCmbJBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmbJBTNActionPerformed(evt);
            }
        });

        jlblJBTN.setText("Kategori Barang*");

        jlblxJBTN.setText(":");

        jlblJBTN2.setText("Satuan Barang*");

        jCmbJBTN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmbJBTN2ActionPerformed(evt);
            }
        });

        jlblxJBTN1.setText(":");

        jlblJBTN1.setText("Harga Jual*");

        jlblxNM_BARANG1.setText(":");

        jTxtFldHRG_JUAL.setText("0");
        jTxtFldHRG_JUAL.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTxtFldHRG_JUAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldHRG_JUALActionPerformed(evt);
            }
        });

        jlblxNM_BARANG2.setText(":");

        jTxtFldHRG_BELI.setText("0");
        jTxtFldHRG_BELI.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTxtFldHRG_BELI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFldHRG_BELIActionPerformed(evt);
            }
        });

        jlblJBTN4.setText("Kode Barang*");

        jlblJBTN3.setText("Harga Belil*");

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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
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
                                    .addComponent(jlblNM_BARANG)
                                    .addComponent(jlblJBTN)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jlblJBTN1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlblJBTN2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlblJBTN3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jlblxNM_BARANG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlblxKD_BARANG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlblxJBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlblxJBTN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jlblxNM_BARANG1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblxNM_BARANG2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtFldKD_BARANG, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldKD_BARANG2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtFldNM_BARANG, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldNM_BARANG2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jCmbJBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCmbJBTN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtFldHRG_JUAL, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldHRG_JUAL2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTxtFldHRG_BELI, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTxtFldHRG_BELI2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jlblJBTN4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblxKD_BARANG)
                            .addComponent(jTxtFldKD_BARANG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldKD_BARANG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTxtFldNM_BARANG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblxNM_BARANG)
                            .addComponent(jlblNM_BARANG)
                            .addComponent(jTxtFldNM_BARANG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblxJBTN)
                            .addComponent(jlblJBTN)
                            .addComponent(jCmbJBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblxJBTN1)
                            .addComponent(jlblJBTN2)
                            .addComponent(jCmbJBTN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblJBTN1)
                            .addComponent(jlblxNM_BARANG1)
                            .addComponent(jTxtFldHRG_JUAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFldHRG_JUAL2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlblJBTN3)
                            .addComponent(jTxtFldHRG_BELI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlblxNM_BARANG2)
                            .addComponent(jTxtFldHRG_BELI2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jlblJBTN4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
       jTxtFldKD_BARANG.requestFocus();
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

    private void jBtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditActionPerformed
        // TODO add your handling code here:
        
       enableField(true);
       enableBtn(false);
       enviBtnSave(false);
       enviBtnSave2(true);
       enviBtnNew(false);
       listSatuan();
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
       enableField(false);
       enableBtn(true);
       enviBtnSave(false);
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

    private void jBtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCariActionPerformed
        // TODO add your handling code here:
        tampilDataKeTabelcari();
    }//GEN-LAST:event_jBtnCariActionPerformed

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

    private void jCmbJBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbJBTNActionPerformed
                // TODO add your handling code here:
        listSatuan();
    }//GEN-LAST:event_jCmbJBTNActionPerformed

    private void jCmbJBTN2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbJBTN2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCmbJBTN2ActionPerformed

    private void jTxtFldNM_BARANGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldNM_BARANGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldNM_BARANGActionPerformed

    private void jTxtFldHRG_JUALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldHRG_JUALActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldHRG_JUALActionPerformed

    private void jTxtFldHRG_BELIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFldHRG_BELIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFldHRG_BELIActionPerformed

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
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(product.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new product().setVisible(true);
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
    private javax.swing.JComboBox jCmbJBTN2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTxtFldHRG_BELI;
    private javax.swing.JTextField jTxtFldHRG_BELI2;
    private javax.swing.JTextField jTxtFldHRG_JUAL;
    private javax.swing.JTextField jTxtFldHRG_JUAL2;
    private javax.swing.JTextField jTxtFldKD_BARANG;
    private javax.swing.JTextField jTxtFldKD_BARANG2;
    private javax.swing.JTextField jTxtFldNM_BARANG;
    private javax.swing.JTextField jTxtFldNM_BARANG2;
    private javax.swing.JLabel jlblJBTN;
    private javax.swing.JLabel jlblJBTN1;
    private javax.swing.JLabel jlblJBTN2;
    private javax.swing.JLabel jlblJBTN3;
    private javax.swing.JLabel jlblJBTN4;
    private javax.swing.JLabel jlblNM_BARANG;
    private javax.swing.JLabel jlblxJBTN;
    private javax.swing.JLabel jlblxJBTN1;
    private javax.swing.JLabel jlblxKD_BARANG;
    private javax.swing.JLabel jlblxNM_BARANG;
    private javax.swing.JLabel jlblxNM_BARANG1;
    private javax.swing.JLabel jlblxNM_BARANG2;
    // End of variables declaration//GEN-END:variables
}
