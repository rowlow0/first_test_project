package test;

public class SetPage {
	int sum(int a) {
		a=(2-1)*10;
		return a+1;
	}

	public static void main(String[] args) {
		SetPage a=new SetPage();
		System.out.println(a.sum(2));

	}

}
