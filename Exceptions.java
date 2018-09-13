package com.CRM.base;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Exceptions {
	
	
	   
		public static void main(String args[]) 
	    {  
			System.out.println("Enter the value");
			Scanner scn = new Scanner(System.in);
	    
	    
        try
        {
        	int n = Integer.parseInt(scn.nextLine());
            if (99%n == 0)
                System.out.println(n + " is a factor of 99");
           throw new FileNotFoundException();
            
            
        }
        
        catch (ArithmeticException| NumberFormatException  ex)
        {
            System.out.println("Number Format Exception " + ex);
        }
        
        catch (IOException  ex)
        {
            System.out.println("Number Format Exception " + ex);
        }
        
        try 
        {
        	throw new ClassNotFoundException();
        }
       
        catch ( ClassNotFoundException ex)
        {
            System.out.println("Number Format Exception " + ex);
        }
       
       
        catch ( Exception ex)
        {
            System.out.println("Arithmetic " + ex);
        }
        
	}
}
