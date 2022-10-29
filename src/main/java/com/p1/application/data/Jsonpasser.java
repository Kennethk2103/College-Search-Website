package com.p1.application.data;

import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;

public class Jsonpasser {

	public static void dataFromWeb(){
		// TODO Auto-generated method stub
		String inline = "";
		
		try {
			///replace api key

			
			
			URL url = new URL("https://api.data.gov/ed/collegescorecard/v1/schools.json?school.degrees_awarded.predominant=2,3,4&_fields=id,school.name,latest.admissions.sat_scores.25th_percentile.critical_reading,latest.admissions.sat_scores.75th_percentile.critical_reading,latest.admissions.sat_scores.25th_percentile.math,latest.admissions.sat_scores.75th_percentile.math&api_key=5mbVerIKAMdqhRABeXysVJFC0Z6BKRq8K5KRVrRc");
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			System.out.println("Response code : " + responseCode);
			
			
			if(responseCode != 200) {
				throw new RuntimeException("HTTPResponseCode: " + responseCode);
				
			}
			else {
				Scanner scan = new Scanner(url.openStream());
				while(scan.hasNextLine()) {
					inline += scan.nextLine();
				}
			}
			System.out.println(inline);
			
			ObjectMapper objectMapper = new ObjectMapper(); 
            JsonNode node = objectMapper.readValue(inline, JsonNode.class); 

            JsonNode array = node.get("results");


            for (int i = 0; i < array.size(); i++) {
                JsonNode jsonNameNode = array.get(i); 
                JsonNode nameNode = jsonNameNode.get("school.name"); 
                JsonNode IDNode = jsonNameNode.get("id");

				JsonNode read25 = jsonNameNode.get("latest.admissions.sat_scores.25th_percentile.critical_reading");
				if(read25==null || read25.asInt()==0){
					CollegeSQL.insertIntoSQlCollege(IDNode.asInt(), nameNode.asText().replaceAll("[^a-zA-Z ]", ""), 200.0,800.0,200.0,800.0);

				}
				else{
					JsonNode read75 = jsonNameNode.get("latest.admissions.sat_scores.75th_percentile.critical_reading");
					JsonNode math25= jsonNameNode.get("latest.admissions.sat_scores.25th_percentile.math");
					JsonNode math75= jsonNameNode.get("latest.admissions.sat_scores.75th_percentile.math");
					CollegeSQL.insertIntoSQlCollege(IDNode.asInt(), nameNode.asText().replaceAll("[^a-zA-Z ]", ""), read25.asDouble(),read75.asDouble(),math25.asDouble(),math75.asDouble());
				}
			

            }


            System.out.println("------------------");
            JsonNode metaData = node.get("metadata");
            JsonNode totalField = metaData.get("total");
            System.out.println("Total Schools: " + totalField.asText());
            JsonNode pageField = metaData.get("page"); 
            System.out.println("Page Number: " + pageField.asText());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String allOut(LinkedList list) {
		String out="";
		for(int i =0;i<list.size();i++) {
			out +=list.get(0) +",";
		}
		return out;
	}

}
