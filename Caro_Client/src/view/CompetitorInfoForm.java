/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import controller.Client;
import controller.Function;

import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.User;

/**
 * @author Admin
 */
public class CompetitorInfoForm extends javax.swing.JFrame {
    private boolean isFriend;
    private User user;
    
    controller.Function func = new Function();

    /**
     * Creates new form CompetitorInfoFrm
     */
    public CompetitorInfoForm(User user) {
        try {
            initComponents();
            this.user = user;
            this.setTitle("Caro Game");
            this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
            this.setResizable(false);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            func.displayImage("assets/avatar/" + user.getAvatar() + ".jpg", jLabel_Avatar, null);
//           jLabel_Avatar.setIcon(new ImageIcon());
            jLabel_NickNameValue.setText(user.getNickname());
            jLabel_NumberOfGameValue.setText("" + user.getNumberOfGame());
            jLabel_NumberOfWinValue.setText("" + user.getNumberOfWin());
            jLabel_NumberOfDrawValue.setText("" + user.getNumberOfDraw());
            jLabel_RankValue.setText("" + user.getRank());
            if (user.getNumberOfGame() == 0) {
                jLabel_WinRatioValue.setText("-");
            } else {
                jLabel_WinRatioValue.setText(String.format("%.2f", (float) user.getNumberOfWin() / user.getNumberOfGame() * 100) + "%");
            }
            jLabel_MarkValue.setText("" + (user.getNumberOfWin() * 10 + user.getNumberOfGame()));
            Client.socketHandle.write("check-friend," + user.getID());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    public void checkFriend(boolean isFriend) {
        this.isFriend = isFriend;
        if (isFriend) {
//            jButton_AddFriend_.setIcon(new ImageIcon("assets/icon/friendship.png"));
//            jButton_AddFriend_.setToolTipText("Bạn bè");
            jLabel_IsFriend.setText("Đã là bạn bè");

        } else {
//            jButton_AddFriend_.setIcon(new ImageIcon("assets/icon/add-friend.png"));
//            jButton_AddFriend_.setToolTipText("Click để gửi yêu cầu kết bạn");
            jLabel_IsFriend.setText("Chưa là bạn bè");
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
        frameLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_Avatar = new javax.swing.JLabel();
        jLabel_NickName = new javax.swing.JLabel();
        jLabel_NickNameValue = new javax.swing.JLabel();
        jLabel_NumberOfGame = new javax.swing.JLabel();
        jLabel_NumberOfGameValue = new javax.swing.JLabel();
        jLabel_NumberOfWin = new javax.swing.JLabel();
        jLabel_NumberOfWinValue = new javax.swing.JLabel();
        jLabel_NumberOfDraw = new javax.swing.JLabel();
        jLabel_NumberOfDrawValue = new javax.swing.JLabel();
        jLabel_WinRatio = new javax.swing.JLabel();
        jLabel_WinRatioValue = new javax.swing.JLabel();
        jLabel_Mark = new javax.swing.JLabel();
        jLabel_MarkValue = new javax.swing.JLabel();
        jLabel_Rank = new javax.swing.JLabel();
        jLabel_RankValue = new javax.swing.JLabel();
        jButton_AddFriend_ = new javax.swing.JButton();
        jLabel_IsFriend = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));

        frameLabel.setBackground(new java.awt.Color(0, 0, 0));
        frameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        frameLabel.setForeground(new java.awt.Color(0, 0, 0));
        frameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        frameLabel.setText("THÔNG TIN ĐỐI THỦ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(frameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(frameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_Avatar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel_NickName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_NickName.setText("Nickname");

        jLabel_NickNameValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_NickNameValue.setText("{nickname}");

        jLabel_NumberOfGame.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_NumberOfGame.setText("Số ván chơi");

        jLabel_NumberOfGameValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_NumberOfGameValue.setText("{sovachoi}");

        jLabel_NumberOfWin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_NumberOfWin.setText("Số ván thắng");

        jLabel_NumberOfWinValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_NumberOfWinValue.setText("{sovanthang}");

        jLabel_NumberOfDraw.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_NumberOfDraw.setText("Số ván hòa");

        jLabel_NumberOfDrawValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_NumberOfDrawValue.setText("{sovanhoa}");

        jLabel_WinRatio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_WinRatio.setText("Tỉ lệ thắng");

        jLabel_WinRatioValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_WinRatioValue.setText("{tillethang}");

        jLabel_Mark.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Mark.setText("Điểm");

        jLabel_MarkValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_MarkValue.setText("{diem}");

        jLabel_Rank.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Rank.setText("Thứ hạng");

        jLabel_RankValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_RankValue.setText("{thuhang}");

        jButton_AddFriend_.setBackground(new java.awt.Color(153, 255, 255));
        jButton_AddFriend_.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_AddFriend_.setText("Kết bạn");
        jButton_AddFriend_.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton_AddFriend_.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_AddFriend_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddFriend_ActionPerformed(evt);
            }
        });

