import java.util.Scanner;


public class prettyMemo {
	
	//TOP-DOWN, MEMOIZED 

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str_num_paras = input.nextLine(); 
		int num_paras = Integer.parseInt(str_num_paras.trim());
		Paragraph[] paras = new Paragraph[num_paras];
		
		//collect each paragraph
		for (int i=0; i<num_paras; i++)
		{
			String str_M = input.nextLine(); //get M
			int M = Integer.parseInt(str_M.trim());
			String words = input.nextLine(); //get words
			String[] words_ls = words.trim().split(" "); //now have list of words
			paras[i] = new Paragraph(words_ls, M);
		}
	
		input.close();
		
	
		for (int i=0; i<num_paras; i++)
		{
			int[][] n_a_tables = TopDownMemoized(paras[i]); //n table is subproblems, a table is for reconstruction
			int n = paras[i]._n;
			int penalty = n_a_tables[0][n];
			System.out.println("penalty: " + penalty);
			prettyHelperFuncs.PrintReconstruction(n_a_tables[1], paras[i]._words, n);
			System.out.println();
		}
		
	}
	
	public static int[][] TopDownMemoized(Paragraph para)
	{
		//p[i][j] is the penalty from putting words with length Li, ..., Lj on a single line
		//n[j] is the minimum total penalty for placing words with lengths L1, ...Lj on lines of width M.
		//a[j] stores an i value for each j and is used for reconstruction
		
		int[][] p = prettyHelperFuncs.ConstructPTable(para._M, para._n, para._lengths);
		int[] n = new int[(para._n) + 1]; 
		int[] a = new int[(para._n) + 1]; 
		for (int i=1; i<=para._n; i++)
		{
			n[i] = -1; //mark as un-computed 
		}
		RecurTopDownMemoized(n, a, p, para._n); //this will fill the n and a tables. 
		//now have updated, filled, n and a tables so
		int[][] answer = {n, a};
		return answer; 
		
	}
	
	public static int RecurTopDownMemoized(int[] n, int[] a, int[][] p, int j)
	{
		//if put something here already
		if (n[j] >= 0)
		{
			return n[j];
		}
		int q;
		//if base case
		if (j == 0)
		{
			q = 0;
		}
		//compute it recursively 
		else
		{
			q = Integer.MAX_VALUE; //infinity 
			for (int i=1; i<=j; i++)
			{
				int x = RecurTopDownMemoized(n, a, p, i-1);
				if(x != Integer.MAX_VALUE && p[i][j] != Integer.MAX_VALUE)//don't want overflow
				{
					int w = p[i][j] + x;
					if (w < q)
					{
						q = w;
						a[j] = i;
					}
				}
			}
			
		}
		
		n[j] = q;
		return q; 
		
		
	}
	


}
