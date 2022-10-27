package com.wen.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.mapper.OrderMapper;
import com.wen.domain.Order;
import com.wen.service.OrderService;
/**
 *
 * @author wls
 * 
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

}
