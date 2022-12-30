
/**
 * Class containing main method used to test BETTester.java Contains both good
 * postfix expressions with only add, subtract, multiply and divide operators.
 * And a few bad ones.
 * 
 * @author john1819
 *
 */
public class BETTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BET tree = new BET();

		// Test 1
		tree.buildFromPostfix("4 50 6 + +");
		tree.printPostfixExpression();
		System.out.println("Ideal output is: 4 +  50 + 6 ");
		System.out.println("Accepted output is ( 4 + ( 50 + 6 ) )  ");
		
		System.out.println("Actual output");
		tree.printInfixExpression();
		System.out.println();

		// Test 2
		tree = new BET("4 50 + 6 +");
		tree.printPostfixExpression();
		System.out.println("Ideal output is: 4 + 50 + 6");
		System.out.println("Accepted output is: ( ( 4 + 50 ) + 6 ) ");
		System.out.println("Actual output");
		tree.printInfixExpression();
		System.out.println();

		// Test 3
		tree = new BET("4 50 + 6 2 * +");
		tree.printPostfixExpression();
		System.out.println("Ideal output is: 4 + 50 + 6 * 2");
		System.out.println("Accepted output is ( ( 4 + 50 ) + ( 6 * 2 ) ) ");
		System.out.println("Actual output");
		tree.printInfixExpression();
		System.out.println();

		// Test 4
		tree = new BET("4 50 6 + + 2 *");
		tree.printPostfixExpression();
		System.out.println("Ideal output is: ( 4 + 50 + 6 ) * 2");
		System.out.println("Accepted output is ( ( 4 + ( 50 + 6 ) ) * 2 ) ");
		System.out.println("Actual output");
		tree.printInfixExpression();
		System.out.println();

		// Test 5
		tree = new BET("a b + c d e + * *");
		
		tree.printPostfixExpression();
		System.out.println("Ideal output is: ( a + b ) * c * ( d + e )");
		System.out.println("Accepted output is ( ( a + b ) * ( c * ( d + e ) ) )  ");
		System.out.println("Actual output");
		tree.printInfixExpression();
		System.out.println();

		// Test 6
		try {
			tree = new BET("4 50 6 +");
			tree.printPostfixExpression();
			System.out.println("Expected output is: 4 + 50 + 6");
			System.out.println("Actual output");
			tree.printInfixExpression();
			System.out.println();
		} catch (IllegalArgumentException ex) {
			System.out.println("Correctly received illegal Argument exception");
		}

	}

}
