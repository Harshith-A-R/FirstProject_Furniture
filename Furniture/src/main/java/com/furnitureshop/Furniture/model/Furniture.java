package com.furnitureshop.Furniture.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "furniture")
public class Furniture {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public byte[] getPicByte() {
		return picByte;
	}
	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	@Column(name = "productname")
	private String productname;

	//@Column(name = "author")
	//private String author;
	
	@Column(name = "price")
	private String price;
//for storing the image in database
	@Column(name = "picByte", length = 1000)
	private byte[] picByte;

	@Override
	public String toString() {
		return "Furniture [id=" + id + ", productname=" + productname + ", price=" + price + ", picByte="
				+ Arrays.toString(picByte) + "]";
	}
	
		

}
