package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /**
     * 正常访问,肯定OK
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + "paymentInfo_OK,id : " + id + " 哈哈";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="3000")//设置峰值超时时间3秒钟,sleep5秒会报错,走兜底方法
    })//添加@HystrixCommand 实现断路器功能,当service方法对应的服务发生异常的时候，会跳转到serviceFallback方法执行
    public String paymentInfo_TimeOut(Integer id) {
        int time = 1;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池 : " + Thread.currentThread().getName() + " paymentInfo_TimeOut,id: " + id + "呵呵 耗时  " + time + "  秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池 : " + Thread.currentThread().getName() + "线程超时,id: " + id + "呵呵 ";
    }
}
