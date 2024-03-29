package javas.webclient;


import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
//@Slf4j //TODO: check why log.info not working after above
public class WebClientDemo {
    private static final Logger logger = LogManager.getLogger("HelloWorld");
    public void demo()
    {

        String url = "www.google.com";
        WebClient client = WebClient.builder()
                .baseUrl(url)
                .build();

        Mono<String> response = client.post()
                .retrieve()
                .bodyToMono(String.class);


        response.subscribe(responseBody ->

                logger.info("responseBody: ",responseBody),
                error -> {
                logger.error("Error happended: ",error);
                });
    }
}
