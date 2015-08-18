#!/bin/bash

mvn install -DskipTests=true

./saiku-server/target/dist/saiku-server/start-saiku.sh
./saiku-server/target/dist/saiku-server/stop-saiku.sh
tail -f ./saiku-server/target/dist/saiku-server/tomcat/logs/saiku.log &
tail -f ./saiku-server/target/dist/saiku-server/tomcat/logs/catalina.out&
