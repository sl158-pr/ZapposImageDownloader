package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class Name: ProductsFileScanner.java
 * 
 * A helper class to scan input text file having a list of product SKUs and
 * return list of product SKUs
 * 
 * @author Sumanth Lakshminarayana, UTA ID: 1000830230
 */

public class ProductsFileScanner {

	/*
	 * Scanning Product SKU file and return String List of SKUS
	 */
	public static ArrayList<String> scanInputSKUSFile(String inputFileName) {

		ArrayList<String> productSKUS = new ArrayList<>();

		try {

			BufferedReader reader = new BufferedReader(new FileReader(
					inputFileName));

			String readLineSKU = null;
			while ((readLineSKU = reader.readLine()) != null) {

				readLineSKU = readLineSKU.trim();

				if (!readLineSKU.isEmpty()) {
					productSKUS.add(readLineSKU);
				}
			}

			reader.close();

		} catch (IOException e) {

			System.out.println("Invalid Input File");
		}

		return productSKUS;
	}

}
