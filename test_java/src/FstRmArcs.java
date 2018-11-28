import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class FstRmArcs {
	public static void main(String[] args) {
		WFST wfst = new WFST(args[0]);
		Integer ilabel = Integer.valueOf(args[1]);
		
		Queue<Integer> queue = new Queue<Integer>();
		SET<Integer> visited = new SET<Integer>();
		ST<Integer,Arc> arcTo = new ST<Integer,Arc>();		
		
		// Use DFS for printing arcs
		for (Integer i : wfst.getInitialStates())
			queue.enqueue(i);
		while (!queue.isEmpty()) {
			Integer v = queue.dequeue();
			if (visited.contains(v)) continue;
			visited.add(v);
			for (Arc arc : wfst.getArcs(v)) {
				if (arc.i() == ilabel) {
					if (arcTo.contains(arc.n()))
						throw new IllegalArgumentException("WFST contain at least"
											+ " two arcs to the node " + arc.n());
					arcTo.put(arc.n(), arc);
				} else {
					Integer p = arc.p();
					Double w = arc.w();
					while(arcTo.contains(p)) {						
						w += arcTo.get(p).w();
						p = arcTo.get(p).p();
					}
					StdOut.println(new Arc(arc.i(), arc.o(), w, p, arc.n()));
				}
				queue.enqueue(arc.n());
			}			
		}
		// Print final states
		ST<Integer,Double> F = wfst.getFinalStates();
		for (Integer f : F.keys()) StdOut.println(f + F.get(f));
	}
}
