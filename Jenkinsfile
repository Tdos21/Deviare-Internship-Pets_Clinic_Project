pipeline {
    agent any

    environment {
        PROJECT_NAME = 'Deviare-Internship-Pets_Clinic_Project'
        GIT_REPO = 'https://github.com/Tdos21/Deviare-Internship-Pets_Clinic_Project.git'
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning repository...'
                git branch: 'main', url: "${GIT_REPO}"
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh './mvnw clean install' // Adjust if using a different build tool
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh './mvnw test'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the application...'
                sh './mvnw package'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                sh './deploy.sh' // Replace with your actual deployment script
            }
        }
    }

    post {
        success {
            echo 'Build and deployment succeeded!'
        }
        failure {
            echo 'Build or deployment failed. Please check logs.'
        }
    }
}
