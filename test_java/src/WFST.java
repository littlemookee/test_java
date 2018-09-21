import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;

public class WFST
{
	private Integer numNodes;
	private Integer numArcs;
	private Integer numFinals;
	private Bag<Arc>[] arcs;
	private Integer[] finalNodes;
	private Double[] finalWeights;
	
	private <T> void resize(T[] a, int newSize)
	{
		if (newSize == 0) newSize = 1;
		T[] newA = (T[]) new Object[newSize];
		for (int i=0; i<a.length; i++) newA[i] = a[i];
		a = newA;
	}
	
	private void addFinalNode(int node, double weight)
	{
		if (finalNodes.length <= numFinals) {
			resize(finalNodes, 2*numFinals);
			resize(finalWeights, 2*numFinals);
		}
		finalNodes[numFinals] = node;
		finalWeights[numFinals++] = weight;
	}
	
	private void addArc(int startNode, Arc arc)
	{
		if (arcs.length <= numNodes) {
			resize(arcs, 2*numNodes);
			arcs[numNodes] = new Bag<Arc>();
		}
		arcs[startNode].add(arc);
		numArcs++;
	}
	
	public WFST(String fileName)
	{
		In in = new In(fileName);
		ST<Integer,Integer> st = new ST<Integer,Integer>();
		
		numNodes = 0;
		numArcs = 0;
		numFinals = 0;
		
		while (!in.isEmpty()) {
			String line = in.readLine();
			String[] splited = line.split("\\s+");
			switch (splited.length) {
				case 1: {
					int node = Integer.parseInt(splited[0]);
					double weight = 0.0;
					if (!st.contains(node)) st.put(node, numNodes++);
					addFinalNode(st.get(node), weight);
				}
				break;
				case 2: {
					int node = Integer.parseInt(splited[0]);
					double weight = Double.parseDouble(splited[1]);
					if (!st.contains(node)) st.put(node, numNodes++);
					addFinalNode(st.get(node), weight);
				}
				break;
				case 4: {
					int startNode = Integer.parseInt(splited[0]);
					int endNode = Integer.parseInt(splited[1]);
					int iSymbol = Integer.parseInt(splited[2]);
					int oSymbol = Integer.parseInt(splited[3]);
					double weight = 0.0;
					
					if (!st.contains(startNode))
						st.put(startNode, numNodes++);
					if (!st.contains(endNode))
						st.put(endNode, numNodes++);
					
					addArc(st.get(startNode), new Arc(iSymbol, oSymbol, weight, st.get(endNode)));					
				}
				break;
				case 5: {
					int startNode = Integer.parseInt(splited[0]);
					int endNode = Integer.parseInt(splited[1]);
					int iSymbol = Integer.parseInt(splited[2]);
					int oSymbol = Integer.parseInt(splited[3]);
					double weight = Integer.parseInt(splited[4]);
					
					if (!st.contains(startNode))
						st.put(startNode, numNodes++);
					if (!st.contains(endNode))
						st.put(endNode, numNodes++);
					
					addArc(st.get(startNode), new Arc(iSymbol, oSymbol, weight, st.get(endNode)));					
				}
				break;
				default:
					throw new IllegalArgumentException("Wrong format of WFST line: " + line);
			}
        }
	}	
	
	public static void main(String[] args)
	{
		
		
	}
}
