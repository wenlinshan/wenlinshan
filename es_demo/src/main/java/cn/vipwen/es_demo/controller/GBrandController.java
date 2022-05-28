package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.model.GBrand;
import cn.vipwen.es_demo.service.GBrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * (g_brand)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/brand")
public class GBrandController {
    /**
     * 服务对象
     */
    @Resource
    private GBrandService gBrandService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GBrand selectOne(Integer id) {
        return gBrandService.getById(id);
    }

    @PostMapping("save")
    public Boolean saveOne(@RequestBody GBrand gBrand) throws SQLException {
        return gBrandService.saveBrand(gBrand);
    }

}
