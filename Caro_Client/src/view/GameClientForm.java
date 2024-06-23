package view;


import controller.Client;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;

import model.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Admin
 */
public class GameClientForm extends javax.swing.JFrame {

    private final User competitor;
    private final JButton[][] button;
    private final int[][] competitorMatrix;
    private final int[][] matrix;
    private final int[][] userMatrix;

    //if changing it you will need to redesign icon
    private final int size = 15;
    private final Timer timer;
    private Integer second;
    private Integer minute;
    private int numberOfMatch;
    private final String[] normalItem;
    private final String[] winItem;
    private final String[] iconItem;
    private final String[] preItem;

    private JButton preButton;
    private int userWin;
    private int competitorWin;
    private boolean isSending;
    private boolean isListening;
    private final String competitorIP;

    public GameClientForm(User competitor, int room_ID, int isStart, String competitorIP) {
        initComponents();
        numberOfMatch = isStart;
        this.competitor = competitor;
        this.competitorIP = competitorIP;

        isSending = false;
        isListening = false;
//        microphoneStatusButton.setIcon(new ImageIcon("assets/game/mute.png"));
//        speakerStatusButton.setIcon(new ImageIcon("assets/game/mutespeaker.png"));

        //init score
        userWin = 0;
        competitorWin = 0;

        this.setTitle("Caro Game");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("assets/image/caroicon.png").getImage());
        this.setResizable(false);
        this.getContentPane().setLayout(null);

        //Set layout dạng lưới cho panel chứa button
        jPanel_BoardGame.setLayout(new GridLayout(size, size));

        //Setup play button
        button = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                button[i][j] = new JButton("");
                button[i][j].setBackground(Color.white);
                button[i][j].setDisabledIcon(new ImageIcon("assets/image/border.jpg"));
                jPanel_BoardGame.add(button[i][j]);
            }
        }

        //SetUp play Matrix
        competitorMatrix = new int[size][size];
        matrix = new int[size][size];
        userMatrix = new int[size][size];

        //Setup UI
        jLabel_PLayer.setFont(new Font("Arial", Font.BOLD, 15));
        jLabel_Competitor.setFont(new Font("Arial", Font.BOLD, 15));
        jLabel_RoomName.setFont(new Font("Arial", Font.BOLD, 15));
        jLabel_RoomName.setAlignmentX(JLabel.CENTER);
        jButton_Send.setBackground(Color.white);
//        sendButton.setIcon(new ImageIcon("assets/image/send2.png"));
        jLabel_PlayerNicknameValue.setText(Client.user.getNickname());
        jLabel_PlayerNumberOfGameValue.setText(Integer.toString(Client.user.getNumberOfGame()));
        jLabel_PlayerNumberOfWinValue.setText(Integer.toString(Client.user.getNumberOfWin()));
        jLabel_PlayerButtonImage.setIcon(new ImageIcon("assets/game/" + Client.user.getAvatar() + ".jpg"));
        jLabel_RoomName.setText("Phòng: " + room_ID);
