package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.model.GTemplate;
import cn.vipwen.es_demo.service.GTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (g_template)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/g_template")
public class GTemplateController {
    /**
     * 服务对象
     */
    @Resource
    private GTemplateService gTemplateService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GTemplate selectOne(Integer id) {
        return null;
    }

}
