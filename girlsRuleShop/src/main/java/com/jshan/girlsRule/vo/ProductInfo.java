package com.jshan.girlsRule.vo;

/**
 * 
 * product_id 제품번호 varchar(20) product_type 제품타입(신발, 상의, 하의) "char(1) 1 : 상의 2 :
 * 하의" fabric 원단 varchar(30) color 컬러 varchar(30) stock_cnt 수량 int size_info 사이즈
 * varchar(10) base_size 기준사이즈 varchar(10) Product_info table vo
 * 
 * @author choi
 * 
 */
public class ProductInfo extends PagingInfo{

	private String productId;
	private int productType;
	private String fabric;
	private String color;
	private int stockCnt;
	private String sizeInfo;
	private String baseSize;

	private ClothesTopDetails tops;
	private ClothesPantsDetails pants;

	public ClothesTopDetails getTops() {
		return tops;
	}

	public void setTops(ClothesTopDetails tops) {
		this.tops = tops;
	}

	public ClothesPantsDetails getPants() {
		return pants;
	}

	public void setPants(ClothesPantsDetails pants) {
		this.pants = pants;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getProductType() {
		return productType;
	}

	public void setProductType(int productType) {
		this.productType = productType;
	}

	public String getFabric() {
		return fabric;
	}

	public void setFabric(String fabric) {
		this.fabric = fabric;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getStockCnt() {
		return stockCnt;
	}

	public void setStockCnt(int stockCnt) {
		this.stockCnt = stockCnt;
	}

	public String getSizeInfo() {
		return sizeInfo;
	}

	public void setSizeInfo(String sizeInfo) {
		this.sizeInfo = sizeInfo;
	}

	public String getBaseSize() {
		return baseSize;
	}

	public void setBaseSize(String baseSize) {
		this.baseSize = baseSize;
	}

	@Override
	public String toString() {
		return "ProductInfo [productId=" + productId + ", productType="
				+ productType + ", fabric=" + fabric + ", color=" + color
				+ ", stockCnt=" + stockCnt + ", sizeInfo=" + sizeInfo
				+ ", baseSize=" + baseSize + "]";
	}

}
