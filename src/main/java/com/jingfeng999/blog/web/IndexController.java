package com.jingfeng999.blog.web;

import com.jingfeng999.blog.exception.BlogNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther : jingfeng999
 * @Date : 2021/4/2 13:33
 * @Description: blog
 * @Version: 1.0
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        int a = 12/0;
        return "blog";
    }
}
