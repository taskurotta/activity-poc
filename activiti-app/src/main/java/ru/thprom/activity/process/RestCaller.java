package ru.thprom.activity.process;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCaller {

    @Value("${rest.url}")
    private String url;

    public void call() {
        System.out.println("call");
        new RestTemplate().getForObject(url, Long.class, 2);
    }
}
