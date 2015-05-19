package com.jshan.girlsRule.vo;

/**
 * product_id 제품번호 varchar(20) waist 허리 int hip 엉덩이 int thigh 허벅지 int
 * leg_opening_bottom 밑단 int leg_opening_top 밑위 int total_length 총장 int elastic
 * 신축성 int thickness 두깨감 int diaphanousness 비침 int lining 안감 int
 * 
 * @author choi
 * 
 */
public class ClothesPantsDetails {

	private String productId;
	private int waist;
	private int hip;
	private int thigh;
	private int legOpeningBottom;
	private int legOpeningTop;
	private int totalLength;
	private int elastic;
	private int thickness;
	private int diaphanousness;
	private int lining;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getWaist() {
		return waist;
	}

	public void setWaist(int waist) {
		this.waist = waist;
	}

	public int getHip() {
		return hip;
	}

	public void setHip(int hip) {
		this.hip = hip;
	}

	public int getThigh() {
		return thigh;
	}

	public void setThigh(int thigh) {
		this.thigh = thigh;
	}

	public int getLegOpeningBottom() {
		return legOpeningBottom;
	}

	public void setLegOpeningBottom(int legOpeningBottom) {
		this.legOpeningBottom = legOpeningBottom;
	}

	public int getLegOpeningTop() {
		return legOpeningTop;
	}

	public void setLegOpeningTop(int legOpeningTop) {
		this.legOpeningTop = legOpeningTop;
	}

	public int getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}

	public int getElastic() {
		return elastic;
	}

	public void setElastic(int elastic) {
		this.elastic = elastic;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public int getDiaphanousness() {
		return diaphanousness;
	}

	public void setDiaphanousness(int diaphanousness) {
		this.diaphanousness = diaphanousness;
	}

	public int getLining() {
		return lining;
	}

	public void setLining(int lining) {
		this.lining = lining;
	}

	@Override
	public String toString() {
		return "ClothesPantsDetails [productId=" + productId + ", waist="
				+ waist + ", hip=" + hip + ", thigh=" + thigh
				+ ", legOpeningBottom=" + legOpeningBottom + ", legOpeningTop="
				+ legOpeningTop + ", totalLength=" + totalLength
				+ ", elastic=" + elastic + ", thickness=" + thickness
				+ ", diaphanousness=" + diaphanousness + ", lining=" + lining
				+ "]";
	}

}
