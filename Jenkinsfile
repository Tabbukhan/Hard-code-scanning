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
                sh 'ggshield secret scan ci'
              echo 'command executed'
                 script {
                    def scanOutput = sh(script: 'ggshield secret scan --show-secrets --json . ci', returnStdout: true).trim()
                    println "GitGuardian Scan Output: ${scanOutput}"
                }
              //sh 'ggshield secret scan --show-secrets --scan-paths . ci'
             // sh 'ggshield secret scan --scan-path . --show-secrets --show-paths'
                
            }
        }
    stage('TruffleHog Scan') {
    steps {
        script {
            // Run TruffleHog scan and parse JSON output
            def jsonOutput = sh(returnStdout: true, script: 'trufflehog --regex --entropy=True --json .').trim()
            def jsonObj = readJSON text: jsonOutput

            // Print secret path
            jsonObj.hits.each { hit ->
                echo "Secret found at ${hit.path}"
            }
        }
    }
}
    stage('Grype Scane') {
        steps {
          sh 'grype dir:. --scope AllLayers'
        }
    }
  }
}
