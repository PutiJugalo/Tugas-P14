import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Barang implements Penjualan
{
    static Connection conn;
    String url = "jdbc:mysql://localhost:3306/penjualan_toko";

    public String barang;
    public Integer harga;
    public String noFaktur;
    public Integer jml;
    public Integer total;
    
    Scanner brg = new Scanner(System.in);

    public void displayDB() throws SQLException
    {
        String teks = "\nTabel Pembelian";
		System.out.println(teks.toUpperCase());

        String sql ="SELECT * FROM pembelian";
		conn = DriverManager.getConnection(url,"root","");
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

        while(result.next())
        {
			System.out.print("\nNomor Faktur\t : ");
            System.out.print(result.getInt("no_faktur"));
            System.out.print("\nNama Barang\t  : ");
            System.out.print(result.getString("nama_barang"));
            System.out.print("\nHarga Barang\t  : ");
            System.out.print(result.getString("harga_barang"));
            System.out.print("\nJumlah Barang\t : ");
            System.out.print(result.getInt("jumlah"));
            System.out.print("\nTotal Harga\t  : ");
            System.out.print(result.getInt("total"));
            System.out.print("\n");
		}
    }

    public void tambahdata() throws SQLException 
    {
        String teks1 = "\n|||Tambah Pembelian|||";
		System.out.println(teks1.toUpperCase());
		
    	try 
        {
	    	System.out.print("Masukkan nomor faktur : ");
	        noFaktur = brg.nextLine();
            noFaktur = noFaktur.toLowerCase();
	
	        System.out.print("Masukkan nama barang : ");
	        barang = brg.nextLine();
            barang = barang.toUpperCase();
		    
            System.out.print("Masukkan harga barang : ");
            harga = brg.nextInt();

            System.out.print("Masukkan jumlah barang : ");
            jml = brg.nextInt();

	        total = harga * jml;
	        System.out.println("Total harga: Rp" + total);
	        
	        String sql = "INSERT INTO pembelian (no_faktur, nama_barang, harga_barang, jumlah, total) VALUES ('"+noFaktur+"','"+barang+"','"+harga+"','"+jml+"','"+total+"')";
	        conn = DriverManager.getConnection(url,"root","");
	        Statement statement = conn.createStatement();
	        statement.execute(sql);
	        System.out.println("Berhasil menambahkan data...!");
    	}

    	catch (SQLException e) 
        {
	        System.err.println("Ada kesalahan memasukkan data...");
	    } 

    	catch (InputMismatchException e) 
        {
	    	System.err.println("Inputan harus berupa angka");
        }
    }

    public void ubahdata() throws SQLException
    {
		String teks2 = "\n|||Ubah Data Pembelian|||";
		System.out.println(teks2.toUpperCase());
		
		try 
        {
            displayDB();
            System.out.print("\nMasukkan Nomor Faktur yang ingin di ubah : ");
            Integer noFaktur = Integer.parseInt(brg.nextLine());
            
            String sql = "SELECT * FROM pembelian WHERE no_faktur = " +noFaktur;
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next())
            {
                System.out.print("Nomor Faktur Baru ["+result.getString("nama_barang")+"]\t: ");
                String barang = brg.nextLine();
                   
                sql = "UPDATE pembelian SET nama_barang='"+barang+"' WHERE no_faktur='"+noFaktur+"'";

                if(statement.executeUpdate(sql) > 0)
                {
                    System.out.println("Berhasil memperbaharui barang (Nomor "+noFaktur+")");
                }
            }
            statement.close();        
        } 

		catch (SQLException e) 
        {
        	System.err.println("Terjadi kesalahan dalam mengubah data");
            System.err.println(e.getMessage());
        }
	}

    public void deletedata() 
    {
		String teks3 = "\n|||Hapus Data Pembelian|||";
		System.out.println(teks3.toUpperCase());
		
		try
        {
	        displayDB();
	        System.out.print("\nMasukan Nomor Faktur yang ingin diHapus : ");
	        Integer noFaktur = Integer.parseInt(brg.nextLine());
	        
	        String sql = "DELETE FROM pembelian WHERE no_faktur = "+ noFaktur;
	        conn = DriverManager.getConnection(url,"root","");
	        Statement statement = conn.createStatement();
	        
	        if(statement.executeUpdate(sql) > 0)
            {
	            System.out.println("Berhasil menghapus data pembelian (Nomor "+noFaktur+")");
	        }
	   }
		catch(SQLException e)
        {
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
        
        catch(Exception e)
        {
            System.out.println("Masukkan data yang benar");
        }
	}

    public void caridata() throws SQLException 
    {
		String teks4 = "\n|||Cari Data Pembelian|||";
		System.out.println(teks4.toUpperCase());
				
		System.out.print("Masukkan Nama Barang yang ingin dicari : ");    
		String kunci = brg.nextLine();
		
		String sql = "SELECT * FROM pembelian WHERE nama_barang LIKE '%"+kunci+"%'";
		conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql); 
                
        while(result.next())
        {
        	System.out.print("\nNomor Faktur : ");
            System.out.print(result.getInt("no_faktur"));
            System.out.print("\nNama Barang  : ");
            System.out.print(result.getString("nama_barang"));
            System.out.print("\nHarga Barang  : ");
            System.out.print(result.getString("harga_barang"));
            System.out.print("\nJumlah Barang : ");
            System.out.print(result.getInt("jumlah"));
            System.out.print("\nTotal Harga  : ");
            System.out.print(result.getInt("total"));
            System.out.print("\n");
        }
	}   
}

