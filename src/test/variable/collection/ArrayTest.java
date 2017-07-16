package test.variable.collection;

public class ArrayTest
{
	public static void main(String [] args) 
	{
		arrayBasicTest01();
		//arrayInitEx();
		
	}

	static void arrayBasicTest01() {
        //String [] sa_alignData  = {"2","2","2","0","0","0","2","0","2","2","1","1","1","1","1","1","1","1"};

		String v_result[][] = new String[10][2]; // reference ���� ����
        v_result[1][0] = "20000101 ";			
        v_result[1][1] = "20011231";
        int len1 = v_result[1][0].length();			
//      System.out.println(Integer.parseInt(v_result[1][0].substring(0,8)));
//      System.out.println(Integer.parseInt(v_result[1][0].trim()));

		String sa_data[] = new String[2]; // reference ���� ����
        sa_data[0] = "����";
        sa_data[1] = "����";

        System.out.println("sa_data[0] =" +sa_data[0]);
        System.out.println("sa_data[1] =" +sa_data[1]);
        if( ("����").equals(sa_data[0]) ) sa_data[0]  = "��������";
        if( ("����").equals(sa_data[1]) ) sa_data[1]  = "����Ȯ��";
        System.out.println("sa_data[0] =" +sa_data[0]);
        System.out.println("sa_data[1] =" +sa_data[1]);

        //if( !("").equals(s_item_name) )
        
        if( Integer.parseInt(v_result[1][0].substring(0,8)) < Integer.parseInt(v_result[1][1]) )
        {
            System.out.println(v_result[1][0]);
            System.out.println(v_result[1][1]);		
        }
        else System.out.println("error");       
        System.out.println(len1);		
	}
	
	static void arrayInitEx() {
        int[] score1 = { 100, 90, 80, 70, 60}; 
        int[] score2 = new int[]{ 100, 90, 80, 70, 60}; 

        String[] name1 = { new String("Kim"), new String("Park"), new String("Yi")}; 
        String[] name2 = { "Kim", "Park", "Yi"}; 
        String[] name3 = new String[]{ new String("Kim"), new String("Park"), new String("Yi")};
        
        //score4[5][3]
        int[][] score4 = {{100, 100, 100}, {20, 20, 20}, {30, 30, 30}, {40, 40, 40}, {50, 50, 50}};
	}
	
	//�����迭
	static void variableArray() {
		
		int[][] score = new int[5][];       // �� ��° ������ ũ��� �������� �ʴ´�. 
		score[0] = new int[3]; 
		score[1] = new int[3]; 
		score[2] = new int[3]; 
		score[3] = new int[3]; 
		score[4] = new int[3];
		
		//ù ��° �ڵ�� ���� 2���� �迭�� �����ϸ� ���簢�� ���̺� ������ �������� �迭�� ������ �� ������, 
		//�� ��° �ڵ�� ���� 2���� �迭�� �����ϸ� ������ ���� �� ������ �ٸ� ũ���� �迭�� �����ϴ� ���� �����ϴ�. 
		int[][] score2 = new int[5][]; 
		score2[0] = new int[4]; 
		score2[1] = new int[3]; 
		score2[2] = new int[2]; 
		score2[3] = new int[2]; 
		score2[4] = new int[3];
		
		//�����迭 ���� �߰�ȣ{}�� �̿��ؼ� ������ ���� ������ �ʱ�ȭ�� ���ÿ� �ϴ� ���� �����ϴ�.
		int[][] score3 = {{100, 100, 100, 100}, {20, 20, 20}, {30, 30}, {40, 40}, {50, 50, 50}}; 
		
	}
	
	
}