package com.beadhouse.service.impl;

import java.util.Date;
import com.beadhouse.dao.CollectionMapper;
import com.beadhouse.dao.ElderUserMapper;
import com.beadhouse.dao.UserMapper;
import com.beadhouse.domen.User;
import com.beadhouse.in.CollectionParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beadhouse.domen.Collection;
import com.beadhouse.domen.ElderUser;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.CollectionService;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ElderUserMapper elderUserMapper;
	@Override
	public BasicData collection(CollectionParam param) {
		User user = userMapper.selectByToken(param.getToken());
        if (user == null) {
            return BasicData.CreateErrorInvalidUser();
        }
        Integer loginUserId=user.getId();
        Integer chatId=param.getChatId();
        Collection collection=new Collection();
        collection.setChatId(chatId);
        collection.setLoginUserId(loginUserId);
        collection.setCreateDate(new Date());
        Collection coll=collectionMapper.selectCollection(collection);
        if(coll!=null){
        	return BasicData.CreateErrorMsg("The news has been collected！");
        }
        collectionMapper.insertCollection(collection);
        Collection col=collectionMapper.selectCollection(collection);
		return BasicData.CreateSucess(col);
	}


	@Override
	public BasicData delcollection(CollectionParam param) {
		User user = userMapper.selectByToken(param.getToken());
		Collection coll = new Collection();
		coll.setCollectionid(param.getCollectionid());
		coll.setLoginUserId(user.getId());
		collectionMapper.deleteCollection(coll);
		return BasicData.CreateSucess();
	}


	@Override
	public BasicData eldercollection(CollectionParam param) {
		ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
        if (elderUser == null) {
            return BasicData.CreateErrorInvalidUser();
        }
        Integer elderUserid=elderUser.getElderUserId();
        Integer chatId=param.getChatId();
        Collection collection=new Collection();
        collection.setChatId(chatId);
        collection.setElderUserId(elderUserid);
        collection.setCreateDate(new Date());
        Collection coll=collectionMapper.selectCollection(collection);
        if(coll!=null){
        	return BasicData.CreateErrorMsg("The news has been collected！");
        }
        collectionMapper.insertCollection(collection);
        Collection col=collectionMapper.selectCollection(collection);
		return BasicData.CreateSucess(col);
	
	}


	@Override
	public BasicData deleldercollection(CollectionParam param) {
		ElderUser elderUser = elderUserMapper.selectByToken(param.getToken());
		if (elderUser == null) {
            return BasicData.CreateErrorInvalidUser();
        }
		Collection coll = new Collection();
		coll.setCollectionid(param.getCollectionid());
		coll.setElderUserId(elderUser.getElderUserId());
		collectionMapper.delElderCollection(coll);
		return BasicData.CreateSucess();
	}

}
