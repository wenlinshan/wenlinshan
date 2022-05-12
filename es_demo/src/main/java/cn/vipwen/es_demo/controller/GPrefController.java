package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.model.GPref;
import cn.vipwen.es_demo.service.GPrefService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* (g_pref)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/g_pref")
public class GPrefController {
/**
* 服务对象
*/
@Resource
private GPrefService gPrefService;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public GPref selectOne(Long id) {
return gPrefService.getById(id);
}

}
