package com.au.bunnings.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.bunnings.pojo.ProductBarcode;

@Service
public class BarcodeServiceImpl implements BarcodeService {
	private Logger logger = LoggerFactory.getLogger(BarcodeServiceImpl.class);

	@Autowired
	CsvReaderWriterService csvService;

	@Override
	public LinkedHashMap<String, List<String>> getBarcodesInfo(String fileName, String companyName) {
		List<ProductBarcode> barcodeList = new ArrayList<>();
		LinkedHashMap<String, List<String>> barcodeMap = new LinkedHashMap<>();
		logger.info("Going to read Barcodes csv {} , company {} ", fileName, companyName);
		csvService.readBarcodeCsv(barcodeList, fileName);
		createBarcodeMap(barcodeList, barcodeMap);
		return barcodeMap;
	}

	private void createBarcodeMap(List<ProductBarcode> barcodeList, LinkedHashMap<String, List<String>> barcodeMap) {
		List<String> newBarcodeList = null;
		for (ProductBarcode barcode : barcodeList) {
			if (!barcodeMap.containsKey(barcode.getSku())) {
				newBarcodeList = new ArrayList<>();
			}
			newBarcodeList.add(barcode.getBarcode());
			barcodeMap.put(barcode.getSku(), newBarcodeList);
		}
	}

}
