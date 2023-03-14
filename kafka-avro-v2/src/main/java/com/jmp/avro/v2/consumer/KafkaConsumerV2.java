package com.jmp.avro.v2.consumer;

import com.jmp.avro.Book;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerV2 {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerV2.class);

    public void consume(String topic) {
        Properties properties = new Properties();
        // normal consumer
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.put("group.id", "book-consumer-group-v2");
        properties.put("auto.commit.enable", "false");
        properties.put("auto.offset.reset", "earliest");

        // avro part (deserializer)
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");
        properties.setProperty("specific.avro.reader", "true");

        try (KafkaConsumer<String, Book> kafkaConsumer = new KafkaConsumer<>(properties)) {
            kafkaConsumer.subscribe(Collections.singleton(topic));

            ConsumerRecords<String, Book> records = kafkaConsumer.poll(Duration.ofSeconds(3));
            logger.info("Kafka consumer v2 is reading ...");
            for (ConsumerRecord<String, Book> consumerRecord : records) {
                Book book = consumerRecord.value();
                logger.info("Book: {}", book);
            }

            kafkaConsumer.commitSync();
        }
    }
}
