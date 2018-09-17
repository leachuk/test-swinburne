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
	echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
        echo 'Testing..'
      }
    }
  }
}
