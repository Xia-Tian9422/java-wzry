package com.blb.shop.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000117697775";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key ="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCmim5+TOdiIc4B/BO/xmr34+s0EsgXm7I3gRdXlyWBUPflFvvaVLvFaFfvFMjif3m57K6oXLvEo7pqU9f6PB1jDKjTrE1bQpqQxr/Dr4qaCdo+M6GfCim4xiUhMt1d5Y59KAujDv3WP8NBYCAAH7Wfu7N2T2IBs+dW51fw2HbS9+/cqE9tFQaBi34hV1J7tsohCFvdeGjc8JDMvcdf8VqLkJUij2C5fA+u7DdfbZXHaOnpowkBZWihpgbbm5FpFAyERk1l+DE4GGkW7aCD0ocMj6ltJF4rFGLqqa6xJgUQaS3Tpn2GLNwTRpmjgJel0+FtKrTiD3iLdUbXeQsNxHi3AgMBAAECggEAC/yYuVR1rvV562CmVz5DwrXP3v6fEjvtltsev/osA+O9A+skk3FX1nyNH7MiRlMggsZLR+jsxtl1+GMyf1tGTjPCDwIuyyu/Dj6P3NjMpAujfqiesGC94gO49mu/zRtRpyZzt/fDQ2Fxf0oY44lkvCt5XkGWb0LwmquqA7bAzqxVnk6wM+crlgSOZT8GvGJf/VeqfCKklXj1c/cjvX/jlXTLF1ksybmvLSRXWIyisF7tmda8ktO0Vj2nI8G5qMcGqKxwz3naUzn/0T+xlDytuwD4xECscJrygN6rPS5dNlmMW6baMdkDoWpHeT0un/uIRJ+3aasw0iP4DagNJ+GkyQKBgQD0Gu9N83oT2j/AVEq5v9t1AbNbvGtSMIENT50Iu4OuIH3c1iDVVVb8I/wN02+oVlsHxSxbXNUhhuPg4Qo6eGde6Y2CAWIqAcfpXsdx8GdtXMfVrcrHRHREkYGwIiTVzFlUg7eGexjROZp6hJLZHPHUPwodVV7fhurxpVs8jtfipQKBgQCup+1Ly5b1oNRt+lffX5a2cnDZ7rv3rZmh8HpBPWLsVOjmo5PuK9bgQ+wsiajDG/oTYSzmixhUBjCgA4eoGnsatPvnBZMN1DpMotjMukNSJYMhq2iH6B/Ch8QuwpWYu0F4GcJA3oKDhwV1fk2KlnFC2LRRWvGygetqHFkG/xIbKwKBgFKxISn99fftsjT8GQhNpxE/DzxfWtfP2JfixWak5xnApgJ+g0bhjwMBSayBWMzk3BeIwtr8WrLt2BphAa8HBLWRVr+YqmFikw9KbTP65X6VtJh9NDlGxkBILm+1UVcfz48iw7QJ1UFjSyJ/8KMd/AoThOCk/+um78YGZx7foOCFAoGASTPA1V/DKbf60Q13/vtFY7qbafwRdEGifqkX1HgyGfOMmf49ZjmhmeVd+1zHCt+S9MRp2Ua7L5xYjPhtxxsyNYNdgPppJAarWKt4SnZW+PsX9uisqymzBgl5RE/x+02gpwHE1QNtmErjEteZEFj1p5rEGMURQEwLqzGubqxBnnkCgYAX6lYnjaPvLv/JyZS5w8dJEupyUXTJQJb4bc3a8hv0tieB8BG0GKSvezzBAET1CQyuSsFpszrPHfhOqfuSS6alY3nmq4EbUQ5/I4a9X8OsWbgzxjtkz7bXBs46LlwfExmcBaH0nU78tbb2rv+zCfqxkDsO8yNpo6MCZxQE1CEp6w==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs2CYutUqXpRVIaTPUKtM1MX7K3tdcH5DPYkPOKExbkQ1Qn7AyI15LEYZ2GXrspRkHJH79HFk+gyJiYlSLBsj5rmSCzVs9nltBReIhbuOAzxuUKAklsUpCb0yH0kQ0hTkMpzTfl4h3J9BSf/4SYz+Vyn9IY0somAB6BoSZP4vxDn4yVWeVbuT41DB5gsclRNLiN+sk1qbqHgp3F8304L9NCds9nF+Fm2BlowZFSqZfgaI61tAKousgWyaWQxoZr57SKFiluiK7udc7Wz+uts5dkZxCGAI7cv9K6UCl7LRkfUP+rBUOEjVCj/eJJ7E4XOjZgB9YRSzZbY8MNxd1B7PkwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/localhost:8080/shop/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/shop/order/callback";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

