// { Driver Code Starts
//Initial Template for Java

import java.util.LinkedList; 
import java.util.Queue; 
import java.io.*;
import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}

class Main {
    
    static Node buildTree(String str){
        
        if(str.length()==0 || str.charAt(0)=='N'){
            return null;
        }
        
        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue
        
        Queue<Node> queue = new LinkedList<>(); 
        
        queue.add(root);
        // Starting from the second element
        
        int i = 1;
        while(queue.size()>0 && i < ip.length) {
            
            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();
                
            // Get the current node's value from the string
            String currVal = ip[i];
                
            // If the left child is not null
            if(!currVal.equals("N")) {
                    
                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }
                
            // For the right child
            i++;
            if(i >= ip.length)
                break;
                
            currVal = ip[i];
                
            // If the right child is not null
            if(!currVal.equals("N")) {
                    
                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));
                    
                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }
        
        return root;
    }
    static void printInorder(Node root)
    {
        if(root == null)
            return;
            
        printInorder(root.left);
        System.out.print(root.data+" ");
        
        printInorder(root.right);
    }
    
	public static void main (String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        int t=Integer.parseInt(br.readLine());
    
	        while(t > 0){
	            String s = br.readLine();
    	    	Node root = buildTree(s);
        	    Tree g = new Tree();
			    Node fresh = g.RemoveHalfNodes(root);
			    printInorder(fresh);
			    System.out.println();
                t--;
            
        }
    }
  
}

// } Driver Code Ends


//User function Template for Java


/*

class Node{
    int data;
    Node left,right;
    Node(int d){
        data=d;
        left=right=null;
    }
}

*/

class Tree
{
    // Return the root of the modified tree after removing all the half nodes.
    static void remove(Node node){
        if(node==null)
            return;
        if(node.left==null && node.right==null){
            return;
        }
        else if(node.left==null || node.right==null){
            Node n;
            while( (node.left==null || node.right==null) ){
				if((node.left==null && node.right==null)){
					break;
				}
                //System.out.println(node.data);
                if(node.left==null){
                   // System.out.println(node.right.data);
                    node.data=node.right.data;
                    node.left=node.right.left;
                    node.right=node.right.right;
                }
                else if(node.right==null){
                    //System.out.println("Right null "+node.left.data);
                    node.data=node.left.data;
					node.right=node.left.right;
                    node.left=node.left.left; 
                }
            }
        }
		System.out.println("Node is "+node.data);
        remove(node.left);
        remove(node.right);
    }
    public static Node RemoveHalfNodes(Node root)
    {
        // Code Here
        remove(root);
        return root;
        
    }
}
