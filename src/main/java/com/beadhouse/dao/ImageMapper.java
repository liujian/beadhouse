package com.beadhouse.dao;

import com.beadhouse.domen.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {

	void insertImage(Image image);

	List<Image> selectImageByLoginUserId(int loginUserId);
}
