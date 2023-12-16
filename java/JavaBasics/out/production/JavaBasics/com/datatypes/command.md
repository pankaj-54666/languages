
- compile java class that is placed inside same folder name & class name matches.
`javac MyClass.java && java MyClass`

- compiling package based class
`javac -d . MyClass.java && java path_to_myclass`

- watch out
during javac you must pass the .java
during java you should NOT pass the .class

