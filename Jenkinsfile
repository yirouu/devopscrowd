pipeline {
  agent {
     node { 
        label ''
        customWorkspace "jenkinsws/workspace"
        } 
  }
    tools {
    maven 'maven 3.8.2'
    jdk 'JDK 1.8'
  }
  stages {
    stage('Maven Build') {
      steps {
        sh 'mvn clean install test'
      }
    }

  }
  post {
    always {
      echo 'No converter for Publisher: hudson.plugins.deploy.DeployPublisher'
    }

  }
  triggers {
    pollSCM('H * * * *')
  }
}
