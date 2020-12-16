1、metadata中没找到共识算法和加密算法的信息
2、metaDataResult.getValidators()是共识节点列表吗？验证者地址集合（是共识节点的子集）
3. 我猜测 cita即使有多个子链，但block是统一且连续的。所以才要通过每个block的meta查看信息（包括链/子链信息）。或者，metadata会发生改变，最新区块的meta为当前状态，以往的是历史状态
4. 虽然有多个子链的概念，但创世块是一个；
5. Transaction.to如果为空 除了可能是创建合约，也可能是合约调用吧？如何判定是普通交易还是合约交易（https://docs.citahub.com/zh-CN/cita/architecture/architecture）
6. service.appAccounts().send().getAccounts()会得到所有账户列表（外部账户+合约账户）吗？
7. 

1、metadata中没找到共识算法和加密算法的信息
A：rpc getMetaData 中不会显示链所使用的加密算法；
获取链的加密算法可使用：rpc getBlockByNumber --height 0
（sha256）"transactionsRoot": "0x56e81f171bcc55a6ff8345e692c0f86e5b48e01b996cadc001622fb5e363b421"
（sm2） "transactionsRoot": "0x995b949869f80fa1465a9d8b6fa759ec65c3020d59c2624662bdff059bdf19b3"
CITA使用的共识算法是：拜占庭容错的 CITA-BFT 共识算法，只有一个，没有配置。

2、metaDataResult.getValidators()是共识节点列表吗？验证者地址集合（是共识节点的子集）
A：共识节点列表获取建议使用 scm NodeManager listNode，对应java-sdk 方法为：com.citahub.cita.protocol.system.CITAjSystemContract.listNode; metaDataResult.getValidators()也是共识节点列表，是出块者proposer集合。

3. 我猜测 cita即使有多个子链，但block是统一且连续的。所以才要通过每个block的meta查看信息（包括链/子链信息）。或者，metadata会发生改变，最新区块的meta为当前状态，以往的是历史状态
A：没有子链。metadata会发生改变，最新区块的meta为当前状态，以往的是历史状态。
rpc getMetaData 可以指定块高，* "latest" - 代表 pending 块的上一个块，表示当前块的交易列表以及交易执行结果都经过了共识  * "pending" - 代表最新出的块，表示当前块的交易列表经过了共识，而交易执行结果尚未经过共识。

4. 虽然有多个子链的概念，但创世块是一个；
A：没有子链。或请澄清怎么定义子链？（例如 EOS 设计中的原生侧链可以称之为子链，这种子链使用 EOS 原生代币，由主网同一组 BP 共同维护，和主网通信是无缝的。）

5. Transaction.to如果为空 除了可能是创建合约，也可能是合约调用吧？如何判定是普通交易还是合约交易（https://docs.citahub.com/zh-CN/cita/architecture/architecture）
A：Transaction.to 为空表示部署合约；
Transaction.to 不为空时，value 为空表示调用合约，调用合约时，to 为系统保留地址中的一个
https://docs.citahub.com/zh-CN/cita/addresses（具体是什么可以使用 cita-cli 执行前开启debug模式 "
switch --debug" debug 为true表示开启）
Transaction.to 不为空时，code为0x,value 不为空时表示普通转账交易，如：
rpc sendRawTransaction --code 0x --private-key 0x5f0258a4778057a8a7d97809bd209055b2fbafa654ce7d31ec7191066b9225e6 --quota 10000000 --address 0x47707d6a7f30056f0d1decc8a3104ff7b5262593 --value 80000

6. service.appAccounts().send().getAccounts()会得到所有账户列表（外部账户+合约账户）吗？
A：scm QuotaManager getAccounts（https://docs.citahub.com/zh-CN/cita/sys-contract-interface/interface#getaccounts）
会查询到admin账户地址和scm QuotaManager setAQL 限制的地址（https://docs.citahub.com/zh-CN/next/cita/sys-contract-interface/interface#setaql）



CITA协助纸贵完成测试链的准备和搭建，帮助纸贵从测试链获取基本数据；


