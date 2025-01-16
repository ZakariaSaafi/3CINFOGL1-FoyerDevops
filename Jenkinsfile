pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "localhost:8081"
        NEXUS_REPOSITORY = "Foyer"
        NEXUS_CREDENTIAL_ID = "nexus-credentials"
        // Properly bind credentials to environment variables
        NEXUS_CREDS = credentials('nexus-credentials')
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
                script {
                    // Read POM xml file using 'readMavenPom' step
                    pom = readMavenPom file: "pom.xml";
                    // Find built artifact under target folder
                    filesByGlob = findFiles(glob: "target/*.jar");
                    // Print some info from the artifact found
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    // Extract the path from the File found
                    artifactPath = filesByGlob[0].path;
                    // Assign to a boolean response verifying If the artifact name exists
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }
    }
}
