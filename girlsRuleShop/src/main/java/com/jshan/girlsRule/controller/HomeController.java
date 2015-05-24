package com.jshan.girlsRule.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jshan.girlsRule.service.HomeService;
import com.jshan.girlsRule.util.PagingHelper;
import com.jshan.girlsRule.vo.ProductInfo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HomeService service;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		
		List<ProductInfo> total = service.readTotalInfo();
		int totalCnt = total.size();
		int rowRange = 10;
		int curPage = 1;
		int pageRange = 10;
		int startIndex = ((curPage - 1) * rowRange);
		int endIndex = (curPage * rowRange);	    
		   
		//테이블
		List<ProductInfo> list = service.readInfo(startIndex, endIndex, rowRange);
		String pagingHtml = PagingHelper.instance.getPaging(totalCnt, rowRange, curPage, pageRange);	
		model.addAttribute("paging", pagingHtml);
		model.addAttribute("list", list);

		return "home";
	}

	@RequestMapping("/upload")
	public @ResponseBody
	String getUploadFile(HttpServletRequest request) {

		try {
			Assert.state(request instanceof MultipartHttpServletRequest,
					"request !instanceof MultipartHttpServletRequest");
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;

			Iterator<String> files = mRequest.getFileNames();
			while (files.hasNext()) {
				String file_name = files.next();
				logger.debug("=<<<<" + file_name);
				MultipartFile file = mRequest.getFile(file_name);
				service.saveInfo(file);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "true";
	}

	@RequestMapping(value="/list",  method=RequestMethod.POST)
	public @ResponseBody
	Map getList(@RequestBody ProductInfo info) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<ProductInfo> total = service.readTotalInfo();
		int totalCnt = total.size();
		int rowRange = 10;
		int curPage = 1;
		int pageRange = 10;
		int startIndex = ((curPage - 1) * rowRange);
		int endIndex = (curPage * rowRange);	    
		   
		//테이블
		List<ProductInfo> list = service.readInfo(startIndex, endIndex, rowRange);
		String pagingHtml = PagingHelper.instance.getPaging(totalCnt, rowRange, curPage, pageRange);	
		map.put("list", list);
		map.put("paging", pagingHtml);
	
		return map;
	}
}
