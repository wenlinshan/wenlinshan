package com.example.spring.response.service;

import com.example.spring.response.domain.Order;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * @author wls
 * @date 2021/12/13 9:20
 * @desc TODO
 */
@Service
public class OrderService {

    public Order testOrder(@NonNull Long orderId, @NonNull Long userId){
        return new Order(orderId,userId.toString());
    }
}
