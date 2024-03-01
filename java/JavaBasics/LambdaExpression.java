package com.example;
import java.util.function.Function;


interface MyFunction{
    double getDouble();
}


//javac -d . LambdaExpression.java && java com/example/LambdaExpression
class LambdaExpression{

    public static String customReverse(Function<String,String> reverseLogic, String str)
    {
        return reverseLogic.apply(str);
    }

    public static void main(String args[]){
        
        System.out.print("Inside LambdaExpression main\n");



    MyFunction obj1;
    obj1 = () -> 3456.345;

    // int a = obj1();      //will not work
    /* Why above will not work.
        OBSERVE obj1() will not work, as above statement is misleading. What "obj1 = () -> 346.345" does is that accept the getDouble's implementation from user. As MyFunction class has single abstract methode, java compiler now know that it need to replace the virtual methode with provided methode in the line.
        Once methode defination to getDouble is provided, now you can assume obj1 as a class object & can call its class variable the way you call normal class*/

    double a = obj1.getDouble(); //will work

    System.out.printf("a: %f\n",a);

    /* type mismatch */
    // obj1 = () -> "Hello";  //explain why it will not work? Hint: consider that the statment will be made as function declration of double getDouble().

    /* generic lambda expression*/

    interface SomeFunc<T> {
        T apply(T t);
    } 

    SomeFunc<String> reverse = (str) -> "reversed: " + str;
    System.out.printf("reversed string: %s\n",reverse.apply("Hello"));


    /* Passing lambda as a argument */

    //instead of defining our own lambda expression taking single input & returning single output. We have used provided Function lambda by java.util.function.Function
    //if you want to accept more arguments, you can either chain these or create new lambda interface accepting muliple values (like MyFunction, but getDouble accepting multiple parametes this time )
    Function<String,String> reverseLogicOne = (str) -> "reverse1: " + str;
    Function<String,String> reverseLogicTwo = (str) -> "reverse2: " + str;

    String s2 = customReverse(reverseLogicOne,"abcd");
    String s3 = customReverse((str)-> "reverse3: " + str, "abcd"); /* very important pattern: create and pass the function in-place */

    System.out.printf("s2: %s, s3=%s\n",s2,s3);

    /* throwing exceptions from lambda*/
    //TO-DO: 




    }
}