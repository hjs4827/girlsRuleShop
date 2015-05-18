package com.jshan.girlsRule.vo;

/**
 * ProductImage_Info table vo
 * product_id 제품번호 varchar(20) image_no 이미지번호 int image_url 이미지URL varchar(200)
 * 
 * @author choi
 * 
 */
public class ProductImageInfo {

	private String productId;
	private int imageNo;
	private String imageUrl;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getImageNo() {
		return imageNo;
	}

	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "ProductImageInfo [productId=" + productId + ", imageNo="
				+ imageNo + ", imageUrl=" + imageUrl + "]";
	}

	
}
