package com.au.bunnings.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.au.bunnings.dto.MergedCatalogDTO;
import com.au.bunnings.pojo.Catalog;
import com.au.bunnings.pojo.ProductBarcode;

public interface CsvReaderWriterService {

	void readCatalogCsv(List<Catalog> catalogList, String fileName, String companyName);

	void readBarcodeCsv(List<ProductBarcode> barcodeList, String fileName);

	void writeToCsv(FileWriter csvWriter, List<MergedCatalogDTO> mergedCatalogList);

	void writeHeaderToCsv(FileWriter csvWriter) throws IOException;
}
