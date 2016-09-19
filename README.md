#FlatWallet

Steps to initialize web app:
```
npm install -g bower
cd FlatWallet/app/src/main/resources/static
bower install
```

Steps to run server from console:
```
cd app
mvn spring-boot:run
```
In order to run app correctly you have to start h2 server (if you want to work in development mode, other databases work fine also).
Download from[LINK](http://repo2.maven.org/maven2/com/h2database/h2/1.4.191/h2-1.4.191.jar) and run:
```
java -jar h2-1.4.191.jar
```
and run the server.

If you want to enable facebook login, please paste your keys in **application.properties**:
```
facebook.appKey=insert app key
facebook.appSecret=insert app secret
```

Server build status:

![alt tag](https://travis-ci.com/mmajews/FlatWallet.svg?token=r2WavVgpdwDCDJYpMPqG&branch=master)

Please configure git:
```
git config branch.autosetuprebase
```