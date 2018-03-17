package Model.Entity.Skill;

public abstract class Skill {

    private int points = 0; // default?

    public Skill(){}

    public Skill(int points){
        this.points = points;
    }

    public void addPoints(int points){
        this.points += points;
    }

    public int getPoints(){
        return points;
    }
}
