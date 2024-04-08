pipeline {
  agent any
  stages {
    stage('TruffleHog Scan') {
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
                //sh 'ggshield secret scan ci'
                //sh 'ggshield secret scan --show-secrets ci'
                //sh 'ggshield secret scan --scan-path .'
              sh 'ggshield secret scan --show-secrets --show-paths ci'
              sh 'ggshield secret scan --scan-path . --show-secrets --show-paths'
                
            }
        }
    stage('Grype Scane') {
        steps {
          sh 'grype dir:. --scope AllLayers'
        }
    }
  }
}
