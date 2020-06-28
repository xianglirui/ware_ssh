<template>
  <div class="login-container">
    <el-form ref="ruleForm2" :model="ruleForm2" status-icon :rules="rules2" class="login-form">

      <div class="title-container">
        <h3 class="title">注册</h3>
      </div>
      <el-form-item prop="nikename">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input v-model="ruleForm2.nikename" placeholder="用户名" />
      </el-form-item>
      <el-form-item prop="pass">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input v-model="ruleForm2.password" type="password" auto-complete="off" placeholder="密码" />
      </el-form-item>
      <el-form-item prop="checkPass">
        <span class="svg-container">
          <i class="el-icon-unlock" />
        </span>
        <el-input v-model="ruleForm2.checkPass" type="password" auto-complete="off" placeholder="确认密码" />
      </el-form-item>
      <el-form-item prop="tel">
        <span class="svg-container">
          <i class="el-icon-phone" />
        </span>
        <el-input v-model="ruleForm2.tel" placeholder="手机号" />
      </el-form-item>
      <el-button type="primary" style="width:100%;margin-bottom:30px;" @click="handleRegister">提交</el-button>
      <el-link style="color: white;position: relative;left: 85%" @click="$router.push('/login')">去登陆</el-link>
    </el-form>
  </div>
</template>

<script>
import {register} from "../../api/user";

export default {
  name: 'Index',
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else if(value.length < 6){
        callback(new Error('密码不得少于6位'))
      } else {
        if (this.ruleForm2.checkPass !== '') {
          this.$refs.ruleForm2.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.ruleForm2.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      ruleForm2: {
        password: '',
        checkPass: '',
        nikename: '',
        tel: ''
      },
      rules2: {
        password: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        nikename: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        tel: [
          { required: true, message: '请输入联系方式', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    handleRegister() {
      const fromData = this.ruleForm2
      this.$refs.ruleForm2.validate((valid) => {
        if (valid) {
          let data = new FormData();
          data.append('nikename',fromData.nikename);
          data.append('password',fromData.password);
          data.append('tel',fromData.tel);
          data.append('role',0+"");
          register(data).then(res=>{
            if(res.data.error){
              this.$message.warning(res.data.error)
              this.ruleForm2={}
            }
            if(res.data.success){
              this.$message.success(res.data.success)
            }
          });
        }
      })
    }
  }
}
</script>

<style lang="scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

  $bg:#283443;
  $light_gray:#fff;
  $cursor: #fff;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;

      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;

        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
</style>
<style lang="scss" scoped>
  $bg:#2d3a4b;
  $dark_gray:#889aa4;
  $light_gray:#eee;

  .login-container {
    min-height: 100%;
    width: 100%;
    background-color: $bg;
    overflow: hidden;

    .login-form {
      position: relative;
      top:-30px;
      width: 520px;
      max-width: 100%;
      padding: 160px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }

    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;
      position: relative;
      left: 65%;

      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
  }
</style>
