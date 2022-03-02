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
 * @Description: 獴哥机构表
 * @Author: jeecg-boot
 * @Date:   2022-03-01
 * @Version: V1.0
 */
@Data
@TableName("mk_orgs")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mk_orgs对象", description="獴哥机构表")
public class MkOrgs implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**0表示未删除,1表示删除*/
	@Excel(name = "0表示未删除,1表示删除", width = 15)
    @ApiModelProperty(value = "0表示未删除,1表示删除")
    @TableLogic
    private java.lang.Integer delFlag;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
    private java.lang.String name;
	/**0南区1北区*/
	@Excel(name = "0南区1北区", width = 15, dicCode = "direction_type")
	@Dict(dicCode = "direction_type")
    @ApiModelProperty(value = "0南区1北区")
    private java.lang.Integer orientation;
	/**所在地区*/
	@Excel(name = "所在地区", width = 15)
    @ApiModelProperty(value = "所在地区")
    private java.lang.String region;
	/**缴费方式*/
	@Excel(name = "缴费方式", width = 15, dicCode = "pay_type")
	@Dict(dicCode = "pay_type")
    @ApiModelProperty(value = "缴费方式")
    private java.lang.Integer payType;
	/**短信是否我方发送*/
	@Excel(name = "短信是否我方发送", width = 15, dicCode = "is_open")
	@Dict(dicCode = "is_open")
    @ApiModelProperty(value = "短信是否我方发送")
    private java.lang.String izSendSms;
	/**服务时间*/
	@Excel(name = "服务时间", width = 15)
    @ApiModelProperty(value = "服务时间")
    private java.lang.String content;
}
