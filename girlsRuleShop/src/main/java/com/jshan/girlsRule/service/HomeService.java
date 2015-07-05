package com.jshan.girlsRule.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jshan.girlsRule.vo.ProductInfo;

public interface HomeService {

	public void saveInfo(MultipartFile file) throws Exception;
	
	public List<ProductInfo> readInfo(int startIndex, int endIndex, int rowRange);
	
	public void exportExcel();

	public List<ProductInfo> readTotalInfo();
}
