package org.jeecg.modules.mcrm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.mcrm.entity.MkOrgGoodSchedule;
import org.jeecg.modules.mcrm.service.IMkOrgGoodScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 獴哥商品排期表
 * @Author: jeecg-boot
 * @Date: 2022-03-01
 * @Version: V1.0
 */
@Api(tags = "獴哥商品排期表")
@RestController
@RequestMapping("/mcrm/mkOrgGoodSchedule")
@Slf4j
public class MkOrgGoodScheduleController extends JeecgController<MkOrgGoodSchedule, IMkOrgGoodScheduleService> {
    @Autowired
    private IMkOrgGoodScheduleService mkOrgGoodScheduleService;

    /**
     * 分页列表查询
     *
     * @param mkOrgGoodSchedule
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "獴哥商品排期表-分页列表查询")
    @ApiOperation(value = "獴哥商品排期表-分页列表查询", notes = "獴哥商品排期表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(MkOrgGoodSchedule mkOrgGoodSchedule,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<MkOrgGoodSchedule> queryWrapper = QueryGenerator.initQueryWrapper(mkOrgGoodSchedule, req.getParameterMap());
        Page<MkOrgGoodSchedule> page = new Page<MkOrgGoodSchedule>(pageNo, pageSize);
        IPage<MkOrgGoodSchedule> pageList = mkOrgGoodScheduleService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param mkOrgGoodSchedule
     * @return
     */
    @AutoLog(value = "獴哥商品排期表-添加")
    @ApiOperation(value = "獴哥商品排期表-添加", notes = "獴哥商品排期表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody MkOrgGoodSchedule mkOrgGoodSchedule) {
        mkOrgGoodScheduleService.save(mkOrgGoodSchedule);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param mkOrgGoodSchedule
     * @return
     */
    @AutoLog(value = "獴哥商品排期表-编辑")
    @ApiOperation(value = "獴哥商品排期表-编辑", notes = "獴哥商品排期表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody MkOrgGoodSchedule mkOrgGoodSchedule) {
        mkOrgGoodScheduleService.updateById(mkOrgGoodSchedule);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "獴哥商品排期表-通过id删除")
    @ApiOperation(value = "獴哥商品排期表-通过id删除", notes = "獴哥商品排期表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        mkOrgGoodScheduleService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "獴哥商品排期表-批量删除")
    @ApiOperation(value = "獴哥商品排期表-批量删除", notes = "獴哥商品排期表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.mkOrgGoodScheduleService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "獴哥商品排期表-通过id查询")
    @ApiOperation(value = "獴哥商品排期表-通过id查询", notes = "獴哥商品排期表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        MkOrgGoodSchedule mkOrgGoodSchedule = mkOrgGoodScheduleService.getById(id);
        if (mkOrgGoodSchedule == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(mkOrgGoodSchedule);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param mkOrgGoodSchedule
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MkOrgGoodSchedule mkOrgGoodSchedule) {
        return super.exportXls(request, mkOrgGoodSchedule, MkOrgGoodSchedule.class, "獴哥商品排期表");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, MkOrgGoodSchedule.class);
    }

}
