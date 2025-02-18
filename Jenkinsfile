pipeline {
    agent any
    environment {
        SONARQUBE_SERVER = 'SonarQube'  // Name of your SonarQube server configured in Jenkins
        NEXUS_URL = 'http://localhost:8081/repository/maven-snapshots/'  // URL for the Nexus snapshot repository
        NEXUS_CREDENTIALS = 'nexus-credentials-id'  // Nexus credentials ID configured in Jenkins
    }
    tools {
        maven 'M2-HOME'
        jdk 'JAVA_HOME'  // Name of the JDK installation configured in Jenkins
    }
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
                script {
                    // This assumes you have a sonar-project.properties file in the root of your repository
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn clean verify sonar:sonar'
                    }
                }
            }
        }
        stage('6. JUnit - Mockito Testing') {
            steps {
                echo 'Running unit tests with JUnit and Mockito...'
                script {
                    // Run JUnit tests
                    sh 'mvn test'
                }
            }
            post {
                always {
                    // Publish JUnit test results
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('7. Nexus - Artifact Management') {
            steps {
                echo 'Deploying artifact to Nexus...'
                script {
                    // Ensure the version in POM is a snapshot version
                    sh """
                        mvn clean deploy -DaltDeploymentRepository=deploymentRepo::default::${NEXUS_URL} -DskipTests
                    """
                }
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
