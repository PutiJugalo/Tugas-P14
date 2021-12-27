import java.sql.*;

public interface Penjualan 
{
    void displayDB() throws SQLException;
    void tambahdata() throws SQLException;
    void ubahdata() throws SQLException;
    void deletedata();
    void caridata() throws SQLException;
}