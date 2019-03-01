package Entities;

import java.util.HashMap;

public class Skill {
    private String name;
    private int point;
    private HashMap<String,String> endorsers;

    public Skill(String name, int point) {
        this.name = name;
        this.point = point;
        this.endorsers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void endorse(){
        this.point += 1;
    }

    public void addEndorser(String uid) {
        this.endorsers.put(uid, this.name);
    }

    public boolean hasEndorsed(String uid){
        if (endorsers.containsKey(uid)){
            return true;
        }
        return false;
    }
}
