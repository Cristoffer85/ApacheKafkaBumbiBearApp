package mygroup.kafkaspringbootlektionmarcus.payload;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customId;
    private String firstName;
    private String lastName;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getCustomId() {return customId;}
    public void setCustomId(String customId) {this.customId = customId;}
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "customId='" + customId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

