pipeline {
    agent any

    environment {
        GITHUB_TOKEN = credentials('3pfW6A6euNVBZRWq2mr7e5eFlq8CDdCdB0b8pCSjD5s')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    git credentialsId: '3pfW6A6euNVBZRWq2mr7e5eFlq8CDdCdB0b8pCSjD5s',
                        url: 'https://github.com/markitu/tools.git',
                        branch: 'main'
                }
            }
        }

        stage('Build and Deploy') {
            steps {
                script {
                    docker.build('tools')
                    docker.image('tools').withRun('-p 7777:7777 --rm -d')
                }
            }
        }
    }

    post {
        success {
            script {
                echo 'Успешно собрано и развернуто'
            }
        }
        failure {
            script {
                echo 'Сборка или разворачивание завершились с ошибкой'
            }
        }
    }
}