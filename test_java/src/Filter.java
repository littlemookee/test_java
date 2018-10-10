
public interface Filter
{
	public class FilterResult
	{
		private final Arc e1;
		private final Arc e2;
		private final int q3;
		
		public FilterResult (Arc e1, Arc e2, Integer q3) {
			this.e1 = e1;
			this.e2 = e2;
			this.q3 = q3;
		}
		
		public Arc e1() { return e1; }
		public Arc e2() { return e2; }
		public int q3() { return q3; }		
	}	
	public Iterable<Integer> getInitialStates();
	public boolean containState(Integer q);
	public Double finalWeight(Integer q);
	public FilterResult filter(Arc e1, Arc e2, Integer q3);
	public boolean isBlockingState(Integer q);	
}
