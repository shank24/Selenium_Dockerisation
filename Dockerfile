FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

#WORKSPACE
WORKDIR /usr/share/udemy

# Add all the .jar under target location from the host into this image.
ADD target/selenium-docker.jar        selenium-docker.jar
ADD target/selenium-docker-tests.jar  selenium-docker-tests.jar
ADD target/libs                       libs

# in case if any other dependencies like .csv/.json/.xlsx
# please add that as well.

# Add Suite Files
ADD book-flight-module.xml    book-flight-module.xml 
ADD search-module.xml         search-module.xml

# Add HealthCheck script
ADD healthcheck.sh            healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

ENTRYPOINT sh healthcheck.sh



  