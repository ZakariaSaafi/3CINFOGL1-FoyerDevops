pipeline {
    agent any
    tools {
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }

    environment {
        // Nexus configurations
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "localhost:8081"
        NEXUS_REPOSITORY = "Foyer"
        NEXUS_CREDENTIAL_ID = "nexus-credentials"
        ARTIFACT_VERSION = "${BUILD_NUMBER}"
    }

    stages {
        stage ('Clone Repository') {
            steps {
                git branch: 'saafi-zakaria-foyer-devops', 
                    url: 'https://github.com/ZakariaSaafi/3CINFOGL1-FoyerDevops.git'
            }
        }
        stage ('Maven Clean') {
            steps {
                bat 'mvn clean'
            }
        }
        stage ('Maven Compile') {
            steps {
                bat 'mvn compile'
            }
        }
        stage ('Maven Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage ('Maven Package') {
            steps {
                bat 'mvn package'
            }
        }
        stage ('Publish to Nexus') {
            steps {
                script {
                    def pom = readMavenPom file: "pom.xml"
                    def filesByGlob = findFiles(glob: "target/*.${pom.packaging}")
                    if (filesByGlob.size() == 0) {
                        error "No artifact found in target directory!"
                    }
                    def artifactPath = filesByGlob[0].path
                    echo "Publishing artifact: ${artifactPath}"

                    nexusArtifactUploader(
                        nexusVersion: NEXUS_VERSION,
                        protocol: NEXUS_PROTOCOL,
                        nexusUrl: NEXUS_URL,
                        groupId: pom.groupId,
                        version: ARTIFACT_VERSION,
                        repository: NEXUS_REPOSITORY,
                        credentialsId: NEXUS_CREDENTIAL_ID,
                        artifacts: [[
                            artifactId: pom.artifactId,
                            classifier: '',
                            file: artifactPath,
                            type: pom.packaging
                        ]]
                    )
                }
            }
        }
    }
}
