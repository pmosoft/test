package test.variable.numeric;

import java.lang.*;
import java.util.*;
import java.math.BigDecimal;                                                 
import java.text.DecimalFormat;                                               


public class divide
{    
    public static void main(String[] args)throws Exception
    {
        double[] da_amt    = {1000,500.6,499.7};
        int      i_treeCnt = 2;

        double[] da_safeAmt = new double[i_treeCnt+1];                          
                                                                              
        double   partTotalAmt = 0.0;
        double   restAmt      = 0.0;
        int      i_maxAmt     = 1;

        for(int i = 0; i <= i_treeCnt; i++)
        {
            da_amt[i] = getRound_d(da_amt[i], "KRW" );              
            System.out.println("da_amt["+i+"] = "+da_amt[i]);
        }            
        if( i_treeCnt == 0 )                                                                 
        {
            da_safeAmt[0] = da_amt[0];
        }
        else if( i_treeCnt == 1 )            
        {
            for(int i = 0; i <= i_treeCnt; i++)
            {
                da_safeAmt[i] = da_amt[i];
            }
        }
        else if( i_treeCnt > 1 )            
        {
            da_safeAmt[0] = da_amt[0];
            for(int i = 1; i <= i_treeCnt; i++)
            {
                da_safeAmt[i] = da_amt[i];
                partTotalAmt  = partTotalAmt + da_safeAmt[i];
                System.out.println("partTotalAmt = "+partTotalAmt);
            }
            restAmt = da_safeAmt[0] - partTotalAmt;
            System.out.println("restAmt = "+restAmt);
        }

        double d_maxAmt = da_amt[2];
        for(int i = 1; i < i_treeCnt; i++)                                
        {                                                                 
            if( da_amt[i] > d_maxAmt )                       
            {                                                         
                d_maxAmt = da_amt[i];
                i_maxAmt    = i;
            }                                                         
        }                           
        da_safeAmt[i_maxAmt] = da_safeAmt[i_maxAmt] + restAmt;                    
        System.out.println("i_maxAmt = "+i_maxAmt);
        for(int i = 0; i <= i_treeCnt; i++)
        {
            System.out.println("da_safeAmt["+i+"] = "+da_safeAmt[i]);
        }            
    }

    private static String getRound(double d_amt, String s_currency ) {               
                                                                              
        String s_amt = "";                                                    
        int    i_cipher = 0; //�ڸ���                                         
                                                                              
        if (("KRW").equals(s_currency) || ("JPY").equals(s_currency) )        
        {                                                                     
            i_cipher = 0;                                                     
            s_amt = getRound(d_amt,0);                                        
        }                                                                     
        else if (("USD").equals(s_currency))                                  
        {                                                                     
            i_cipher = 2;                                                     
            s_amt = getFloor(d_amt,2);                                        
        }                                                                     
        else                                                                  
        {                                                                     
            i_cipher = 0;                                                     
            s_amt = getRound(d_amt,0);                                        
        }                                                                     
        return s_amt;                                                         
    }                                                                         
    private static double getRound_d(double d_amt, String s_currency ) {               
                                                                              
        int    i_cipher = 0; //�ڸ���                                         
                                                                              
        if (("KRW").equals(s_currency) || ("JPY").equals(s_currency) )        
        {                                                                     
            i_cipher = 0;                                                     
            d_amt = getRound_d(d_amt,0);                                        
        }                                                                     
        else if (("USD").equals(s_currency))                                  
        {                                                                     
            i_cipher = 2;                                                     
            d_amt = getFloor_d(d_amt,2);                                        
        }                                                                     
        else                                                                  
        {                                                                     
            i_cipher = 0;                                                     
            d_amt = getRound_d(d_amt,0);                                        
        }                                                                     
        return d_amt;                                                         
    }                                                                         
    private static String getRound(double d, int i_cipher) {                         
        double y = d;                                                         
        BigDecimal money = new BigDecimal(y);                                 
        money = money.setScale(i_cipher, BigDecimal.ROUND_HALF_UP);           
        return money.toString();                                              
    }                                                                         
    private static double getRound_d(double d, int i_cipher) {                         
        double y = d;                                                         
        BigDecimal money = new BigDecimal(y);                                 
        money = money.setScale(i_cipher, BigDecimal.ROUND_HALF_UP);           
        return money.doubleValue();                                              
    }                                                                         
    private static String getFloor(double d, int i_cipher ) {                        
                                                                              
        BigDecimal money = new BigDecimal(d);                                 
        money = money.setScale(i_cipher, BigDecimal.ROUND_FLOOR);             
        return money.toString();                                              
    }                                                                         
    private static double getFloor_d(double d, int i_cipher ) {                        
                                                                              
        BigDecimal money = new BigDecimal(d);                                 
        money = money.setScale(i_cipher, BigDecimal.ROUND_FLOOR);             
        return money.doubleValue();                                              
    }                                                                         
}