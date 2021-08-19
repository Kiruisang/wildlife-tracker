package models;

import org.sql2o.Connection;
import java.util.List;

public class EndangeredAnimal extends Animal{
    private int id;
    private String health;
    private String age;

    public static final String TYPE = "Endangered";

    public EndangeredAnimal(String name, String health, String age){
        super(name);
        this.health = health;
        this.age = age;
        this.type = TYPE;

        if(name.isEmpty() || health.isEmpty() || age.isEmpty()){
            throw new IllegalArgumentException("Some inputs are missing !");
        }
    }
    public String getHealth() {
        return health;
    }
    public  String getAge() {
        return age;
    }
    @Override
    public int getId() {
        return id;
    }
    public static String getType() {
        return TYPE;
    }
    @Override
    public boolean equals (Object otherEndangeredAnimal) {
        if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
            return false;
        } else {
            EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
            return this.getName().equals(newEndangeredAnimal.getName()) &&
                    this.getHealth().equals(newEndangeredAnimal.getHealth()) &&
                    this.getAge().equals(newEndangeredAnimal.getAge());

        }
    }
    public static List<EndangeredAnimal> allEndangeredAnimals() {
        String sql = "SELECT * FROM animals WHERE type='Endangered';";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }
    public static EndangeredAnimal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            EndangeredAnimal endangeredAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return endangeredAnimal;
        }
    }

}
