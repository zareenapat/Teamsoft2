package POJOModels;

public class UserPage {

	public int page;
	public int per_page;
	public int total;
	public int total_pages;
	public UserDetail[] data;
	public UserSupport support;

	public UserSupport getSupport() {
		return support;
	}
	public void setAd(UserSupport support) {
		this.support = support;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPer_page() {
		return per_page;
	}
	public void setPer_page(int per_page) {
		this.per_page = per_page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}
	public UserDetail[] getData() {
		return data;
	}
	public void setData(UserDetail[] data) {
		this.data = data;
	}
}