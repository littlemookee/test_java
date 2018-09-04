public class Arc
{
	private static final String NEWLINE = System.getProperty("line.separator");
	
	final int isymbol;
	final int osymbol;
	final double weight;
	final int nextnode;
	
	public Arc(int isymbol, int osymbol, double weight, int nextnode)
	{
		this.isymbol = isymbol;
		this.osymbol = osymbol;
		this.weight = weight;
		this.nextnode = nextnode;				
	}
	
    /**
     * Returns a string representation of the Arc.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,  
     *         followed by the <em>V</em> adjacency lists
     */
/*    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }*/	
}
