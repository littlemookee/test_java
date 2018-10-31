
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;

/**
 * 
 */

/**
 * @author mikhail
 * @param <wfst2>
 *
 */
public class LabelReachabilityFilter<wfst2> implements Filter
{
	private final Bag<Integer> I;
	private final ST<Integer,Double> Q;
	private final WFST wfst1;
	private final WFST wfst2;
	private final ST<Integer,Bag<Integer>> r;
	
	private Bag<Integer> procState(Integer q) {
		Bag<Integer> res = new Bag<Integer>();
		for (Arc arc : wfst1.getArcs(q)) {
			if (arc.o() == 0)
				for (Integer l : procState(arc.n()))
					res.add(l);
			else res.add(arc.o());
		}
		return res;
	}
	
	public LabelReachabilityFilter(WFST wfst1, WFST wfst2) {
		this.wfst1 = wfst1;
		this.wfst2 = wfst2;
		I = new Bag<Integer>();
		I.add(0);
		Q = new ST<Integer,Double>();
		Q.put(0,0.0);
		r = new ST<Integer,Bag<Integer>>();
		for (Integer q : wfst1.getStates())
			r.put(q,procState(q));
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
		if (e1.o() == e2.i()) q = 0;
		for (Arc arc : wfst2.getArcs(e2.p())) {
			if (arc.i() == 0) continue;
			if (r(arc.i(),e1.n())) {
				q = 0;
				break;				
			}
		}
		return new FilterResult(e1, e2, q);
	}
	
	private boolean r(int l, int q) {
		boolean r = false;
		return r;
	}

	@Override
	public boolean isBlockingState(Integer q) { return q == Q.size(); }
}
