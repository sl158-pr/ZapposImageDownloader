package main;

import java.io.IOException;
import java.util.ArrayList;

import util.HTTPRequestHelper;
import util.HTTPResponseHelpher;
import util.ImageHelperClass;
import util.Product;
import util.ProductsFileScanner;

import com.google.gson.Gson;

/**
 * Class Name: ZapposProductImageDownloader.java
 * 
 * Class Using the Zappos API to find the URL for each product's default image
 * (defaultImageUrl in the product API). The program will download each image
 * and write it into an "images" directory underneath the current directory
 * 
 * @author Sumanth Lakshminarayana, UTA ID: 1000830230
 */

public class ZapposProductImageDownloader {

	/**
	 * @param args
	 */
	private static final String PRODUCT_URL = "http://api.zappos.com/Product/7515478?id=";
	private static final String YOUR_API_KEY = "&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZapposProductImageDownloader zappos = new ZapposProductImageDownloader();
		String inputFileName = null;

		// Reading the input file (having a list of SKUs) provided through
		// command
		// line arguments

		if (args.length != 1) {
			System.out.println("Valid SKU file input required");
			System.exit(0);
		} else {
			inputFileName = args[0].trim();
		}
		if (inputFileName != null && !inputFileName.isEmpty()) {
			zappos.saveProductInfoFromZappos(inputFileName);
		}

	}

	/*
	 * Scans the input file and download each image of valid products and write
	 * it into an "images" directory underneath the current directory
	 */
	public void saveProductInfoFromZappos(String inputFileName) {

		// Scanning the input file to get all the SKUS listed
		ArrayList<String> allZapposSKUS = ProductsFileScanner
				.scanInputSKUSFile(inputFileName);

		for (String singleSKU : allZapposSKUS) {
			storeProductImagesFromZappos(singleSKU);
		}
	}

	/*
	 * HTTP request to Zappos API to get each product information using the SKU
	 * Download each image of valid products, write it into an "images"
	 * directory underneath the current directory
	 */
	private static void storeProductImagesFromZappos(String singleSKU) {

		String httpRequest = PRODUCT_URL + singleSKU + YOUR_API_KEY;

		Gson gsonObject = new Gson();

		try {
			String httpResponse = HTTPRequestHelper.httpRequest(httpRequest);
			//System.out.println(httpResponse);
			HTTPResponseHelpher responseOutput = gsonObject.fromJson(httpResponse,
					HTTPResponseHelpher.class);
			//System.out.println(responseOutput);
			for (int i = 0; i < responseOutput.getProduct().length; i++) {
				Product productInfo = responseOutput.getProduct()[i];
				ImageHelperClass.storeImageLocally(productInfo.getDefaultImageUrl(),
						productInfo.getProductId());
			}
		} catch (IOException e) {

			e.getMessage();
		}
	}
}
