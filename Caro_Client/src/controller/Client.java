/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;
import view.*;

import javax.swing.*;

/**
 * @author Admin
 */
public class Client {
    public static User user;
    public static LoginForm loginFrm;
    public static RegisterForm registerFrm;
    public static HomePageForm homePageFrm;
    public static RoomListForm roomListFrm;
    public static FriendListForm friendListFrm;
    public static FindRoomForm findRoomFrm;
    public static WaitingRoomForm waitingRoomFrm;
    public static GameClientForm gameClientFrm;
    public static CreateRoomPasswordForm createRoomPasswordFrm;
    public static JoinRoomPasswordForm joinRoomPasswordFrm;
    public static CompetitorInfoForm competitorInfoFrm;
    public static RankForm rankFrm;
    public static GameNoticeForm gameNoticeFrm;
    public static FriendRequestForm friendRequestFrm;
    public static GameAIForm gameAIFrm;
    public static RoomNameForm roomNameFrm;
    public static SocketHandle socketHandle;
    
    public Client() {
    }

    public static JFrame getVisibleJFrame() {
        if (roomListFrm != null && roomListFrm.isVisible())
            return roomListFrm;
        if (friendListFrm != null && friendListFrm.isVisible()) {
            return friendListFrm;
        }
        if (createRoomPasswordFrm != null && createRoomPasswordFrm.isVisible()) {
            return createRoomPasswordFrm;
        }
        if (joinRoomPasswordFrm != null && joinRoomPasswordFrm.isVisible()) {
            return joinRoomPasswordFrm;
        }
        if (rankFrm != null && rankFrm.isVisible()) {
            return rankFrm;
        }
        return homePageFrm;
    }

    public static void openView(View viewName) {
        if (viewName != null) {
            switch (viewName) {
                case LOGIN:
                    loginFrm = new LoginForm();
                    loginFrm.setVisible(true);
                    break;
                case REGISTER:
                    registerFrm = new RegisterForm();
                    registerFrm.setVisible(true);
                    break;
                case HOMEPAGE:
                    homePageFrm = new HomePageForm();
                    homePageFrm.setVisible(true);
                    break;
                case ROOM_LIST:
                    roomListFrm = new RoomListForm();
                    roomListFrm.setVisible(true);
                    break;
                case FRIEND_LIST:
                    friendListFrm = new FriendListForm();
                    friendListFrm.setVisible(true);
                    break;
                case FIND_ROOM:
                    findRoomFrm = new FindRoomForm();
                    findRoomFrm.setVisible(true);
                    break;
                case WAITING_ROOM:
                    waitingRoomFrm = new WaitingRoomForm();
                    waitingRoomFrm.setVisible(true);
                    break;

                case CREATE_ROOM_PASSWORD:
                    createRoomPasswordFrm = new CreateRoomPasswordForm();
                    createRoomPasswordFrm.setVisible(true);
                    break;
                case RANK:
                    rankFrm = new RankForm();
                    rankFrm.setVisible(true);
                    break;
                case GAME_AI:
                    gameAIFrm = new GameAIForm();
                    gameAIFrm.setVisible(true);
                    break;
                case ROOM_NAME_FRM:
                    roomNameFrm = new RoomNameForm();
                    roomNameFrm.setVisible(true);
            }
        }
    }

    public static void openView(View viewName, int arg1, String arg2) {
        if (viewName != null) {
            switch (viewName) {
                case JOIN_ROOM_PASSWORD:
                    joinRoomPasswordFrm = new JoinRoomPasswordForm(arg1, arg2);
                    joinRoomPasswordFrm.setVisible(true);
                    break;
                case FRIEND_REQUEST:
                    friendRequestFrm = new FriendRequestForm(arg1, arg2);
                    friendRequestFrm.setVisible(true);
            }
        }
    }

    public static void openView(View viewName, User competitor, int room_ID, int isStart, String competitorIP) {
        if (viewName == View.GAME_CLIENT) {
            gameClientFrm = new GameClientForm(competitor, room_ID, isStart, competitorIP);
            gameClientFrm.setVisible(true);
        }
    }

