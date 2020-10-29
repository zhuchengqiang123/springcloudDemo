package com.zcq.springcloud.controller;

import com.zcq.springcloud.entities.CommonResult;
import com.zcq.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j//日志 log.info(".....")
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
//    public static final String PAYMENT_URL = "http://localhost:8001";  单机版

    @Resource
    private RestTemplate restTemplate;

    /**
     * 只能发Get请求,实质是post --> postForObject
     * /payment/create为服务端(8001)的mapping
     * @param payment
     * @return
     */
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
//        log.info("payment--->"+payment.toString());
        System.out.println("payment--->"+payment.toString());
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    /**
     * /consumer/payment 为 8001的mapping
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操做失败");
        }

    }
}
