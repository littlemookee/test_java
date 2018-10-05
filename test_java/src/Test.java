
public class Test {
	public static void main(String[] args) {		
		int N = 10;
		int[] a = new int[N];
		int k = 3;
		
		for (int i=0; i<N; i++) a[i] = i;

		int processedCount = 0; 
		int baseIndex = 0;
		while (processedCount<N)
		{
			int curElement = a[baseIndex];
			int nextIndex = (baseIndex + k) % N;			
			for(; a[nextIndex] != curElement; nextIndex = (nextIndex + k) % N)
			{
				int tempElement = a[nextIndex];
				a[nextIndex] = curElement;
				curElement = tempElement;
				processedCount++;
			}
			baseIndex = nextIndex + 1;
		}
		
		int l = 0;		
	}
}
