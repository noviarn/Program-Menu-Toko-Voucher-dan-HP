package Model;

public class Barang {
	protected String id;
	protected double harga;
	protected String nama;
	protected int stok;
	
	public Barang() {}
	
	public Barang(String id, String nama, double harga) {
		this.id=id;
		this.nama=nama;
		this.harga=harga;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getNama() {
		return this.nama;
	}
	
	public double getHarga() {
		return this.harga;
	}
	
	public int getStok() {
		return this.stok;
	}
	
	public void minusStock(int jumlah) {
		this.stok-=jumlah;
	}
}

