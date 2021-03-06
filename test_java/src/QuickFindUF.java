import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF
{
	private int[] id;
	private int count;
	private int touch;
	
	public QuickFindUF(int N)
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
		touch++;
		return id[p];
	}
	
	public boolean connected(int p, int q)
	{
		return find(p) == find(q);
	}
	
	public void union(int p, int q)
	{
		int pId = id[p];
		int qId = id[q];
		touch += 2;
		
		if (pId == qId) return;
		
		for (int i = 0; i < id.length; i++) {
			touch++;
			if (id[i] == pId) {
				id[i] = qId;
				 touch++;				
			}			
		}
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
		QuickFindUF uf = new QuickFindUF(N);
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