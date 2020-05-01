import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller {
    Model model;
    View view;
    DAO dao;

    public Controller(Model model, View view, DAO dao) {
        this.model = model;
        this.view = view;
        this.dao = dao;

        if (dao.getJmlData() != 0) {
            String datas[][] = dao.read();
            view.tabel.setModel((new JTable(datas, view.namaKolom)).getModel());
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }

        view.tabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pilih = view.tabel.getSelectedRow();
                if (pilih == -1) {
                    return;
                }
                String judul = (String) view.tabel.getValueAt(pilih, 2);
                view.tfJudul.setText(judul);
                String tipe = (String) view.tabel.getValueAt(pilih, 3);
                view.tfTipe.setText(tipe);
                String episode = (String) view.tabel.getValueAt(pilih, 4);
                view.tfEpisode.setText(episode);
                String genre = (String) view.tabel.getValueAt(pilih, 5);
                view.tfGenre.setText(genre);
                String rating = (String) view.tabel.getValueAt(pilih, 7);
                view.tfRating.setText(rating);
            }
        });

        view.bSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = view.getSearch();
                model.setSearch(search);
                String datas[][] = dao.search(model);
                view.tabel.setModel((new JTable(datas, view.namaKolom)).getModel());
            }
        });

        view.bRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datas[][] = dao.read();
                view.tfJudul.setText("");
                view.tfTipe.setText("");
                view.tfEpisode.setText("");
                view.tfRating.setText("");
                view.tfGenre.setText("");
                view.tabel.setModel((new JTable(datas, view.namaKolom)).getModel());
            }
        });

        view.bCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String judul = view.getJudul();
                    String tipe = view.getTipe();
                    Integer episode = view.getEpisode();
                    String genre = view.getGenre();
                    String status = view.getStatus();
                    Integer rating = view.getRating();

                    model.setModel(judul, tipe, episode, genre, status, rating);
                    dao.insert(model);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Harap isi semua field");
                }
            }
        });

        view.bUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int baris = view.tabel.getSelectedRow();

                if (baris >= 0) {
                    String dataTerpilihX = view.tabel.getValueAt(baris, 1).toString();
                    int dataTerpilih = Integer.parseInt(dataTerpilihX);

                    int input = JOptionPane.showConfirmDialog(null, "Apa anda ingin UPDATE ID : " + dataTerpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

                    if (input == 0) {
                        String judul = view.getJudul();
                        String tipe = view.getTipe();
                        int episode = view.getEpisode();
                        String genre = view.getGenre();
                        String status = view.getStatus();
                        int rating = view.getRating();

                        dao.update(dataTerpilih, judul, tipe, episode, genre, status, rating);
                    } else {
                        JOptionPane.showMessageDialog(null, "Tidak Jadi Diupdate");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan diupdate dahulu");
                }
            }
        });

        view.bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int baris = view.tabel.getSelectedRow();

                if (baris >= 0) {
                    String dataTerpilihX = view.tabel.getValueAt(baris, 1).toString();
                    int dataTerpilih = Integer.parseInt(dataTerpilihX);

                    int input = JOptionPane.showConfirmDialog(null, "Apa anda ingin menghapus ID : " + dataTerpilih + "?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

                    if (input == 0) {
                        dao.delete(dataTerpilih);
                    } else {
                        JOptionPane.showMessageDialog(null, "Tidak Jadi Dihapus");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih data yang akan didelete dahulu");
                }
            }
        });

        view.bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int input = JOptionPane.showConfirmDialog(null, "Apa anda yakin ingin keluar?", "Pilih Opsi...", JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    System.exit(0);
                }
            }
        });
    }
}

