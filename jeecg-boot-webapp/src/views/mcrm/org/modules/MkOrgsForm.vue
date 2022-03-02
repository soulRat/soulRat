<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="机构名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入机构名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="0南区1北区" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="orientation">
              <j-dict-select-tag type="list" v-model="model.orientation" dictCode="direction_type" placeholder="请选择0南区1北区" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="所在地区" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="region">
             <j-area-linkage type="cascader" v-model="model.region" placeholder="请输入省市区"  />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="缴费方式" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="payType">
              <j-dict-select-tag type="list" v-model="model.payType" dictCode="pay_type" placeholder="请选择缴费方式" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="短信是否我方发送" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="izSendSms">
              <j-dict-select-tag type="radio" v-model="model.izSendSms" dictCode="is_open" placeholder="请选择短信是否我方发送" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="服务时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="content">
              <a-textarea v-model="model.content" rows="4" placeholder="请输入服务时间" />
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'MkOrgsForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/mcrm/mkOrgs/add",
          edit: "/mcrm/mkOrgs/edit",
          queryById: "/mcrm/mkOrgs/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>