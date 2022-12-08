package tsekkaa.arvosi;

public class NextActivity {
    private String name;

    public NextActivity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return name;

    }
}
