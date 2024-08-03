pipeline {
    agent any

    environment {
        DOCKERHUB_REPO = 'abdomokh/categories-service'
        DOCKER_TAG = 'latest'
        HELM_CHART_DIR = 'charts/my-app'  // Adjust to your chart directory
        OPENSHIFT_PROJECT = 'abdomokh38-dev'
        HELM_RELEASE_NAME = 'categories-services-realase'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-username/your-spring-microservice-repo.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKERHUB_REPO}:${DOCKER_TAG} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
                        sh "docker push ${DOCKERHUB_REPO}:${DOCKER_TAG}"
                    }
                }
            }
        }

        stage('Login to OpenShift') {
            steps {
                script {
                    sh "oc login ${OPENSHIFT_URL} --token=${OPENSHIFT_TOKEN}"
                    sh "oc project ${OPENSHIFT_PROJECT}"
                }
            }
        }

        stage('Deploy using Helm') {
            steps {
                script {
                    // Ensure Helm is installed
                    sh 'helm version || (curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash)'

                    // Package the Helm chart if necessary
                    sh "helm package ${HELM_CHART_DIR}"

                    // Install or upgrade the Helm chart
                    sh "helm upgrade --install ${HELM_RELEASE_NAME} ${HELM_CHART_DIR} --namespace ${OPENSHIFT_PROJECT} --create-namespace"
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
