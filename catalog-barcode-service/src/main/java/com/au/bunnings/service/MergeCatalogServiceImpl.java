package com.au.bunnings.service;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.au.bunnings.dto.MergedCatalogDTO;

@Service
public class MergeCatalogServiceImpl implements MergeCatalogService {
	private Logger logger = LoggerFactory.getLogger(MergeCatalogServiceImpl.class);
	private String outputFile = "C:\\Bunnings\\codingskills\\output\\my_output.csv";

	@Autowired
	private CsvReaderWriterService csvService;

	@Override
	public List<MergedCatalogDTO> mergeCatalogs(LinkedHashMap<String, String> catalogMapA,
			LinkedHashMap<String, String> catalogMapB, LinkedHashMap<String, List<String>> barcodeMapA,
			LinkedHashMap<String, List<String>> barcodeMapB) {
		logger.info("Going to start merging of catalogs...");
		Set<Entry<String, String>> catalogSetA = catalogMapA.entrySet();
		Set<Entry<String, List<String>>> barcodeSetB = barcodeMapB.entrySet();
		FileWriter csvWriter = null;
		List<MergedCatalogDTO> mergedCatalogList = new ArrayList<>();
		try {
			csvWriter = new FileWriter(outputFile);
			csvService.writeHeaderToCsv(csvWriter);

			for (Entry<String, String> catlogA : catalogSetA) {
				String productCodeInfoA = catlogA.getKey();
				logger.info("processing product " + productCodeInfoA);
				String[] productCodeArrayA = productCodeInfoA.split(",");
				String productCodeA = productCodeArrayA[0];
				String sourceA = productCodeArrayA[1];
				List<String> barcodeAList = barcodeMapA.get(productCodeA); // get barcodeA list based on product code
				boolean found = false;
				for (String barcodeA : barcodeAList) {
					for (Entry<String, List<String>> barcodeB : barcodeSetB) {
						List<String> barcodesBList = barcodeB.getValue();
						if (barcodesBList.contains(barcodeA)) {
							logger.info("Going to delete key from catalogB " + barcodeB.getKey());
							catalogMapB.remove(barcodeB.getKey() + ",B");
							found = true;
						}
					}
					if (found) {
						break;
					}
				}
				if (!found) {
					createMergedList(catlogA, catalogMapB, mergedCatalogList);
				}
				setDtoInMergedList(productCodeA, catlogA.getValue(), sourceA, mergedCatalogList);
			}
			csvService.writeToCsv(csvWriter, mergedCatalogList);
		} catch (Exception e) {
			logger.info("exception occured " + e.getStackTrace());
		}
		return mergedCatalogList;
	}

	private void createMergedList(Entry<String, String> catlogA, LinkedHashMap<String, String> catalogMapB,
			List<MergedCatalogDTO> mergedCatalogList) {

		String desc = catlogA.getValue();
		String key = catalogMapB.entrySet().stream().filter(a -> a.getValue().equals(desc)).map(m -> m.getKey())
				.findFirst().orElse(null);
		String[] productCodeArrayB = key.split(",");
		String productCodeB = productCodeArrayB[0];
		String sourceB = productCodeArrayB[1];
		String description = catalogMapB.get(productCodeB + "," + sourceB);
		setDtoInMergedList(productCodeB, description, sourceB, mergedCatalogList);
	}

	private void setDtoInMergedList(String productCode, String description, String source,
			List<MergedCatalogDTO> mergedCatalogList) {
		MergedCatalogDTO mergedDto = new MergedCatalogDTO();
		mergedDto.setSku(productCode);
		mergedDto.setDescription(description);
		mergedDto.setSource(source);
		mergedCatalogList.add(mergedDto);
	}
}
