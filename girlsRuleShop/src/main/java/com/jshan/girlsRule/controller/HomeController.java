package com.jshan.girlsRule.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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

		// 테이블
		List<ProductInfo> list = service.readInfo(startIndex, endIndex,
				rowRange);
		String pagingHtml = PagingHelper.instance.getPaging(totalCnt, rowRange,
				curPage, pageRange);
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

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> getList(@RequestBody ProductInfo info) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<ProductInfo> total = service.readTotalInfo();
		int totalCnt = total.size();
		int rowRange = 10;
		int curPage = info.getCurPage();
		int pageRange = 10;
		int startIndex = ((curPage - 1) * rowRange);
		int endIndex = (curPage * rowRange);

		// 테이블
		List<ProductInfo> list = service.readInfo(startIndex, endIndex,
				rowRange);
		String pagingHtml = PagingHelper.instance.getPaging(totalCnt, rowRange,
				curPage, pageRange);
		map.put("list", list);
		map.put("paging", pagingHtml);

		return map;
	}

	@RequestMapping("/download")
	public void getDownloadFile(HttpServletResponse response) throws Exception {

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition",
				"attachment; filename=bumworld.xls");

		// 출력용 workbook을 생성합니다.
		WritableWorkbook workbook = Workbook.createWorkbook(response
				.getOutputStream());

		WritableSheet sheet = workbook.createSheet("상품", 0);

		// Setting Background colour for Cells
		Colour bckcolor = Colour.DARK_GREEN;
		WritableCellFormat cellFormat = new WritableCellFormat();
		cellFormat.setBackground(bckcolor);

		// Setting Colour & Font for the Text

		WritableCellFormat titleFormat = new WritableCellFormat(
				new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, // Bold
						false, // 이탤릭체여부
						UnderlineStyle.NO_UNDERLINE, // 밑줄 스타일
						Colour.BLACK, // 폰트 색
						ScriptStyle.NORMAL_SCRIPT)); // 스크립트 스타일
		titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		titleFormat.setAlignment(Alignment.CENTRE);
		titleFormat.setWrap(true);
		titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		titleFormat.setBackground(Colour.GRAY_25);

		String[] headers = new String[] { "상품 ID", "상품 타입", "소재", "색상", "수량",
				"사이즈", "기본 사이즈" };
		for (int i = 0; i < headers.length; i++) {
			Label label = new Label(i, 0, headers[i], titleFormat);
			sheet.addCell(label);
		}

		List<ProductInfo> total = service.readTotalInfo();
		int count = 1;
		for (int i = 0; i < total.size(); i++) {
			ProductInfo info = total.get(i);
			for (int j = 0; j < headers.length; j++) {
				Label label = null;
				switch (j) {
				case 0:
					label = new Label(j, count, info.getProductId());
					break;
				case 1:
					label = new Label(j, count, Integer.toString(info
							.getProductType()));
					break;
				case 2:
					label = new Label(j, count, info.getFabric());
					break;
				case 3:
					label = new Label(j, count, info.getColor());
					break;
				case 4:
					label = new Label(j, count, Integer.toString(info
							.getStockCnt()));
					break;
				case 5:
					label = new Label(j, count, info.getSizeInfo());
					break;
				case 6:
					label = new Label(j, count, info.getBaseSize());
					break;
				}
				sheet.addCell(label);
			}
			count++;
		}

		workbook.write();
		workbook.close();

	}

}
