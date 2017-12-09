package ingenuity.com.beloteeasyscore;

class EventsHelper {

    private static team currenTeam = team.LEFT_TEAM;
    private static events currentEvent = events.FIRST_LAUNCH;

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
        currentEvent = _events;
    }

    static String getEventName(events _events) {
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
}
