package com.liyp.springcloud.server;

import com.liyp.springcloud.entities.CommentResult;
import com.liyp.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public CommentResult<Payment> paymentSQL(Long id) {
        return new CommentResult<>(500, "兜底方法-----------PaymentFallbackService " + id);
    }
}
