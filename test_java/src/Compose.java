
import edu.princeton.cs.algs4.*;

public class Compose {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final SET<Integer> I;
	private final ST<Integer,Double> F;
	private final ST<ComposeState,Integer> Q;
	private final Queue<ComposeState> queue;
	private final ST<Integer,Bag<Arc>> arcs;
	
	public Compose(WFST wfst1, WFST wfst2, Filter filter) {
		I = new SET<Integer>();
		F = new ST<Integer,Double>();
		Q = new ST<ComposeState,Integer>();
		queue = new Queue<ComposeState>();
		arcs = new ST<Integer,Bag<Arc>>();		
		
		// initialize containers for compose wfst and queue
		for (Integer q1 : wfst1.getInitialStates())
			for (Integer q2 : wfst2.getInitialStates())
				for (Integer q3 : filter.getInitialStates()) {
					ComposeState composeState = new ComposeState(q1, q2, q3);
					Integer q = Q.size();
					Q.put(composeState, q);
					I.add(q);
					queue.enqueue(composeState);
				}
		
		// make DFS to compose two wfsts
		while (!queue.isEmpty()) {
			ComposeState composeState = queue.dequeue();
			
			// is final state
			if (wfst1.isFinal(composeState.q1()) &&
				wfst2.isFinal(composeState.q2()) &&
				filter.containState(composeState.q3())) {
					Double weight = wfst1.getFinalStates().get(composeState.q1()) +
									wfst2.getFinalStates().get(composeState.q2()) +
									filter.finalWeight(composeState.q3());
					F.put(Q.get(composeState), weight);
			}
			
			// iterate over all arcs from q1 and q2 and make composition if filter allows
			for (Arc e1 : wfst1.getArcs(composeState.q1())) {
				for (Arc e2 : wfst2.getArcs(composeState.q2())) {
					Filter.FilterResult f = filter.filter(e1, e2, composeState.q3());
					
					if (filter.isBlockingState(f.q3())) continue;
					
					// add state
					ComposeState newComposeState = new ComposeState(f.e1().n(), f.e2().n(),f.q3());
					if (!Q.contains(newComposeState)) {
						Q.put(newComposeState, Q.size());
						queue.enqueue(newComposeState);
					}
					
					// add arc
					Bag<Arc> E;
					Integer p = Q.get(composeState);
					Integer n = Q.get(newComposeState);
					if (arcs.contains(p)) E = arcs.get(p);
					else				  E = new Bag<Arc>();
					Arc arc = new Arc(f.e1().i(), f.e2().o(), f.e1().w() + f.e2().w(), n); 
					E.add(arc);
					arcs.put(p, E);
					//StdOut.println(p + " " + arc);
				}
			}
		}		
		if (F.isEmpty())
			throw new IllegalArgumentException("Compose: Cannot compose these WFSTs with this filter");
	}
	
    /**
     * Returns a string representation of the Arc
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Integer q : arcs.keys())
        	for (Arc e : arcs.get(q))
        		if (e.i() != -1 && e.o() != -1)
        			s.append(q + " " + e + NEWLINE);        
        for (Integer q : F.keys())
        	s.append(q + " " + F.get(q) + NEWLINE);
        return s.toString();
    }
	
    public static void main(String[] args) {    	
        WFST wfst1 = new WFST(args[0]);
        StdOut.println(wfst1);
        WFST wfst2 = new WFST(args[1]);
        StdOut.println(wfst2);
        Compose composeWFST = new Compose(wfst1, wfst2, new EpsilonMatchingFilter(wfst1, wfst2));
        StdOut.println(composeWFST);
    }
}
