import java.util.Scanner;


public class prettyIter {
	
	//ITERATIVE, BOTTOM-UP

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
			int[][] n_a_tables = IterBottomUp(paras[i]); //n table is subproblems, a table is for reconstruction
			int n = paras[i]._n;
			int penalty = n_a_tables[0][n];
			System.out.println("penalty: " + penalty);
			prettyHelperFuncs.PrintReconstruction(n_a_tables[1], paras[i]._words, n);
			System.out.println();
		}
		
	}
	
	
	public static int[][] IterBottomUp(Paragraph para)
	{
		//p[i][j] is the penalty from putting words with length Li, ..., Lj on a single line
		//n[j] is the minimum total penalty for placing words with lengths L1, ...Lj on lines of width M.
		//a[j] stores an i value for each j and is used for reconstruction
		
		int[][] p = prettyHelperFuncs.ConstructPTable(para._M, para._n, para._lengths);
		
		//set my space for subproblems
		int[] n = new int[(para._n) + 1]; 
		int[] a = new int[(para._n) + 1]; 
		//base case
		n[0] = 0; 
		//now fill n table bottom-up
		for (int j=1; j<=para._n; j++)
		{
			n[j] = Integer.MAX_VALUE; //infinity 
			for (int i = 1; i<=j; i++)
			{
				if(p[i][j] != Integer.MAX_VALUE && n[i-1] != Integer.MAX_VALUE) //dont want to deal with overflow when compute w
				{
					int w = p[i][j] + n[i-1];
					if (w < n[j])
					{
						n[j] = w;
						a[j] = i; //store i for reconstruction 
					}
				}
				
			}
		}
		
		int[][] answer = {n, a};
		return answer; 
	}
	
			
	
	

}
