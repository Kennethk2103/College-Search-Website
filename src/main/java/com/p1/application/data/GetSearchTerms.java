package com.p1.application.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.domain.EntityScan;
@EntityScan
public class GetSearchTerms {
	private static LinkedList<LinkedList<String>> Terms;
	public static LinkedList<LinkedList<String>> getTerms() {
		return Terms;
	}
	public static void setTerms(LinkedList<LinkedList<String>> terms) {
		Terms = terms;
	}
	public static void setUp(){ //first collum is name, second is datatype
		int i=0;
        Terms = new LinkedList<>();
        File file = new File("searchBy.txt");
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				line = line.replaceAll("\t", " ");
				line = line.replaceAll(" ", ".");
				if(line.contains("root")){
					line = line.substring(line.indexOf("t")+2,line.length());
					Terms.add(new LinkedList<String>());
					Terms.get(i).add(line.substring(0,line.lastIndexOf(".")));
				}
				else{
					Terms.add(new LinkedList<String>());
					Terms.get(i).add("latest."+ line.substring(0,line.lastIndexOf(".")));
				}
				
				Terms.get(i).add(line.substring(line.lastIndexOf(".")+1,line.length()));

				i++;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
    }

}
