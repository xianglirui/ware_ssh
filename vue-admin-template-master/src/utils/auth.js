import Cookies from 'js-cookie'

const TokenKey = 'user_admin_token'
const username = "username"

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function setUser(token) {
  return Cookies.set(username,token)
}

export function getUser() {
  return Cookies.get(username)
}

export function removeUser() {
  return Cookies.remove(username)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}


