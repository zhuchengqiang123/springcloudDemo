package com.zcq.springcloud.serivce.impl;

import com.zcq.springcloud.dao.PaymentDao;
import com.zcq.springcloud.entities.Payment;
import com.zcq.springcloud.serivce.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentSerivceImpl implements PaymentService {
    @Resource
    private PaymentDao pd;
    @Override
    public int create(Payment payment) {
        return pd.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return pd.getPaymentById(id);
    }
}
