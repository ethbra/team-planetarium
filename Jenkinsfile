properties([
	pipelineTriggers([
		[$class: 'SCMTrigger', scmpoll_spec: '', quietPeriod: 15]
	])
])

pipeline {

	agent {
		label 'Agent Jenkins'
	}
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
						sh '''
							pwd
							mvn test
            	   		'''
					} catch (Exception e){
						echo "Test failed, but continuing..."
						currentBuild.result = 'UNSTABLE'
					}
				}
			}
		}
		stage('Newman Test') {
			steps {
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
				to: '$DEFAULT_RECIPIENTS'
			)
		}
	}
}
