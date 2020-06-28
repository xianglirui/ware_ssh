<template>
  <div class="dashboard-container">
    <div v-if="role">
      <el-card>
        <el-row :gutter="30">
          <el-col :span="6">账户名：{{userInfo.nikename}}</el-col>
          <el-col :span="6">姓名：{{userInfo.name}}</el-col>
          <el-col :span="6">电话：{{userInfo.tel}}</el-col>
          <el-col :span="6">住址：{{userInfo.place}}</el-col>
        </el-row>
      </el-card>
      <el-tag type="warning" style="margin-top: 50px">我的库存</el-tag>
      <div style="margin-top: 20px">
        <el-table
          :data="tableData"
          stripe
          border
          style="width: 100%">
          <el-table-column
            type="index"
            width="80">
          </el-table-column>
          <el-table-column
            prop="oid"
            label="单据号"
            width="200">
          </el-table-column>
          <el-table-column
            prop="inTime"
            label="入库时间"
            width="200">
          </el-table-column>
          <el-table-column
            prop="man"
            label="负责人"
            width="180">
          </el-table-column>
          <el-table-column
            prop="num"
            label="数量"
            width="160">
          </el-table-column>
          <el-table-column
            prop="name"
            label="材料名"
            width="200">
          </el-table-column>
          <el-table-column
            label="单价">
            <template slot-scope="scope">
              <span>￥</span>
              <span style="margin-left: 10px">{{ scope.row.price }}</span>
            </template>
          </el-table-column>

          <el-table-column
            label="总价">
            <template slot-scope="scope">
              <span>￥</span>
              <span style="margin-left: 10px">{{ scope.row.price*scope.row.num }}</span>
            </template>
          </el-table-column>
        </el-table>
        <div style="margin-top: 30px"></div>
        <el-button type="primary" round plain style="margin-right: 20px" @click="$router.push('/enter/index')">详细信息</el-button>
        <el-button type="primary" round plain icon="el-icon-shopping-cart-full" @click="$router.push('/purchase/index')">去采购</el-button>
      </div>
    </div>

    <div v-if="!role">
      <div class="dashboard-text">欢迎访问，管理员</div>
      <el-row>
        <el-col style="margin-top:120px;margin-bottom: 30px"><el-tag>选择你的操作</el-tag></el-col>
        <el-col :span="6"><el-button type="info" icon="el-icon-s-custom" plain @click="$router.push('/form/index')">用户信息</el-button></el-col>
        <el-col :span="6"><el-button type="info" icon="el-icon-circle-plus" plain @click="$router.push('/enter/index')">入库管理</el-button></el-col>
        <el-col :span="6"><el-button type="info" icon="el-icon-remove" plain @click="$router.push('/out/index')">出库管理</el-button></el-col>
        <el-col :span="6"><el-button type="info" icon="el-icon-s-order" plain @click="$router.push('/purchase/index')">采购审批</el-button></el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import {getUser, getToken} from "../../utils/auth";
import {getUserInfo} from "../../api/user";
import request from "../../utils/request";
import {findInWareByUid, getPurchaseListByUid} from "../../api/table";

export default {
  name: 'Dashboard',
  computed: {
    // ...mapGetters([
    //   'name'
    // ])
  },
  created() {
    if(this.role){
      getUserInfo(getUser()).then(res=>{
        let user = res.data.user
        if(user.name===null){
          alert("请完善您的个人信息")
          this.$router.push('/form/index')
        }
        this.userInfo.name = user.name
        this.userInfo.nikename = user.nikename
        this.userInfo.tel = user.tel
        this.userInfo.place = user.place
      })


      let data = new FormData()
      data.append("username",getUser())
      findInWareByUid(data).then(res=>{
        this.tableData = res.data.list
        let kong = this.tableData.filter(v=>{
          return v.num===0
        })
        if(this.tableData.length===0 || kong.length!==0){
          alert("材料不足")
        }
      })
    }else {

    }

  },
  data() {
    return {
      userInfo:{
        nikename:'',
        name:'',
        tel:'',
        place:''
      },
      role:getToken()==="user",
      tableData: []
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
