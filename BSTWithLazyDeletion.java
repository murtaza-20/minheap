import java.util.Scanner;

public class BSTWithLazyDeletion {
	public static void main (String[] args) {
		TreeNodeMain trm = new TreeNodeMain();
		TreeNode root = null;
        Scanner scan = new Scanner(System.in);		

		do {
			System.out.println ("1. Insert");
			System.out.println ("2. Delete");
			System.out.println ("3. Find Max");
			System.out.println ("4. Find Min");
			System.out.println ("5. Contains");
			System.out.println ("6. In order Tree Traversal");
			System.out.println ("7. Height");
			System.out.println ("8. No. Of Nodes");
			System.out.println ("9. Exit");
			System.out.print ("Enter your choice : ");
			int ch = scan.nextInt ();
			switch (ch) {
				case 1:
					System.out.print ("Please enter element (integer value) : ");
					int element = scan.nextInt();

					System.out.println ("Your choice : 1");
					System.out.println ("Element : " + element);
					//bst.insert(new RBTreeNode(element));
					root = trm.insert (root, element);
					System.out.println ("Element inserted");
					break;
				case 2:
					System.out.print ("Please enter element (integer value) to delete in the tree : ");
					element = scan.nextInt();

					System.out.println ("Your choice : 2");
					System.out.println ("Element : " + element);
					boolean res = trm.delete (root, element);
					if (res) 
						System.out.println ("Result : Lazy deleted successfully.") ;
					else
						System.out.println ("Result : Could not delete. Some problem.");
					break;
				case 3:
					System.out.println ("Your choice : 3");
					TreeNode temp = trm.findMax (root);
					System.out.println ("Max : " + temp.getKey());
					break;
				case 4:
					System.out.println ("Your choice : 4");
					temp = trm.findMin (root);
					System.out.println ("Min : " + temp.getKey());
					break;
				case 5:
					System.out.print ("Please enter element (integer value) to search in the tree : ");
					element = scan.nextInt();

					System.out.println ("Your choice : 5");
					System.out.println ("Element : " + element);
					res = trm.contains (root, element);
					if (res) 
						System.out.println ("Result : Yes, tree contains the element " + element) ;
					else
						System.out.println ("Result : Could find the element");
					break;
				case 6:
					System.out.println ("Your choice : 6");
					trm.inorder(root);
					System.out.println();
					break;
				case 7:
					System.out.println ("Your choice : 7");
					System.out.println ("Height of the tree : " + trm.height(root));
					break;
				case 8:
					System.out.println ("Your choice : 8");
					int nodeCnt = trm.countNodes(root);
					int lazyCnt = root.getDeleteCount();
					System.out.println ("No. of Nodes : " + nodeCnt);
					System.out.println ("No. of deleted Nodes : " + lazyCnt);
					System.out.println ("No. of total Nodes : " + (nodeCnt));
					break;
				case 9:
					System.exit(0);
					break;
			}
		} while (true);


	}

	private static class TreeNode {
		int key;
		boolean isDeleted;
		TreeNode leftChild;
		TreeNode rightChild;
		static int deleteCount;
		
		public TreeNode () {
			key = 0;
			isDeleted = false;
			leftChild = null;
			rightChild = null;
			deleteCount = 0;
		}

		public TreeNode (int key) {
			this.key = key;
			isDeleted = false;
			leftChild = null;
			rightChild = null;
			deleteCount = 0;
		}

		public void setKey (int key) {
			this.key = key;
		}

		public int getKey () {
			return this.key;
		}

		public boolean isDeleted () {
			return this.isDeleted;
		}

		public void setDeleted (boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public void setLeftChild (TreeNode leftChild) {
			this.leftChild = leftChild;
		}

		public TreeNode getLeftChild () {
			return this.leftChild;
		}

		public void setRightChild (TreeNode rightChild) {
			this.rightChild = rightChild;
		}

		public TreeNode getRightChild () {
			return this.rightChild;
		}

		public void incrementDelete () {
			this.deleteCount++;
		}

		public void decrementDelete () {
			if (deleteCount > 0)
				this.deleteCount--;
		}

		public int getDeleteCount () {
			return this.deleteCount;
		}
	}

	private static class TreeNodeMain {

		int cnt = 0;

