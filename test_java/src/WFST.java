import edu.princeton.cs.algs4.*;
//import java.util.NoSuchElementException;

public class WFST
{
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final ST<Integer,Bag<Arc>> arcs;
	private final SET<FinalState> finalStates;
	private final SET<Integer> initialStates;
		
	public WFST(String fileName)
	{
		arcs = new ST<Integer,Bag<Arc>>();
		finalStates = new SET<FinalState>();
		initialStates = new SET<Integer>();
		In in = new In(fileName);
		while (!in.isEmpty()) {
			String line = in.readLine();
			String[] splited = line.split("\\s+");
			switch (splited.length) {
				case 1: {
					Integer node = Integer.parseInt(splited[0]);					
					finalStates.add(new FinalState(node, 0.0));
					if (!initialStates.contains(node)) initialStates.add(node);
				}
				break;
				case 2: {
					Integer node = Integer.parseInt(splited[0]);
					Double weight = Double.parseDouble(splited[1]);
					finalStates.add(new FinalState(node, weight));
					if (!initialStates.contains(node)) initialStates.add(node);
				}
				break;
				case 4: {
					int prevState = Integer.parseInt(splited[0]);
					int nextState = Integer.parseInt(splited[1]);
					int iSymbol = Integer.parseInt(splited[2]);
					int oSymbol = Integer.parseInt(splited[3]);
					
					Bag<Arc> outArcs;
					if (arcs.contains(prevState)) outArcs = arcs.get(prevState);
					else 						  outArcs = new Bag<Arc>();
					outArcs.add(new Arc(iSymbol,oSymbol,0.0,nextState));
					arcs.put(prevState, outArcs);
					if (!initialStates.contains(prevState)) initialStates.add(prevState);
				}
				break;
				case 5: {
					int prevState = Integer.parseInt(splited[0]);
					int nextState = Integer.parseInt(splited[1]);
					int iSymbol = Integer.parseInt(splited[2]);
					int oSymbol = Integer.parseInt(splited[3]);					
					double weight = Double.parseDouble(splited[4]);
					
					Bag<Arc> outArcs;
					if (arcs.contains(prevState)) outArcs = arcs.get(prevState);
					else 						  outArcs = new Bag<Arc>();
					outArcs.add(new Arc(iSymbol,oSymbol,weight,nextState));
					arcs.put(prevState, outArcs);
					if (!initialStates.contains(prevState)) initialStates.add(prevState);
				}
				break;
				default:
					throw new IllegalArgumentException("Wrong format of WFST line: " + line);
			}
        }
		for (Integer node : arcs.keys())
			for (Arc arc : arcs.get(node))
				if (initialStates.contains(arc.getNextNode()))
					initialStates.delete(arc.getNextNode());
	}
	
    /**
     * Returns a string representation of the Arc.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Integer key : arcs.keys())
        	for (Arc arc : arcs.get(key))
        		s.append(key + " " + arc + NEWLINE);        
        for (FinalState node : finalStates)
        	s.append(node + NEWLINE);
        return s.toString();
    }
    
    public SET<Integer> getInitialStates() {
    	return initialStates;
    }
    
    public SET<FinalState> getFinalStates() {
    	return finalStates;    	
    }
	
    public static void main(String[] args) {    	
        WFST wfst = new WFST(args[0]);
        StdOut.println(wfst);
        StdOut.println("Initial states: " + wfst.getInitialStates());
    }
}
