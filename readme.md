##AWS Batch with Secrets Manager example project


the AWS Job Definition for the Batch Job needs a environment variable dbSecret
this includes the path to the secret
        Environment:
          - Name: dbSecret    
            Value: '{{resolve:secretsmanager:arn:aws:secretsmanager:eu-central-1:.....}}'      


implement the java code to use the user/password via properties for example, see spring boot example that already does this:
https://github.com/wolfgangunger/ungerw-spring-boot-db-sm-docker


to build the docker image (change ECR path if neccessary):
docker build -t 971801......amazonaws.com/ecs-repository:java_batch1 .

to run and test the container locally:
docker run 971801.....amazonaws.com/ecs-repository:java_batch1

docker login (aws configure must be executed first)
$(aws ecr get-login --no-include-email --region eu-central-1)

to push the container :
docker push 97180.....amazonaws.com/ecs-repository:java_batch1