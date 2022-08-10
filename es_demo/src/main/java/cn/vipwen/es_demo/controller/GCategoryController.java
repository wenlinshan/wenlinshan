package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.model.GCategory;
import cn.vipwen.es_demo.service.GCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* (g_category)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/g_category")
public class GCategoryController {
/**
* 服务对象
*/
@Resource
private GCategoryService gCategoryService;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public GCategory selectOne(Integer id) {
return gCategoryService.getById(id);
}

}
