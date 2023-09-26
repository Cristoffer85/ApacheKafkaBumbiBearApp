package KafkaGroup.BumbiBearApp.payload;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "BumbiCollection") // Define the MongoDB collection name
public class MongoUser {

    @Id
    private String id; // Use String or ObjectId as the ID type

    private String species;
    private String type;
    private String fullname;

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getSpecies() {return species;}
    public void setSpecies(String species) {this.species = species;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public String getFullname() {return fullname;}
    public void setFullname(String fullname) {this.fullname = fullname;}


    @Override
    public String toString() {
        return "MongoUser{" +
                "id='" + id + '\'' +
                ", species='" + species + '\'' +
                ", type='" + type + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}

