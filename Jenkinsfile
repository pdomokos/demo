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
                script{
                     docker.withRegistry('http://172.18.0.4:5000') {

                         def customImage = docker.build("demo-image:${env.BUILD_ID}")

                         /* Push the container to the custom Registry */
                         customImage.push()
                    }
                }
           }
        }
        stage('Deploy'){
            steps {
                sh "docker stop demo | true"
                sh "docker rm demo | true"
                sh "docker run --name demo -d -p 9000:9000 172.18.0.4:5000/demo-image:${env.BUILD_ID} --server.port=9000"
            }
        }
    }
}