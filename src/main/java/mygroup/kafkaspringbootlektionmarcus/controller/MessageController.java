package mygroup.kafkaspringbootlektionmarcus.controller;

import mygroup.kafkaspringbootlektionmarcus.kafka.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController                         //2st olika API:er, REST och SOAP (REST är bättre, och snabbare) (= SOAP bygger på REST, i grunden)
@RequestMapping("/api/v1/kafka")
public class MessageController {

    private final KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    //http://localhost:8080/api/v1/kafka/publish?message=Hello World
    @GetMapping("/publish")
    public ResponseEntity<String>publish(@RequestParam("message")String message){

        kafkaProducer.sendMessage("myTopic", message);
        return ResponseEntity.ok("Message sent to the topic");
    }
}

