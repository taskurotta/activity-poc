package ru.thprom.activity.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCaller {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${rest.url}")
    private String url;

    public void call() {
        log.info("before call rest");
        new RestTemplate().getForObject(url, Long.class, 2);
    }
}
