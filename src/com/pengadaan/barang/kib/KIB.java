package com.pengadaan.barang.kib;

import com.pengadaan.barang.report_kartu.*;
import com.pengadaan.barang.util.Util;
import com.pengadaan.barang.transaksi_in.*;
import com.pengadaan.barang.kategory.*;
import com.pengadaan.barang.PengadaanBarang;
import com.pengadaan.barang.util.CategoryBarangDv;
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
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.codehaus.groovy.tools.shell.ParseCode;

public class KIB extends javax.swing.JFrame {

    private Integer row;
    private PengadaanBarang aplikasiInventory = new PengadaanBarang();
    private JTable jTabel = new JTable();
    private DefaultTableModel DfltTblMode;
    private TableColumn column;
    private String e, r, i, k, a, startdate, enddate;
    private int idcategory;

    public KIB() {
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

            String sql = "Select * from mst_category_barang where type_barang_id=1";
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
            Logger.getLogger(KIB.class.getName()).log(Level.SEVERE, null, ex);
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
        Sheet sheet = wb.createSheet("KIB - " + jComboBox1.getSelectedItem().toString());

        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dt1.format(jXDatePicker1.getDate()));
        startdate = dt1.format(jXDatePicker1.getDate());
        SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd");
        enddate = dt2.format(jXDatePicker2.getDate());
        Date dates = new Date();
        String strToDate = dt1.format(dates);

        Row rowss = sheet.createRow((short) 5);
        Cell cell = rowss.createCell((short) 0);
        cell.setCellValue("KARTU INVENTARIS BARANG");
        CellStyle cellStyle = wb.createCellStyle();
        Font fonts = wb.createFont();//Create font
        fonts.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
        fonts.setFontHeight((short) 200);
        cellStyle.setFont(fonts);//set it to bold
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cell.setCellStyle(cellStyle);

        rowss = sheet.createRow((short) 1);
        rowss.createCell(2).setCellValue("SKPD");
        rowss.createCell(3).setCellValue(": Dinas Pendapatan Pengelolaan Keuangan dan Aset Daerah");
        rowss = sheet.createRow((short) 2);
        rowss.createCell(2).setCellValue("KAB/KOTA");
        rowss.createCell(3).setCellValue(": TANGERANG SELATAN");
        rowss = sheet.createRow((short) 3);
        rowss.createCell(2).setCellValue("PROVINSI");
        rowss.createCell(3).setCellValue(": BANTEN");

        rowss = sheet.createRow((short) 6);
        Cell cellcol2 = rowss.createCell((short) 0);
        cellcol2.setCellValue(jComboBox1.getSelectedItem().toString());
        cellcol2.setCellStyle(cellStyle);

        rowss = sheet.createRow((short) 7);
        Cell cellcol21 = rowss.createCell((short) 0);
        cellcol21.setCellValue("PERIODE : " + util.getMonth(startdate) + " - " + util.getMonth(enddate));
        cellcol21.setCellStyle(cellStyle);

        CellStyle style = wb.createCellStyle(); //Create new style
        style.setWrapText(true);

        // cell = rowss.createCell(0);
        CellStyle cellStyleheader = wb.createCellStyle();
        cellStyleheader.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleheader.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleheader.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleheader.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleheader.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleheader.setWrapText(true);
        cellStyleheader.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        CellStyle cellStyleheader1 = wb.createCellStyle();
        cellStyleheader1.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleheader1.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleheader1.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleheader1.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleheader1.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleheader1.setFillForegroundColor(IndexedColors.BLACK.getIndex());
        cellStyleheader1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font fonhdr = wb.createFont();
        fonhdr.setBoldweight(Font.BOLDWEIGHT_BOLD);
        fonhdr.setColor(IndexedColors.WHITE.getIndex());
        cellStyleheader1.setWrapText(true);
        cellStyleheader1.setFont(fonhdr);
        cellStyleheader1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        String[] headerlist = {"No.", "Kode", "", "Nama Barang/Jenis Barang", "Nomor Register", "Jumlah", "Merk/Type",
            "Ukuran/CC", "Bahan", "Tahun Pembelian", "Nomor", "", "", "", "", "Asal Usul Cara Perolehan",
            "Harga Satuan", "Harga (Rp)", "", "Ket."};

