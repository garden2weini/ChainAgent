# 区块链Agent-联盟链

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
ref: 
- https://github.com/xuperchain/xuperchain
- https://xuperchain.readthedocs.io/zh/latest/development_manuals/XuperRPC.html

Xuperchain网络：
- 测试网络接入地址：14.215.179.74:37101（XuperChain-testnet）
- 开放网络接口地址：39.156.69.83:37100
- 开放网络：https://xuper.baidu.com/n/console#/xuperos/info
~~~
docker build . -t xuperchain
# run xchain daemon
docker run -d -p 37101:37101 -p 47101:47101 --rm --name xchain xuperchain
# enter running container
docker exec -ti xchain bash
# run command
./xchain-cli status
~~~

## Fabric Agent
- Low Level API: https://github.com/hyperledger/fabric-sdk-java
- High-level API: https://github.com/hyperledger/fabric-gateway-java


## CITA Agent
CITA测试链，限制是每秒100，每IP每天10MB
~~~
curl -X POST --data '{"jsonrpc":"2.0","method":"peerCount","params":[],"id":74}' https://testnet.citahub.com | jq
curl -X POST --data '{"jsonrpc":"2.0","method":"peersInfo","params":[],"id":83}' https://testnet.citahub.com | jq
curl -X POST --data '{"jsonrpc":"2.0","method":"blockNumber","params":[],"id":83}' https://testnet.citahub.com | jq
curl -X POST --data '{"jsonrpc":"2.0","method":"getVersion","params":[],"id":83}' https://testnet.citahub.com | jq
curl -X POST --data '{"jsonrpc":"2.0","method":"getBlockByNumber","params":["0xF9", true],"id":1}' https://testnet.citahub.com | jq
~~~
CITA区块链分为两大类节点：
- 共识节点：共识节点具有出块和投票权限，交易由共识节点排序并打包成块，共识完成后即被确认为合法区块。
- 普通节点：普通节点没有出块和投票权限，其他方面和共识节点相同。可以同步和验证链上所有的原始数据，接受交易数据并向其他节点广播。

账户(account)： 链上唯一的标识，表现为地址。权限管理的主体对象。
- 外部账户(external owned account)： 拥有公私钥对，可发送交易的用户。
- 合约账户(contract account)： 拥有相关的代码(code)及存储(storage)。
