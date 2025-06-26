/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package manajemen.view;

import java.awt.Color;
import javax.swing.BorderFactory;
import manajemen.controller.FutsalController;
import manajemen.model.User;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author ACER
 */
public class FormInputPengguna extends javax.swing.JDialog {

    // Definisikan variabel warna di tingkat kelas
    final private Color simpanNormal = new Color(153, 227, 62);
    final private Color simpanHover = new Color(90, 140, 30);
    final private Color batalNormal = new Color(255, 62, 65);
    final private Color batalHover = new Color(160, 30, 35);
    // Atribut untuk mode edit
    private User userToEdit;

    /**
     * Creates new form FormInputPengguna
     */
    public FormInputPengguna(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        // Atur semua placeholder saat form pertama kali dibuat
        initializePlaceholders();
        updateUserFieldsState();
    }
// Constructor untuk mode Edit

    public FormInputPengguna(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.userToEdit = user;
        populateForm();
        updateUserFieldsState(); 
    }

    // Helper untuk Placeholder
    private void addPlaceholder(JTextField field, String placeholder) {
        if (field.getText().isEmpty()) {
            field.setText(placeholder);
            field.setForeground(Color.GRAY);
        }
    }

    private void removePlaceholder(JTextField field, String placeholder) {
        if (field.getText().equals(placeholder)) {
            field.setText("");
            field.setForeground(Color.BLACK);
        }
    }

    // Overload untuk JPasswordField
    private void addPlaceholder(JPasswordField field, String placeholder) {
        // Untuk password, kita set echo char agar teks placeholder terlihat
        char defaultChar = field.getEchoChar();
        if (new String(field.getPassword()).isEmpty()) {
            field.setEchoChar((char) 0); // Tampilkan teks biasa
            field.setText(placeholder);
            field.setForeground(Color.GRAY);
        }
    }

    private void removePlaceholder(JPasswordField field, String placeholder) {
        char defaultChar = new JPasswordField().getEchoChar(); // Dapatkan echo char default
        if (new String(field.getPassword()).equals(placeholder)) {
            field.setText("");
            field.setEchoChar(defaultChar); // Kembalikan mode password
            field.setForeground(Color.BLACK);
        }
    }

    // Method baru untuk menginisialisasi semua placeholder
    private void initializePlaceholders() {
        addPlaceholder(txtId, "Masukkan ID");
        addPlaceholder(txtNama, "Masukkan Nama");
        addPlaceholder(txtNoHp, "Masukkan No Hp");
        addPlaceholder(txtUsername, "Username (opsional)");
        addPlaceholder(pwdPassword, "Password (opsional)");
    }
// Method untuk mengisi form saat mode edit

    private void populateForm() {
        txtId.setText(userToEdit.getUserId());
        txtNama.setText(userToEdit.getNamaLengkap());
        txtNoHp.setText(userToEdit.getNoHp());
        txtUsername.setText(userToEdit.getUsername());
        // Kosongkan password demi keamanan, hanya diisi jika ingin diubah
        pwdPassword.setText("");
        cmbRole.setSelectedItem(userToEdit.getRole());
        // Saat mode edit, text field sudah terisi, jadi warnanya hitam
        txtId.setForeground(Color.BLACK);
        txtNama.setForeground(Color.BLACK);
        txtNoHp.setForeground(Color.BLACK);
        txtUsername.setForeground(Color.BLACK);

        // Tambahkan placeholder untuk password yang dikosongkan
        addPlaceholder(pwdPassword, "Password (opsional)");

        txtId.setEditable(false);
    }

