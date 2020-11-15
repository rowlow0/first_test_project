//http://tech.javacafe.io/2018/12/03/HashMap/ 참조.
//HashMap은 key-value 데이터 구조.
package test;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Fibonacci{
	private Map<Integer,BigInteger> memoizeHashMap=new HashMap<>();
	{
	memoizeHashMap.put(0,BigInteger.ZERO);
	memoizeHashMap.put(1,BigInteger.ONE);
	memoizeHashMap.put(2,BigInteger.ONE);
}
	BigInteger fibonacci(int n) {
		if(memoizeHashMap.containsKey(n)) {
			return memoizeHashMap.get(n);
		}else {
			BigInteger result=fibonacci(n-1).add(fibonacci(n-2));
			memoizeHashMap.put(n,result);
			return result;
		}
	}
}
public class HashMapTest {

	public static void main(String[] args) {
Map<String,Double> productPrice=new HashMap<>();
productPrice.put("Rice", 6.9);
productPrice.put("Flour", 3.9);
productPrice.put("Sugar", 4.9);
productPrice.put("Milk", 3.9);
productPrice.put("Egg", 1.9);
Double egg=productPrice.get("Egg");
System.out.println("The price of Egg is: "+egg);
System.out.println();
Set<String> keys=productPrice.keySet();
for(String key:keys) {
	System.out.println("keys:"+key);
}
System.out.println();
keys.forEach(key -> System.out.println("keys:"+key));
System.out.println();
Collection<Double> values=productPrice.values();
values.forEach(value->System.out.println("values:"+value));
System.out.println();
Set<Map.Entry<String, Double>>entries=productPrice.entrySet();

for(Map.Entry<String, Double> entry:entries) {
	System.out.print("key: "+entry.getKey());
	System.out.println(", value: "+entry.getValue());
}

productPrice.forEach((key,value)->{
	System.out.print("key: "+key);
	System.out.println(", Value: "+value);
});
System.out.println();
Fibonacci fibonacci=new Fibonacci();
for(int i=0;i<100;i++) {
	System.out.println(fibonacci.fibonacci(i));
	}

}
}
