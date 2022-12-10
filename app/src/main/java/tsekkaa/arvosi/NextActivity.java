package tsekkaa.arvosi;

public class NextActivity {
    //Creates String
    private String name;

    //Creates construtor with value
    public NextActivity(String name){
        this.name = name;
    }

    //Creates a method to return name
    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name;

    }
}
