pipeline {
  agent any
  
  stages {
    stage('TruffleHog Scan1') {
            steps {
                // Run TruffleHog scan
                sh 'trufflehog --regex --entropy=True .'
              script {
                    // Run TruffleHog scan and capture the output
                    def trufflehogOutput = sh(script: 'trufflehog --regex --entropy=True .', returnStdout: true).trim()
                    
                    // Print the TruffleHog output with ANSI color codes
                    echo "\u001B[33m${trufflehogOutput}\u001B[0m"
                }
            }  
    }
      stage('GitGuardian Scan') {
            //agent {
                //docker { image 'gitguardian/ggshield' }
            //}
            environment {
                GITGUARDIAN_API_KEY = credentials('guardian-token')
            }
            steps {
                sh 'ggshield secret scan ci --show-secrets -o'
              echo 'command executed'
                 script {
                    def scanOutput = sh(script: 'ggshield secret scan ci', returnStdout: true).trim()
                    echo "GitGuardian Scan Output: ${scanOutput}"
                }
              //sh 'ggshield secret scan --show-secrets --scan-paths . ci'
             // sh 'ggshield secret scan --scan-path . --show-secrets --show-paths'
                
            }
        }
    
    stage('Grype Scane') {
        steps {
          sh 'grype dir:. --scope AllLayers'
        }
    }
  }
}