        jLabel_IsFriend.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_IsFriend.setForeground(new java.awt.Color(0, 51, 255));
        jLabel_IsFriend.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_IsFriend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton_AddFriend_, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_NumberOfGame)
                    .addComponent(jLabel_NickName)
                    .addComponent(jLabel_NumberOfWin)
                    .addComponent(jLabel_NumberOfDraw)
                    .addComponent(jLabel_WinRatio)
                    .addComponent(jLabel_Mark)
                    .addComponent(jLabel_Rank))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_RankValue)
                    .addComponent(jLabel_NickNameValue)
                    .addComponent(jLabel_MarkValue)
                    .addComponent(jLabel_WinRatioValue)
                    .addComponent(jLabel_NumberOfDrawValue)
                    .addComponent(jLabel_NumberOfWinValue)
                    .addComponent(jLabel_NumberOfGameValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel_Avatar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_NickName)
                            .addComponent(jLabel_NickNameValue))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_NumberOfGame)
                            .addComponent(jLabel_NumberOfGameValue))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_NumberOfWinValue)
                            .addComponent(jLabel_NumberOfWin))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_NumberOfDraw)
                            .addComponent(jLabel_NumberOfDrawValue))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_WinRatio)
                            .addComponent(jLabel_WinRatioValue))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_Mark)
                            .addComponent(jLabel_MarkValue))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_Rank)
                            .addComponent(jLabel_RankValue))
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton_AddFriend_, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGap(5, 5, 5)
                .addComponent(jLabel_IsFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AddFriend_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddFriend_ActionPerformed
        if (isFriend) {
            JOptionPane.showMessageDialog(rootPane, "Bạn và đối thủ đang là bạn bè");
        } else {
            int res = JOptionPane.showConfirmDialog(rootPane, "Bạn đồng ý gửi lời mời kết bạn tới đối thủ chứ", "Xác nhận yêu cầu kết bạn", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                try {
                    Client.socketHandle.write("make-friend," + user.getID());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_jButton_AddFriend_ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Client.closeView(Client.View.COMPETITOR_INFO);
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel frameLabel;
    private javax.swing.JButton jButton_AddFriend_;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Avatar;
    private javax.swing.JLabel jLabel_IsFriend;
    private javax.swing.JLabel jLabel_Mark;
    private javax.swing.JLabel jLabel_MarkValue;
    private javax.swing.JLabel jLabel_NickName;
    private javax.swing.JLabel jLabel_NickNameValue;
    private javax.swing.JLabel jLabel_NumberOfDraw;
    private javax.swing.JLabel jLabel_NumberOfDrawValue;
    private javax.swing.JLabel jLabel_NumberOfGame;
    private javax.swing.JLabel jLabel_NumberOfGameValue;
    private javax.swing.JLabel jLabel_NumberOfWin;
    private javax.swing.JLabel jLabel_NumberOfWinValue;
    private javax.swing.JLabel jLabel_Rank;
    private javax.swing.JLabel jLabel_RankValue;
    private javax.swing.JLabel jLabel_WinRatio;
    private javax.swing.JLabel jLabel_WinRatioValue;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}