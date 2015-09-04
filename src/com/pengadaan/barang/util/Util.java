/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengadaan.barang.util;

/**
 *
 * @author sfandrianah
 */
public class Util {
    
    public String getMonth(String text){
     
         String years = text.substring(0, 4);
         String month = text.substring(5,7);
         String tgl = text.substring(8,10);
         String subMonth = "";
         switch(month){
             case "01":
                 subMonth = "Januari";
                 break;

             case "02":
                 subMonth = "Februari";
                 break;

             case "03":
                 subMonth = "Maret";
                 break;
             case "04":
                 subMonth = "April";
                 break;

             case "05":
                 subMonth = "Mei";
                 break;

             case "06":
                 subMonth = "Juni";
                 break;
             case "07":
                 subMonth = "Juli";
                 break;
             case "08":
                 subMonth = "Agustus";
                 break;
             case "09":
                 subMonth = "September";
                 break;
             case "10":
                 subMonth = "Oktober";
                 break;
             case "11":
                 subMonth = "November";
                 break;
             case "12":
                 subMonth = "September";
                 break;
                 
         }
         
        return tgl+" "+subMonth+" "+years;
    }
    
    
}
