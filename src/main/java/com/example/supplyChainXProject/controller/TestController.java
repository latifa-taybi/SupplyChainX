package com.example.supplyChainXProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/test-log")
    public String testLog() {
        logger.info("Test de log INFO");
        logger.error("Test de log ERROR");
        return "Logs générés !";
    }
}
