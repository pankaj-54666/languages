
- compile java class that is placed inside same folder name & class name matches.
`javac MyClass.java && java MyClass`

- compiling package based class
`javac -d . MyClass.java && java path_to_myclass`

- compile with verbose
`javac MyClass.java -verbose`

- watch out
during javac you must pass the .java
during java you should NOT pass the .class

A package name if build with `javac MyClass.java`, then it will crete .class in same directory.
&  the class file will not be executable unless you place it in correct package(aka directory) structure. Hence MUST use `-d .` to build class having package defined.

