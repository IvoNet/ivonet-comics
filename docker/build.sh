#!/usr/bin/env bash

cd ..
mvn clean package
cd docker
mv ../artifact/ivonet-comics.war ./

docker rmi ivonet/comics:latest
docker build -t ivonet/comics .

rm -r ivonet-comics.war