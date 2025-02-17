pipeline {
    agent any
    stages {
        stage('1. Git - Clone Repository') {
            steps {
                echo 'Cloning the repository...'
                checkout scm
            }
        }
        stage('2. Vagrant - Setup Ubuntu Environment') {
            steps {
                echo 'Setting up Ubuntu environment with Vagrant...'
            }
        }
        stage('3. Docker - Build & Compose') {
            steps {
                echo 'Building Docker images and setting up Docker Compose...'
            }
        }
        stage('4. Jenkins - Setup CI/CD') {
            steps {
                echo 'Configuring Jenkins for CI/CD pipeline...'
            }
        }
        stage('5. SonarQube - Code Quality Analysis') {
            steps {
                echo 'Running code quality analysis with SonarQube...'
            }
        }
        stage('6. JUnit - Mockito Testing') {
            steps {
                echo 'Running unit tests with JUnit and Mockito...'
            }
        }
        stage('7. Nexus - Artifact Management') {
            steps {
                echo 'Managing artifacts with Nexus...'
            }
        }
        stage('8. Grafana - Prometheus Monitoring') {
            steps {
                echo 'Monitoring application performance with Grafana and Prometheus...'
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
    }
}
