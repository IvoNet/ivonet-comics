# IvoNet Comics website

On this website it is possible to browse and read comics.


# Prerequisites

* Maven 3x
* Java 1.8+
* Java EE 7 compatible application server
* npm
* bower


# Build guide

```bash
mvn package -P bower

```

## application.properties

Note that you need to set the `rootFolder` property to a location where your comics reside.

For the docker container this needs to be set to /comics


# Deep clean

```bash
mvn clean -P clean
```


# References:

* [AngularJS](https://angularjs.org)
* [Bootstrap](http://getbootstrap.com)
* [Lightbox](https://github.com/compact/angular-bootstrap-lightbox)
* [Webworkers](http://www.html5rocks.com/en/tutorials/workers/basics/)
* [ngmodules](http://ngmodules.org)
* [ng-newsletter](http://www.ng-newsletter.com)

# License

        Copyright 2016 Ivo Woltring <WebMaster@ivonet.nl>
        
        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at
        
            http://www.apache.org/licenses/LICENSE-2.0
        
        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
