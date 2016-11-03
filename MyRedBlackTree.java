import java.util.Scanner;

public class MyRedBlackTree < T extends Comparable < T >> {

    private RBTreeNode < T > nil = new RBTreeNode < T > ();
    private RBTreeNode < T > root = nil;

    public MyRedBlackTree() {
        root.left = nil;
        root.right = nil;
        root.parent = nil;
    }

    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        MyRedBlackTree bst = new MyRedBlackTree();
		do {
			System.out.println ("1. Insert");
			System.out.println ("2. Contains");
			System.out.println ("3. Print");
			System.out.println ("4. Exit");
			System.out.print ("Enter your choice : ");
			int ch = scan.nextInt ();
			switch (ch) {
				case 1:
					System.out.print ("Please enter element (integer value) : ");
					int element = scan.nextInt();

					System.out.println ("Your choice : 1");
					System.out.println ("Element : " + element);
					bst.insert(new RBTreeNode(element));
					System.out.println ("Element inserted");
					break;
				case 2:
					System.out.print ("Please enter element (integer value) to search in the tree : ");
					element = scan.nextInt();

					System.out.println ("Your choice : 2");
					System.out.println ("Element : " + element);
					boolean res = bst.search (element);
					if (res) 
						System.out.println ("Result : True. Found in the tree.") ;
					else
						System.out.println ("Result : False. Not Found in the tree.");
				break;
				case 3:
					System.out.println ("Your choice : 2");
					bst.inorder(bst.root);
					System.out.println ();
				break;
				case 4:
					System.exit(0);
				break;
			}
		} while (true);     

    }

    public void inorder(RBTreeNode root) {
        if (!isNil(root)) {
            inorder(root.left);
            if (root.color == RBTreeNode.RED)
                System.out.print("*" + root.element + " ");
            else
                System.out.print(root.element + " ");
            inorder(root.right);

        }
    }

    public void insert(T element) {
        insert(new RBTreeNode < T > (element));
    }

    private void insert(RBTreeNode < T > z) {

        RBTreeNode < T > y = nil;
        RBTreeNode < T > x = root;

        while (!isNil(x)) {
            y = x;
            if (z.element.compareTo(x.element) < 0) {
                x.numLeft++;
                x = x.left;
            } else {
                x.numRight++;
                x = x.right;
            }
        }

        z.parent = y;
        if (isNil(y))
            root = z;
        else if (z.element.compareTo(y.element) < 0)
            y.left = z;
        else
            y.right = z;

        z.left = nil;
        z.right = nil;
        z.color = RBTreeNode.RED;

        insertFixup(z);

    }

    private boolean isNil(RBTreeNode node) {
        return node == nil;
    }

    private boolean isRed (RBTreeNode node) {
    	return node.isRed;
    }

    private void insertFixup(RBTreeNode < T > z) {
        RBTreeNode < T > y = nil;
        while (z.parent.color == RBTreeNode.RED) {
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;
                if (y.color == RBTreeNode.RED) {
                    z.parent.color = RBTreeNode.BLACK;
                    y.color = RBTreeNode.BLACK;
                    z.parent.parent.color = RBTreeNode.RED;
                    z = z.parent.parent;
                } else if (z == z.parent.right) {
                    z = z.parent;
                    leftRotate(z);
                } else {
                    z.parent.color = RBTreeNode.BLACK;
                    z.parent.parent.color = RBTreeNode.RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                y = z.parent.parent.left;
                if (y.color == RBTreeNode.RED) {
                    z.parent.color = RBTreeNode.BLACK;
                    y.color = RBTreeNode.BLACK;
                    z.parent.parent.color = RBTreeNode.RED;
                    z = z.parent.parent;
                } else if (z == z.parent.left) {
                    z = z.parent;
                    rightRotate(z);
                } else {
                    z.parent.color = RBTreeNode.BLACK;
                    z.parent.parent.color = RBTreeNode.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = RBTreeNode.BLACK;
    }

    private void leftRotate(RBTreeNode < T > x) {
        
        leftRotateFixup(x);
        
        RBTreeNode < T > y;
        y = x.right;
        x.right = y.left;
        
        if (!isNil(y.left))
            y.left.parent = x;
        y.parent = x.parent;

        if (isNil(x.parent))
            root = y;
        else if (x.parent.left == x)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    private void leftRotateFixup(RBTreeNode x) {

        if (isNil(x.left) && isNil(x.right.left)) {
            x.numLeft = 0;
            x.numRight = 0;
            x.right.numLeft = 1;
        } else if (isNil(x.left) && !isNil(x.right.left)) {
            x.numLeft = 0;
            x.numRight = 1 + x.right.left.numLeft +
                x.right.left.numRight;
            x.right.numLeft = 2 + x.right.left.numLeft +
                x.right.left.numRight;
        } else if (!isNil(x.left) && isNil(x.right.left)) {
            x.numRight = 0;
            x.right.numLeft = 2 + x.left.numLeft + x.left.numRight;

        } else {
            x.numRight = 1 + x.right.left.numLeft +
                x.right.left.numRight;
            x.right.numLeft = 3 + x.left.numLeft + x.left.numRight +
                x.right.left.numLeft + x.right.left.numRight;
        }
    }

    private void rightRotate(RBTreeNode < T > y) {
        rightRotateFixup(y);
        RBTreeNode < T > x = y.left;
        y.left = x.right;

        if (!isNil(x.right))
            x.right.parent = y;
        x.parent = y.parent;

        if (isNil(y.parent))
            root = x;

        else if (y.parent.right == y)
            y.parent.right = x;

        else
            y.parent.left = x;
        x.right = y;

        y.parent = x;

    }

    private void rightRotateFixup(RBTreeNode y) {

        if (isNil(y.right) && isNil(y.left.right)) {
            y.numRight = 0;
            y.numLeft = 0;
            y.left.numRight = 1;
        } else if (isNil(y.right) && !isNil(y.left.right)) {
            y.numRight = 0;
            y.numLeft = 1 + y.left.right.numRight +
                y.left.right.numLeft;
            y.left.numRight = 2 + y.left.right.numRight +
                y.left.right.numLeft;
        } else if (!isNil(y.right) && isNil(y.left.right)) {
            y.numLeft = 0;
            y.left.numRight = 2 + y.right.numRight + y.right.numLeft;

        } else {
            y.numLeft = 1 + y.left.right.numRight +
                y.left.right.numLeft;
            y.left.numRight = 3 + y.right.numRight +
                y.right.numLeft +
                y.left.right.numRight + y.left.right.numLeft;
        }

    }

    public int size() {
        return root.numLeft + root.numRight + 1;
    }

    public boolean search(T element) {
        RBTreeNode < T > current = root;
        while (!isNil(current)) {
            if (current.element.equals(element))
                return true;
            else if (current.element.compareTo(element) < 0)
                current = current.right;
            else
                current = current.left;
        }
        return false;
    }

    private static class RBTreeNode < T extends Comparable < T >> {
	    public static final int BLACK = 0;
	    public static final int RED = 1;
	    public T element;

	    RBTreeNode < T > parent;
	    RBTreeNode < T > left;
	    RBTreeNode < T > right;
	    public int numLeft = 0;
	    public int numRight = 0;
	    public int color;
	    public boolean isRed;

	    RBTreeNode() {
	        color = BLACK;
	        numLeft = 0;
	        numRight = 0;
	        parent = null;
	        left = null;
	        right = null;
	    }

	    RBTreeNode(T element) {
	        this();
	        this.element = element;
	    }
	}
}