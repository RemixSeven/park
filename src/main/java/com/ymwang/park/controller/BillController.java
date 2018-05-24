package com.ymwang.park.controller;

import com.ymwang.park.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wym
 * @Date: 2018/5/23
 */
@RestController
@RequestMapping(value = "/wallet")
public class BillController {
    @Autowired
    BillService billService;
}
