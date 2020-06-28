import request from '@/utils/request'
import {removeToken, removeUser} from "@/utils/auth";
import router from "@/router";

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function register(data) {
  return request({
    url: '/saveUser',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/vue-admin-template/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  removeToken()
  removeUser()
  router.push('/login')
}

export function getUserInfo(nikename) {
  return request({
    url:'/userInfo',
    method: 'get',
    params: { nikename }
  })
}


export function getUserList() {
  return request({
    url:'/userList',
    method: 'get',
  })
}


export function update(data) {
  return request({
    url:'/editUser',
    method: 'post',
    data
  })
}

export function deleteUser(data) {
  return request({
    url:'/deleteUser',
    method: 'post',
    data
  })
}

