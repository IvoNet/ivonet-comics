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
