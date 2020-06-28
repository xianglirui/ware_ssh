import request from '@/utils/request'

export function savePurchase(data) {
  return request({
    url:'/savePurchase',
    method: 'post',
    data
  })
}


export function getPurchaseListByUid(data) {
  return request({
    url:'/findByListByUid',
    method: 'post',
    data
  })
}

export function getPurchaseList() {
  return request({
    url:'/findPurchaseByList',
    method: 'post',
  })
}

export function updatePurchaseAgree(data) {
  return request({
    url:'/updatePurchaseIfAgree',
    method: 'post',
    data
  })
}

export function updatePurchaseRefuse(data) {
  return request({
    url:'/updatePurchaseIfRefuse',
    method: 'post',
    data
  })
}

export function findInWareByUid(data) {
  return request({
    url:'/findInWareByUid',
    method: 'post',
    data
  })
}


export function findInWare() {
  return request({
    url:'/findInWare',
    method: 'post',
  })
}


export function addInWare(data) {
  return request({
    url:'/addInWare',
    method: 'post',
    data
  })
}

export function updateInWare(data) {
  return request({
    url:'/updateInWare',
    method: 'post',
    data
  })
}

export function deleteInWare(data) {
  return request({
    url:'/deleteInWare',
    method: 'post',
    data
  })
}



export function findOutWareByUid(data) {
  return request({
    url:'/findOutWareByUid',
    method: 'post',
    data
  })
}


export function findOutWare() {
  return request({
    url:'/findOutWare',
    method: 'post',
  })
}


export function addOutWare(data) {
  return request({
    url:'/addOutWare',
    method: 'post',
    data
  })
}

export function updateOutWare(data) {
  return request({
    url:'/updateOutWare',
    method: 'post',
    data
  })
}

export function deleteOutWare(data) {
  return request({
    url:'/deleteOutWare',
    method: 'post',
    data
  })
}
