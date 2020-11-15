package test;

import java.util.Scanner;

public class MultipleOfFour {

	// TODO Auto-generated method stub
	 static int multipleOfFour(int val) {
int i=1;
int count=0;
for(i=1;i<=val;i++)if(i%4==0) {count++;}return count;
	 }
	public static void main(String[] args) {
		Scanner val=new Scanner(System.in);
		int b=val.nextInt();
		System.out.println(b);
		int a=MultipleOfFour.multipleOfFour(b);
		System.out.println(a);
		val.close();
		
	}

}
