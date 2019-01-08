public class Discuss1{
	public static int fib(int n) {
		if(n <= 1){
			return n;
		}
		return fib(n - 2) + fib(n - 1);
	}

	public static int fib2(int n, int k, int f0, int f1) {
		if(n == k) {
			return f0;
		}
		return fib2(n, k + 1, f1, f1 + f0);
	}

	public static void main(String[] args) {
		for(int i = 0; i < 10; i ++) {
			System.out.print(fib2(5, 0, 0, 1) + " ");
		}
		
	}
}