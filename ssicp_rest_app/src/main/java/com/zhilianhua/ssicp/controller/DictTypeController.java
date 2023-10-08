package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.DictType;
import com.zhilianhua.ssicp.entity.request.DictTypeParams;
import com.zhilianhua.ssicp.entity.response.DictVo;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.DictTypeService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 代忠明
 * @description 字典类型表
 * @date 2023-06-27
 */
@RestController
@Privilege("字典类型")
@RequestMapping(value = "/dictTypes")
public class DictTypeController {
    @Autowired
    private DictTypeService dictTypeService;


    /**
     * 新增
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @PostMapping("")
    @Privilege(value = "新增字典类型")
    public ResponseEntity insert(@Valid @RequestBody DictType dictType) {
        dictTypeService.insert(dictType);
        return ResponseEntityUtils.success();
    }

    /**
     * 刪除
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @DeleteMapping("/{code}")
    @Privilege(value = "删除字典类型")
    public ResponseEntity delete(@PathVariable String code) {
        dictTypeService.delete(code);
        return ResponseEntityUtils.success();
    }

    /**
     * 更新
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @PutMapping("/{code}")
    @Privilege(value = "修改字典类型")
    public ResponseEntity update(@Valid @RequestBody DictType dictType) {
        dictTypeService.update(dictType);
        return ResponseEntityUtils.success();
    }

    /**
     * 查询 根据主键查询
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @GetMapping("/{code}")
    @Privilege(value = "查询字典类型详细")
    public ResponseEntity load(@PathVariable String code) {
        DictType load = dictTypeService.load(code);
        return ResponseEntityUtils.success(load);
    }

    /**
     * 查询 分页查询
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @GetMapping("/pageList")
    @Privilege(value = "分页查询字典类型")
    public ResponseEntity<DictType> pageList(DictTypeParams dictTypeParams) {
        return ResponseEntityUtils.success(dictTypeService.pageList(dictTypeParams));
    }

    @GetMapping("/all")
    @Privilege(value = "查询全部字典数据")
    public ResponseEntity<List<DictVo>> findAll(){
        return ResponseEntityUtils.success(dictTypeService.findAll());
    }
}