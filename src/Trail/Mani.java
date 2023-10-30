package Trail;

public class Mani 
{
	public static void main(String[] args) 
	{
		String s = "abcabcdd";
		//Find the largest substring in the string --output abc
		
		char[] chary = s.toCharArray();
		char ch;
				
		for(int i=0; i<chary.length ;i++)
		{
			
			for(int j=i+1; j<chary.length ;j++)
			{
				if(chary[i]==chary[j])
				{
					if(chary[i]!='d')
					{
					System.out.print(chary[i]);
					}
				}
			}
			
		}
	}
}

