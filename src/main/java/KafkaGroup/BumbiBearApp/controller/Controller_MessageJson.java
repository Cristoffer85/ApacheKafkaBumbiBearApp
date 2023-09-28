package KafkaGroup.BumbiBearApp.controller;

import KafkaGroup.BumbiBearApp.payload.MySQLUser;
import KafkaGroup.BumbiBearApp.producer.Producer_KafkaJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class Controller_MessageJson {
    private Producer_KafkaJson kafkaProducer;

    @Autowired
    public Controller_MessageJson(Producer_KafkaJson kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody MySQLUser mySQLUser) {
        kafkaProducer.sendMessage("BumbiRegister", mySQLUser);
        return ResponseEntity.ok("[Json] Message sent to Kafka Topic");
    }
}

