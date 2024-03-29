package javas.webclient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        logger.info("ENTER:: APP");


        WebClientDemo webClientDemo = new WebClientDemo();
        webClientDemo.test();
    }
}
