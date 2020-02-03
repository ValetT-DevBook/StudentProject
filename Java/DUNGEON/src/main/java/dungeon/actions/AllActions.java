package dungeon.actions;

import java.util.List;
import java.util.ArrayList;

public class AllActions{

    public static final List<Action> LIST_ACTIONS = AllActions.createList();
    
    /**
     * Create a static list
     * If you want crate a new action, place here
     * The form is add a line "result.add(new YourAction());"
     * @return List<Action>
     */
    private static List<Action> createList(){
        List<Action> result = new ArrayList<Action>();  
        result.add(new AttackAction());
        result.add(new LookAction());
        result.add(new MoveAction());
        result.add(new UseAction());
        result.add(new StatusAction());
        return result;
    }
}

