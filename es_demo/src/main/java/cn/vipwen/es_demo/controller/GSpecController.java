package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.model.GSpec;
import cn.vipwen.es_demo.service.GSpecService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* (g_spec)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/g_spec")
public class GSpecController {
/**
* 服务对象
*/
@Resource
private GSpecService gSpecService;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public GSpec selectOne(Integer id) {
return gSpecService.getById(id);
}

}
