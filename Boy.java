package com.CRM.base;

class Boy extends Human {
	    
	 public  void walk(){
	       System.out.println("Boy walks");
	   }
	public  void walk1()
	   {
	       System.out.println("public boy method");
	   }
	   public static void main( String args[]) {
	       /* Reference is of Human type and object is
	        * Boy type
	        */
	       Boy obj = new Boy();
	       /* Reference is of HUman type and object is
	        * of Human type.
	        */
	       Human obj1 = new Human();
	       obj.walk();
	       obj1.walk1();
	       
	   }
	}
