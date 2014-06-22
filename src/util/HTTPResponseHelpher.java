package util;


/**
 * Class Name: RequestResponseHelperClass.java
 * 
 * Helpher class to store the response in the format for Zappos API call for a
 * product search
 * 
 * @author Sumanth Lakshminarayana, UTA ID: 1000830230
 */

public class HTTPResponseHelpher {

	public String getStatusCode() {
		return statusCode;
	}

	public Product[] getProduct() {
		return product;
	}

	private String statusCode;
	private Product[] product;	

	public HTTPResponseHelpher(String response, Product[] products) {
		this.statusCode = response;
		this.product = products;
	}


}
