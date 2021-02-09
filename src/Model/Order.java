package Model;

public class Order {
	private String id;
	private Voucher voucher;
	private Handphone handphone;
	private int jumlah;
	public static int index = 0;
	
	public Order(String id, Voucher voucher, int jumlah) {
		this.id="OV" + id + "-" + index;
		this.voucher=voucher;
		this.jumlah=jumlah;
		index++;
	}
	
	public Order(String id, Handphone handphone, int jumlah) {
		this.id="OH" + id + "-" + index;
		this.handphone=handphone;
		this.jumlah=jumlah;
		index++;
	}
	
	public Voucher getVoucher() {
		return this.voucher;
	}
	
	public Handphone getHandphone() {
		return this.handphone;
	}
	
	public int getJumlah() {
		return this.jumlah;
	}
	
	public String getId() {
		return this.id;
	}
}
