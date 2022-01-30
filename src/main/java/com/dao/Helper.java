package com.dao;

public class Helper {
	
	public static String getWords(String des) {
		
		String strs[] = des.split(" ");
		
		if(strs.length > 10) {
			
			String result = "";
			for(int i = 0; i < 10; i++) {
				result += strs[i] + " ";
			}
			//System.out.println(result);
			return result;
		}
		
		else {
			return des;
		}
	}
}
