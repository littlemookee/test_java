import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF
{
	private int[] id;
	private int count;
	private int touch;
	
	public QuickUnionUF(int N)
	{
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
		touch = N;
	}
	
	public int count()
	{
		return count;
	}
	
	public int find(int p)
	{
		while (p != id[p]) {
			p = id[p];	
			touch += 2;
		}
		touch++;
		return p;
	}
	
	public boolean connected(int p, int q)
	{ return find(p) == find(q);}
	
	public void union(int p, int q)
	{
		int pRoot = find(p);
		int qRoot = find(q);
		
		if (pRoot == qRoot) return;
		
		id[pRoot] = qRoot;
		touch++;
		count--;		
	}
		
	public void resetTouch()
	{
		touch = 0;
	}
	
	public int getTouch()
	{
		return touch;
	}
	
	public static void main(String[] args)
	{
		In in = new In(args[0]);
		int N = in.readInt();
		QuickUnionUF uf = new QuickUnionUF(N);
		while (!in.isEmpty())
		{
			int p = in.readInt();
			int q = in.readInt();
			uf.resetTouch();
			if (uf.connected(p, q)) continue;
			uf.union(p, q);
			StdOut.println(p + " " + q);
			StdOut.println("Touch = " + uf.getTouch());
		}
		StdOut.println(uf.count() + " components");
	}
}