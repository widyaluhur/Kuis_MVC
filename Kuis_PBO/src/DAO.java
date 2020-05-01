import javax.swing.*;
import java.sql.*;

public class DAO {
    Connection koneksi;
    Statement statement;

    public DAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/kuispbo";
            koneksi = DriverManager.getConnection(url, "root", "");
            statement = koneksi.createStatement();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Class Not found : " + ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL Exception : " + ex);
        }
    }

    public void insert(Model model) {
        try {
            String query = "INSERT INTO tb_data VALUES (null, '" + model.getJudul() + "', '" + model.getTipe() + "', " + model.getEpisode() + ", '" + model.getGenre() + "', '" + model.getStatus() + "', " + model.getRating() + ")";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public String[][] read() {
        try {
            int jmlData = 0;
            int nomor = 1;
            String data[][] = new String[getJmlData()][8];
            String query = "SELECT * FROM tb_data";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data[jmlData][0] = Integer.toString(nomor);
                data[jmlData][1] = resultSet.getString("id");
                data[jmlData][2] = resultSet.getString("judul");
                data[jmlData][3] = resultSet.getString("tipe");
                data[jmlData][4] = resultSet.getString("episode");
                data[jmlData][5] = resultSet.getString("genre");
                data[jmlData][6] = resultSet.getString("status");
                data[jmlData][7] = resultSet.getString("rating");
                nomor++;
                jmlData++;
            }

            return data;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

            return null;
        }
    }

    public int getJmlData() {
        int jmlData = 0;
        try {
            String query = "SELECT  * FROM tb_data";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jmlData++;
            }

            return jmlData;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

            return 0;
        }
    }

    void update(int dataTerpilih, String judul, String tipe, int episode, String genre, String status, int rating) {
        try {
            String query = "UPDATE tb_data SET judul = '" + judul + "',tipe = '" + tipe + "', episode = " + episode + ",genre = '" + genre + "', status = '" + status + "', rating = " + rating + " WHERE id = " + dataTerpilih + "";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diupdate");
        } catch (Exception sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public void delete(int id) {
        try {
            String query = "DELETE FROM tb_data WHERE id = " + id + "";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");

        } catch (SQLException sql) {
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public String[][] search(Model model) {
        try {
            int jmlData = 0;
            int nomor = 1;
            String datas[][] = new String[getJmlDataSearch(model)][8];
            String query = "SELECT * FROM tb_data WHERE judul LIKE '%" + model.getSearch() + "%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                datas[jmlData][0] = Integer.toString(nomor);
                datas[jmlData][1] = resultSet.getString("id");
                datas[jmlData][2] = resultSet.getString("judul");
                datas[jmlData][3] = resultSet.getString("tipe");
                datas[jmlData][4] = resultSet.getString("episode");
                datas[jmlData][5] = resultSet.getString("genre");
                datas[jmlData][6] = resultSet.getString("status");
                datas[jmlData][7] = resultSet.getString("rating");
                nomor++;
                jmlData++;
            }

            return datas;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

            return null;
        }
    }

    public int getJmlDataSearch(Model model) {
        int jmlData = 0;
        try {
            String query = "SELECT * FROM tb_data WHERE judul LIKE '%" + model.getSearch() + "%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jmlData++;
            }

            return jmlData;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

            return 0;
        }
    }
}

