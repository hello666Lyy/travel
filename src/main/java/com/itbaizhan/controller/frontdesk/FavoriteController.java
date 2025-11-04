package com.itbaizhan.controller.frontdesk;

import com.itbaizhan.pojo.Member;
import com.itbaizhan.service.FavoriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/frontdesk/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping("/add")
    public String add(Integer pid, HttpSession session, @RequestHeader("Referer")  String referer) {
        Member member = (Member) session.getAttribute("member");
        favoriteService.addFavorite(pid,member.getMid());
        return "redirect:"+referer;
    }

    @RequestMapping("/del")
    public String del(Integer pid, HttpSession session, @RequestHeader("Referer")  String referer) {
        Member member = (Member) session.getAttribute("member");
        favoriteService.delFavorite(pid,member.getMid());
        return "redirect:"+referer;
    }

}

