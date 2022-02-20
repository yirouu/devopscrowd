pipeline {
       agent {
    node {
      label 'label'
      customWorkspace dir'D:\Documents\workspace'
    }
  }
    tools {
        maven 'maven 3.8.2'
    }
    stages {
        stage('clone git') {
            steps {
             git url: "https://github.com/yirouu/devopscrowd.git"
            }
        }
          stage('build code') {
            steps {
             sh "mvn clean install"
            }
        }
    }
    post {
           deploy adapters: [tomcat9(url: 'http://localhost:8090/', 
                              credentialsId: 'tomcat')], 
                     war: '**/*.war',
                     contextPath: 'app'
    }
}
