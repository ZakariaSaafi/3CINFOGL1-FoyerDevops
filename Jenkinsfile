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
        stage ('MAVEN SONARQUBE') {
            steps { 
                bat 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Happy@900@900'
            }    
        }
        stage ('maven test'){
            steps {
                bat 'mvn test'
            }
        }
        stage ('NEXUS') {
            steps {
                bat 'mvn deploy -DskipTests' 
            }
        }
    }
}
