## Task/Note/TODO CLI program in Java

A simple Java CLI program so that you can create, update and keep track of your tasks and TODOs.

### Requirements to run:

- Java version: 21
- Terminal with UTF-8 (Bash, PowerShell)

### How to run:

On the root directory, type in the terminal the following command to package the application with
dependencies to a JAR file. Recommend way to run the application. The resulting JAR file will be at
the directory `target/*-jar-with-dependencies.jar`.

```bash
mvn package assembly:single
```

With this JAR file, you can rename it and put in a different directory, just remember to keep the extension `.jar`.
To run, just type in the terminal:

```bash
java -jar [nameOfTheJarFile].jar
```

When you run the program for the first time, it will look for a hidden file called `.saved_tasks.csv` in the same
directory of the JAR file; if it doesn't exit, it will automatically create one. You can manually edit it, if you want,
just be careful with the formatting, otherwise, a wrong formatting can result in an exception.

### Dependencies used:

- JUnit-Jupiter (Tests)
- Lombok (Reduce the boilerplate code used, you may require to download a plugin for your IDE)
- GSON (JSON parser library)
