package test.variable.collection;

import java.util.*;
import java.lang.*;

public class CountTime
{

    public CountTime() {}
    
	public long getArraySet(int MAXSIZE)
	{
        String str = "Test*";
        String sum = "";
        String tmp = "";
        String[] arr = new String[MAXSIZE];
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                arr[i] = "Test";
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("ThreeDVector set3DPoint ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long getArrayGet(int MAXSIZE)
	{
        String str = "Test*";
        String sum = "";
        String tmp = "";
        String[] arr = new String[MAXSIZE];
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                tmp = arr[i];
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("ThreeDVector set3DPoint ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long getVectorSet(int MAXSIZE)
	{
        String str = "Test*";
        String tmp = "";
        Vector vec   = new Vector();
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                vec.addElement(str);
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("ThreeDVector set3DPoint ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long getVectorGet(int MAXSIZE)
	{
        String str = "Test*";
        String tmp = "";
        Vector vec   = new Vector();
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            for (int i=0 ; i<MAXSIZE ; i++)
                vec.addElement(str);

            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                tmp = (String)vec.elementAt(i);
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("ThreeDVector set3DPoint ERROR : " + e.toString());
        }
    
        return end - start;
    }
    
    public long getVector2Set(int MAXSIZE)
	{
        String str = "Test*";
        String tmp = "";
        Vector vec = new Vector(MAXSIZE);
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                vec.addElement(str);
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("ThreeDVector set3DPoint ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long getVector2Get(int MAXSIZE)
	{
        String str = "Test*";
        String tmp = "";
        Vector vec = new Vector(MAXSIZE);
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            for (int i=0 ; i<MAXSIZE ; i++)
                vec.addElement(str);

            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                tmp = (String)vec.elementAt(i);
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("ThreeDVector set3DPoint ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long getArrayListSet(int MAXSIZE)
	{
        String str = "Test*";
        String tmp = "";
        ArrayList arrList = new ArrayList();
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                arrList.add(str);
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("ThreeDVector set3DPoint ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long getArrayListGet(int MAXSIZE)
	{
        String str = "Test*";
        String tmp = "";
        ArrayList arrList = new ArrayList();
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            for (int i=0 ; i<MAXSIZE ; i++)
                arrList.add(str);

            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                tmp = (String)arrList.get(i);
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("getArrayListGet ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long getArrayList2Set(int MAXSIZE)
	{
        String str = "Test*";
        String tmp = "";
        ArrayList arrList = new ArrayList(MAXSIZE);
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                arrList.add(str);
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("ThreeDVector set3DPoint ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long getArrayList2Get(int MAXSIZE)
	{
        String str = "Test*";
        String tmp = "";
        ArrayList arrList = new ArrayList(MAXSIZE);
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            for (int i=0 ; i<MAXSIZE ; i++)
                arrList.add(str);

            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                tmp = (String)arrList.get(i);
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("getArrayListGet ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long gethashMapSet(int MAXSIZE)
	{
        String str   = "Test*";
        String idx   = "Index";
        String tmp = "";      
        HashMap hash = new HashMap();
        long start   = new Date().getTime();
        long end     = new Date().getTime();
        int  cnt     = 0;

        try
        {
            start = new Date().getTime();
            for (cnt=0 ; cnt<MAXSIZE ; cnt++)
                hash.put(idx, str);
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("ThreeDVector set3DPoint ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long gethashMapGet(int MAXSIZE)
	{
        String str   = "Test*";
        String idx   = "Index";
        String tmp = "";
        HashMap hash = new HashMap();
        long start   = new Date().getTime();
        long end     = new Date().getTime();
        int  cnt     = 0;

        try
        {
            for (cnt=0 ; cnt<MAXSIZE ; cnt++)
                hash.put(new Integer(cnt), str);

            cnt = 0;
            start = new Date().getTime();
            for (cnt=0 ; cnt<MAXSIZE ; cnt++)
                tmp = (String)hash.get(new Integer(cnt));
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("ThreeDVector set3DPoint ERROR : " + e.toString());
        }
    
        return end - start;
    }

    public long getStringTokenizerSet(int MAXSIZE)
	{
        int cnt    = 0;
        String str = "Test*";
        String sum = "";
        String tmp = "";
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
            {
                tmp += str;
                cnt++;
                if(cnt%1000 == 0)
                {
                    sum += tmp;
                    tmp = "";
                }
            }
            sum.substring(0, sum.length()-1);
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("getStringTokenizerSet ERROR : " + e.toString());
        }
    
        return end - start;
    }

	public long getStringTokenizerGet(int MAXSIZE)
	{
        int cnt    = 0;
        String str = "Test*";
        String sum = "";
        String tmp = "";
        StringTokenizer token = null;
        long start = new Date().getTime();
        long end   = new Date().getTime();

        try
        {
            for (int i=0 ; i<MAXSIZE ; i++)
            {
                tmp += str;
                cnt++;
                if(cnt%1000 == 0)
                {
                    sum += tmp;
                    tmp = "";
                }
            }
            sum.substring(0, sum.length()-1);

            token = new StringTokenizer(sum, "*");
            start = new Date().getTime();
            for (int i=0 ; i<MAXSIZE ; i++)
                tmp = token.nextToken();
            end = new Date().getTime();
        } 
        catch(Exception e)
        {
            System.out.println("getStringTokenizerGet ERROR : " + e.toString());
        }
    
        return end - start;
    }


}