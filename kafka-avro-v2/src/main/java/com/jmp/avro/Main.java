package com.jmp.avro;

import com.jmp.avro.v2.consumer.KafkaConsumerV2;
import com.jmp.avro.v2.producer.KafkaProducerV2;

public class Main {
    private static final String TOPIC_NAME = "book-kafka-avro";

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("c")) {
            KafkaConsumerV2 consumerV2 = new KafkaConsumerV2();
            consumerV2.consume(TOPIC_NAME);
        } else {
            KafkaProducerV2 producerV2 = new KafkaProducerV2();
            producerV2.produce(TOPIC_NAME);
        }

    }
}
