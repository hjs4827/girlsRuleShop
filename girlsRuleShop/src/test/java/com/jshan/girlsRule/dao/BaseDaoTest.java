package com.jshan.girlsRule.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		 // "file:src/main/webapp/WEB-INF/config/springmvc/dispatcher-servlet.xml"
		"classpath:/spring/context-common.xml"
		, "classpath:/spring/context-datasource.xml"
		, "classpath:/spring/context-sqlmap.xml"
		, "classpath:/spring/context-transaction.xml"
})
public class BaseDaoTest {
//	private Logger logger = LoggerFactory.getLogger(BaseDaoTest.class);
	
	@Autowired
	BaseDao dao;
	
	@Before
	public void initTable(){
		dao.delete("test.testDeleteProductInfo", "ops001");
	}
	
	@Test
	public void baseDaoInsertTest(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("product_id", "ops001");
		params.put("product_type", "A");
		params.put("fabric", "RAYON POLYESTER");
		params.put("color", "BLACK, CHARCOLE, KHAKI");
		params.put("stock_cnt", 3);
		params.put("size_info", "S, M");
		params.put("base_size", "S");
		System.out.println(params.toString());
		dao.insert("test.testInsertProductInfo",params);
		
		@SuppressWarnings("unchecked")
		Map<String, Object> insertData = (Map<String, Object>) dao.getSingleRow("test.testRetrieveProductInfoById", params.get("product_id") );
		
		assertEquals(params.get("product_type"), insertData.get("product_type"));
		assertEquals(params.get("fabric"), insertData.get("fabric"));
		assertEquals(params.get("color"), insertData.get("color"));
		assertEquals(params.get("stock_cnt"), insertData.get("stock_cnt"));
		assertEquals(params.get("size_info"), insertData.get("size_info"));
		assertEquals(params.get("base_size"), insertData.get("base_size"));
		
	}
}
