pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn'
      }
    }
    stage('Test') {
      steps {
        echo 'Testing..'
      }
    }
    stage('Deploy') {
      if (env.BRANCH_NAME == 'master') {
        echo 'I only execute on the master branch'
      } else {
        echo "I execute on the ${env.BRANCH_NAME} branch"
      }
    }
  }
}
