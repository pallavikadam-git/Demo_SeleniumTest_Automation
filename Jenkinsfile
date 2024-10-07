pipeline {
    agent any

    environment {
        MAVEN_HOME = tool name: 'MAVEN_HOME', type: 'maven'
        PROJECT_DIR = 'C:\\Users\\IncubXperts\\IdeaProjects\\Demo_Test_Automation'
    }

    stages {
        stage('Clean and Compile') {
            steps {
                dir(PROJECT_DIR) {
                    bat "${MAVEN_HOME}/bin/mvn clean compile"
                }
            }
        }
        stage('Run Tests') {
            steps {
                script {
                    dir(PROJECT_DIR) {
                        catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                            bat "${MAVEN_HOME}/bin/mvn test"
                        }
                    }
                }
            }
        }
        stage('Generate Reports Main Run') {
            steps {
                dir(PROJECT_DIR) {
                    script {
                        // Archive and publish the main run report
                        catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                            archiveArtifacts artifacts: 'TestReport/ExtentReport.html', allowEmptyArchive: true
                            publishHTML([
                                allowMissing: false,
                                alwaysLinkToLastBuild: true,
                                keepAll: true,
                                reportDir: 'TestReport',
                                reportFiles: 'ExtentReport.html',
                                reportName: 'Main Run Extent Report',  // Give a distinct name to the main run report
                                includeImages: true
                            ])
                        }
                    }
                }
            }
        }
        stage('Rerun Failed Tests') {
            steps {
                script {
                    dir(PROJECT_DIR) {
                        if (fileExists('target/surefire-reports/testng-failed.xml')) {
                            echo 'Re-running failed tests...'
                            // Copy the failed test suite to a new file to avoid overwriting original results
                            bat 'copy target\\surefire-reports\\testng-failed.xml target\\surefire-reports\\testng-rerun.xml'
                            catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                                bat "${MAVEN_HOME}/bin/mvn -Dsurefire.suiteXmlFiles=target/surefire-reports/testng-rerun.xml verify"
                            }
                        } else {
                            echo 'No failed tests to rerun.'
                        }
                    }
                }
            }
        }
        stage('Generate Report For Rerun') {
            steps {
                dir(PROJECT_DIR) {
                    script {
                        // Archive and publish the rerun report
                        catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                            // Copy the main run report to a new directory for the rerun report
                            bat 'if not exist TestReportDir mkdir TestReportDir'
                            bat 'copy TestReport\\ExtentReport.html TestReportDir\\ExtentReport.html'

                            archiveArtifacts artifacts: 'TestReportDir/ExtentReport.html', allowEmptyArchive: true
                            publishHTML([
                                allowMissing: false,
                                alwaysLinkToLastBuild: true,
                                keepAll: true,
                                reportDir: 'TestReportDir',
                                reportFiles: 'ExtentReport.html',
                                reportName: 'Rerun Extent Report',  // Ensure a different name for the rerun report
                                includeImages: true
                            ])
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Checking if reports are generated...'
            script {
                archiveArtifacts artifacts: 'TestReport/ExtentReport.html', allowEmptyArchive: true
                archiveArtifacts artifacts: 'TestReportDir/ExtentReport.html', allowEmptyArchive: true
                junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            }
            cleanWs()
        }
        failure {
            echo 'Build failed!'
        }
        success {
            echo 'Build was successful!'
        }
    }
}