
import edu.princeton.cs.algs4.*;

public class Compose {
	ST<Integer,ComposeState> Q;
	SET<ComposeState> I;
	Queue<Integer> queue;
	Bag<FinalState> F;
	
	public Compose(WFST wfst1, WFST wfst2, Filter filter) {
		F = new Bag<FinalState>();
		for (Integer state1 : wfst1.getInitialStates())
			for (Integer state2 : wfst2.getInitialStates())
				for (Integer state3 : filter.getInitialStates()) {
					ComposeState composeState = new ComposeState(state1, state2, state3);
					Integer state = Q.size();
					Q.put(state, composeState);
					I.add(composeState);
					queue.enqueue(state);
				}
		
		while (!queue.isEmpty()) {
			Integer state = queue.dequeue();
			ComposeState composeState = Q.get(state);
			if (wfst1.getFinalStates().contains(new FinalState(composeState.state1,0.0)) &&
				wfst2.getFinalStates().contains(new FinalState(composeState.state2,0.0)) &&
				filter.getStates().contains(composeState.state3)) {
				
				
				
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
