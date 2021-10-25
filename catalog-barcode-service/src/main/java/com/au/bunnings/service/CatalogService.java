package com.au.bunnings.service;

import java.util.LinkedHashMap;

public interface CatalogService {

	LinkedHashMap<String, String> getCatalogsInfo(String fileName, String companyName);

}
