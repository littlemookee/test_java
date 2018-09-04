import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import java.util.NoSuchElementException;

public class WFST
{
	private final int numnodes;
	private final int numarcs;
	private Bag<Arc>[] arcs;
	private final int finalnode;
	private final double finalweight;
	
	public WFST(In in)
	{
		try {
			
			while (!in.isEmpty()) {
				String[] splited = in.readLine().split("\\s+");
				switch (splited.length) {
				case 1: {
					finalnode = Integer.parseInt(splited[0]);
					finalweight = 0.0;
				}
					break;
				case 2:
					break;
				case 4:
					break;
				case 5:
					break;
				default:
					break;
				}
				
				
			}
			
			
			
            numnodes = in.readInt();
            if (numnodes < 0) throw new IllegalArgumentException("number of nodes in a WFST must be nonnegative");
            /*indegree = new int[V];
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }*/
            numarcs = in.readInt();
            if (numarcs < 0) throw new IllegalArgumentException("number of arcs in a WFST must be nonnegative");
            /*for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w); 
            }*/
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }		
		
	}	
	
	public static void main(String[] args)
	{
		
		
	}
}
