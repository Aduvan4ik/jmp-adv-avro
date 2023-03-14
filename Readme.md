To build app:  
mvn clean install  
To run kafka infrastructure (UI is available on http://127.0.0.1:3030/):  
docker compose up  
To run V1 producer:    
java -jar .\kafka-avro-v1\target\kafka-avro-v1.jar  
To run V1 consumer:  
java -jar .\kafka-avro-v1\target\kafka-avro-v1.jar c  
To run V2 producer:  
java -jar .\kafka-avro-v2\target\kafka-avro-v2.jar  
To run V2 consumer:  
java -jar .\kafka-avro-v2\target\kafka-avro-v2.jar c  

Schema versions have full compatibility, so you can send event buy V1 producer and read by V2 and vice versa 



