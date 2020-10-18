package com.bif.bitcoin.client;

import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.GenericRpcException;


import java.io.Serializable;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class BitcoinClientX extends BitcoinJSONRPCClient {
    public BitcoinClientX(String rpcUrl) throws MalformedURLException {
        super(new URL(rpcUrl));
    }

    public ArrayList getNodeAddresses() {
        return (ArrayList) query("getnodeaddresses");
    }

    public LinkedHashMap getMemoryInfo() {
        return (LinkedHashMap) query("getmemoryinfo");
    }

    public LinkedHashMap getChainTxStats() {
        return (LinkedHashMap) query("getchaintxstats");
    }

    public RawTransaction getRawTransactionX(String txId, String blockHash) throws GenericRpcException {
        RawTransaction rawTransaction =  new RawTransactionImpl2((Map<String, Object>)  query("getrawtransaction", txId, 1, blockHash));
        //System.out.println("Tx Info:" + rawTransaction);
        return rawTransaction;

    }

    private class RawTransactionImpl2 extends MapWrapper implements RawTransaction, Serializable {

        private RawTransactionImpl2(Map<String, ?> tx) {
            super(tx);
        }

        @Override
        public String error() {
            return mapStr("error");
        }

        @Override
        public String hex() {
            return mapStr("hex");
        }

        @Override
        public String txId() {
            return mapStr("txid");
        }

        @Override
        public int version() {
            return mapInt("version");
        }

        @Override
        public long lockTime() {
            return mapLong("locktime");
        }

        @Override
        public String hash() {
            return mapStr("hash");
        }

        @Override
        public long size() {
            return mapLong("size");
        }

        @Override
        public long vsize() {
            return mapLong("vsize");
        }

        private class InImpl extends MapWrapper implements In, Serializable {

            private InImpl(Map<String, ?> m) {
                super(m);
            }

            @Override
            public String txid() {
                return mapStr("txid");
            }

            @Override
            public Integer vout() {
                return mapInt("vout");
            }

            @Override
            public BigDecimal amount() {
                return mapBigDecimal("amount");
            }

            @Override
            @SuppressWarnings("unchecked")
            public Map<String, Object> scriptSig() {
                return (Map<String, Object>) m.get("scriptSig");
            }

            @Override
            public long sequence() {
                return mapLong("sequence");
            }

            @Override
            public RawTransaction getTransaction() {
                try {
                    return getRawTransaction(mapStr("txid"));
                } catch (GenericRpcException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public Out getTransactionOutput() {
                return getTransaction().vOut().get(mapInt("vout"));
            }

            @Override
            public String scriptPubKey() {
                return mapStr("scriptPubKey");
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public List<In> vIn() {
            final List<Map<String, ?>> vIn = (List<Map<String, ?>>) m.get("vin");
            return new AbstractList<In>() {

                @Override
                public In get(int index) {
                    return new RawTransactionImpl2.InImpl(vIn.get(index));
                }

                @Override
                public int size() {
                    return vIn.size();
                }
            };
        }

        private class OutImpl extends MapWrapper implements Out, Serializable {

            private OutImpl(Map<String, ?> m) {
                super(m);
            }

            @Override
            public BigDecimal value() {
                return mapBigDecimal("value");
            }

            @Override
            public int n() {
                return mapInt("n");
            }

            private class ScriptPubKeyImpl extends MapWrapper implements ScriptPubKey, Serializable {

                public ScriptPubKeyImpl(Map<String, ?> m) {
                    super(m);
                }

                @Override
                public String asm() {
                    return mapStr("asm");
                }

                @Override
                public String hex() {
                    return mapStr("hex");
                }

                @Override
                public int reqSigs() {
                    return mapInt("reqSigs");
                }

                @Override
                public String type() {
                    return mapStr("type");
                }

                @Override
                @SuppressWarnings("unchecked")
                public List<String> addresses() {
                    return (List<String>) m.get("addresses");
                }

            }

            @Override
            @SuppressWarnings("unchecked")
            public ScriptPubKey scriptPubKey() {
                return new RawTransactionImpl2.OutImpl.ScriptPubKeyImpl((Map<String, ?>) m.get("scriptPubKey"));
            }

            @Override
            public TxInput toInput() {
                return new BasicTxInput(transaction().txId(), n());
            }

            @Override
            public RawTransaction transaction() {
                return RawTransactionImpl2.this;
            }

        }

        @Override
        @SuppressWarnings("unchecked")
        public List<Out> vOut() {
            final List<Map<String, ?>> vOut = (List<Map<String, ?>>) m.get("vout");
            return new AbstractList<Out>() {

                @Override
                public Out get(int index) {
                    return new RawTransactionImpl2.OutImpl(vOut.get(index));
                }

                @Override
                public int size() {
                    return vOut.size();
                }
            };
        }

        @Override
        public String blockHash() {
            return mapStr("blockhash");
        }

        @Override
        public Integer confirmations() {
            Object o = m.get("confirmations");
            return o == null ? null : ((Number)o).intValue();
        }

        @Override
        public Date time() {
            return mapDate("time");
        }

        @Override
        public Date blocktime() {
            return mapDate("blocktime");
        }
    }



}
