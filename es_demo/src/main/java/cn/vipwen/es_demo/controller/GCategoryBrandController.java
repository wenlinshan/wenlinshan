package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.model.GCategoryBrand;
import cn.vipwen.es_demo.service.GCategoryBrandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* (g_category_brand)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/g_category_brand")
public class GCategoryBrandController {
/**
* 服务对象
*/
@Resource
private GCategoryBrandService gCategoryBrandService;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public GCategoryBrand selectOne(Integer id) {
return gCategoryBrandService.getById(id);
}

}
