
public class prettyHelperFuncs {
	
	//HELPER FUNCTIONS FOR COLLECTING P TABLE AND FOR RECONSTRUCTION

	public static void PrintReconstruction(int[] a, String[] words, int n)
	{
		if (a[n] == 0) //base case
		{
			return;
		}
		
		PrintReconstruction(a, words, a[n]-1);
		for (int i = a[n]; i<=n; i++)
		{
			System.out.print(words[i] + " ");
		}
		System.out.println();
	
	}
	
	public static int[][] ConstructPTable(int M, int n, int[] lengths)
	{
		//es[i][j] is number of extra spaces at the end of a line when we put wordi....wordj on a line
		//p[i][j] is the penalty from putting words with length Li, ..., Lj on a single line
		//notice that es[i, j] = es[i, j-1] - Lj - 1
		
		//fill es table
		int[][] es = new int[n+1][n+1]; // again we leave es[0][0] empty for simplicity so matches pseudo-code
		for (int i=1; i<=n; i++)
		{
			es[i][i] = M - lengths[i];
			for (int j=i+1; j<=n; j++)
			{
				es[i][j] = es[i][j-1] - lengths[j] - 1;
			}
		}
		
		//now fill p table
		int[][] p = new int[n+1][n+1];
		for (int i=1; i<=n; i++)
		{
			for(int j=1; j<=n; j++)
			{
				if (es[i][j] < 0) //doesnt fit
				{
					p[i][j] = Integer.MAX_VALUE; //infinity 
				}
				else if (j ==n) //fits and is last line so no penalty 
				{
					p[i][j] = 0; 
				}
				else //fits and is not last line
				{
					p[i][j] = (int) Math.pow((double)es[i][j], (double) 3);
				}
					
			}
		}
		
		return p; 
	}
	
	
}
