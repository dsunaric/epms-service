# EPMS - Service

Executive Process Model Suggestion Service

This Project is meant to provide a service for suggesting changes to an executable BPMN process Model based on current 
research and best practices for building executable BPMN. 

This Software is part of the bachelor thesis on ['How to Model and Optimize Executable Process Models'](https://github.com/dsunaric/thesis)

## status

in developement

## build

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

## run

```sh
java -jar target/*.jar
```

Open in browser: [http://localhost:8080](http://localhost:8080)

## development

### run

Run class `at.epms.EpmsApplication` from your favorite IDE.
