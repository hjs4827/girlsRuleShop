package com.jshan.girlsRule.vo;

/**
 * product_id 제품번호 varchar(20) shoulder 어깨 int chest 가슴 int sleeve 소매 int
 * armhole 암홀 int total_length 총장 int elastic 신축성 int thickness 두깨감 int
 * diaphanousness 비침 int lining 안감 int
 * 
 * @author choi
 * 
 */
public class ClothesTopDetails {

	private String productId;
	private int shoulder;
	private int chest;
	private int sleeve;
	private int armhole;
	private int totalLength;
	private int eleastic;
	private int thickness;
	private int diaphanousness;
	private int lining;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getShoulder() {
		return shoulder;
	}

	public void setShoulder(int shoulder) {
		this.shoulder = shoulder;
	}

	public int getChest() {
		return chest;
	}

	public void setChest(int chest) {
		this.chest = chest;
	}

	public int getSleeve() {
		return sleeve;
	}

	public void setSleeve(int sleeve) {
		this.sleeve = sleeve;
	}

	public int getArmhole() {
		return armhole;
	}

	public void setArmhole(int armhole) {
		this.armhole = armhole;
	}

	public int getTotalLength() {
		return totalLength;
	}

	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}

	public int getEleastic() {
		return eleastic;
	}

	public void setEleastic(int eleastic) {
		this.eleastic = eleastic;
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
		return "ClothesTopDetails [productId=" + productId + ", shoulder="
				+ shoulder + ", chest=" + chest + ", sleeve=" + sleeve
				+ ", armhole=" + armhole + ", totalLength=" + totalLength
				+ ", eleastic=" + eleastic + ", thickness=" + thickness
				+ ", diaphanousness=" + diaphanousness + ", lining=" + lining
				+ "]";
	}

}
