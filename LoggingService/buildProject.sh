#!/bin/bash

cd ./logService
javac *.java
cd ../

echo "Building Client Jar file"
javac ./logClient/*.java
clientManifest=`pwd`"/ClientManifest.txt"
echo -e "Main-Class: logClient.LogClient\n" > $clientManifest
jar cvfm LogClient.jar $clientManifest logService/*.class logClient/*.class


echo "Building Server Jar file"
javac ./logServer/*.java
serverManifest=`pwd`"/ServerManifest.txt"
echo -e "Main-Class: logServer.LogServer\n" > $serverManifest
jar cvfm LogServer.jar $serverManifest logService/*.class logServer/*.class
