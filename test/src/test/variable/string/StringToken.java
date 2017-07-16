package test.variable.string;

import java.lang.String;
import java.util.StringTokenizer;

public class StringToken
{
    public static void main(String[] args)throws Exception
    {

    	StringTokenizer st = new StringTokenizer("this;is;a;test",";");
    	while (st.hasMoreTokens()) 
    	{
            System.out.println(st.nextToken());
        }
    }
}