package com.beadhouse.service;

import java.util.List;


import com.beadhouse.domen.Contact;
import com.beadhouse.domen.Quest;
import com.beadhouse.in.ContactsParam;
import com.beadhouse.in.DefineQuestParam;
import com.beadhouse.in.RegistrationParam;
import com.beadhouse.out.BasicData;

public interface ContractService {
	/**
	 * 获取用户联系人列表
	 */
	BasicData<List<Contact>> getcontacts(ContactsParam param);
	
	/**
	 * 获取问题列表
	 */
    BasicData<List<Quest>> getquestlist(ContactsParam param);

	/**
	 * 保存自定义问题
	 * @param param
	 * @return
	 * @throws Exception
	 */
	BasicData saveDefineQuest(DefineQuestParam param);
}
