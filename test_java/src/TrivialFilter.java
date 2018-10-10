
import edu.princeton.cs.algs4.*;

public class TrivialFilter implements Filter
{
	private final Bag<Integer> I;
	private final ST<Integer,Double> Q;
	
	public TrivialFilter(WFST wfst1, WFST wfst2) {
		I = new Bag<Integer>();
		I.add(0);
		Q = new ST<Integer,Double>();
		Q.put(0,0.0);
	}
	
	public Iterable<Integer> getInitialStates() { return I; }
	
	public Iterable<Integer> getStates() { return Q.keys(); }
	
	public boolean containState(Integer q) { return Q.contains(q);  }
	
	public Double finalWeight(Integer q ) { return Q.get(q); }
	
	public FilterResult filter(Arc e1, Arc e2, Integer q3) {
		int q = 0;
		if (e1.o() != e2.i()) q = 1;
		return new FilterResult(e1, e2, q);
	}
	
	public boolean isBlockingState(Integer q) { return q == Q.size(); }
}
