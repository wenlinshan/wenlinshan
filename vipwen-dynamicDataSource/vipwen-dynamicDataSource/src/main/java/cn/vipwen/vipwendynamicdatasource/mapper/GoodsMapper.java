package cn.vipwen.vipwendynamicdatasource.mapper;

import cn.vipwen.vipwendynamicdatasource.annotation.DbSource;
import cn.vipwen.vipwendynamicdatasource.constant.DBConstantEnum;
import cn.vipwen.vipwendynamicdatasource.domain.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


@DbSource(DBConstantEnum.SLAVE1)
public interface GoodsMapper extends BaseMapper<Goods> {
}