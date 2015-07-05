package com.jshan.girlsRule.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PagingHelper
{
  public static PagingHelper instance = new PagingHelper();
  
	private Logger logger = LoggerFactory.getLogger(PagingHelper.class);
  
  private PagingHelper()
  {
  }  

/**
   * @param totalCount 전체 레코드 수
   * @param pageSize 결과 목록에 보여지는 레코드 수
   * @param pageGroupSize 페이지 출력 범위
   * @param currentPage 현재페이지
   * @return
   */
  public String getPaging(int totalCount, int pageSize, int currentPage, int pageGroupSize){

	   String pageStr = "";
	   
	   int pageGroupCount = totalCount / (pageSize * pageGroupSize) + (totalCount % (pageSize * pageGroupSize) == 0 ? 0 :1);
	   int numPageGroup = (int)Math.ceil((double)currentPage / pageGroupSize); 
	  
	   if (totalCount > 0){
	      int pageCount = (totalCount / pageSize) + (totalCount % pageSize == 0 ? 0 : 1);
	      int startPage = pageGroupSize * (numPageGroup - 1) + 1;
	      int endPage = startPage + pageGroupSize-1;
	   	      	   
	      if (endPage > pageCount ){
	         endPage = pageCount;
	      }
	     
	      if (numPageGroup > 1){
	    	  pageStr += "<li><a href='javascript:goPage("+   ((numPageGroup-2) * pageGroupSize + 1)   +")'>&laquo;</a></li>";
	      }
	   
	      for (int i = startPage; i <= endPage; i++){

	         if (currentPage == i){
	        	pageStr += "<li class='active'><a href='javascript:goPage("+i +")'>"+i+"</a></li>";
	        }else{
	        	pageStr += "<li><a href='javascript:goPage("+i +")'>"+i+"</a></li>";
	         }
	      }
	   
	      if (numPageGroup < pageGroupCount){
	    	  pageStr += "	<li><a href='javascript:goPage("+ (numPageGroup * pageGroupSize + 1) +")'>&raquo;</a></li>";
	      }
	   }
	   return pageStr;
	}


}