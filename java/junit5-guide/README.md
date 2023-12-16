
`gradle init` -> then move /src director from app folder to root & then delete app folder
`gradle tasks`
`gradle build` `gradle run` `gradle tests` : common commands

rename folder from java-junit5 to junit5-guide as java keyword not allowed in gradle project.

`gradle clean test` : sometimes when project does not build as expected, first clean the gradle.
issue1: one of the case of when i updated the gradle test to show passed test, but runniung gradle test was still running the old build.gradle, so i needed to run gradle clean test


# show passed/failed test status
```
//build.gradle
test {
    testLogging.showStandardStreams = true
    testLogging {
        events "passed", "skipped", "failed"
    }
}
```