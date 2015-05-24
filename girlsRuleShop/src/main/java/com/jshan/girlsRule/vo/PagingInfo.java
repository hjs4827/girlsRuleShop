package com.jshan.girlsRule.vo;

public class PagingInfo {

	private int startIndex;
	private int endIndex;

	private int rowRange;
	private int curPage;

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getRowRange() {
		return rowRange;
	}

	public void setRowRange(int rowRange) {
		this.rowRange = rowRange;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	@Override
	public String toString() {
		return "PagingInfo [startIndex=" + startIndex + ", endIndex="
				+ endIndex + ", rowRange=" + rowRange + ", curPage=" + curPage
				+ "]";
	}

}
