package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.entity.db.DictData;
import com.zhilianhua.ssicp.entity.request.DictTypeParams;
import com.zhilianhua.ssicp.entity.response.base.PageResultEntity;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.DictDataService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 代忠明
 * @description 字典数据表
 * @date 2023-06-27
 */
@RestController
@Privilege("字典数据")
@RequestMapping(value = "/dictDatas")
public class DictDataController {
    @Autowired
    private DictDataService dictDataService;


    /**
     * 新增
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @PostMapping("")
    @Privilege(value = "新增字典数据")
    public ResponseEntity insert(@Valid @RequestBody DictData dictData) {
        dictDataService.insert(dictData);
        return ResponseEntityUtils.success();
    }

    /**
     * 刪除
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @DeleteMapping("/{id}")
    @Privilege(value = "删除字典数据")
    public ResponseEntity delete(@PathVariable Integer id) {
        dictDataService.delete(id);
        return ResponseEntityUtils.success();
    }

    /**
     * 更新
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @PutMapping("/{id}")
    @Privilege(value = "修改字典数据")
    public ResponseEntity update(@Valid @RequestBody DictData dictData) {
        dictDataService.update(dictData);
        return ResponseEntityUtils.success();
    }

    /**
     * 查询 根据主键 id 查询
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @GetMapping("/{id}")
    @Privilege(value = "查询字典数据详细")
    public ResponseEntity load(@PathVariable Integer id) {
        DictData load = dictDataService.load(id);
        return ResponseEntityUtils.success(load);
    }

    /**
     * 查询 分页查询
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @GetMapping("/pageList")
    @Privilege(value = "分页查询字典数据")
    public ResponseEntity pageList(DictTypeParams dictTypeParams) {
        PageResultEntity pageEntity = dictDataService.pageList(dictTypeParams);
        return ResponseEntityUtils.success(pageEntity);

    }

    /**
     * 查询 字典数据
     *
     * @author 代忠明
     * @date 2023/06/27
     **/
    @GetMapping("/listAll")
    @Privilege(value = "根据类型查询字典数据")
    public ResponseEntity listAll(String[] typecodes) {
        Map listAll = dictDataService.listAll(Arrays.asList(typecodes));
        return ResponseEntityUtils.success(listAll);
    }

}