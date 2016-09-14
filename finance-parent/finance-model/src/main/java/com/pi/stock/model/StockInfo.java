package com.pi.stock.model;

import java.util.Date;

public class StockInfo extends BaseObject {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String code;

	private String name;

	private String fullName;

	private String enName;

	private String address;

	private String codeA;

	private String nameA;

	private String dateA;

	private String gCapitalA;

	private String fCapitalA;

	private String codeB;

	private String nameB;

	private String dateB;

	private String gCapitalB;

	private String fCapitalB;

	private String area;

	private String province;

	private String city;

	private String industry;

	private String website;

	private Date createTime;

	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName == null ? null : fullName.trim();
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName == null ? null : enName.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getCodeA() {
		return codeA;
	}

	public void setCodeA(String codeA) {
		this.codeA = codeA == null ? null : codeA.trim();
	}

	public String getNameA() {
		return nameA;
	}

	public void setNameA(String nameA) {
		this.nameA = nameA == null ? null : nameA.trim();
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
		this.gCapitalA = gCapitalA == null ? null : gCapitalA.trim();
	}

	public String getfCapitalA() {
		return fCapitalA;
	}

	public void setfCapitalA(String fCapitalA) {
		this.fCapitalA = fCapitalA == null ? null : fCapitalA.trim();
	}

	public String getCodeB() {
		return codeB;
	}

	public void setCodeB(String codeB) {
		this.codeB = codeB == null ? null : codeB.trim();
	}

	public String getNameB() {
		return nameB;
	}

	public void setNameB(String nameB) {
		this.nameB = nameB == null ? null : nameB.trim();
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
		this.gCapitalB = gCapitalB == null ? null : gCapitalB.trim();
	}

	public String getfCapitalB() {
		return fCapitalB;
	}

	public void setfCapitalB(String fCapitalB) {
		this.fCapitalB = fCapitalB == null ? null : fCapitalB.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry == null ? null : industry.trim();
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website == null ? null : website.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}