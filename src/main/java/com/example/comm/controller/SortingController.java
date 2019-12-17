package com.example.comm.controller;


import com.example.comm.pojo.cursors.Cursors;


import com.example.comm.pojo.ng.Ng;
import com.example.comm.service.cursors.CursoursService;
import com.example.comm.service.ng.NgService;
import com.example.comm.service.sorting.SortingService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Create by self on 2019/11/28 14:28
 * sorting控制层
 */
@Controller
@RequestMapping(value = "/sorting")
public class SortingController {

    private Logger logger = Logger.getLogger(SortingController.class);

    @Resource
    private SortingService sortingService;//获取资源

    @Resource
    private CursoursService cursoursService;//获取资源
    @Resource
    private NgService ngService;//获取资源

    @RequestMapping("/index")
    public String helloSpringBoot() {
        System.out.println("进入Index界面");
        return "index";
    }


    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public Cursors ajax(Model model, @RequestParam("order") String order) throws Exception {
        System.out.println("进行AJAX");
        System.out.println("order" + order);
        Cursors cursors = cursoursService.getCursorsBybarcode(order);//把获取到的记录存到List里
        return cursors;
    }

    @RequestMapping(value = "/ng", method = RequestMethod.POST)
    @ResponseBody
    public Ng ng(Model model, @RequestParam("ng") String ng) throws Exception {
        System.out.println("进行AJAX");
        System.out.println("ng" + ng);
        Ng ng1 = ngService.getNg();
        System.out.println("时间"+ng1.getN_date());
        return ng1;
    }


}
