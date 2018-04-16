node ('ADFSlaveLarge'){
    def rtMaven
	def server
    def buildInfo
	def prjVersionNo
	def snapOrRels
	
	def uploadJar =params.Upload_Jar_To_Artifactory
	def uploadTestclass =params.Upload_TestClass_To_Artifactory
	def uploadReport =params.Upload_TestData_and_Report
	def updateJira =params.Update_JIRA
	
	def junitJobName = "ADF_Junit_CI_Build" 
	
	def _cfg = ''
	currentBuild.result = "SUCCESS"

    try {
		def jobName = "${env.JOB_NAME}".trim()
		
		stage('checkout source') {
			echo    '====stage 1:scm poll stage===='
			//git branch: 'development', credentialsId: 'd5d57beb-8a91-4ac4-b76b-b00b94456372', url: 'https://sourcecode.jnj.com/scm/itx-aci/pangea.git'    	
			checkout scm
			prjVersionNo = sh (returnStdout: true, script: 'awk -v RS="</*version>" \'NR==2{printf "%s",$0}\' ${WORKSPACE}/pangea-parent/pom.xml')
	        snapOrRels = getSnapOrRels(prjVersionNo)
		    _cfg = getJenkinsConfigurationId ()
			echo " _cfg ***** = ${_cfg}"
			getTargetJira()
		}
	
		stage('build source') {
			echo    '====stage 2:Build source===='
			
			rtMaven = Artifactory.newMavenBuild()
			rtMaven.tool="Maven 3.3.9"
			server = Artifactory.server 'artifactory'
            server.credentialsId = 'd5d57beb-8a91-4ac4-b76b-b00b94456372'
			rtMaven.deployer.deployArtifacts = false
			rtMaven.deployer releaseRepo: 'taan-maven-release', snapshotRepo: 'taan-maven-snapshot', server: server
			buildInfo = Artifactory.newBuildInfo()
			
			
			withMaven(
				globalMavenSettingsConfig: _cfg,
				maven:'Maven 3.3.9'
			 ) {
				// Run the maven build
				// rtMaven.run pom:'pangea-parent/pom.xml',goals:'clean install -Dmaven.test.skip=true'
        
				sh 'rm -rf ${WORKSPACE}/pangea-parent/target'
			    
				sh 'mvn clean install -Dmaven.test.skip=true -f pangea-parent/pom.xml'
        
			}
			
			
		    sshagent (credentials: ['sa-its-adaas']) {
              sh '''
              ssh -o StrictHostKeyChecking=no -l sa-its-adaas awsacinva1110.jnj.com "
                 cd /app/cur_install/bin && 
                 ./stopserver.sh &&
                 ./delete.sh all" 
			   sleep 10
				 
				 '''
				 
			 
            }
			
		}
		
		
        stage('deploy cluster'){
            sshagent (credentials: ['sa-its-adaas']) {

              sh "ssh -o StrictHostKeyChecking=no -l sa-its-adaas awsacinva1110.jnj.com 'cd /app/cur_install/bin && ./deploy.sh && cp /home/sa-its-eap-jenkins/.m2/repository/com/jnj/adf/adf-compute/0.3.04-0.4/adf-compute-0.3.04-0.4.jar /app/adf/compute/lib0 && cp /home/sa-its-eap-jenkins/.m2/repository/com/jnj/adf/adf-compute-cluster/0.3.04-0.4/adf-compute-cluster-0.3.04-0.4.jar /app/adf/compute/lib0 && cp /home/sa-its-eap-jenkins/.m2/repository/com/jnj/adf/adf-compute-cluster-protocol/0.3.04-0.4/adf-compute-cluster-protocol-0.3.04-0.4.jar /app/adf/compute/lib0 && cp ${WORKSPACE}/pangea-parent/pangea-test/src/test/resources/grid-server.xml /app/adf/curation/conf && ./startserver.sh curation && ./startserver.sh cachegrid && ./hotdeploy.sh all /home/sa-its-eap-jenkins/.m2/repository/com/jnj/adf/adf-compute-cluster/0.3.04-0.4/adf-compute-cluster-0.3.04-0.4.jar &&./hotdeploy.sh all /home/sa-its-eap-jenkins/.m2/repository/com/jnj/adf/adf-compute/0.3.04-0.4/adf-compute-0.3.04-0.4-wan-shuffle-function.jar && ./hotdeploy.sh all /app/cur_install/source/extension/adf-topic-queue-0.3-v1-1121.jar && ./hotdeploy.sh all /home/sa-its-eap-jenkins/.m2/repository/com/jnj/adf/DI-plugin/0.3.04-SNAPSHOT/DI-plugin-0.3.04-SNAPSHOT.jar &&./startserver.sh computer' "

			  }
        }		
		
		
    	stage('test and report') {
            echo '====stage est and report===='
			withMaven(
				globalMavenSettingsConfig: _cfg,
				maven:'Maven 3.3.9'
			 ) {
				//rtMaven.run pom:'pangea-parent/pangea-test/pom.xml',goals:'-Dtest=BaseFlowTest -DgridName=grid -DfailIfNoTests=false -Dgs.home=/apps/tmp/gemlite -Dnaming.server=awsacinva1110[15006] -Dgs.bind.ip=localhost -Dnaming.server._COMPUTING=awsacinva1110[15007] -Dsecurity-service-account-username=SA-ITS-UNITYRestUser -Dsecurity-service-account-password=khtYa7I3wRfkVEzyuP+mkg== -Dmaven.test.skip=false -Dtesting.compute.seedNode=awsacinva1110:2551 -Dtesting.compute.remote=true clean verify'
				//sh 'mvn -DgridName=atom -DfailIfNoTests=false -Dgs.home=/apps/tmp/gemlite -Dnaming.server=awsacinva1110[15006] -Dgs.bind.ip=localhost -Dnaming.server._COMPUTING=awsacinva1110[15007] -Dsecurity-service-account-username=SA-ITS-UNITYRestUser -Dsecurity-service-account-password=khtYa7I3wRfkVEzyuP+mkg== -Dmaven.test.skip=false -Dtesting.compute.seedNode=awsacinva1110:2551 -Dtesting.compute.remote=true clean verify -f pangea-parent/pom.xml'
				//sh 'mvn -DgridName=grid -DfailIfNoTests=false -Dgs.home=/apps/tmp/gemlite -Dnaming.server=awsacinva1110[15006] -Dgs.bind.ip=localhost -Dnaming.server._COMPUTING=awsacinva1110[15007] -Dlogin.name=SA-ITS-ADFXD_TST -Dlogin.password=encrypted(3F91D85500E1FA91571CD8BAF47B592F) -Dmaven.test.skip=false -DcomputingNode=awsacinva1110:2551 -Dtesting.compute.remote=true clean verify -f pangea-parent/pom.xml'
				sh 'mvn -DgridName=grid -DfailIfNoTests=false -Dgs.home=/apps/tmp/gemlite -Dnaming.server=awsacinva1110[15006] -Dgs.bind.ip=localhost -Dnaming.server._COMPUTING=awsacinva1110[15007] -Dlogin.name=pulse -Dlogin.password=QL0AFWMIX8NRZTKeof9cXsvbvu8= -Dmaven.test.skip=false -DcomputingNode=awsacinva1110:2551 -Dtesting.compute.remote=true clean verify -f pangea-parent/pom.xml'

				junit 'pangea-parent/pangea-test/target/surefire-reports/*.xml'
				cucumber fileIncludePattern: '**/*.json', jsonReportDirectory: 'pangea-parent/pangea-test/target/Destination', sortingMethod: 'ALPHABETICAL'

				if(updateJira){
					updateJiraComment(uploadReport,"${JOB_NAME}",snapOrRels,prjVersionNo)
				}
			}
    				
    	}		

  
        if(uploadJar){		
		    stage('deploy jar') {
			echo    '====stage 6:install jar===='
			  rtMaven.run pom:'pangea-parent/pom.xml',goals:'install -Dmaven.test.skip=true',buildInfo: buildInfo
			  rtMaven.deployer.deployArtifacts buildInfo
			} 
		}
		
	
		
		if(uploadReport){
		
		    stage('upload data and report') {
			    echo '====stage 8:upload Test data to Artifactory===='
				//	sh "zip -r -o ${WORKSPACE}/pangea-parent/pangea-test/src/test/resources/features/testData.zip ${WORKSPACE}/pangea-parent/src/test/resources/features/ -i '*.xlsx'  -x '${WORKSPACE}/pangea-parent/adf-test-framework/src/test/resources/features/view3/viewDefineTest.xlsx' -x '${WORKSPACE}/pangea-parent/adf-test-framework/src/test/resources/features/sample/readExcelSample.xlsx' "
				//		
				//	def uploadExcelData = """{ 
				//	"files": [
				//	  {
				//	    "pattern": "pangea-parent/pangea-test/src/test/resources/features/testData.zip",
				//        "props": "groupId=com.jnj.pangea.pangea-test;artifactId=TestData",
				//        "target": "taan-maven-${snapOrRels}/com/jnj/pangea/pangea-test/${prjVersionNo}/TestData/testData_${BUILD_NUMBER}.zip",
				//        "flat": "false"   
				//      }]
				//	}"""
				//    
				//	server.upload(uploadExcelData)
				
				node ('master'){
				  withEnv(["junitJobName=${junitJobName}","snapOrRels=${snapOrRels}","prjVersionNo=${prjVersionNo}"]) {
				  sh '''
					cd $JENKINS_HOME/jobs/${JOB_NAME}/lastSuccessful
					tar cvf ./cucumber.tar ./cucumber-html-reports/
				  '''    
				
				  def uploadTestResult = """{ 
				  "files": [
				  {
				    "pattern": "$JENKINS_HOME/jobs/${junitJobName}/lastSuccessful/cucumber.tar",
                    "props": "groupId=com.jnj.pangea.pangea-test/;artifactId=CucumberReportHTML",
                    "target": "taan-maven-${snapOrRels}/com/jnj/pangea/pangea-test//${prjVersionNo}/CucumberReportHTML/cucumberReport_${BUILD_NUMBER}.tar",
                    "flat": "false" 
                  }]
				 }"""
				 
				 server.upload(uploadTestResult)		
				}  
			  }
			}
		}
			 
    	 stage('destroy test env'){
    	    sshagent (credentials: ['sa-its-adaas']) {
              sh '''
              ssh -o StrictHostKeyChecking=no -l sa-its-adaas awsacinva1110.jnj.com "
                 cd /app/cur_install/bin && 
                 ./stopserver.sh &&
                 ./delete.sh all" '''
            }
        }			 
		
		  
	} catch(ex){
		echo "enter into Exception" 
        currentBuild.result = 'FAILURE'
		sshagent (credentials: ['sa-its-adaas']) {
		  sh '''
		  ssh -o StrictHostKeyChecking=no -l sa-its-adaas awsacinva1110.jnj.com "
			 cd /app/cur_install/bin && 
			 ./stopserver.sh &&
			 ./delete.sh all" '''
		}
        throw ex
    }finally{
		echo "currentBuild.result = ${currentBuild.result}"
		step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: 'xli241@its.jnj.com', sendToIndividuals: true])
   }
}


