package test.variable.collection;

import java.lang.String;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class alala
{
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception
    {

//        char a=','; //char�� �̱�(')
//        char b='' ;
//        String src ="15,000,000";
//        String src1 = ",";
//        String src2 = "";
//        String src3 = "7ABCD1";
    	
    	String haystack = "abdcghbaabcdij";
    	String meedle = "bcda";
    	
		List<Integer> indices01 = new ArrayList<Integer>();
        
        for(int j=0; j<haystack.length();j++){
        	if(haystack.charAt(j) == meedle.charAt(0)){
        		indices01.add(j);        		
        	}
        }

        String haystack2;
		char[] haystack3,meedle3;

		meedle3 = meedle.toCharArray();
		Arrays.parallelSort(meedle3);
		
        for(int i=0;i<indices01.size();i++){
        	if(indices01.get(0) <= meedle.length()){
        		haystack2 = haystack.substring(0, meedle.length());
        		haystack3 = haystack2.toCharArray();
        		Arrays.parallelSort(haystack3);

        		
        		if ( haystack3 == meedle3) {
                    System.out.println("i="+i);
        		}
        	} else  {
        		// sorry, time is lack because my ability. 
        	}
        		
            System.out.println("i="+i);
        }
        

    }	


	public static List<Integer> getAnagramIndices(String haystack, String meedle)
    {
    	

		List<Integer> indices = new ArrayList<Integer>();
   	
		indices.add(1);
        return indices;
        
    }
}
