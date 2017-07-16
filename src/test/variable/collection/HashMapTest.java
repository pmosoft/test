package test.variable.collection;

import java.util.HashMap;

public class HashMapTest{
	
	public static void main(String args[]) 
	{
		MapEx02();
    }

	static void MapEx02(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("people", "���");
        map.put("baseball", "�߱�");

        System.out.println(map.get("people"));
        System.out.println(map.containsKey("people"));
        System.out.println(map.remove("people"));
        System.out.println(map.size());	}
	
	
	static void MapEx01(){
        String str   = "Test*";
        String idx   = "Index";
        HashMap hash = new HashMap();
        hash.put(idx, str);
        System.out.println(hash.get(idx));		
	}
}