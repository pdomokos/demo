pipeline {
    agent any
    tools { 
        maven 'Default Maven' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
		    echo "JAVA_HOME = ${JAVA_HOME}"
                ''' 
            }
        }

	stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml' 
                }
            }
        }
    stage ('Push') {
            steps {
                 docker.withRegistry('http://localhost:5001') {

                     def customImage = docker.build("demo-image:${env.BUILD_ID}")

                     /* Push the container to the custom Registry */
                     customImage.push()
                }
           }
    }
}
