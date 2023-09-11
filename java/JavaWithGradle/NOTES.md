To build any gradle project from scratch, you can follow below steps:

(a) Install Java (if you still haven't)
(b) Install Gradle (https://gradle.org/install/) & Check Gradle Version

(c) Go to destination directory and issue `gradle init`
You can also follow steps at: https://docs.gradle.org/current/samples/sample_building_java_applications.html

Once gradle init is successful, you have sample application ready.

(d) Compile Gradle (Compile Project) 
`gradle build`(used systems gradle version) OR `./gradlew build`(uses project gradle version)

(e) Run Project
`./gradlew run`



## Common Issue faced on Linux
a. Gradle installatino in Linux.
Solution: Install the binary using `wget` as extact and then add to PATH.

b. On Window WSL, the path variable get lost.
Solution: It's the defaul behaviour for wsl. As each time path is sourced from `~/.bashrc` file.
Hence, you need to make an entry to this file. You can use below command example.
`echo 'export PATH=$PATH:/home/pankaj/gradle/gradle-8.3/bin' >> ~/.bashr`

OR you can directly add entry using `vim` or `nano`

## Gradle Command Line Interface
https://docs.gradle.org/current/userguide/command_line_interface.html#command_line_interface

## Gradle task, lifecycle and etc
https://docs.gradle.org/current/userguide

## Other Helpful
(a) Running gradle with different that what is set on JAVA_HOME
`gradle -Dorg.gradle.java.home=/JDK_PATH build`

(b) Running multiple version of gradle on machine.
install each gradle from gradle home, then set different alias.

ex: g7j17 `alias g7j17="/home/pankaj/gradle/gradle-8.3/bin/gradle  -Dorg.gradle.java.home=/JDK_PATH"`

(c) Add entry to PATH.

(d) project's gradlew is preferred over system gradle.

## Gradle Stuff 
(a) What does `plugin id='application'` does in build.gradle?
It make available task `gradle run`, which builds application and then run it.

(b) view & make new task & run them
`gradle tasks`
`gradle run|build|other_task`
Make new task by applying corresponing plugin, or make yourself using gradle build

(c) There is also a udemy course on gradle.