        String[] headerlist2 = {"", "Lokasi", "Barang", "", "", "", "",
            "", "", "", "Pabrik", "Rangka", "Mesin", "Polisi", "BPKB", "",
            "", "ADM+HNR", "Jumlah", ""};

        rowss = sheet.createRow((short) 8);
        Cell cellheader;
        for (int ih = 0; ih < headerlist.length; ih++) {
            cellheader = rowss.createCell((short) ih);
            cellheader.setCellValue(headerlist[ih]);
            cellheader.setCellStyle(cellStyleheader);
//            System.out.println(headerlist[ih]);
        }

        rowss = sheet.createRow((short) 9);
        for (int ih = 0; ih < headerlist2.length; ih++) {
            cellheader = rowss.createCell((short) ih);
            cellheader.setCellValue(headerlist2[ih]);
            cellheader.setCellStyle(cellStyleheader);
//            System.out.println(headerlist2[ih]);
        }

        String sql = "select * from mst_barang where ctg_barang_id=" + idcategory;

//        System.out.println(sql);
        CellStyle cellStyleisi = wb.createCellStyle();
        cellStyleisi.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyleisi.setBorderRight(CellStyle.BORDER_THIN);
        cellStyleisi.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleisi.setBorderBottom(CellStyle.BORDER_THIN);
//            cellStyleisi.setWrapText(true);
        Cell cellisi;

//            System.out.println(headerlist[ih]);
        String[][] kolomarray;
        int hat = 0;
        for (int jt = 0; jt < jTable1.getRowCount(); jt++) {

            String kolom1 = jTabel.getValueAt(jt, 0).toString();
            String kolom2 = jTabel.getValueAt(jt, 1).toString();
            String kolom3 = jTabel.getValueAt(jt, 2).toString();
            String kolom4 = jTabel.getValueAt(jt, 3).toString();
            String kolom5 = jTabel.getValueAt(jt, 4).toString();
            String kolom6 = jTabel.getValueAt(jt, 5).toString();
            String kolom7 = jTabel.getValueAt(jt, 6).toString();
            String kolom8 = jTabel.getValueAt(jt, 7).toString();
            String kolom9 = jTabel.getValueAt(jt, 8).toString();
            String kolom10 = jTabel.getValueAt(jt, 9).toString();
            String kolom11 = jTabel.getValueAt(jt, 10).toString();
            String kolom12 = jTabel.getValueAt(jt, 11).toString();
            String kolom13 = jTabel.getValueAt(jt, 12).toString();
            String kolom14 = jTabel.getValueAt(jt, 13).toString();
            String kolom15 = jTabel.getValueAt(jt, 14).toString();
            String kolom16 = jTabel.getValueAt(jt, 15).toString();
            String kolom17 = jTabel.getValueAt(jt, 16).toString();
            String kolom18 = jTabel.getValueAt(jt, 17).toString();
            String kolom19 = jTabel.getValueAt(jt, 18).toString();
            String kolom20 = jTabel.getValueAt(jt, 19).toString();
            String kolom21 = jTabel.getValueAt(jt, 20).toString();
            String kolom22 = jTabel.getValueAt(jt, 21).toString();

            kolomarray = new String[jTable1.getRowCount()][20];
            kolomarray[jt][0] = kolom1;
            kolomarray[jt][1] = kolom3;
            kolomarray[jt][2] = kolom4;
            kolomarray[jt][3] = kolom6;
            kolomarray[jt][4] = kolom7;
            kolomarray[jt][5] = kolom8;
            kolomarray[jt][6] = kolom9;
            kolomarray[jt][7] = kolom10;
            kolomarray[jt][8] = kolom11;
            kolomarray[jt][9] = kolom12;
            kolomarray[jt][10] = kolom13;
            kolomarray[jt][11] = kolom14;
            kolomarray[jt][12] = kolom15;
            kolomarray[jt][13] = kolom16;
            kolomarray[jt][14] = kolom17;
            kolomarray[jt][15] = kolom18;
            kolomarray[jt][16] = kolom19;
            kolomarray[jt][17] = kolom20;
            kolomarray[jt][18] = kolom21;
            kolomarray[jt][19] = kolom22;

            Double totalall;

//            System.out.println("price = "+kolomarray[jt][18]);
//            int nu=0;
            if (Double.valueOf(kolomarray[jt][18]) <= 1000000) {
                hat += 1;
                rowss = sheet.createRow((short) 10);
                for (int ih = 0; ih < headerlist2.length; ih++) {
                    if (ih == 0) {
                        cellheader = rowss.createCell((short) ih);
                        cellheader.setCellValue("BMD DIBAWAH 1 JUTA");
                        cellheader.setCellStyle(cellStyleheader1);
                    } else {
                        cellheader = rowss.createCell((short) ih);
                        cellheader.setCellValue("");
                        cellheader.setCellStyle(cellStyleheader1);
                    }

                }
                rowss = sheet.createRow((short) 11 + jt);
                for (int ii = 0; ii < kolomarray[jt].length; ii++) {
                    cellisi = rowss.createCell((short) ii);
                    cellisi.setCellValue(kolomarray[jt][ii]);
//                    System.out.println("tes" + kolomarray[jt][ii]);
                    cellisi.setCellStyle(cellStyleisi);
                }
            } else {
                System.out.println("jt="+jt+"hat="+hat);
                if (jt == hat) {
                     System.out.println("masuk diatas");
                    rowss = sheet.createRow((short) 11 + hat);
                    
                    for (int ih = 0; ih < headerlist2.length; ih++) {
                        if (ih == 0) {
                            cellheader = rowss.createCell((short) ih);
                            cellheader.setCellValue("BMD DIATAS 1 JUTA");
                            cellheader.setCellStyle(cellStyleheader1);
                        } else {
                            cellheader = rowss.createCell((short) ih);
                            cellheader.setCellValue("");
                            cellheader.setCellStyle(cellStyleheader1);
                        }

                    }
                    sheet.addMergedRegion(new CellRangeAddress(11 + hat, 11 + hat, 0, 19));
                } else {
                rowss = sheet.createRow((short) 11 + jt);
                for (int ii = 0; ii < kolomarray[jt].length; ii++) {
                    cellisi = rowss.createCell((short) ii);
                    cellisi.setCellValue(kolomarray[jt][ii]);
//                    System.out.println("tes" + kolomarray[jt][ii]);
                    cellisi.setCellStyle(cellStyleisi);
                }
                }
            }
//            System.out.println("jt = "+jt);

        }
        CellStyle cellStylefooter = wb.createCellStyle();
        cellStylefooter.setBorderLeft(CellStyle.BORDER_THIN);
        cellStylefooter.setBorderRight(CellStyle.BORDER_THIN);
        cellStylefooter.setBorderBottom(CellStyle.BORDER_THIN);
        cellStylefooter.setBorderTop(CellStyle.BORDER_THIN);
        cellStylefooter.setAlignment(CellStyle.ALIGN_CENTER);
        int jumlahrow = jTable1.getRowCount() + 12;
//        int jumlahrow2 = jTable1.getRowCount() + 11;
        rowss = sheet.createRow((short) jumlahrow);
        Cell cellfooter;
        for (int ih = 0; ih < headerlist.length; ih++) {
            cellfooter = rowss.createCell((short) ih);
            cellfooter.setCellValue("");
            cellfooter.setCellStyle(cellStylefooter);
//            System.out.println(headerlist[ih]);
        }
        rowss = sheet.createRow((short) jumlahrow + 1);
        for (int ih = 0; ih < headerlist.length; ih++) {
            cellfooter = rowss.createCell((short) ih);
            if (ih == 3) {
                cellfooter.setCellValue("Total");
            } else {
                cellfooter.setCellValue("");
            }

            cellfooter.setCellStyle(cellStylefooter);
//            System.out.println(headerlist[ih]);
        }

//BAGIAN TANDA TANGAN
        CellStyle cellstyletd1 = wb.createCellStyle();
        Font font2 = wb.createFont();//Create font
        font2.setBoldweight(Font.BOLDWEIGHT_BOLD);//Make font bold
        cellstyletd1.setFont(font2);//set it to bold
        cellstyletd1.setAlignment(CellStyle.ALIGN_CENTER);

