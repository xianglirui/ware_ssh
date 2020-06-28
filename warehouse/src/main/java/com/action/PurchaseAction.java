package com.action;

import com.domain.Inware;
import com.domain.Purchase;
import com.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.service.InWareService;
import com.service.PurchaseService;
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

@Controller("purchaseAction")
@Scope("prototype")
public class PurchaseAction  extends ActionSupport implements ModelDriven<Purchase> {
    //注入bean
    @Resource
    private PurchaseService purchaseService;
    @Resource
    private UserService userService;
    @Resource
    private InWareService inWareService;

    private HttpServletRequest request = ServletActionContext.getRequest();

    Map<String,Object> map = new HashMap<>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public String savePurchase(){
        System.out.println("收到用户采购请求");

        map.clear();

        String username = request.getParameter("username");
        System.out.println("用户昵称:\n"+username);
        User user = userService.selectByNikeName(username);
        purchase.setUid(user.getId());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        String time = df.format(new Date());
        purchase.setTime(time);
        purchase.setStatus("申请中");
        purchaseService.add(purchase);
        map.put("success","success");
        return "savePurchase";
    }

    public String findByListByUid(){
        System.out.println("收到查询用户历史采购记录");
        map.clear();

        String username = request.getParameter("username");
        System.out.println("用户昵称:\n"+username);
        User user = userService.selectByNikeName(username);
        List<Purchase> purchases = purchaseService.selectListByUid(user.getId());
        map.put("list",purchases);
        return "findByListByUid";
    }

    public String findPurchaseByList(){
        System.out.println("收到管理员查询采购申请");
        map.clear();
        List<Purchase> purchaseList = purchaseService.selectList();
        map.put("admin_list",purchaseList);
        return "findPurchaseByList";
    }

    public String updatePurchaseIfAgree(){
        System.out.println("收到管理员同意入库请求");
        map.clear();

        //更改状态
        purchase.setStatus("申请通过");

        Purchase p = purchaseService.select(purchase.getName(),purchase.getMan(),purchase.getTime());
        purchase.setId(p.getId());

        purchaseService.update(purchase);

        //入库
        OID oid = new OID();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        String time = df.format(new Date());

        Inware inware = new Inware();
        inware.setDetail(purchase.getDetail());
        inware.setInTime(time);
        inware.setNote(purchase.getNote());
        inware.setMan(purchase.getMan());
        inware.setPrice(purchase.getPrice());
        inware.setOid(oid.generateShortUuid());
        inware.setUid(purchase.getUid());
        inware.setName(purchase.getName());
        inware.setNum(purchase.getNum());

        inWareService.add(inware);

        map.put("success","success");
        return "updatePurchaseIfAgree";
    }

    public String updatePurchaseIfRefuse(){
        System.out.println("收到管理员拒绝入库请求");
        map.clear();
        purchase.setStatus("驳回申请");

        Purchase p = purchaseService.select(purchase.getName(),purchase.getMan(),purchase.getTime());
        purchase.setId(p.getId());

        purchaseService.update(purchase);
        map.put("success","success");
        return "updatePurchaseIfRefuse";
    }



    //用于封装模型
    private Purchase purchase = new Purchase();

    @Override
    public Purchase getModel() {
        return purchase;
    }
}
