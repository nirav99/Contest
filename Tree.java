import java.util.Random;

/**
 * Class to find least common ancestor for a binary tree, i.e. the tree without
 * binary search property. For simplicity, it is assumed that each element in
 * the
 * tree is unique.
 * @author nirav99
 *
 */
public class Tree
{
  /**
   * Tree node
   * @author nirav99
   *
   */
  class node
  {
    private int data;
    node left;
    node right;
    
    node(int data)
    {
      this.data = data;
      left = null;
      right = null;
    }
  }
  
  private node root;
  private int size;
  private boolean found = false;
  private boolean continueRecursion = true;
  
  /**
   * Class constructor
   */
  public Tree()
  {
    root = null;
    size = 0;
  }
  
  /**
   * Method to build a binary tree. For simplicity, it builds a binary search
   * tree. However, the special property of a binary search tree is not used to
   * find the least common ancestor.
   * @param data
   */
  public void addNode(int data)
  {
    node newNode = new node(data);
    size++;
    
    if(root == null)
    {
      root = newNode;
      return;
    }
    node trav = root;
    
    while(true)
    {
      if(trav.data > data)
      {
        if(trav.left == null)
        {
          trav.left = newNode;
          break;
        }
        else
        {
          trav = trav.left;
        }
      }
      else
      {
        if(trav.right == null)
        {
          trav.right = newNode;
          break;
        }
        else
        {
          trav = trav.right;
        }
      }
    }
  }
  
  /**
   * Print the tree in-order
   */
  public void printTree()
  {
    System.out.println("The tree is : ");
    printTreeHelper(root);
    System.out.println();
  }
  
  /**
   * Recursive helper method for printTree
   * @param t
   */
  private void printTreeHelper(node t)
  {
    if(t != null)
    {
      printTreeHelper(t.left);
      System.out.print(t.data + " ");
      printTreeHelper(t.right);
    }
  }
  
  /**
   * Given a data element, find the path from root to that element
   * @param data
   * @return
   */
  private int[] findPath(int data)
  {
    int path[] = new int[size];
    found = false;
    continueRecursion = true;
    search(data, path, 0, root);
    
    if(true == found)
    {
      return path;
    }
    else
    {
      return null;
    }
  }
  
  /**
   * Method to find the least common ancestor between two tree nodes.
   * If at least one node does not exist, it says "No common ancestor".
   * @param data1
   * @param data2
   */
  public void findCommonAncestor(int data1, int data2)
  {
    int path1[] = findPath(data1);
    int path2[] = findPath(data2);
    
    if(path1 == null || path2 == null)
    {
      System.out.println("No common ancestor, at least one value does not exist");
      return;
    }
    
    for(int i = 0; i < path1.length && i < path2.length ; i++)
    {
      if(path1[i] == path2[i])
      {
        if((i < path1.length -1 && i < path2.length -1 && path1[i + 1] != path2[i + 1]) ||
           (i >= path1.length -1 || i == path2.length -1))
        {
          System.out.println("Minimum common ancestor of " + data1 + " and " + data2 + " is "+ path1[i]);
          break;
        }
      }
    }
  }
  
  /**
   * Recursive method to find the path to the node to search for from the root
   * @param data
   * @param path
   * @param level
   * @param start
   */
  private void search(int data, int path[], int level, node start)
  {
    if(continueRecursion == false)
      return;
    if(start != null)
    {
      path[level] = start.data;
      if(start.data == data)
      {
        found = true;
        continueRecursion = false;
       }
      else
      {
        search(data, path, level + 1, start.left);
        search(data, path, level + 1, start.right);
      }
    }
  }
  
  public static void main(String args[])
  {
    Tree t = new Tree();
    
    for(int i = 0; i < args.length; i++)
    {
      t.addNode(Integer.parseInt(args[i]));
    }
    
    t.printTree();
    
    Random r = new Random();
    
    int data1 = Integer.parseInt(args[r.nextInt(args.length)]);
    int data2 = Integer.parseInt(args[r.nextInt(args.length)]);
    
    if(data1 == data2)
    {
      System.out.println("Least common ancestor of " + data1 + " and " + data2 + " is " + data1);
    }
    else
    {
      System.out.println("Least common ancestor of " + data1 + " and " + data2);
      t.findCommonAncestor(data1, data2);
    }
  }
}
