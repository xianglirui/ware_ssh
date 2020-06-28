<template>
  <div class="app-container">
    <div v-if="role">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="您的姓名">
          <el-input v-model="form.name" placeholder="姓名" />
        </el-form-item>
        <el-form-item label="您的性别">
          <el-radio v-model="form.sex" label="男">男</el-radio>
          <el-radio v-model="form.sex" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="您的住址">
          <el-input v-model="form.place" placeholder="住址" style="width: 100%;" />
        </el-form-item>
        <el-form-item label="您的年龄">
          <el-input v-model="form.age" placeholder="年龄" style="width: 100%;" />
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="form.tel" placeholder="手机号" style="width: 100%;" />
        </el-form-item>
        <el-form-item label="账户名">
          <el-input v-model="form.nikename" placeholder="账户名" style="width: 100%;" disabled/>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" placeholder="密码" style="width: 100%;" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交</el-button>
          <el-button @click="onCancel">取消</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div v-if="!role">
      <el-card shadow="hover">
        <el-row>
          <el-col :span="17" style="margin-bottom: 10px"><el-tag effect="plain" style="margin-bottom: 5px">用户信息管理</el-tag></el-col>
          <el-col :span="4"><el-input v-model="search" size="mini" placeholder="输入用户姓名搜索"></el-input></el-col>
          <el-col :span="1" style="margin-left: 0.5%"><el-button size="mini" @click="handleSearch">搜索</el-button></el-col>
          <el-col :span="1" style="margin-left: 3%"><el-button size="mini" type="danger" @click="dialogFormVisible = true">新增</el-button></el-col>
        </el-row>
        <el-table :data="tableData" border stripe max-height="500" style="width: 100%" highlight-current-row @current-change="handleCurrentChange" ref="singleTable">
          <el-table-column type="index" width="50"></el-table-column>
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="sex" label="性别" width="80"></el-table-column>
          <el-table-column prop="age" label="年龄" width="80"></el-table-column>
          <el-table-column prop="place" label="住址"></el-table-column>
          <el-table-column prop="tel" label="电话"></el-table-column>
          <el-table-column prop="nikename" label="登录账号"></el-table-column>
          <el-table-column prop="password" label="登录密码"></el-table-column>
          <el-table-column label="权限">
            <template slot-scope="scope">
              <span v-if="scope.row.role===0">用户</span>
              <span v-else>管理员</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="170">
            <template slot-scope="scope">
              <div><el-button type="primary" size="mini"  @click="editUsers(scope.$index,scope.row)">修改</el-button>
                <el-button type="info" size="mini" @click="handleDelete(scope.$index,scope.row)">删除</el-button></div>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
      <el-dialog title="新增用户信息" :visible.sync="dialogFormVisible">
        <el-form :model="form1" style="width: 60%;position: relative;left: 15%">
          <el-form-item label="姓名" label-width="120px">
            <el-input v-model="form1.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" label-width="120px">
            <el-input v-model="form1.sex" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="年龄" label-width="120px">
            <el-input v-model="form1.age" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="住址" label-width="120px">
            <el-input v-model="form1.place" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话" label-width="120px">
            <el-input v-model="form1.tel" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="账户" label-width="120px">
            <el-input v-model="form1.nikename" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="120px">
            <el-input v-model="form1.password" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addUser">确 定</el-button>
        </div>
      </el-dialog>
      <el-dialog title="修改用户信息" :visible.sync="dialogVisible">
        <el-form :model="editUser" style="width: 60%;position: relative;left: 15%">
          <el-form-item label="姓名" label-width="120px">
            <el-input v-model="editUser.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" label-width="120px">
            <el-input v-model="editUser.sex" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="年龄" label-width="120px">
            <el-input v-model="editUser.age" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="住址" label-width="120px">
            <el-input v-model="editUser.place" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话" label-width="120px">
            <el-input v-model="editUser.tel" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="账户" label-width="120px">
            <el-input v-model="editUser.nikename" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="120px">
            <el-input v-model="editUser.password" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="edit">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import {deleteUser, getUserInfo, getUserList, register, update} from "../../api/user";
  import {getToken, getUser} from "../../utils/auth";

export default {
  data() {
    return {
      form: {
        name: '',
        sex: '男',
        nikename: '',
        tel: '',
        password: '',
        place: '',
        age:'',
      },
      search:'',
      tableData: [],
      role:getToken()==="user",
      editUser:{},
      currentRow: null,
      dialogVisible: false,
      dialogFormVisible: false,
      form1: {
        name: '',
        sex: '',
        nikename: '',
        tel: '',
        password: '',
        place: '',
        age:''
      },
    }
  },
  created() {
    if(this.role){
      getUserInfo(getUser()).then(res=>{
        let user = res.data.user
        this.form.name = user.name
        this.form.nikename = user.nikename
        this.form.tel = user.tel
        this.form.place = user.place
        this.form.age = user.age
        this.form.password = user.password
      })
    }else {
      getUserList().then(res=>{
        this.tableData = res.data.list
        console.log(this.tableData)
      })
    }
  },
  methods: {
    onSubmit() {
      let data1 = new FormData();
      data1.append('nikename',this.form.nikename);
      data1.append('password',this.form.password);
      data1.append('tel',this.form.tel);
      data1.append('sex',this.form.sex);
      data1.append('place',this.form.place);
      data1.append('age',this.form.age);
      data1.append('name',this.form.name);
      data1.append('role',0+"");
      update(data1).then(res=>{
        if(res.data.success){
          this.$message.success('修改成功')
        }
      })
    },
    onCancel() {
      this.form = {}
    },
    handleSearch(){
      const i = this.tableData.filter(v=>{
        return this.search===v.name
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
    edit(){
      let data1 = new FormData();
      data1.append('nikename',this.editUser.nikename);
      data1.append('password',this.editUser.password);
      data1.append('tel',this.editUser.tel);
      data1.append('sex',this.editUser.sex);
      data1.append('place',this.editUser.place);
      data1.append('age',this.editUser.age);
      data1.append('name',this.editUser.name);
      data1.append('role',0+"");
      update(data1).then(res=>{
        if(res.data.success){
          this.$message.success('修改成功')
        }
      })
    },
    addUser(){
      let data1 = new FormData();
      data1.append('nikename',this.form1.nikename);
      data1.append('password',this.form1.password);
      data1.append('tel',this.form1.tel);
      data1.append('sex',this.form1.sex);
      data1.append('place',this.form1.place);
      data1.append('age',this.form1.age);
      data1.append('name',this.form1.name);
      data1.append('role',0+"");
      register(data1).then(res=>{
        if(res.data.error){
          this.$message.warning("用户名已存在")
        }
        if(res.data.success){
          this.$message.success("添加成功")
        }
      })
    },
    handleDelete(index,item){
      let data1 = new FormData();
      data1.append('nikename',item.nikename);
      data1.append('password',item.password);
      data1.append('tel',item.tel);
      data1.append('sex',item.sex);
      data1.append('place',item.place);
      data1.append('age',item.age);
      data1.append('name',item.name);
      data1.append('role',item.role);
      deleteUser(data1).then(res=>{
        if(res.data.success){
          this.$message.success('删除成功')
        }
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

