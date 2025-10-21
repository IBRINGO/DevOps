pipeline {
    agent any

    environment {
        DB_URL = "jdbc:mysql://mysql:3306/ecommerce"
        DB_USER = "root"
        DB_PASS = "root"
        TOMCAT_WEBAPPS = "/var/jenkins_home/tomcat_webapps"
        MAVEN_OPTS = "-Dmaven.repo.local=/root/.m2/repository"
    }

    stages {

        stage('Checkout') {
            steps {
                echo "📦 Récupération du code source..."
                git branch: 'main', url: 'https://github.com/IBRINGO/DevOps.git'
            }
        }

        stage('Parallel Build & Tests') {
            parallel {
                stage('Build (cached)') {
                    steps {
                        sh 'mvn clean package -DskipTests'
                    }
                }

                stage('Unit Tests') {
                    steps {
                        sh 'mvn test -Dtest=UtilisateurUnitTest'
                    }
                }

                stage('Integration Tests (H2)') {
                    steps {
                        sh 'mvn test -DTEST_ENV=true -Dtest=SampleTest'
                    }
                }
            }
        }

        stage('Coverage Report') {
            steps {
                echo "📈 Génération du rapport de couverture JaCoCo..."
                sh 'mvn clean test jacoco:report'
            }
        }

        stage('SonarQube Analysis') {
            environment {
                scannerHome = tool 'sonar-scanner'
            }
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh """
                        ${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectKey=ecommerce \
                        -Dsonar.projectName="Ecommerce Website" \
                        -Dsonar.projectVersion=1.0 \
                        -Dsonar.sources=src/main/java \
                        -Dsonar.tests=src/test/java \
                        -Dsonar.java.binaries=target/classes \
                        -Dsonar.junit.reportPaths=target/surefire-reports \
                        -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml \
                        -Dsonar.host.url=http://sonarqube:9000
                    """
                }
            }
        }

        stage('Publish Test Report') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Incremental Deploy to Tomcat') {
            when {
                changeset "**/*.java"
            }
            steps {
                echo "🚀 Déploiement incrémental sur Tomcat..."
                sh "mkdir -p ${TOMCAT_WEBAPPS}"
                sh "rm -f ${TOMCAT_WEBAPPS}/ecommerce.war"
                sh "cp target/*.war ${TOMCAT_WEBAPPS}/ecommerce.war"
                sh 'sleep 25'
            }
        }
    }

    post {
        success {
            echo "✅ Déploiement terminé avec succès !"
            echo "🌍 Accède à l’application : http://localhost:8085/ecommerce/"
        }
        failure {
            echo "❌ Pipeline échoué ! Consulte les logs Jenkins pour les erreurs."
        }
    }
}
