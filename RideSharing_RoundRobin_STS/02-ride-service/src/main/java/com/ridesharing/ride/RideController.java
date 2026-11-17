package com.ridesharing.ride;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RideController {
    @Value("${server.port}")
    private String port;

    @GetMapping({"/rides", "/rides/"})
    public String getRides() {
        return "Ride Service Response from Port: " + port;
    }
}
