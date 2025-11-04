package com.itbaizhan.controller.frontdesk;

import com.itbaizhan.bean.Result;
import com.itbaizhan.mapper.MemberMapper;
import com.itbaizhan.pojo.Member;
import com.itbaizhan.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/frontdesk/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/register")
    public ModelAndView register(Member member, String checkCode, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();

        String sessionCheckCode = (String)session.getAttribute("checkCode");
        if(!sessionCheckCode.equalsIgnoreCase(checkCode)) {
            modelAndView.addObject("message","验证码错误");
            modelAndView.setViewName("/frontdesk/register");
            return modelAndView;
        }

        Result result = memberService.register(member);
        if(!result.isFlag()) {
            modelAndView.addObject("message",result.getMessage());
            modelAndView.setViewName("/frontdesk/register");
        } else {
            modelAndView.setViewName("/frontdesk/register_ok");
        }
        return modelAndView;
    }

    @RequestMapping("/active")
    public ModelAndView active(String activeCode) {
        ModelAndView modelAndView = new ModelAndView();
        String active = memberService.active(activeCode);
        modelAndView.addObject("message",active);
        modelAndView.setViewName("/frontdesk/active_result");
        return modelAndView;
    }
}
