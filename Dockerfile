# Используем образ с Maven и OpenJDK 17
FROM maven:3.8.4-openjdk-17-slim

# Копируем файлы приложения в контейнер
COPY . /usr/src/app

# Устанавливаем переменную окружения для Maven
ENV MAVEN_HOME /usr/share/maven

# Добавляем Maven в переменную PATH
ENV PATH $MAVEN_HOME/bin:$PATH

# Устанавливаем и собираем зависимости с использованием Maven
WORKDIR /usr/src/app
RUN mvn clean install

# Задаем команду для запуска приложения
CMD ["java", "-jar", "target/tools.jar"]