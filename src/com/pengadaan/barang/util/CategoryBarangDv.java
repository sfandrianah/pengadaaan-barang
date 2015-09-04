/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengadaan.barang.util;

/**
 *
 * @author yudith
 */
public class CategoryBarangDv {
    
    private int id;
    private String name;

    public CategoryBarangDv(int id,String name){
        this.id = id;
        this.name= name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return name;
    }
    
            
    
}
