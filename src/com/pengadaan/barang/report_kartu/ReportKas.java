package com.pengadaan.barang.report_kartu;

import Util.Util;
import com.pengadaan.barang.transaksi_in.*;
import com.pengadaan.barang.kategory.*;
import com.pengadaan.barang.PengadaanBarang;
import com.pengadaan.barang.produk.CategoryBarangDv;
import com.pengadaan.barang.produk.product;
import com.pengadaan.barang.start;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ReportKas extends javax.swing.JFrame {

    private Integer row;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable();
    private DefaultTableModel DfltTblMode;
    private TableColumn column;   
    private String e,r,i,k,a,startdate,enddate;
    private int idcategory;
    
    public ReportKas() {
        initComponents();
        aplikasiInventory.konekkeDatabase();
//        tampilDataKeTabel();
        enableBtn(false);
        enviBtnSave(true);
        enviBtnSave2(false);
        enviBtnNew(true);
//        jBtnNew.setEnabled(true);
        
      jXDatePicker1.setDate(Calendar.getInstance().getTime());
        jXDatePicker1.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        jXDatePicker2.setDate(Calendar.getInstance().getTime());
        jXDatePicker2.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
       listCategoryBarang();
        
        

       
        
    }
    
    private void listCategoryBarang(){
        
        try {
            
        String sql = "Select * from mst_category_barang";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set;
            set = st.executeQuery(sql);
            int no = 0;
            while (set.next()) {
                jComboBox1.addItem(new CategoryBarangDv(set.getInt("id"),set.getString("nm_ctg")));
              }
        } catch (SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }
    
    private void listTypeBarang(){
       
        try {
             String sql2 = "Select * from mst_type_barang";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set;
            set = st.executeQuery(sql2);
            while (set.next()) {
//                System.out.println("tes"+set.getInt("id"));
//                jCmbJBTN.addItem(new TypeBarangDv(set.getInt("id"), set.getString("name")));

            }
//            jCmbJBTN.setSelectedIndex(2);
            
           // jCmbJBTN.getSelectedItem();
        } catch (SQLException ex) {
            Logger.getLogger(ReportKas.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }

    private void clearTEXT() {
//        jTxtFldKD_CTGRY.setText("");
//        jTxtFldNM_CTGRY.setText("");
    }
    
    private void enableBtn(boolean x) {
//        jBtnDlt.setEnabled(x);
////        jBtnEdit.setVisible(true);
//        jBtnEdit.setEnabled(x);
    }
    
    private void enviBtnSave(boolean x) {
//        jBtnSave.setEnabled(x);
//        jBtnSave.setVisible(x);
    }
    
    private void enviBtnNew(boolean x){
//        jBtnNew.setEnabled(x);
//        jBtnNew.setVisible(x);
    }
    
    private void enviBtnSave2(boolean x) {
//        jBtnSave2.setEnabled(x);
//        jBtnSave2.setVisible(x);
    }
    
    private void enableField(boolean x) {
//        jTxtFldKD_CTGRY.setEnabled(x);
//        jTxtFldNM_CTGRY.setEnabled(x);
    }
    
    private void tampilDataKeTabel() {
        jTabel = jTable1;
        tabelModel(jTabel);
    }
    
    private void tampilDataKeTabelcari() {
        jTabel = jTable1;
        cari(jTabel);
    }
    
    
    private void kondisiEdit() { 
//       r = jTxtFldKD_CTGRY.getText();
//       i = jTxtFldNM_CTGRY.getText();
//       k = jTxtFldKD_CTGRY2.getText();
//       a = jTxtFldNM_CTGRY2.getText();
       
       try {
       if (r.equals("") || i.equals("")) 
           {
           JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
           clearTEXT();
           }
       else if (r.equals(k) && i.equals(a)) 
           {
           JOptionPane.showMessageDialog(null, "Tidak ada data yang diperbaharui!");
           }
       else {    
                 Statement st = aplikasiInventory.config.getConnection().createStatement();  
                 st.executeUpdate(
                       "update mst_category_barang set "+
                       "kd_ctg = "+"'"+ r +"', "+
                       "nm_ctg = "+"'"+ i +"' "+ 
                       "where kd_ctg = '"+ k +"'");
       
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
            Object[] field = {"No","Date","No. Transaksi","Divisi","Keterangan"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);
            
            String sql = "Select * from trx_pemasukan";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String sqls = "Select * from mst_divisi where id="+set.getInt("divisi_id");
                Statement sts = aplikasiInventory.config.getConnection().createStatement();
                ResultSet sets = sts.executeQuery(sqls);
                String divisiname = "";
                 while (sets.next()) {
                     divisiname = sets.getString("name");
                 }
                 String sqls2 = "Select * from mst_divisi where id="+set.getInt("divisi_id");
                Statement sts2 = aplikasiInventory.config.getConnection().createStatement();
                ResultSet sets2 = sts2.executeQuery(sqls2);
                if(sets != null){
                    String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("trx_date_pemasukan");
                String kolom3 = set.getString("trx_no_pemasukan");
                String kolom4 = divisiname;
                String kolom5 = set.getString("trx_desc_pemasukan");
                String[] data = {kolom1, kolom2, kolom3,kolom4,kolom5};
                DfltTblMode.addRow(data);
                }
                
            }
            
            jTabel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel.getColumnModel().getColumn(1);
            column.setPreferredWidth(156);
            column = jTabel.getColumnModel().getColumn(2);
            column.setPreferredWidth(240);
            
            column = jTabel.getColumnModel().getColumn(3);
            column.setPreferredWidth(240);
            
            column = jTabel.getColumnModel().getColumn(4);
            column.setPreferredWidth(240);
            
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
        
    }
    
    
    private void kondisiHapus() {
//       e = jTxtFldKD_CTGRY.getText();
            
       try {
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            st.executeUpdate(
            " delete from trx_pemasukan where kd_ctg ='"+ e +"'");
            clearTEXT();
            tampilDataKeTabel();
            
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
       }
        catch (SQLException ex) {
              JOptionPane.showMessageDialog(this,"Data gagal dihapus: " +ex);
        }
    }    
    
    private void mcExcel(Sheet sheet,Cell cell,Row rowss,int rw,int cl,String text){
        rowss = sheet.createRow((short) rw);
        cell = rowss.createCell((short) cl);
        cell.setCellValue(text);
    }
    
    private void mcExcelZero(Sheet sheet,Cell cell,Row rowss,int rw,int cl,String text){
        rowss = sheet.createRow((short) rw);
        cell = rowss.createCell((short) 0);
        cell.setCellValue(text);
    }
    
    private void exportExcelKartu() throws FileNotFoundException, IOException{
        
        Util util = new Util();
        
         Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet1");

        Row rowss = sheet.createRow((short) 0);
        Cell cell = rowss.createCell((short) 0);
        cell.setCellValue("KARTU PERSEDIAAN BARANG");
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cell.setCellStyle(cellStyle);
        
        rowss = sheet.createRow((short) 1);
        rowss.createCell(0).setCellValue("SKPD");
        rowss.createCell(1).setCellValue(": Dinas Pendapatan Pengelolaan Keuangan dan Aset Daerah");
        rowss = sheet.createRow((short) 2);
        rowss.createCell(0).setCellValue("KAB/KOTA");
        rowss.createCell(1).setCellValue(": TANGERANG SELATAN");
        rowss = sheet.createRow((short) 3);
        rowss.createCell(0).setCellValue("PROVINSI");
        rowss.createCell(1).setCellValue(": BANTEN");
        
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dt1.format(jXDatePicker1.getDate()));
        startdate = dt1.format(jXDatePicker1.getDate());
        SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd");
        enddate = dt2.format(jXDatePicker2.getDate());
        
        rowss = sheet.createRow((short) 5);
        rowss.createCell(0).setCellValue("GUDANG");
        rowss.createCell(1).setCellValue(":");
        rowss = sheet.createRow((short) 6);
        rowss.createCell(0).setCellValue("CATEGORY BARANG");
        rowss.createCell(1).setCellValue(": "+jComboBox1.getSelectedItem().toString());
        rowss = sheet.createRow((short) 7);
        rowss.createCell(0).setCellValue("SATUAN");
        rowss.createCell(1).setCellValue(":");
        rowss = sheet.createRow((short) 8);
        rowss.createCell(0).setCellValue("PERIODE");
        rowss.createCell(1).setCellValue(": "+util.getMonth(startdate)+" - "+util.getMonth(enddate));
        
        CellStyle style = wb.createCellStyle(); //Create new style
            style.setWrapText(true);
        
        rowss = sheet.createRow((short)10);
        cell = rowss.createCell(0);
        
        rowss.setCellValue("NO");
        rowss.createCell(1).setCellValue("No./Tgl. Surat Dasar Penerimaan/ Pengeluaran");
//        rowss.set
        sheet.addMergedRegion(new CellRangeAddress(10,11,0,0));
        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                0, //last row  (0-based)
                0, //first column (0-based)
                10 //last column  (0-based)
        ));
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        // Write the output to a file
         
//           FileOutputStream fileOut = new FileOutputStream("D:\\excel\\workbook-"+strDate+".xls");
                   FileOutputStream fileOut = new FileOutputStream("1.xls");

//            fileOut
            
            wb.write(fileOut);
            fileOut.close();
        
       
    }
    
    private void cari(JTable jTabel){
    try {
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dt1.format(jXDatePicker1.getDate()));
        startdate = dt1.format(jXDatePicker1.getDate());
        SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd");
        enddate = dt2.format(jXDatePicker2.getDate());
        CategoryBarangDv category = (CategoryBarangDv) jComboBox1.getSelectedItem();
       idcategory = category.getId();
       System.out.println(enddate);
        
            Object[] field = {"No","Date","No. Transaksi","Uraian","Stock","Harga Satuan","Total Harga"};
            DfltTblMode = new DefaultTableModel(null, field);
//            DfltTblMode = new DefaultTableModel();
            
            jTabel.setModel(DfltTblMode);
//            TableColumnModel cm = jTabel.getColumnModel();
//            ColumnGroup g_name = new ColumnGroup("Barang-Barang");
//            g_name.add(cm.getColumn(1));
//            g_name.add(cm.getColumn(2));
//            GroupableTableHeader header = (GroupableTableHeader)jTabel.getTableHeader();
//            header.addColumnGroup(g_name);
//            jTabel.setTableHeader(header);
//            DfltTblMode.get
            
           // String sql = "SELECT * FROM trx_pemasukan_item  tpi JOIN trx_pemasukan tp WHERE tpi.trx_pemasukan_id=tpi.trx_pemasukan_id and tp.trx_date_pemasukan BETWEEN '"+startdate+"' and '"+enddate+"'";
           String sql = "SELECT SUM(tpi.trx_qty_pemasukan_item) as totalqty, tpi.trx_price_pemasukan_item as price,SUM(tpi.trx_total_pemasukan_item)"
                + " as totalall, tpi.trx_honor_pemasukan_item as honor,tp.trx_date_pemasukan as date , tp.trx_no_pemasukan as code"
                + ", tpi.barang_id as barangid FROM mst_barang mb JOIN trx_pemasukan_item tpi ON  mb.id=tpi.barang_id JOIN trx_pemasukan tp"
                + " WHERE tp.trx_date_pemasukan BETWEEN '"+startdate+"' and '"+enddate+"' and mb.ctg_barang_id="+idcategory+" GROUP BY mb.id"
                + "";
           
           System.out.println(sql);
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);
            
            int no = 0;
            while (set.next()) {
                no++;
                String sqls = "Select * from mst_barang where id="+set.getInt("barangid");
                Statement sts = aplikasiInventory.config.getConnection().createStatement();
                ResultSet sets = sts.executeQuery(sqls);
                String divisiname = "";
                 while (sets.next()) {
                     divisiname = sets.getString("nm_barang");
                 }
               
                    String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("date");
                String kolom3 = set.getString("code");
                String kolom4 = divisiname;
                String kolom5 = set.getString("totalqty");
                String kolom6 = set.getString("price");
                String kolom7 = set.getString("totalall");
                String[] data = {kolom1, kolom2, kolom3,kolom4,kolom5,kolom6,kolom7};
                DfltTblMode.addRow(data);
                
            }
            
            jTabel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel.getColumnModel().getColumn(1);
            column.setPreferredWidth(156);
            column = jTabel.getColumnModel().getColumn(2);
            column.setPreferredWidth(240);
            
            column = jTabel.getColumnModel().getColumn(3);
            column.setPreferredWidth(240);
            
            column = jTabel.getColumnModel().getColumn(4);
            column.setPreferredWidth(240);
                
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jBtnCari = new javax.swing.JButton();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMnKembali = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Form List Transaksi");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Date", "No Transaksi", "Uraian", "Stock Masuk", "Harga Satuan", "Total Harga"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(30);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jLabel1.setText("Start Date");

        jBtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jBtnCari.setText("Search");
        jBtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCariActionPerformed(evt);
            }
        });

        jLabel2.setText("End Date");

        jLabel3.setText("Category Barang");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/excel.png"))); // NOI18N
        jButton1.setText("Export Excel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jBtnCari)))
                            .addComponent(jButton1))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
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

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
       klikTabel(jTabel);
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        new start().setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void jCmbJBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmbJBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCmbJBTNActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            exportExcelKartu();
        } catch (IOException ex) {
            Logger.getLogger(ReportKas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ReportKas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportKas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportKas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportKas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ReportKas().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCari;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMnKembali;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    // End of variables declaration//GEN-END:variables
}