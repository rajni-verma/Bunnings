package com.au.bunnings.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.au.bunnings.dto.MergedCatalogDTO;

public interface MergeCatalogService {

	List<MergedCatalogDTO> mergeCatalogs(LinkedHashMap<String, String> catalogMapA,
			LinkedHashMap<String, String> catalogMapB, LinkedHashMap<String, List<String>> barcodeMapA,
			LinkedHashMap<String, List<String>> barcodeMapB);
}
