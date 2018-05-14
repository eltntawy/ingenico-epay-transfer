pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
                success {
                    archive 'target/*.jar'
                    archiveArtifacts 'backend/target/*.jar'
                }
            }
        }
        stage('Deliver') {
            steps {
                bat './deliver.bat'
            }
        }
    }
}
