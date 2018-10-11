package glasch.handsanalyzer.model;

/*
 * Author: glaschenko
 * Created: 11.10.2018
 */
public enum ActionType {
    FOLD, CALL, RAISE;

    public static ActionType fromString(String action) {
        if ("folds".equals(action)) return FOLD;
        if ("calls".equals(action) || "checks".equals(action)) return CALL;
        if ("bets".equals(action) || "posts".equals(action) || "raises".equals(action)) return RAISE;
        throw new IllegalStateException("unexpected action: " + action);
    }

  public   char toActionChar(){
        switch (this){
            case FOLD:  return 'F';
            case CALL:  return 'C';
            default: return 'R' ;
        }
    }
}
