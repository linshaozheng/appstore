package com.linsz.demo.controller;

import com.linsz.demo.error.BusinessException;
import com.linsz.demo.model.AyUser;
import com.linsz.demo.service.AyUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/ayUser")
public class AyUserController {
    @Resource
    private AyUserService ayUserService;
    @RequestMapping("/test")
    public String test(Model model){
        //查询数据库的所有数据
        List<AyUser>ayUser=ayUserService.findAll();
        model.addAttribute("users",ayUser);
        return "ayUser";
    }
    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<AyUser> ayUser = ayUserService.findAll();
        model.addAttribute("users",ayUser);
        throw new BusinessException("业务异常");
    }

//    @RequestMapping("/update")
//    public String update(Model model) {
//        AyUser ayUser = new AyUser();
//        ayUser.setId("1");
//        ayUser.setName("阿艺");
//        boolean isSuccess = ayUserService.update(ayUser);
//        return "success";
//    }
    @RequestMapping("/findByNameAndPasswordRetry")
    public String findByNameAndPasswordRetry(Model model){
        AyUser ayUser=ayUserService.findByNameAndPasswordRetry("阿艺","123456");
        model.addAttribute("users",ayUser);
        return "success";
    }

}
