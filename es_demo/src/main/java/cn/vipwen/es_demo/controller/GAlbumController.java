package cn.vipwen.es_demo.controller;

import cn.vipwen.es_demo.model.GAlbum;
import cn.vipwen.es_demo.service.GAlbumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* (g_album)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("/g_album")
public class GAlbumController {
/**
* 服务对象
*/
@Resource
private GAlbumService gAlbumService;

/**
* 通过主键查询单条数据
*
* @param id 主键
* @return 单条数据
*/
@GetMapping("selectOne")
public GAlbum selectOne(Integer id) {
return gAlbumService.getById(id);
}

}
