pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage ('GIT') {
            steps {
                git branch: 'saafi-zakaria-foyer-devops', 
                    url: 'https://github.com/ZakariaSaafi/3CINFOGL1-FoyerDevops.git'
            }
        }
        stage ('MAVEN CLEAN') {
            steps {
                bat 'mvn clean'
            }
        }
        stage ('MAVEN COMPILE') {
            steps {
                bat 'mvn compile'
            }
        }
        stage ('maven test'){
            steps {
                bat 'mvn test'
            }
        }
        stage ('MAVEN PACKAGE') {
            steps {
                bat 'mvn package'
            }
        }
        stage('NEXUS UPLOAD') {
            steps {
                nexusArtifactUploader(
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    nexusUrl: 'localhost:8081',
                    groupId: 'tn.esprit',
                    version: '1.0.0-SNAPSHOT',
                    repository: 'zakaria-maven-hosted',
                    credentialsId: 'nexus-credentials',
                    artifacts: [
                        [artifactId: 'foyer',
                         classifier: '',
                         file: 'target/foyer-1.0.0-SNAPSHOT.jar',
                         type: 'jar']
                    ]
                )
            }
        }
    }
}
