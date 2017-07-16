package test.variable.string;
/*

import java.io.*;

public class TabConvSpace {
	public static void main(String[] args) {

        if (args.length != 1){
            System.out.println("����: java ReaderTest ���ϸ�");
            System.exit(0);
        }

		String			s;

        String dir      = "";
        String filename = args[0]+".new";

        FileOutputStream fout;		
        
		try {
			FileReader     fr = new FileReader(args[0]);
			BufferedReader br = new BufferedReader(fr);

            File ff = new File(dir, filename);

            fout = new FileOutputStream (args[0]);            
            
			while ((s=br.readLine()) != null) {
				System.out.println(s.length());
                fout.write(s+"\n");
            }

			fr.close();
			fout.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("������ ������ �������� �ʽ��ϴ�.");
		}
		catch (IOException e) {
			System.out.println("IOException �߻��߽��ϴ�.");
		}
		catch (Exception e) {
			System.out.println("Exception = " + e);
		}
    }
}

*/