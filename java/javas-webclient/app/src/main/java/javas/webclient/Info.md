

## Challenges when implementing the issue
Issue1: when trying to test .post methode, error was displayed that .contentType is not set properly.
`https://docs.spring.io/spring-framework/reference/web/webflux-webclient/client-body.html`

RootCause: Root cause was that webClient uses Jackson to convert requestBody to json.
But, jackson was not properly configured in build.gradle

Issue2: Once i imported jackson `2.14.0` now different error message displayed.
RootCause: need to use latest version of jackson which is `2.17.0`

Consolidated Solution: If the problem persist it is better to use Gson or Jackson to convert th requestBody to json before 
passing the requestBody to webClient.

Other way is to add latest jackson depedency in build.gradle
