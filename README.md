# FlatWallet

Steps to run web app:
```
npm install -g gulp bower
cd webapp
npm install
bower install
gulp dev
```

Steps to run server from console:
```
cd app
mvn spring-boot:run
```
In order to run app correctly you have to start h2 server.
Download from[LINK](http://repo2.maven.org/maven2/com/h2database/h2/1.4.191/h2-1.4.191.jar) and run:
```
java -jar h2-1.4.191.jar
```
and run the server.

Server build status:

![alt tag](https://travis-ci.com/mmajews/FlatWallet.svg?token=r2WavVgpdwDCDJYpMPqG&branch=master)