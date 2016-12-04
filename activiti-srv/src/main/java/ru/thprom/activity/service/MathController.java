package ru.thprom.activity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/math")
public class MathController {

    AtomicInteger counter = new AtomicInteger();
    volatile long startTime = 0L;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{value}")
    public long call(@PathVariable int value) {
        long result = value * value;

        int counterValue = counter.incrementAndGet();

        if (counterValue == 1) {
            startTime = System.currentTimeMillis();
        }

        if (counterValue % 1000 == 0) {
            int taskPerSeconds = (int) (counterValue / ((float) (System.currentTimeMillis() - startTime) / 1000));
            log.info("Counter = {}, task per seconds = {}", counterValue, taskPerSeconds);

        }

        return result;
    }

}
