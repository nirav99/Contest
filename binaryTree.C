#include <iostream>
using namespace std;

#include <stdlib.h>

typedef struct nodeType
{
  int data;
  nodeType* left;
  nodeType* right;
}node;

class BinaryTree
{
  public:
    BinaryTree()
    {
      root = NULL;
    }

    void addData(int data);
    void inOrder();
    void postOrder();
    void preOrder();
    ~BinaryTree();
  private:
    node* root;
    void deleteTree(node *r);
    void inOrderHelper(node *r);
    void postOrderHelper(node *r);
    void preOrderHelper(node *r);
};

void BinaryTree::inOrder()
{
  inOrderHelper(root);
}

BinaryTree::~BinaryTree()
{
  cout << "Deleting binary tree" << endl;
  deleteTree(root);
  cout << "Tree deleted" << endl;
}

void BinaryTree::deleteTree(node *trav)
{
  if(trav)
  {
    deleteTree(trav->left);
    deleteTree(trav->right);
    delete trav;
 }
}

void BinaryTree::inOrderHelper(node *trav)
{
  if(trav)
  {
    inOrderHelper(trav->left);
    cout << trav->data << " ";
    inOrderHelper(trav->right);
  }
}

void BinaryTree::postOrder()
{
  postOrderHelper(root);
}

void BinaryTree::postOrderHelper(node *trav)
{
  if(trav)
  {
    postOrderHelper(trav->left);
    postOrderHelper(trav->right);
    cout << trav->data << " ";
  }
}

void BinaryTree::preOrder()
{
  preOrderHelper(root);
}

void BinaryTree::preOrderHelper(node *trav)
{
  if(trav)
  {
    cout << trav->data << " ";
    preOrderHelper(trav->left);
    preOrderHelper(trav->right);
  }
}

void BinaryTree::addData(int data)
{
  node *newNode = new node;
  node *trav = NULL;

  if(!newNode)
  {
    cerr << "Error in allocating memory. Exiting";
    exit(-1);
  }
  newNode->data = data;
  newNode->left = NULL;
  newNode->right = NULL;

  if(!root)
  {
    root = newNode;
  }
  else
  {
    trav = root;

    while(1)
    {
      if(trav->data > data)
      {
        if(!trav->left)
        {
          trav->left = newNode;
          break;
        }
        else
          trav = trav->left;
      }
      else
      {
        if(!trav->right)
        {
          trav->right = newNode;
          break;
        }
        else
          trav = trav->right;
      }
    }
  } 
}

int main(int argc, char* argv[])
{
  int input[argc -1];
  int temp;
  BinaryTree t;

  for(int i = 1; i < argc; i++)
  {
    input[i -1] = atoi(argv[i]);    
    cout << "Adding to tree : " << input[i-1] << endl;
    t.addData(input[i -1]);
  }

  cout << endl << "Printing tree inorder" << endl;
  t.inOrder();

  cout << endl << "Printing tree preorder" << endl;
  t.preOrder();

  cout << endl << "Printing tree postorder" << endl;
  t.postOrder();

  cout << endl;
  return 0;
}
