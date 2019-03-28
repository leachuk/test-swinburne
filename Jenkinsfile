pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn'
      }
    }
    stage('Develop') {
         DEV02_AUTHOR_CREDS = credentials('dev-author-creds')
         DEV02_PUBLISH1_CREDS = credentials('dev-publish1-creds')

       }
       steps {
         sh "mvn -Dvault.useProxy=false -DskipTests -e -U -P deploymentpackage,installdeploymentpackage clean install -Dcrx.host=52.62.114.58 -Dcrx.port=4502 -Dcrx.username=${DEV02_AUTHOR_CREDS_USR} -Dcrx.password=${DEV02_AUTHOR_CREDS_PSW} -e -U -P deploymentpackage,installdeploymentpackage -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
         sh "mvn -Dvault.useProxy=false -DskipTests -e -U -P deploymentpackage,installdeploymentpackage clean install -Dcrx.host=52.63.178.158 -Dcrx.port=4503 -Dcrx.username=${DEV02_PUBLISH1_CREDS_USR} -Dcrx.password=${DEV02_PUBLISH1_CREDS_PSW} -e -U -P deploymentpackage,installdeploymentpackage -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
            }
     }

    stage('CacheClearPublish') {
      when { branch 'develop-v2' }
      environment {
        DEV02_PUBLISH1_CREDS = credentials('dev-publish1-creds')
      }
      steps {
        sh 'curl -u ${DEV02_PUBLISH1_CREDS} -X POST  http://52.63.178.158:4503/etc/acs-commons/dispatcher-flush/disp-flush/_jcr_content/configuration.flush.html'
      }
    }
  }
}

