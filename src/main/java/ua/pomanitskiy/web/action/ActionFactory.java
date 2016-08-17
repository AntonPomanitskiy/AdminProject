package ua.pomanitskiy.web.action;

import ua.pomanitskiy.web.action.interfaces.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anton on 07.08.16.
 */
public final class ActionFactory {

    /**
     * Default constructor.
     */
    private ActionFactory() {
    }

    /**
     * Map for store actions.
     */
    private static Map<String, Class> actions = getClasses();

    /**
     * The method for getting all possible actions at loading class.
     * @return map of actions
     */
    private static Map<String, Class> getClasses() {
        Map<String, Class> map = new HashMap<String, Class>();

        map.put("dispatcher", DispatcherAction.class);

        return map;
    }

    /**
     *
     * @param name to get action
     * @return action
     */
    public static Action getAction(final String name) {

        Action action = null;

        Class actionClass = null;
        if ((actionClass = actions.get(name)) == null) {
            throw new IllegalArgumentException("Don't know this action ("
                    + name + ").");
        }

        try {
            action = (Action) actionClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
        return action;
    }

}
