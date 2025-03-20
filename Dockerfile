FROM bellsoft/liberica-openjdk-alpine:17.0.14

# Install curl and jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources .

# Environment variables
# BROWSER
# HUB_HOST
# TEST_SUITE
# THREAD_COUNT

# Run the tests
ENTRYPOINT exec java -cp 'libs/*' \
            -Dselenium.grid.enabled=true \
            -Dselenium.grid.hubHost=${HUB_HOST} \
            -Dbrowser=${BROWSER} \
            org.testng.TestNG \
            -threadcount ${THREAD_COUNT} \
            test-suites/${TEST_SUITE}