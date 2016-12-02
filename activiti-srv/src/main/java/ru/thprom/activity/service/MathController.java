package ru.thprom.activity.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("/{value}")
    public long call(@PathVariable int value) {
        long result = value * value;
        System.out.println(value + " * " + value + " = " + result);
        return result;
    }

}
