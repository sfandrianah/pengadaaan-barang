package com.pengadaan.barang.report_kartu;

import Util.Util;
import com.pengadaan.barang.transaksi_in.*;
import com.pengadaan.barang.kategory.*;
import com.pengadaan.barang.PengadaanBarang;
import Util.CategoryBarangDv;
import com.pengadaan.barang.produk.product;
import com.pengadaan.barang.start;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.codehaus.groovy.tools.shell.ParseCode;

public class ReportKas extends javax.swing.JFrame {

    private Integer row;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable();
    private DefaultTableModel DfltTblMode;
    private TableColumn column;
    private String e, r, i, k, a, startdate, enddate;
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

    private void listCategoryBarang() {

        try {

            String sql = "Select * from mst_category_barang";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set;
            set = st.executeQuery(sql);
            int no = 0;
            while (set.next()) {
                jComboBox1.addItem(new CategoryBarangDv(set.getInt("id"), set.getString("nm_ctg")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(product.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void listTypeBarang() {

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

    private void enviBtnNew(boolean x) {
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

            if (r.equals("") || i.equals("")) {
                JOptionPane.showMessageDialog(null, "Data harus diisi semua!");
                clearTEXT();
            } else if (r.equals(k) && i.equals(a)) {
                JOptionPane.showMessageDialog(null, "Tidak ada data yang diperbaharui!");
            } else {
                Statement st = aplikasiInventory.config.getConnection().createStatement();
                st.executeUpdate(
                        "update mst_category_barang set "
                        + "kd_ctg = " + "'" + r + "', "
                        + "nm_ctg = " + "'" + i + "' "
                        + "where kd_ctg = '" + k + "'");

                tampilDataKeTabel();
                JOptionPane.showMessageDialog(this, "Data berhasil diperbaharui");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Data gagal diperbaharui! : " + ex);
        }
    }

    private void tabelModel(JTable jTabel) {
        try {

            Object[] field = {"No", "Date", "No. Transaksi", "Divisi", "Keterangan"};
            DfltTblMode = new DefaultTableModel(null, field);
            jTabel.setModel(DfltTblMode);

            String sql = "Select * from trx_pemasukan";
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String sqls = "Select * from mst_divisi where id=" + set.getInt("divisi_id");
                Statement sts = aplikasiInventory.config.getConnection().createStatement();
                ResultSet sets = sts.executeQuery(sqls);
                String divisiname = "";
                while (sets.next()) {
                    divisiname = sets.getString("name");
                }
                String sqls2 = "Select * from mst_divisi where id=" + set.getInt("divisi_id");
                Statement sts2 = aplikasiInventory.config.getConnection().createStatement();
                ResultSet sets2 = sts2.executeQuery(sqls2);
                if (sets != null) {
                    String kolom1 = String.valueOf(no).toString();
                    String kolom2 = set.getString("trx_date_pemasukan");
                    String kolom3 = set.getString("trx_no_pemasukan");
                    String kolom4 = divisiname;
                    String kolom5 = set.getString("trx_desc_pemasukan");
                    String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5};
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

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e);
        }
    }

    private void klikTabel(JTable jTabel) {
        jTabel.setRowSelectionAllowed(true);
        row = jTabel.getSelectedRow();
        String kolom1 = jTabel.getValueAt(row, 0).toString();
        String kolom2 = jTabel.getValueAt(row, 1).toString();
        String kolom3 = jTabel.getValueAt(row, 2).toString();

    }

    private void kondisiHapus() {
//       e = jTxtFldKD_CTGRY.getText();

        try {
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            st.executeUpdate(
                    " delete from trx_pemasukan where kd_ctg ='" + e + "'");
            clearTEXT();
            tampilDataKeTabel();

            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Data gagal dihapus: " + ex);
        }
    }

    private void mcExcel(Sheet sheet, Cell cell, Row rowss, int rw, int cl, String text) {
        rowss = sheet.createRow((short) rw);
        cell = rowss.createCell((short) cl);
        cell.setCellValue(text);
    }

    private void mcExcelZero(Sheet sheet, Cell cell, Row rowss, int rw, int cl, String text) {
        rowss = sheet.createRow((short) rw);
        cell = rowss.createCell((short) 0);
        cell.setCellValue(text);
    }

    private void exportExcelKartu() throws FileNotFoundException, IOException {

        Util util = new Util();

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("KARTU PERSEDIAAN BARANG");

        Row rowss = sheet.createRow((short) 0);
        Cell cell = rowss.createCell((short) 0);
        cell.setCellValue("KARTU PERSEDIAAN BARANG");
        CellStyle cellStyle = wb.createCellStyle();
        Font fonts = wb.createFont();//Create font
        fonts.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
        fonts.setFontHeight((short) 200);
        cellStyle.setFont(fonts);//set it to bold
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
        Date dates = new Date();
        String strToDate = dt1.format(dates);

        rowss = sheet.createRow((short) 5);
        rowss.createCell(0).setCellValue("GUDANG");
        rowss.createCell(1).setCellValue(":");
        rowss = sheet.createRow((short) 6);
        rowss.createCell(0).setCellValue("CATEGORY BARANG");
        rowss.createCell(1).setCellValue(": " + jComboBox1.getSelectedItem().toString());
        rowss = sheet.createRow((short) 7);
        rowss.createCell(0).setCellValue("SATUAN");
        rowss.createCell(1).setCellValue(":");
        rowss = sheet.createRow((short) 8);
        rowss.createCell(0).setCellValue("PERIODE");
        rowss.createCell(1).setCellValue(": " + util.getMonth(startdate) + " - " + util.getMonth(enddate));

        CellStyle style = wb.createCellStyle(); //Create new style
        style.setWrapText(true);

        rowss = sheet.createRow((short) 10);
        // cell = rowss.createCell(0);

        Cell cell2 = rowss.createCell((short) 0);
        cell2.setCellValue("No");
        CellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle2.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cell2.setCellStyle(cellStyle2);

        Cell cell3 = rowss.createCell((short) 1);
        cell3.setCellValue("No./Tgl. Surat Dasar Penerimaan/ Pengeluaran");
        CellStyle cellStyle3 = wb.createCellStyle();
        cellStyle3.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle3.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle3.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle3.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle3.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle3.setWrapText(true);
        cell3.setCellStyle(cellStyle3);

        Cell cell4 = rowss.createCell((short) 2);
        cell4.setCellValue("Uraian");
        CellStyle cellStyle4 = wb.createCellStyle();
        cellStyle4.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle4.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle4.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle4.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle4.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle4.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle4.setWrapText(true);

        cell4.setCellStyle(cellStyle4);

        Cell cell5 = rowss.createCell((short) 3);
        cell5.setCellValue("Barang-barang");
        CellStyle cellStyle5 = wb.createCellStyle();
        cellStyle5.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle5.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle5.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle5.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle5.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle5.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle5.setWrapText(true);
        cell5.setCellStyle(cellStyle5);
        Cell cell37 = rowss.createCell((short) 4);
        cell37.setCellStyle(cellStyle5);
        Cell cell38 = rowss.createCell((short) 5);
        cell38.setCellStyle(cellStyle5);

        Cell cell6 = rowss.createCell((short) 6);
        cell6.setCellValue("Harga Satuan");
        CellStyle cellStyle6 = wb.createCellStyle();
        cellStyle6.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle6.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle6.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle6.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle6.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle6.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle6.setWrapText(true);
        cell6.setCellStyle(cellStyle6);

        Cell cell7 = rowss.createCell((short) 7);
        cell7.setCellValue("Jumlah Harga Barang Yang Diterima / Yang");
        CellStyle cellStyle7 = wb.createCellStyle();
        cellStyle7.setAlignment(CellStyle.ALIGN_CENTER);

        cellStyle7.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle7.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle7.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle7.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle7.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle7.setWrapText(true);
        cell7.setCellStyle(cellStyle7);

        Cell cell33 = rowss.createCell((short) 8);
        cell33.setCellStyle(cellStyle5);
        Cell cell34 = rowss.createCell((short) 9);
        cell34.setCellStyle(cellStyle5);

        Cell cell8 = rowss.createCell((short) 10);
        cell8.setCellValue("Ket 1");
        CellStyle cellStyle8 = wb.createCellStyle();
        cellStyle8.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle8.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle8.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle8.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle8.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle8.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle8.setWrapText(true);
        cell8.setCellStyle(cellStyle8);

        rowss = sheet.createRow((short) 11);
        Cell cell12 = rowss.createCell((short) 0);
        CellStyle cellStyle12 = wb.createCellStyle();
        cellStyle12.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle12.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle12.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle12.setBorderBottom(CellStyle.BORDER_THIN);
        cell12.setCellStyle(cellStyle12);

        Cell cell13 = rowss.createCell((short) 1);
        CellStyle cellStyle13 = wb.createCellStyle();
        cellStyle13.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle13.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle13.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle13.setBorderBottom(CellStyle.BORDER_THIN);
        cell13.setCellStyle(cellStyle13);

        Cell cell14 = rowss.createCell((short) 2);
        CellStyle cellStyle14 = wb.createCellStyle();
        cellStyle14.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle14.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle14.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle14.setBorderBottom(CellStyle.BORDER_THIN);
        cell14.setCellStyle(cellStyle14);

        Cell cell15 = rowss.createCell((short) 3);
        cell15.setCellValue("Masuk");
        CellStyle cellStyle15 = wb.createCellStyle();
        cellStyle15.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle15.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle15.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle15.setBorderBottom(CellStyle.BORDER_THIN);
        cell15.setCellStyle(cellStyle15);

        Cell cell16 = rowss.createCell((short) 4);
        cell16.setCellValue("Keluaran");
        CellStyle cellStyle16 = wb.createCellStyle();
        cellStyle16.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle16.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle16.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle16.setBorderBottom(CellStyle.BORDER_THIN);
        cell16.setCellStyle(cellStyle16);

        Cell cell17 = rowss.createCell((short) 5);
        cell17.setCellValue("Sisa");
        CellStyle cellStyle17 = wb.createCellStyle();
        cellStyle17.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle17.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle17.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle17.setBorderBottom(CellStyle.BORDER_THIN);
        cell17.setCellStyle(cellStyle17);

        Cell cell191 = rowss.createCell((short) 6);
        cell191.setCellStyle(cellStyle17);

//        rowss.createCell(7).setCellValue("Bertambah");
        Cell cell18 = rowss.createCell((short) 7);
        cell18.setCellValue("Bertambah");
        CellStyle cellStyle18 = wb.createCellStyle();
        cellStyle18.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle18.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle18.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle18.setBorderBottom(CellStyle.BORDER_THIN);
        cell18.setCellStyle(cellStyle18);

        Cell cell19 = rowss.createCell((short) 8);
        cell19.setCellValue("Berkurang");
        cell19.setCellStyle(cellStyle18);

        Cell cell20 = rowss.createCell((short) 9);
        cell20.setCellValue("Sisa");
        cell20.setCellStyle(cellStyle18);

        Cell cell35 = rowss.createCell((short) 10);
        cell35.setCellStyle(cellStyle18);

//        String sql = "SELECT SUM(tpi.trx_qty_pemasukan_item) as totalqty, tpi.trx_price_pemasukan_item as price,SUM(tpi.trx_total_pemasukan_item)"
//                + " as totalall, tpi.trx_honor_pemasukan_item as honor,tp.trx_date_pemasukan as date , tp.trx_no_pemasukan as code"
//                + ", tpi.barang_id as barangid FROM mst_barang mb JOIN trx_pemasukan_item tpi ON  mb.id=tpi.barang_id JOIN trx_pemasukan tp"
//                + " WHERE tp.trx_date_pemasukan BETWEEN '" + startdate + "' and '" + enddate + "' and mb.ctg_barang_id=" + idcategory + " GROUP BY mb.id"
//                + "";
        String sql = "select * from mst_barang where ctg_barang_id=" + idcategory;

        System.out.println(sql);
        Statement st;
        try {
            st = aplikasiInventory.config.getConnection().createStatement();

            ResultSet set = st.executeQuery(sql);

            Double jumlahStockMasuk = 0D;
            Double jumlahPriceMasuk = 0D;
            int no = 0;
            while (set.next()) {
                no++;
                String sqls = "Select * from trx_stock where barang_id=" + set.getInt("id") + " and trx_stock_type=1";
                Statement sts = aplikasiInventory.config.getConnection().createStatement();
                ResultSet sets = sts.executeQuery(sqls);
                Integer qty_masuk = 0;
                while (sets.next()) {
//                    qty_masuk += sets.getInt("trx_stock_qty");
                    if (sets.last() == true) {
                        qty_masuk += sets.getInt("trx_stock_qty");
                    } else {
                        qty_masuk += 0;
                    }
                }

                String sqls2 = "Select * from trx_stock where barang_id=" + set.getInt("id") + " and trx_stock_type=2";
                Statement sts2 = aplikasiInventory.config.getConnection().createStatement();
                ResultSet sets2 = sts2.executeQuery(sqls2);
                Integer qty_keluar = 0;
                while (sets2.next()) {
                    if (sets2.last() == true) {
                        qty_keluar += sets2.getInt("trx_stock_qty");
                    } else {
                        qty_keluar += 0;
                    }

                }

                Integer qty_sisa = qty_masuk - qty_keluar;
                int norow = no + 11;
                rowss = sheet.createRow((short) norow);
                CellStyle cellStyle19 = wb.createCellStyle();
                cellStyle19.setBorderLeft(CellStyle.BORDER_THIN);
                cellStyle19.setBorderRight(CellStyle.BORDER_THIN);
//                 rowss.createCell(0).setCellValue();    
                Cell cell21 = rowss.createCell((short) 0);
                cell21.setCellValue(String.valueOf(no).toString());
                cell21.setCellStyle(cellStyle19);

                Cell cell22 = rowss.createCell((short) 1);
                cell22.setCellValue("");
                cell22.setCellStyle(cellStyle19);

                Cell cell23 = rowss.createCell((short) 2);
                cell23.setCellValue(set.getString("nm_barang"));
                cell23.setCellStyle(cellStyle19);

                Cell cell24 = rowss.createCell((short) 3);
                cell24.setCellValue(qty_masuk);
                cell24.setCellStyle(cellStyle19);

                Cell cell25 = rowss.createCell((short) 4);
                cell25.setCellValue(qty_keluar);
                cell25.setCellStyle(cellStyle19);

                Cell cell26 = rowss.createCell((short) 5);
                cell26.setCellValue(qty_sisa);
                cell26.setCellStyle(cellStyle19);

                Cell cell27 = rowss.createCell((short) 6);
                cell27.setCellValue(Double.parseDouble(set.getString("hrg_jual_barang")));
                cell27.setCellStyle(cellStyle19);

                Double price_masuk = Double.parseDouble(set.getString("hrg_jual_barang")) * qty_masuk;
                Double price_keluar = Double.parseDouble(set.getString("hrg_jual_barang")) * qty_keluar;
                Double price_sisa = price_masuk - price_keluar;
                Cell cell28 = rowss.createCell((short) 7);
//                Double masukprice = Double.parseDouble(set.getString("price"));
                cell28.setCellValue(price_masuk);
                cell28.setCellStyle(cellStyle19);

                Cell cell29 = rowss.createCell((short) 8);
                cell29.setCellValue(price_keluar);
                cell29.setCellStyle(cellStyle19);

                Cell cell30 = rowss.createCell((short) 9);
                cell30.setCellValue(price_sisa);
                cell30.setCellStyle(cellStyle19);

                Cell cell31 = rowss.createCell((short) 10);
                cell31.setCellValue("-");
                cell31.setCellStyle(cellStyle19);

                jumlahPriceMasuk += price_masuk;
                jumlahStockMasuk += qty_masuk;

                /*
                 String kolom2 = set.getString("date");
                 String kolom3 = set.getString("code");
                 String kolom4 = divisiname;
                 String kolom5 = set.getString("totalqty");
                 String kolom6 = set.getString("price");
                 String kolom7 = set.getString("totalall");
                 */
            }
            int jumlahrow = no + 12;
            rowss = sheet.createRow((short) jumlahrow);
//            rowss.createCell(0).setCellValue(""));    
            CellStyle cellStyle20 = wb.createCellStyle();
            cellStyle20.setBorderLeft(CellStyle.BORDER_THIN);
            cellStyle20.setBorderRight(CellStyle.BORDER_THIN);
            cellStyle20.setBorderBottom(CellStyle.BORDER_THIN);
            cellStyle20.setBorderTop(CellStyle.BORDER_THIN);
//                 rowss.createCell(0).setCellValue();    
            Cell cell40 = rowss.createCell((short) 0);
            cell40.setCellStyle(cellStyle20);

            Cell cell41 = rowss.createCell((short) 1);
            cell41.setCellValue("Jumlah");
            cell41.setCellStyle(cellStyle20);

            Cell cell42 = rowss.createCell((short) 2);
            cell42.setCellStyle(cellStyle20);

            Cell cell43 = rowss.createCell((short) 3);
            cell43.setCellValue(jumlahStockMasuk);
            cell43.setCellStyle(cellStyle20);

            Cell cell44 = rowss.createCell((short) 4);
            cell44.setCellStyle(cellStyle20);

            Cell cell45 = rowss.createCell((short) 5);
            cell45.setCellStyle(cellStyle20);

            Cell cell46 = rowss.createCell((short) 6);
            cell46.setCellStyle(cellStyle20);

            Cell cell47 = rowss.createCell((short) 7);
            cell47.setCellValue(jumlahPriceMasuk);
            cell47.setCellStyle(cellStyle20);

            Cell cell48 = rowss.createCell((short) 8);
            cell48.setCellStyle(cellStyle20);

            Cell cell49 = rowss.createCell((short) 9);
            cell49.setCellStyle(cellStyle20);

            Cell cell50 = rowss.createCell((short) 10);
            cell50.setCellValue("-");
            cell50.setCellStyle(cellStyle20);
//BAGIAN TANDA TANGAN
            CellStyle cellstyletd1 = wb.createCellStyle();
             Font font2 = wb.createFont();//Create font
            font2.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            cellstyletd1.setFont(font2);//set it to bold
            cellstyletd1.setAlignment(CellStyle.ALIGN_CENTER);
           
             CellStyle cellstyletd = wb.createCellStyle();
            cellstyletd.setAlignment(CellStyle.ALIGN_CENTER);
            
            rowss = sheet.createRow((short) jumlahrow+2);
            Cell celltd0 = rowss.createCell((short) 8);
            celltd0.setCellValue("Tangerang Selatan , "+util.getMonth(strToDate));
            celltd0.setCellStyle(cellstyletd);
            
            rowss = sheet.createRow((short) jumlahrow+3);
            Cell celltd = rowss.createCell((short) 0);
            celltd.setCellValue("ATASAN LANGSUNG");
            celltd.setCellStyle(cellstyletd);
            
            Cell celltd1 = rowss.createCell((short) 8);
            celltd1.setCellValue("PENYIMPAN BARANG");
            celltd1.setCellStyle(cellstyletd);
            
            rowss = sheet.createRow((short) jumlahrow+7);
            Cell celltd11 = rowss.createCell((short) 0);
            celltd11.setCellValue("(HADIANA.SE.MM)");
            celltd11.setCellStyle(cellstyletd1);
            
            Cell celltd12 = rowss.createCell((short) 8);
            celltd12.setCellValue("(Hendra Subrata)");
            celltd12.setCellStyle(cellstyletd1);
           
            
            rowss = sheet.createRow((short) jumlahrow+8);
            Cell celltd21 = rowss.createCell((short) 0);
            celltd21.setCellValue("NIP. 19711231 200212 1 009");
            celltd21.setCellStyle(cellstyletd);
            
            Cell celltd22 = rowss.createCell((short) 8);
            celltd22.setCellValue("NIP.19710326 200801 1001");
            celltd22.setCellStyle(cellstyletd);
            
//        rowss.set
            sheet.addMergedRegion(new CellRangeAddress(10, 11, 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(10, 11, 1, 1));
            sheet.addMergedRegion(new CellRangeAddress(10, 11, 2, 2));
            sheet.addMergedRegion(new CellRangeAddress(10, 10, 3, 5));
            sheet.addMergedRegion(new CellRangeAddress(10, 11, 6, 6));
            sheet.addMergedRegion(new CellRangeAddress(10, 10, 7, 9));
            sheet.addMergedRegion(new CellRangeAddress(10, 11, 10, 10));
            
            sheet.addMergedRegion(new CellRangeAddress(jumlahrow+3, jumlahrow+3, 0, 1));
            sheet.addMergedRegion(new CellRangeAddress(jumlahrow+7, jumlahrow+7, 0, 1));
            sheet.addMergedRegion(new CellRangeAddress(jumlahrow+8, jumlahrow+8, 0, 1));
            sheet.addMergedRegion(new CellRangeAddress(jumlahrow+2, jumlahrow+2, 8, 10));
            sheet.addMergedRegion(new CellRangeAddress(jumlahrow+3, jumlahrow+3, 8, 10));
            sheet.addMergedRegion(new CellRangeAddress(jumlahrow+7, jumlahrow+7, 8, 10));
            sheet.addMergedRegion(new CellRangeAddress(jumlahrow+8, jumlahrow+8, 8, 10));
            
            sheet.setColumnWidth(1, 5000);
            sheet.autoSizeColumn(2);
            sheet.addMergedRegion(new CellRangeAddress(
                    0, //first row (0-based)
                    0, //last row  (0-based)
                    0, //first column (0-based)
                    10 //last column  (0-based)
            ));

            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");//dd/MM/yyyy
            Date now = new Date();
            String strDate = sdfDate.format(now);

            //wb = new HSSFWorkbook();
            Sheet sheet2 = wb.createSheet("Daftar Barang");
            Row rows2 = sheet2.createRow((short) 0);
            Cell cellDb = rows2.createCell((short) 0);
            cellDb.setCellValue("Daftar Pengadaan Barang " + util.getMonth(startdate) + " - " + util.getMonth(enddate));
            CellStyle cellStyleDb = wb.createCellStyle();
            Font font = wb.createFont();//Create font
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
            cellStyleDb.setFont(font);//set it to bold
            cellStyleDb.setAlignment(CellStyle.ALIGN_CENTER);
            cellDb.setCellStyle(cellStyleDb);

            rows2 = sheet2.createRow((short) 3);
            rows2.setHeight((short) 400);
            Cell cellDb1 = rows2.createCell((short) 0);
            cellDb1.setCellValue("No");
            CellStyle cellStyleDb1 = wb.createCellStyle();//set it to bold
            cellStyleDb1.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyleDb1.setBorderLeft(CellStyle.BORDER_THIN);
            cellStyleDb1.setBorderRight(CellStyle.BORDER_THIN);
            cellStyleDb1.setBorderTop(CellStyle.BORDER_THIN);
            cellStyleDb1.setBorderBottom(CellStyle.BORDER_THIN);
            cellDb1.setCellStyle(cellStyleDb1);

            Cell cellDb2 = rows2.createCell((short) 1);
            cellDb2.setCellValue("Nama Barang");
            cellDb2.setCellStyle(cellStyleDb1);

            Cell cellDb7 = rows2.createCell((short) 2);
            cellDb7.setCellValue("Banyaknya");
            cellDb7.setCellStyle(cellStyleDb1);

            Cell cellDb3 = rows2.createCell((short) 3);
            cellDb3.setCellValue("Satuan");
            cellDb3.setCellStyle(cellStyleDb1);

            Cell cellDb4 = rows2.createCell((short) 4);
            cellDb4.setCellValue("Harga Satuan");
            cellDb4.setCellStyle(cellStyleDb1);

            Cell cellDb5 = rows2.createCell((short) 5);
            cellDb5.setCellValue("Honor + Admin");
            cellDb5.setCellStyle(cellStyleDb1);

            Cell cellDb6 = rows2.createCell((short) 6);
            cellDb6.setCellValue("Jumlah");
            cellDb6.setCellStyle(cellStyleDb1);

            int i;
            for (i = 0; i < jTable1.getRowCount(); i++) {
                int n = i + 1;

                String kolom2 = jTable1.getValueAt(i, 1).toString();
                String kolom3 = jTable1.getValueAt(i, 2).toString();
                String kolom4 = jTable1.getValueAt(i, 3).toString();
                String kolom5 = jTable1.getValueAt(i, 4).toString();
                String kolom6 = jTable1.getValueAt(i, 5).toString();
                String kolom7 = jTable1.getValueAt(i, 6).toString();

                int rownya = i + 4;
                rows2 = sheet2.createRow((short) rownya);
                Cell cellDb11 = rows2.createCell((short) 0);
                cellDb11.setCellValue(n);
                CellStyle cellStyleDb11 = wb.createCellStyle();//set it to bold
//                cellStyleDb11.setAlignment(CellStyle.ALIGN_CENTER);
                cellStyleDb11.setBorderLeft(CellStyle.BORDER_THIN);
                cellStyleDb11.setBorderRight(CellStyle.BORDER_THIN);
                cellStyleDb11.setBorderBottom(CellStyle.BORDER_DOTTED);
                cellDb11.setCellStyle(cellStyleDb11);

                Cell cellDb12 = rows2.createCell((short) 1);
                cellDb12.setCellValue(kolom2);
                cellDb12.setCellStyle(cellStyleDb11);

                Cell cellDb13 = rows2.createCell((short) 2);
                cellDb13.setCellValue(kolom3);
                cellDb13.setCellStyle(cellStyleDb11);

                Cell cellDb14 = rows2.createCell((short) 3);
                cellDb14.setCellValue(kolom4);
                cellDb14.setCellStyle(cellStyleDb11);

                Cell cellDb15 = rows2.createCell((short) 4);
                cellDb15.setCellValue(kolom5);
                cellDb15.setCellStyle(cellStyleDb11);

                Cell cellDb16 = rows2.createCell((short) 5);
                cellDb16.setCellValue(kolom6);
                cellDb16.setCellStyle(cellStyleDb11);

                Cell cellDb17 = rows2.createCell((short) 6);
                cellDb17.setCellValue(kolom7);
                cellDb17.setCellStyle(cellStyleDb11);

            }

            int rownyafot = jTable1.getRowCount() + 4;
            rows2 = sheet2.createRow((short) rownyafot);
            Cell cellDb21 = rows2.createCell((short) 0);
            cellDb21.setCellValue("");
            CellStyle cellStyleDb21 = wb.createCellStyle();//set it to bold
            cellStyleDb21.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyleDb21.setBorderLeft(CellStyle.BORDER_THIN);
            cellStyleDb21.setBorderRight(CellStyle.BORDER_THIN);
            cellStyleDb21.setBorderBottom(CellStyle.BORDER_THIN);
            cellStyleDb21.setBorderTop(CellStyle.BORDER_THIN);
            cellDb21.setCellStyle(cellStyleDb21);

            Cell cellDb22 = rows2.createCell((short) 1);
            cellDb22.setCellValue("Jumlah");
            cellDb22.setCellStyle(cellStyleDb21);

            Cell cellDb23 = rows2.createCell((short) 2);
            cellDb23.setCellValue("");
            cellDb23.setCellStyle(cellStyleDb21);

            Cell cellDb24 = rows2.createCell((short) 3);
            cellDb24.setCellValue("");
            cellDb24.setCellStyle(cellStyleDb21);

            Cell cellDb25 = rows2.createCell((short) 4);
            cellDb25.setCellValue("");
            cellDb25.setCellStyle(cellStyleDb21);

            Cell cellDb26 = rows2.createCell((short) 5);
            cellDb26.setCellValue("");
            cellDb26.setCellStyle(cellStyleDb21);

            Cell cellDb27 = rows2.createCell((short) 6);
            cellDb27.setCellValue("");
            cellDb27.setCellStyle(cellStyleDb21);

            sheet2.addMergedRegion(new CellRangeAddress(0, 1, 0, 6));
            sheet2.autoSizeColumn(0);
            sheet2.autoSizeColumn(1);
            sheet2.autoSizeColumn(2);
            sheet2.autoSizeColumn(3);
            sheet2.autoSizeColumn(4);
            sheet2.autoSizeColumn(5);
            sheet2.autoSizeColumn(6);

            // FileOutputStream fileOut = new FileOutputStream("D:\\excel\\workbook-"+strDate+".xls");
            File yourFile = new File("KPB.xls");
            int nums = 0;
            while(yourFile.exists()) { 
                nums++;
                yourFile.createNewFile();
                yourFile.renameTo(new File("KPB"+nums+".xls"));
            }
//FileOutputStream oFile = new FileOutputStream(yourFile, false); 
            FileOutputStream fileOut = new FileOutputStream(yourFile,true);
            
//            HSSFSheet sheetto = wb.createSheet(fileOut);
            // fileOut
//            fileOut.write(b, off, len);
            wb.write(fileOut);
//            wb.write(fileOut);
            fileOut.close();

        } catch (SQLException ex) {
            Logger.getLogger(ReportKas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cari(JTable jTabel) {
        try {
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(dt1.format(jXDatePicker1.getDate()));
            startdate = dt1.format(jXDatePicker1.getDate());
            SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd");
            enddate = dt2.format(jXDatePicker2.getDate());
            CategoryBarangDv category = (CategoryBarangDv) jComboBox1.getSelectedItem();
            idcategory = category.getId();
            System.out.println(enddate);

            //   Object[] field = {"No", "Date", "No. Transaksi", "Uraian", "Stock Masuk","Stock Keluar", "Harga Satuan", "Total Harga"};
            Object[] field = {"No", "Nama Barang", "Banyaknya", "Satuan", "Harga Satuan", "Honor + ADM", "Jumlah"};
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
            /*String sql = "SELECT SUM(tpi.trx_qty_pemasukan_item) as totalqty, tpi.trx_price_pemasukan_item as price,SUM(tpi.trx_total_pemasukan_item)"
             + " as totalall, tpi.trx_honor_pemasukan_item as honor,tp.trx_date_pemasukan as date , tp.trx_no_pemasukan as code"
             + ", tpi.barang_id as barangid FROM mst_barang mb JOIN trx_pemasukan_item tpi ON  mb.id=tpi.barang_id JOIN trx_pemasukan tp"
             + " WHERE tp.trx_date_pemasukan BETWEEN '" + startdate + "' and '" + enddate + "' and mb.ctg_barang_id=" + idcategory + " GROUP BY mb.id"
             + "";
             */
            //String sql = "SELECT * FROM mst_barang WHERE ctg_barang_id="+idcategory;
            String sql = "SELECT SUM(ts.trx_stock_qty) as stock, mb.nm_barang as name, "
                    + "mb.satuan_barang as satuan, ts.trx_stock_price as price from mst_barang mb "
                    + "JOIN trx_stock ts on mb.id=ts.barang_id where ts.trx_stock_date "
                    + "BETWEEN '" + startdate + "' and '" + enddate + "' and mb.ctg_barang_id=" + idcategory
                    + " and ts.trx_stock_type=1 GROUP BY mb.id";
            System.out.println(sql);
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                //String sqls = "Select * from mst_barang where id=" + set.getInt("barangid");
//                String sqls = "Select * from trx_pemasukan_item tpi JOIN trx_pemasukan tp ON "
//                        + "tpi.trx_pemasukan_id=tp.id where tp.trx_date_pemasukan BETWEEN '" + startdate + "' and '" + enddate +"' and "
//                        + "tpi.barang_id="+set.getInt("id");
//                Statement sts = aplikasiInventory.config.getConnection().createStatement();
//                
//                ResultSet sets = sts.executeQuery(sqls);
//                String divisiname = "";
//                String namaBarang = "";
//                Integer bnyk = 0;
//                Double price = 0D;
//                
//                while (sets.next()) {
//                    bnyk += sets.getInt("trx_qty_pemasukan_item");
//                    price = sets.getDouble("trx_price_pemasukan_item");
//                }
                Double totalall = Double.parseDouble(set.getString("stock")) * Double.parseDouble(set.getString("price"));
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("name");
                String kolom3 = set.getString("stock");
                String kolom4 = set.getString("satuan");
                String kolom5 = set.getString("price");
                String kolom6 = "0";
                String kolom7 = totalall.toString();
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7};
                DfltTblMode.addRow(data);

            }

            jTabel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            column = jTabel.getColumnModel().getColumn(0);
            column.setPreferredWidth(40);
            column = jTabel.getColumnModel().getColumn(1);
            column.setPreferredWidth(150);
            column = jTabel.getColumnModel().getColumn(2);
            column.setPreferredWidth(40);

            column = jTabel.getColumnModel().getColumn(3);
            column.setPreferredWidth(70);

            column = jTabel.getColumnModel().getColumn(4);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(5);
            column.setPreferredWidth(100);
            column = jTabel.getColumnModel().getColumn(6);
            column.setPreferredWidth(100);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e);
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
        setTitle("Pengadaan Barang");
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
