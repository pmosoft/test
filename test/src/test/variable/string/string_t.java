package test.variable.string;

import java.lang.String;
import java.sql.Date;

public class string_t
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

//		String aaa = "20031002702����                          000000000622620001                                         000000000144140000000013068040000000000000000000000000144140000000000000000000000000000000��������������μ������Ρ�����                                                        0000000700000000007431  0000000000000000";
		
//		System.out.println(aaa.length());

//        byte[] ba;
//        ba = (aaa.getBytes());
	
//		System.out.println(ba.length);
 
//		SET @��ÿ�������  = (SELECT ��ÿ�������  FROM TB_IQF_����_������ WHERE �������� = DATEFORMAT(GETDATE() ,'yyyymmdd'));
//		SET @�Ϳ�������    = (SELECT �Ϳ�������    FROM TB_IQF_����_������ WHERE �������� = DATEFORMAT(GETDATE() ,'yyyymmdd'));    
 
        String src="WHERE �������� = DATEFORMAT(GETDATE() ,'yyyymmdd'))";
		//System.out.println(src);
		//src.substring(0,7

 		src = src.replaceAll("DATEFORMAT\\(GETDATE\\(\\) ,'yyyymmdd'\\)","'20061120'");
		System.out.println(src);

		System.out.println ( System.currentTimeMillis ( ) );
		
		//System.out.println(src.indexOf("getdddate"));
//        String src1="��ȭ��� ���� ���к����� ����� �����մϴ�.";
//        int src_len = src.length();
//        String src_len1 = src_len + "";
//        String src_len2 = Integer.toString(src_len);
//        if(src_len > 14)
//        {
//            src_len1 = src.substring(0,15);
//            src_len1 = src_len1 + "...";
//        }
//        System.out.println(src_len);
//
//
//        String src_len3 = Integer.toString(src_len);
//        String src_len4 = Integer.toString( Integer.parseInt(src_len3)- 1 );
//        System.out.println(src_len4);
//
//        //   molding_nm  = (molding_nm == null ? "&nbsp;" : encode.br(molding_nm));

    }
}
//    src = src.substring(src.indexOf("'")+1,src.length());


//    char a='a'; //char�� �̱�(')
//    char b='b';
//    String src1 ="'";
//    String src2 = "''";
//    System.System.out.printlnln("src1=" + src1);
//    System.System.out.printlnln("src1=" + src1.charAt(0));
//    System.System.out.printlnln("src2=" + src2);
//    System.System.out.printlnln("src2=" + src2.charAt(0));
//    src = src.replace(src1.charAt(0),src2.charAt(0));
//    System.System.out.printlnln(src);

//    public static String line(String src)
//    {
//        int len = src.length();
//        int linenum  = 0, i=0;
//
//        for(i = 0; i < len; i++)
//            if(src.charAt(i) == '\n')
//                linenum++;
//
//        StringBuffer dest = new StringBuffer(len + linenum * 3);
//        for(i = 0; i < len; i++)
//            if(src.charAt(i) == '\n')
//                dest.append("<br>");
//            else
//                dest.append(src.charAt(i));
//
//        return dest.toString();
//    }
