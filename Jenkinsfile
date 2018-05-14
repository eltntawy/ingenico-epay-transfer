pipeline {
  agent {
    docker {
      image 'maven:3.3.9-jdk-8'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('init') {
      steps {
        echo 'init for build'
      }
    }
  }
}