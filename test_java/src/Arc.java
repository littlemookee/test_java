public class Arc
{	
	private final Integer i;
	private final Integer o;
	private final Integer p;
	private final Integer n;
	private final Double w;	
	
	public Arc(Integer i, Integer o, Double w, Integer p, Integer n)
	{
		this.i = i;
		this.o = o;
		this.w = w;
		this.p = p;
		this.n = n;				
	}	
	
	public Integer i() { return i; }
	public Integer o() { return o; }
	public Integer p() { return p; }
	public Integer n() { return n; }
	public Double w() { return w; }	
	
    /**
     * Returns a string representation of the Arc.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + " " + i + " " + o);
        if (w != 0.0) s.append(" " + w);
        return s.toString();
    }	
}
