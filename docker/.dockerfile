# Use the base Artemis image
FROM apache/activemq-artemis

# Add your custom configuration
COPY ./broker.xml /var/lib/apache-artemis-instance/etc/broker.ts

# Add your certificates
COPY ./certificates/broker.ks /var/lib/apache-artemis-instance/etc/broker.ks
COPY ./certificates/broker.ts /var/lib/apache-artemis-instance/etc/broker.ts

