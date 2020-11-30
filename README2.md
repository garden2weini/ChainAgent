# 区块链Agent-公链

## FISCO BCOS
注意：推荐使用OracleJDK！！！
https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/community.html
https://fisco-bcos-documentation.readthedocs.io/en/latest/docs/installation.html
https://webasedoc.readthedocs.io/zh_CN/latest/docs/WeBASE-Install/developer.html
https://github.com/FISCO-BCOS/spring-boot-starter
https://github.com/FISCO-BCOS/java-sdk-demo
https://github.com/FISCO-BCOS/evidenceSample

~~~
# https://github.com/bcosorg/bcos/blob/master/docker/README.md
docker pull bcosorg/bcos:alpine
# 连接到容器
$ docker exec -it $(docker ps -a | grep bcos-node-0 | awk 'NR==1{print $1}') sh
# 查看日志，blk不断增长说明单节点网络已正常工作
$ tail -f /nodedata/logs/info* | grep "Report"
~~~
### REF
- Docker运行BCOS及配置：https://github.com/bcosorg/bcos

## XuperChain
ref: https://github.com/xuperchain/xuperchain
~~~
docker build . -t xuperchain
# run xchain daemon
docker run -d -p 37101:37101 -p 47101:47101 --rm --name xchain xuperchain
# enter running container
docker exec -ti xchain bash
# run command
./xchain-cli status
~~~
