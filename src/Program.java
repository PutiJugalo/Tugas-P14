import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program 
{
	static Connection conn;
	
    public static void main(String[] args) 
    {
    	Scanner terimaInput = new Scanner (System.in);
    	String pilihanUser;
    	Boolean isLanjutkan = true;
    	
    	String url = "jdbc:mysql://localhost:3306/penjualan_toko";

		try 
        {
            String welcome = "Selamat Datang di Toko Roti Puti";
            System.out.println(welcome.toLowerCase());
            
            Date datenow = new Date();
            SimpleDateFormat tgl = new SimpleDateFormat("E, dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss zzz");
	

			System.out.println("Tanggal \t: " + tgl.format(datenow));
			System.out.println("Waktu \t\t: " + time.format(datenow));

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Class Driver ditemukan...!");
			Barang brng = new Barang ();
			
			while (isLanjutkan) 
            {
				System.out.println("~~~~~~~~~~~~~~~~~~~~");
				System.out.println("||DAFTAR PEMBELIAN||");
				System.out.println("~~~~~~~~~~~~~~~~~~~~");
				System.out.println("1. View Data Pembelian");
				System.out.println("2. Tambah Data Pembelian");
				System.out.println("3. Ubah Data Pembelian");
				System.out.println("4. Hapus Data Pembelian");
				System.out.println("5. Cari Data Pembelian");
				
				System.out.print("\nPilihan anda (1/2/3/4/5): ");
				pilihanUser = terimaInput.next();
				
				switch (pilihanUser) 
                {
                    case "1":
                    brng.displayDB();
					break;
                    
                    case "2":
					brng.tambahdata();
					break;
                    
                    case "3":
					brng.ubahdata();
					break;
                    
                    case "4":
					brng.deletedata();
					break;
                    
                    case "5":
					brng.caridata();
					break;
                    
                    default:
					System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
				}
				
				System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
				pilihanUser = terimaInput.next();
				isLanjutkan = pilihanUser.equalsIgnoreCase("y");

			}
			System.out.println("Program selesai...");
			
		}
		catch(ClassNotFoundException ex) {
			System.err.println("Driver Error");
			System.exit(0);
		}
		catch(SQLException e){
			System.err.println("Tidak berhasil koneksi");
		}
    }
}

