package com.beadhouse.service;

import com.beadhouse.in.CollectionParam;
import com.beadhouse.out.BasicData;

public interface CollectionService {
	 
	 /**
	  * 用户收藏消息
	  */
	 BasicData collection(CollectionParam param);
	 
	 /**
	  * 删除收藏消息
	  */
	 
	 BasicData delcollection(CollectionParam param);
	 
	 /**
	  * 老人收藏消息
	  */
	 BasicData eldercollection(CollectionParam param);
	 /**
	  * 删除老人收藏消息
	  */
	 BasicData deleldercollection(CollectionParam param);
}
