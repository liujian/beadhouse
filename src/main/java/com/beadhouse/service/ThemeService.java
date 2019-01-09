package com.beadhouse.service;

import com.beadhouse.out.BasicData;


public interface ThemeService {

	/**
	 * 获取logo及主题颜色
	 * @return
	 */
	BasicData theme();

	/**
	 * 获取logo及主题颜色和版本号
	 * @return
	 */
	BasicData getVersionCode();
}
