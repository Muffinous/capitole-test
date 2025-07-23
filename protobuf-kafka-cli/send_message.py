import json
import sys
from kafka import KafkaProducer
import person_pb2

# Converts a JSON object to a serialized Protobuf message
def json_to_protobuf(json_data):
    person = person_pb2.Person()
    person.name = json_data["name"]
    person.id = json_data["id"]
    if "email" in json_data:
        person.email = json_data["email"]
    return person.SerializeToString()

# Reads a JSON file, converts it to Protobuf, and sends it to Kafka
def main(json_path):
    with open(json_path, 'r') as f:
        data = json.load(f)

    producer = KafkaProducer(bootstrap_servers='localhost:9092')
    protobuf_msg = json_to_protobuf(data)
    producer.send('person-topic', protobuf_msg)
    producer.flush()
    print("Message sent to Kafka!")

# Entry point: checks for required argument and runs main function
if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("\n" + "="*50)
        print("⚠️  MISSING FILE ⚠️")
        print("Please run the program as:")
        print("    python send_message.py sample.json")
        print("="*50 + "\n")
        sys.exit(1)
    main(sys.argv[1])