    public static void openView(View viewName, User user) {
        if (viewName == View.COMPETITOR_INFO) {
            competitorInfoFrm = new CompetitorInfoForm(user);
            competitorInfoFrm.setVisible(true);
        }
    }

    public static void openView(View viewName, String arg1, String arg2) {
        if (viewName != null) {
            switch (viewName) {
                case GAME_NOTICE:
                    gameNoticeFrm = new GameNoticeForm(arg1, arg2);
                    gameNoticeFrm.setVisible(true);
                    break;
                case LOGIN:
                    loginFrm = new LoginForm(arg1, arg2);
                    loginFrm.setVisible(true);
            }
        }
    }

    public static void closeView(View viewName) {
        if (viewName != null) {
            switch (viewName) {
                case LOGIN:
                    loginFrm.dispose();
                    break;
                case REGISTER:
                    registerFrm.dispose();
                    break;
                case HOMEPAGE:
                    homePageFrm.dispose();
                    break;
                case ROOM_LIST:
                    roomListFrm.dispose();
                    break;
                case FRIEND_LIST:
                    friendListFrm.stopAllThread();
                    friendListFrm.dispose();
                    break;
                case FIND_ROOM:
                    findRoomFrm.stopAllThread();
                    findRoomFrm.dispose();
                    break;
                case WAITING_ROOM:
                    waitingRoomFrm.dispose();
                    break;
                case GAME_CLIENT:
                    gameClientFrm.stopAllThread();
                    gameClientFrm.dispose();
                    break;
                case CREATE_ROOM_PASSWORD:
                    createRoomPasswordFrm.dispose();
                    break;
                case JOIN_ROOM_PASSWORD:
                    joinRoomPasswordFrm.dispose();
                    break;
                case COMPETITOR_INFO:
                    competitorInfoFrm.dispose();
                    break;
                case RANK:
                    rankFrm.dispose();
                    break;
                case GAME_NOTICE:
                    gameNoticeFrm.dispose();
                    break;
                case FRIEND_REQUEST:
                    friendRequestFrm.dispose();
                    break;
                case GAME_AI:
                    gameAIFrm.dispose();
                    break;
                case ROOM_NAME_FRM:
                    roomNameFrm.dispose();
                    break;
            }

        }
    }

    public static void closeAllViews() {
        if (loginFrm != null) loginFrm.dispose();
        if (registerFrm != null) registerFrm.dispose();
        if (homePageFrm != null) homePageFrm.dispose();
        if (roomListFrm != null) roomListFrm.dispose();
        if (friendListFrm != null) {
            friendListFrm.stopAllThread();
            friendListFrm.dispose();
        }
        if (findRoomFrm != null) {
            findRoomFrm.stopAllThread();
            findRoomFrm.dispose();
        }
        if (waitingRoomFrm != null) waitingRoomFrm.dispose();
        if (gameClientFrm != null) {
            gameClientFrm.stopAllThread();
            gameClientFrm.dispose();
        }
        if (createRoomPasswordFrm != null) createRoomPasswordFrm.dispose();
        if (joinRoomPasswordFrm != null) joinRoomPasswordFrm.dispose();
        if (competitorInfoFrm != null) competitorInfoFrm.dispose();
        if (rankFrm != null) rankFrm.dispose();
        if (gameNoticeFrm != null) gameNoticeFrm.dispose();
        if (friendRequestFrm != null) friendRequestFrm.dispose();
        if (gameAIFrm != null) gameAIFrm.dispose();
        if (roomNameFrm != null) roomNameFrm.dispose();
    }

    public static void main(String[] args) {
        new Client().initView();
    }

    public void initView() {

        loginFrm = new LoginForm();
        loginFrm.setVisible(true);
        socketHandle = new SocketHandle();
        socketHandle.run();
    }

    public enum View {
        LOGIN,
        REGISTER,
        HOMEPAGE,
        ROOM_LIST,
        FRIEND_LIST,
        FIND_ROOM,
        WAITING_ROOM,
        GAME_CLIENT,
        CREATE_ROOM_PASSWORD,
        JOIN_ROOM_PASSWORD,
        COMPETITOR_INFO,
        RANK,
        GAME_NOTICE,
        FRIEND_REQUEST,
        GAME_AI,
        ROOM_NAME_FRM
    }
}