        CellStyle cellstyletd = wb.createCellStyle();
        cellstyletd.setAlignment(CellStyle.ALIGN_CENTER);

        Cell celltd;
        rowss = sheet.createRow((short) jumlahrow + 3);

        celltd = rowss.createCell((short) 3);
        celltd.setCellValue("Mengetahui,");
        celltd.setCellStyle(cellstyletd);

        celltd = rowss.createCell((short) 15);
        celltd.setCellValue("Tangerang Selatan , " + util.getMonth(strToDate));
        celltd.setCellStyle(cellstyletd);

        rowss = sheet.createRow((short) jumlahrow + 4);

        celltd = rowss.createCell((short) 3);
        celltd.setCellValue("KEPALA DINAS PENDAPATAN PENGELOLAAN");
        celltd.setCellStyle(cellstyletd);

        celltd = rowss.createCell((short) 15);
        celltd.setCellValue("PENGURUS BARANG");
        celltd.setCellStyle(cellstyletd);

        rowss = sheet.createRow((short) jumlahrow + 5);

        celltd = rowss.createCell((short) 3);
        celltd.setCellValue("KEUANGAN DAN ASET DAERAH");
        celltd.setCellStyle(cellstyletd);

        rowss = sheet.createRow((short) jumlahrow + 11);

