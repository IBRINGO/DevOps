pipeline {
    agent any

    environment {
        DB_URL = "jdbc:mysql://mysql:3306/ecommerce"
        DB_USER = "root"
        DB_PASS = "root"
        TOMCAT_WEBAPPS = "C:\\apache-tomcat-10.1.30\\webapps"
        MAVEN_OPTS = "-Dmaven.repo.local=C:\\Users\\Administrator\\.m2\\repository"
    }

    tools {
    jdk 'JDK17'
    maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                echo "📦 Clonage du dépôt Git..."
                git branch: 'main', url: 'https://github.com/IBRINGO/DevOps.git'
            }
        }

        stage('Build') {
            steps {
                echo "⚙️ Compilation du projet Maven..."
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            environment {
                scannerHome = tool 'SonarScanner'
            }
            steps {
                echo "🔍 Analyse de la qualité du code avec SonarQube..."
                withSonarQubeEnv('SonarQubeLocal') {
                    withCredentials([string(credentialsId: 'jenkins-token', variable: 'SONAR_TOKEN')]) {
                        bat """
                            "%scannerHome%\\bin\\sonar-scanner.bat" ^
                            -Dsonar.projectKey=ecommerce ^
                            -Dsonar.projectName="Ecommerce Website" ^
                            -Dsonar.projectVersion=1.0 ^
                            -Dsonar.sources=src\\main\\java ^
                            -Dsonar.java.binaries=target\\classes ^
                            -Dsonar.host.url=http://localhost:9000 ^
                            -Dsonar.login=%SONAR_TOKEN%
                        """
                    }
                }
            }
        }


        stage('Deploy to Tomcat') {
            steps {
                echo "🚀 Déploiement du package WAR sur Tomcat..."
                bat """
                    if not exist "%TOMCAT_WEBAPPS%" mkdir "%TOMCAT_WEBAPPS%"
                    copy target\\*.war "%TOMCAT_WEBAPPS%\\ecommerce.war" /Y
                """
            }
        }
    }

    post {
        success {
            echo "✅ Build et déploiement réalisés avec succès !"
            echo "🌍 Application disponible sur : http://localhost:8085/ecommerce/"
        }
        failure {
            echo "❌ Le pipeline a échoué — vérifie les logs Jenkins."
        }
    }
}
