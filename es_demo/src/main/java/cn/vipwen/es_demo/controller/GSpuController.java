package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.model.GSpu;
import cn.vipwen.es_demo.service.GSpuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (g_spu)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/g_spu")
public class GSpuController {
    /**
     * 服务对象
     */
    @Resource
    private GSpuService gSpuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GSpu selectOne(Integer id) {
        return null;
    }

}