def getSnapOrRels(String prjVersionNo){
    def versionType;
	if (prjVersionNo.toUpperCase().contains("SNAPSHOT")) { 
		versionType = "snapshot"
	}else {
		versionType = "release"
	}
	return versionType
}
 
def updateJiraComment(Boolean uploadReport,String jobName,String snapOrRels,String prjVersionNo){
	echo "enter into updateJiraComment "
    //def commit = sh (returnStdout: true, script: "git log -1 --pretty=format:%s --grep='[a-zA-Z]-[0-9]' ").trim()
	 def commit = sh (returnStdout: true, script: "git log --pretty=format:%s --grep='[a-zA-Z]-[0-9]' --after='3 days ago' ").trim()
    echo "commit=   '${commit}' "
	def jenkinsUrl = "${env.JENKINS_URL}".trim()
	def buildNumber ="${env.BUILD_NUMBER}".trim()
	def commitArray = commit.split(" |,")
	def jiraMap = [:]
	for (int i = 0; i < commitArray.size(); ++i) { 
		def commitMsg = "${commitArray[i]}".trim()
		echo "updateJiraComment commitMsg=   '${commitMsg}' "
		def matcher = commitMsg ==~ /[A-Za-z]{1,}-\d{4}/
		if(matcher){
          jiraMap.put("${commitArray[i]}","${commitArray[i]}")
		  

		}
	}
	
	for (String key : jiraMap.keySet()) {
      echo "key=   '${key}' "
			if (uploadReport) {
				//jiraAddComment comment: "Cucumber Report URL: ${jenkinsUrl}job/${jobName}/${buildNumber}/cucumber-html-reports/overview-features.html  \r\n Artifactory Cucumber Report URL: https://artifactrepo.jnj.com/artifactory/list/taan-maven-${snapOrRels}/com/jnj/pangea/pangea-test/${prjVersionNo}/CucumberReportHTML/   \r\n Artifactory Test Data URL: https://artifactrepo.jnj.com/artifactory/list/taan-maven-${snapOrRels}/com/jnj/adf/adf-test-framework/${prjVersionNo}/TestData/ ", idOrKey: "${key}", site: 'JiraForJnj'
				jiraAddComment comment: "Cucumber Report URL: ${jenkinsUrl}job/${jobName}/${buildNumber}/cucumber-html-reports/overview-features.html  \r\n Artifactory Cucumber Report URL: https://artifactrepo.jnj.com/artifactory/list/taan-maven-${snapOrRels}/com/jnj/pangea/pangea-test/${prjVersionNo}/CucumberReportHTML/   \r\n Artifactory Test Data URL: https://artifactrepo.jnj.com/artifactory/list/taan-maven-${snapOrRels}/com/jnj/pangea/pangea-test/${prjVersionNo}/TestData/ ", idOrKey: "${key}", site: 'JiraForJnj'
			
			} else {
				jiraAddComment comment: "Cucumber Report URL for Local: ${jenkinsUrl}job/${jobName}/${buildNumber}/cucumber-html-reports/overview-features.html ", idOrKey: "${key}", site: 'JiraForJnj'
			}
    }
	
	
}

