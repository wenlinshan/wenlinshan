package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.model.GPara;
import cn.vipwen.es_demo.service.GParaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* (g_para)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/g_para")
public class GParaController {
/**
* 服务对象
*/
@Resource
private GParaService gParaService;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public GPara selectOne(Integer id) {
return null;
}

}
