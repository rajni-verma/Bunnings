package com.au.bunnings.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.au.bunnings.dto.MergedCatalogDTO;
import com.au.bunnings.service.BarcodeService;
import com.au.bunnings.service.CatalogService;
import com.au.bunnings.service.MergeCatalogService;

@SpringBootTest
public class MergeCatalogServiceTest {

	private Logger logger = LoggerFactory.getLogger(MergeCatalogServiceTest.class);

	@Autowired
	CatalogService catalogService;

	@Autowired
	BarcodeService barcodeService;

	@Autowired
	MergeCatalogService mergeCatalogService;

	@Test
	public void testMergeCatalogs() {
		logger.info("Executing merge catalogs test...");
		LinkedHashMap<String, String> catalogMapA = this.catalogService
				.getCatalogsInfo("C:\\Bunnings\\codingskills\\input\\catalogA.csv", "A");
		LinkedHashMap<String, String> catalogMapB = this.catalogService
				.getCatalogsInfo("C:\\Bunnings\\codingskills\\input\\catalogB.csv", "B");
		LinkedHashMap<String, List<String>> barcodeMapA = this.barcodeService
				.getBarcodesInfo("C:\\Bunnings\\codingskills\\input\\barcodesA.csv", "A");
		LinkedHashMap<String, List<String>> barcodeMapB = this.barcodeService
				.getBarcodesInfo("C:\\Bunnings\\codingskills\\input\\barcodesB.csv", "B");

		List<MergedCatalogDTO> mergedCatalogList = this.mergeCatalogService.mergeCatalogs(catalogMapA, catalogMapB,
				barcodeMapA, barcodeMapB);

		assertEquals(7, mergedCatalogList.size());
		assertEquals("165-rcy-650", mergedCatalogList.get(2).getSku());
		assertEquals("Tea - Decaf 1 Cup", mergedCatalogList.get(2).getDescription());
		assertEquals("A", mergedCatalogList.get(2).getSource());
	}
}
