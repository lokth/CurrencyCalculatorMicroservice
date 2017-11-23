package com.thisistime.microservices.limitsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thisistime.microservices.limitsservice.bean.LimitsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitsConfiguration retreiveLimitsFromConfiguration(){
        return new LimitsConfiguration(configuration.getMaximum(),configuration.getMinimum());

    }


    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallbackRetreiveConfiguration")
    public LimitsConfiguration retreiveConfiguration(){
        throw new RuntimeException("Not available");
    }

    public LimitsConfiguration fallbackRetreiveConfiguration(){
        return new LimitsConfiguration(999, 9);
    }
}
