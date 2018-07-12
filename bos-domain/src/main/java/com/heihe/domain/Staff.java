package com.heihe.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 	取派员
 */

public class Staff implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String telephone;
	private String haspda = "0";//是否有PDA，1：有 0：无
	private String deltag;  // 作废标记  1：表示删除  0：未被删除
	private String station; //所属单位
	private String standard; //收费标准
	private Set decidedzones = new HashSet(0); // 一个取派员管理多个定区

	// Constructors

	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff(String id) {
		this.id = id;
	}

	/** full constructor */
	public Staff(String id, String name, String telephone, String haspda,
			String deltag, String station, String standard, Set decidedzones) {
		this.id = id;
		this.name = name;
		this.telephone = telephone;
		this.haspda = haspda;
		this.deltag = deltag;
		this.station = station;
		this.standard = standard;
		this.decidedzones = decidedzones;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHaspda() {
		return this.haspda;
	}

	public void setHaspda(String haspda) {
		this.haspda = haspda;
	}

	public String getDeltag() {
		return this.deltag;
	}

	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Set getDecidedzones() {
		return this.decidedzones;
	}

	public void setDecidedzones(Set decidedzones) {
		this.decidedzones = decidedzones;
	}

}