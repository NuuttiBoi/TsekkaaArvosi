package tsekkaa.arvosi;

/**
 * Class for setting names
 * @author Riku Nokelainen
 */
public class NextActivity {
    //Creates String
    private String name;

    /**
     * Is a constructor that generates name
     * @param name
     */
    public NextActivity(String name){
        this.name = name;
    }

    /**
     * Gets the name from construtor
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return name
     */
    @Override
    public String toString(){
        return name;

    }
}
