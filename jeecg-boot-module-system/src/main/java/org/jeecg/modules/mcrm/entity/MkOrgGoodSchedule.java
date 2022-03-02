package org.jeecg.modules.mcrm.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 獴哥商品排期表
 * @Author: jeecg-boot
 * @Date:   2022-03-01
 * @Version: V1.0
 */
@Data
@TableName("mk_org_good_schedule")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mk_org_good_schedule对象", description="獴哥商品排期表")
public class MkOrgGoodSchedule implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**0表示未删除,1表示删除*/
	@Excel(name = "0表示未删除,1表示删除", width = 15)
    @ApiModelProperty(value = "0表示未删除,1表示删除")
    @TableLogic
    private Integer delFlag;
	/**机构id*/
	@Excel(name = "机构id", width = 15, dictTable = "mk_orgs", dicText = "name", dicCode = "id")
	@Dict(dictTable = "mk_orgs", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "机构id")
    private String orgId;
	/**商品Id*/
	@Excel(name = "商品Id", width = 15, dictTable = "mk_goods", dicText = "name", dicCode = "id")
	@Dict(dictTable = "mk_goods", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "商品Id")
    private String goodId;
	/**日期*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "日期")
    private Date scheduleDate;
	/**现货数量*/
	@Excel(name = "现货数量", width = 15)
    @ApiModelProperty(value = "现货数量")
    private Integer spotNum;
	/**预售数量*/
	@Excel(name = "预售数量", width = 15)
    @ApiModelProperty(value = "预售数量")
    private Integer presellNum;
}
