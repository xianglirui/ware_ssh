<template>
  <div class="app-container">
    <div v-if="role">
      <el-tag type="warning" style="margin-bottom: 10px" stripe max-height="500">采购记录</el-tag>
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column type="index" width="80"></el-table-column>
        <el-table-column prop="name" label="材料名"></el-table-column>
        <el-table-column prop="man" label="负责人"></el-table-column>
        <el-table-column prop="time" label="采购时间"></el-table-column>
        <el-table-column prop="num" label="数量"></el-table-column>
        <el-table-column prop="price" label="单价"></el-table-column>
        <el-table-column prop="status" label="状态"></el-table-column>
      </el-table>

      <el-button round icon="el-icon-goods" plain type="primary" style="margin-top: 20px" @click="dialogVisible=true">采购</el-button>

      <el-dialog title="采购清单" :visible.sync="dialogVisible">
        <el-form :model="form1" style="width: 60%;position: relative;left: 15%">
          <el-form-item label="选择材料" label-width="120px">
            <el-select v-model="form1.value" clearable placeholder="请选择" style="width: 100%">
              <el-option
                v-for="item in options"
                :key="item.label"
                :label="item.label"
                :value="item.value">
                <span style="float: left">{{ item.label }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px;width: 60px">单价：{{ item.value }}</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="选择数量" label-width="120px">
            <el-input-number v-model="form1.num" :min="1" label="描述文字" style="width: 100%"></el-input-number>
          </el-form-item>
          <el-form-item label="负责人" label-width="120px">
            <el-input v-model="form1.man"></el-input>
          </el-form-item>
          <el-form-item label="备注" label-width="120px">
            <el-input type="textarea" v-model="form1.note" autocomplete="off" maxlength="100" show-word-limit :autosize="{ minRows: 3}"></el-input>
          </el-form-item>
          <el-form-item label="明细" label-width="120px">
            <el-input type="textarea" v-model="form1.detail" autocomplete="off" maxlength="100" show-word-limit :autosize="{ minRows: 3}"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="buy">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div v-if="!role">
      <el-tag type="warning" style="margin-bottom: 10px" stripe max-height="500">采购申请</el-tag>
      <el-table :data="tableData1" stripe border style="width: 100%">
        <el-table-column type="index" width="80"></el-table-column>
        <el-table-column prop="man" label="客户名"></el-table-column>
        <el-table-column prop="name" label="材料名"></el-table-column>
        <el-table-column prop="time" label="采购时间"></el-table-column>
        <el-table-column prop="num" label="数量"></el-table-column>
        <el-table-column prop="note" label="备注"></el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="success" size="mini"  @click="agree(scope.$index,scope.row)">同意</el-button>
            <el-button type="info" size="mini" @click="refuse(scope.$index,scope.row)">驳回</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
  import {getToken, getUser} from "../../utils/auth";
  import {
    getPurchaseList,
    getPurchaseListByUid,
    savePurchase,
    updatePurchaseAgree,
    updatePurchaseRefuse
  } from "../../api/table";

  export default {
    name: "Purchase",
    data(){
      return{
        dialogVisible: false,
        form1:{
          value:'',
          num:'',
          man:'',
          note:'',
          detail:'',
          price:''
        },
        tableData1:[],
        tableData:[],
        num:1,
        role:getToken()==="user",
        options: [{
          value: '78',
          label: '黄金糕'
        }, {
          value: '102',
          label: '双皮奶'
        }, {
          value: '69',
          label: '蚵仔煎'
        }, {
          value: '144',
          label: '龙须面'
        }, {
          value: '506',
          label: '北京烤鸭'
        }],
      }
    },
    created() {
      if(this.role){
        let data = new FormData()
        data.append("username",getUser())
        getPurchaseListByUid(data).then(res=>{
          this.tableData = res.data.list
        })
      }else {
        getPurchaseList().then(res=>{
          this.tableData1 = res.data.admin_list
        })
      }

    },
    methods:{
      buy(){
        if(this.form1.value==='' || this.form1.man===''){
          this.$message.warning("材料信息、负责人信息不能为空")
        }else {
          let vl = this.options.filter(v=>{
            return v.value===this.form1.value
          })
          let data = new FormData();
          data.append("note",this.form1.note)
          data.append("man",this.form1.man)
          data.append("detail",this.form1.detail)
          data.append("num",this.form1.num)
          data.append("name",vl[0].label)
          data.append("price",vl[0].value)
          data.append("username",getUser())

          savePurchase(data).then(res=>{
            if(res.data.success){
              this.$message.success('提交成功')
            }
          })
        }

      },
      agree(index,item){
        let data = new FormData();
        data.append("note",item.note)
        data.append("man",item.man)
        data.append("detail",item.detail)
        data.append("num",item.num)
        data.append("name",item.name)
        data.append("price",item.price)
        data.append("time",item.time)
        data.append("uid",item.uid)
        updatePurchaseAgree(data).then(res=>{
          if(res.data.success){
            this.$message.success('操作成功')
          }
        })
      },
      refuse(index,item){
        let data = new FormData();
        data.append("note",item.note)
        data.append("man",item.man)
        data.append("detail",item.detail)
        data.append("num",item.num)
        data.append("name",item.name)
        data.append("price",item.price)
        data.append("time",item.time)
        data.append("uid",item.uid)
        updatePurchaseRefuse(data).then(res=>{
          if(res.data.success){
            this.$message.success('操作成功')
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
