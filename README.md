# 区块链Agent

## ETH Agent
以太坊Agent

## BTC Agent
。。。

## REF
### BTC
#### 获取链交易状态数据
~~~
curl --user bitcoin:bitcoin --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getchaintxstats", "params": [] }' -H 'content-type: application/json;' http://127.0.0.1:18332
~~~
~~~
{
"result":{
"time":1600565965,
"txcount":57790681,
"window_final_block_hash":"00000000f8af24b49b53101c5338d6375511ba92239f6763a183468f70890c35",
"window_final_block_height":1835175,
"window_block_count":4320,
"window_tx_count":173429,
"window_interval":1583469,
"txrate":0.1095247207239295
},
"error":null,
"id":"curltest"
}
~~~
#### 获取内存使用
~~~
curl --user bitcoin:bitcoin --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getmemoryinfo", "params": [] }' -H 'content-type: text/plain;' http://127.0.0.1:18332
~~~
~~~
{"result":{"locked":{"used":64272,"free":1264,"total":65536,"locked":65536,"chunks_used":2002,"chunks_free":2}},"error":null,"id":"curltest"}
~~~
#### 获取当前节点信息
~~~
curl --user bitcoin:bitcoin --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getnodeaddresses", "params": [] }' -H 'content-type: application/json;' http://127.0.0.1:18332
~~~
~~~
{"result":[{"time":1599774437,"services":1033,"address":"190.188.225.38","port":18333}],"error":null,"id":"curltest"}
~~~
  
