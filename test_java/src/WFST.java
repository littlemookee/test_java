import edu.princeton.cs.algs4.*;
//import java.util.NoSuchElementException;

public class WFST
{
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final ST<Integer,Bag<Arc>> arcs;
	private final SET<Integer> I;
	private final ST<Integer,Double> F;
		
	public WFST(String fileName)
	{
		arcs = new ST<Integer,Bag<Arc>>();
		I = new SET<Integer>();
		F = new ST<Integer,Double>();		
		In in = new In(fileName);
		while (!in.isEmpty()) {
			String line = in.readLine();
			String[] splited = line.split("\\s+");
			switch (splited.length) {
				case 1: {
					Integer q = Integer.parseInt(splited[0]);
					if (!I.contains(q)) I.add(q);
					F.put(q, 0.0);
				}
				break;
				case 2: {
					Integer q = Integer.parseInt(splited[0]);
					Double w = Double.parseDouble(splited[1]);
					if (!I.contains(q)) I.add(q);
					F.put(q, w);
				}
				break;
				case 4: {
					int p = Integer.parseInt(splited[0]);
					int n = Integer.parseInt(splited[1]);
					int i = Integer.parseInt(splited[2]);
					int o = Integer.parseInt(splited[3]);
					
					Bag<Arc> E;
					if (arcs.contains(p)) E = arcs.get(p);
					else				  E = new Bag<Arc>();
					E.add(new Arc(i, o, 0.0, p, n));
					arcs.put(p, E);
					if (!I.contains(p)) I.add(p);
				}
				break;
				case 5: {
					int p = Integer.parseInt(splited[0]);
					int n = Integer.parseInt(splited[1]);
					int i = Integer.parseInt(splited[2]);
					int o = Integer.parseInt(splited[3]);					
					double w = Double.parseDouble(splited[4]);
					
					Bag<Arc> E;
					if (arcs.contains(p)) E = arcs.get(p);
					else				  E = new Bag<Arc>();
					E.add(new Arc(i, o, w, p, n));
					arcs.put(p, E);
					if (!I.contains(p)) I.add(p);
				}
				break;
				default:
					throw new IllegalArgumentException("Wrong format of WFST line: " + line);
			}
        }
		for (Integer q : arcs.keys())			
			for (Arc e : arcs.get(q))
				if (I.contains(e.n())) I.delete(e.n());
		
		for (Integer f : F.keys()) {
			Bag<Arc> E;
			if (arcs.contains(f)) E = arcs.get(f);
			else {
				E = new Bag<Arc>();
				arcs.put(f, E);	
			}
			// Use -1 as <eps>_L, add for each state arc(-1,0) arc(0,-1)
			E.add(new Arc(-1, 0, 0.0, f, f));
			E.add(new Arc(0, -1, 0.0, f, f));
		}
	}
	
    /**
     * Returns a string representation of the Arc.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Integer q : arcs.keys())
        	for (Arc e : arcs.get(q))
        		if (e.i() != -1 && e.o() != -1)
        			s.append(q + " " + e + NEWLINE);        
        for (Integer q : F.keys())
        	s.append(q + " " + F.get(q) + NEWLINE);
        return s.toString();
    }
    
    public SET<Integer> getInitialStates() { return I; }
    
    public ST<Integer,Double> getFinalStates() { return F; }
    
    public boolean isFinal(Integer q) { return F.contains(q); }
    
    public Bag<Arc> getArcs(Integer q) {
    	if (arcs.contains(q)) return arcs.get(q);
    	else 				  return new Bag<Arc>();
    }    
	
    public static void main(String[] args) {    	
        WFST wfst = new WFST(args[0]);
        StdOut.println(wfst);
        StdOut.println("Initial states: " + wfst.getInitialStates());
    }
}
