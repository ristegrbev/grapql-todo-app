package com.riste.grbev.todo.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/test")
public class TestController {

    @GetMapping
    public Map<String, Integer> test() {
        Map<String, Integer> map = new HashMap<>();
        map.put("test", 1);
        return map;
    }
}
