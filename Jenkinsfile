pipeline{
    agent any
    environment {
        IMAGE_NAME="registry-vpc.cn-shanghai.aliyuncs.com/epiboly/epiboly-base"
        APP_NAME="ms-app"
        HELM_NAME="${APP_NAME}-${ENV}"
        DING_DING_TOKEN="ed73760cff1f973b16f14401e9cc9b48cbd9a877034251ff5645a2d0e00db596"
    }
    stages {
        stage('Clone') {
            steps{
                checkout scm
               sh '''
               curl -X POST \
                'https://oapi.dingtalk.com/robot/send?access_token='''+DING_DING_TOKEN+'''' \
                -H 'Content-Type: application/json' \
                -d '

                {
                   "msgtype": "markdown",
                   "markdown": {
                       "title":"Jenkins工程构建开始",
                       "text": "##### 构建开始 \n > 服务： '''+HELM_NAME+''' \n\n > 分支： '''+GIT_BRANCH+'''"
                   }
                }

                '
               '''
            }
        }
        stage('Env') {
            steps{
               script {
                    if(env.TAG == ''){
                        env.TAG = "${APP_NAME}-${GIT_COMMIT}"
                    }
                    if(env.NODE == '' || env.NODE == null){
                        env.node = 'master'
                    }
               }
            }
        }
        stage('Build') {
            when {
                allOf{
                    expression { env.BUILD=='true'  }
                    expression { env.ENV != 'prod'  }
                }
            }
            steps{
                sh "mvn clean package -U -Dmaven.test.skip=true"
            }
        }
        stage('Build Image') {
            when {
                allOf{
                    expression { env.BUILD=='true'  }
                    expression { env.ENV != 'prod'  }
                }
            }
            steps{
                sh "docker build -f Dockerfile --force-rm=true --rm -t ${IMAGE_NAME}:${env.TAG} ."
                sh "docker push ${IMAGE_NAME}:${env.TAG}"
                sh "docker images | grep none |awk '{print \$3}' |xargs -i docker rmi {}"
            }
        }
       stage('Install Helm App') {
           when {
                expression { env.INSTALL=='true' }
           }
           steps{
               sh "helm upgrade --install  --namespace ${env.ENV} --create-namespace --set image.tag=${env.TAG} ${HELM_NAME} kubernetes/chart -f kubernetes/env/${ENV}.yaml"
           }
           post {
             failure {
                  sh '''
                  curl -X POST \
                   'https://oapi.dingtalk.com/robot/send?access_token='''+DING_DING_TOKEN+'''' \
                   -H 'Content-Type: application/json' \
                   -d '

                   {
                      "msgtype": "markdown",
                      "markdown": {
                          "title":"Jenkins工程构建失败",
                          "text": "##### 构建<font color=Red>失败</font> \n > 服务： '''+HELM_NAME+''' \n\n > 分支： '''+GIT_BRANCH+'''"
                      }
                   }

                   '
                  '''
             }
             success {
                  sh '''
                  curl -X POST \
                   'https://oapi.dingtalk.com/robot/send?access_token='''+DING_DING_TOKEN+'''' \
                   -H 'Content-Type: application/json' \
                   -d '

                   {
                      "msgtype": "markdown",
                      "markdown": {
                          "title":"Jenkins工程构建成功",
                          "text": "##### 构建<font color=Green>成功</font> \n > 服务： '''+HELM_NAME+''' \n\n > 分支： '''+GIT_BRANCH+'''"
                      }
                   }

                   '
                  '''
             }
           }
       }
       stage('Uninstall Helm App') {
           when {
             expression { env.INSTALL=='false' }
           }
           steps{
                sh "helm uninstall ${HELM_NAME} --namespace ${env.ENV}"
           }
       }
    }
}
