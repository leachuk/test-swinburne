pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn'
      }
    }
    stage('Test') {
      when {
        branch 'feature/S3KMD-887_jenkins'
      }
      steps {
	echo "Running branch build number: ${env.BUILD_ID}"
      }
    }
    stage() {
      when {
        branch 'develop'
      }
      steps {
        echo 'Deploying to test environment'
      }
    }
  }
}
