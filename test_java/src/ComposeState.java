
public class ComposeState implements Comparable<ComposeState>
{
	final Integer state1;
	final Integer state2;
	final Integer state3;
	
	public ComposeState (Integer state1, Integer state2, Integer state3) {
		this.state1 = state1;
		this.state2 = state2;
		this.state3 = state3;		
	}
	
	public int compareTo(ComposeState that) {
		if (this.state1 > that.state1) return +1;
		if (this.state1 < that.state1) return -1;
		if (this.state2 > that.state2) return +1;
		if (this.state2 < that.state2) return -1;
		if (this.state3 > that.state3) return +1;
		if (this.state3 < that.state3) return -1;		
		return 0;		
	}	
}
