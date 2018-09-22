public class FinalNode
{
	private final Integer node;
	private final Double weight;
	
	public FinalNode(Integer node, Double weight) {
		this.node = node;
		this.weight = weight;		
	}

    /**
     * Returns a string representation of the Arc.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(node);
        if (weight != 0.0) 
            s.append(" " + weight);
        return s.toString();
    }	
}
