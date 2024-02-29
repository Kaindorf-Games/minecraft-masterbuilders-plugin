package com.gmail.stefvanschiedev.buildinggame.leaderboard;

import java.util.HashMap;
import java.util.Map;

public class Member {
    private String name;
    private Map<String, Double> attributes;

    public Member(){
        this("name", new HashMap<String, Double>());
    }

    public Member(String name, Map<String, Double> attributes){
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Member{" +
            "name='" + name + '\'' +
            ", attributes=" + attributes +
            '}';
    }
}