        celltd = rowss.createCell((short) 3);
        celltd.setCellValue("NIP. 19610903 199102 1 001");
        celltd.setCellStyle(cellstyletd);

        celltd = rowss.createCell((short) 15);
        celltd.setCellValue("NIP. 19720203 199303 1 003");
        celltd.setCellStyle(cellstyletd);

        rowss = sheet.createRow((short) jumlahrow + 10);

        celltd = rowss.createCell((short) 3);
        celltd.setCellValue("H. UUS KUSNADI, SE. Msi");
        celltd.setCellStyle(cellstyletd);

        celltd = rowss.createCell((short) 15);
        celltd.setCellValue("ENDANG SUHERMAN");
        celltd.setCellStyle(cellstyletd);

        sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 19));
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 0, 19));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 19));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 19));
//        rowss.set
        sheet.addMergedRegion(new CellRangeAddress(8, 9, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 2));
        for (int sl = 3; sl < 10; sl++) {
            sheet.addMergedRegion(new CellRangeAddress(8, 9, sl, sl));
        }
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 10, 14));
        sheet.addMergedRegion(new CellRangeAddress(8, 9, 15, 15));
        sheet.addMergedRegion(new CellRangeAddress(8, 9, 16, 16));
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 17, 18));
        sheet.addMergedRegion(new CellRangeAddress(8, 9, 19, 19));

        for (int sl = 3; sl < 12; sl++) {
            sheet.addMergedRegion(new CellRangeAddress(jumlahrow + sl, jumlahrow + sl, 15, 17));
        }

        sheet.setColumnWidth(15, 5000);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(15);
//            sheet.addMergedRegion(new CellRangeAddress(
//                    0, //first row (0-based)
//                    0, //last row  (0-based)
//                    0, //first column (0-based)
//                    10 //last column  (0-based)
//            ));

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
        File yourFile = new File("KIB.xls");
        int nums = 0;
        while (yourFile.exists()) {
            nums++;
            yourFile.createNewFile();
            yourFile.renameTo(new File("KIB" + nums + ".xls"));
        }
//FileOutputStream oFile = new FileOutputStream(yourFile, false); 
        FileOutputStream fileOut = new FileOutputStream(yourFile, true);

//            HSSFSheet sheetto = wb.createSheet(fileOut);
        // fileOut
//            fileOut.write(b, off, len);
        wb.write(fileOut);
