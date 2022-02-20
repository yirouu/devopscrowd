pipeline {
 agent {
    label ('ws') { 
        workspacePath 'jenkinsws/workspace'
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
           stage('deploy code') {
            steps {
              deploy adapters: [tomcat9(url: 'http://localhost:8090/', 
                              credentialsId: 'tomcat')], 
                     war: '**/*.war',
                     contextPath: 'app'
            }
        }
    }
 
}
