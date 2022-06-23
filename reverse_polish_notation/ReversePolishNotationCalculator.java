// This program reads a Reverse Polish Notation mathematical Expression
// and evaluates it.  The program will show each step if the user chooses that
// otherwise the program will only print out the end result
//
//Apram Cherra
//Bit 143 Fall 2021

import java.util.*;

public class ReversePolishNotationCalculator {

  // This gets the remainder of the input out of the Scanner
  // prints that remaining input (and also prints out the current contents of the
  // stack)
  // and then re-loads the remaining input into a new Scanner
  // This means that we can keep Scanning the remainder of the input
  private static Scanner printRemainingExpression(
    Stack<Double> numbers,
    Scanner scExpression
  ) {
    String remainderOfExpr = scExpression.nextLine();
    System.out.println(
      "Remaining expression: " + remainderOfExpr + " Stack: " + numbers
    );
    scExpression.close(); // may as well close out the old one before creating a new replacement
    return new Scanner(remainderOfExpr + "\n");
  }

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    char evalAgain = 'y';
    Stack<Double> operands = new Stack<>();

    ShouldWeTryAgain:while (evalAgain == 'y') {
      double nextNumber = 0;

      System.out.println("\nRPN calculator");
      System.out.println("\tSupported operators: + - * /");
      System.out.print(
        "Type your RPN expression in so that it can be evaluated: "
      );
      String rpnExpr = keyboard.nextLine();

      boolean explain = false;
      System.out.print(
        "Would you like me to explain how to expression is evaluated? (y or Y for yes, anything else means no) "
      );
      String szExplain = keyboard.nextLine().trim().toLowerCase();
      if (szExplain.length() == 1 && szExplain.charAt(0) == 'y') {
        System.out.println("We WILL explain the evaluation, step by step");
        explain = true;
      }

      Scanner scExpr = new Scanner(rpnExpr + "\n");
      while (scExpr.hasNext()) {
        if (explain) {
          scExpr = printRemainingExpression(operands, scExpr);
        }
        // if the next thing in the expression is a number:

        String nextToken = scExpr.next();

        if (isNumeric(nextToken)) {
          nextNumber = Double.parseDouble(nextToken);
          if (explain) {
            System.out.println(
              "\tPushing " +
              nextNumber +
              " onto the stack of operands (numbers)"
            );
          }
          operands.add(nextNumber);
        } else {
          if (nextToken.length() > 1) {
            System.err.println(
              "ERROR! Operator (non-numeric input) contains more than 1 character: " +
              nextToken
            );
            continue ShouldWeTryAgain;
          }
          double second;
          double first;

          try {
            second = operands.pop().doubleValue();
            first = operands.pop().doubleValue();
            if (explain) System.out.println(
              "\tPopping " +
              second +
              " and " +
              first +
              " then applying " +
              nextToken +
              " to them, then pushing the result back onto the stack"
            );
          } catch (EmptyStackException ese) {
            System.err.println(
              "ERROR! Expected to find 2 operands (numbers) but we don't have two numbers on the stack!"
            );
            System.out.println(
              "Since we can't evaluate that expression we'll ask you for another one to evaluate instead"
            );
            continue ShouldWeTryAgain;
          }

          switch (nextToken) {
            case "+":
              operands.push(first + second);
              break;
            case "-":
              operands.push(first - second);
              break;
            case "*":
              operands.push(first * second);
              break;
            case "/":
              operands.push(first / second);
              break;
            default:
              System.err.println(
                "ERROR! Operator not recognized: " + nextToken
              );
              System.out.println(
                "Since we can't evaluate that expression we'll ask you for another one to evaluate instead"
              );
            // continue ShouldWeTryAgain; // This line will jump back to the outer loop
          }
        }
      }

      // At this point we've finished reading through the expression

      // If there's more than 1 operand (number) left then we print this error
      // message:
      if (operands.size() > 1) {
        System.err.println(
          "ERROR! Ran out of operators before we used up all the operands (numbers):"
        );
      }
      // Go through all the operands an print them out:
      System.err.println("\t" + operands);

      // If there wasn't any operands then print out this error message:
      if (operands.size() < 1) {
        System.err.println("ERROR! Ran out of operands (numbers)");
      }

      // If there's exactly 1 operand then it must be the answer
      if (operands.size() == 1) {
        System.out.println("END RESULT: " + operands.pop());
      }

      System.out.print(
        "\nWould you like to evaluate another expression? (y or Y for yes, anything else to exit) "
      );
      String repeat = keyboard.nextLine().trim().toLowerCase();
      if (repeat.length() == 1 && repeat.charAt(0) == 'y') {
        evalAgain = 'y';
        operands = new Stack<>();
      } else {
        evalAgain = 'n';
      }
    }
    System.out.println("Thank you for using RPN Calculator!");
  }

  public static boolean isNumeric(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      double d = Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }
}
