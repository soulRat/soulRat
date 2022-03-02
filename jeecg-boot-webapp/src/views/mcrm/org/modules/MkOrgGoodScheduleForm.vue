<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="机构id" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="orgId">
              <j-dict-select-tag type="list" v-model="model.orgId" dictCode="mk_orgs,name,id" placeholder="请选择机构id" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="商品Id" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="goodId">
              <j-dict-select-tag type="list" v-model="model.goodId" dictCode="mk_goods,name,id" placeholder="请选择商品Id" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="scheduleDate">
              <j-date placeholder="请选择日期" v-model="model.scheduleDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="现货数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="spotNum">
              <a-input-number v-model="model.spotNum" placeholder="请输入现货数量" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="预售数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="presellNum">
              <a-input-number v-model="model.presellNum" placeholder="请输入预售数量" style="width: 100%" />
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
    name: 'MkOrgGoodScheduleForm',
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
           spotNum: [
              { required: false},
              { pattern: /^-?\d+\.?\d*$/, message: '请输入数字!'},
           ],
           presellNum: [
              { required: false},
              { pattern: /^-?\d+\.?\d*$/, message: '请输入数字!'},
           ],
        },
        url: {
          add: "/mcrm/mkOrgGoodSchedule/add",
          edit: "/mcrm/mkOrgGoodSchedule/edit",
          queryById: "/mcrm/mkOrgGoodSchedule/queryById"
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