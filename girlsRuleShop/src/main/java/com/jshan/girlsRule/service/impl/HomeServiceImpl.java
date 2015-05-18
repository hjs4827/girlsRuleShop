package com.jshan.girlsRule.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jshan.girlsRule.dao.BaseDao;
import com.jshan.girlsRule.service.HomeService;
import com.jshan.girlsRule.vo.ClothesPantsDetails;
import com.jshan.girlsRule.vo.ClothesTopDetails;
import com.jshan.girlsRule.vo.ProductInfo;

@Service
public class HomeServiceImpl implements HomeService {

	private Logger logger = LoggerFactory.getLogger(HomeServiceImpl.class);
	
	@Autowired
	BaseDao dao;

	@Override
	public void readInfo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exportExcel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveInfo(MultipartFile file) {
		// TODO Auto-generated method stub
		List<String> headers = new ArrayList<String>();

		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(file.getInputStream());
			HSSFSheet sheet = wb.getSheetAt(0);
			logger.debug("sheet name = " + wb.getSheetName(0));
			List<ProductInfo> list = new ArrayList<ProductInfo>();
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
				Iterator cells = row.cellIterator();
				int j = 0;
				if (i == 0) {
					while (cells.hasNext()) {
						HSSFCell cell = (HSSFCell) cells.next();
						headers.add(cell.getStringCellValue());
						logger.debug(cell.getStringCellValue());
					}
				}else{	
					ProductInfo info = new ProductInfo();
					ClothesPantsDetails pants = new ClothesPantsDetails();
					ClothesTopDetails tops = new ClothesTopDetails();
					info.setPants(pants);
					info.setTops(tops);
					while (cells.hasNext()) {						
						HSSFCell cell = (HSSFCell) cells.next();
						if(j == 0){
							info.setProductId(cell.getStringCellValue());
						}else if(j == 1){
							info.setFabric(cell.getStringCellValue());
						}else if(j == 2){
							info.setColor(cell.getStringCellValue());
						}else if(j == 3){
							info.setProductType((int) cell.getNumericCellValue());
						}else if(j == 4){
							info.setStockCnt((int) cell.getNumericCellValue());
						}else if(j == 5){
							info.setSizeInfo(cell.getStringCellValue());
						}else if(j == 6){
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							info.setBaseSize(cell.getStringCellValue());
						}
									
						
						if(j > 6 && info.getProductType() == 1){
							if(j == 7){
								tops.setShoulder((int) cell.getNumericCellValue());
							}else if(j == 8){
								tops.setChest((int) cell.getNumericCellValue());
							}else if(j == 10){
								tops.setSleeve((int) cell.getNumericCellValue());
							}else if(j == 11){
								tops.setArmhole((int) cell.getNumericCellValue());
							}else if(j == 12){
								tops.setTotalLength((int) cell.getNumericCellValue());
							}else if(j == 13){
								tops.setElastic((int) cell.getNumericCellValue());
							}else if(j == 14){
								tops.setThickness((int) cell.getNumericCellValue());
							}else if(j == 15){
								tops.setDiaphanousness((int) cell.getNumericCellValue());
							}else if(j == 16){
								tops.setLining((int) cell.getNumericCellValue());
							}
						}else if(j > 6 && info.getProductType() == 2){
							if(j == 7){
								pants.setWaist((int) cell.getNumericCellValue());
							}else if(j == 8){
								pants.setHip((int) cell.getNumericCellValue());
							}else if(j == 9){
								pants.setThigh((int) cell.getNumericCellValue());
							}else if(j == 10){
								pants.setLegOpeningBottom((int) cell.getNumericCellValue());
							}else if(j == 11){
								pants.setLegOpeningTop((int) cell.getNumericCellValue());
							}else if(j == 12){
								pants.setTotalLength((int) cell.getNumericCellValue());
							}else if(j == 13){
								pants.setElastic((int) cell.getNumericCellValue());
							}else if(j == 14){
								pants.setThickness((int) cell.getNumericCellValue());
							}else if(j == 15){
								pants.setDiaphanousness((int) cell.getNumericCellValue());
							}else if(j == 16){
								pants.setLining((int) cell.getNumericCellValue());
							}
						}
						j++;
					}
					list.add(info);
				}
			}
			
			for(ProductInfo info : list){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("product_id", info.getProductId());
				params.put("product_type", info.getProductType());
				params.put("fabric", info.getFabric());
				params.put("color", info.getColor());
				params.put("stock_cnt", info.getStockCnt());
				params.put("size_info", info.getSizeInfo());
				params.put("base_size", info.getBaseSize());
				System.out.println(params.toString());
				dao.insert("test.testInsertProductInfo",params);
				
				info.getTops().setProductId(info.getProductId());
				info.getPants().setProductId(info.getProductId());
				dao.insert("test.testInsertClothesTops",info.getTops());
				dao.insert("test.testInsertClothesPants", info.getPants());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
