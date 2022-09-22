def mvnCmd(String cmd) {
  sh 'mvn -B -X -s settings.xml -DskipTests=true ' + cmd
}

pipeline {
    agent {
        node {
            label 'carbonio-agent-v1'
        }
    }
    environment {
        JAVA_OPTS = '-Dfile.encoding=UTF8'
        LC_ALL = 'C.UTF-8'
        ARTIFACTORY_ACCESS=credentials('artifactory-jenkins-gradle-properties-splitted')
        BUILD_PROPERTIES_PARAMS='-Dartifactory_user=$ARTIFACTORY_ACCESS_USR -Dartifactory_password=$ARTIFACTORY_ACCESS_PSW'
        jenkins_build = 'true'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '25'))
        timeout(time: 2, unit: 'HOURS')
        skipDefaultCheckout()
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Package') {
            steps {
              mvnCmd("$BUILD_PROPERTIES_PARAMS clean verify")
              publishCoverage adapters: [jacocoAdapter(path: '**/target/site/jacoco/jacoco.xml')], calculateDiffForChangeRequests: true, failNoReports: true
              junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish snapshot to maven') {
            steps {
                mvnCmd("$BUILD_PROPERTIES_PARAMS clean deploy")
            }
        }

        stage('Publish to maven') {
            when {
                buildingTag()
            }
            steps {
                mvnCmd("$BUILD_PROPERTIES_PARAMS deploy")
            }
        }
    }
}
