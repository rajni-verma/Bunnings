package com.au.bunnings.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.au.bunnings.dto.MergedCatalogDTO;
import com.au.bunnings.service.BarcodeService;
import com.au.bunnings.service.CatalogService;
import com.au.bunnings.service.MergeCatalogService;

@Controller
@RequestMapping("/v1/catalog-barcode")
public class MergeController {

	private Logger logger = LoggerFactory.getLogger(MergeController.class);
	private MergeCatalogService mergeService;
	private CatalogService catalogService;
	private BarcodeService barcodeService;

	@Autowired
	public MergeController(MergeCatalogService mergeService, CatalogService catalogService, BarcodeService barcodeService) {
		this.mergeService = mergeService;
		this.catalogService = catalogService;
		this.barcodeService = barcodeService;
	}

	@GetMapping("/merge")
	public ResponseEntity<List<MergedCatalogDTO>> mergeCatalogs() {
		logger.info("merge catalogs operation...");
		LinkedHashMap<String, String> catalogMapA = this.catalogService
				.getCatalogsInfo("C:\\Bunnings\\codingskills\\input\\catalogA.csv", "A");
		LinkedHashMap<String, String> catalogMapB = this.catalogService
				.getCatalogsInfo("C:\\Bunnings\\codingskills\\input\\catalogB.csv", "B");
		LinkedHashMap<String, List<String>> barcodeMapA = this.barcodeService
				.getBarcodesInfo("C:\\Bunnings\\codingskills\\input\\barcodesA.csv", "A");
		LinkedHashMap<String, List<String>> barcodeMapB = this.barcodeService
				.getBarcodesInfo("C:\\Bunnings\\codingskills\\input\\barcodesB.csv", "B");

		List<MergedCatalogDTO> mergedCatalogList = this.mergeService.mergeCatalogs(catalogMapA, catalogMapB,
				barcodeMapA, barcodeMapB);
		return new ResponseEntity<>(mergedCatalogList, HttpStatus.OK);

	}
}