def Map getTargetJira(){
    echo "enter into getTargetJira() "
	    //def commit = sh (returnStdout: true, script: "git log -1 --pretty=format:%s --grep='[a-zA-Z]-[0-9]' ").trim()
	 def commit = sh (returnStdout: true, script: "git log --pretty=format:%s --grep='[a-zA-Z]-[0-9]' --after='3 days ago' ").trim()
     echo "commit=   '${commit}' "


	def commitArray = commit.split(" |,")
	def jiraMap = [:]
	for (int i = 0; i < commitArray.size(); ++i) { 
		def commitMsg = "${commitArray[i]}".trim()
		echo "commitMsg=   '${commitMsg}' "
		def matcher = commitMsg ==~ /[A-Za-z]{1,}-\d{4}/
		if(matcher){
			jiraMap.put("${commitArray[i]}","${commitArray[i]}")
		  
		}
	}
	
	for (String key : jiraMap.keySet()) {
      echo "key=   '${key}' "
			
    }
	
	return jiraMap
}


def String getJenkinsConfigurationId (){

	ws = env.WORKSPACE
	echo "ws is ${ws}"
    def configurationId = ''
    if (ws.find('/sa-itsus-taan-ciuser/')) {		
        echo 'Matched taan-ci-build Jenkins'
        configurationId = '34ebbea8-f90f-4c87-83ca-9f0523f11bf6'
    } else if (ws.find('/sa-itsus-taan-cduser/')) {
        echo 'Matched taan-cd-deploy Jenkins'
        configurationId = '*****TODO'
    } else {
        echo 'ERROR: Could not match EAP or ADF.'
    }
    echo "Matched  Jenkins ${configurationId}  "
	
	return configurationId

}