package view;


import controller.Client;

import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Admin
 */
public class HomePageForm extends javax.swing.JFrame {
    /**
     * Creates new form GiaoDienChinhFrm
     */
    public HomePageForm() {
        initComponents();
        this.setTitle("Caro Game Nhóm 5");
        this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        jLabel4.setText(Client.user.getNickname());
        numberOfWinValue.setText(Integer.toString(Client.user.getNumberOfWin()));
        numberOfGameValue.setText(Integer.toString(Client.user.getNumberOfGame()));
        jLabel8.setIcon(new ImageIcon("assets/avatar/" + Client.user.getAvatar() + ".jpg"));
//        sendMessageButton.setIcon(new ImageIcon("assets/image/send2.png"));
        messageTextArea.setEditable(false);
        if (Client.user.getNumberOfGame() == 0) {
            winRatioValue.setText("-");
        } else {
            winRatioValue.setText(String.format("%.2f", (float) Client.user.getNumberOfWin() / Client.user.getNumberOfGame() * 100) + "%");
        }
        drawValue.setText("" + Client.user.getNumberOfDraw());
        markValue.setText("" + (Client.user.getNumberOfGame() + Client.user.getNumberOfWin() * 10));
        rankValue.setText("" + Client.user.getRank());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel2 = new javax.swing.JPanel();
        quickGameButton = new javax.swing.JButton();
        goRoomButton = new javax.swing.JButton();
        createRoomButton = new javax.swing.JButton();
        findRoomButton = new javax.swing.JButton();
        playWithBotButton = new javax.swing.JButton();
        friendListButton = new javax.swing.JButton();
        scoreBoardButton = new javax.swing.JButton();
        scoreBotButton = new javax.swing.JButton();
        exitGameButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        numberOfWinLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numberOfWinValue = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        numberOfGameValue = new javax.swing.JLabel();
        numberOfGameLabel = new javax.swing.JLabel();
        markLabel = new javax.swing.JLabel();
        markValue = new javax.swing.JLabel();
        rankLabel = new javax.swing.JLabel();
        rankValue = new javax.swing.JLabel();
        winRatioLabel = new javax.swing.JLabel();
        winRatioValue = new javax.swing.JLabel();
        drawLabel = new javax.swing.JLabel();
        drawValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        messageInput = new javax.swing.JTextField();
        sendMessageButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        frameLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));

        quickGameButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        quickGameButton.setForeground(new java.awt.Color(255, 51, 51));
        quickGameButton.setText("Chơi nhanh");
        quickGameButton.setContentAreaFilled(false);
        quickGameButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quickGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quickGameButtonActionPerformed(evt);
            }
        });

        goRoomButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        goRoomButton.setForeground(new java.awt.Color(255, 51, 51));
        goRoomButton.setText("Vào phòng");
        goRoomButton.setContentAreaFilled(false);
        goRoomButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        goRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goRoomButtonActionPerformed(evt);
            }
        });

        createRoomButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        createRoomButton.setForeground(new java.awt.Color(255, 51, 51));
        createRoomButton.setText("Tạo Phòng");
        createRoomButton.setContentAreaFilled(false);
        createRoomButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRoomButtonActionPerformed(evt);
            }
        });

        findRoomButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        findRoomButton.setForeground(new java.awt.Color(255, 51, 51));
        findRoomButton.setText("Tìm phòng");
        findRoomButton.setContentAreaFilled(false);
        findRoomButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        findRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findRoomButtonActionPerformed(evt);
            }
        });

        playWithBotButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        playWithBotButton.setForeground(new java.awt.Color(255, 51, 51));
        playWithBotButton.setText("Chơi với máy");
        playWithBotButton.setContentAreaFilled(false);
        playWithBotButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        playWithBotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playWithBotButtonActionPerformed(evt);
            }
        });

        friendListButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        friendListButton.setForeground(new java.awt.Color(255, 51, 51));
        friendListButton.setText("Danh sách bạn bè");
        friendListButton.setContentAreaFilled(false);
        friendListButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        friendListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendListButtonActionPerformed(evt);
            }
        });

        scoreBoardButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        scoreBoardButton.setForeground(new java.awt.Color(255, 51, 51));
        scoreBoardButton.setText("Bảng xếp hạng");
        scoreBoardButton.setContentAreaFilled(false);
        scoreBoardButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        scoreBoardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreBoardButtonActionPerformed(evt);
            }
        });

        scoreBotButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        scoreBotButton.setForeground(new java.awt.Color(255, 51, 51));
        scoreBotButton.setText("Đăng xuất");
        scoreBotButton.setContentAreaFilled(false);
        scoreBotButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        scoreBotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreBotButtonActionPerformed(evt);
            }
        });

        exitGameButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        exitGameButton.setForeground(new java.awt.Color(255, 51, 51));
        exitGameButton.setText("Thoát Game");
        exitGameButton.setContentAreaFilled(false);
        exitGameButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitGameButtonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 5));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("NickName");
        jLabel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jLabel1AncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        numberOfWinLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numberOfWinLabel.setForeground(new java.awt.Color(0, 0, 0));
        numberOfWinLabel.setText("Số ván thắng");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("{day la Nick name}");

        numberOfWinValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numberOfWinValue.setForeground(new java.awt.Color(0, 0, 0));
        numberOfWinValue.setText("{day la so van thang}");

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));

        numberOfGameValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numberOfGameValue.setForeground(new java.awt.Color(0, 0, 0));
        numberOfGameValue.setText("{day la so van da choi}");

        numberOfGameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numberOfGameLabel.setForeground(new java.awt.Color(0, 0, 0));
        numberOfGameLabel.setText("Số ván đã chơi");

        markLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        markLabel.setForeground(new java.awt.Color(0, 0, 0));
        markLabel.setText("Điểm");

        markValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        markValue.setForeground(new java.awt.Color(0, 0, 0));
        markValue.setText("{day la diem}");

        rankLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rankLabel.setForeground(new java.awt.Color(0, 0, 0));
        rankLabel.setText("Thứ hạng");

        rankValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rankValue.setForeground(new java.awt.Color(0, 0, 0));
        rankValue.setText("{day la thu hang}");

        winRatioLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        winRatioLabel.setForeground(new java.awt.Color(0, 0, 0));
        winRatioLabel.setText("Tỉ lệ thắng");

        winRatioValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        winRatioValue.setForeground(new java.awt.Color(0, 0, 0));
        winRatioValue.setText("{day la ti le thang}");

        drawLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        drawLabel.setForeground(new java.awt.Color(0, 0, 0));
        drawLabel.setText("Số ván hòa");

        drawValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        drawValue.setForeground(new java.awt.Color(0, 0, 0));
        drawValue.setText("{day la so van hoa}");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberOfWinLabel)
                            .addComponent(jLabel1)
                            .addComponent(numberOfGameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(numberOfGameValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(numberOfWinValue)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(markLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rankLabel)
                            .addComponent(winRatioLabel)
                            .addComponent(drawLabel))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(drawValue)
                            .addComponent(winRatioValue)
                            .addComponent(markValue)
                            .addComponent(rankValue))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(numberOfGameValue))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numberOfGameLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numberOfWinValue)
                            .addComponent(numberOfWinLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drawLabel)
                            .addComponent(drawValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(winRatioLabel)
                            .addComponent(winRatioValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(markValue)
                            .addComponent(markLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rankLabel)
                            .addComponent(rankValue))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        messageTextArea.setColumns(20);
        messageTextArea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        messageTextArea.setRows(5);
        messageTextArea.setText("<<Tin nhắn và tin tức>>\n");
        jScrollPane1.setViewportView(messageTextArea);

        messageInput.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        messageInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageInputKeyPressed(evt);
            }
        });

        sendMessageButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sendMessageButton.setText("Gửi");
        sendMessageButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(messageInput, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendMessageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(quickGameButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(goRoomButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createRoomButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(findRoomButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scoreBoardButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scoreBotButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playWithBotButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(friendListButton, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(messageInput, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sendMessageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(quickGameButton)
                        .addGap(18, 18, 18)
                        .addComponent(goRoomButton)
                        .addGap(18, 18, 18)
                        .addComponent(createRoomButton)
                        .addGap(18, 18, 18)
                        .addComponent(findRoomButton)
                        .addGap(18, 18, 18)
                        .addComponent(playWithBotButton)
                        .addGap(18, 18, 18)
                        .addComponent(friendListButton)
                        .addGap(18, 18, 18)
                        .addComponent(scoreBoardButton)
                        .addGap(18, 18, 18)
                        .addComponent(scoreBotButton)
                        .addGap(18, 18, 18)
                        .addComponent(exitGameButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        frameLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        frameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        frameLabel.setText("GAME CARO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(frameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jLabel1AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1AncestorMoved

    private void createRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createRoomButtonActionPerformed
        int res = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn đặt mật khẩu cho phòng không?", "Tạo phòng", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            Client.closeView(Client.View.HOMEPAGE);
            Client.openView(Client.View.CREATE_ROOM_PASSWORD);
        } else if (res == JOptionPane.NO_OPTION) {
            try {
                Client.socketHandle.write("create-room,");
                Client.closeView(Client.View.HOMEPAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }//GEN-LAST:event_createRoomButtonActionPerformed

    private void findRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findRoomButtonActionPerformed
        try {
            Client.closeView(Client.View.HOMEPAGE);
            Client.openView(Client.View.ROOM_LIST);
            Client.socketHandle.write("view-room-list,");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_findRoomButtonActionPerformed

    private void scoreBoardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreBoardButtonActionPerformed
        Client.openView(Client.View.RANK);
    }//GEN-LAST:event_scoreBoardButtonActionPerformed

    private void scoreBotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoreBotButtonActionPerformed
        try {
            Client.socketHandle.write("offline," + Client.user.getID());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        Client.closeView(Client.View.HOMEPAGE);
        Client.openView(Client.View.LOGIN);
    }//GEN-LAST:event_scoreBotButtonActionPerformed

    private void exitGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitGameButtonActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_exitGameButtonActionPerformed

    private void friendListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendListButtonActionPerformed
        Client.closeView(Client.View.HOMEPAGE);
        Client.openView(Client.View.FRIEND_LIST);
    }//GEN-LAST:event_friendListButtonActionPerformed

    private void quickGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quickGameButtonActionPerformed
        Client.closeView(Client.View.HOMEPAGE);
        Client.openView(Client.View.FIND_ROOM);
    }//GEN-LAST:event_quickGameButtonActionPerformed

    private void playWithBotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playWithBotButtonActionPerformed
        // TODO add your handling code here:
        Client.openView(Client.View.GAME_AI);
    }//GEN-LAST:event_playWithBotButtonActionPerformed

    private void goRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goRoomButtonActionPerformed
        Client.openView(Client.View.ROOM_NAME_FRM);
    }//GEN-LAST:event_goRoomButtonActionPerformed

    private void sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageButtonActionPerformed
        sendMessage();
    }//GEN-LAST:event_sendMessageButtonActionPerformed

    private void messageInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageInputKeyPressed
        if (evt.getKeyCode() == 10) {
            sendMessage();
        }
    }//GEN-LAST:event_messageInputKeyPressed

    private void sendMessage() {
        try {
            if (messageInput.getText().isEmpty()) {
                throw new Exception("Vui lòng nhập nội dung tin nhắn");
            }
            String temp = messageTextArea.getText();
            temp += "Tôi: " + messageInput.getText() + "\n";
            messageTextArea.setText(temp);
            Client.socketHandle.write("chat-server," + messageInput.getText());
            messageInput.setText("");
            messageTextArea.setCaretPosition(messageTextArea.getDocument().getLength());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }

    public void addMessage(String message) {
        String temp = messageTextArea.getText();
        temp += message + "\n";
        messageTextArea.setText(temp);
        messageTextArea.setCaretPosition(messageTextArea.getDocument().getLength());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createRoomButton;
    private javax.swing.JLabel drawLabel;
    private javax.swing.JLabel drawValue;
    private javax.swing.JButton exitGameButton;
    private javax.swing.JButton findRoomButton;
    private javax.swing.JLabel frameLabel;
    private javax.swing.JButton friendListButton;
    private javax.swing.JButton goRoomButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel markLabel;
    private javax.swing.JLabel markValue;
    private javax.swing.JTextField messageInput;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JLabel numberOfGameLabel;
    private javax.swing.JLabel numberOfGameValue;
    private javax.swing.JLabel numberOfWinLabel;
    private javax.swing.JLabel numberOfWinValue;
    private javax.swing.JButton playWithBotButton;
    private javax.swing.JButton quickGameButton;
    private javax.swing.JLabel rankLabel;
    private javax.swing.JLabel rankValue;
    private javax.swing.JButton scoreBoardButton;
    private javax.swing.JButton scoreBotButton;
    private javax.swing.JButton sendMessageButton;
    private javax.swing.JLabel winRatioLabel;
    private javax.swing.JLabel winRatioValue;
    // End of variables declaration//GEN-END:variables
}
