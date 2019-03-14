pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn'
      }
    }
    stage('Develop') {
      when { branch 'develop' }
      environment {
        DEV_AUTHOR_CREDS = credentials('dev-author-creds')
        DEV_PUBLISH1_CREDS = credentials('dev-publish1-creds')
      }
      steps {
        sh "mvn -Dvault.useProxy=false -DskipTests -e -U -P deploymentpackage,installdeploymentpackage clean install -Dcrx.host=52.63.25.46 -Dcrx.port=4502 -Dcrx.username=${DEV_AUTHOR_CREDS_USR} -Dcrx.password=${DEV_AUTHOR_CREDS_PSW} -e -U -P deploymentpackage,installdeploymentpackage -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
        sh "mvn -Dvault.useProxy=false -DskipTests -e -U -P deploymentpackage,installdeploymentpackage clean install -Dcrx.host=52.63.236.227 -Dcrx.port=4503 -Dcrx.username=${DEV_PUBLISH1_CREDS_USR} -Dcrx.password=${DEV_PUBLISH1_CREDS_PSW} -e -U -P deploymentpackage,installdeploymentpackage -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
      }
    }
    stage('Test') {
      when { branch 'test' }
      environment {
        TEST_AUTHOR_CREDS = credentials('test-author-creds')
        TEST_PUBLISH1_CREDS = credentials('test-publish1-creds')
        TEST_PUBLISH2_CREDS = credentials('test-publish2-creds')
      }
      steps {
        sh "mvn -Dvault.useProxy=false -DskipTests -e -U -P deploymentpackage,installdeploymentpackage clean install -Dcrx.host=13.211.194.181 -Dcrx.port=4502 -Dcrx.username=${TEST_AUTHOR_CREDS_USR} -Dcrx.password=${TEST_AUTHOR_CREDS_PSW} -e -U -P deploymentpackage,installdeploymentpackage -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
        sh "mvn -Dvault.useProxy=false -DskipTests -e -U -P deploymentpackage,installdeploymentpackage clean install -Dcrx.host=13.237.162.203 -Dcrx.port=4503 -Dcrx.username=${TEST_PUBLISH1_CREDS_USR} -Dcrx.password=${TEST_PUBLISH1_CREDS_PSW} -e -U -P deploymentpackage,installdeploymentpackage -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
        sh "mvn -Dvault.useProxy=false -DskipTests -e -U -P deploymentpackage,installdeploymentpackage clean install -Dcrx.host=13.238.172.197 -Dcrx.port=4503 -Dcrx.username=${TEST_PUBLISH2_CREDS_USR} -Dcrx.password=${TEST_PUBLISH2_CREDS_PSW} -e -U -P deploymentpackage,installdeploymentpackage -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
      }
    }
     stage('Dev02') {
          when { branch 'develop-v2' }
          environment {
            DEV02_AUTHOR_CREDS = credentials('dev-author-creds')
            DEV02_PUBLISH1_CREDS = credentials('dev-publish1-creds')

          }
          steps {
            sh "mvn -Dvault.useProxy=false -DskipTests -e -U -P deploymentpackage,installdeploymentpackage clean install -Dcrx.host=52.62.114.58 -Dcrx.port=4502 -Dcrx.username=${DEV02_AUTHOR_CREDS_USR} -Dcrx.password=${DEV02_AUTHOR_CREDS_PSW} -e -U -P deploymentpackage,installdeploymentpackage -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
            sh "mvn -Dvault.useProxy=false -DskipTests -e -U -P deploymentpackage,installdeploymentpackage clean install -Dcrx.host=52.63.178.158 -Dcrx.port=4503 -Dcrx.username=${DEV02_PUBLISH1_CREDS_USR} -Dcrx.password=${DEV02_PUBLISH1_CREDS_PSW} -e -U -P deploymentpackage,installdeploymentpackage -DskipTests=true -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn"
               }
        }
    stage('CacheClearPublish-dev') {
          when { branch 'test' }
          environment {
            DEV_PUBLISH1_CREDS = credentials('dev-publish1-creds')
          }
          steps {
            sh 'curl -u ${DEV_PUBLISH1_CREDS} -X POST  http://52.63.236.227:4503/etc/acs-commons/dispatcher-flush/disp-flush/_jcr_content/configuration.flush.html'
          }
        }
    stage('CacheClearPublish-dev02') {
          when { branch 'test' }
          environment {
            DEV02_PUBLISH1_CREDS = credentials('dev-publish1-creds')
          }
          steps {
            sh 'curl -u ${DEV02_PUBLISH1_CREDS} -X POST  http://52.63.178.158:4503/etc/acs-commons/dispatcher-flush/disp-flush/_jcr_content/configuration.flush.html'
          }
        }

    stage('CacheClearPublish-1') {
      when { branch 'test' }
      environment {
        TEST_PUBLISH1_CREDS = credentials('test-publish1-creds')
      }
      steps {
        sh 'curl -u ${TEST_PUBLISH1_CREDS} -X POST  http://13.238.172.197:4503/etc/acs-commons/dispatcher-flush/disp-flush/_jcr_content/configuration.flush.html'
      }
    }
    stage('CacheClearPublish-2') {
      when { branch 'test' }
      environment {
        TEST_PUBLISH2_CREDS = credentials('test-publish2-creds')
      }
      steps {
        sh 'curl -u ${TEST_PUBLISH2_CREDS} -X POST  http://13.237.162.203:4503/etc/acs-commons/dispatcher-flush/disp-flush/_jcr_content/configuration.flush.html'
      }
    }
  }
}

