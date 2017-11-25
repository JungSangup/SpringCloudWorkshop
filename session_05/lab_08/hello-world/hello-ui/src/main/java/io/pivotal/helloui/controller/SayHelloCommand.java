package io.pivotal.helloui.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SayHelloCommand {
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

    

    @HystrixCommand(fallbackMethod = "sayHelloFallback")
    public String sayHello(String toWho) {
        try{
            return restTemplate.getForObject("http://hello-world-server/hello?name={name}", String.class, toWho);
        	//return restTemplate.getForObject("hello-world-server/hello?name={name}", String.class, toWho);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String sayHelloFallback(String toWho) {
        return String.format("Error, can't say hello to %s", toWho);
    }

}
