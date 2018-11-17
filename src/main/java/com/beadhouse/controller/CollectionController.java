package com.beadhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.beadhouse.in.CollectionParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.CollectionService;


@RestController
public class CollectionController {
  
    @Autowired
    CollectionService collectionService;
    
    /**
     * 用户收藏消息
     *
     * @param request
     * @return
     */
    @RequestMapping("collection")
    @ResponseBody
    @Transactional
    public BasicData collection(@Valid @RequestBody CollectionParam param, HttpServletRequest request) {
        return collectionService.collection(param);
    }
    
    /**
     * 删除收藏消息
     *
     * @param request
     * @return
     */
    @RequestMapping("delcollection")
    @ResponseBody
    @Transactional
    public BasicData delcollection(@Valid @RequestBody CollectionParam param, HttpServletRequest request) {
        return collectionService.delcollection(param);
    }
    
    /**
     * 老人收藏消息
     *
     * @param request
     * @return
     */
    @RequestMapping("eldercollection")
    @ResponseBody
    @Transactional
    public BasicData eldercollection(@Valid @RequestBody CollectionParam param, HttpServletRequest request) {
        return collectionService.eldercollection(param);
    }
    
    /**
     * 删除老人收藏消息
     *
     * @param request
     * @return
     */
    @RequestMapping("elder/delcollection")
    @ResponseBody
    @Transactional
    public BasicData deleldercollection(@Valid @RequestBody CollectionParam param, HttpServletRequest request) {
        return collectionService.deleldercollection(param);
    }
    

}
