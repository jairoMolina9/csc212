/*
Jairo Molina
23499086
CSC 212-12A
Fall 2019

Assignment 6: Generic Stack
Program that uses the Stack class to solve the problem of detecting whether a String
has balanced parentheses, brackets and braces.

*/

import java.util.Scanner;

public class MolinaJairo6 {

  public static void main (String [] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("Enter any expression with {}, () or []");

    if( parenthesesBalanced(sc.nextLine()) )
      System.out.println("Balanced");
    else
      System.out.println("Not Balanced");

  }

  public static Boolean parenthesesBalanced(String input) {

    boolean balanced = false; //default value

    input = input.replaceAll("\\s",""); //gets rid off white spaces

    Stack<Character> stck = new Stack<Character>();

       for(int i=0;i<input.length();i++)
       {

          if (input.charAt(i) == '{' || input.charAt(i) == '(' || input.charAt(i) == '[')
            stck.push(input.charAt(i));

          if (input.charAt(i) == '}' || input.charAt(i) == ')' || input.charAt(i) == ']')
          {

             if (stck.size() == 0) //if there is no opening bracket
               {
                   return false;
               }

               try {

               char tmp = stck.pop();

               if( (tmp == '(' && input.charAt(i) == ')') || (tmp == '[' && input.charAt(i) == ']') || (tmp == '{' && input.charAt(i) == '}') )
                  balanced = true;
               else
                 return false;

               } catch ( Exception e) {

                 System.out.println("Error: " + e.getMessage());

                 return false;

               }
          }

       }

       if(stck.size() == 0) { //double checks list is empty, not necessary but to make sure

         balanced = true;

       }

    return balanced; //no need to check for size > 0 since default balanced value is false

  }

}

class Node<T> {

   private T data;

   private Node<T> next;

   public Node() {

      data = null;

      next = null;

   }

   public Node(T d, Node<T> n) {

      data = d;

      next = n;
   }

   public T getData() {

      return data;

   }

   public Node<T> getNext() {

      return next;

   }

   public void setData(T d) {

      data = d;

   }

   public void setNext(Node<T> n) {

      next = n;

   }

}

class LinkedList<T>{

   // instance variables
   private Node<T> head, tail;

   private int size;

   // constructor
   public LinkedList() {

      head = tail = null;

      size = 0;

   }

   // utility methods

   public int size() {

      return size;

   }

   public boolean isEmpty() {

      return size == 0;

   }

   // methods to change the list

   public void addHead(T item) {

      Node<T> n = new Node<T>(item, head);

      head = n;

      size++;

      if (tail == null)
         tail = head;

   }

   public void addTail(T item) {

      Node<T> n = new Node<T>(item, null);

      if (tail == null)
         head = tail = n;
      else {
         tail.setNext(n);
         tail = n;
      }

      size++;

   }

   public T removeHead() throws Exception {

      if (head == null)
         throw new Exception("Empty List");

      Node<T> n = head;

      head = head.getNext();

      if (head == null)
         tail = head;

      size--;

      return n.getData();
   }

   // LinkedList testing methods:

   public String toString() {

      String ans = "";

      Node<T> n = head;

      while (n != null) {

         ans += n.getData();

         ans += "-->";

         n = n.getNext();

      }

      return ans;

   }
 }

  class Stack<T> {

      private LinkedList<T> data;

      public Stack() {

         data = new LinkedList<T>();

      }

      public T pop() throws Exception {

         return data.removeHead();

      }

      public void push(T x) {

         data.addHead(x);

      }

      public String toString() {

        return data.toString();

      }

      public int size() {

        return data.size();

      }
   }
