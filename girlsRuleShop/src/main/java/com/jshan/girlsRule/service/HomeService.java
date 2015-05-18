package com.jshan.girlsRule.service;

import org.springframework.web.multipart.MultipartFile;

public interface HomeService {

	public void saveInfo(MultipartFile file);
	
	public void readInfo();
	
	public void exportExcel();
}
