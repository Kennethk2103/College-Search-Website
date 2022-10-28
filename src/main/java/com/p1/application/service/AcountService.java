package com.p1.application.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.boolex.Matcher;

@Service

public class AcountService {



    public static boolean passwordSatisfactory(String password) {
		if(password.length()<5) {
			return false;
		}
		String [] expres = new String[3];
		expres[0] = "[A-Z]";
		expres[1] = "[a-z]";
		expres[2]= "[0-9]";
		
		for(int i =0;i<expres.length;i++) {
			Pattern pattern = Pattern.compile(expres[i]);
			java.util.regex.Matcher m = pattern.matcher(password);
			if(m.find()==false) {
				return false;
			}
		}
		return true;		
	}
}
