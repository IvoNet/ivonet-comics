#!/usr/bin/env bash

cd ..
#mvn clean -P clean
mvn clean package -P bower
cd docker
mv ../artifact/ivonet-comics.war ./

#docker rmi ivonet/comics:latest
docker build -t ivonet/comics .
docker push ivonet/comics:latest

rm -r ivonet-comics.war
#docker run --rm -p 8888:8080 -p 9990:9990  -v /Volumes/books/Comics:/comics ivonet/comics
