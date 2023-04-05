//
// Name: Dubey, Keshav
// Project: 2
// Due: 02/21/2023
// Course: cs-2400-02-sp23
// Description: This project implements the StackInterface<T> within LinkedStack<T>
//      to both convert infix expressions to postfix expression and evaluate postfix expressions
//

import java.util.EmptyStackException;

/** A class that implements the StackInterface<T> interface using a singly
  linked list to store the stack items.
  @param <T> the type of the items stored in the stack */
public final class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode; // References the first node in the chain

    /** Creates an empty stack. */
    public LinkedStack() {

        topNode = null;
    } // end default constructor

    /** Adds a new item to the top of the stack.
      @param newEntry the item to add */
    public void push(T newEntry) {
        topNode = new Node(newEntry, topNode);
    } // end push

    /** Removes and returns the top item from the stack.
      @return the date stored within the top node
      @throws EmptyStackException if the stack is empty */
    public T pop() {
        if (empty()) {
            throw new EmptyStackException();
        } else {
            T top = peek(); // Might throw EmptyStackException // Assertion: topNode != null
            topNode = topNode.getNextNode();
            return top;
        }
    } // end pop

    /** Returns the top item of the stack without removing it.
      @return the data store within the top node
      @throws EmptyStackException if the stack is empty */
    public T peek() {
        if (empty()) {
            throw new EmptyStackException();
        } else {
            return topNode.getData();
        }
    } // end peek

    /** Checks if the stack is empty.
      @return true if the stack is empty, false otherwise */
    public boolean empty() {
        return topNode == null;
    } // end isEmpty

    /** Removes all items from the stack. */
    public void clear() {
        topNode = null;
    } // end clear

    /** A private nested class that represents a node in the linked list. */
    private class Node
    {

        private T data; // Entry in stack
        private Node next; // Link to next node

        /** Creates a new node with the given data and next node.
          @param newEntry the data to store in the new node
          @param topNode the node that should follow the new node in the list */
        public Node(T newEntry, Node topNode) {
            this.data = newEntry;
            this.next = topNode;
        }

        /** Returns the node that follows this node in the list.
          @return the next node */
        public Node getNextNode() {
            return topNode.next;
        }

        /** Returns the data stored in this node.
          @return the data */
        public T getData() {
            return topNode.data;
        }

        // Constructors and the methods getData, setData, getNextNode, setNextNode are here.

    } // end Node

} // end LinkedStack