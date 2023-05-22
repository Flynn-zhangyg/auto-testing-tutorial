node {
  stage("Clone the project") {
    git branch: 'master', url: 'https://github.com/Flynn-zhangyg/auto-testing-tutorial.git'
  }

  stage("Compilation") {
    sh "./mvn clean install -DskipTests"
  }

  stage("Tests and Deployment") {
    stage("Deployment") {
      sh 'nohup ./mvn spring-boot:run -Dserver.port=8001 &'
    }
  }
}