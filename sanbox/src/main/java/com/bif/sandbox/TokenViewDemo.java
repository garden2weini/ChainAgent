package com.bif.sandbox;

/**
 * 获取币价：https://documenter.getpostman.com/view/5728777/RzZ6HfX2?version=latest#5766b28a-b824-4af4-8aae-1ed0decf7896
 *
 * http://www.tokenview.com:8088/coin/marketInfo/eth
 * http://www.tokenview.com:8088/coin/marketInfo/btc
 * http://www.tokenview.com:8088/coin/marketInfo/eos
 * http://www.tokenview.com:8088/coin/marketInfo/ont
 * http://www.tokenview.com:8088/coin/marketInfo/xrp
 * "data": {
 *     "_id": "bitcoin", // 币的全称小写字母
 *     "changeUsd1h": "1.05", // 1小时涨跌幅
 *     "changeUsd24h": "-11.09085", // 24小时涨跌幅
 *     "changeUsd7d": "9.34", // 7天涨跌幅
 *     "priceUsd": "6060.27", // 美元价格
 *     "priceBtc": "6060.27", // 相对于多少个btc
 *     "volumeUsd": "48385853374", // 交易额（美元）
 *     "name": "Bitcoin", // 币的全称
 *     "erc20Addr": "", // 如果是ETH的代币，这里会是代币地址
 *     "marketCapUsd": "110781414314", // 市值（美元）
 *     "symbol": "BTC",  // 币价单位符号
 *     "rank": "1", // 市值排名
 *     "uniqueId": "btc", // 简称小写
 *     "circulatingSupply": "18279908.54", // 这个币的流通量
 *     "totalUsd": "1.865007689300477E11", // 已弃用
 *     "partialObject": false // 已弃用
 * }
 */
public class TokenViewDemo {

}
