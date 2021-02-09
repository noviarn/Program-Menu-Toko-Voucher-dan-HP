package Model;

public class Voucher extends Barang {
	private double pajak;
	public static int total;
	
	public Voucher() {}
	
	public Voucher(String id, String nama, double harga, int stok, double pajak) {
		super(id,nama,harga);
		this.pajak=pajak;
		super.stok=stok;
	}
	
	public double getPajak() {
		return this.pajak;
	}
	
	public double getHargaJual() {
		return super.harga*(1+this.pajak);
	}

}
