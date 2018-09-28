
import edu.princeton.cs.algs4.*;

public class Compose {
	SET<Integer> I;
	ST<Integer,Double> F;
	ST<Integer,ComposeState> Q;
	Queue<Integer> queue;
	
	
	public Compose(WFST wfst1, WFST wfst2, Filter filter) {
		I = new SET<Integer>();
		F = new ST<Integer,Double>();
		Q = new ST<Integer,ComposeState>();
		queue = new Queue<Integer>();
		
		// initialize containers for compose wfst and queue
		for (Integer i1 : wfst1.getInitialStates())
			for (Integer i2 : wfst2.getInitialStates())
				for (Integer i3 : filter.getInitialStates()) {
					ComposeState composeState = new ComposeState(i1, i2, i3);
					Integer i = Q.size();
					Q.put(i, composeState);
					I.add(i);
					queue.enqueue(i);
				}
		
		// make DFS to compose two wfsts
		while (!queue.isEmpty()) {
			Integer q = queue.dequeue();			
			
			ComposeState composeState = Q.get(q);
			
			// q is final state
			if (wfst1.isFinal(composeState.q1()) &&
				wfst2.isFinal(composeState.q2()) &&
				filter.getStates().contains(composeState.q3())) {
					Double weight = wfst1.getFinalStates().get(composeState.q1()) +
									wfst2.getFinalStates().get(composeState.q2()) +
									F.get(composeState.q3());
					F.put(q, weight);
			}
			
			// iterate over all arcs from q1 and q2 and make composition if filter allows
			for (Arc e1 : wfst1.getArcs(composeState.q1())) {
				for (Arc e2 : wfst2.getArcs(composeState.q2())) {
					Filter.FilterResult f = filter.filter(e1, e2, composeState.q3());
					if (filter.isBlockingState(f.q3())) continue;
					
					
					
				}
			}
			
			
		}
	}
	
    public static void main(String[] args) {    	
        WFST wfst1 = new WFST(args[0]);
        WFST wfst2 = new WFST(args[1]);        
        Compose compose = new Compose(wfst1, wfst2, new Filter(wfst1,wfst2));
        //StdOut.println("Start nodes: " + wfst.getStartNodes());
    }
}
