pipeline {
    agent any

     stages{
        stage('Git Chekout'){

            steps{
                git branch: 'narimenazzouz', url: 'https://github.com/NarimenAzzouz/DevOps.git'
            }

        }
        stage('UNIT TESTING'){

            steps{
                sh 'mvn test'
            }

        }
        stage('Integration Testing'){

            steps{
                sh 'mvn verify -DskipUnitTests'
            }

        }
        stage('Static Code Analysis') {
            steps {
               withSonarQubeEnv(credentialsId: 'sonar-api' ,  installationName: 'sonarserver') {
                sh 'mvn clean package sonar:sonar'
    
               } 
            }
        }
        stage('Quality Gate Status') {
            steps{
              script{
                waitForQualityGate abortPipeline: false, credentialsId: 'sonar-api'
              }
            }
        }
        stage('Upload war file to nexus') {
            steps{
              script{
                def readPomVersion = readMavenPom file: 'pom.xml'
                nexusArtifactUploader artifacts:
                [
                    [
                        artifactId: 'DevOps_Project', 
                        classifier: '', 
                        file: 'target/DevOps_Project-1.0.jar', 
                        type: 'jar'
                        ]
                ],
                credentialsId: 'nexus-auth', 
                groupId: 'tn.esprit', 
                nexusUrl: '192.168.50.10:8081', 
                nexusVersion: 'nexus3', 
                protocol: 'http', 
                repository: 'devops-release', 
                version: "${readPomVersion.version}"
              }
            }
        }
     }
}