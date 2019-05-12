package algorithms.steiner;


import java.util.ArrayList; 
public class Steiner {
	
	static ArrayList<ArrayList<Integer>> wtArr = new ArrayList<ArrayList<Integer>>();
	public void getWt(int s, int e, int wt)
	{
		ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.add(s);
		ar.add(e);
		ar.add(wt);
		wtArr.add(ar);
	}
	
	public int findWt(int s, int e)
	{
		for(int i = 0; i < wtArr.size(); i++)
		{
			if(wtArr.get(i).get(0) == s && wtArr.get(i).get(1) == e)
				return wtArr.get(i).get(2);
			
			else if(wtArr.get(i).get(1) == s && wtArr.get(i).get(0) == e)
				return wtArr.get(i).get(2);
				
		}
		return 0;
	}

}
