//
// Name: Dubey, Keshav
// Project: 2
// Due: 02/21/2023
// Course: cs-2400-02-sp23
// Description: This project implements the StackInterface<T> within LinkedStack<T>
//      to both convert infix expressions to postfix expression and evaluate postfix expressions
//

/** The Expression class provides static methods to convert infix expressions to postfix expressions
 and to evaluate postfix expressions. It also contains a private helper method to get the precedence
 of the operators and a method to convert a string to a string array. */
public class Expression {

    /** Convert an infix expression to postfix notation.
     @param infixExpression An array of strings containing the infix expression.
     @return An array of strings containing the postfix expression.
     @throws RuntimeException if the input is not well-formed, i.e., if parentheses are not formed appropriately or if one of the inputs is not an integer literal. */
    public static String[] convertToPostfix(String[] infixExpression) throws RuntimeException {
        StackInterface<String> operatorStack = new LinkedStack<>();
        StringBuilder postFix = new StringBuilder();
        int openParenthesesCount = 0; //Maintains a count of open parentheses to check if there is an imbalance of parentheses

        for (String nextIndex : infixExpression) {
            //Checks whether the expression fed into the method has nothing fed into it, and throws an exception if so.
            if (nextIndex.isBlank()) {
                throw new RuntimeException("This blank expression cannot be converted.");
            }

            // If the next token is a left parenthesis, it is pushed onto the operator stack. The parentheses count is increased by 1.
            // If the next token is an operator, it is added to the postfix expression after popping any operators from the operator stack
            // that have higher or equal precedence and pushing the next operator onto the stack.
            // If the next token is a right parenthesis and the parentheses count isn't 0, it is added to the postfix expression after
            // popping all operators from the stack until a left parenthesis is encountered. The parentheses count is decreased by 1.
            switch (nextIndex) {
                case "(" -> {
                    operatorStack.push(nextIndex);
                    openParenthesesCount++;
                }
                case "+", "-", "*", "/", "^" -> {
                    while (!operatorStack.empty() && getPrecedence(nextIndex) <= getPrecedence(operatorStack.peek())) {
                        postFix.append(operatorStack.peek()).append(" ");
                        operatorStack.pop();
                    }
                    operatorStack.push(nextIndex);
                }
                case ")" -> {
                    if (openParenthesesCount != 0) {
                        String topOperator = operatorStack.pop();
                        while (!topOperator.equals("(")) {
                            postFix.append(topOperator).append(" ");
                            topOperator = operatorStack.pop();
                        }
                        openParenthesesCount--;
                    } else {
                        throw new RuntimeException("This infix expression is not well-formed due to the left parentheses not being formed appropriately.");
                    }
                }
                default -> {
                    // If the next token is a letter, it is added to the postfix expression.
                    // If the next token is a digit, it is checked if it is an integer literal. If it is, then it is appended.
                    // All other elements are ignored.
                    if (Character.isLetter(nextIndex.charAt(0))) {
                        postFix.append(nextIndex).append(" ");
                    } else if (Character.isDigit(nextIndex.charAt(0))) {
                        try {
                            Integer.parseInt(nextIndex);
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("One of the inputs is not an integer literal");
                        }
                        postFix.append(nextIndex).append(" ");
                    }
                }
            }
        }

        // After all the strings have been processed, any remaining operators on the stack are popped and added to the postfix expression.
        // If a left parenthesis is encountered on the stack, the infix expression is not well-formed.
        while (!operatorStack.empty()) {
            String topOperator = operatorStack.pop();
            if (!topOperator.equals("(")) {
                postFix.append(topOperator).append(" ");
            } else {
                throw new RuntimeException("This infix expression is not well-formed due to the right parentheses not being formed appropriately.");
            }
        }

        return toStringArray(postFix.toString());
    }

    /** Evaluate a postfix expression and return the result.
     @param postfixExpression An array of strings containing the postfix expression.
     @return The integer result of evaluating the postfix expression.
     @throws RuntimeException if one of the inputs is not an integer literal and thus cannot be evaluated. */
    public static int evaluatePostfix(String[] postfixExpression) {
        StackInterface<Integer> valueStack = new LinkedStack<>();

        for (String nextIndex : postfixExpression) {
            //Checks whether the expression fed into the method has nothing fed into it, and throws an exception if so.
            if (nextIndex.isBlank()) {
                throw new RuntimeException("The Postfix expression is completely blank and cannot be evaluated.");
            }

            // If the element is an operator, pop the top two values from the stack as operands and perform the operation.
            // An Empty Stack exception is thrown if there aren't the required operands present.
            switch (nextIndex) {
                case "+", "-", "*", "/", "^"-> {
                    int result = 0;
                    int operandTwo = valueStack.pop();
                    int operandOne = valueStack.pop();
                    switch (nextIndex) {
                        case "+" -> result = operandOne + operandTwo;
                        case "-" -> result = operandOne - operandTwo;
                        case "*" -> result = operandOne * operandTwo;
                        case "/" -> result = operandOne / operandTwo;
                        case "^" -> result = (int) Math.pow(operandOne, operandTwo);
                    }
                    valueStack.push(result);
                }
                default -> {
                    // If the element is not an integer literal, throw a runtime exception
                    if (Character.isLetterOrDigit(nextIndex.charAt(0))) {
                        try {
                            valueStack.push(Integer.parseInt(nextIndex));
                        } catch (NumberFormatException e) {
                            throw new RuntimeException("One of the inputs is not an integer literal and thus cannot be evaluated.");
                        }
                    }
                }
            }
        }

        // Return the result, which should be the only element left on the stack
        return valueStack.peek();
    }

    /** Returns the precedence level of the given operator.
     @param op the operator whose precedence level is to be determined
     @return an integer representing the precedence level of the operator, where higher numbers represent higher precedence */
    private static int getPrecedence(String op) {
        //Precedence is dictated by the order of operations, thus exponents have the highest value.
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> -1;
        };
    }

    /** Returns an array of Strings obtained by splitting the input String.
     @param tempString the String to be split into an array of Strings
     @return an array of Strings obtained by splitting the input String into substrings separated by a space character */
    public static String[] toStringArray(String tempString) {
        return tempString.split(" ");
    }
}
