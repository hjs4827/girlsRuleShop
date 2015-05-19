package com.jshan.girlsRule.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jshan.girlsRule.vo.ProductInfo;

public interface HomeService {

	public void saveInfo(MultipartFile file);
	
	public List<ProductInfo> readInfo();
	
	public void exportExcel();
}
