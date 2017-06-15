import java.util.*;

/**
 * Given a list of pairs of cities and the cost to travel between them,
 * Compute the path with minimum cost of travel from given source city to given destination city
 * (done using Dijkstra's algorithm)
 * @author nirav99
 *
 */
public class MinCostPath
{
  private HashMap<String, HashSet<Node>> adjacencyList;
  
  public MinCostPath()
  {
  	adjacencyList = new HashMap<String, HashSet<Node>>();
  }
  
  public void addEdges(String s1, String s2, int cost)
  {
  	Node n1 = new Node(s1);
  	Node n2 = new Node(s2);
  	n2.weight = cost;
    n1.weight = cost;
    
    HashSet<Node> valSet1 = adjacencyList.get(s1);
    HashSet<Node> valSet2 = adjacencyList.get(s2);
    
    if(valSet1 == null)
    	valSet1 = new HashSet<Node>();
    if(valSet2 == null)
    	valSet2 = new HashSet<Node>();
    
    valSet1.add(n2);
    valSet2.add(n1);
    	
    adjacencyList.put(s1, valSet1);
    adjacencyList.put(s2, valSet2);
  }
  
  public void printGraph()
  {
  	for(Map.Entry<String, HashSet<Node>> entry : adjacencyList.entrySet())
  	{
  		System.out.println("Node : " + entry.getKey());
  		System.out.println("Neighbors : ");
  		
  		for(Node value : entry.getValue())
  		{
  			System.out.println(value);
  		}
  		System.out.println("=========");
  	}
  }

  /**
   * Implements Dijkstra's algorithm to find the shortest path
   * @param start
   * @param end
   */
  public void findShortestPath(String start, String end)
  {
  	HashSet<String> visitedNodes = new HashSet<String>();
  	HashMap<String, String> pred = new HashMap<String, String>();
  	
  	HashMap<String, Integer> distMap = new HashMap<String, Integer>();
  	
  	for(String s : adjacencyList.keySet())
  	{
  		distMap.put(s, Integer.MAX_VALUE);
  	}
  	
  	distMap.put(start, 0);
  	
  	while(true)
  	{
  		String v = getMinimumCostNodeNotVisited(distMap, visitedNodes);
  		
  		if(v == null)
  			break;
  		
  		visitedNodes.add(v);
  		
  		HashSet<Node> neighbors = adjacencyList.get(v);
  		
  		for(Node w : neighbors)
  		{
  			if(!visitedNodes.contains(w.name))
  			{
  			  int originalDist = distMap.get(w.name);
  			  int newDist = distMap.get(v) + cost(v, w.name);
  			
  			  if(newDist < originalDist)
  			  {
  				  distMap.put(w.name, newDist);
  				  pred.put(w.name, v);
  			  }
  			}
  		}
  	}
  	printShortestPath(start, end, distMap, pred);
  }

  private void printShortestPath(String start, String end, HashMap<String, Integer> distMap, HashMap<String, String> predMap)
  {
  	System.out.println("Min cost between : " + start + ", " + end + " : " + distMap.get(end));
  	System.out.println("The path : ");
  	
  	String trav = end;
  	
  	LinkedList<String> path = new LinkedList<String>();
  	path.add(trav);
  	boolean foundPath = false;
  	
  	while(true)
  	{
  		String prev = predMap.get(trav);
  		
  		if(prev == null)
  			break;
  		path.add(0,  prev);
  		trav = prev;
  		
  		if(trav.equals(start))
  		{
  			foundPath = true;
  			break;
  		}
  	}
  	
  	if(!foundPath)
  	{
  		System.out.println("No path found");
  	}
  	else
  	{
  	  for(int i = 0; i < path.size(); i++)
  		  System.out.print(path.get(i) + " ");
  	}
  	System.out.println();
  }
  
  private String getMinimumCostNodeNotVisited(HashMap<String, Integer> distMap, HashSet<String> visitedNodes)
  {
  	int minValue = Integer.MAX_VALUE;
  	String minNode = null;
  	
  	for(Map.Entry<String, Integer> entry : distMap.entrySet())
  	{
  		if(minValue > entry.getValue() && !visitedNodes.contains(entry.getKey()))
  		{
  			minValue = entry.getValue();
  			minNode = entry.getKey();
  		}
  	}
  	
  	return minNode;
  }
  
  private int cost(String n1, String n2)
  {
    HashSet<Node> neighbors = adjacencyList.get(n1);
    
    Iterator<Node> iter = neighbors.iterator();
    Node temp;
    
    while(iter.hasNext())
    {
    	temp = iter.next();
    	
      if(temp.name.equals(n2))
      	return temp.weight;
    }
    
    return Integer.MAX_VALUE;
  }
  
  public static void main(String[] args)
  {
  	try
  	{
  		MinCostPath graph = new MinCostPath();
  		
  		graph.addEdges("AUS", "LHR", 1500);
  		graph.addEdges("AUS", "JFK", 300);
  		graph.addEdges("JFK", "LHR", 700);
  		graph.addEdges("AUS", "ORD", 300);
  		graph.addEdges("ORD", "LHR", 900);
  		graph.addEdges("ORD", "JFK", 200);
  		graph.addEdges("AUS", "IAH", 200);
  		graph.addEdges("IAH", "LHR", 1100);
  		graph.addEdges("IAH", "FRA", 1000);
  		graph.addEdges("IAH", "CDG", 1000);
  		graph.addEdges("IAH", "AMS", 900);
  		graph.addEdges("JFK", "FRA", 800);
  		graph.addEdges("JFK", "CDG", 800);
  		graph.addEdges("JFK", "AMS", 750);
  		graph.addEdges("ORD", "FRA", 750);
  		graph.addEdges("ORD", "IAH", 100);
  		graph.addEdges("LHR", "HAM", 200);
  		graph.addEdges("FRA", "HAM", 50);
  		graph.addEdges("HAM", "MUN", 80);
  		graph.addEdges("FRA", "MUN", 50);
  		graph.addEdges("IAH", "MUN", 1600);
  		graph.addEdges("JFK", "MUN", 850);
  		graph.printGraph();
  		
  		graph.findShortestPath("AUS", "MUN");
  	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  	}
  }
}

class Node
{
	String name;
	int weight;
	
	Node(String name)
	{
		this.name = name;
		weight = 0;
	}
	
	@Override
	public String toString()
	{
		return this.name + " : " + this.weight;
	}
	
	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object n2)
	{
		return (n2 instanceof Node) && this.name.equals(((Node) n2).name);
	}
}

