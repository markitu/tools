pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    git url: 'https://github.com/markitu/tools.git',
                        branch: 'main'
                    echo "Workspace path: ${WORKSPACE}"
                }
            }
        }

        

        stage('Build and Deploy') {
            steps {
                script {
                    sh 'mvn clean install'
                    sh 'docker-compose -f ${WORKSPACE}/docker-compose.yml
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
