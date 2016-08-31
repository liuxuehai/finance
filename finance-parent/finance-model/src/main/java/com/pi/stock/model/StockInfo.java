package com.pi.stock.model;

import java.io.Serializable;

public class StockInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;//公司代码 
	private String name;//公司简称 
	private String fullName;//公司全称
	private String enName;//英文名称
	private String address;//注册地址
	private String codeA;//A股代码
	private String nameA;//A股简称
	private String dateA;//A股上市日期
	private String gCapitalA;//A股总股本
	private String fCapitalA;//A股流通股本
	private String codeB;//B股代码
	private String nameB;//B股简称
	private String dateB;//B股上市日期
	private String gCapitalB;//B股总股本
	private String fCapitalB;//B股流通股本
	private String area;
	private String province;
	private String city;
	private String industry;
	private String website;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCodeA() {
		return codeA;
	}
	public void setCodeA(String codeA) {
		this.codeA = codeA;
	}
	public String getNameA() {
		return nameA;
	}
	public void setNameA(String nameA) {
		this.nameA = nameA;
	}
	public String getDateA() {
		return dateA;
	}
	public void setDateA(String dateA) {
		this.dateA = dateA;
	}
	public String getgCapitalA() {
		return gCapitalA;
	}
	public void setgCapitalA(String gCapitalA) {
		this.gCapitalA = gCapitalA;
	}
	public String getfCapitalA() {
		return fCapitalA;
	}
	public void setfCapitalA(String fCapitalA) {
		this.fCapitalA = fCapitalA;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCodeB() {
		return codeB;
	}
	public void setCodeB(String codeB) {
		this.codeB = codeB;
	}
	public String getNameB() {
		return nameB;
	}
	public void setNameB(String nameB) {
		this.nameB = nameB;
	}
	public String getDateB() {
		return dateB;
	}
	public void setDateB(String dateB) {
		this.dateB = dateB;
	}
	public String getgCapitalB() {
		return gCapitalB;
	}
	public void setgCapitalB(String gCapitalB) {
		this.gCapitalB = gCapitalB;
	}
	public String getfCapitalB() {
		return fCapitalB;
	}
	public void setfCapitalB(String fCapitalB) {
		this.fCapitalB = fCapitalB;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
}
