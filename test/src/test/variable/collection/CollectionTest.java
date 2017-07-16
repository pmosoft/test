package test.variable.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CollectionTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listTest01();
		
	}

	static void listTest01(){

		Collection list = new ArrayList();
		ArrayList list2 = new ArrayList();
		
		//List<Sam01> list3 = new List<Sam01>();
		
		List<Sam01> names2 = new ArrayList<Sam01>();
		List<String> names = new ArrayList<String>();
		
		names.add("a1");names.add("a2");
		names2.add(new Sam01("a","b"));
		
	}
	static class Sam01{		
		public String s1;
		public String s2;
		
		public Sam01(String s1, String s2){
			this.s1 = "s1";
			this.s2 = "s2";			
		}
	}

}
