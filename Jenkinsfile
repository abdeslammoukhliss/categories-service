pipeline {
    agent any

    environment {
             DOCKERHUB_REPO = 'abdomokh/categories-service'
              DOCKER_TAG = 'latest'
              OPENSHIFT_URL='https://api.sandbox-m4.g2pi.p1.openshiftapps.com:6443'
              HELM_CHART_DIR = 'spring-microservice/'  // Adjust to your chart directory
              OPENSHIFT_PROJECT = 'abdomokh38-dev'
              HELM_RELEASE_NAME = 'categories-services-realase'
              MAVEN_OPTS = "-Dmaven.repo.local=$WORKSPACE/.m2/repository"
              KUBECONFIG = credentials('kubeconfig')


    }

    stages {

        stage('Checkout') {

            steps {
                git branch: 'main', url: 'https://github.com/abdeslammoukhliss/categories-service.git'
            }
        }


           stage('Deploy with Ansible') {

                    steps {
                        script {
                             ansiblePlaybook installation: 'ansible', playbook: 'deploy_microservice.yml',extraVars: [
                                                                                                                                 KUBECONFIG: "${env.KUBECONFIG}"
                                                                                                                                  ]
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
