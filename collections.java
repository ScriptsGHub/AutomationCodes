package com.CRM.base;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class collections {

	
	    public static void main(String[] args)
	    {
	    	ArrayList<Integer>  ar= new ArrayList<Integer>();
	    	for ( int i=0;i <10; i++)
	    		ar.add(i);
	    	
	    	Iterator<Integer> itr = ar.iterator();
	    	while (itr.hasNext())
	    	{
	    		int a = (Integer)itr.next();
	    		System.out.print(ar);
	    		if (a % 2 != 0)
	             itr.remove();
	    	}
	    	
	    	System.out.println(ar);
	    	
	    
	 
	    	
	      
	       
	   }

}
