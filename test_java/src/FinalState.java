
public class FinalState implements Comparable<FinalState>
{
	private final Integer state;
	private final Double weight;
	
	public FinalState(Integer state, Double weight) {
		this.state = state;
		this.weight = weight;		
	}

	public int compareTo(FinalState that) {
		if (this.state > that.state) return +1;
		if (this.state < that.state) return -1;
		return 0;		
	}	
	
    /**
     * Returns a string representation of the Arc.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(state);
        if (weight != 0.0) 
            s.append(" " + weight);
        return s.toString();
    }
}
