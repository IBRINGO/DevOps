pipeline {
    agent any

    tools {
        maven 'Maven3'      
        jdk 'JDK17'             
    }

    environment {
        SONARQUBE = credentials('sonarqube-token') 
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/IBRINGO/DevOps.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeLocal') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=mon-projet -Dsonar.host.url=http://localhost:9000 -Dsonar.login=$SONARQUBE'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
