package com.ymwang.park.utils;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: wym
 * @Date: 2018/4/28
 */
public class PatternUtil {
    /**
     * 校验手机.
     *
     * @param mobile the mobile
     * @return the string
     */
    public static boolean checkMobile(String mobile) {
        if (!StringUtils.isEmpty(mobile)) {
            mobile = mobile.trim();
            if (mobile.length() != 11) {
                return false;
            }
            String regEx = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(mobile);
            return matcher.find();
        }
        return false;
    }
    /**
     * 校验是否是字母数字
     *
     * @param content
     * @return
     */
    public static boolean checkCharacter(String content) {
        if (!StringUtils.isEmpty(content)) {
            String trueCard = content.trim();
            String regEx = "^[A-Za-z0-9]+$";
            Pattern keywordPattern = Pattern.compile(regEx);
            Matcher matcher = keywordPattern.matcher(trueCard);
            return matcher.find();
        }
        return false;
    }
    /**
     * 校验是否是车牌号
     *
     * @param carNumber
     * @return
     */
    public static boolean checkCarNumber(String carNumber) {
        if (!StringUtils.isEmpty(carNumber)) {
            carNumber = carNumber.trim();
            if (carNumber.length() != 7) {
                return false;
            }
            String regEx = "^(([\\u4e00-\\u9fa5][a-zA-Z]|[\\u4e00-\\u9fa5]{2}\\d{2}|[\\u4e00-\\u9fa5]{2}[a-zA-Z])[-]?|([wW][Jj][\\u4e00-\\u9fa5]{1}[-]?)|([a-zA-Z]{2}))([A-Za-z0-9]{5}|[DdFf][A-HJ-NP-Za-hj-np-z0-9][0-9]{4}|[0-9]{5}[DdFf])$";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(carNumber);
            return matcher.find();
        }
        return false;
    }
    public static boolean checkChineseCharacter(String content){
        if (!StringUtils.isEmpty(content)) {
            String trueCard = content.trim();
            String regEx = "(\\w*[\\u4E00-\\u9FFF]+\\w*)*";
            Pattern keywordPattern = Pattern.compile(regEx);
            Matcher matcher = keywordPattern.matcher(trueCard);
            return matcher.find();
        }
        return false;
    }
    public static PatternEnum transform(String content) {
        PatternEnum patternEnum = PatternEnum.UNRECOGNIZED;
        if (checkMobile(content)){
            patternEnum = PatternEnum.MOBILE;
        } else if (checkCharacter(content)){
            patternEnum = PatternEnum.CHARACTER;
        }else if (checkChineseCharacter(content)){
            patternEnum = PatternEnum.CHINESE;
        }
        return patternEnum;
    }
    public enum PatternEnum {
        MOBILE("1", "手机号码"),
        CHARACTER("2","字母数字"),
        CARNUMBER("3","车牌号"),
        CHINESE("4","汉字"),
        UNRECOGNIZED("-1", "未知");
        @Getter
        private String dm;
        @Getter
        private String mc;

        PatternEnum(String dm, String mc) {
            this.dm = dm;
            this.mc = mc;
        }
    }
}
