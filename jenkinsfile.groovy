node{
    
properties([parameters([string(defaultValue: '127.0.0.1', description: 'please give the ip address to build a site', name: 'IP', trim: true)]), pipelineTriggers([pollSCM('* * * * *')])])

}

    stage("Install git"){
        sh "ssh  ec2-user@${IP}        sudo yum install git python-pip  -y"
    }
    stage("clone a repo"){
        git 'https://github.com/farrukh90/flask-examples.git'
    }
    stage("Copy files"){
        sh "scp  *  ec2-user@${IP}:/TMP/"
    }    
    stage("Install requirements"){
        sh "ssh     ec2-user@${IP}     sudo pip install -r /tmp/requirements.txt"
    }
    stage("Run app"){
        sh "ssh     ec2-user@${IP}     python /tmp/01-hello-world/hello-py"
    }