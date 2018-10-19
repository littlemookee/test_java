
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;

public class EpsilonMatchingFilter implements Filter
{
	private final Bag<Integer> I;
	private final ST<Integer,Double> Q;
	
	public EpsilonMatchingFilter(WFST wfst1, WFST wfst2) {
		I = new Bag<Integer>();
		I.add(0);
		Q = new ST<Integer,Double>();
		Q.put(0,0.0);
		Q.put(1,0.0);
		Q.put(2,0.0);
	}

	@Override
	public Iterable<Integer> getInitialStates() { return I; }

	@Override
	public boolean containState(Integer q) { return Q.contains(q); }

	@Override
	public Double finalWeight(Integer q) { return Q.get(q); }

	@Override
	public FilterResult filter(Arc e1, Arc e2, Integer q3) {
		int q = Q.size();
		if (e1.o() >  0 && e2.i() >  0 && e1.o() == e2.i())                       q = 0;
		if (e1.i() !=-1 && e1.o() == 0 && e2.i() == 0  && e2.o() !=-1 && q3 == 0) q = 0;
		if (e1.o() ==-1 && e2.i() == 0 && e2.o() != -1 && q3 != 2)                q = 1;
		if (e1.i() !=-1 && e1.o() == 0 && e2.i() == -1 && q3 != 1)                q = 2;		
		return new FilterResult(e1, e2, q);
	}

	@Override
	public boolean isBlockingState(Integer q) { return q == Q.size(); }

}
