public class RBTreeMain {
	public static void main (String[] args) {
		
	}

	private static class RBTree {
		
		public RBTreeNode insert (RBTreeNode root, int element) {
			if (root == null) {
				root = new RBTreeNode(element);
			} else if (element != root.getElement()) {
				int dir = element < root.getElement() ? 0 : 1;
				if (element < root.getElement()) {
					root.setLeft (insert (root.getLeft(), element));
				} else {
					root.setRight (insert (root.getRight(), element));
				}

				if (dir == 0) {
					if (root.getLeft().isRed()) {
						if (root.getRight.isRed()) {
							root.setRed(true);
							root.getLeft().setRed(false);
							root.getRight().setRed(false);
						} else {
							if (root.getLeft().getLeft().isRed()) {
								//single rotation
								root = singleRotation (root, !dir);
							} else if (root.getLeft().getRight().isRed()) {
								//double rotation
								root = doubleRotation (root, !dir);
							}
						}
					}
				} else if (dir == 1) {
					if (root.getRight().isRed()) {
						if (root.getLeft.isRed()) {
							root.setRed(true);
							root.getRight().setRed(false);
							root.getLeft().setRed(false);
						} else {
							if (root.getRight().getRight().isRed()) {
								//single rotation
								root = singleRotation (root, !dir);
							} else if (root.getRight().getLeft().isRed()) {
								//double rotation
								root = doubleRotation (root, !dir);
							}
						}
					}
				}
			}
			return root;
		}

		private RBTreeNode singleRotation (RBTreeNode root, int dir) {
			if (dir == 0) {
				RBTreeNode save = root.getRight();
				root.setRight (save.getLeft());
				save.setLeft (root);

				root.setRed(true);
				save.setRed(false);

				return save;
			} else if (dir == 1) {
				RBTreeNode save = root.getLeft();
				root.setLeft (save.getRight());
				save.setRed (root);

				root.setRed(true);
				save.setRed(false);

				return save;
			}
		}

		private RBTreeNode doubleRotation (RBTreeNode root, int dir) {
			if (dir == 0) {
				root.setRight (singleRotation(root.getRight()), !dir);
				return singleRotation (root, dir);
			} else if(dir == 1) {
				root.setLeft (singleRotation(root.getLeft()), !dir);
				return singleRotation(root, dir);
			}
		}
	}

	private static class RBTreeNode {
		int element;
		RBTreeNode left;
		RBTreeNode right;
		RBTreeNode parent;
		boolean isRed;

		public RBTreeNode () {
			this.element = 0;
			this.left = null;
			this.right = null;
			this.parent = null;
			this.isRed = false;
		}

		public RBTreeNode (int element) {
			this.element = element;
			this.left = null;
			this.right = null;
			this.parent = null;
			this.isRed = false;
		}

		public void setElement (int element) {
			this.element = element;
		}

		public int getElement () {
			return this.element;
		}

		public void setLeft (RBTreeNode left) {
			this.left = left;
		}

		public RBTreeNode getLeft () {
			return this.right;
		}

		public void setRight (RBTreeNode right) {
			this.right = right;
		}

		public RBTreeNode getRight () {
			return this.right;
		}

		public void setParent (RBTreeNode parent) {
			this.parent = parent;
		}

		public RBTreeNode getParent () {
			return this.parent;
		}

		public boolean isRed () {
			return this.isRed;
		}

		public void setRed (boolean color) {
			this.isRed = color;
		}
	}
}