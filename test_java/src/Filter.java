
import edu.princeton.cs.algs4.*;

public class Filter
{
	public class FilterResult {
		private final Arc e1;
		private final Arc e2;
		private final int q3;
		
		public FilterResult (Arc e1, Arc e2, Integer q3) {
			this.e1 = e1;
			this.e2 = e2;
			this.q3 = q3;
		}
		
		public Arc e1() { return e1; }
		public Arc e2() { return e2; }
		public int q3() { return q3; }		
	}
	
	WFST wfst1;
	WFST wfst2;
	private final Bag<Integer> I;
	private final SET<Integer> Q;
	
	public Filter(WFST wfst1, WFST wfst2) {
		this.wfst1 = wfst1;
		this.wfst2 = wfst2;
		I = new Bag<Integer>();
		I.add(0);
		Q = new SET<Integer>();
		Q.add(0);
	}
	
	public Bag<Integer> getInitialStates() { return I; }
	
	public SET<Integer> getStates() { return Q; }
	
	public FilterResult filter(Arc e1, Arc e2, Integer q3) {
		int q = 0;
		if (e1.o() != e2.i()) q = 1;
		return new FilterResult(e1, e2, q);
	}
	
	public boolean isBlockingState(Integer q) { return q == Q.size(); }
}
