package algorithms.steiner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		Steiner steiner = new Steiner();

		File file = new File("/Users/nishthaahuja/Desktop/Algorithms/resources/P4Z/p410.stp"); 
		Scanner sc = new Scanner(file);
				
		  int ctr=0;
		  int node=0;
		  int edge=0;
		  int s=0;
		  int d=0;
		  int flag = 0;
		  int w=0;
		  int ctr2 = 0;
		  int NoOfTerminals=0;
		  ArrayList<ArrayList<Integer>> pairsArray = new ArrayList<ArrayList<Integer>>();
		  int[] terminal_nodes=new int[1000];
		  Graph g = null;
		    while (sc.hasNextLine())
		    {
		    		if(ctr==9)
		    		{
		    			String[] arr=sc.nextLine().split(" ");
		    			node=Integer.parseInt(arr[1]);
		    			System.out.println("Node " + node);
		    		
		    		}
		
		    		else if(ctr==10)
		     	{
		     		String[] arr1=sc.nextLine().split(" ");
		     		edge=Integer.parseInt(arr1[1]);
		     		System.out.println("Edges " + edge);	
		     		g = new Graph(node);
		     	}
		    		
		    		else if(ctr>=11 && flag == 0) 
		     		{
		     		String temp = sc.nextLine();
		     		if(temp.equals("END")) 
		     		{
		     			flag = 1;
		     			continue;
		     		}
		     		else 
		     		{
		     			 String[] arrValues=temp.split(" ");
		     			 s=Integer.parseInt(arrValues[1]);
		     			 d=Integer.parseInt(arrValues[2]);
		     			 w=Integer.parseInt(arrValues[3]);
		     			 steiner.getWt(s,d,w);
		     			 g.makeAnEdge(s-1,d-1,w);

		     		}
		     	}
		    		else if(ctr==10+edge+3) 
		    		{

		    			 String[] terminal=sc.nextLine().split(" ");
		    			 NoOfTerminals=Integer.parseInt(terminal[1]);
		    			 
		    		}
		    		
		    		else if(ctr>10+edge+3) 
		    		{
		    			String temp=sc.nextLine();
		    			
		    			if(temp.equals("END")) 
		    			{
		    				break;
		    			}
		    			else
		    			{
			    			String[] arr=temp.split(" ");
		    				terminal_nodes[ctr2++]= Integer.parseInt(arr[1]);
		    			}
		    		
		    		}
		    		
		     	else
		     	{
		     		sc.nextLine();
		     	}
		     	ctr++;
		    }
		   System.out.println();
		    for(int i=0;i<NoOfTerminals;i++) 
			{
				for(int j=i+1;j<NoOfTerminals;j++) 
				{
					 ArrayList<Integer> pairs = new ArrayList<Integer>(); 
					 pairs.add(terminal_nodes[i]);
					 pairs.add(terminal_nodes[j]);
					 pairsArray.add(pairs);
			
				}
			}
		    
		    
		    Graph g_new=new Graph(node);
		    for(int i=0;i<pairsArray.size();i++) 
		    {
		    	long val=g.dijkstra(node,pairsArray.get(i).get(0)-1, pairsArray.get(i).get(1)-1);
		    	int value=(int)val;
		    	g_new.makeAnEdge(pairsArray.get(i).get(0)-1, pairsArray.get(i).get(1)-1, value);
		    }
		    // g_new.printGraph(node);
		    
		     ArrayList<ArrayList<Integer>> abc = new ArrayList<ArrayList<Integer>>();
		     ArrayList<Integer> originalPath=new ArrayList<Integer>();
		     abc= g_new.Prims(node);
		     for(int i=0;i<abc.size();i++) 
		     {	
		    	 	originalPath=g.pathPrint(node,abc.get(i).get(0),abc.get(i).get(1));
		    	 	for(int k = 0 ; k < originalPath.size()-1; k++)
		    	 	{
		    	 		System.out.print("E " + originalPath.get(k)+ " "+ originalPath.get(k+1)+ " ");
		    	 		System.out.println(steiner.findWt(originalPath.get(k),originalPath.get(k+1)));
		    	 	}

		     }
		     System.out.println("END");
		     System.out.println("SECTION Terminals");
		     for(int i = 0; i < NoOfTerminals; i++)
		     {
		    	 	System.out.println("T "+terminal_nodes[i]);
		     }
		     System.out.println("END");
		     //total cost
		     int total_cost=0;
		     for(int i=0;i<abc.size();i++) 
		     {
		    	 	total_cost=total_cost+abc.get(i).get(2);
		     }
		     System.out.println("SECTION Cost");
		     System.out.println(total_cost);
		     System.out.println("END");
		     	  
	}	

}
