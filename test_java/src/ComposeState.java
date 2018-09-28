
public class ComposeState implements Comparable<ComposeState>
{
	private final Integer q1;
	private final Integer q2;
	private final Integer q3;
	
	public ComposeState (Integer q1, Integer q2, Integer q3) {
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;		
	}
	
	public int compareTo(ComposeState that) {
		if (this.q1 > that.q1) return +1;
		if (this.q1 < that.q1) return -1;
		if (this.q2 > that.q2) return +1;
		if (this.q2 < that.q2) return -1;
		if (this.q3 > that.q3) return +1;
		if (this.q3 < that.q3) return -1;		
		return 0;		
	}
	
	public Integer q1() {
		return q1;		
	}
	
	public Integer q2() {
		return q2;		
	}

	public Integer q3() {
		return q3;		
	}
}
