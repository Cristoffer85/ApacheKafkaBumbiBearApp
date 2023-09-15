package mygroup.kafkaspringbootlektionmarcus.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {

    @Primary
    @Bean(name = "remoteMongoTemplate")
    public MongoTemplate remoteMongoTemplate() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb+srv://cristofferostberg85:<MongoDBPassword>@cluster0.imetavy.mongodb.net/?retryWrites=true&w=majority"))
                .build();
        MongoDatabase database = MongoClients.create(settings).getDatabase("KafkaJsonApp");
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(settings), "KafkaJsonApp"));
    }

    @Bean(name = "localMongoTemplate")
    public MongoTemplate localMongoTemplate() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                .build();
        MongoDatabase database = MongoClients.create(settings).getDatabase("KafkaJsonApp");
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(settings), "KafkaJsonApp"));
    }
}

