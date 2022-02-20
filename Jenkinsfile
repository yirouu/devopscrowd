pipeline {
  agent {
     node { 
        label ''
        customWorkspace "D:\\Documents\\workspace"
        } 
  }
    tools {
    maven 'maven 3.8.2'
    jdk 'JDK 1.8'
  }
  stages {
    
    stage('Maven Clone') {
      steps {
        git url:'https://github.com/yirouu/devopscrowd.git'
      }
    }
    
    stage('Maven Build') {
      steps {
        bat 'mvn clean install test'
      }
    }
    
        stage('Maven Deploy') {
      steps {
          deploy adapters: [tomcat9(url: 'http://localhost:8090/devopscrowds', 
                              credentialsId: '74daef0d-88dc-490f-8892-8d3f678153d4')], 
                     war: '**/*.war'
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
