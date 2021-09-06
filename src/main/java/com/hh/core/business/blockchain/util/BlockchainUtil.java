package com.hh.core.business.blockchain.util;

import com.alibaba.fastjson.JSONObject;
import com.alipay.mychain.sdk.api.MychainClient;
import com.alipay.mychain.sdk.api.env.ClientEnv;
import com.alipay.mychain.sdk.api.env.ISslOption;
import com.alipay.mychain.sdk.api.env.SignerOption;
import com.alipay.mychain.sdk.api.env.SslBytesOption;
import com.alipay.mychain.sdk.api.logging.AbstractLoggerFactory;
import com.alipay.mychain.sdk.api.logging.ILogger;
import com.alipay.mychain.sdk.api.utils.Utils;
import com.alipay.mychain.sdk.crypto.MyCrypto;
import com.alipay.mychain.sdk.crypto.hash.Hash;
import com.alipay.mychain.sdk.crypto.keyoperator.Pkcs8KeyOperator;
import com.alipay.mychain.sdk.crypto.keypair.Keypair;
import com.alipay.mychain.sdk.crypto.signer.SignerBase;
import com.alipay.mychain.sdk.domain.account.Identity;
import com.alipay.mychain.sdk.message.query.QueryTransactionResponse;
import com.alipay.mychain.sdk.message.transaction.account.DepositDataRequest;
import com.alipay.mychain.sdk.message.transaction.account.DepositDataResponse;
import com.alipay.mychain.sdk.utils.IOUtil;
import com.hh.core.business.blockchain.entity.SuchuanBill;
import com.hh.core.business.blockchain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class BlockchainUtil {

    /**
     * baas上创建的帐户名字
     */
    private static final String account = "firstAccount";
    private static Identity userIdentity;
    private static Keypair userKeypair;

    /**
     * sdk client
     */
    private static MychainClient sdk;
    /**
     * client key password
     */
    private static String keyPassword = "James4016.";
    /**
     * user password
     */
    private static String userPassword = "James4016.";
    /**
     * host ip
     */

    private static String host = "139.196.14.169";

    /**
     * server port
     */
    private static int port = 18130;
    /**
     * trustCa password.
     */
    private static String trustStorePassword = "mychain";
    /**
     * mychain environment
     */
    private static ClientEnv env;
    /**
     * mychain is tee Chain
     */
    private static boolean isTeeChain = false;
    /**
     * tee chain publicKeys
     */
    private static List<byte[]> publicKeys = new ArrayList<>();

    public static void initMychainEnv() throws IOException {
        // any user key for sign message
        String userPrivateKeyFile = "G:\\Workspace\\idea\\hh\\core-example\\src\\main\\resources\\firstAccount\\user.key";
        userIdentity = Utils.getIdentityByName(account);
        Pkcs8KeyOperator pkcs8KeyOperator = new Pkcs8KeyOperator();
        userKeypair = pkcs8KeyOperator.load(IOUtil.inputStreamToByte(BlockchainUtil.class.getClassLoader().getResourceAsStream(userPrivateKeyFile)), userPassword);

        // use publicKeys by tee
        if (isTeeChain) {
            Keypair keypair = new Pkcs8KeyOperator()
                    .loadPubkey(
                            IOUtil.inputStreamToByte(BlockchainUtil.class.getClassLoader().getResourceAsStream("test_seal_pubkey.pem")));
            byte[] publicKeyDer = keypair.getPubkeyEncoded(); //tee_rsa_public_key.pem 从BaaS下载获取
            publicKeys.add(publicKeyDer);
        }

        env = buildMychainEnv();

        ILogger logger = AbstractLoggerFactory.getInstance(BlockchainUtil.class);
        env.setLogger(logger);
    }

    private static ClientEnv buildMychainEnv() throws IOException {
        InetSocketAddress inetSocketAddress = InetSocketAddress.createUnresolved(host, port);
        String keyFilePath = "firstAccount/client.key";
        String certFilePath = "firstAccount/client.crt";
        String trustStoreFilePath = "firstAccount/trustCa";

        // build ssl option
        ISslOption sslOption = new SslBytesOption.Builder()
                .keyBytes(IOUtil.inputStreamToByte(BlockchainUtil.class.getClassLoader().getResourceAsStream(keyFilePath)))
                .certBytes(IOUtil.inputStreamToByte(BlockchainUtil.class.getClassLoader().getResourceAsStream(certFilePath)))
                .keyPassword(keyPassword)
                .trustStorePassword(trustStorePassword)
                .trustStoreBytes(
                        IOUtil.inputStreamToByte(BlockchainUtil.class.getClassLoader().getResourceAsStream(trustStoreFilePath)))
                .build();

        List<InetSocketAddress> socketAddressArrayList = new ArrayList<InetSocketAddress>();
        socketAddressArrayList.add(inetSocketAddress);

        List<SignerBase> signerBaseList = new ArrayList<SignerBase>();
        SignerBase signerBase = MyCrypto.getInstance().createSigner(userKeypair);
        signerBaseList.add(signerBase);
        SignerOption signerOption = new SignerOption();
        signerOption.setSigners(signerBaseList);

        return ClientEnv.build(socketAddressArrayList, sslOption, signerOption);
    }
    public static void initSdk() {
        sdk = new MychainClient();
        boolean initResult = sdk.init(env);
        if (!initResult) {
            exit("initSdk", "sdk init failed.");
        }else{
            System.out.println("sdk init success");
        }
    }

    public static DepositDataResponse depositdata(User user){
        JSONObject json = (JSONObject) JSONObject.toJSON(user);
        String result = json.toJSONString();

        DepositDataRequest request = new DepositDataRequest(
                Utils.getIdentityByName(account),
                Utils.getIdentityByName(account),
                result.getBytes(),
                BigInteger.ZERO);
        DepositDataResponse response = sdk.getAccountService().depositData(request);
        System.out.println("交易hash为： "+response.getTxHash()+  "，交易结果："+response.getErrorCode());
        return response;
    }

    public static void readData() {
        Hash hash = new Hash("f1155ed60023d095ea4b496bef35c86e61b246ca6a157a2ccc0be890d9418768");
        QueryTransactionResponse result = sdk.getQueryService().queryTransaction(hash);
        byte[] data = result.getTransaction().getData();
        String billJson = new String(data);
        log.info("billJson:{}", billJson);
        SuchuanBill bill = JSONObject.parseObject(billJson, SuchuanBill.class);
    }

    private static void exit(String tag, String msg) {
        exit(String.format("%s error : %s ", tag, msg));
    }

    private static void exit(String msg) {
        System.out.println(msg);
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {

        //step 1:init mychain env.
        initMychainEnv();

        //step 2: init sdk client
        initSdk();

        //step 3: deposit data
        /*SuchuanBill bill = new SuchuanBill();
        bill.setBillCode("abcdefg");
        bill.setFillTime("2021-05-10");
        bill.setStorageUnit("象屿科技");
        bill.setStorageAddr("厦门航运中心");
        bill.setStorageMobile("18965082080");
        depositdata(bill);*/

        // 读取数据
        readData();

        //step 6 : sdk shut down
        sdk.shutDown();
    }
}
