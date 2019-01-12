# Simple bot using Diskord
This is a simple bot example that uses the [Diskord](https://github.com/JesseCorbett/Diskord) wrapper.

# About bot token
If you want to run this bot, create a new file named 'bot.token' in the resources folder
containing only the bot's token.

See the .gitignore file to see how the file is ignored.

**NEVER PUBLISH YOUR BOT TOKEN**

# About this example
This bot uses [Spring](https://spring.io/) in kotlin for easy dependency management (autowiring and bean creation etc.)
**Many of the dependency versions are set in gradle.properties, and automatically used due to the settings.gradle.kts script*

If you don't like Spring, you can choose to not use it of course.

# Recommended
Here is my recommended toolkit if you plan on making a more advanced bot:
* Dependency management: [Spring](https://spring.io/)
* Reactive programming: (event-based flow) [Reactive](http://reactivex.io/) [[Kotlin bindings](https://github.com/ReactiveX/RxKotlin)]
* Any logging facade such as [Log4j](https://logging.apache.org/log4j/), 
[Logback](https://logback.qos.ch/), 
[Sl4j](https://www.slf4j.org/) etc..
* JSON handling: [FasterXML/Jackson](https://github.com/FasterXML/jackson) (or [Gson](https://github.com/google/gson)). Diskord ships with Jackson.

Diskord ships with the libraries for coroutines and [okhttp3](https://github.com/square/okhttp) for websocket related things.

# Kotlin/native support
Currently Diskord does not support kotlin/native, therefore you cannot generate native binaries.