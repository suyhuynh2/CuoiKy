/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * @author Admin
 */
public class FindRoomForm extends javax.swing.JFrame {
    private Timer timer;
    private boolean found;

    /**
     * Creates new form FindRoomFrm
     */
    public FindRoomForm() {
        initComponents();
        this.setTitle("Caro Game");
        this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        jProgressBar1.setValue(70);
        found = false;
        startFind();
        sendFindRequest();
    }

    public void stopAllThread() {
        timer.stop();
    }

    public void startFind() {
        jLabel_Found.setVisible(false);
        timer = new Timer(1000, new ActionListener() {
            int count = 20;

            @Override
            public void actionPerformed(ActionEvent e) {
                count--;
                if (count >= 0) {
                    if (count >= 10)
                        jLabel_CountDouwnTime.setText("00:" + count);
                    else
                        jLabel_CountDouwnTime.setText("00:0" + count);
                    jProgressBar1.setValue(Math.round((float) count / 20 * 100));
                } else {
                    ((Timer) (e.getSource())).stop();
                    try {
                        Client.socketHandle.write("cancel-room,");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    }
                    int res = JOptionPane.showConfirmDialog(rootPane, "Tìm kiếm thất bại, bạn muốn thử lại lần nữa chứ?");
                    if (res == JOptionPane.YES_OPTION) {
                        startFind();
                        sendFindRequest();
                    } else {
                        //Có thể hỏi chơi với máy không
                        Client.closeView(Client.View.FIND_ROOM);
                        Client.openView(Client.View.HOMEPAGE);
                    }
                }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public void sendFindRequest() {
        try {
            Client.socketHandle.write("quick-room,");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    public void showFoundRoom() {
        found = true;
        timer.stop();
        jLabel_Found.setVisible(true);
        jLabel_Finding.setVisible(false);

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
        jLabel_Title = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton_Loading = new javax.swing.JButton();
        jLabel_Found = new javax.swing.JLabel();
        jLabel_Finding = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel_CountDouwnTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(440, 220));

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel_Title.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_Title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_Title.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Title.setText("TÌM PHÒNG NHANH");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Title, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton_Loading.setBackground(new java.awt.Color(204, 255, 255));
        jButton_Loading.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_Loading.setText("Cancel");
        jButton_Loading.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton_Loading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoadingActionPerformed(evt);
            }
        });

        jLabel_Found.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Found.setForeground(new java.awt.Color(0, 51, 204));
        jLabel_Found.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Found.setText("Đã tìm thấy đối thủ, đang vào phòng");

        jLabel_Finding.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel_Finding.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Finding.setText("Đang tìm đối thủ");

        jLabel_CountDouwnTime.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel_CountDouwnTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_CountDouwnTime.setText("00:20");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel_Finding, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_CountDouwnTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_Found, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Loading, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel_CountDouwnTime)
                .addGap(18, 18, 18)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel_Finding)
                .addGap(18, 18, 18)
                .addComponent(jLabel_Found)
                .addGap(18, 18, 18)
                .addComponent(jButton_Loading)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_LoadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoadingActionPerformed
        if (found)
            return;
        try {
            Client.socketHandle.write("cancel-room,");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        timer.stop();
        Client.closeView(Client.View.FIND_ROOM);
        Client.openView(Client.View.HOMEPAGE);
    }//GEN-LAST:event_jButton_LoadingActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Loading;
    private javax.swing.JLabel jLabel_CountDouwnTime;
    private javax.swing.JLabel jLabel_Finding;
    private javax.swing.JLabel jLabel_Found;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
