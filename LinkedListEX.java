package com.CRM.base;
import java.util.*;


public class LinkedListEX {
	public static void main(String args[]) {

	LinkedList<String> linkedlist  = new LinkedList<String>();
    linkedlist.add("Item1");
    linkedlist.add("Item5");
    linkedlist.add("Item3");
    linkedlist.add("Item6");
    linkedlist.add("Item2");
	 
    for (String str :linkedlist)
    	System.out.println(str);
    
    linkedlist.addFirst("First item");
    linkedlist.addLast("last item");
    
    //Special for loop
    for (String str1 :linkedlist)
    	System.out.println(str1);
    
	//get method
    System.out.println(linkedlist.get(1));
    
    //set
    
    linkedlist.set(4, "setted element");
    System.out.println(linkedlist.get(4));
    
    
    // remove element first & last
    linkedlist.removeFirst();
    linkedlist.removeLast();
    
    System.out.println(linkedlist.get(0));
  
    // remove on basis of index
    
    linkedlist.remove(0);
    System.out.println(linkedlist.get(0));
    
    // to iterate-> Print all values of linked list
    
    // for loop
    
    for (int i=0; i<linkedlist.size();i++)
    	
    {
    	System.out.println(linkedlist.get(i));
    }
    
  // Advanced for loop- for each
    
    for (String str : linkedlist)
    	System.out.println(str);
    
    //using iterator 
    
     Iterator<String> it= linkedlist.iterator();
     while (it.hasNext())
     {
    	 System.out.println(it.next());
     }
     
     // using while loop
     
     int n =0;
     
     while (linkedlist.size()>n)
     {
    	 System.out.println(linkedlist.get(n));
    	 n = n +1;
    	 
     }
    }
}