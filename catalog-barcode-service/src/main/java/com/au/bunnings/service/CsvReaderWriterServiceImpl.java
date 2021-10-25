package com.au.bunnings.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.au.bunnings.dto.MergedCatalogDTO;
import com.au.bunnings.pojo.Catalog;
import com.au.bunnings.pojo.ProductBarcode;

@Service
public class CsvReaderWriterServiceImpl implements CsvReaderWriterService {
	private Logger logger = LoggerFactory.getLogger(CsvReaderWriterServiceImpl.class);

	@Override
	public void readCatalogCsv(List<Catalog> catalogList, String fileName, String companyName) {
		logger.info("Going to read catalog info from csv {} , company {} ", fileName, companyName);
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				Catalog catalog = new Catalog();
				catalog.setSku(values[0]);
				catalog.setDescription(values[1]);
				catalogList.add(catalog);
			}
			Catalog.setCompanyName(companyName);
		} catch (Exception e) {
			logger.info("excpetion occured..." + e.getMessage());
		}
	}

	@Override
	public void readBarcodeCsv(List<ProductBarcode> barcodeList, String fileName) {
		logger.info("Going to read catalog info from csv {} , company {} ", fileName);
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");

				ProductBarcode barcode = new ProductBarcode();
				barcode.setSupplierId(Integer.parseInt(values[0]));
				barcode.setSku(values[1]);
				barcode.setBarcode(values[2]);
				barcodeList.add(barcode);
			}
		} catch (Exception e) {
			logger.info("excpetion occured..." + e.getMessage());
		}
	}
	
	@Override
	public void writeToCsv(FileWriter csvWriter, List<MergedCatalogDTO> mergedCatalogList) {
		try {
			for (MergedCatalogDTO dto : mergedCatalogList) {
				csvWriter.append(dto.getSku());
				csvWriter.append(",");
				csvWriter.append(dto.getDescription());
				csvWriter.append(",");
				csvWriter.append(dto.getSource());
				csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException exception) {
			logger.info("exception occured.." + exception.getMessage());
		}
	}
	
	@Override
	public void writeHeaderToCsv(FileWriter csvWriter) throws IOException{
		csvWriter.append("SKU");
		csvWriter.append(",");
		csvWriter.append("Description");
		csvWriter.append(",");
		csvWriter.append("Source");
		csvWriter.append("\n");
	}

}
