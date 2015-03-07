package net.tdu.test.think;

public abstract class TestBase<T> {

	public void print(T [] objs){
		if(objs!=null){
			for(T o : objs){
				System.out.println(o);
			}
		}
	}
	
}
