pipeline {
	agent any

	stages {
		stage('Checkout') {
			steps {
				script{
					checkout scm
					echo "Checked out to branch: ${env.BRANCH_NAME}"
				}
			}
		}
		stage('Maven tests') {
			steps{
				script{
					try {
						sh 'ls'
						sh '''
							pwd
							cd Planetarium
							mvn test -f pom.xml
            	   		'''
					} catch (Exception e){
						echo "Test failed, but continuing..."
						currentBuild.result = 'UNSTABLE'
					}
				}
			}
		}
		stage('Maven build') {
			steps {
				sh '''
                    pwd
                    mvn package -DskipTests=true -f Planetarium/pom.xml

                    mv -f Planetarium/target/Planetarium-1.0.jar Planetarium/
                '''
			}
		}
		stage('Newman Test') {
			steps {
				sh '''
                    ls
                    pwd

                    cd Planetarium
                    java -jar Planetarium-1.0.jar > output.txt 2>&1  &

                    sleep 2
				'''
				sh '''
					pwd
					newman run materials/postman/Planetarium.postman_collection.json -e materials/postman/Planetarium.postman_environment.json --env-var url=localhost:8080 -r cli,json
				'''
			}
		}
	}
	post {
		always {

			emailext(
				subject: "Build Results: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
				body: "The build has finished. Please find the attached log file.",
				attachLog: true,
				attachmentsPattern: 'Planetarium/output.txt',
				to: '$DEFAULT_RECIPIENTS'
			)
		}
	}
}
