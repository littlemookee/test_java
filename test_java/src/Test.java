import edu.princeton.cs.algs4.*;

public class Test {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int[] a;

	public Test(int N, int k) {
		a = new int[N];
		
		for (int i=0; i<N; i++) a[i] = i;

		int processedCount = 0; 
		int baseIndex = 0;

		while (processedCount < N)
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
	}
	
	public int[] getArray() {
		return a;
	}

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i=0; i<a.length; i++) {
        	if (i > 0)
        		s.append(" ");        
       		s.append(a[i]);
        }
       	s.append(NEWLINE);
        return s.toString();
    }
	
	public static void main(String[] args) {
		Test test = new Test(10,4);
		StdOut.println(test.toString());
	}
}
