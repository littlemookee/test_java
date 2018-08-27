import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF
{
	private int[] id;
	private int count;
	
	public QuickUnionUF(int N)
	{
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}
	
	public int count()
	{ return count; }
	
	public int find(int p)
	{
		while (p != id[p]) p = id[p];
		return p;
	}
	
	public boolean connected(int p, int q)
	{ return find(p) == find(q);}
	
	public void union(int p, int q)
	{
		int pId = find(p);
		int qId = find(q);
		
		if (pId == qId) return;
		
		id[pId] = qId; 
		count--;		
	}
		
	public static void main(String[] args)
	{
		In in = new In(args[0]);
		int N = in.readInt();
		QuickFindUF uf = new QuickFindUF(N);
		while (!in.isEmpty())
		{
			int p = in.readInt();
			int q = in.readInt();
			if (uf.connected(p, q)) continue;
			uf.union(p, q);
			StdOut.println(p + " " + q);
		}
		StdOut.println(uf.count() + " components");
	}
}