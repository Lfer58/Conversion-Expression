//
// Name: Dubey, Keshav
// Project: 2
// Due: 02/21/2023
// Course: cs-2400-02-sp23
// Description: This project implements the StackInterface<T> within LinkedStack<T>
//      to both convert infix expressions to postfix expression and evaluate postfix expressions
//

import java.util.EmptyStackException;

/** An interface for a stack data structure that stores items of type T.
  @param <T> the type of items stored in the stack */
public interface StackInterface<T> {

    /** Adds a new item to the top of the stack.
     @param newEntry the item to add */
    public void push(T newEntry);

    /** Removes and returns the top item from the stack.
     @return the date stored within the top node
     @throws EmptyStackException if the stack is empty */
    public T pop();

    /** Returns the top item of the stack without removing it.
     @return the data store within the top node
     @throws EmptyStackException if the stack is empty */
    public T peek();

    /** Checks if the stack is empty.
     @return true if the stack is empty, false otherwise */
    public boolean empty();

    /** Removes all items from the stack. */
    public void clear();
}
