//
// Name: Dubey, Keshav
// Project: 2
// Due: 02/21/2023
// Course: cs-2400-02-sp23
// Description: This project implements the StackInterface<T> within LinkedStack<T>
//      to both convert infix expressions to postfix expression and evaluate postfix expressions
//

public class ExpressionTest {

    public static void main(String[] args) {

        System.out.println("Expression by K. Dubey\n");

        //For every expression entered into the command line, it is printed out and turned into a String array.
        //Then is it converted to a postfix expression and evaluated. Lastly, it outputs both the postfix and its result.
        for (String s : args) {
            System.out.println(s);
            String[] infixExpression = Expression.toStringArray(s);
            String[] postfixExpression = Expression.convertToPostfix(infixExpression);
            int postfixResult = Expression.evaluatePostfix(postfixExpression);
            System.out.println("        " + String.join(" ",postfixExpression) + " = " + postfixResult);
        }

    }
}
