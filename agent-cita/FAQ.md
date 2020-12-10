1、metadata中没找到共识算法和加密算法的信息
2、metaDataResult.getValidators()是共识节点列表吗？
-->验证者地址集合（是共识节点的子集）
3. 我猜测 cita即使有多个子链，但block是统一且连续的。所以才要通过每个block的meta查看信息（包括链/子链信息）。或者，metadata会发生改变，最新区块的meta为当前状态，以往的是历史状态
4. 虽然有多个子链的概念，但创世块是一个；
5. Transaction.to如果为空 除了可能是创建合约，也可能是合约调用吧？如何判定是普通交易还是合约交易（https://docs.citahub.com/zh-CN/cita/architecture/architecture）
6. service.appAccounts().send().getAccounts()会得到所有账户列表（外部账户+合约账户）吗？
7. 

CITA协助纸贵完成测试链的准备和搭建，帮助纸贵从测试链获取基本数据；