//        vsIcon.setIcon(new ImageIcon("assets/game/swords-1.png"));
        jLabel_CompetitorNicknameValue.setText(competitor.getNickname());
        jLabel_CompetotorNumberOfGameValue.setText(Integer.toString(competitor.getNumberOfGame()));
        jLabel_CompetitorNumberOfWinValue.setText(Integer.toString(competitor.getNumberOfWin()));
        jButton_CompetotorImage.setIcon(new ImageIcon("assets/game/" + competitor.getAvatar() + ".jpg"));
        jButton_CompetotorImage.setToolTipText("Xem thông tin đối thủ");
        jLabel_PlayerCurrentPosition.setVisible(false);
        jLabel_CompetitorPosition.setVisible(false);
        jButton_DrawRequest.setVisible(false);
        jLabel_PlayerTurn.setVisible(false);
        jLabel_CompetitorTurn.setVisible(false);
        jLabel_CountDown.setVisible(false);
        jTextArea_Message.setEditable(false);
        jLabel_Score.setText("Tỉ số: 0-0");

        //Setup timer
        second = 60;
        minute = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = minute.toString();
                String temp1 = second.toString();
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                if (temp1.length() == 1) {
                    temp1 = "0" + temp1;
                }
                if (second == 0) {
                    jLabel_CountDown.setText("Thời Gian " + temp + ":" + temp1);
                    second = 60;
                    minute = 0;
                    try {
                        Client.openView(Client.View.GAME_CLIENT, "Bạn đã thua do quá thời gian", "Đang thiết lập ván chơi mới");
                        increaseWinMatchToCompetitor();
                        Client.socketHandle.write("lose,");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                    }

                } else {
                    jLabel_CountDown.setText("Thời Gian:" + temp + ":" + temp1);
                    second--;
                }

            }

        });

        //Setup icon
        normalItem = new String[2];
        normalItem[1] = "assets/image/o2.jpg";
        normalItem[0] = "assets/image/x2.jpg";
        winItem = new String[2];
        winItem[1] = "assets/image/owin.jpg";
        winItem[0] = "assets/image/xwin.jpg";
        iconItem = new String[2];
        iconItem[1] = "assets/image/o3.jpg";
        iconItem[0] = "assets/image/x3.jpg";
        preItem = new String[2];
        preItem[1] = "assets/image/o2_pre.jpg";
        preItem[0] = "assets/image/x2_pre.jpg";
        setupButton();

        setEnableButton(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitGame();
            }
        });

    }

    public void exitGame() {
        try {
            timer.stop();
            voiceCloseMic();
            voiceStopListening();
            Client.socketHandle.write("left-room,");
            Client.closeAllViews();
            Client.openView(Client.View.HOMEPAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        Client.closeAllViews();
        Client.openView(Client.View.HOMEPAGE);
    }

    public void stopAllThread() {
        timer.stop();
        voiceCloseMic();
        voiceStopListening();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jFrame4 = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Message = new javax.swing.JTextArea();
        jTextField_Message = new javax.swing.JTextField();
        jPanel_BoardGame = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_PLayer = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel_PlayerNickname = new javax.swing.JLabel();
        jLabel_PlayerNicknameValue = new javax.swing.JLabel();
        jLabel_PlayerNumberOfGame = new javax.swing.JLabel();
        jLabel_PlayerNumberOfGameValue = new javax.swing.JLabel();
        jLabel_PlayerNumberOfWin = new javax.swing.JLabel();
        jLabel_PlayerNumberOfWinValue = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel_Competitor = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel_CompetitorNickname = new javax.swing.JLabel();
        jLabel_CompetotorNumberOfGame = new javax.swing.JLabel();
        jLabel_CompetitorNumberOfWin = new javax.swing.JLabel();
        jLabel_CompetitorNumberOfWinValue = new javax.swing.JLabel();
        jLabel_CompetotorNumberOfGameValue = new javax.swing.JLabel();
        jLabel_CompetitorNicknameValue = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel_RoomName = new javax.swing.JLabel();
        jButton_Send = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel_PlayerButtonImage = new javax.swing.JLabel();
        jButton_CompetotorImage = new javax.swing.JButton();
        jLabel_Score = new javax.swing.JLabel();
        jLabel_PlayerTurn = new javax.swing.JLabel();
        jLabel_PlayerCurrentPosition = new javax.swing.JLabel();
        jLabel_CompetitorTurn = new javax.swing.JLabel();
        jLabel_CompetitorPosition = new javax.swing.JLabel();
        jLabel_CountDown = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton_DrawRequest = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_Main = new javax.swing.JMenu();
        jMenuItem_NewGame = new javax.swing.JMenuItem();
        jMenuItem_Exit = new javax.swing.JMenuItem();
        jMenu_Help = new javax.swing.JMenu();
        jMenuItem_Help = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame4Layout = new javax.swing.GroupLayout(jFrame4.getContentPane());
        jFrame4.getContentPane().setLayout(jFrame4Layout);
        jFrame4Layout.setHorizontalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame4Layout.setVerticalGroup(
            jFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        jTextArea_Message.setColumns(20);
        jTextArea_Message.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea_Message.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Message);

        jTextField_Message.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_Message.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_MessageKeyPressed(evt);
            }
        });

        jPanel_BoardGame.setBackground(new java.awt.Color(51, 51, 51));
        jPanel_BoardGame.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel_BoardGameLayout = new javax.swing.GroupLayout(jPanel_BoardGame);
        jPanel_BoardGame.setLayout(jPanel_BoardGameLayout);
        jPanel_BoardGameLayout.setHorizontalGroup(
            jPanel_BoardGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_BoardGameLayout.setVerticalGroup(
            jPanel_BoardGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 76, 48));

        jLabel_PLayer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_PLayer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_PLayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_PLayer.setText("BẠN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_PlayerNickname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_PlayerNickname.setText("Nickname");

        jLabel_PlayerNicknameValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_PlayerNicknameValue.setText("{nickname}");

        jLabel_PlayerNumberOfGame.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_PlayerNumberOfGame.setText("Số ván chơi");

        jLabel_PlayerNumberOfGameValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_PlayerNumberOfGameValue.setText("{sovanchoi}");

        jLabel_PlayerNumberOfWin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_PlayerNumberOfWin.setText("Số ván thắng");

        jLabel_PlayerNumberOfWinValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_PlayerNumberOfWinValue.setText("{sovanthang}");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_PlayerNumberOfGame)
                            .addComponent(jLabel_PlayerNickname))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_PlayerNumberOfGameValue)
                            .addComponent(jLabel_PlayerNicknameValue)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel_PlayerNumberOfWin)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_PlayerNumberOfWinValue)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_PlayerNickname)
                    .addComponent(jLabel_PlayerNicknameValue))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_PlayerNumberOfGame)
                    .addComponent(jLabel_PlayerNumberOfGameValue))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_PlayerNumberOfWin)
                    .addComponent(jLabel_PlayerNumberOfWinValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_PLayer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel_PLayer, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        jPanel3.setBackground(new java.awt.Color(15, 10, 222));
        jPanel3.setForeground(new java.awt.Color(102, 102, 102));

        jLabel_Competitor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_Competitor.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Competitor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Competitor.setText("ĐỐI THỦ");

        jLabel_CompetitorNickname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_CompetitorNickname.setText("Nickname");

        jLabel_CompetotorNumberOfGame.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_CompetotorNumberOfGame.setText("Số ván chơi");

        jLabel_CompetitorNumberOfWin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_CompetitorNumberOfWin.setText("Số ván thắng");

        jLabel_CompetitorNumberOfWinValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_CompetitorNumberOfWinValue.setText("{sovanthang}");

        jLabel_CompetotorNumberOfGameValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_CompetotorNumberOfGameValue.setText("{sovanchoi}");

        jLabel_CompetitorNicknameValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_CompetitorNicknameValue.setText("{nickname}");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel_CompetitorNumberOfWin)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_CompetitorNumberOfWinValue))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_CompetotorNumberOfGame)
                            .addComponent(jLabel_CompetitorNickname))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_CompetotorNumberOfGameValue)
                            .addComponent(jLabel_CompetitorNicknameValue))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_CompetitorNickname)
                    .addComponent(jLabel_CompetitorNicknameValue))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_CompetotorNumberOfGame)
                    .addComponent(jLabel_CompetotorNumberOfGameValue))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_CompetitorNumberOfWin)
                    .addComponent(jLabel_CompetitorNumberOfWinValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Competitor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel_Competitor)
                .addGap(3, 3, 3)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        jLabel_RoomName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_RoomName.setForeground(new java.awt.Color(0, 0, 0));
        jLabel_RoomName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_RoomName.setText("{Tên Phòng}");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_RoomName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_RoomName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jButton_Send.setText("Gửi");
        jButton_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SendActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        jLabel_PlayerButtonImage.setBackground(new java.awt.Color(102, 102, 102));
        jLabel_PlayerButtonImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jButton_CompetotorImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CompetotorImageActionPerformed(evt);
            }
        });

        jLabel_Score.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Score.setForeground(new java.awt.Color(0, 255, 255));
        jLabel_Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Score.setText("Tỉ số  0-0");

        jLabel_PlayerTurn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_PlayerTurn.setForeground(new java.awt.Color(255, 76, 48));
        jLabel_PlayerTurn.setText("Đến lượt bạn");

        jLabel_PlayerCurrentPosition.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_PlayerCurrentPosition.setText("x/o");

        jLabel_CompetitorTurn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_CompetitorTurn.setForeground(new java.awt.Color(15, 10, 222));
        jLabel_CompetitorTurn.setText("Đến lượt đối thủ");

        jLabel_CompetitorPosition.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_CompetitorPosition.setText("x/o");

        jLabel_CountDown.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_CountDown.setForeground(new java.awt.Color(0, 255, 255));
        jLabel_CountDown.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_CountDown.setText("Thời gian 00:20");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel_PlayerTurn)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_PlayerCurrentPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(278, 278, 278)
                        .addComponent(jLabel_CompetitorPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_CompetitorTurn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel_PlayerButtonImage, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(jLabel_CountDown))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                    .addComponent(jLabel_Score, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_CompetotorImage, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_PlayerCurrentPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_CompetitorPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel_Score)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_CountDown)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton_CompetotorImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_PlayerButtonImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_PlayerTurn)
                            .addComponent(jLabel_CompetitorTurn))))
                .addContainerGap())
        );

        jButton_DrawRequest.setBackground(new java.awt.Color(255, 255, 204));
        jButton_DrawRequest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton_DrawRequest.setForeground(new java.awt.Color(0, 0, 0));
        jButton_DrawRequest.setText("Cầu hòa");
        jButton_DrawRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DrawRequestActionPerformed(evt);
            }
        });

        jMenu_Main.setText("Menu");
        jMenu_Main.setToolTipText("");

        jMenuItem_NewGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_NewGame.setText("Game mới");
        jMenuItem_NewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_NewGameActionPerformed(evt);
            }
        });
        jMenu_Main.add(jMenuItem_NewGame);

        jMenuItem_Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem_Exit.setText("Thoát");
        jMenuItem_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ExitActionPerformed(evt);
            }
        });
        jMenu_Main.add(jMenuItem_Exit);

        jMenuBar1.add(jMenu_Main);

        jMenu_Help.setText("Help");

        jMenuItem_Help.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem_Help.setText("Trợ giúp");
        jMenuItem_Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_HelpActionPerformed(evt);
            }
        });
        jMenu_Help.add(jMenuItem_Help);

        jMenuBar1.add(jMenu_Help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jTextField_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton_Send, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton_DrawRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_BoardGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_Message, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Send, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_DrawRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_BoardGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        //for(int i=0; i<5; i++){
            //    for(int j=0;j<5;j++){
                //        jPanel_BoardGame.add(button[i][j]);
                //    }
            //}

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_NewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_NewGameActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Thông báo", "Tính năng đang được phát triển", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem_NewGameActionPerformed

    private void jMenuItem_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ExitActionPerformed
        exitGame();
    }//GEN-LAST:event_jMenuItem_ExitActionPerformed

    private void jButton_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SendActionPerformed
        try {
            if (jTextField_Message.getText().isEmpty()) {
                throw new Exception("Vui lòng nhập nội dung tin nhắn");
            }
            String temp = jTextArea_Message.getText();
            temp += "Tôi: " + jTextField_Message.getText() + "\n";
            jTextArea_Message.setText(temp);
            Client.socketHandle.write("chat," + jTextField_Message.getText());
            jTextField_Message.setText("");
            jTextArea_Message.setCaretPosition(jTextArea_Message.getDocument().getLength());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_jButton_SendActionPerformed

    private void jButton_DrawRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DrawRequestActionPerformed

        try {
            int res = JOptionPane.showConfirmDialog(rootPane, "Bạn có thực sự muốn cầu hòa ván chơi này", "Yêu cầu cầu hòa", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                Client.socketHandle.write("draw-request,");
                timer.stop();
                setEnableButton(false);
                Client.openView(Client.View.GAME_NOTICE, "Yêu cầu hòa", "Đang chờ phản hồi từ đối thủ");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }//GEN-LAST:event_jButton_DrawRequestActionPerformed

    private void jMenuItem_HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_HelpActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(rootPane, "Luật chơi: luật quốc tế 5 nước chặn 2 đầu\n"
                + "Hai người chơi luân phiên nhau chơi trước\n"
                + "Người chơi trước đánh X, người chơi sau đánh O\n"
                + "Bạn có 20 giây cho mỗi lượt đánh, quá 20 giây bạn sẽ thua\n"
                + "Khi cầu hòa, nếu đối thủ đồng ý thì ván hiện tại được hủy kết quả\n"
                + "Với mỗi ván chơi bạn có thêm 1 điểm, nếu hòa bạn được thêm 5 điểm,\n"
                + "nếu thắng bạn được thêm 10 điểm\n"
                + "Chúc bạn chơi game vui vẻ");
    }//GEN-LAST:event_jMenuItem_HelpActionPerformed

    private void jButton_CompetotorImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CompetotorImageActionPerformed

        Client.openView(Client.View.COMPETITOR_INFO, competitor);

    }//GEN-LAST:event_jButton_CompetotorImageActionPerformed

    private void jTextField_MessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_MessageKeyPressed
        if (evt.getKeyCode() == 10) {
            try {
                if (jTextField_Message.getText().isEmpty()) {
                    return;
                }
                String temp = jTextArea_Message.getText();
                temp += "Tôi: " + jTextField_Message.getText() + "\n";
                jTextArea_Message.setText(temp);
                Client.socketHandle.write("chat," + jTextField_Message.getText());
                jTextField_Message.setText("");
                jTextArea_Message.setCaretPosition(jTextArea_Message.getDocument().getLength());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }//GEN-LAST:event_jTextField_MessageKeyPressed

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(rootPane, message);
    }

    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/sound/click.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void playSound1() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/sound/1click.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void playSound2() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("assets/sound/win.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void stopTimer() {
        timer.stop();
    }

    int not(int i) {
        if (i == 1) {
            return 0;
        }
        if (i == 0) {
            return 1;
        }
        return 0;
    }

    void setupButton() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int a = i, b = j;

                button[a][b].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            button[a][b].setDisabledIcon(new ImageIcon(normalItem[not(numberOfMatch % 2)]));
                            button[a][b].setEnabled(false);
                            playSound();
                            second = 60;
                            minute = 0;
                            matrix[a][b] = 1;
                            userMatrix[a][b] = 1;
                            button[a][b].setEnabled(false);
                            try {
                                if (checkRowWin() == 1 || checkColumnWin() == 1 || checkRightCrossWin() == 1 || checkLeftCrossWin() == 1) {
                                    //Xử lý khi người chơi này thắng
                                    setEnableButton(false);
                                    increaseWinMatchToUser();
                                    Client.openView(Client.View.GAME_NOTICE, "Bạn đã thắng", "Đang thiết lập ván chơi mới");
                                    Client.socketHandle.write("win," + a + "," + b);
                                } else {
                                    Client.socketHandle.write("caro," + a + "," + b);
                                    displayCompetitorTurn();

                                }
                                setEnableButton(false);
                                timer.stop();
                            } catch (Exception ie) {
                                ie.printStackTrace();
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                        }
                    }
                });
                button[a][b].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        if (button[a][b].isEnabled()) {
                            button[a][b].setBackground(Color.GREEN);
                            button[a][b].setIcon(new ImageIcon(normalItem[not(numberOfMatch % 2)]));
                        }
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        if (button[a][b].isEnabled()) {
                            button[a][b].setBackground(null);
                            button[a][b].setIcon(new ImageIcon("assets/image/blank.jpg"));
                        }
                    }
                });
            }
        }
    }

    public void displayDrawRefuse() {
        JOptionPane.showMessageDialog(rootPane, "Đối thủ không chấp nhận hòa, mời bạn chơi tiếp");
        timer.start();
        setEnableButton(true);
    }

    public void displayCompetitorTurn() {
        jLabel_CountDown.setVisible(false);
        jLabel_CompetitorTurn.setVisible(true);
        jLabel_CompetitorPosition.setVisible(true);
        jLabel_PlayerTurn.setVisible(false);
        jButton_DrawRequest.setVisible(false);
        jLabel_PlayerCurrentPosition.setVisible(false);
    }

    public void displayUserTurn() {
        jLabel_CountDown.setVisible(false);
        jLabel_CompetitorTurn.setVisible(false);
        jLabel_CompetitorPosition.setVisible(false);
        jLabel_PlayerTurn.setVisible(true);
        jButton_DrawRequest.setVisible(true);
        jLabel_PlayerCurrentPosition.setVisible(true);
    }

    public void startTimer() {
        jLabel_CountDown.setVisible(true);
        second = 60;
        minute = 0;
        timer.start();
    }

    public void addMessage(String message) {
        String temp = jTextArea_Message.getText();
        temp += competitor.getNickname() + ": " + message + "\n";
        jTextArea_Message.setText(temp);
        jTextArea_Message.setCaretPosition(jTextArea_Message.getDocument().getLength());
    }

    public void addCompetitorMove(String x, String y) {
        displayUserTurn();
        startTimer();
        setEnableButton(true);
        caro(x, y);
    }

    public void setLose(String xx, String yy) {
        caro(xx, yy);
    }

    public void increaseWinMatchToUser() {
        Client.user.setNumberOfWin(Client.user.getNumberOfWin() + 1);
        jLabel_PlayerNumberOfWinValue.setText("" + Client.user.getNumberOfWin());
        userWin++;
        jLabel_Score.setText("Tỉ số: " + userWin + "-" + competitorWin);
        String tmp = jTextArea_Message.getText();
        tmp += "--Bạn đã thắng, tỉ số hiện tại là " + userWin + "-" + competitorWin + "--\n";
        jTextArea_Message.setText(tmp);
        jTextArea_Message.setCaretPosition(jTextArea_Message.getDocument().getLength());
    }

    public void increaseWinMatchToCompetitor() {
        competitor.setNumberOfWin(competitor.getNumberOfWin() + 1);
        jLabel_CompetitorNumberOfWinValue.setText("" + competitor.getNumberOfWin());
        competitorWin++;
        jLabel_Score.setText("Tỉ số: " + userWin + "-" + competitorWin);
        String tmp = jTextArea_Message.getText();
        tmp += "--Bạn đã thua, tỉ số hiện tại là " + userWin + "-" + competitorWin + "--\n";
        jTextArea_Message.setText(tmp);
        jTextArea_Message.setCaretPosition(jTextArea_Message.getDocument().getLength());
    }

    public void displayDrawGame() {
        String tmp = jTextArea_Message.getText();
        tmp += "--Ván chơi hòa--\n";
        jTextArea_Message.setText(tmp);
        jTextArea_Message.setCaretPosition(jTextArea_Message.getDocument().getLength());
    }

    public void showDrawRequest() {
        int res = JOptionPane.showConfirmDialog(rootPane, "Đối thử muốn cầu hóa ván này, bạn đồng ý chứ", "Yêu cầu cầu hòa", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            try {
                timer.stop();
                setEnableButton(false);
                Client.socketHandle.write("draw-confirm,");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        } else {
            try {
                Client.socketHandle.write("draw-refuse,");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        }
    }

    public void voiceOpenMic() {

        Thread sendThread = new Thread() {

            @Override
            public void run() {
                AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
                TargetDataLine microphone;
                try {
                    microphone = AudioSystem.getTargetDataLine(format);

                    DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                    microphone = (TargetDataLine) AudioSystem.getLine(info);
                    microphone.open(format);

                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    int numBytesRead;
                    int CHUNK_SIZE = 1024;
                    byte[] data = new byte[microphone.getBufferSize() / 5];
                    microphone.start();

                    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);

                    int port = 5555;

                    InetAddress address = InetAddress.getByName(competitorIP);
                    DatagramSocket socket = new DatagramSocket();
                    byte[] buffer = new byte[1024];
                    isSending = true;
                    while (isSending) {
                        numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
                        out.write(data, 0, numBytesRead);
                        DatagramPacket request = new DatagramPacket(data, numBytesRead, address, port);
                        socket.send(request);

                    }
                    out.close();
                    socket.close();
                    microphone.close();
                } catch (LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }
            }

        };
        sendThread.start();

    }

    public void voiceCloseMic() {
        isSending = false;
    }


    public void voiceListening() {
        //                    microphone = AudioSystem.getTargetDataLine(format);
        //                    DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        //                    microphone = (TargetDataLine) AudioSystem.getLine(info);
        //                    microphone.open(format);
        //                    microphone.start();
        Thread listenThread = new Thread() {
            @Override
            public void run() {
                try {
                    AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, true);
                    SourceDataLine speakers;
                    DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                    speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                    speakers.open(format);
                    speakers.start();
                    try {
                        DatagramSocket serverSocket = new DatagramSocket(5555);
                        isListening = true;
                        while (isListening) {
                            byte[] buffer = new byte[1024];
                            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                            serverSocket.receive(response);
                            speakers.write(response.getData(), 0, response.getData().length);
                            jProgressBar1.setValue((int) volumeRMS(response.getData()));
                        }
                        speakers.close();
                        serverSocket.close();
                    } catch (SocketTimeoutException ex) {
                        System.out.println("Timeout error: " + ex.getMessage());
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        System.out.println("Client error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }

        };
        listenThread.start();
    }

    private int getMax(byte[] bytes) {
        int max = bytes[0];
        for (int i = 1; i < bytes.length; i++) {
            if (bytes[i] > max) max = bytes[i];
        }
        return max;
    }

    public double volumeRMS(byte[] raw) {
        double sum = 0d;
        if (raw.length == 0) {
            return sum;
        } else {
            for (byte b : raw) {
                sum += b;
            }
        }
        double average = sum / raw.length;

        double sumMeanSquare = 0d;
        for (byte b : raw) {
            sumMeanSquare += Math.pow(b - average, 2d);
        }
        double averageMeanSquare = sumMeanSquare / raw.length;
        return Math.sqrt(averageMeanSquare);
    }

    public void voiceStopListening() {
        isListening = false;
    }

    public void addVoiceMessage(String message) {
        String temp = jTextArea_Message.getText();
        temp += competitor.getNickname() + " " + message + "\n";
        jTextArea_Message.setText(temp);
        jTextArea_Message.setCaretPosition(jTextArea_Message.getDocument().getLength());
    }

    public void newgame() {

        if (numberOfMatch % 2 == 0) {
            JOptionPane.showMessageDialog(rootPane, "Đến lượt bạn đi trước");
            startTimer();
            displayUserTurn();
            jLabel_CountDown.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Đối thủ đi trước");
            displayCompetitorTurn();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                button[i][j].setIcon(new ImageIcon("assets/image/blank.jpg"));
                button[i][j].setDisabledIcon(new ImageIcon("assets/image/border.jpg"));
                button[i][j].setText("");
                competitorMatrix[i][j] = 0;
                matrix[i][j] = 0;
                userMatrix[i][j] = 0;
            }
        }
        setEnableButton(true);
        if (numberOfMatch % 2 != 0) {
            blockGame();
        }

        jLabel_PlayerCurrentPosition.setIcon(new ImageIcon(iconItem[numberOfMatch % 2]));
        jLabel_CompetitorPosition.setIcon(new ImageIcon(iconItem[not(numberOfMatch % 2)]));
        preButton = null;
        numberOfMatch++;
    }

    public void updateNumberOfGame() {
        competitor.setNumberOfGame(competitor.getNumberOfGame() + 1);
        jLabel_CompetotorNumberOfGameValue.setText(Integer.toString(competitor.getNumberOfGame()));
        Client.user.setNumberOfGame(Client.user.getNumberOfGame() + 1);
        jLabel_PlayerNumberOfGameValue.setText(Integer.toString(Client.user.getNumberOfGame()));
    }

    public void blockGame() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                button[i][j].setBackground(Color.white);
                button[i][j].setDisabledIcon(new ImageIcon("assets/image/border.jpg"));
                button[i][j].setText("");
                competitorMatrix[i][j] = 0;
                matrix[i][j] = 0;
                jButton_DrawRequest.setVisible(false);
            }
        }
        timer.stop();
        setEnableButton(false);
    }

    public void setEnableButton(boolean b) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == 0) {
                    button[i][j].setEnabled(b);
                }
            }
        }
    }

    public int checkRow() {
        int win = 0, hang = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (check) {
                    if (competitorMatrix[i][j] == 1) {
                        hang++;
                        list.add(button[i][j]);
                        if (hang > 4) {
                            for (JButton jButton : list) {
                                button[i][j].setDisabledIcon(new ImageIcon(winItem[numberOfMatch % 2]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        hang = 0;
                    }
                }
                if (competitorMatrix[i][j] == 1) {
                    check = true;
                    list.add(button[i][j]);
                    hang++;
                } else {
                    list = new ArrayList<>();
                    check = false;
                }
            }
            list = new ArrayList<>();
            hang = 0;
        }
        return win;
    }

    public int checkColumn() {
        int win = 0, cot = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (check) {
                    if (competitorMatrix[i][j] == 1) {
                        cot++;
                        list.add(button[i][j]);
                        if (cot > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[numberOfMatch % 2]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        check = false;
                        cot = 0;
                        list = new ArrayList<>();
                    }
                }
                if (competitorMatrix[i][j] == 1) {
                    check = true;
                    list.add(button[i][j]);
                    cot++;
                } else {
                    list = new ArrayList<>();
                    check = false;
                }
            }
            list = new ArrayList<>();
            cot = 0;
        }
        return win;
    }

    public int checkRightCross() {
        int win = 0, cheop = 0, n = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                if (check) {
                    if (n - j >= 0 && competitorMatrix[n - j][j] == 1) {
                        cheop++;
                        list.add(button[n - j][j]);
                        if (cheop > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[numberOfMatch % 2]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        cheop = 0;
                    }
                }
                if (competitorMatrix[i][j] == 1) {
                    n = i + j;
                    check = true;
                    list.add(button[i][j]);
                    cheop++;
                } else {
                    check = false;
                    list = new ArrayList<>();
                }
            }
            cheop = 0;
            check = false;
            list = new ArrayList<>();
        }
        return win;
    }

    public int checkLeftCross() {
        int win = 0, cheot = 0, n = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                if (check) {
                    if (n - j - 2 * cheot >= 0 && competitorMatrix[n - j - 2 * cheot][j] == 1) {
                        list.add(button[n - j - 2 * cheot][j]);
                        cheot++;
                        System.out.print("+" + j);
                        if (cheot > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[numberOfMatch % 2]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        cheot = 0;
                    }
                }
                if (competitorMatrix[i][j] == 1) {
                    list.add(button[i][j]);
                    n = i + j;
                    check = true;
                    cheot++;
                } else {
                    check = false;
                }
            }
            list = new ArrayList<>();
            n = 0;
            cheot = 0;
            check = false;
        }
        return win;
    }

    public int checkRowWin() {
        int win = 0, hang = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (check) {
                    if (userMatrix[i][j] == 1) {
                        hang++;
                        list.add(button[i][j]);
                        if (hang > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[not(numberOfMatch % 2)]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        hang = 0;
                    }
                }
                if (userMatrix[i][j] == 1) {
                    check = true;
                    list.add(button[i][j]);
                    hang++;
                } else {
                    list = new ArrayList<>();
                    check = false;
                }
            }
            list = new ArrayList<>();
            hang = 0;
        }
        return win;
    }

    public int checkColumnWin() {
        int win = 0, cot = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (check) {
                    if (userMatrix[i][j] == 1) {
                        cot++;
                        list.add(button[i][j]);
                        if (cot > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[not(numberOfMatch % 2)]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        check = false;
                        cot = 0;
                        list = new ArrayList<>();
                    }
                }
                if (userMatrix[i][j] == 1) {
                    check = true;
                    list.add(button[i][j]);
                    cot++;
                } else {
                    check = false;
                }
            }
            list = new ArrayList<>();
            cot = 0;
        }
        return win;
    }

    public int checkRightCrossWin() {
        int win = 0, cheop = 0, n = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                if (check) {
                    if (n >= j && userMatrix[n - j][j] == 1) {
                        cheop++;
                        list.add(button[n - j][j]);
                        if (cheop > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[not(numberOfMatch % 2)]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        cheop = 0;
                    }
                }
                if (userMatrix[i][j] == 1) {
                    n = i + j;
                    check = true;
                    list.add(button[i][j]);
                    cheop++;
                } else {
                    check = false;
                    list = new ArrayList<>();
                }
            }
            cheop = 0;
            check = false;
            list = new ArrayList<>();
        }
        return win;
    }

    public int checkLeftCrossWin() {
        int win = 0, cheot = 0, n = 0;
        boolean check = false;
        List<JButton> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                if (check) {
                    if (n - j - 2 * cheot >= 0 && userMatrix[n - j - 2 * cheot][j] == 1) {
                        list.add(button[n - j - 2 * cheot][j]);
                        cheot++;
                        System.out.print("+" + j);
                        if (cheot > 4) {
                            for (JButton jButton : list) {
                                jButton.setDisabledIcon(new ImageIcon(winItem[not(numberOfMatch % 2)]));
                            }
                            win = 1;
                            break;
                        }
                        continue;
                    } else {
                        list = new ArrayList<>();
                        check = false;
                        cheot = 0;
                    }
                }
                if (userMatrix[i][j] == 1) {
                    list.add(button[i][j]);
                    n = i + j;
                    check = true;
                    cheot++;
                } else {
                    check = false;
                }
            }
            list = new ArrayList<>();
            n = 0;
            cheot = 0;
            check = false;
        }
        return win;
    }

    public void caro(String x, String y) {
        int xx, yy;
        xx = Integer.parseInt(x);
        yy = Integer.parseInt(y);
        // danh dau vi tri danh
        competitorMatrix[xx][yy] = 1;
        matrix[xx][yy] = 1;
        button[xx][yy].setEnabled(false);
//        playSound1();
        if (preButton != null) {
            preButton.setDisabledIcon(new ImageIcon(normalItem[numberOfMatch % 2]));
        }
        preButton = button[xx][yy];
        button[xx][yy].setDisabledIcon(new ImageIcon(preItem[numberOfMatch % 2]));
        if (checkRow() == 1 || checkColumn() == 1 || checkLeftCross() == 1 || checkRightCross() == 1) {
            timer.stop();
            setEnableButton(false);
            increaseWinMatchToCompetitor();
            Client.openView(Client.View.GAME_NOTICE, "Bạn đã thua", "Đang thiết lập ván chơi mới");
        }
    }

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_CompetotorImage;
    private javax.swing.JButton jButton_DrawRequest;
    private javax.swing.JButton jButton_Send;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JFrame jFrame4;
    private javax.swing.JLabel jLabel_Competitor;
    private javax.swing.JLabel jLabel_CompetitorNickname;
    private javax.swing.JLabel jLabel_CompetitorNicknameValue;
    private javax.swing.JLabel jLabel_CompetitorNumberOfWin;
    private javax.swing.JLabel jLabel_CompetitorNumberOfWinValue;
    private javax.swing.JLabel jLabel_CompetitorPosition;
    private javax.swing.JLabel jLabel_CompetitorTurn;
    private javax.swing.JLabel jLabel_CompetotorNumberOfGame;
    private javax.swing.JLabel jLabel_CompetotorNumberOfGameValue;
    private javax.swing.JLabel jLabel_CountDown;
    private javax.swing.JLabel jLabel_PLayer;
    private javax.swing.JLabel jLabel_PlayerButtonImage;
    private javax.swing.JLabel jLabel_PlayerCurrentPosition;
    private javax.swing.JLabel jLabel_PlayerNickname;
    private javax.swing.JLabel jLabel_PlayerNicknameValue;
    private javax.swing.JLabel jLabel_PlayerNumberOfGame;
    private javax.swing.JLabel jLabel_PlayerNumberOfGameValue;
    private javax.swing.JLabel jLabel_PlayerNumberOfWin;
    private javax.swing.JLabel jLabel_PlayerNumberOfWinValue;
    private javax.swing.JLabel jLabel_PlayerTurn;
    private javax.swing.JLabel jLabel_RoomName;
    private javax.swing.JLabel jLabel_Score;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_Exit;
    private javax.swing.JMenuItem jMenuItem_Help;
    private javax.swing.JMenuItem jMenuItem_NewGame;
    private javax.swing.JMenu jMenu_Help;
    private javax.swing.JMenu jMenu_Main;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel_BoardGame;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Message;
    private javax.swing.JTextField jTextField_Message;
    // End of variables declaration//GEN-END:variables


}
