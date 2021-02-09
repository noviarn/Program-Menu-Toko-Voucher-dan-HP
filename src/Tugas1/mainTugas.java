package Tugas1;

import java.util.Scanner;
import Model.Barang;
import Model.Handphone;
import Model.Order;
import Model.Voucher;

public class mainTugas {
	
	static Scanner scanner1 = new Scanner(System.in);
	static Scanner scanner2 = new Scanner(System.in);
	static Barang[] barang = new Barang[100];
	static Order[] order = new Order[100];
	static int jumlahVoucher=0, jumlahHandphone=0, jumlahOrder=0, jumlahBarang=0;
	
	public static void menuUtama() {
		int inputPilihan, idx;
		
		while(true) {
			System.out.println("---------Menu Toko Voucher & HP------------");
			System.out.println("1. Pesan Barang");
			System.out.println("2. Lihat Pesanan");
			System.out.println("3. Barang Baru");
			System.out.print("Pilihan : ");
			inputPilihan=scanner1.nextInt();
			
			switch(inputPilihan) {
				case 1:
						System.out.println("Daftar Barang Toko Voucher & HP");
						for(idx=0;idx<jumlahBarang;idx++){
							if(barang[idx].getId().contains("H")) {
								Handphone hp=(Handphone)barang[idx];
							
								System.out.println("ID      : "+hp.getId());
								System.out.println("Nama    : "+hp.getNama()+" "+hp.getWarna());
								System.out.println("Stok    : "+hp.getStok());
								System.out.printf("Harga   : %.0f\n", hp.getHarga());
							}
								else {
									Voucher voucher = (Voucher)barang[idx];
							
									System.out.println("ID      : "+voucher.getId());
									System.out.println("Nama    : "+voucher.getNama());
									System.out.printf("Nominal : %.0f\n", voucher.getHarga());
									System.out.println("Stok    : "+voucher.getStok());
									System.out.printf("Harga   : %.0f\n", voucher.getHargaJual());
								}
						System.out.println("-------------------------------");
						}
						pesanBarang();
						break;
				case 2:
						lihatPesanan();
						break;
				case 3:
						barangBaru();
						break;
			}
		}
	}
	
	public static String checkID (String id) {
		int i;
		for(i=0;i <jumlahBarang;i++) {
			if(id.contentEquals(barang[i].getId()))
				return id;
		}
		return "Tidak ada.";
	}
	
	public static int check_ID (String id){
		int i;
		for(i=0;i<jumlahBarang;i++){
			if(id.equals(barang[i].getId()))
				return i;
		}
		return -1;
	}
	
	public static void barangBaru() {
		char inputBarang;
		String nama, warna;
		int harga, stok;
		double ppn;
		
		System.out.print("Voucher /  Handphone (V/H) : ");
		inputBarang=scanner2.next().charAt(0); 
		scanner2.nextLine();
		System.out.print("Nama : ");
		nama=scanner2.nextLine();
		System.out.print("Harga : ");
		harga=scanner1.nextInt(); 
		System.out.print("Stok : ");
		stok=scanner1.nextInt(); 

		if(inputBarang=='v' || inputBarang=='V') {
			System.out.print("PPN : ");
			ppn=scanner1.nextDouble(); 
			
			jumlahVoucher++;
			Voucher voucher=new Voucher("V0"+jumlahVoucher, nama, harga, stok, ppn);
			barang[jumlahBarang]=(Barang)voucher;
			jumlahBarang++;
			System.out.println("Voucher telah berhasil diinput\n");
		}
			else if(inputBarang=='h' || inputBarang=='H') {
				System.out.print("Warna : ");
				warna = scanner2.next();
				
				jumlahHandphone++;
				Handphone hp=new Handphone("H0"+jumlahHandphone, nama, harga, stok, warna);
				barang[jumlahBarang]=(Barang)hp;
				jumlahBarang++;
				System.out.println("Handphone telah berhasil diinput\n");
			}
	}
	
	public static void pesanBarang() {
		System.out.println("Ketik 0 untuk batal");
		System.out.print("Pesan barang (ID) : ");
		String id=scanner2.nextLine();
		String check=checkID(id);
		int idx, jumlah, uang;
		double total;
		
		if(check!="Tidak ada.") {
			idx=check_ID(id);
			System.out.print("Masukkan jumlah : ");
			jumlah=scanner1.nextInt();
			
			if(id.contains("H")) {
				Handphone hp=(Handphone)barang[idx];
				
				if(jumlah>0&&jumlah<=hp.getStok()){
					total=jumlah*hp.getHarga();
					System.out.printf("%d @ %s dengan total harga %.0f\n", jumlah, hp.getNama(), total);
					System.out.print("Masukkan jumlah uang : ");
					uang=scanner1.nextInt();

					if(uang<total){
						System.out.println("Jumlah uang tidak mencukupi\n");
					}
						else{
							order[jumlahOrder]=new Order(id, hp, jumlah);
							Handphone.total+=total;
							jumlahOrder++;
							hp.minusStock(jumlah);
							System.out.println("Berhasil dipesan\n");
						}
				}
					else {
						System.out.println("Stok tidak mencukupi\n");
					}
			}
				else if(id.contains("V")){
					Voucher voucher=(Voucher)barang[idx];
					
					if(jumlah>0 && jumlah<=voucher.getStok()){
						total=jumlah*voucher.getHargaJual();
						System.out.printf("%d @ %s dengan total harga %.0f\n", jumlah, voucher.getNama(), total);
						System.out.print("Masukkan jumlah uang : ");
						uang=scanner1.nextInt();
	
						if(uang<total){
							System.out.println("Jumlah uang tidak mencukupi\n");
						}
							else {
								order[jumlahOrder]=new Order(id, voucher, jumlah);
								Voucher.total+=total;
								jumlahOrder++;
								voucher.minusStock(jumlah);
								System.out.println("Berhasil dipesan\n");
							}
					}
					else {
						System.out.println("Stok tidak mencukupi\n");
					}
				}
		}
		else {
			System.out.println("Barang tidak ditemukan\n");
		}
	}
	
	public static void lihatPesanan() {
		int i;
		
		System.out.println("Daftar Pesanan Toko Multiguna");
		for(i=0;i<jumlahOrder;i++){
			System.out.println("ID      : "+order[i].getId());
			if(order[i].getId().contains("OH")) {
				System.out.println("Nama    : "+order[i].getHandphone().getNama());
				System.out.println("Jumlah  : "+order[i].getJumlah());
				System.out.println("Total   : "+Handphone.total);
				System.out.println("------------------------------------------");
			}
				else{
					System.out.println("Nama    : "+order[i].getVoucher().getNama());
					System.out.println("Jumlah  : "+order[i].getJumlah());
					System.out.println("Total   : "+Voucher.total);
					System.out.println("------------------------------------------");
				}
		}
	}

	public static void main(String[] args) {
		for(;;)	{
			menuUtama();
		}
	}

}
