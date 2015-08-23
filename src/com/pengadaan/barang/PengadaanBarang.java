package com.pengadaan.barang;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import koneksi.SettingUI;


public class PengadaanBarang {

    public SettingUI config = new SettingUI(null, true);
    private String PROP_FILE="config.ini";
    private Properties p2;
    public start Start = new start();
    public String vsNIK,Jabatan;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new login().setVisible(true);        
    }
    
    public void konekkeDatabase() {
        config.setServer("localhost");
        config.setUser("root");
        config.setPassword("");
        config.setDatabase("pengadaan_barang");
        config.makeConnect();
    }

    public static Properties loadProperties(String sFile) {
      Properties p = new Properties();
      try {
	FileInputStream in = new FileInputStream(sFile);
	p.load(in);
	in.close();
      } catch (IOException iOException) {
 	System.out.println(iOException);
      }

      return p;
   }

   public static void saveProperties(Properties p, String sFile)
       throws IOException{

   FileOutputStream out = null;
   try {
	out = new FileOutputStream(sFile);
	p.store(out, "Ini baris komentar\nFile konfigurasi");

   } catch (FileNotFoundException ex) {
	JOptionPane.showMessageDialog(null, ex.getMessage());
   } finally {
        try {
	   out.close();
         } catch (IOException ex) {
	    JOptionPane.showMessageDialog(null, ex.getMessage());
         }
    }
  }

  public void loadNIK(JLabel Label1){
        p2 = loadProperties(PROP_FILE);
	Label1.setText(p2.getProperty("NIK"));	
  }

  public void saveNIK(String NIK) {
      try {
        Properties p = new Properties();
        
	p.setProperty("NIK", NIK);
	
	saveProperties(p, PROP_FILE);
	} catch (IOException ex) {
	    JOptionPane.showMessageDialog(null, ex.getMessage());
	}
  }
  
  public void HakAkses(){
      try{
            Statement st = config.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select JABATAN as JBTN from karyawan where NIK = '"+vsNIK+"'");
            
         if (rs.next()) {
             Jabatan = rs.getString("JBTN");
            if (Jabatan.equals("Admin")){
                 Start.jMnItmCTGRY.setVisible(false);
//                 Start.jMnItmCabang.setVisible(false);
//                 Start.jMnItmDept.setVisible(false);
//                 Start.jMnItmKaryawan.setVisible(false);
//                 Start.jMnItmSBS.setVisible(false);
            }
            else if (Jabatan.equals("Teknisi")){
                 Start.jMnMaster.setVisible(false);
                 Start.jMnTRN.setVisible(false);
                 Start.jMnPopulasi.setVisible(false);
            }
            else if (Jabatan.equals("Super User") || Jabatan.equals("User")){
                 Start.jMnMaster.setVisible(false);
                 Start.jMnPopulasi.setVisible(false);
            }
         }
      }
      catch (SQLException ex){
          
      }
  }
       
}

