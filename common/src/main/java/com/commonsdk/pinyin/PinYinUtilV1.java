package com.commonsdk.pinyin;

import com.commonsdk.pinyin.HanziToPinyin.Token;

import java.util.ArrayList;

/**
 * 汉字转拼音工具类（调用HanziToPinyin类）
 *
 * @author ztx
 */
public class PinYinUtilV1 {
    /**
     * （调用HanziToPinyin类）汉字返回拼音，字母原样返回，都转换为小写
     *
     * @param input 汉字
     * @return 拼音
     */
    public static String getPinYin(String input) {
        ArrayList<Token> tokens = HanziToPinyin.getInstance().get(input);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (Token token : tokens) {
                if (Token.PINYIN == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * （调用HanziToPinyin类） 返回首字母拼音，都转换为小写
     *
     * @param input 汉字
     * @return 首字母
     */
    public static String getPinYinHead(String input) {
        ArrayList<Token> tokens = HanziToPinyin.getInstance().get(input);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (Token token : tokens) {
                if (Token.PINYIN == token.type) {
                    sb.append(token.target.substring(0, 1));
                } else {
                    sb.append(token.source.substring(0, 1));
                }
            }
        }
        return sb.toString().toLowerCase();
    }
}
