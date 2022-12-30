import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This is a binary expression tree that converts postfix expressions into infix
 * expressions. It prints the expressions and its order based on precedence.
 * 
 * Code from https://www.geeksforgeeks.org/expression-tree/
 * 
 * @author Chase Hollander
 *
 */
public class BET {

    private BinaryNode root;
    private String postfix;

    /**
     * Default constructor - creates an empty tree.
     */
    public BET() {
        root = null;
    }

    /**
     * Constructor accepting postfix string to parse.
     * 
     * @param pf - the postfix string
     */
    public BET(String pf) {
        postfix = pf;
        buildFromPostfix(pf);
        // builds tree based on input postfix string
    }

    /**
     * Method accepts input string and builds the tree based on it.
     * 
     * @param pf - the postfix string
     * @return - true if tree build, false if bad pf format
     */
    public boolean buildFromPostfix(String pf) {
        char ch;
        postfix = pf;
        Stack<BinaryNode> st = new Stack<BinaryNode>();
        BinaryNode t1, t2, temp;

        for (int i = 0; i < postfix.length(); i++) {
            ch = postfix.charAt(i);
            if (!isOperator(postfix.charAt(i))) {

                if (ch >= '0' && ch <= '9') {
                    int n = 0;

                    // Checks to see if the character is a digit
                    // as a char.
                    while (Character.isDigit(ch)) {
                        n = n * 10 + (int) (ch - '0');
                        i++;
                        ch = postfix.charAt(i);
                    }
                    i--;

                    temp = new BinaryNode(n + " ");
                    st.push(temp);

                } else if (ch != ' ') {
                    temp = new BinaryNode(ch + " ");
                    st.push(temp);
                }

            } else if (isOperator(ch)) {
                temp = new BinaryNode(ch + " ");
                try {
                    t1 = st.pop();
                    t2 = st.pop();
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException();
                }

                temp.left = t2;
                temp.right = t1;

                st.push(temp);

            }

        }
        root = st.pop();
        if (!st.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    /**
     * This method recursively prints the trees contents in order.
     * 
     * @param root - refers to root node in BinaryNode
     * @return - nothing. A void method that prints the contents in order of
     *         precedence.
     */
    protected void inorder(BinaryNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            System.out.print("( ");
            inorder(root.left);
        }
        System.out.print(root.element);
        if (root.right != null) {
            inorder(root.right);
            System.out.print(") ");

        }

    }

    /**
     * Method that prints an infix expression from the BET. If tree is empty,
     * prints "No expression available"
     */
    public void printInfixExpression() {
        if (root == null) {
            System.out.print("No expression available");
        }
        inorder(root);
    }

    /**
     * Method that prints postfix expression used to build BET. If tree is
     * empty, prints "No expression available"
     */
    public void printPostfixExpression() {
        System.out.println(postfix);
    }

    /**
     * Outputs number of nodes in the tree.
     * 
     * @return int
     */
    public int size() {
        return size(root);

    }

    /**
     * This helper method is used to find the size of the tree.
     * 
     * @param node - refers to the node in BinaryNode
     * @return - count. Variable that holds the trees size
     */
    private int size(BinaryNode node) {
        int count = 1;
        if (node == null) {
            return 0;
        }
        count += size(node.left);
        count += size(node.right);

        return count;
    }

    /**
     * isEmpty() - returns true if empty, false if not.
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        if ((size() == 0 && root == null)) {
            return true;
        }
        return false;
    }

    /**
     * This method returns a boolean for characters that classify as operators.
     * 
     * @param ch - a char use to represent an operator
     * @return boolean - returns true if operator
     */
    public boolean isOperator(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * BinaryNode class- class representing a node in the BET. Not accessible
     * outside this class
     * 
     * @author john1819
     *
     */
    private class BinaryNode {
        String element;
        BinaryNode right;
        BinaryNode left;

        /**
         * Constructor to create node with known data.
         * 
         * @param String - data to be stored
         */
        public BinaryNode(String element) {
            this.element = element;
            right = null;
            left = null;
        }

    }

}
