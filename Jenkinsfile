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

                def nexusRepo = readPomVersion.version.endsWith("SNAPSHOT") ? "devops-snapshot" : "devops-release"
                nexusArtifactUploader artifacts:
                [
                    [
                        artifactId: 'DevOps_Project', 
                        classifier: '', 
                        file: 'target/DevOps_Project-1.1-SNAPSHOT.jar', 
                        type: 'jar'
                        ]
                ],
                credentialsId: 'nexus-auth', 
                groupId: 'tn.esprit', 
                nexusUrl: '192.168.50.10:8081', 
                nexusVersion: 'nexus3', 
                protocol: 'http', 
                repository: nexusRepo, 
                version: "${readPomVersion.version}"
              }
            }
        }
        stage('Docker Image Build') {

            
            steps{


              script {
                sh 'docker image build -t $JOB_NAME:v1.$BUILD_ID .'
                sh 'docker image tag  $JOB_NAME:v1.$BUILD_ID narimenazzouz/$JOB_NAME:v1.$BUILD_ID'

              }
            }
        }
     }
}