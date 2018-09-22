import edu.princeton.cs.algs4.*;
//import java.util.NoSuchElementException;

public class WFST
{
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final ST<Integer,Bag<Arc>> arcs;
	private final Bag<FinalNode> finalNodes;
		
	public WFST(String fileName)
	{
		arcs = new ST<Integer,Bag<Arc>>();
		finalNodes = new Bag<FinalNode>();
		In in = new In(fileName);
		while (!in.isEmpty()) {
			String line = in.readLine();
			String[] splited = line.split("\\s+");
			switch (splited.length) {
				case 1: {
					Integer node = Integer.parseInt(splited[0]);					
					finalNodes.add(new FinalNode(node, 0.0));
				}
				break;
				case 2: {
					Integer node = Integer.parseInt(splited[0]);
					Double weight = Double.parseDouble(splited[1]);
					finalNodes.add(new FinalNode(node, weight));					
				}
				break;
				case 4: {
					int startNode = Integer.parseInt(splited[0]);
					int endNode = Integer.parseInt(splited[1]);
					int iSymbol = Integer.parseInt(splited[2]);
					int oSymbol = Integer.parseInt(splited[3]);
					
					Bag<Arc> outArcs;
					if (arcs.contains(startNode)) outArcs = arcs.get(startNode);
					else 						  outArcs = new Bag<Arc>();
					outArcs.add(new Arc(iSymbol,oSymbol,0.0,endNode));
					arcs.put(startNode, outArcs);
				}
				break;
				case 5: {
					int startNode = Integer.parseInt(splited[0]);
					int endNode = Integer.parseInt(splited[1]);
					int iSymbol = Integer.parseInt(splited[2]);
					int oSymbol = Integer.parseInt(splited[3]);					
					double weight = Double.parseDouble(splited[4]);
					
					Bag<Arc> outArcs;
					if (arcs.contains(startNode)) outArcs = arcs.get(startNode);
					else 						  outArcs = new Bag<Arc>();
					outArcs.add(new Arc(iSymbol,oSymbol,weight,endNode));
					arcs.put(startNode, outArcs);
				}
				break;
				default:
					throw new IllegalArgumentException("Wrong format of WFST line: " + line);
			}
        }
	}
	
    /**
     * Returns a string representation of the Arc.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Integer key : arcs.keys())
        	for (Arc arc : arcs.get(key))
        		s.append(key + " " + arc + NEWLINE);        
        for (FinalNode node : finalNodes)
        	s.append(node + NEWLINE);
        return s.toString();
    }	
	
    public static void main(String[] args) {    	
        WFST wfst = new WFST(args[0]);
        StdOut.println(wfst);
    }
}
