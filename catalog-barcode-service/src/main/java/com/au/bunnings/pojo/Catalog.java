package com.au.bunnings.pojo;

public class Catalog {

	private String sku;
	private String description;
	private static String companyName;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static String getCompanyName() {
		return companyName;
	}

	public static void setCompanyName(String companyName) {
		Catalog.companyName = companyName;
	}

}
