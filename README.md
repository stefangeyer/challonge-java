# challonge-java

[![CircleCI](https://circleci.com/gh/stefangeyer/challonge-java.svg?style=svg)](https://circleci.com/gh/stefangeyer/challonge-java)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/47dc0fcd548d40eb92c2c0f32272b194)](https://www.codacy.com/app/stefangeyer/challonge-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=stefangeyer/challonge-java&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/stefangeyer/challonge-java/branch/master/graph/badge.svg)](https://codecov.io/gh/stefangeyer/challonge-java)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/at.stefangeyer.challonge/core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/at.stefangeyer.challonge/core)

This library binds the [CHALLONGE! REST API](http://api.challonge.com/v1) calls for Java and any other JVM language.

Released under the MIT license.

## gradle

```groovy
compile group: 'at.stefangeyer.challonge', name: 'core', version: '2.0'
```

## maven

```xml
<dependency>
    <groupId>at.stefangeyer.challonge</groupId>
    <artifactId>core</artifactId>
    <version>2.0</version>
</dependency>
```

## examples

Check the [wiki](https://github.com/stefangeyer/challonge-java/wiki) for instructions on how get started.

## modules

This project is split up in the modules core, rest and serializers. While core contains
the main functionality and interfaces for the rest and serializer package, the latter contains all available 
implementations of their respective interfaces. Rest and serializers contain all implementations of the core module's interfaces `Serializer` 
and `RestClient`. Additional implementations may follow in the future.

### serializers

Currently, there are the following serializer implementations:
- [Gson](https://github.com/google/gson)

### rest clients

Currently, there are the following rest client implementations:
- [Retrofit](https://github.com/square/retrofit)