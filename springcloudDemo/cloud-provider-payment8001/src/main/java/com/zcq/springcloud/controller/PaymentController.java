package com.zcq.springcloud.controller;

import com.zcq.springcloud.entities.CommonResult;
import com.zcq.springcloud.entities.Payment;
import com.zcq.springcloud.serivce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService ps;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = ps.create(payment);
//        log.info("*****插入结果:"+result);
        System.out.println("*****插入结果:"+result);

        if (result>0){
            return new CommonResult(200,"新增成功,port:"+serverPort,result);
        }else{
            return new CommonResult(444,"插入失败,port:"+serverPort,null);
        }
    };

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = ps.getPaymentById(id);
//        log.info("*****查询结果:"+payment);

        if (payment != null){
            return new CommonResult(200,"查询成功,port:"+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应数据,port:"+serverPort+",查询Id:"+id,null);
        }
    }

    @GetMapping(value="/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element:services){
            System.out.println("**********************element: "+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances) {
            System.out.println(instance.getInstanceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri()+"\t"+instance.getMetadata());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        //暂停几秒
        try {
//            feign 默认1秒
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
