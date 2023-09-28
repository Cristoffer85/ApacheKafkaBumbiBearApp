package KafkaGroup.BumbiBearApp.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "KafkaGroup.BumbiBearApp.repository")
public class MongoDBConfig {

    @Value("${mongodb.connection-string}") // Inject the property value from application.properties
    private String mongoConnectionString;

    @Bean(name = "remoteMongoTemplate")
    public MongoTemplate remoteMongoTemplate() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoConnectionString))
                .build();
        MongoDatabase database = MongoClients.create(settings).getDatabase("BumbiBearApp");

        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(settings), "BumbiBearApp"));
    }

    @Bean(name = "localMongoTemplate")
    public MongoTemplate localMongoTemplate() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                .build();
        MongoDatabase database = MongoClients.create(settings).getDatabase("BumbiBearApp");

        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(MongoClients.create(settings), "BumbiBearApp"));
    }

    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("remoteMongoTemplate") MongoTemplate remoteMongoTemplate,
                                       @Qualifier("localMongoTemplate") MongoTemplate localMongoTemplate) {
        try {
            // Attempt to establish a connection to remote MongoDB server
            remoteMongoTemplate.getDb().getName();
            return remoteMongoTemplate;
        } catch (Exception e) {
            // If remote connection fails, log error and use local MongoDB server
            e.printStackTrace();
            return localMongoTemplate;
        }
    }
}


