package com.ridesharing.rider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiderController {
    @Value("${server.port}")
    private String port;

    @GetMapping({"/riders", "/riders/"})
    public String getRiders() {
        return "Rider Service Running on Port: " + port;
    }
}
