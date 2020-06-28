package com.action;

import com.domain.Inware;
import com.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.InWareService;
import com.service.UserService;
import com.utils.OID;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("InWareAction")
@Scope("prototype")
public class InWareAction extends ActionSupport implements ModelDriven<Inware> {
    @Resource
    private InWareService inWareService;
    @Resource
    private UserService userService;

    Map<String,Object> map = new HashMap<>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    private HttpServletRequest request = ServletActionContext.getRequest();

    //用于封装模型
    private Inware inware = new Inware();

    public String findInWareByUid(){
        System.out.println("收到用户查询入库信息请求");

        String username = request.getParameter("username");
        System.out.println("用户昵称:\n"+username);
        User user = userService.selectByNikeName(username);
        map.clear();
        List<Inware> inwares = inWareService.selectListByUid(user.getId());
        map.put("list",inwares);
        return "findInWareByUid";
    }

    public String findInWare(){
        System.out.println("收到管理员查询入库信息请求");

        map.clear();
        List<Inware> inwares = inWareService.selectList();
        map.put("list",inwares);
        return "findInWare";
    }

    public String addInWare(){
        System.out.println("收到管理员新增入库信息请求");
        map.clear();
        User user = userService.selectByName(inware.getMan());
        if(user==null){
            map.put("error","err");
        }else {
            assert false;
            inware.setUid(user.getId());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            String time = df.format(new Date());
            inware.setInTime(time);

            OID oid = new OID();
            inware.setOid(oid.generateShortUuid());

            inWareService.add(inware);

            map.put("success","ok");
        }

        return "addInWare";
    }


    public String updateInWare(){
        System.out.println("收到管理员修改入库信息请求");
        map.clear();
        inWareService.update(inware);
        map.put("success","ok");
        return "updateInWare";
    }

    public String deleteInWare(){
        System.out.println("收到管理员删除入库信息请求");
        map.clear();
        inWareService.delete(inware);
        map.put("success","ok");
        return "deleteInWare";
    }

    @Override
    public Inware getModel() {
        return inware;
    }
}
