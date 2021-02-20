package com.spring.test2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjtlcb.fcloud.utils.SecurityUtil;

import java.util.HashMap;
import java.util.Map;

public class Test {

    //泰隆银行服务请求地址
    private static String TLHttpUrl = "http://10.4.161.102/api/qnyp/";
    //商户appID
    private static String appID = "6d9df84d-99f4-4cbe-8056-002b11a12447";
    //加密秘钥，注意存储，防止泄露
    private static String appSecretKey = "6ef6f26d-bfa4-3cc6-e053-010000756946";
    //自有私钥，安全保存，防止泄露
    private static String RSAPrivateKey = "MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCcGBFiLJkKGiKk504vmWeLQSjq6LNeJREwTAwIU8B/gD2KWI2oBK4foYQdF2AGfMvkCYgZtRTFgL8ytRG9ppxMVhLQ7r/wQdRDQHU0FXqs+ZLC9d4r02LgV77eLdfgkiIq9b3rRP/P26aFnp3H9IZydL9AskgHpAJYQ+5kulB/D2Na5ejY18U96KnlLyyvodPcU1v77ohPDaLKvS5RdROXLTMzbW7UjR8bURFuv9D8aIlS6bpf2lxBBjt8lvf8PaM/yrTuTbFgfBQUitFlGBirDCEQaz7lvorwERz5k1FhF9CBLi0tYaTFmFm2t3Wi3yggHBJXln++KL2wrNrls/e7AgMBAAECggEBAI/6Wz506Iy64q28dQ/OrgVyiu1yys/R3PLxkj+0EOEKRrlvSOprtriSYKv8vaP+RIsIh7M+pfQu9y0sezq1jYGT+zMgpYZVEhceQ31ROqCGa+rMoqtGBfPsu69wFamc4UtC9HUoZdUyQ30Ie3zOfZzhNu/UAkCZ9Ve5ARGEEW/ALfGI38/hBGQvPHF2TqfySLZfyqW+CJ3GbtNjieCbn8CbRCgWJk6NxtfIsZJSCIu4WOveKR7qbtZEjcWz0CBGZj0YZDeCfaQLVX5PAs7T8Q6hYi3NBxe/pPOEGCcu/88T2ZrUIydEVYpGYJBfJxNI5A39LQY4H3fh0yN1RyxjU5ECgYEA2HbvK4YiMfl3iMcd+ce6Mm7xruYCtPuiogMl7xtizOo/foraacsLUNdrwkZk/xHv/tTRSolfVOYiUIxD1kWnsPAIc167yklBn3Hz6fziB7e/r8/yxWr+iNBxzLELWXJ57VkRcfaPOZkAT4A9DzgzX9m+pvfRb+wUyFyL6qNKBU0CgYEAuJpvFv+MlrWgJILJd7ApN85TKbq0+TZNhGA3Gcp9b0Ddy9FUaL/NaxO/MHebw0f3HpyXNXNraVSxZ1NHfrHFzHzOm6VpG3LmxHKY7pTpY7cGi8y3kiG/A61ThvNWPxqEaEHCiVADab+p/gncQQ3zwMrT2TaRWLHg9Q+FfUqpTScCgYEArmDpfTt6Mp4QEIyvdjfSpKxBYC8FAT0xn6g/l+aM6hzO96buGomqXP6dXY0N8XlGyBgzXgNaW+9kt1S3lVbZpy1G8YHgcC4hgkvvPZEtmSAKeJxhq23tDNQ2yLnbNbyFwpGc/xyr9q9t052wEqGoqU+lSaab/rjFaT88q9HNYIUCgYEArAMjHo/y5X/KABtqNT+NuLVloo2aElm6M24gCcU3ylHsGf4tJAoNFAhrb3MUTguhxgFtmF27KYPEZp4gEXr+74MgVcH0xaH0S/roa++P5d1mmR+hseYGAhwgp6/YnqZL2IXmCmLnO0Nf2BpCBNBGYE/exRjmqHnfPxo+bbgDN6MCgYEA0sYG2fywM7zQMubMosLyizIrHOVTdOkK9DzwZ/GHOyX4DMjb3GALAS/c/WVxK+cebQLx5jZ4fdG8G5qwTcbxdpOMEGeyEZbXZeMk2nO0+2rHUqjo+WOGNQpBXAtxKV8tTmY9gQ+OkJ/klgMFA8CniyMBaAg1PSaC+PS8KAdm+sU=";
    //自有公钥，提供给银行，
    private static String RSAPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnBgRYiyZChoipOdOL5lni0Eo6uizXiURMEwMCFPAf4A9iliNqASuH6GEHRdgBnzL5AmIGbUUxYC/MrURvaacTFYS0O6/8EHUQ0B1NBV6rPmSwvXeK9Ni4Fe+3i3X4JIiKvW960T/z9umhZ6dx/SGcnS/QLJIB6QCWEPuZLpQfw9jWuXo2NfFPeip5S8sr6HT3FNb++6ITw2iyr0uUXUTly0zM21u1I0fG1ERbr/Q/GiJUum6X9pcQQY7fJb3/D2jP8q07k2xYHwUFIrRZRgYqwwhEGs+5b6K8BEc+ZNRYRfQgS4tLWGkxZhZtrd1ot8oIBwSV5Z/vii9sKza5bP3uwIDAQAB";
    //泰隆银行生成提供的公钥
    private static String TLPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnYwrs3uxDChe84C/v1rdBAOCesHX9oBt11fiHaM6Ik7h5SCBVCqF7JjuJdlcCR0YMkxd5QmvZ543EWAFHyVaul6XT2LpjWJo2StI4b9udHmftB6+jXlnxXhhY1xNvQl2E7SZ4Ewk+e5cxKV1U9+uFNWUyui10o2cTkUy94MatCBQV5wiSRhgVg1xaBffqXB1bTptzZG1mdJtEHEeaCwcW2JIEbxOqlGI55vdowXGCm5FjBf26qA8L2as3769XanMd2o35w57dgTe2XmnX3lGiKOlP7I7ErhwphaXLuIhzVIin/ICuLNlfYTA2VvFRcd8Ww5+W8LRP4qzGUzeupPV8QIDAQAB";