    private void updateUserFieldsState() {
        // 1. Ambil role yang sedang dipilih
        String selectedRole = (String) cmbRole.getSelectedItem();

        // 2. Cek apakah role tersebut "Admin"
        if ("Admin".equals(selectedRole)) {
            // Jika "Admin", aktifkan field Username dan Password
            txtUsername.setEnabled(true);
            pwdPassword.setEnabled(true);
            // Kita juga bisa mengembalikan placeholder-nya jika perlu
            addPlaceholder(txtUsername, "Username (opsional)");
            addPlaceholder(pwdPassword, "Password (opsional)");
        } else {
            // Jika bukan "Admin" (alias "Pelanggan"), nonaktifkan field
            txtUsername.setEnabled(false);
            pwdPassword.setEnabled(false);

            // 3. Kosongkan isinya untuk memastikan tidak ada data yang tersimpan
            txtUsername.setText("");
            pwdPassword.setText("");
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNoHp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pwdPassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        cmbRole = new javax.swing.JComboBox<>();
        btnSimpanUser = new javax.swing.JButton();
        btnBatalUser = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(460, 430));

        jPanel1.setToolTipText("Form Input Pengguna");
        jPanel1.setMinimumSize(new java.awt.Dimension(420, 420));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(420, 420));
        jPanel2.setPreferredSize(new java.awt.Dimension(420, 420));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("ID");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        txtId.setBackground(new java.awt.Color(240, 240, 240));
        txtId.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdFocusLost(evt);
            }
        });
        jPanel2.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 180, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nama");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        txtNama.setBackground(new java.awt.Color(240, 240, 240));
        txtNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtNama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNamaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNamaFocusLost(evt);
            }
        });
        jPanel2.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 180, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("No.HP");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));

        txtNoHp.setBackground(new java.awt.Color(240, 240, 240));
        txtNoHp.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtNoHp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNoHpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNoHpFocusLost(evt);
            }
        });
        jPanel2.add(txtNoHp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 180, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Username");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        txtUsername.setBackground(new java.awt.Color(240, 240, 240));
        txtUsername.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsernameFocusLost(evt);
            }
        });
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        jPanel2.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 180, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Password");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        pwdPassword.setBackground(new java.awt.Color(240, 240, 240));
        pwdPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pwdPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pwdPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pwdPasswordFocusLost(evt);
            }
        });
        jPanel2.add(pwdPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 180, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Role");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));

        cmbRole.setBackground(new java.awt.Color(240, 240, 240));
        cmbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pelanggan", "Admin" }));
        cmbRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRoleActionPerformed(evt);
            }
        });
        jPanel2.add(cmbRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, -1, -1));

        btnSimpanUser.setBackground(new java.awt.Color(153, 227, 62));
        btnSimpanUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSimpanUser.setText("Simpan");
        btnSimpanUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSimpanUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSimpanUserMouseExited(evt);
            }
        });
        btnSimpanUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanUserActionPerformed(evt);
            }
        });
        jPanel2.add(btnSimpanUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 110, 40));

        btnBatalUser.setBackground(new java.awt.Color(255, 62, 65));
        btnBatalUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBatalUser.setText("Batal");
        btnBatalUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBatalUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBatalUserMouseExited(evt);
            }
        });
        btnBatalUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalUserActionPerformed(evt);
            }
        });
        jPanel2.add(btnBatalUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 110, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("INPUT DATA USER/PELANGGAN");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void btnSimpanUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanUserActionPerformed
        String id = txtId.getText();
        if (id.equals("Masukkan ID")) {
            id = "";
        }
        String nama = txtNama.getText();
        if (nama.equals("Masukkan Nama")) {
            nama = "";
        }
        String noHp = txtNoHp.getText();
        if (noHp.equals("Masukkan No Hp")) {
            noHp = "";
        }
        String username = txtUsername.getText();
        if (username.equals("Username (opsional)")) {
            username = "";
        }
        String password = new String(pwdPassword.getPassword());
        if (password.equals("Password (opsional)")) {
            password = "";
        }
        String role = (String) cmbRole.getSelectedItem();

        // Validasi sederhana (bisa Anda kembangkan)
        if (id.isEmpty() || nama.isEmpty() || noHp.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID, Nama, No.HP, dan Role tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User(id, nama, noHp, username, password, role);
        FutsalController controller = new FutsalController();
        boolean sukses;

        if (userToEdit == null) { // Mode Tambah
            sukses = controller.tambahUser(user);
        } else { // Mode Edit
            sukses = controller.updateUser(user);
        }

        if (sukses) {
            JOptionPane.showMessageDialog(this, "Data pengguna berhasil disimpan!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data pengguna.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSimpanUserActionPerformed

    private void btnBatalUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalUserActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBatalUserActionPerformed

    private void txtIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFocusGained
        removePlaceholder(txtId, "Masukkan ID");
    }//GEN-LAST:event_txtIdFocusGained

    private void txtIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFocusLost
        addPlaceholder(txtId, "Masukkan ID");
    }//GEN-LAST:event_txtIdFocusLost

    private void txtNamaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNamaFocusGained
        removePlaceholder(txtNama, "Masukkan Nama");
    }//GEN-LAST:event_txtNamaFocusGained

    private void txtNamaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNamaFocusLost
        addPlaceholder(txtNama, "Masukkan Nama");
    }//GEN-LAST:event_txtNamaFocusLost

    private void txtNoHpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoHpFocusGained
        removePlaceholder(txtNoHp, "Masukkan No Hp");
    }//GEN-LAST:event_txtNoHpFocusGained

    private void txtNoHpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNoHpFocusLost
        addPlaceholder(txtNoHp, "Masukkan No Hp");
    }//GEN-LAST:event_txtNoHpFocusLost

    private void txtUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusGained
        removePlaceholder(txtUsername, "Username (opsional)");
    }//GEN-LAST:event_txtUsernameFocusGained

    private void txtUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusLost
        addPlaceholder(txtUsername, "Username (opsional)");
    }//GEN-LAST:event_txtUsernameFocusLost

    private void pwdPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwdPasswordFocusGained
        removePlaceholder(pwdPassword, "Password (opsional)");
    }//GEN-LAST:event_pwdPasswordFocusGained

    private void pwdPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pwdPasswordFocusLost
        addPlaceholder(pwdPassword, "Password (opsional)");
    }//GEN-LAST:event_pwdPasswordFocusLost

    private void btnSimpanUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanUserMouseEntered
        btnSimpanUser.setBackground(simpanHover);
//        btnSimpanUser.setBorder(BorderFactory.createLineBorder(simpanNormal, 2));
    }//GEN-LAST:event_btnSimpanUserMouseEntered

    private void btnSimpanUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanUserMouseExited
        btnSimpanUser.setBackground(simpanNormal);
//        btnSimpanUser.setBorder(null); // Hapus border saat mouse keluar
    }//GEN-LAST:event_btnSimpanUserMouseExited

    private void btnBatalUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalUserMouseEntered
        btnBatalUser.setBackground(batalHover);
//        btnBatalUser.setBorder(BorderFactory.createLineBorder(batalNormal, 2));
    }//GEN-LAST:event_btnBatalUserMouseEntered

    private void btnBatalUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalUserMouseExited
        btnBatalUser.setBackground(batalNormal);
//        btnBatalUser.setBorder(null); // Hapus border saat mouse keluar
    }//GEN-LAST:event_btnBatalUserMouseExited

    private void cmbRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRoleActionPerformed
        updateUserFieldsState();
    }//GEN-LAST:event_cmbRoleActionPerformed

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
            java.util.logging.Logger.getLogger(FormInputPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormInputPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormInputPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormInputPengguna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormInputPengguna dialog = new FormInputPengguna(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatalUser;
    private javax.swing.JButton btnSimpanUser;
    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField pwdPassword;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
