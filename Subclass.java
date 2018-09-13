package com.CRM.base;

public class Subclass extends Superclass {
	 int a= 10;
	Subclass()
	{
	
		System.out.println("Subclass constructor");
	}
	
	
	
	public static void main(String[] args)
	{
		Subclass obj = new Subclass();
		Subclass obj1 = new Subclass();
		
		obj.a = 20;
		obj1.a=30;
		System.out.println(obj.a);
		System.out.println(obj1.a);
		
		
	}
	
}