    //初始化安全类
    /*static {
        //可以放到工程的启动类中
        SecurityUtil.init(TLHttpUrl, appID, appSecretKey, RSAPrivateKey, TLPublicKey);
    }*/

    public static void main(String args[]) throws Exception {
        /*//生成RSA公私钥，企业商户保存私钥（防止泄露），公钥提供给泰隆银行
        Map<String, Object> RSAKeys = RSAUtil.initKey();
        System.out.println("私钥:\n" + RSAKeys.get("RSAPrivateKey"));
        System.out.println("公钥:\n" + RSAKeys.get("RSAPublicKey"));

        // 请求方法
        String appAccessToken = SecurityUtil.getTLToken();
        // token有效期为1小时,一小时内的业务可以用同一个token（已封装定时获取,SecurityUtil.init就会启动定时任务），如果业务接口报token失效可以调用SecurityUtil.getTLToken()更新SecurityUtil类的静态token
        if (appAccessToken == null) {
            System.out.println("获取token失败");
            return;
        }

        // 组装报文
        JSONObject reqMsg1 = new JSONObject();
        //业务数据字段
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> bodymap = new HashMap<String, Object>();
        headmap.put("bussSeqNo", "8acd96c269a04d2a89be00220a266d28");
        bodymap.put("bussType", "LOAN");
        map.put("head", headmap);
        map.put("body", bodymap);
        reqMsg1.put("reqData", JSON.toJSON(map));
        //请求方法
        JSONObject rspMsg1 = SecurityUtil.toTLBuss(reqMsg1, "getClientInfo");

        //业务接口2-getBankInfo
        //组装报文
        JSONObject reqMsg2 = new JSONObject();
        //业务数据字段
        Map<String, Object> map2 = new HashMap<String, Object>();
        Map<String, Object> headmap2 = new HashMap<String, Object>();
        Map<String, Object> bodymap2 = new HashMap<String, Object>();
        headmap2.put("bussSeqNo", "8acd96c269a04d2a89be00220a266d28");
        bodymap2.put("bankCardNo", "621480123456789014");
        map2.put("head", headmap2);
        map2.put("body", bodymap2);
        reqMsg2.put("reqData", JSON.toJSON(map2));
        //请求方法
        JSONObject rspMsg2 = SecurityUtil.toTLBuss(reqMsg2, "getBankInfo");*/


        /*String reqInput="{\"sign\":\"26E4FB3525949EF2D7EE8A6314C21478\",\"signMethod\":\"MD5\",\"rsaEncryptData\":\"kDSFzVtIlztww3hrBHrF71r3kYVn5H23Oa2WOGT7Q5ttduP9nwwjGOOwZIDhJlHZ6Mjgfiz9P2RGkfWUIgP1msWdIlJ8KuQG7vbrNSBG9dqWCTzmEpntod0VEJOyb2/KLhwrwBhgjQ5iq/Pg/e9KAAjIUGkzl8JmHaWBd+XF6vRUT+VqhYUhMlUGHr1YvIogLep0K7QoijwqGVFUZJ1uSklEiVdls0GxzQmqfTeiwgV7uoMbKLzPcJk2m0CBfm5deOdGEG3cMlGjKUrSRtHTRm2VjyIZNBviQvqXohkXCDQ8HcPlgRf25w+ryeig5gTknTtH5J7KgewaSKpNAEui/g==\",\"encryptMethod\":\"AES\",\"reqData\":\"Aoa6Oeygafp8/J6+lPynTH+/eFEcc7gdWuwosdi1wtlTilL6XoL+QG7CZV1qPQbxmIkgmQcOOTEgsjBSEi2Y/+2veqFeoGl2Yfz6/W6/chi3mtBvj7whhkO1yK03atEAf8ayAVGxaVbiPyXb3SVikGZkxCUXy7JzZG0VncnpZzN3LcSb/E1emsmwD+iDEJnm3KLWiT4YCSYiZRnrQ6E0Eqy9QcdIuMCTzN+mIP06lD+Ctn3VLfiVbKqylCzZ9ex1i/prkL/6aGatp7LiV2ZjG7/WN5C8lJAI2xiupBNgLj7HZ8P9QCq5pNA3BBBRU0Pg1cDGKoBDFQOQR04qVNeAqac9kaMjzQKPmAbDTzOdKTg=\",\"appAccessToken\":\"\",\"seqNO\":\"329406\",\"appID\":\"6d9df84d-99f4-4cbe-8056-002b11a12447\"}";
        //收到回调密文，解密方法
        JSONObject reqMsg =SecurityUtil.decrypt(JSON.parseObject(reqInput));*/

        JSONObject rspqMsg3 = new JSONObject();
        Map<String, Object> map3 = new HashMap<String, Object>();
        Map<String, Object> headmap3 = new HashMap<String, Object>();
        Map<String, Object> bodymap3 = new HashMap<String, Object>();
        headmap3.put("errorCode", "0");
        headmap3.put("errorMsg", "消息已收到");
        map3.put("head", headmap3);
        map3.put("body", bodymap3);
        rspqMsg3.put("rspData", JSON.toJSON(map3));
        System.out.println(JSON.toJSON(map3));
        //组装响应报文，调加密方法
        rspqMsg3 = SecurityUtil.encrypt(rspqMsg3);
        System.out.println(rspqMsg3);
    }

}
