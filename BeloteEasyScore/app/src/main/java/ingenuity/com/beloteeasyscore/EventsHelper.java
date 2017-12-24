package ingenuity.com.beloteeasyscore;

import android.util.Log;

import com.facebook.Profile;

public class EventsHelper {

    private static final String LogTag = "BeloteEasyScore";
    private static final String SubLogTag = "EventsHelper: ";
    private static team currenTeam = team.LEFT_TEAM;
    private static events currentEvent = events.FIRST_LAUNCH;
    private static int leftTeamGlobalScore = 0;
    private static int rightTeamGlobalScore = 0;

    private static player currentPlayerToAdd = player.PLAYER_1_RIGHT_TEAM;

    private static Profile userProfile = null;
    private static String userName = null ;
    private static String userSurname  = null ;
    private static String userPictureUrl = null ;

    private static String player1RightTeamName = "player 1" ;
    private static String player1RightTeamPictureUrl = null ;
    private static String player2RightTeamName ="player 2";
    private static String player2RightTeamPictureUrl = null ;
    private static String player1LeftTeamName = "player 1" ;
    private static String player1LeftTeamPictureUrl = null ;
    private static String player2LeftTeamName = "player 2" ;
    private static String player2LeftTeamPictureUrl = null ;


    enum events {
        FIRST_LAUNCH,
        SELECT_INPUT_MENU_DISPLAYED,
        NUMERIC_INPUTS_SELECTED_NOT_DISPLAYED,
        NUMERIC_INPUTS_SELECTED_DISPLAYED,
        GIRD_INPUTS_SELECTE,
        VOICE_INPUTS_SELECTED,
        CAMERA_INPUTS_SELECTED,
    }

    enum team {
        LEFT_TEAM,
        RIGHT_TEAM
    }

    enum player {
        PLAYER_1_LEFT_TEAM,
        PLAYER_2_LEFT_TEAM,
        PLAYER_1_RIGHT_TEAM,
        PLAYER_2_RIGHT_TEAM
    }

    static Profile getUserProfile() {
        return userProfile;
    }

    static void setUserProfile(Profile _profile) {
        userProfile = _profile;
    }

    static String getUserName() {
        return userName;
    }

    static void setUserName(String _name) {
        userName = _name;
    }

    static String getUserSurname() {
        return userSurname;
    }

    static void setUserSurname(String _surname) {
        userSurname = _surname;
    }

    static String getUserPictureUrl() {
        return userPictureUrl;
    }

    static void setUserPictureUrl(String _pictureUrl) {
        userPictureUrl = _pictureUrl;
    }

    static team getCurrentSelectedTeam() {
        return currenTeam;
    }

    static void setCurrentSelectedTeam(team _team) {
        currenTeam = _team;
    }

    static events getcurrentEvent() {
        return currentEvent;
    }

    static void setcurrentEvent(events _events) {
        Log.d(LogTag, SubLogTag + "Current Event: " + getEventName(_events) );
        currentEvent = _events;
    }

    static void updateGlobalScore(team _team, int _newScore) {
        if(_team == team.LEFT_TEAM) leftTeamGlobalScore += _newScore;
        if(_team == team.RIGHT_TEAM) rightTeamGlobalScore += _newScore;
    }

    static int getGlobalScore(team _team){
        if(_team == team.LEFT_TEAM) return leftTeamGlobalScore;
        if(_team == team.RIGHT_TEAM) return rightTeamGlobalScore;
        return -1;
    }

    static String getGlobalScoreString(team _team){
        if(_team == team.LEFT_TEAM) return String.valueOf(leftTeamGlobalScore);
        if(_team == team.RIGHT_TEAM) return String.valueOf(rightTeamGlobalScore);
        return "-1";
    }

