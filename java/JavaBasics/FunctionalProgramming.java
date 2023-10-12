package com.functional;
import java.util.function.Function;

// compile and run as: javac -d . FunctionalProgramming.java && java com/functional/FunctionalProgramming

class FunctionalProgramming{

    @FunctionalInterface //this annotation is optional
    interface MyCustomFunction{
        String display(String s); //functional interface can only have 1 abstract methode
    }

    public void testLambdaExpressionFromCustomInterface(){

        MyCustomFunction f1 = str -> str + ":end";
        System.out.printf("f1: %s\n",f1.display("passed-string"));
    }


    public void testLambdaExpressionFromProvidedFunctionInterface(){
        /* the Function interface is just a provided interface by java.util.function package
            It has defined 3functions as .andThen .compose and .apply
         */
        Function<Integer, String> fone = (a) -> "::start " + Integer.toString(a);

        Function<String,String> ftwo = str -> str + " end::";

        Function<Integer,String> combination1 = fone.andThen(ftwo); // -->
        Function<Integer,String> combination2 = ftwo.compose(fone); //<--

        System.out.printf("c1: %s\n",combination1.apply(123));

        System.out.printf("c2: %s\n",combination2.apply(123));

    }

    public static void main(String args[]){
        System.out.printf("Inside Main\n");

        FunctionalProgramming obj = new FunctionalProgramming();

//        obj.testLambdaExpressionFromProvidedFunctionInterface();
        obj.testLambdaExpressionFromCustomInterface();
    }
}
