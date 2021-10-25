package com.au.bunnings.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.bunnings.pojo.Catalog;

@Service
public class CatalogServiceImpl implements CatalogService {
	private Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class);

	@Autowired
	CsvReaderWriterService csvService;

	@Override
	public LinkedHashMap<String, String> getCatalogsInfo(String fileName, String companyName) {

		List<Catalog> catalogList = new ArrayList<>();
		LinkedHashMap<String, String> catalogMap = new LinkedHashMap<>();
		logger.info("Going to read catalogA csv");
		csvService.readCatalogCsv(catalogList, fileName, companyName);
		for (Catalog catalog : catalogList) {
			catalogMap.put(catalog.getSku() + "," + companyName, catalog.getDescription());
		}
		return catalogMap;
	}
}
