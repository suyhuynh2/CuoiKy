package view;

import controller.Client;

import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import controller.Function;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @author Admin
 */
public class RegisterForm extends javax.swing.JFrame {

    Function func = new Function();

    /**
     * Creates new form DangKiFrm
     */
    public RegisterForm() {
        initComponents();
        this.setTitle("Caro Game");
        this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        jComboBox_Avatar.setMaximumRowCount(5);
        for (int i = 0; i <= 5; i++) {
            jComboBox_Avatar.addItem(new ImageIcon("assets/avatar/" + i + ".jpg"));
        }
        func.displayIcon(jLabel_Image.getWidth(), jLabel_Image.getHeight(), "/image/background_caro.png", jLabel_Image);
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
        framelabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_UserName = new javax.swing.JLabel();
        jLabel_Password = new javax.swing.JLabel();
        jLabel_Avatar = new javax.swing.JLabel();
        nicknameLabel = new javax.swing.JLabel();
        jTextField_UserName = new javax.swing.JTextField();
        jPasswordField_Password = new javax.swing.JPasswordField();
        jTextField_NickName = new javax.swing.JTextField();
        jComboBox_Avatar = new javax.swing.JComboBox<>();
        registerButton = new javax.swing.JButton();
        registerButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel_Image = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        framelabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        framelabel.setForeground(new java.awt.Color(0, 0, 0));
        framelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        framelabel.setText("ĐĂNG KÝ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(framelabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(framelabel, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_UserName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_UserName.setText("Tài Khoản");

        jLabel_Password.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Password.setText("Mật Khẩu");

        jLabel_Avatar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_Avatar.setText("Chọn Avatar");

        nicknameLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nicknameLabel.setText("Nick Name");

        jTextField_UserName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        registerButton.setBackground(new java.awt.Color(204, 255, 255));
        registerButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        registerButton.setText("Đăng kí");
        registerButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        registerButton1.setBackground(new java.awt.Color(204, 255, 255));
        registerButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        registerButton1.setText("Cancel");
        registerButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registerButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registerButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nicknameLabel)
                                    .addComponent(jLabel_UserName)
                                    .addComponent(jLabel_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_Avatar)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_Avatar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(registerButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_NickName, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_UserName)
                .addGap(18, 18, 18)
                .addComponent(jTextField_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_Password)
                .addGap(18, 18, 18)
                .addComponent(jPasswordField_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(nicknameLabel)
                .addGap(18, 18, 18)
                .addComponent(jTextField_NickName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Avatar)
                    .addComponent(jComboBox_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Image, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        try {
            String username = jTextField_UserName.getText();
            if (username.isEmpty()) {
                throw new Exception("Vui lòng nhập tên tài khoản");
            }
            String password = String.copyValueOf(jPasswordField_Password.getPassword());
            if (password.isEmpty()) {
                throw new Exception("Vui lòng nhập mật khẩu");
            }
            String nickName = jTextField_NickName.getText();
            int avatar = jComboBox_Avatar.getSelectedIndex();
            if (avatar == -1) {
                throw new Exception("Vui lòng chọn avatar");
            }
            if (nickName.isEmpty()) {
                throw new Exception("Vui lòng nhập nickname");
            }
            Client.closeAllViews();
            Client.openView(Client.View.GAME_NOTICE, "Đăng kí tài khoản", "Đang chờ phản hồi");
            String encodeUsername = func.encode(username);
            String encodePassword = func.encode(password);
            Client.socketHandle.write("register," + encodeUsername + "," + encodePassword + "," + nickName + "," + avatar);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void registerButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButton1ActionPerformed
        Client.closeView(Client.View.REGISTER);
        Client.openView(Client.View.LOGIN);
    }//GEN-LAST:event_registerButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel framelabel;
    private javax.swing.JComboBox<ImageIcon> jComboBox_Avatar;
    private javax.swing.JLabel jLabel_Avatar;
    private javax.swing.JLabel jLabel_Image;
    private javax.swing.JLabel jLabel_Password;
    private javax.swing.JLabel jLabel_UserName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField_Password;
    private javax.swing.JTextField jTextField_NickName;
    private javax.swing.JTextField jTextField_UserName;
    private javax.swing.JLabel nicknameLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton registerButton1;
    // End of variables declaration//GEN-END:variables
}