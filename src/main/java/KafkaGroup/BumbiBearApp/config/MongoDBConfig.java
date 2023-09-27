package KafkaGroup.BumbiBearApp.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
@EnableMongoRepositories(basePackages = "KafkaGroup.BumbiBearApp.repository")
public class MongoDBConfig {

    @Bean(name = "remoteMongoTemplate")
    public MongoTemplate remoteMongoTemplate() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb+srv://cristofferostberg85:<password>@cluster0.imetavy.mongodb.net/?retryWrites=true&w=majority"))
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

    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("remoteMongoTemplate") MongoTemplate remoteMongoTemplate,
                                       @Qualifier("localMongoTemplate") MongoTemplate localMongoTemplate) {
        try {
            // Attempt to establish a connection to the remote MongoDB server
            remoteMongoTemplate.getDb().getName();
            return remoteMongoTemplate;
        } catch (Exception e) {
            // If the remote connection fails, log the error and use the local MongoDB server
            e.printStackTrace();
            return localMongoTemplate;
        }
    }
}


