package test;

public class JavaTest001 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=5,b=3,c=a++,d=c++,e=++d,f=e++;
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
		System.out.println("-------------------------");
		System.out.println("-------------------------");
		int sum=0;
		for(int i=1;i<=10;i++) {
			if(i%3==0) {
				sum+=i;
			}else if(i%4==0) {
				sum+=i;
			}
		}//3,6,9,4,8
		int sum2=3+6+9+4+8;
		System.out.println(sum);
		System.out.println(sum2);
		System.out.println("-------------------------");
		System.out.println("-------------------------");
		int input=4;
		System.out.println(fact(input));
	}
	public static int fact(int n) {
		if(n<=1) {return n;
	}else {
		return fact(n-1)*n;
	}

}
}