## vue + ssh完成的企业原材料出入库管理系统



### 项目简介：

该项目是本学期的期末大作业，比较简单，但为了证明我会ssh，于是传了上来。

因为有别的事，所以三个期末大作业被拖到6月底，距离上交只有8天，还有三个项目作业，算作不到三天一个项目。好在以vue为前端框架的前后端分离开发我比较熟，于是我便采取前后端分离开发。ssh没学过，但是ssm和springboot我熟，所以把一个ssh项目搭建起来后，ssh的开发流程差不多就清楚了。

搭建一个ssh项目看了几篇博客，这篇是最完整的[SSH框架整合（IDEA版+Maven）](https://blog.csdn.net/h2503652646/article/details/104933654/)。

项目搭建起来后，选择在后端跨域，拦截器类[AjaxFilter.java](./warehouse/src/main/java/com/utils/AjaxFilter.java),然后在web.xml添加filter配置。除此之外，struct.xml里一个action的格式必须是这样的：

```xml
<action name="saveUser" class="userAction" method="saveUser">
     <result name="register" type="json">
         <param name="root">map</param>
     </result>
</action>
<!--1. action标签的name是这一个action唯一标志，class是action要指向的action类，method是该action类的方法，也是后端的响应请求url
2. result标签的name属性是method方法所要return的字符串，返回结果类型为json，当然要添加struts2-json-plugin依赖，param标签格式只能这么写，map只要返回的hashmap的名字，这个在对应action类里。-->
```



前端套用的是[vue-admin-template](https://github.com/PanJiaChen/vue-admin-template)模板。u1s1，原作者的登录权限功能，token拦截器、vuex存储用户信息的那一套行云流水的代码我至今也没有百分百搞清楚，另外在后端配置全局拦截器和jwt功能也麻烦。最关键的是时间不够，所以我直接将用户权限信息保存在cookie里，前端路由跳转前查看cookie里的权限信息————虽然这种办法的安全性为负值，但是临时应付一下期末作业还是可以的。



#### 开发心得：

一个项目做完后，总的来看，ssh和springboot的快捷性不是一个数量级的，但是ssh的action（我用的是模板驱动）还有点意思，感觉还比较新鲜。

但是用ssh做前后端分离时，后端怎么接收前端传递过来的对象参数（除了用`request.getParameter("username")`这种老办法之外），这就是一个坑，坑了我好长时间，不过最后我知道了还有表单对象这个玩意儿。

```javascript
// 使用表单对象替代一般对象:{"username":"xlr"}
let data = new FormData();
data.append('username','xlr');
// console.log(data.get('username'))
login(data).then(res=>{
    console.log(res.data)
})
```





#### 项目启动

前端文件为`vue-admin-template-master`，后端为`warehouse`，vue和ssh工程启动没必要多说了，文件夹下还有一个`create.sql`文件，为创建表的sql。





