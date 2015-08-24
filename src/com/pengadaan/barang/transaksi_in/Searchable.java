/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengadaan.barang.transaksi_in;

import java.util.Collection;

/**
 *
 * @author sfandrianah
 */
public interface Searchable<E, V>{

	

	/**

	 * Searches an underlying inventory of items consisting of type E

	 * @param value A searchable value of type V

	 * @return A Collection of items of type E.

	 */

	public Collection<E> search(V value);

	

}
