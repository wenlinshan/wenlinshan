package cn.vipwen.sharding.demo.service;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.vipwen.sharding.demo.domain.TOrder;
import cn.vipwen.sharding.demo.mapper.TOrderMapper;
/**
 *
 * @author wls
 * 
 */
@Service
public class TOrderService extends ServiceImpl<TOrderMapper, TOrder> {

}
