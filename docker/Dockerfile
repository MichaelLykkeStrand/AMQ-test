FROM openjdk:11

# Set the version and the home directory
ENV ARTEMIS_VERSION 2.30.0
ENV ARTEMIS_HOME /opt/apache-artemis-${ARTEMIS_VERSION}

# Download and unzip the ActiveMQ Artemis broker
RUN curl -o apache-artemis-${ARTEMIS_VERSION}-bin.tar.gz https://downloads.apache.org/activemq/activemq-artemis/${ARTEMIS_VERSION}/apache-artemis-${ARTEMIS_VERSION}-bin.tar.gz \
    && tar xfz apache-artemis-${ARTEMIS_VERSION}-bin.tar.gz -C /opt/ \
    && rm apache-artemis-${ARTEMIS_VERSION}-bin.tar.gz

# Create a broker instance
RUN ${ARTEMIS_HOME}/bin/artemis create /var/lib/artemis-broker \
    --user admin \
    --password admin \
    --allow-anonymous

# Copy your custom broker.xml, keystore, and truststore into the Docker image
# COPY bootstrap.xml /var/lib/artemis-broker/etc/
COPY broker.xml /var/lib/artemis-broker/etc/
COPY bootstrap.xml /var/lib/artemis-broker/etc/bootstrap.xml

COPY certificates/broker.ks /var/lib/artemis-broker/etc/
COPY certificates/broker.ts /var/lib/artemis-broker/etc/

# Set the entry point
ENTRYPOINT ["/var/lib/artemis-broker/bin/artemis", "run"]
