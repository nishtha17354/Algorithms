package algorithms.steiner;

import java.util.*;

public class Graph {
	int v;
	int[][] twoDarray;
	  ArrayList<ArrayList<Integer>> prims_newgraph = new ArrayList<ArrayList<Integer>>();
	
	public Graph(int v) 
	{
		this.v = v;
		twoDarray = new int[v][v];	
	}
	
	public void makeAnEdge(int source,int destination,int weight) 
	{
		twoDarray[source][destination]=weight;
		twoDarray[destination][source]=weight;
	}
	
	public int getEdge(int source,int destination) 
	{
		return 	twoDarray[source][destination];
	}
	public void printGraph(int v)
	{
		for(int i = 0; i < v; i++)
		{
			for(int j = 0; j < v; j++)
			{
				System.out.print(twoDarray[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
	public long dijkstra(int v,int src, int dest)
	{
		boolean visited[]=new boolean[v];

		long dist[]=new long[v];

		for(int i=0 ;i<v;i++) 

		{

			dist[i]=Integer.MAX_VALUE;

			visited[i]=false;

		}
		dist[src]=0;

		for(int i=0;i<v-1;i++)
	
		{
			int minV= minVertex(dist,visited,v);

			visited[minV]=true;

			for(int j=0;j<v;j++) 

			{
				if(  !visited[j] &&twoDarray[minV][j] !=0&& dist[minV]!=Integer.MAX_VALUE) 
				{
					long new_distance=dist[minV]+twoDarray[minV][j];

					if(new_distance<dist[j]) 
					{
						dist[j]=new_distance;
					}
				}
			}
		}

		return dist[dest];
	}

	
	public ArrayList<Integer> pathPrint(int v,int src, int dest)
	{
		int parent[] = new int[v]; 
		boolean visited[]=new boolean[v];

		long dist[]=new long[v];
		parent[src] = -1;
		// parent[] = -1;
		for(int i=0 ;i<v;i++) 
		{
			dist[i]=Integer.MAX_VALUE;
			visited[i]=false;
		}
		dist[src]=0;

		for(int i=1;i<v;i++)
		{
			int minV= minVertex(dist,visited,v);

			visited[minV]=true;

			for(int j=0;j<v;j++) 
			{
				if(  !visited[j] &&twoDarray[minV][j] !=0&& dist[minV]!=Integer.MAX_VALUE) 
				{
					long new_distance=dist[minV]+twoDarray[minV][j];

					if(new_distance<dist[j]) 
					{
						parent[j] = minV;
						dist[j]=new_distance;
					}
				}
			}
		}
		ArrayList<Integer> arraylist = new ArrayList<Integer>();
		int val = dest;

		// printPath1(dest,parent);
		// System.out.println("Asli");
		while(val != -1)
		{
			arraylist.add(val+1);
			val = parent[val];
		}
		Collections.reverse(arraylist);
		return arraylist;

		
	
	}
	
	public static int minVertex(long[] distance,boolean[] visited, int V)
	{
		int minimum_v=-1;
		 long min = Integer.MAX_VALUE;
		for(int i=0; i<V;i++) 
		{
			if(visited[i]==false && distance[i]<=min) 
				
			{
				min = distance[i]; 
				minimum_v=i;
			}
		}
		
		return minimum_v;
		
	}
	
	public static int findMin(long[] weight, boolean[] visited)
	{
		int temp = -1;
		for(int i = 0; i < weight.length; i++)
		{
			if(!visited[i] && (temp == -1 || weight[i] < weight[temp]))
				temp = i;
		}
		return temp;
	}
	
	public   ArrayList<ArrayList<Integer>>  Prims(int v) {
		boolean[] visited=new boolean[v];
		long[] weight= new long[v];
		int[] parent=new int[v];
		weight[0]=0;
		parent[0]=-1;
		for(int i=1;i<v;i++) 
		{
			weight[i]=Integer.MAX_VALUE;
		}
		for(int i=0;i<v;i++) 
		{
			int min_V=	findMin(weight,visited);
			visited[min_V]=true;
			for(int j=0;j<v;j++) 
			{
				if(twoDarray[min_V][j]!=0 && !visited[j]) 
				{
					if(twoDarray[min_V][j] < weight[j]) 
					{
					weight[j]=twoDarray[min_V][j];
					parent[j]=min_V;
					}
					
				}
			}
		}
		//printing
		 //ArrayList<Integer> terminal_prims = new ArrayList<Integer>(); 
		for(int i=1;i<v;i++) 
		{
			 
			if(weight[i]!= 2147483647) 
			{
			ArrayList<Integer> terminal_prims = new ArrayList<Integer>(); 
			int x=(int)weight[i];
			terminal_prims.add(parent[i]);
			terminal_prims.add(i);
			terminal_prims.add(x);
			prims_newgraph.add(terminal_prims);
			}	
			//prims_newgraph.add(terminal_prims);
		}
		return prims_newgraph;
//		for(int i=0;i<prims_newgraph.size();i++) {
//			System.out.println(prims_newgraph.get(i));
//		}
		
	}

	
//	public static void main (String[] args) 
//   { 
//     graph g = new graph(5);
//     g.makeAnEdge(0,1,4);
//	 g.makeAnEdge(0,2,8);
//     g.makeAnEdge(1,3,6);
//     g.makeAnEdge(1,2,2);
//	 g.makeAnEdge(2,3,3);
//	 g.makeAnEdge(2,4,9);
//	 g.makeAnEdge(3,4,4);
//     System.out.println(g.pathPrint(5,0,4));
//  } 
	   
	
}
