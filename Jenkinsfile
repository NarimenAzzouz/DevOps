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
     }
}