

## Challenges when implementing the issue

    ###Issue0: `JNI error has occurred issue was coming when runing the application`
    RootCause: Root cause was the use of `java` name in package path. to resolved we changed java to javasnippet(=javas)
    ### Issue1: when trying to test .post methode, error was displayed that .contentType is not set properly.
        `https://docs.spring.io/spring-framework/reference/web/webflux-webclient/client-body.html`
        
        RootCause: Root cause was that webClient uses Jackson to convert requestBody to json.
        But, jackson was not properly configured in build.gradle

    ### Issue2: Once i imported jackson `2.14.0` now different error message displayed.
        RootCause: need to use latest version of jackson which is `2.17.0`

        Consolidated Solution: If the problem persist it is better to use Gson or Jackson to convert th requestBody to json before 
        passing the requestBody to webClient.

        Other way is to add latest jackson depedency in build.gradle


    ## Issue3: after adding log4j log was not getting printed.
    RootCause: it was due to fact that by default log.info is disabled and log.error is being displayed only. To fix it added log4j.properties file in resources location



## References & Docs

(a) WebClient get and post : https://docs.spring.io/spring-framework/reference/web/webflux-webclient/client-body.html
(b) How to include log4j in the project: https://logging.apache.org/log4j/2.x/
(c) Include jackson / gson in build.gradle
(d) How to include lombok to project(use plugin version) `https://projectlombok.org/setup/gradle`
(e) GitHub dummy GET,POST apis
`https://github.com/Ovi/DummyJSON`
`https://dummyjson.com/docs`

(f) WebClient .filter chaining for request and response `https://docs.spring.io/spring-framework/reference/web/webflux-webclient/client-filter.html`
(g) .retrival vs .exchange (exchange has header and httpStatus decision available, we can take decisiion based on that, and also convert responseBody to different entity based on appropriate status code)
ex: when error responseEntity need to be caset to ErrorResponseVo else to ResponseVo

(h) .onStatus with .retrival
TODO: check if onStatus is only used to map statusCode to Mono<Exception> or for some other purpose also?

() TODO:  .onErrorResume for converting mono erros to Entity or some fixed type Exception (https://www.javaguides.net/2023/11/spring-boot-webclient-tutorial.html)
() .toEntity :: to convert to responseEntity! (https://howtodoinjava.com/spring-webflux/webclient-get-post-example/)
(https://reflectoring.io/spring-webclient/)