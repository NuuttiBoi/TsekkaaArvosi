package tsekkaa.arvosi;

import java.util.ArrayList;

public class ActivitySelectionSingleton {
    private static final ActivitySelectionSingleton ourInstance = new ActivitySelectionSingleton();
    //Creates a list
    private ArrayList<NextActivity> activity;

    public static ActivitySelectionSingleton getInstance() {
        return ourInstance;
    }
    //Adds values to the list
    private ActivitySelectionSingleton() {
        activity = new ArrayList<NextActivity>();
        activity.add(new NextActivity("VERENPAINEEN MITTAUS"));
        activity.add(new NextActivity("VEREN HAPPIPITOISUUS"));
        activity.add(new NextActivity("VERENSOKERI"));
        activity.add(new NextActivity("ASETUKSET"));
        activity.add(new NextActivity("c(o_oc("));

    }
    public ArrayList<NextActivity> getActivity() {
        return activity;
    }
    public NextActivity getName(int i){
        return activity.get(i);
    }
}


