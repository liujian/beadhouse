package com.beadhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.beadhouse.in.DefineQuestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beadhouse.in.ContactsParam;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.ContractService;

@RestController
public class ContractController {
	
	@Autowired
	ContractService contractService;

	/**
	 * 获取关联人列表
	 * @param param
	 * @return
	 * @author liujian
	 * @Date 2018-09-25
	 */
	 @RequestMapping("getContacts")
     @ResponseBody
     @Transactional
     public BasicData getContacts(@Valid @RequestBody ContactsParam param,HttpServletRequest request){
     	return contractService.getcontacts(param);
      }
	 
	 /**
	 * 获取问题列表
	 * @param param
	 * @return
	 * @author liujian
	 * @Date 2018-09-25
	 * @throws Exception 
	 */
	 @RequestMapping("getQuestList")
     @ResponseBody
     @Transactional
     public BasicData getQuestList(@Valid @RequestBody ContactsParam param,HttpServletRequest request){
		
		 return contractService.getquestlist(param);
	 }

	/**
	 * 保存自定义问题
	 * @param param
	 * @return
	 * @author Camile
	 * @Date 2018-09-30
	 * @throws Exception
	 */
	@RequestMapping("saveDefineQuest")
	@ResponseBody
	@Transactional
	public BasicData saveDefineQuest(@Valid @RequestBody DefineQuestParam param, HttpServletRequest request) {

		return contractService.saveDefineQuest(param);
	}
	 
	 
}
