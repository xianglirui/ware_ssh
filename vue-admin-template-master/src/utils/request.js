import axios from 'axios'

const service = axios.create({
  baseURL: "http://localhost:8080/warehouse_war_exploded",
  timeout: 5000
})

export default service
