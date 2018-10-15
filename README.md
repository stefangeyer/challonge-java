#challonge-kotlin

This library binds the [CHALLONGE! REST API](http://api.challonge.com/v1) calls for Kotlin and any other JVM language. 
Once completed, this library will replace it's predecessor [challonge-java](https://github.com/stefangeyer/challonge-java).

**This library is still in development, use at own risk**

The main differences between this project and it's previous version are the following:
- Removal of all unnecessary dependencies
- Modularization to allow multiple implementations of certain components
- Usage of Kotlin to make use of it's language features such as default parameter arguments

## modules

This project is split up in the 3 main modules core, rest and serializers. As the name suggests, the core module contains
the main functionality. Rest and serializers contain all implementations of the core module's interfaces `Serializer` 
and `RestClient`. The different implementations may use 3rd party libraries to achieve their goal.
Additional implementations may follow in the future.

### serializers

Currently, there are the following serializer implementations:
- [Gson](https://github.com/google/gson)

### rest clients

Currently, there are the following rest client implementations:
- [Retrofit](https://github.com/square/retrofit)