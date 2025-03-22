pipeline{

    agent any

    stages {
        stage('Build Jar'){
            steps{
                sh "mvn clean package -DskipTests"
            }

        }

        stage('Build Image') {
            steps{
                sh "docker build -t=pgavriluk/selenium:latest ."
            }

        }

        stage('Push Image') {
            environment{
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh "docker push pgavriluk/selenium:latest"
                sh "docker tag pgavriluk/selenium:latest pgavriluk/selenium:${env.BUILD_NUMBER}"
                sh "docker push pgavriluk/selenium:${env.BUILD_NUMBER}"
            }

        }
    }

    post {
        always {
            sh "docker logout"
        }
    }
}