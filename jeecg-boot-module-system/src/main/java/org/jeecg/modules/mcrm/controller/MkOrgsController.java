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
import org.jeecg.modules.mcrm.entity.MkOrgs;
import org.jeecg.modules.mcrm.service.IMkOrgsService;

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
 * @Description: 獴哥机构表
 * @Author: jeecg-boot
 * @Date:   2022-03-01
 * @Version: V1.0
 */
@Api(tags="獴哥机构表")
@RestController
@RequestMapping("/mcrm/mkOrgs")
@Slf4j
public class MkOrgsController extends JeecgController<MkOrgs, IMkOrgsService> {
	@Autowired
	private IMkOrgsService mkOrgsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mkOrgs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "獴哥机构表-分页列表查询")
	@ApiOperation(value="獴哥机构表-分页列表查询", notes="獴哥机构表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MkOrgs mkOrgs,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MkOrgs> queryWrapper = QueryGenerator.initQueryWrapper(mkOrgs, req.getParameterMap());
		Page<MkOrgs> page = new Page<MkOrgs>(pageNo, pageSize);
		IPage<MkOrgs> pageList = mkOrgsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mkOrgs
	 * @return
	 */
	@AutoLog(value = "獴哥机构表-添加")
	@ApiOperation(value="獴哥机构表-添加", notes="獴哥机构表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MkOrgs mkOrgs) {
		mkOrgsService.save(mkOrgs);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mkOrgs
	 * @return
	 */
	@AutoLog(value = "獴哥机构表-编辑")
	@ApiOperation(value="獴哥机构表-编辑", notes="獴哥机构表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MkOrgs mkOrgs) {
		mkOrgsService.updateById(mkOrgs);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "獴哥机构表-通过id删除")
	@ApiOperation(value="獴哥机构表-通过id删除", notes="獴哥机构表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mkOrgsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "獴哥机构表-批量删除")
	@ApiOperation(value="獴哥机构表-批量删除", notes="獴哥机构表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mkOrgsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "獴哥机构表-通过id查询")
	@ApiOperation(value="獴哥机构表-通过id查询", notes="獴哥机构表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MkOrgs mkOrgs = mkOrgsService.getById(id);
		if(mkOrgs==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mkOrgs);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mkOrgs
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MkOrgs mkOrgs) {
        return super.exportXls(request, mkOrgs, MkOrgs.class, "獴哥机构表");
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
        return super.importExcel(request, response, MkOrgs.class);
    }

}
