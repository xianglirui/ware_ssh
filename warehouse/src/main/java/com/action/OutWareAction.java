package com.action;

import com.domain.Inware;
import com.domain.Outware;
import com.domain.Purchase;
import com.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.InWareService;
import com.service.OutWareService;
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

@Controller("outWareAction")
@Scope("prototype")
public class OutWareAction  extends ActionSupport implements ModelDriven<Outware> {
    @Resource
    private OutWareService outWareService;
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


    public String findOutWareByUid(){
        System.out.println("收到用户查询出库信息请求");

        String username = request.getParameter("username");
        System.out.println("用户昵称:\n"+username);
        User user = userService.selectByNikeName(username);
        map.clear();
        List<Outware> inwares = outWareService.selectListByUid(user.getId());
        map.put("list",inwares);
        return "findOutWareByUid";
    }

    public String findOutWare(){
        System.out.println("收到管理员查询出库信息请求");

        map.clear();
        List<Outware> inwares = outWareService.selectList();
        map.put("list",inwares);
        return "findOutWare";
    }

    public String addOutWare(){
        System.out.println("收到管理员新增出库信息请求");
        map.clear();
        User user = userService.selectByName(outware.getMan());
        if(user==null){
            map.put("error","err");
        }else {
            assert false;
            outware.setUid(user.getId());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            String time = df.format(new Date());
            outware.setOutTime(time);

            OID oid = new OID();
            outware.setOid(oid.generateShortUuid());

            outWareService.add(outware);

            map.put("success","ok");
        }

        return "addOutWare";
    }


    public String updateOutWare(){
        System.out.println("收到管理员修改出库信息请求");
        map.clear();
        outWareService.update(outware);
        map.put("success","ok");
        return "updateOutWare";
    }

    public String deleteOutWare(){
        System.out.println("收到管理员删除出库信息请求");
        map.clear();
        outWareService.delete(outware);
        map.put("success","ok");
        return "deleteOutWare";
    }
    
    private Outware outware = new Outware();
    @Override
    public Outware getModel() {
        return outware;
    }
    
    
}
