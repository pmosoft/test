package test.variable.collection;

import java.util.ArrayList;
import java.util.Vector;
import java.util.*;

public class ArrayListTest
{
	public static void main(String [] args) 
	{
	    // ArrayList�� vector�� �ִ� ���   ����
        ArrayList al1 = new ArrayList(); 
        ArrayList al2 = new ArrayList(); 
        ArrayList al3 = new ArrayList(); 
        ArrayList al4 = new ArrayList(); 
        ArrayList al6 = new ArrayList(); 

        al1.add("aa");
        al1.get(0);
        System.out.println("al1.get(0) = " + al1.get(0) );

        Vector v1 = new Vector();
        Vector v2 = new Vector();
        v1.addElement("va");
        v1.addElement("vb");
        v1.addElement("vc");        
        	
        al1.add(v1);
        v2 = (Vector) al1.get(1);

        System.out.println("al1.get(1) = " + al1.get(1) );
        System.out.println("v2.elementAt(0) = " + v2.elementAt(0) );
        System.out.println("v2.elementAt(1) = " + v2.elementAt(1) );
        System.out.println("v2.elementAt(2) = " + v2.elementAt(2) );                
	    // ArrayList�� vector�� �ִ� ���   ��        
	    
        //CollectionTest ct = new CollectionTest();
        
	    //Collection c1 = ct.col();

        CollectionTest0001 ct = new CollectionTest0001();	    
	    al6 = (ArrayList) ct.col();     

        Vector v3 = new Vector();
        Vector v4 = new Vector();
        
        v3 = (Vector)al6.get(0);
        System.out.println("v3.elementAt(0) = " + v3.elementAt(0) );
        System.out.println("v3.elementAt(1) = " + v3.elementAt(1) );
        System.out.println("v3.elementAt(2) = " + v3.elementAt(2) );                


	    
	}
}

class CollectionTest0001
{
    public Collection col()
    {
        ArrayList  al5 = new ArrayList();
        Vector v3 = new Vector();
        Vector v4 = new Vector();            
        v3.addElement("v3a");
        v3.addElement("v3b");
        v3.addElement("v3c");        
        v4.addElement("v4a");
        v4.addElement("v4b");
        v4.addElement("v4c");        
    
    	al5.add( v3 );
    	al5.add( v4 );
    
    	return al5;
    }
}