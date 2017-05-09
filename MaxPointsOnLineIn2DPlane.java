import java.util.*;

/**
 * Find maximum points that lie on a straight line in a 2D plane
 * @author nirav99
 *
 */
public class MaxPointsOnLineIn2DPlane
{
	
	private static int findMaxPoints(ArrayList<Point> pointList)
	{
		if(pointList == null || pointList.size() < 2)
			throw new IllegalArgumentException("Error: you must provide at least 2 points");
		
		int maxPoints = 0; // Max number of points that lie on the same line
		double slope = 0;
		int numPoints;
		
		HashSet<Point> currentList = null;
		HashSet<Point> maxList = new HashSet<Point>();
		
		for(int i = 0; i < pointList.size() - 1; i++)
		{
			
			for(int j = i + 1; j < pointList.size(); j++)
			{
				slope = calculateSlope(pointList.get(i), pointList.get(j));
				numPoints = 2;
				
				if(currentList != null && currentList.size() > maxList.size())
					maxList = currentList;
				
				currentList = new HashSet<Point>();
				currentList.add(pointList.get(i));
				currentList.add(pointList.get(j));
				
				
				for(int k = 0; k < pointList.size(); k++)
				{
					if(k == i || k == j)
						continue;
					
				  double newSlope = calculateSlope(pointList.get(i), pointList.get(k));
				
				  if(newSlope == slope)
				  {
					  numPoints++;
					  currentList.add(pointList.get(k));
				  }
				  if(numPoints > maxPoints)
				  {
					  maxPoints = numPoints;
				  }
				}
			}
		}
		
		System.out.println("Max points on the same line : " + maxPoints);
		for(Point p : maxList)
			System.out.print(p + " ");
		System.out.println();
		return maxPoints;
	}
	
	private static double calculateSlope(Point p1, Point p2)
	{
	  int denominator = p1.X - p2.X;
	  
	  int numerator = p1.Y - p2.Y;
	  
	  return (denominator != 0) ? 1.0 * numerator / denominator : Double.MAX_VALUE;
	}
	
  public static void main(String[] args)
  {
  	try
  	{
  		ArrayList<Point> pointList = new ArrayList<Point>();
  		
  		
  		pointList.add(new Point(7,2));
  		pointList.add(new Point(1,0));
  		pointList.add(new Point(8,4));
  		pointList.add(new Point(3,0));
  		pointList.add(new Point(5,0));
  		pointList.add(new Point(8,5));
  		pointList.add(new Point(10,0));
  		pointList.add(new Point(9,5));
  		pointList.add(new Point(4,0));
  		pointList.add(new Point(10,6));
  		
  		
  		pointList.add(new Point(-1, 1));
  		pointList.add(new Point(0, 0));
  		pointList.add(new Point(1, 1));
  		pointList.add(new Point(2, 2));
  		pointList.add(new Point(3, 3));
  		pointList.add(new Point(3, 4));
  		
  		System.out.println(findMaxPoints(pointList));
  	}
  	catch(Exception e)
  	{
  		e.printStackTrace();
  	}
  }
}

class Point
{
	int X;
	int Y;
	
	Point(int x, int y)
	{
		this.X = x;
		this.Y = y;
	}
	
	@Override
	public String toString()
	{
		return "(" + this.X + "," + this.Y + ")";
	}
}
