#!/usr/bin/env bash

docker run --rm -p 8080:8080 -v $(pwd)/../src/test/resources/:/comics ivonet/comics