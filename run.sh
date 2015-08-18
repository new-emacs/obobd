#!/bin/bash

mvn install -DskipTests=true

kill -9  `lsof -i:8080 | awk '{print $2}'`
./saiku-server/target/dist/saiku-server/start-saiku.sh
tail -f ./saiku-server/target/dist/saiku-server/tomcat/logs/saiku.log &
tail -f ./saiku-server/target/dist/saiku-server/tomcat/logs/catalina.out&
