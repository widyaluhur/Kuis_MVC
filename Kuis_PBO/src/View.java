import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {
    JLabel lJudul, lTipe, lStatus, lEpisode, lRating, lGenre;
    JTextField tfJudul, tfTipe, tfEpisode, tfRating, tfGenre, tfSearch;
    String status[] = {"Selesai", "Belum"};
    JComboBox cbStatus;
    JButton bRefresh, bCreate, bUpdate, bDelete, bExit, bSearch;
    JTable tabel;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"#", "ID", "Judul", "Tipe", "Episode", "Genre", "Status", "Rating"};

    public View() {
        setTitle("Pengolahan Data Film");
        tableModel = new DefaultTableModel(namaKolom, 0);
        tabel = new JTable(tableModel);
        scrollPane = new JScrollPane(tabel);

        lJudul = new JLabel("Judul");
        lTipe = new JLabel("Tipe");
        lStatus = new JLabel("Status");
        lEpisode = new JLabel("Episode");
        lRating = new JLabel("Rating");
        lGenre = new JLabel("Genre");

        tfJudul = new JTextField();
        tfTipe = new JTextField();
        tfEpisode = new JTextField();
        tfRating = new JTextField();
        tfGenre = new JTextField();
        tfSearch = new JTextField();
        cbStatus = new JComboBox(status);

        bRefresh = new JButton("Refresh");
        bCreate = new JButton("Create");
        bUpdate = new JButton("Update");
        bDelete = new JButton("Delete");
        bExit = new JButton("Exit");
        bSearch = new JButton("Search");

        setLayout(null);
        add(scrollPane);
        add(lJudul);
        add(tfJudul);
        add(lTipe);
        add(tfTipe);
        add(lStatus);
        add(cbStatus);
        add(lEpisode);
        add(tfEpisode);
        add(lRating);
        add(tfRating);
        add(lGenre);
        add(tfGenre);
        add(tfSearch);

        add(bSearch);
        add(bRefresh);
        add(bCreate);
        add(bUpdate);
        add(bDelete);
        add(bExit);

        scrollPane.setBounds(40, 350, 1000, 350);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        lJudul.setBounds(40, 40, 100, 30);
        tfJudul.setBounds(40, 80, 350, 25);
        lTipe.setBounds(40, 115, 100, 30);
        tfTipe.setBounds(40, 155, 150, 25);
        lStatus.setBounds(240, 115, 100, 30);
        cbStatus.setBounds(240, 155, 150, 25);
        lEpisode.setBounds(40, 190, 100, 30);
        tfEpisode.setBounds(40, 230, 150, 25);
        lRating.setBounds(240, 190, 100, 30);
        tfRating.setBounds(240, 230, 150, 25);
        lGenre.setBounds(40, 265, 100, 30);
        tfGenre.setBounds(40, 305, 350, 25);

        tfSearch.setBounds(530, 80, 260, 25);
        bSearch.setBounds(810, 80, 100, 30);

        bRefresh.setBounds(430, 305, 100, 30);
        bCreate.setBounds(550, 305, 100, 30);
        bUpdate.setBounds(670, 305, 100, 30);
        bDelete.setBounds(790, 305, 100, 30);
        bExit.setBounds(910, 305, 100, 30);

        setSize(1100, 800);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getJudul() {
        return tfJudul.getText();
    }

    public String getTipe() {
        return tfTipe.getText();
    }

    public int getEpisode() {
        return (Integer.parseInt(tfEpisode.getText()));
    }

    public int getRating() {
        return Integer.parseInt(tfRating.getText());
    }

    public String getGenre() {
        return tfGenre.getText();
    }

    public String getStatus() {
        return cbStatus.getSelectedItem().toString();
    }

    public String getSearch() {
        return tfSearch.getText();
    }
}