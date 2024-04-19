pipeline {
  agent any
  
  stages {
    stage('TruffleHog Scan1') {
            steps {
                // Run TruffleHog scan
                sh 'trufflehog --regex --entropy=True .'
             
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
                sh 'ggshield secret scan --show-secrets ci'
                sh 'ggshield secret scan ci -b trufflehog'

              echo 'command executed'
                 script {
                    def scanOutput = sh(script: 'ggshield secret scan ci', returnStdout: true).trim()
                    echo "GitGuardian Scan Output: ${scanOutput}"
                }
              
                
            }
        }

    // Vulnerability check
    stage('Grype Scane') {
        steps {
          sh 'grype dir:. --scope AllLayers'
        }
    }

    
      
     stage('Semgrep-Scan') {
          environment {
      // The following variable is required for a Semgrep Cloud Platform-connected scan:
      SEMGREP_APP_TOKEN = credentials('SEMGREP_APP_TOKEN')
          }
        steps {
          //sh 'pip3 install semgrep'
         // sh 'semgrep ci --verbose'
          sh 'semgrep ci --no-git-ignore'
      }
    }
  
  }

  post {
        success {
            emailext subject: 'Build Successful',
                      body: 'The Jenkins build was successful.',
                      to: 'tabasum.khan@credmudra.com'
        }
        failure {
            emailext subject: 'Build Failed',
                      body: 'The Jenkins build failed.',
                      to: 'tabasum.khan@credmudra.com'
        }
    }
}
