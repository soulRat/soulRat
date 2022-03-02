package org.jeecg.modules.mcrm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.mcrm.entity.MkGoods;
import org.jeecg.modules.mcrm.service.IMkGoodsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 獴哥商品表
 * @Author: jeecg-boot
 * @Date:   2022-03-01
 * @Version: V1.0
 */
@Api(tags="獴哥商品表")
@RestController
@RequestMapping("/mcrm/mkGoods")
@Slf4j
public class MkGoodsController extends JeecgController<MkGoods, IMkGoodsService> {
	@Autowired
	private IMkGoodsService mkGoodsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mkGoods
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "獴哥商品表-分页列表查询")
	@ApiOperation(value="獴哥商品表-分页列表查询", notes="獴哥商品表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MkGoods mkGoods,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MkGoods> queryWrapper = QueryGenerator.initQueryWrapper(mkGoods, req.getParameterMap());
		Page<MkGoods> page = new Page<MkGoods>(pageNo, pageSize);
		IPage<MkGoods> pageList = mkGoodsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mkGoods
	 * @return
	 */
	@AutoLog(value = "獴哥商品表-添加")
	@ApiOperation(value="獴哥商品表-添加", notes="獴哥商品表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MkGoods mkGoods) {
		mkGoodsService.save(mkGoods);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mkGoods
	 * @return
	 */
	@AutoLog(value = "獴哥商品表-编辑")
	@ApiOperation(value="獴哥商品表-编辑", notes="獴哥商品表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MkGoods mkGoods) {
		mkGoodsService.updateById(mkGoods);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "獴哥商品表-通过id删除")
	@ApiOperation(value="獴哥商品表-通过id删除", notes="獴哥商品表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mkGoodsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "獴哥商品表-批量删除")
	@ApiOperation(value="獴哥商品表-批量删除", notes="獴哥商品表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mkGoodsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "獴哥商品表-通过id查询")
	@ApiOperation(value="獴哥商品表-通过id查询", notes="獴哥商品表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MkGoods mkGoods = mkGoodsService.getById(id);
		if(mkGoods==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mkGoods);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mkGoods
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MkGoods mkGoods) {
        return super.exportXls(request, mkGoods, MkGoods.class, "獴哥商品表");
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
        return super.importExcel(request, response, MkGoods.class);
    }

}
