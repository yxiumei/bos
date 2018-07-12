package com.heihe.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 区域
 */

public class Region implements java.io.Serializable {

	// Fields

	private String id; 
	private String province; // 省
	private String city;  // 市
	private String district;  // 区
	private String postcode; //邮编
	private String shortcode; //简码
	private String citycode; // 城市编码
	private Set subareas = new HashSet(0); // 一个区域对应过个分区

	// Constructors
	
	// name方法用作管理分区中添加分区的下拉框选择
	public String getName(){
		return province +" "+city+" "+district;
	}

	/** default constructor */
	public Region() {
	}

	/** minimal constructor */
	public Region(String id) {
		this.id = id;
	}

	/** full constructor */
	public Region(String id, String province, String city, String district,
			String postcode, String shortcode, String citycode, Set subareas) {
		this.id = id;
		this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
		this.shortcode = shortcode;
		this.citycode = citycode;
		this.subareas = subareas;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getShortcode() {
		return this.shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}

	public String getCitycode() {
		return this.citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public Set getSubareas() {
		return this.subareas;
	}

	public void setSubareas(Set subareas) {
		this.subareas = subareas;
	}

}