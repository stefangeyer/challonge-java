# challonge-java

[![CircleCI](https://circleci.com/gh/stefangeyer/challonge-kotlin.svg?style=svg)](https://circleci.com/gh/stefangeyer/challonge-kotlin)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1769c74da0854cb4895fba208b7b95da)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=stefangeyer/challonge-kotlin&amp;utm_campaign=Badge_Grade)


This library binds the [CHALLONGE! REST API](http://api.challonge.com/v1) calls for Java and any other JVM language.

<!---
The main differences between this project and it's previous version are the following:
- Removal of all unnecessary dependencies
- Modularization to allow multiple implementations of certain components
- Usage of Kotlin to make use of it's language features such as default parameter arguments
-->

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