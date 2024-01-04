pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Шаг для проверки кода из репозитория
                checkout scm
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                // Шаг для сборки Docker-образа и его загрузки в Docker Hub
                script {
                    def dockerImage = docker.build('markitu33/tools:1.0.0')
                    dockerImage.push()
                }
            }
        }

        stage('Deploy Docker Container') {
            steps {
                // Шаг для запуска Docker-контейнера
                script {
                    dockerImage('markitu33/tools:1.0.0').run('-p 7777:7777 -d --name my-container')
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}
