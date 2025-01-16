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
        stage('NEXUS') {
            steps {
                nexusArtifactUploader(
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    nexusUrl: 'localhost:8081',
                    groupId: 'foyer',
                    version: '1.0.0',
                    repository: 'zakaria',
                    credentialsId: 'nexus-credentials', // Jenkins credential ID
                    artifacts: [
                        [artifactId: 'foyer-zakaria-artifact',
                         type: 'jar',
                         file: 'builds/*.jar']
                    ]
                )
            }
        }
    }
}
