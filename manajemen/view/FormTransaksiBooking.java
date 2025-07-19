/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manajemen.view;

import manajemen.controller.FutsalController;
import manajemen.model.Booking;
import manajemen.model.Lapangan;
import manajemen.model.User;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class FormTransaksiBooking extends javax.swing.JFrame {

    /**
     * Creates new form FormTransaksiBooking
     */
    public FormTransaksiBooking() {
        initComponents();
        jdcTanggal.setMinSelectableDate(new java.util.Date());
        loadDropdownData();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        tblJadwal.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                tblJadwalValueChanged(evt);
            }
        });
    }

    private void loadDropdownData() {
        FutsalController controller = new FutsalController();

        cmbPelanggan.removeAllItems(); 
        List<User> daftarPelanggan = controller.getAllPelanggan();
        for (User pelanggan : daftarPelanggan) {
            cmbPelanggan.addItem(pelanggan);
        }

        cmbLapangan.removeAllItems(); 
        List<Lapangan> daftarLapangan = controller.getAllLapangan();
        for (Lapangan lapangan : daftarLapangan) {
            cmbLapangan.addItem(lapangan);
        }
    }

    private void displayJadwal(List<Booking> bookings) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Jam");
        model.addColumn("Status");
        model.addColumn("Dipesan oleh");

        int jamMulaiOperasional = 8;
        int jamSelesaiOperasional = 23;

        for (int jam = jamMulaiOperasional; jam < jamSelesaiOperasional; jam++) {
            String slotWaktu = String.format("%02d:00 - %02d:00", jam, jam + 1);
            String status = "Tersedia";
            String pemesan = "-";

            for (Booking booking : bookings) {
                int jamBookingMulai = booking.getJamMulai().toLocalTime().getHour();
                int durasiBooking = booking.getDurasiJam();
                int jamBookingSelesai = jamBookingMulai + durasiBooking;

                if (jam >= jamBookingMulai && jam < jamBookingSelesai) {
                    status = "DIBOOKING";
                    pemesan = booking.getPelanggan().getNamaLengkap();
                    break;
                }
            }
            model.addRow(new Object[]{slotWaktu, status, pemesan});
        }

        tblJadwal.setModel(model);
    }

    private void tblJadwalValueChanged(javax.swing.event.ListSelectionEvent evt) {
        int[] selectedRows = tblJadwal.getSelectedRows();

        if (!evt.getValueIsAdjusting() && selectedRows.length > 0) {

            boolean pilihanValid = true;
            for (int row : selectedRows) {
                if (!tblJadwal.getValueAt(row, 1).toString().equals("Tersedia")) {
                    pilihanValid = false;
                    break;
                }
            }

            if (pilihanValid && selectedRows.length > 1) {
                java.util.Arrays.sort(selectedRows);
                for (int i = 0; i < selectedRows.length - 1; i++) {
                    if (selectedRows[i + 1] != selectedRows[i] + 1) {
                        pilihanValid = false; // Ditemukan jeda!
                        break;
                    }
                }
            }

            if (pilihanValid) {
                String jamMulai = tblJadwal.getValueAt(selectedRows[0], 0).toString().substring(0, 5);
                String jamSelesai = tblJadwal.getValueAt(selectedRows[selectedRows.length - 1], 0).toString().substring(8, 13);

                int durasi = selectedRows.length;

                Lapangan lapanganTerpilih = (Lapangan) cmbLapangan.getSelectedItem();
                double totalHarga = lapanganTerpilih.getHargaSewaPerJam() * durasi;

                txtJamPilihan.setText(jamMulai + " - " + jamSelesai);
                txtHargaPilihan.setText(String.valueOf(totalHarga));

            } else {
                JOptionPane.showMessageDialog(this, "Pilihan tidak valid! Pastikan semua jam yang dipilih 'Tersedia' dan berurutan.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                tblJadwal.clearSelection(); // Batalkan pilihan
                txtJamPilihan.setText("");
                txtHargaPilihan.setText("");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        panelSidebar = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnBooking = new javax.swing.JButton();
        btnUsers = new javax.swing.JButton();
        btnLapangan = new javax.swing.JButton();
        btnRiwayat = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnInventaris = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        judul = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jdcTanggal = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbLapangan = new javax.swing.JComboBox<>();
        btnCekJadwal = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        cmbPelanggan = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtJamPilihan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtHargaPilihan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnSimpanBooking = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblJadwal = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Transaksi Booking - Manajemen Futsal");
        setLocationByPlatform(true);
        setResizable(false);

        jPanel1.setMinimumSize(new java.awt.Dimension(1310, 740));
        jPanel1.setPreferredSize(new java.awt.Dimension(1310, 740));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSidebar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel6.setText("CallmeFutsal");

        btnBooking.setBackground(new java.awt.Color(153, 227, 62));
        btnBooking.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnBooking.setText("Booking");
        btnBooking.setEnabled(false);
        btnBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingActionPerformed(evt);
            }
        });

        btnUsers.setBackground(new java.awt.Color(153, 227, 62));
        btnUsers.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnUsers.setText("Users");
        btnUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsersActionPerformed(evt);
            }
        });

        btnLapangan.setBackground(new java.awt.Color(153, 227, 62));
        btnLapangan.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnLapangan.setText("Lapangan");
        btnLapangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLapanganActionPerformed(evt);
            }
        });

        btnRiwayat.setBackground(new java.awt.Color(153, 227, 62));
        btnRiwayat.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnRiwayat.setText("Riwayat");
        btnRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRiwayatActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/manajemen/assets/callmefutsal-ikon.png"))); // NOI18N

        btnInventaris.setBackground(new java.awt.Color(153, 227, 62));
        btnInventaris.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnInventaris.setText("Inventaris");
        btnInventaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSidebarLayout = new javax.swing.GroupLayout(panelSidebar);
        panelSidebar.setLayout(panelSidebarLayout);
        panelSidebarLayout.setHorizontalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSidebarLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel8))
                    .addGroup(panelSidebarLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnLapangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUsers, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBooking, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        panelSidebarLayout.setVerticalGroup(
            panelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSidebarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(87, 87, 87)
                .addComponent(btnBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLapangan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRiwayat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(286, Short.MAX_VALUE))
        );

        jPanel1.add(panelSidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 790));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        judul.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        judul.setText("Manajemen Futsal");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(judul, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(938, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(judul)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 1110, 60));

        panelContent.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel7.setText("List Booking");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Filter Lapangan", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 14))); // NOI18N

        jLabel3.setText("Pilih Tanggal");

        jLabel2.setText("Pilih Lapangan");

        btnCekJadwal.setBackground(new java.awt.Color(153, 227, 62));
        btnCekJadwal.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnCekJadwal.setText("Cek Jadwal");
        btnCekJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekJadwalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbLapangan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jdcTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(61, 61, 61))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(btnCekJadwal))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLapangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCekJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Data Booking", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 14))); // NOI18N

        cmbPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPelangganActionPerformed(evt);
            }
        });

        jLabel1.setText("Pilih Pelanggan");

        txtJamPilihan.setEditable(false);

        jLabel4.setText("Jam Pilihan");

        txtHargaPilihan.setEditable(false);

        jLabel5.setText("Harga");

        btnSimpanBooking.setBackground(new java.awt.Color(153, 227, 62));
        btnSimpanBooking.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        btnSimpanBooking.setText("Booking");
        btnSimpanBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanBookingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cmbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnSimpanBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtJamPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHargaPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJamPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHargaPilihan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSimpanBooking, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblJadwal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblJadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblJadwal.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tblJadwal);

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelContentLayout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7)))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(11, 11, 11)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
        );

        jPanel1.add(panelContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 1020, 660));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCekJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekJadwalActionPerformed
        Lapangan lapanganTerpilih = (Lapangan) cmbLapangan.getSelectedItem();
        java.util.Date tanggalDipilih = jdcTanggal.getDate();

        if (lapanganTerpilih == null || tanggalDipilih == null) {
            JOptionPane.showMessageDialog(this, "Silakan pilih lapangan dan tanggal terlebih dahulu.", "Input Tidak Lengkap", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalStr = sdf.format(tanggalDipilih);

        try {
            java.util.Date utilDate = sdf.parse(tanggalStr);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            FutsalController controller = new FutsalController();
            List<Booking> bookings = controller.getBookingsByDateAndLapangan(lapanganTerpilih.getLapanganId(), sqlDate);

            displayJadwal(bookings);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Format tanggal salah. Gunakan format YYYY-MM-DD.", "Error Format Tanggal", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCekJadwalActionPerformed

    private void btnSimpanBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanBookingActionPerformed
        if (txtJamPilihan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan pilih slot waktu yang tersedia dari tabel.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User pelangganTerpilih = (User) cmbPelanggan.getSelectedItem();
        Lapangan lapanganTerpilih = (Lapangan) cmbLapangan.getSelectedItem();

        java.util.Date tanggalDipilih = jdcTanggal.getDate();

        if (tanggalDipilih == null) {
            JOptionPane.showMessageDialog(this, "Tanggal booking belum dipilih.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalStr = sdf.format(tanggalDipilih);

        String jamMulaiStr = txtJamPilihan.getText().substring(0, 5);
        int durasi = tblJadwal.getSelectedRows().length;
        double totalHarga = Double.parseDouble(txtHargaPilihan.getText());
        try {
            java.util.Date utilDate = sdf.parse(tanggalStr);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
            long ms = sdfTime.parse(jamMulaiStr).getTime();
            java.sql.Time sqlTime = new java.sql.Time(ms);

            User adminLogin = new User("ADM01", "Admin Utama", null, null, null, "admin");
            String bookingId = "BK" + System.currentTimeMillis();

            Booking bookingBaru = new Booking(
                    bookingId,
                    pelangganTerpilih,
                    lapanganTerpilih,
                    adminLogin,
                    sqlDate,
                    sqlTime,
                    durasi,
                    totalHarga,
                    "Tunai",
                    "Belum Lunas",
                    new java.sql.Timestamp(System.currentTimeMillis())
            );
            FutsalController controller = new FutsalController();
            boolean sukses = controller.simpanBooking(bookingBaru);

            if (sukses) {
                JOptionPane.showMessageDialog(this, "Booking berhasil disimpan!");
                btnCekJadwal.doClick();
                txtJamPilihan.setText("");
                txtHargaPilihan.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan booking.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan format tanggal atau jam.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSimpanBookingActionPerformed

    private void cmbPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPelangganActionPerformed
        //
    }//GEN-LAST:event_cmbPelangganActionPerformed

    private void btnBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingActionPerformed
        //
    }//GEN-LAST:event_btnBookingActionPerformed

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        this.dispose();
        new FormManajemenPengguna().setVisible(true);
    }//GEN-LAST:event_btnUsersActionPerformed

    private void btnRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRiwayatActionPerformed
        this.dispose();
        new FormLaporanBooking().setVisible(true);
    }//GEN-LAST:event_btnRiwayatActionPerformed

    private void btnLapanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLapanganActionPerformed
        this.dispose();
        new FormManajemenLapangan().setVisible(true);
    }//GEN-LAST:event_btnLapanganActionPerformed

    private void btnInventarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarisActionPerformed
        this.dispose();
        new FormManajemenInventaris().setVisible(true);
    }//GEN-LAST:event_btnInventarisActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormTransaksiBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTransaksiBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTransaksiBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTransaksiBooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormTransaksiBooking().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBooking;
    private javax.swing.JButton btnCekJadwal;
    private javax.swing.JButton btnInventaris;
    private javax.swing.JButton btnLapangan;
    private javax.swing.JButton btnRiwayat;
    private javax.swing.JButton btnSimpanBooking;
    private javax.swing.JButton btnUsers;
    private javax.swing.JComboBox<Lapangan> cmbLapangan;
    private javax.swing.JComboBox<User> cmbPelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdcTanggal;
    public javax.swing.JLabel judul;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelSidebar;
    private javax.swing.JTable tblJadwal;
    private javax.swing.JTextField txtHargaPilihan;
    private javax.swing.JTextField txtJamPilihan;
    // End of variables declaration//GEN-END:variables
}
