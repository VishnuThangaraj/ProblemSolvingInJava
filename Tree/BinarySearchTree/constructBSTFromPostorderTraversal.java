import java.util.*;

/*
You are given an array postOrder consisting of PostOrder Traversal of a valid Binary Search Tree.
Your task is to create a unique Binary Search Tree are return the head node of the tree.
A Binary Search Tree is a binary tree where for every node, any descendant of Node.
left has a value strictly less than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.

NOTE
You just need to complete the CreateTree function and return the head node of the tree generated.

Input Format
First line contains an integer n denoting the nodes in the BST.
Second line contains n space-seprated integers denoting PostOrder traversal of the tree

Output Format
For each node in preorder traveral of tree, print the a line in folowing format:
Node.left.val->Node.val<-Node.right.val
If, Any node is null print ..

Example 1
Input
6
1 7 5 50 40 10

Output
5->10<-40
1->5<-7
.->1<-.
.->7<-.
.->40<-50
.->50<-.

Explanation
The Binary Search Tree for Following input would be

         10
        /   \
       5     40
      / \     \
     1   7     50
Hence the output shows the child nodes for each node.

Example 2
Input
6
1 2 6 7 5 3

Output
2->3<-5
1->2<-.
.->1<-.
.->5<-7
6->7<-.
.->6<-.

Explanation
The Binary Search Tree for Following input would be

          3
        /   \
       2     5
      /       \
     1         7
              /
             6
Hence the output shows the child nodes for each node.

Constraints
0 <= n <= 10^9
-10^9 <= postOrder[i] <= 10^9
*/

public class Main {
    public static Scanner scn = new Scanner(System.in);

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }


    public static TreeNode CreateTree(int n,int[] postOrder) {
        if(postOrder.length == 0){
			return null; //bottom of the tree / empty tree
		}

		TreeNode root = new TreeNode(postOrder[postOrder.length-1]); //last element will be parent/root in preorder

		int index = postOrder.length-1;
		for(;index>=0; index--){
			if(postOrder[index] < postOrder[postOrder.length-1]){
				break;
			}
		}

		//child nodes
		root.left = CreateTree(n,Arrays.copyOfRange(postOrder,0,index+1));
		root.right = CreateTree(n,Arrays.copyOfRange(postOrder,index+1,postOrder.length-1));

		return root;
    }

    // input_section=================================================

    public static void display(TreeNode node) {
        if (node == null) return;
        StringBuilder sb = new StringBuilder();
        sb.append((node.left != null ? node.left.val : "."));
        sb.append("->" + node.val + "<-");
        sb.append((node.right != null ? node.right.val : "."));
        System.out.println(sb.toString());
        display(node.left);
        display(node.right);

    }

    public static void solve() {
        int n = scn.nextInt();
        int[] post = new int[n];
        for (int i = 0; i < n; i++)
            post[i] = scn.nextInt();

        TreeNode root = CreateTree(n,post);
        display(root);
    }

    public static void main(String[] args) {
        solve();
    }
}
