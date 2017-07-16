package test.variable.generic;

public class CollectionGenericT02 {

	public static void main(String[] args) {
        Person<String> p1 = new Person<String>();
        Person<StringBuilder> p2 = new Person<StringBuilder>();
        
        System.out.println("p1="+p1);
        System.out.println("p2="+p2);
	}
}

//class Person<E>{
//class Person<eeee>{
//class Person<?>{

class Person<T>{
    public T info;// p1 �Ͻ� ������ Ÿ���� String�̵ȴ�.(�ν��Ͻ� ������ String ���׸� ����)
		// p2 �Ͻ� ������ Ÿ���� StringBuilder�� �ȴ�.
}
