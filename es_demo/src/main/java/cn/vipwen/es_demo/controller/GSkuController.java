package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.model.GSku;
import cn.vipwen.es_demo.service.GSkuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * (g_sku)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/g_sku")
public class GSkuController {
    /**
     * 服务对象
     */
    @Resource
    private GSkuService gSkuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GSku selectOne(Integer id) {
        return null;
    }

    @PostMapping("bach")
    public String importAll(int pageNum) throws IOException {
        return gSkuService.importSku(pageNum);
    }

}
