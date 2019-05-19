package com.chris.chriseurekaclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by 廖师兄
 * 2018-03-18 20:23
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

	//降级  当http://localhost:8003/hystrixTestServer 无法访问时触发降级
	@HystrixCommand(fallbackMethod = "fallback")
	@GetMapping("/hystrixTest1")
	public String hystrixTest1() {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://localhost:8003/hystrixTestServer", String.class);
		return result;
	}
	//异常触发降级
	@HystrixCommand
	@GetMapping("/hystrixTest2")
	public String hystrixTest2() {
		throw new RuntimeException("发送异常了");
	}

	//超时设置 (超时 触发降级)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),//设置超时时间3000 毫秒
	})
	@GetMapping("/hystrixTest3")
	public String hystrixTest3() {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://localhost:8003/hystrixTestServer", String.class);
		return result;
	}

	//熔断 +超时+降级
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),//设置超时时间3000 毫秒
			@HystrixProperty(name = "circuitBreaker.enabled", value = "true"),  				//设置熔断
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),	//请求数达到后才计算
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗  （休眠间隔）
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),	//错误率
	})
	@GetMapping("/hystrixTest4")
	public String hystrixTest4() {
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://localhost:8003/hystrixTestServer", String.class);
		return result;
	}

	private String fallback() {
		return "太拥挤了, 请稍后再试~~";
	}
	private String defaultFallback() {
		return "默认提示：太拥挤了, 请稍后再试~~";
	}
}