    private static String getEventName(events _events) {
        switch (_events) {
            case FIRST_LAUNCH:
                return "FIRST_LAUNCH";
            case SELECT_INPUT_MENU_DISPLAYED:
                return "SELECT_INPUT_MENU_DISPLAYED";
            case NUMERIC_INPUTS_SELECTED_DISPLAYED:
                return "NUMERIC_INPUTS_SELECTED_DISPLAYED";
            case NUMERIC_INPUTS_SELECTED_NOT_DISPLAYED:
                return "NUMERIC_INPUTS_SELECTED_NOT_DISPLAYED";
            default:
                return "UNKNOWN EVENT";
        }
    }

    static String getTeamName(team _team) {
        switch (_team) {
            case LEFT_TEAM:
                return "LEFT TEAM";
            case RIGHT_TEAM:
                return "RIGHT TEAM";
            default:
                return "UNKNOWN TEAM";
        }
    }

    static void setCurrentPlayerToAdd(player _currenPlayerToAdd) {
        currentPlayerToAdd = _currenPlayerToAdd;
    }

    static void addNewPlayer(String _name, String _pictureUrl) {
        switch (currentPlayerToAdd){
            case PLAYER_1_LEFT_TEAM:
                player1LeftTeamName = _name;
                player1LeftTeamPictureUrl = _pictureUrl;
                break;
            case PLAYER_2_LEFT_TEAM:
                player2LeftTeamName = _name;
                player2LeftTeamPictureUrl = _pictureUrl;
                break;
            case PLAYER_1_RIGHT_TEAM:
                player1RightTeamName = _name;
                player1RightTeamPictureUrl = _pictureUrl;
                break;
            case PLAYER_2_RIGHT_TEAM:
                player2RightTeamName = _name;
                player2RightTeamPictureUrl = _pictureUrl;
                break;
            default:
                break;
        }
    }

    static String getPlayerName(player _selectedPlayer) {
        switch (_selectedPlayer){
            case PLAYER_1_LEFT_TEAM:
                return player1LeftTeamName;
            case PLAYER_2_LEFT_TEAM:
                return player2LeftTeamName;
            case PLAYER_1_RIGHT_TEAM:
                return player1RightTeamName;
            case PLAYER_2_RIGHT_TEAM:
                return player2RightTeamName;
            default:
                return "Unknown player";
        }
    }

    static void setPlayerName(player _selectedPlayer,String _name) {
        switch (_selectedPlayer){
            case PLAYER_1_LEFT_TEAM:
                player1LeftTeamName = _name;
                break;
            case PLAYER_2_LEFT_TEAM:
                player2LeftTeamName = _name;
                break;
            case PLAYER_1_RIGHT_TEAM:
                player1RightTeamName = _name;
                break;
            case PLAYER_2_RIGHT_TEAM:
                player2RightTeamName = _name;
                break;
            default:
                break;
        }
    }

    static String getPlayerPictureUrl(player _selectedPlayer) {
        switch (_selectedPlayer){
            case PLAYER_1_LEFT_TEAM:
                return player1LeftTeamPictureUrl;
            case PLAYER_2_LEFT_TEAM:
                return player2LeftTeamPictureUrl;
            case PLAYER_1_RIGHT_TEAM:
                return player1RightTeamPictureUrl;
            case PLAYER_2_RIGHT_TEAM:
                return player2RightTeamPictureUrl;
            default:
                return "Unknown player";
        }
    }

    static void setPlayerPictureUrl(player _selectedPlayer,String _pictureUrl) {
        switch (_selectedPlayer){
            case PLAYER_1_LEFT_TEAM:
                player1LeftTeamPictureUrl = _pictureUrl;
                break;
            case PLAYER_2_LEFT_TEAM:
                player2LeftTeamPictureUrl = _pictureUrl;
                break;
            case PLAYER_1_RIGHT_TEAM:
                player1RightTeamPictureUrl = _pictureUrl;
                break;
            case PLAYER_2_RIGHT_TEAM:
                player2RightTeamPictureUrl = _pictureUrl;
                break;
            default:
                break;
        }
    }
}
