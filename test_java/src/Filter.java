
import edu.princeton.cs.algs4.*;

public class Filter
{
	WFST wfst1;
	WFST wfst2;
	private final Bag<Integer> initialStates;
	private final SET<Integer> states;
	
	public Filter(WFST wfst1, WFST wfst2) {
		this.wfst1 = wfst1;
		this.wfst2 = wfst2;
		initialStates = new Bag<Integer>();
		initialStates.add(0);
		states = new SET<Integer>();
		states.add(0);
	}
	
	public Bag<Integer> getInitialStates() {
		return initialStates;		
	}
	
	public SET<Integer> getStates() {
		return states;		
	}
}
