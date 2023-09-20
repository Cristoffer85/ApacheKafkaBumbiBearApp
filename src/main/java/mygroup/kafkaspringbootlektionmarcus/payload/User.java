package mygroup.kafkaspringbootlektionmarcus.payload;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String species;
    private String type;
    private String fullname;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getSpecies() {return species;}
    public void setSpecies(String species) {this.species = species;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getFullname() {return fullname;}
    public void setFullname(String fullName) {this.fullname = fullName;}

    @Override
    public String toString() {
        return "Entry - " +
                "Species= " + species +
                ", type= " + type +
                ", fullname= " + fullname;
    }
}