//            wb.write(fileOut);
        fileOut.close();

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
            Object[] field = {"No", "Date", "Kode Lokasi", "Kode Barang", "Category Barang",
                "Nama Barang", "No Registrasi", "Jumlah Barang", "Merk", "Ukuran", "Bahan", "Tahun", "Nomor Pabrik", "Nomor Rangka", "Nomor Mesin", "Nomor Polisi", "Nomor BPKB", "Cara Perolehan", "Harga",
                "Honor", "Total", "Keterangan"};
            DfltTblMode = new DefaultTableModel(null, field);
//            DfltTblMode = new DefaultTableModel();

            jTabel.setModel(DfltTblMode);
            String sql = "SELECT * FROM trx_asset_item ts where ts.trx_asset_item_date "
                    + "BETWEEN '" + startdate + "' and '" + enddate + "' and ts.category_barang_id=" + idcategory
                    + " ORDER BY trx_asset_item_price_jumlah asc";
            System.out.println(sql);
            Statement st = aplikasiInventory.config.getConnection().createStatement();
            ResultSet set = st.executeQuery(sql);

            int no = 0;
            while (set.next()) {
                no++;
                String sqls = "Select * from mst_category_barang where id=" + set.getInt("category_barang_id");
//                String sqls = "Select * from trx_pemasukan_item tpi JOIN trx_pemasukan tp ON "
//                        + "tpi.trx_pemasukan_id=tp.id where tp.trx_date_pemasukan BETWEEN '" + startdate + "' and '" + enddate +"' and "
//                        + "tpi.barang_id="+set.getInt("id");
                Statement sts = aplikasiInventory.config.getConnection().createStatement();

                ResultSet sets = sts.executeQuery(sqls);
                String divisiname = "";
                String namaBarang = "";
                Integer bnyk = 0;
                Double price = 0D;

                while (sets.next()) {
//                    bnyk += sets.getInt("trx_qty_pemasukan_item");
                    namaBarang = sets.getString("nm_ctg");
                }
//                Double totalall = Double.parsesDouble(set.getString("stock")) * Double.parseDouble(set.getString("price"));
                String kolom1 = String.valueOf(no).toString();
                String kolom2 = set.getString("trx_asset_item_date");
                String kolom3 = set.getString("trx_asset_item_code_lokasi");
                String kolom4 = set.getString("trx_asset_item_code_barang");
                String kolom5 = namaBarang;
                String kolom6 = set.getString("trx_asset_item_barang");
                String kolom7 = set.getString("trx_asset_item_no_reg");
                String kolom8 = set.getString("trx_asset_item_jumlah");
                String kolom9 = set.getString("trx_asset_item_merk");
                String kolom10 = set.getString("trx_asset_item_ukuran");
                String kolom11 = set.getString("trx_asset_item_bahan");
                String kolom12 = set.getString("trx_asset_item_tahun");
                String kolom13 = set.getString("trx_asset_item_no_pabrik");
                String kolom14 = set.getString("trx_asset_item_no_rangka");
                String kolom15 = set.getString("trx_asset_item_no_mesin");
                String kolom16 = set.getString("trx_asset_item_no_polisi");
                String kolom17 = set.getString("trx_asset_item_no_bpkb");
                String kolom18 = set.getString("trx_asset_item_cr_prl");
                String kolom19 = set.getString("trx_asset_item_price");
                String kolom20 = set.getString("trx_asset_item_honor");
                String kolom21 = set.getString("trx_asset_item_price_jumlah");
                String kolom22 = set.getString("trx_asset_item_ket");
                String[] data = {kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7, kolom8, kolom9, kolom10, kolom11, kolom12, kolom13, kolom14, kolom15, kolom16, kolom17, kolom18, kolom19, kolom20, kolom21, kolom22};
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
        setTitle("Kartu Inventaris Barang");
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
                "No", "Date", "Kode Lokasi", "Kode Barang", "Nama Barang", "Harga", "Total"
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
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
            Logger.getLogger(KIB.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(KIB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KIB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KIB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KIB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new KIB().setVisible(true);
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
