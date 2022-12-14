package tsekkaa.arvosi;

import java.util.ArrayList;

/**
 * Class for instance and private list
 * @author Riku Nokelainen
 */
public class ActivitySelectionSingleton {
    private static final ActivitySelectionSingleton ourInstance = new ActivitySelectionSingleton();
    //Creates a list
    private ArrayList<NextActivity> activity;

    /**
     * Gets instance and returns it
     * @return ourInstance
     */
    public static ActivitySelectionSingleton getInstance() {
        return ourInstance;
    }
    //Adds values to the list

    private ActivitySelectionSingleton() {
        activity = new ArrayList<NextActivity>();
        activity.add(new NextActivity("VERENPAINEEN KIRJAUS"));
        activity.add(new NextActivity("VEREN HAPPIPITOISUUDEN KIRJAUS"));
        activity.add(new NextActivity("VERENSOKERIN KIRJAUS"));
        activity.add(new NextActivity("ASETUKSET"));
    }

    /**
     * Gets the activity list
     * @return activity
     */
    public ArrayList<NextActivity> getActivity() {
        return activity;
    }
}


