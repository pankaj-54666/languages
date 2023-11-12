package com.functional;
import java.util.function.Function;

// compile and run as: 
//javac -d . FunctionalProgramming.java && java com/functional/FunctionalProgramming

class FunctionalProgramming{

    @FunctionalInterface //this annotation is optional
    interface MyCustomFunction{
        String display(String s); //functional interface can only have 1 abstract methode
    }

    public void testLambdaExpressionFromCustomInterface(){

        /* task:override the display using annonymos inner class*/
        MyCustomFunction f0 = new MyCustomFunction(){
            public String display(String s){
                return s + "::end";
            }
        };

        System.out.printf("Using anonymos inner class:: %s\n",f0.display("passed-string"));

        /* task: using lambda */
        MyCustomFunction  f1 = (str)-> { 
            return str + ":end";
        };
        System.out.printf("f1: %s\n",f1.display("passed-string"));

        /* task: using shorthand notation (skipping () and return statement in expression)*/
        MyCustomFunction f2 = str -> str + ":end";

        /* task: methode refernce and lambda*/
        @FunctionalInterface 
        interface MyCustomFunctionTwo{
            void display(String s); 
        }
        MyCustomFunctionTwo f3  = System.out::println; //you can refer any static methode as long as their return and params matches with lambda
        

        f3.display("Now you can use this as normal println methode: "+ 10);
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


    void testLambdaExpressionApplication(){

    }

    public static void main(String args[]){
        System.out.printf("Inside Main\n");

        FunctionalProgramming obj = new FunctionalProgramming();

//        obj.testLambdaExpressionFromProvidedFunctionInterface();
        obj.testLambdaExpressionFromCustomInterface();
    }
}
