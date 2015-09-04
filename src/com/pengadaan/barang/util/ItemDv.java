/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengadaan.barang.util;

import com.pengadaan.barang.produk.*;

/**
 *
 * @author yudith
 */
public class ItemDv {
    
    private int id;
    private int no;
    private String name;
    private String satuan;
    private int idBarang;
    private double price;
    private double honor;
    private double jumlah;
    private int qty;

    public ItemDv(int no,int idBarang,String satuan,int qty,double price,double honor,double jumlah){
        this.no = no;
        this.satuan= name;
        this.idBarang = idBarang;
        this.price = price;
        this.honor = honor;
        this.jumlah = jumlah;
        this.qty = qty;
    }

    public int getNo() {
        return no;
    }

    public String getSatuan() {
        return satuan;
    }

    
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public double getPrice() {
        return price;
    }

    public double getHonor() {
        return honor;
    }

    public double getJumlah() {
        return jumlah;
    }

    public int getQty() {
        return qty;
    }

    
    
    public String toString(){
        return name;
    }
    
            
    
}
