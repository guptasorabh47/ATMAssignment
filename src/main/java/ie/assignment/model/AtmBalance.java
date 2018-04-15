package ie.assignment.model;

public class AtmBalance {
	private int five;
	private int ten;
	private int twenty;
	private int fifty;

	public AtmBalance() {
	}

	public AtmBalance(int fifty, int twenty, int ten, int five) {
		this.fifty = fifty;
		this.twenty = twenty;
		this.ten = ten;
		this.five = five;
	}

	public int getFifty() {
		return fifty;
	}

	public int getTwenty() {
		return twenty;
	}

	public int getTen() {
		return ten;
	}

	public int getFive() {
		return five;
	}

	public void setFifty(int fifty) {
		this.fifty = fifty;
	}

	public void setTwenty(int twenty) {
		this.twenty = twenty;
	}

	public void setTen(int ten) {
		this.ten = ten;
	}

	public void setFive(int five) {
		this.five = five;
	}
}
