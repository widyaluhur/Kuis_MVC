import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class ViewLogin extends JFrame {
    private Connection koneksi;
    private Statement statement;
    private ResultSet resultSet;
    JLabel lJudul, lUsername, lPassword;
    JTextField tfUser, tfPassword;
    JButton bLogin;

    public ViewLogin() {
        setTitle("LOGIN");
        lJudul = new JLabel("LOGIN ADMIN");
        lUsername = new JLabel("Username : ");
        lPassword = new JLabel("Password : ");

        tfUser = new JTextField();
        tfPassword = new JTextField();

        bLogin = new JButton("Login");

        setLayout(null);
        add(lJudul);
        add(lUsername);
        add(lPassword);
        add(tfUser);
        add(tfPassword);
        add(bLogin);

        lJudul.setBounds(160, 30, 100, 20);
        lUsername.setBounds(70, 70, 80, 25);
        tfUser.setBounds(160, 70, 145, 25);
        lPassword.setBounds(70, 110, 80, 25);
        tfPassword.setBounds(160, 110, 145, 25);
        bLogin.setBounds(160, 150, 80, 25);

        setSize(400, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost/kuispbo";
                    koneksi = DriverManager.getConnection(url, "root", "");
                    statement = koneksi.createStatement();
                    try {
                        String query = "SELECT * FROM tb_users WHERE username = '" + tfUser.getText() + "' "
                                + "AND password = '" + tfPassword.getText() + "'";
                        resultSet = statement.executeQuery(query);
                        if (resultSet.next()) {
                            if (tfUser.getText().equals(resultSet.getString("username"))
                                    && tfPassword.getText().equals(resultSet.getString("password"))) {
                                setVisible(false);
                                MVC mvc = new MVC();
                                JOptionPane.showMessageDialog(null, "Login Berhasil");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Username atau Password Salah");
                        }
                    } catch (Exception sql) {
                        JOptionPane.showMessageDialog(null, sql.getMessage());
                    }
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Class Not found : " + ex);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "SQL Exception : " + ex);
                }
            }
        });
    }
}
