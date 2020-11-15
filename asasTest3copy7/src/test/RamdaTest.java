package test;

@FunctionalInterface
interface Say{
	int someting(int a,int b);
}

class Person{
	public void hi(Say line) {
		int number=line.someting(3, 4);
		System.out.println("NUMBER is "+number);
	}
}

public class RamdaTest {

	public static void main(String[] args) {
		Person rin=new Person();
		rin.hi(new Say() {
			public int someting(int a,int b) {
				System.out.println("My Name is Jang");
				System.out.println("Hello~");
				System.out.println("parameter number is "+a+","+b);
				return a*b;
			}
		});

		Person rin1=new Person();
		rin1.hi((a,b)->{
			System.out.println("This is Jang");
			System.out.println("Thank you Lamda");
			System.out.println("parameter number is "+a+","+b);
			return a*b;
		});
	}

}
