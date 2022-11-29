package com.p1.application.data;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public class Jsonpasser {

	public static void dataFromWeb() {
		int n;
		// TODO Auto-generated method stub
		

		/// replace api key
		double num =1;
		for (int k = 0; k < num; k++) {
			String inline = "";
			try {
				LinkedList<LinkedList<String>> SearchTerms = GetSearchTerms.getTerms();
				String urlString = "https://api.data.gov/ed/collegescorecard/v1/schools.json?school.degrees_awarded.predominant=2,3,4&_fields=";
				for (int i = 0; i < SearchTerms.size(); i++) {
					if (i == (SearchTerms.size() - 1)) {
						urlString += SearchTerms.get(i).get(0);
					} else {
						urlString += SearchTerms.get(i).get(0) + ",";
					}
				}
				urlString += "&&api_key=5mbVerIKAMdqhRABeXysVJFC0Z6BKRq8K5KRVrRc&page=" + k + "";
				//System.out.println(urlString);
				URL url = new URL(urlString);

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();

				int responseCode = conn.getResponseCode();
				// System.out.println("Response code : " + responseCode);

				if (responseCode != 200) {
					throw new RuntimeException("HTTPResponseCode: " + responseCode);

				} else {
					Scanner scan = new Scanner(url.openStream());
					while (scan.hasNextLine()) {
						inline += scan.nextLine();
					}
					scan.close();
				}
				//System.out.println(inline);

				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode node = objectMapper.readValue(inline, JsonNode.class);

				JsonNode array = node.get("results");

				for (int i = 0; i < array.size(); i++) {

					JsonNode jsonNameNode = array.get(i);
						LinkedList<JsonNode> jsonList = new LinkedList<>();
						for (int j = 0; j < jsonNameNode.size(); j++) {
							jsonList.add(jsonNameNode.get(SearchTerms.get(j).get(0)));
						}
						CollegeSQL.insertIntoSQlCollege(jsonList, SearchTerms);
					

				}

				//System.out.println("------------------");
				JsonNode metaData = node.get("metadata");
				JsonNode totalField = metaData.get("total");
				num = Math.ceil(totalField.asDouble()/20);
				//System.out.println("Total Schools: " + totalField.asText());
				JsonNode pageField = metaData.get("page");
				//System.out.println("Page Number: " + pageField.asText());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String allOut(LinkedList list) {
		String out = "";
		for (int i = 0; i < list.size(); i++) {
			out += list.get(0) + ",";
		}
		return out;
	}

}
