<template>
  <div class="app-container">
    <el-card shadow="hover">
      <el-row>
        <el-col :span="17" style="margin-bottom: 10px"><el-tag effect="plain" style="margin-bottom: 5px">入库信息管理</el-tag></el-col>
        <el-col :span="4"><el-input v-model="search" size="mini" placeholder="输入单据号搜索"></el-input></el-col>
        <el-col :span="1" style="margin-left: 0.5%"><el-button size="mini"  @click="handleSearch()">搜索</el-button></el-col>
        <el-col :span="1" style="margin-left: 3%" v-if="!role"><el-button size="mini" type="danger" @click="dialogFormVisible = true">新增</el-button></el-col>
      </el-row>
      <el-table :data="tableData" border stripe max-height="500" style="width: 100%"  highlight-current-row @current-change="handleCurrentChange" ref="singleTable">
        <el-table-column type="index" width="50"></el-table-column>
        <el-table-column prop="oid" label="单据号"></el-table-column>
        <el-table-column prop="inTime" label="入库时间"></el-table-column>
        <el-table-column prop="man" label="负责人"></el-table-column>
        <el-table-column prop="price" label="单价"></el-table-column>
        <el-table-column prop="note" label="备注"></el-table-column>
        <el-table-column prop="detail" label="明细"></el-table-column>
        <el-table-column label="操作" v-if="!role">
          <template slot-scope="scope">
            <el-button type="primary" size="mini"  @click="editUsers(scope.$index,scope.row)">修改</el-button>
            <el-button type="info" size="mini" @click="handleDelete(scope.$index,scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="新增入库材料信息" :visible.sync="dialogFormVisible">
      <el-form :model="form" style="width: 60%;position: relative;left: 15%">
        <el-form-item label="选择材料" label-width="120px">
          <el-select v-model="form.value" clearable placeholder="请选择" style="width: 100%">
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
          <el-input-number v-model="form.num" :min="1" label="描述文字" style="width: 100%"></el-input-number>
        </el-form-item>
        <el-form-item label="负责人" label-width="120px">
          <el-input v-model="form.man" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="备注" label-width="120px">
          <el-input type="textarea" v-model="form.note" maxlength="100" show-word-limit :autosize="{ minRows: 3}"></el-input>
        </el-form-item>
        <el-form-item label="明细" label-width="120px">
          <el-input type="textarea" v-model="form.detail" maxlength="100" show-word-limit :autosize="{ minRows: 3}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addWAre">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改入库材料信息" :visible.sync="dialogVisible">
      <el-form :model="editUser" style="width: 60%;position: relative;left: 15%">
        <el-form-item label="负责人" label-width="120px">
          <el-input v-model="editUser.man" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="单价" label-width="120px">
          <el-input v-model="editUser.price" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="备注" label-width="120px">
          <el-input type="textarea" v-model="editUser.note" autocomplete="off" maxlength="100" show-word-limit :autosize="{ minRows: 3}"></el-input>
        </el-form-item>
        <el-form-item label="明细" label-width="120px">
          <el-input type="textarea" v-model="editUser.detail" autocomplete="off" maxlength="100" show-word-limit :autosize="{ minRows: 3}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editWare">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {getToken, getUser} from "../../utils/auth";
  import {addInWare, deleteInWare, findInWare, findInWareByUid, updateInWare} from "../../api/table";

  export default {
    name: "Enter",
    data() {
      return {
        search:'',
        editUser:{},
        currentRow: null,
        dialogVisible: false,
        dialogFormVisible: false,
        form: {
          oid :'',
          enter_time: '',
          man: '',
          name:'',
          note: '',
          detail: '',
          num:'',
          value:''
        },
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
        role:getToken()==="user",
        tableData: [],
      }
    },
    created() {
      if(this.role){
        let data = new FormData();
        data.append("username",getUser());
        findInWareByUid(data).then(res=>{
          this.tableData = res.data.list
        })
      }else {
        findInWare().then(res=>{
          console.log(res.data.list)
          this.tableData = res.data.list
        })
      }
    },
    methods:{
      handleSearch(){
        const i = this.tableData.filter(v=>{
          return this.search===v.oid
        })
        this.$refs.singleTable.setCurrentRow(i[0]);
      },
      handleCurrentChange(val) {
        this.currentRow = val;
      },
      editUsers(index,item){
        this.editUser = item
        this.dialogVisible = true
      },
      handleDelete(index,item){
        let data = new FormData();
        data.append("note",item.note)
        data.append("man",item.man)
        data.append("detail",item.detail)
        data.append("num",item.num)
        data.append("name",item.name)
        data.append("price",item.price)
        data.append("inTime",item.inTime)
        data.append("oid",item.oid)
        data.append("uid",item.uid)
        data.append("id",item.id)
        deleteInWare(data).then(res=>{
          if(res.data.success){
            this.$message.success('删除成功')
          }
        })
      },
      addWAre(){
        let vl = this.options.filter(v=>{
          return v.value===this.form.value
        })
        let data = new FormData();
        data.append("note",this.form.note)
        data.append("man",this.form.man)
        data.append("detail",this.form.detail)
        data.append("num",this.form.num)
        data.append("name",vl[0].label)
        data.append("price",vl[0].value)
        data.append("username",getUser())

        addInWare(data).then(res=>{
          if(res.data.success){
            this.$message.success('增加成功')
          }
          if(res.data.error){
            this.$message.error('请输入正确的负责人姓名')
          }
        })
      },
      editWare(){
        let data = new FormData();
        data.append("note",this.editUser.note)
        data.append("man",this.editUser.man)
        data.append("detail",this.editUser.detail)
        data.append("num",this.editUser.num)
        data.append("name",this.editUser.name)
        data.append("price",this.editUser.price)
        data.append("inTime",this.editUser.inTime)
        data.append("oid",this.editUser.oid)
        data.append("uid",this.editUser.uid)
        data.append("id",this.editUser.id)
        updateInWare(data).then(res=>{
          if(res.data.success){
            this.$message.success('修改成功')
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
