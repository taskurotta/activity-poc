package ru.thprom.activity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{value}")
    public long call(@PathVariable int value) {
        long result = value * value;
        log.info("Math call: {} * {} = {}", value, value, result);
        return result;
    }

}
