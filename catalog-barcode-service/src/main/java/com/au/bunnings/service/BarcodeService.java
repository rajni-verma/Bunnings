package com.au.bunnings.service;

import java.util.LinkedHashMap;
import java.util.List;

public interface BarcodeService {

	LinkedHashMap<String, List<String>> getBarcodesInfo(String fileName, String companyName);

}
