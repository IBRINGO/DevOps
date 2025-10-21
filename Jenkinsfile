pipeline {
    agent any

    environment {
        // 🔧 Variables globales
        DB_URL = "jdbc:mysql://mysql:3306/ecommerce"
        DB_USER = "root"
        DB_PASS = "root"
        TOMCAT_WEBAPPS = "C:\\apache-tomcat-10.1.30\\webapps"
        MAVEN_OPTS = "-Dmaven.repo.local=/root/.m2/repository"
    }
    tools {
        maven 'maven'   // nom défini dans Manage Jenkins > Tools
        jdk 'JDK17'      // optionnel si tu veux forcer la version du JDK
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
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            environment {
                scannerHome = tool 'SonarScanner'   // nom défini dans Manage Jenkins > Tools
            }
            steps {
                echo "🔍 Analyse de la qualité du code avec SonarQube..."
                withSonarQubeEnv('SonarQubeLocal') { // nom configuré dans Manage Jenkins > System
                    sh """
                        ${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=ecommerce \
                        -Dsonar.projectName="Ecommerce Website" \
                        -Dsonar.projectVersion=1.0 \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.java.binaries=target/classes \
                        -Dsonar.host.url=http://localhost:9000
                    """
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                echo "🚀 Déploiement du package WAR sur Tomcat..."
                sh """
                    mkdir -p ${TOMCAT_WEBAPPS}
                    cp target/*.war ${TOMCAT_WEBAPPS}/ecommerce.war
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
