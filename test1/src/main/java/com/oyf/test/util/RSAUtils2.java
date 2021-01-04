package com.oyf.test.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * @author ：ouyangfei
 * @date ：Created in 2020/11/25
 * @description：公钥加密
 */
public class RSAUtils2 {

    /**
     * SIGN_ALGORITHMS
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * 公钥加密
     *
     * @param content
     * @param public_key
     * @return
     * @throws Exception
     */
    public static String signWithPublicKey(String content, String public_key) throws Exception {
        byte[] buffer = Base64.decodeBase64(public_key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(1, publicKey);
        byte[] data = content.getBytes("utf-8");
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        int key_len = publicKey.getModulus().bitLength() / 8 - 11;
        for (int i = 0; inputLen - offSet > 0; offSet = i * key_len) {
            byte[] cache;
            if (inputLen - offSet > key_len) {
                cache = cipher.doFinal(data, offSet, key_len);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] encryptedData = out.toByteArray();
        out.close();
        return new String(Base64.encodeBase64(encryptedData));
    }

    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decodeBase64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 私钥解密
     *
     * @param content
     * @param private_key
     * @param input_charset
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String content, String private_key, String input_charset)
            throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decodeBase64(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        // rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), input_charset);
    }

    public static void main(String args[]) throws Exception {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDzyt1mROVxMSkwoYSTBiRyIVvGxsjl0Gw3Flzz2+MDEsZrrQ/HI3ZzLjL9XKjWJw63HJzOAE6nRy61slHzkdKADHf/51nBcUQgyvV4hGpe+U307xE4bdavQe63uA2OHgHzRg9ifPp7e2I31JHralM+OpwHUEv6rnjuO1ujTXOdwIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMPPK3WZE5XExKTChhJMGJHIhW8bGyOXQbDcWXPPb4wMSxmutD8cjdnMuMv1cqNYnDrccnM4ATqdHLrWyUfOR0oAMd//nWcFxRCDK9XiEal75TfTvETht1q9B7re4DY4eAfNGD2J8+nt7YjfUketqUz46nAdQS/queO47W6NNc53AgMBAAECgYB+5j+rGgbYVEqA4NSU/R4bNNJNPErtWPSo01Vhubj4EWuGfHOyRyYjVgsZm7us4xt1BjNiOpSJURMyf0q5L5WA28NAqqJlDyV0gGaQhj9zm/clg+BGzFX6LhVPJr0jAbufKG7mwAaJ2BEMJc8FqW2Hdj80iz7erjlgxKh/jITjaQJBAPNeb2bp7XegBez2OjXdTu1CzLxtVtZ+m8GLnDiRIAPaPKl0Ni6EdKaTuFG+dxW4/g+nTT6+2d4SrYBX2nquuwUCQQDN+NMvydszg6STF8XraFQZbxYhF4vidcIDhbtg9mI2GGcXPG932kaX2lc8OBZvkoRmOTRSWjsEIttpoiHUaDRLAkBjtNLrVPenRPZoiNB8dPj7F50NrbYgFqr1dp9ARaTaLzUfL69rC3VHMcoYI3JigfIEs8Rf6o0UAI74wAWQsbmJAkArLR/IAr5qfAwBsZgRktseQcUXxsHSBPFYn63502we/tqNamPLcfweVOy4eOFrHgGXFCK9qKB6a3wnFo+U4QslAkEAm+vFhEXpzAo6szb5iCO5S3AlUyGFPsgUaMvm4f87XA07XRp+H/4Nh4mLWo2IpTwVjanOLNaoGBKyLDC4UGJDxg==";
        String text = "将“主题”定义的中心词改成“思想与倾向”，虽只是一词之增，但由于它符合作文心理过程的实际，符合文章内容的实际，因而无论对写作实践或阅读实践，都具有重要的意义。写作，尤其是文艺创作，正如黑格尔所说：“一方面求助于常醒的理解力，另一方面也要求助于深厚的心胸和灌注生气的情感。”树立了“主题”是“最主要的思想和倾向”的深刻观念，将使习作者更自觉地用“两条腿走路”，更自觉地酝“情”发“思”，使二者相互渗透，相互激发。这就是“情感思维”。在“情感思维”中，情之所至，材料跃然，思如流水（联想和想象的纽带就是情感）。作家的经验证明：正是在情思激越时，妙笔才能生花，写出文情并茂的传世之作。即使是写逻辑类的论说文，也当如朱光潜先生所说：“也还是要动一点情感，要用一点形象思维”。如果把“主题”仅仅定义为“主要思想”，就会“暗示”人们去写所谓“零度风格”的文章。而“零度风格”的文章既不易写成，更不会打动读者（“零度风格”，zero style，参见朱光潜《漫谈说理文》）。阅读呢？固然，阅读要通过概念、判断、推理去评析，但首先要通过形象、情境和美感等去鉴赏。主题仅仅是“主要思想”的观念，会“暗示”人们将阅读的注意力投向理性分析，而忽视形象思维（不少学生形象思维能力差，与他们自小就接受的“主题就是主要思想”这个定义不无关系）。其实，阅读应当交错地运用抽象思维与形象思维，领会文中情理相生的旨趣。鉴赏文学作品，既要借助想象（与“深厚的心胸和灌注生气的情感”相关联），又要借助分析、综合和概括（与“常醒的理解力”相关联），挖掘作品的思想意义和所蕴含的哲理。这才能发挥文学的认识作用、教育作用和美感作用的整体功能。定义的中心词何以用“倾向”而不用“情感”？这是因为“倾向”除含有“憎爱之情”外，还有“态度”、“趣向”等几个义项，即有更广的外延。文体不同，内容不同，“情感”的类型也各异。各种“思>；情”的文体（各种应用文、政论文、学术论文等），狭义的“情感”（憎爱之情）色彩并不浓，蕴含于文中的，主要是某种志向、愿望、态度或精神。而这些广义的“情感”，均可用“倾向”这一术语来指称。例如，一些学术论文，与其说内中蕴含一种“情感，勿宁说蕴含着一种“倾向”，一种执着地探索并证明真理的欲望、志向和求实精神。因为通常总是把“情感”理解为狭义的，所以用“倾向”可以使定义对各类文体都适用。将“主题”定义的中心词改成“思想与倾向”，虽只是一词之增，但由于它符合作文心理过程的实际，符合文章内容的实际，因而无论对写作实践或阅读实践，都具有重要的意义。写作，尤其是文艺创作，正如黑格尔所说：“一方面求助于常醒的理解力，另一方面也要求助于深厚的心胸和灌注生气的情感。”树立了“主题”是“最主要的思想和倾向”的深刻观念，将使习作者更自觉地用“两条腿走路”，更自觉地酝“情”发“思”，使二者相互渗透，相互激发。这就是“情感思维”。在“情感思维”中，情之所至，材料跃然，思如流水（联想和想象的纽带就是情感）。作家的经验证明：正是在情思激越时，妙笔才能生花，写出文情并茂的传世之作。即使是写逻辑类的论说文，也当如朱光潜先生所说：“也还是要动一点情感，要用一点形象思维”。如果把“主题”仅仅定义为“主要思想”，就会“暗示”人们去写所谓“零度风格”的文章。而“零度风格”的文章既不易写成，更不会打动读者（“零度风格”，zero style，参见朱光潜《漫谈说理文》）。阅读呢？固然，阅读要通过概念、判断、推理去评析，但首先要通过形象、情境和美感等去鉴赏。主题仅仅是“主要思想”的观念，会“暗示”人们将阅读的注意力投向理性分析，而忽视形象思维（不少学生形象思维能力差，与他们自小就接受的“主题就是主要思想”这个定义不无关系）。其实，阅读应当交错地运用抽象思维与形象思维，领会文中情理相生的旨趣。鉴赏文学作品，既要借助想象（与“深厚的心胸和灌注生气的情感”相关联），又要借助分析、综合和概括（与“常醒的理解力”相关联），挖掘作品的思想意义和所蕴含的哲理。这才能发挥文学的认识作用、教育作用和美感作用的整体功能。将“主题”定义的中心词改成“思想与倾向”，虽只是一词之增，但由于它符合作文心理过程的实际，符合文章内容的实际，因而无论对写作实践或阅读实践，都具有重要的意义。写作，尤其是文艺创作，正如黑格尔所说：“一方面求助于常醒的理解力，另一方面也要求助于深厚的心胸和灌注生气的情感。”树立了“主题”是“最主要的思想和倾向”的深刻观念，将使习作者更自觉地用“两条腿走路”，更自觉地酝“情”发“思”，使二者相互渗透，相互激发。这就是“情感思维”。在“情感思维”中，情之所至，材料跃然，思如流水（联想和想象的纽带就是情感）。作家的经验证明：正是在情思激越时，妙笔才能生花，写出文情并茂的传世之作。即使是写逻辑类的论说文，也当如朱光潜先生所说：“也还是要动一点情感，要用一点形象思维”。如果把“主题”仅仅定义为“主要思想”，就会“暗示”人们去写所";
        String privateText = "eEPkodEjHNay1r0ggTDIj8MF0Z0QuaOihosuwehCtJZkb68P8vgi1tkdKedx5F9g8ahaDbVQ9Ur1Ck8LNOyhJxlDPskVL+hA6PQgbDBp0aCXUw1h2NFI8i0litYC1RrxagtBD7tuBEEr6Y6Q6NTvvqau+D6f5K+N929pk7jtVsN3GaZ+p/vxgPwCmM53OMeTnUUJIKuJ7o4fqlWc0kDopJ3G4HbuZqeLD/eulPUM8KfZCxbTradQtPPwh3kD89RGESWsK9p+BGA/HT7z5IZrpvDujXB71xKu+1WTUV8eE11340Yb2QU6/jozUFBTqcjBI+H9pSxKI+4dI4bDgJmrVmi9TMA7wD1b5gB5PnNQ4XlzKkR3hghTsis83dAYddH4aOa3Vypm3v2jes0sOe2CG5r2oVYeCpwVbkdP+GUSSwc9X8GQrqw7/NmW2Le7HmNUqmy4g48DpEiUIfEPgjeHfdRRi1nvgIIDTZSV7G/yidCrn5RXpSqlYVyHhpJWM2+SUYXn5Byu+4iyy1uP3WXjR1Hvx0hEvCL/6KlURyFWwRGWHSn+Y7p6B66DNqkyrhuyTTsZ1uFBUjC6/T6Ag9p/fxwHbPD+TdMkehLX6kVrR9c5cgAjGHw8mawvecHMcAPhekd5KS0kTS9SVbmH2rXAZ3P5/lwcNFU3/+haeqFylpOWDQmu4Xtwps1JOs8KP4D23HFSbWHA7mpLsv1911H/5A7H4UFSyBohj7ogHqCLgwiDWR1PbLVfZYpfxyv5xKqIlR3blxIida4aYmUfqGuVlJSfmdHGzHNEOqcZh2x3mABobCSRR5tlN4+GqtobJafaskc84rKNmM6rgtmGYvRWL2vke74ap2UlpRZ7b/+0LQV3zbLG0xyjaf48DM5vb4O0mAERpHnE9qKj3rlqVgTjr8PyLMdJnWkIfPy/oy4AYYBYlPSgJE6KZtPJ8+HR7J0TayTO5aN9OMcPuw15MSVwb8vAm7Q9OIhLQZMzBQAwdRZb0t8jonrd5V9n0QzO47akKJ7PHWoHMyLYmeSHPnxxNBQmh/s0Xj0GiR32cV+qPt5mKbKwLpCXkbCHrFbcpoarq5laEuxc4oKOc3zIFgX9SqiPZgdo8RnQ2MhzDcJSUUfAtdgSwCyclgzQUV+482kcmxa8XmvCnliyVMrYMwte86JLAaw8VqGHAYTZ6Ekqwi1IqJ4Utxy59BQKxwc5ZqPLN09DmZ0OqIrA7HwH155DDxIJiBr/9Cs1PuiEx+iQ2OT3YhbvNJLpiq4rbpt2v75NzhrJ9iHhZNd4gXPU1W5LUYUWBmoj90RlT+sN+M6uMW4U8FMe9PKkGAjecoDdvwaVmlccJMCmS3/UGyOi/Gcl1YQHdQmFId7F2HFxuv4AXSSFXkGDsbCU8lvc4KIpb/VVJJ51sdxcekBpQlOATDXzANamrTu+EFL1Q4Ycm2JfY2c3qyflMhA+8EqcLcPloyVbRghjfV71nPYb9L7A2dPXVa1O2g5H87OGhblmm/P9D3SJsZuZ979ni4mOdRbeqGswCr6D9wQmRx2OsNk84CuUD0bygHshKTDssGHQWYUSaQ5pn4ti6bv8x0L+HZ7CcnXZOizvq8RCum3F3uwLqISDLshAJvmpgzWCEalgSMqQrcPNgVtgG0xouHIK9HthyHsjCOyrT4LyIrsbd78Bt2tc0xSFuuH+tmB+ZszQHCNNz3xGoWNRRFaLu+etdpg3UQhhpH4GG3wrlnXLc2vjPiOliSDpNf5kwuFekn+vHnfuQWfKJKD3AAJGwXBrvNLfm4jc41k1/3NI8JaxcYhJC8yGZ6WBooP1OM/w+HApuxByvPZVkUlH2wfT4D34jAfIJgDjUzz/1RNhkJhvSiOJUzNGtYBtIZ2LIVlqp9fai4ugzOejuiFkr/qiPttjGVhA2uIM7AyFBpgtSvM+EA2R/ghEsVdUrNUL6nu2UYovcgWLRvDEGDUdp0bv2geIu5gZpQRT3l+UF09jgAa0gvn89/UIEajkQz15204iv3YlleTOs3W9Zl2eqCcBSVKgdCZt6kML";

//        privateText = signWithPublicKey(text, publicKey);
        System.out.println(privateText);

        String s = decryptByPrivateKey(privateText, privateKey, "UTF-8");
        System.out.println(s);
        String s1 = java.net.URLDecoder.decode(s ,"UTF-8");
        System.out.println(s1);
    }

}
