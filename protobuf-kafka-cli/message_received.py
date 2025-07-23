from kafka import KafkaConsumer
import person_pb2

# Listens to Kafka topic and prints received messages
def main():
    consumer = KafkaConsumer(
        'person-topic',
        bootstrap_servers='localhost:9092',
        auto_offset_reset='earliest',
        group_id='my-group'
    )

    print("Waiting for messages... Press Ctrl+C to exit.")
    try:
        # Continuously listen for new messages
        for message in consumer:
            person = person_pb2.Person()
            person.ParseFromString(message.value)
            print(f"Received message:\n  Name: {person.name}\n  ID: {person.id}\n  Email: {person.email}")
            print("="*50)
    except KeyboardInterrupt:
        print("\nExiting gracefully...")
    finally:
        consumer.close() # Always close the consumer connection

# Script entry point
if __name__ == '__main__':
    main()
