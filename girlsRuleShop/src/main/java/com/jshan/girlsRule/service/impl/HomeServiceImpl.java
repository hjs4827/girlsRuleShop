package com.jshan.girlsRule.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInfo> readTotalInfo() {
		// 
		ProductInfo info = new ProductInfo();
		List<ProductInfo> list = dao.getList("main.getProductList", info);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductInfo> readInfo(int startIndex, int endIndex, int rowRange) {
		// 
		ProductInfo info = new ProductInfo();
		info.setStartIndex(startIndex);
		info.setEndIndex(endIndex);
		info.setRowRange(rowRange);
		List<ProductInfo> list = dao.getList("main.getProductListForSearch", info);
		return list;
	}

	

	@Override
	public void saveInfo(MultipartFile file) throws Exception {
		List<String> headers = new ArrayList<String>();

			Workbook w = Workbook.getWorkbook(file.getInputStream());
			Sheet sheet = w.getSheet(0);
			
			List<ProductInfo> list = new ArrayList<ProductInfo>();
	        int header_row = 1;
	    	 //row씩 읽기 
	        for (int i = header_row; i < sheet.getRows(); i++) {
	        	 //byte[] byte_ = sheet.getDrawing(i).getImageData();
	        	ProductInfo vo = new ProductInfo();
				ClothesPantsDetails pants = new ClothesPantsDetails();
				ClothesTopDetails tops = new ClothesTopDetails();
				vo.setPants(pants);
				vo.setTops(tops);
	        	 for (int j = 0; j < sheet.getColumns(); j++) {
			          Cell cell = sheet.getCell(j, i);
			          switch(j){
			          	case 0:
			          		vo.setProductId(cell.getContents());
			          		break;
			          	case 1:
			          		vo.setFabric(cell.getContents());
			          		break;
			          	case 2:
			          		vo.setColor(cell.getContents());
			          		break;
			          	case 3:
			          		vo.setProductType(Integer.parseInt(cell.getContents()));
			          		break;
			          	case 4:
			          		vo.setStockCnt(Integer.parseInt(cell.getContents()));
			          		break;				          		
			          	case 5:
			          		vo.setSizeInfo(cell.getContents());
			          		break;
			          	case 6:
			          		vo.setBaseSize(cell.getContents());
			          		break;
			          	default:
			          		break;
			          }		
			          if(vo.getProductType() == 1){
			        	  switch(j){
				        	  case 7:
				        		  tops.setShoulder(Integer.parseInt(cell.getContents()));
				        		  break;
				        	  case 8:
				        		  tops.setChest(Integer.parseInt(cell.getContents()));
				        		  break;
				        	  case 10:
				        		  tops.setSleeve(Integer.parseInt(cell.getContents()));
				        		  break;
				        	  case 11:
				        		  tops.setArmhole(Integer.parseInt(cell.getContents()));
				        		  break;
				        	  case 12:
				        		  tops.setTotalLength(Integer.parseInt(cell.getContents()));
				        		  break;
				        	  case 13:
				        		  tops.setElastic(Integer.parseInt(cell.getContents()));
				        		  break;
				        	  case 14:
				        		  tops.setThickness(Integer.parseInt(cell.getContents()));
				        		  break;
				        	  case 15:
				        		  tops.setDiaphanousness(Integer.parseInt(cell.getContents()));
				        		  break;
				        	  case 16:
				        		  tops.setLining(Integer.parseInt(cell.getContents()));
				        		  break;
				        	  default:
				        			 break;
			        	  }
			          }else if(vo.getProductType() == 2){
			        	  switch(j){
			        	  case 7:
			        			pants.setWaist(Integer.parseInt(cell.getContents()));
			        		  break;
			        	  case 8:
			        		  pants.setHip(Integer.parseInt(cell.getContents()));
			        		  break;
			        	  case 9:
			        		  pants.setThigh(Integer.parseInt(cell.getContents()));
			        		  break;
			        	  case 10:
			        		  pants.setLegOpeningBottom(Integer.parseInt(cell.getContents()));
			        		  break;
			        	  case 11:
			        		  pants.setLegOpeningTop(Integer.parseInt(cell.getContents()));
			        		  break;
			        	  case 12:
			        		  pants.setTotalLength(Integer.parseInt(cell.getContents()));
			        		  break;
			        	  case 13:
			        		  pants.setElastic(Integer.parseInt(cell.getContents()));
			        		  break;
			        	  case 14:
			        		  pants.setThickness(Integer.parseInt(cell.getContents()));
			        		  break;
			        	  case 15:
			        		  pants.setDiaphanousness(Integer.parseInt(cell.getContents()));
			        		  break;
			        	  case 16:
			        		  pants.setLining(Integer.parseInt(cell.getContents()));
			        		  break;
			        	  default:
			        			 break;
			        	  }
			          }
	        	 }
	        	 logger.info(vo.toString());
		         list.add(vo);
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
				dao.insert("test.testInsertProductInfo",info);
				
				info.getTops().setProductId(info.getProductId());
				info.getPants().setProductId(info.getProductId());
				dao.insert("test.testInsertClothesTops",info.getTops());
				dao.insert("test.testInsertClothesPants", info.getPants());
			}
		
	}



	@Override
	public void exportExcel() {
		// TODO Auto-generated method stub
		
	}
}
