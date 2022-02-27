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
       git  branch: 'userTestBranch', url:'https://github.com/yirouu/devopscrowd.git'
      }
    }
    
    
     stage('Maven Clean') {
      steps {
        bat 'mvn clean install test'
      }
    }
    
  
  
    stage('Docker Image') {
       steps {
       bat 'docker build -t yirouu/devopscrowd:2.0.0 .'
     }       
    }
   
    stage('Docker Push') {
       steps {
           withCredentials([string(credentialsId: 'docker-hub-pw', variable: 'dockerhubpw')]) {
    bat "docker login -u yirouu -p ${dockerhubpw}"
}
         
       bat 'docker push yirouu/devopscrowd:2.0.0'
        }       
    }

 
   
}
  post {
     always {
        bat 'docker run -p 80:8090 yirouu/devopscrowd:2.0.0'

         }


  }
  triggers {
    pollSCM('H * * * *')
  }
}
