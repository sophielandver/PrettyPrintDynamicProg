public class Paragraph {
	
	public int _M;//max number of characters per line
	public int _n; //number of words
	public String[] _words; //list of words
	public int[] _lengths; //list of word lengths
	
	/*
	 * Constructor for Paragraph class
	 */
	public Paragraph(String[] words, int M)
	{
		_M = M;
		_n = words.length;
		//shift every word in words list over 1 index to make things simpler for later
		String[] shifted_words = new String[(words.length + 1)];
		for (int i=1; i<(shifted_words.length); i++)
		{
			shifted_words[i] = words[i-1];
		}
		_words = shifted_words;
		
		int[] lengths = new int[(words.length + 1)];
		for (int i=1; i<(shifted_words.length); i++)
		{
			lengths[i] = _words[i].length();
		}
		_lengths = lengths;
		

	}

}
