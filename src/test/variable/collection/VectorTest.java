package test.variable.collection;

import java.util.Vector;

public class VectorTest extends Vector {
	
	public static void main(String args[]) {
		VectorTest v = new VectorTest();
		
        Vector v1 = new Vector();
        Vector v2 = new Vector();
        Vector v3 = new Vector();
        Vector v4 = new Vector();        

        v1.addElement("a");
        v1.addElement("b");
        v1.addElement("c");
        v1.addElement("d");

        v2.addElement("a");
        v2.addElement("b");
        v2.addElement("c");
        v2.addElement("d");

        v3.addElement("a");
        v3.addElement("b");
        v3.addElement("c");
        v3.addElement("d");

        v4.addElement(v1);
        v4.addElement(v2);
        v4.addElement(v3);
        v4.addElement(v4);


        Vector v5 = new Vector();
        Vector v6 = new Vector();
        Vector v7 = new Vector();
        Vector v8 = new Vector();        

        v5 = (Vector)v4.elementAt(0);
        v6 = (Vector)v4.elementAt(1);
        v7 = (Vector)v4.elementAt(2);
        v8 = (Vector)v4.elementAt(3);        


        for (int i = 0; i < v.size(); i++) 
        {
			System.out.println(v.elementAt(i));
		}				

        String aa = null;
        v.addElement(aa);

        v.addElement("aaaa");
        v.addElement("bbbb");
        v.addElement("cccc");
        v.addElement("dddd");

        for (int i = 0; i < v.size(); i++) 
        {
			System.out.println(v.get(i));
		}				

        Vector v9 = new Vector();        
//        v9.addElement("aaaa");
//        v9.addElement("bbbb");
//        v9.addElement("cccc");
//        v9.addElement("dddd");
//
//	    System.out.println("v9.isEmpty()                             = " + v9.isEmpty()                           );
//	    System.out.println("v9.size()                                = " + v9.size()                              );
//	    System.out.println("v9.capacity()                            = " + v9.capacity()                          );
////	    System.out.println("indexOf(String "aaaa")                   = " + indexOf(String "aaaa")                 );
//	    System.out.println("v9.firstElement()    	                 = " + v9.firstElement() 	                  );
//	    System.out.println("v9.lastElement() 	                     = " + v9.lastElement() 	                  );
////	    System.out.println("v9.setElementAt(String "aaaa", 1) );	 = " + v9.setElementAt(String "aaaa", 1) );	  );
////	    System.out.println("v9.removeAllElements()                   = " + v9.removeAllElements()                 );
	    v9.clear();
	    System.out.println("v9.isEmpty()                             = " + v9.isEmpty()                           );	    

//        Vector v1 = new Vector();
//        String s1 = "";
//
//        v1.addElement("1");
//        v1.addElement("2");
//        v1.addElement("3");
//        v1.addElement("4");        
//        s1 = (String)Collections.max( v1 );
//        System.out.println("s1 = "+s1);        
    
    }
}