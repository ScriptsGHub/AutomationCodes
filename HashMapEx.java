package com.CRM.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapEx {

	public static void main(String[] args) {
		
		//Hash map is a class which implements Map interface 
		//inherits AbstractMap class
		// Contain only unique elements
		// stores values on basis of key and value
		// it may have one null key and multiple null values
		// it maintains no order
		
		HashMap<Integer, String> hm= new HashMap<Integer, String>();
		
		//strore value
		
		hm.put(1, "value1");
		hm.put(2, "value2");
		hm.put(3, "value3");
		hm.put(4, "value4");
		hm.put(5, "value5");
		
		//Fetch value
		
		System.out.println(hm.get(1));
		System.out.println(hm.get(4));
		
		// for loop- Entry set
		
		for(Entry<Integer, String> m : hm.entrySet())
		{
			System.out.println(m.getKey() + " " + m.getValue());
		}
		
		// remove -> complete Key and value will be removed no shift
		
		System.out.println(hm);
		hm.remove(3);
		System.out.println(hm);
	}

}
