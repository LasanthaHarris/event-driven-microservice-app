FROM openjdk:11-jre-slim AS builder

WORKDIR application
ADD maven/${project.build.finalName}.jar ${project.build.finalName}.jar
COPY src/main/resources/sh/wait-for wait-for
RUN chmod +x wait-for
RUN java -Djarmode=layertools -jar ${project.build.finalName}.jar extract

FROM openjdk:11-jre-slim
RUN apt-get -q update && apt-get -qy install netcat
LABEL PROJECT_NAME=${project.artifactId} \
	  NAME=${project.id} 	

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
COPY --from=builder application/wait-for ./
#CMD sh -c './wait-for www.eficode.com:80 -- echo "Eficode site is up"'
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]