package Model;

public class Handphone extends Barang{
	private String warna;
	public static int total;
	
	public Handphone() {}
	
	public Handphone(String id, String nama, double harga, int stok, String warna) {
		super(id,nama, harga);
		this.warna=warna;
		super.stok=stok;
	}
	
	public String getWarna() {
		return this.warna;
	}

}
