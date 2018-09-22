public class Arc
{	
	final Integer iSymbol;
	final Integer oSymbol;
	final Double weight;
	final Integer nextNode;
	
	public Arc(Integer iSymbol, Integer oSymbol, Double weight, Integer nextNode)
	{
		this.iSymbol = iSymbol;
		this.oSymbol = oSymbol;
		this.weight = weight;
		this.nextNode = nextNode;				
	}
	
    /**
     * Returns a string representation of the Arc.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(nextNode + " " + iSymbol + " " + oSymbol);
        if (weight != 0.0) 
            s.append(" " + weight);
        return s.toString();
    }	
}
