To build any gradle project from scratch, you can follow below steps:

(a) Install Java (if you still haven't)
(b) Install Gradle (https://gradle.org/install/) & Check Gradle Version

(c) Go to destination directory and issue `gradle init`
You can alos follow steps at: https://docs.gradle.org/current/samples/sample_building_java_applications.html

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
`echo 'export PATH=$PATH:/usr/local/go/bin' >> ~/.bashrc`

OR you can directly add entry using `vim` or `nano`




