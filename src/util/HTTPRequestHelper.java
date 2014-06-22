package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class Name: RequestResponseHelperClass.java
 * 
 * Helpher class to request product information to the Zappos
 * and return the response 
 * 
 * @author Sumanth Lakshminarayana, UTA ID: 1000830230
 */

public class HTTPRequestHelper {

	public static String httpRequest(String httpRequest) throws IOException{

		URL httpUrl = new URL(httpRequest);
		HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();

		//Checking for bad requests
		if (connection.getResponseCode() != 200) {
			System.out.println("Error: " + connection.getResponseCode() + " " + connection.getResponseMessage());

			throw new IOException(connection.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		StringBuilder httpResponse = new StringBuilder();

		String readLine;
		while ((readLine = reader.readLine()) != null) 
			httpResponse.append(readLine);

		reader.close();

		connection.disconnect();

		return httpResponse.toString();
	}
}
