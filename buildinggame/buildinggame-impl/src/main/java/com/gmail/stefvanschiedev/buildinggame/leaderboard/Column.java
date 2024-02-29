package com.gmail.stefvanschiedev.buildinggame.leaderboard;

public class Column {
    private String columnName;
    private String name;
    private Boolean attribute;
    private Boolean isFloat;

    public Column(){
        this("Name", "name", false,false);
    }

    public Column(String columnName, String name, Boolean attribute, Boolean isFloat){
        this.columnName = columnName;
        this.name = name;
        this.attribute = attribute;
        this.isFloat = isFloat;
    }

    @Override
    public String toString() {
        return "Column{" +
            "columnName='" + columnName + '\'' +
            ", name='" + name + '\'' +
            ", attribute=" + attribute +
            ", isFloat=" + isFloat +
            '}';
    }
}
