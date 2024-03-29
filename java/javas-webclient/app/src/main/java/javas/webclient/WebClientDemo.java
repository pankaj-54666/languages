package javas.webclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javas.webclient.exchange.ProductRequestVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//@Component
//@Slf4j //TODO: check why log.info not working after above
public class WebClientDemo {
    //How to use logger https://logging.apache.org/log4j/2.x/manual/api.html
    private static final Logger logger = LogManager.getLogger(WebClientDemo.class);
//    public static Logger lprintf = LogManager.getFormatterLogger("Foo");

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

//        postSync();
            postSync();
//        getSync();
        }catch (Exception e){
            logger.info("Error: ",e);
        }
    }
}
