# EPMS - Service

## production

### build

```sh
mvn generate-sources
cd src/main/webapp
npm install
npm run build
cd -
mvn package
```

*Camunda:* If you don't have a licence key or want to use
Camunda's community edition then you have to add `-Dcamunda-edition=ce`
to every `mvn`-command.

### run

```sh
java -jar target/*.jar
```

Open in browser: [http://localhost:8080](http://localhost:8080)

## development

### run

Run class `at.epms.EpmsApplication` from your favorite IDE.
