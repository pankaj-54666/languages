package javas.webclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javas.webclient.exchange.ProductRequestVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//@Component
//@Slf4j //TODO: check why log.info not working after above
public class WebClientDemo {
    //How to use logger https://logging.apache.org/log4j/2.x/manual/api.html
    private static final Logger logger = LogManager.getLogger(WebClientDemo.class);
//    public static Logger lprintf = LogManager.getFormatterLogger("Foo");

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            logger.info("logRequest(way1) Request: " + clientRequest.method() + " " + clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> logger.info(name + ": " + value)));
            return Mono.just(clientRequest);
        });
    }

    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            logger.info("logResponse(way1) Response Status: " + clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }

    public void filterDemo()
    {
        String url = "https://dummyjson.com/carts";

        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                /* different way to intercept clientRequest*/
                .filter(logRequest())
                .filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
                    logger.info("logRequest (way2) clientRequest: {}: {}",clientRequest.method() ,clientRequest.url());
                    return Mono.just(clientRequest);
                }))
                .filter(((clientRequest, nextFilter) -> { //same as (clientRequest,nextFilter) =>request = clienRequest only(can you check the api)
                    logger.info("log request(way3): methode{}, url:{}",clientRequest.method(),clientRequest.url());
                    return nextFilter.exchange(clientRequest);
                })) //https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/function/client/ExchangeFilterFunction.html#filter(org.springframework.web.reactive.function.client.ClientRequest,org.springframework.web.reactive.function.client.ExchangeFunction)

                /* different way to intercept clientResponse*/
                .filter(logResponse())
                .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                    logger.info("logResponse(way1) Response Status:{} ", clientResponse.statusCode());
                    return Mono.just(clientResponse);
                }))

                /* Adding extra header */
                .filter((request, next) -> {
                    ClientRequest filteredRequest = ClientRequest.from(request)
                            .header("test-header", "test-header-value")
                            .build();

                    return next.exchange(filteredRequest);
                })
                //for more filter example check :https://docs.spring.io/spring-framework/reference/web/webflux-webclient/client-filter.html


                .filter(logRequest())
                .build();

        webClient.get()
                .retrieve()
                .bodyToMono(String.class)
//                .log()
                .block();
    }

    public void postSync()  {
        String baseUrl = "https://dummyjson.com";

//        WebClient client = WebClient.create(baseUrl);
        WebClient client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();

        ProductRequestVo requestBody = new ProductRequestVo("BMW Pencisl");

        Gson gson = new Gson(); //way1 to convert to json
        ObjectMapper objectMapper = new ObjectMapper(); //way2 to convert to json

        try {
            //https://docs.spring.io/spring-framework/reference/web/webflux-webclient/client-body.html
            String responseBody = client.post()
                    .uri("/products/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)     //Was not working when jackson depedency was not properly configured
//                  .bodyValue(gson.toJson(requestBody)) //preferred2
//                  .bodyValue(objectMapper.writeValueAsString(requestBody))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println(responseBody);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getSync()
    {

        String url = "https://dummyjson.com/carts";

        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();

        String sres = client.get()
                .retrieve()
                .bodyToMono(String.class)
//                .log()
                .block();

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String sjson = gson.toJson(sres);
//
//        logger.info("sjson: {}",sjson);
    }
    public void getAsync()
    {

        String url = "https://dummyjson.com/carts"; //https://github.com/Ovi/DummyJSON

        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();

        Mono<String> response = client.get()
                .retrieve()
                .bodyToMono(String.class);

        //TODO: this does not get called, as main thread is exited & does not wait for Mono response to be finished
        response.subscribe(
                responseBody -> logger.info("Response body: {}", responseBody),
                error -> logger.error("Error happened: ", error)
        );

    }

    public void test()
    {
        try {
            logger.info("Enter::test");
//
//            getSync();
//            postSync();

            filterDemo();
        }catch (Exception e){
            logger.info("Error: ",e);
        }
    }
}