		public TreeNode insertNode(TreeNode root, int key) {
			TreeNode newNode = new TreeNode (key);
		    if (root == null) {
		        return newNode;
		    } else if (newNode.getKey() > root.getKey()) {
		        root.setRightChild (insertNode(root.getRightChild(), key));
		    } else if (newNode.getKey() < root.getKey()) {
		        root.setLeftChild (insertNode(root.getLeftChild(), key));
		    }
		    return root;
		}

		public TreeNode insert (TreeNode root, int key) {
			if (root == null) {
				//Check if the tree is empty. Make a new root node.
				root = new TreeNode (key);

				//Return the newly created root.
				return root;
			} else {
				TreeNode curr = root;
				TreeNode temp = null;
				TreeNode newNode = new TreeNode (key);
				boolean exists = false;

				while (curr != null) {
					temp = curr;
					if (newNode.getKey() == curr.getKey()) {
						//If the given key already exists, exit the loop.
						exists = true;
						break;
					} else if (newNode.getKey() < curr.getKey()) {
						//If the given key is less than the current key, move to the left of current node.
						curr = curr.getLeftChild();
					} else {
						//If the given key is greater than the current key, move to the right of current node.
						curr = curr.getRightChild();
					}
				}

				if (!exists) {
					//If the given key does not exists in the tree, check whether it is less than last node in the tree.
					if (newNode.getKey() < temp.getKey()) {
						//If yes, make the new node as the left child of the current node (last node).
						temp.setLeftChild (newNode);
					} else {
						//If no, make the new node as the right child of the current node (last node).
						temp.setRightChild (newNode);
					}
					//Return the root as it is.
					return root;
				} else {
					//If the given key already exists in the tree, check whether it is marked as deleted.
					if (curr.isDeleted()) {
						//If yes, mark it as not deleted.
						curr.setDeleted (false);
						curr.decrementDelete();
					}
					//Return the root as it is.
					return root;
				}
			}
		}

		public void inorder (TreeNode root) {
			if (root != null) {
				inorder(root.getLeftChild());
				if (root.isDeleted()) 
					System.out.print ("*"+root.getKey() + " ");
				else
					System.out.print (root.getKey() + " ");
				inorder(root.getRightChild());
			}
		}

		public boolean delete (TreeNode root, int key) {
			TreeNode x = root;
			while (x != null) {
				if (key == x.getKey()) {
					x.setDeleted(true);
					x.incrementDelete();
					return true;
				} else if (key < x.getKey()) {
					x = x.getLeftChild();
				} else {
					x = x.getRightChild();
				}
			}
			return false;
		}

		public boolean contains (TreeNode root, int key) {
			TreeNode x = root;
			while (x != null) {
				if (key == x.getKey()) {
					return !x.isDeleted();
				} else if (key < x.getKey()) {
					x = x.getLeftChild();
				} else {
					x = x.getRightChild();
				}
			}
			return false;	
		}

		public int height(TreeNode root){
		    if(root == null || (root.getLeftChild() == null && root.getRightChild() == null)) {
		    	return 0;
		    } else {
		    	return Math.max(height(root.getLeftChild()), height(root.getRightChild())) + 1;
		    }
		}

		public TreeNode findMin(TreeNode root) {
			if (root == null) 
				return  null;
			TreeNode tmp = findMin (root.getLeftChild());
			if (tmp != null) 
				return tmp;
			if (!root.isDeleted()) 
				return root;
			return findMin(root.getRightChild());
		}

		public TreeNode findMax(TreeNode root) {
			if (root == null) 
				return  null;
			TreeNode tmp = findMax (root.getRightChild());
			if (tmp != null) 
				return tmp;
			if (!root.isDeleted()) 
				return root;
			return findMax(root.getLeftChild());
		}

		public int countNodes(TreeNode root) {
		    if(root == null)
		        return 0;

		    int left = getLeftHeight(root)+1;    
		    int right = getRightHeight(root)+1;
		 
		    if(left==right){
		        return (2<<(left-1))-1;
		    } else {
		        return countNodes(root.getLeftChild())+countNodes(root.getRightChild())+1;
		    }
		}

		public int getLeftHeight(TreeNode n) {
		    if(n == null) 
		    	return 0;
		 
		    int height = 0;
		    while (n.getLeftChild() != null){
		        height++;
		        n = n.getLeftChild();
		    }
		    return height;
		}

		public int getRightHeight(TreeNode n){
		    if(n == null) 
		    	return 0;
		 
		    int height = 0;
		    while(n.getRightChild() != null){
		        height++;
		        n = n.getRightChild();
		    }
		    return height;
		}
	}
}