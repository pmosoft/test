package test.variable.generic;

import java.util.ArrayList;

public class CollectionGenericT01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		collectionGenericTest01();
	}

	static void collectionGenericTest01(){
        // productŬ������ �ڼհ�ü���� ������ �� �ִ� ArrayList�� ����
        ArrayList<Product> list = new ArrayList<Product>();
        list.add(new Product());
        list.add(new Vcr());
        list.add(new Audio());
         
        Product p = list.get(0);
        Vcr v = (Vcr)list.get(1);		
	}
	// ���ϵ�ī�� [?]
	// Product �Ǵ� �� �ڼյ��� ��� ArrayList�� �Ű������� �޴� �޼���
	public static void printAll(ArrayList<? extends Product> list){
	   for(Product p : list){
		   System.out.println(p);
	   }
	}
	// ���ϵ� ī���� �Ǵٸ� ����   
	public static <T extends Product> void printAll2(ArrayList<T> list){
        for(Product p : list){
            System.out.println(p);
        }
    }
	
	static class Product{};
	static class Vcr extends Product{};
	static class Audio extends Product{};
	
}
