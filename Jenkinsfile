pipeline {
	agent any
	triggers {
		pollSCM('H/5 * * * *')  // Poll every 5 minutes as an example, only for testing
	}

	stages {
		//stage('Checkout Repository') {
		//    steps {
		//        script {
		//            // Checkout the repository and fetch all branches
		//            checkout([
		//                $class: 'GitSCM',
		//                branches: [[name: '*/main']],  // Checkout the main branch to get all branches
		//                userRemoteConfigs: [[
		//                    url: 'https://github.com/ethbra-revature/team-planetarium.git',
		//                ]],
		//                extensions: [[$class: 'CloneOption', noTags: false, shallow: false]]  // Ensure all branches are fetched
		//            ])
		//        }
		//    }
		//}
		//stage('Get Latest Commit Hash') {
		//    steps {
		//        script {
		//            // Fetch all branches and get the latest commit hash from the entire repo
		//            def latestCommitHash = sh(script: "git log --all --pretty=format:'%H' -n 1", returnStdout: true).trim()
		//            echo "Latest commit hash: ${latestCommitHash}"
		//
		//            // Save the commit hash to an environment variable
		//            env.LATEST_COMMIT_HASH = latestCommitHash
		//
		//        }
		//    }
		//}
		//stage('Checkout Latest Commit') {
		//    steps {
		//        script {
		//            // Checkout the specific commit using the latest commit hash
		//            checkout([
		//                $class: 'GitSCM',
		//                branches: [[name: "${env.LATEST_COMMIT_HASH}"]],  // Checkout using the commit hash
		//                userRemoteConfigs: [[
		//                    url: 'https://github.com/ethbra-revature/team-planetarium.git',  // Replace with your repo URL
		//                ]]
		//            ])
		//        }
		//    }
		//}
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
				sh 'ls'
				sh '''
                    pwd

                    mvn test -f Planetarium/pom.xml
                '''
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
                    java -jar Planetarium/Planetarium-1.0.jar > output.txt 2>&1  &

                    sleep 2

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
