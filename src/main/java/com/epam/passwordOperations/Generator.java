package com.epam.passwordOperations;


public class Generator 
{
	public String generatePassword(PreferredPassword prefPass) 
	{
		boolean IncludeUpper=prefPass.isIncludeUpper();
		boolean IncludeLower=prefPass.isIncludeLower();
		boolean IncludeNum=prefPass.isIncludeNum();
		boolean IncludeSym=prefPass.isIncludeSym();
		int length=prefPass.getLength();
		Alphabet alphabet;
		
		alphabet=new Alphabet(IncludeUpper , IncludeLower, IncludeNum, IncludeSym);
		
		final StringBuilder pass = new StringBuilder();

		final int alphabetLength = alphabet.getAlphabet().length();
		
		int max = alphabetLength - 1; 
        int min = 0; 
        int range = max - min + 1; 
        
		
		
		for(int i=0;i<length;i++)
		{
			
			int index=(int)(Math.random() * range) + min; 
			
			pass.append(alphabet.getAlphabet().charAt(index));
		}
		
		return pass.toString();
		
	}
}
