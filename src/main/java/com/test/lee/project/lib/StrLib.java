package com.test.lee.project.lib;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Pattern;

public class StrLib
{
    /**
     * 문자열 null 체크  null 이거나 빈공백이면 true 리턴
     * @param str
     * @return
     */
    public static boolean isEmptyStr (String str)
    {
        if (str == null)
            return true;

        if (str.equals (""))
            return true;

        if (str.equals ("<br>"))
            return true;

        if (str.equals ("null"))
            return true;

        return false;
    }

    public static boolean isEmptyStr (Object obj)
    {
        if (obj == null)
            return true;

        return false;
    }

    public static boolean isExistStr (String str)
    {
        return !isEmptyStr (str);
    }

    public static String onlyDigit (String data)
    {
        if (data == null)
            return "";

        return data.replaceAll ("[^0-9]", "");
    }

    public static boolean phoneNumberCheck (String mobile)
    {
        if (StrLib.isEmptyStr (mobile))
            return false;

        if (mobile.length () < 10)
            return false;

        String prefix = mobile.substring (0, 3);

        if ("010".equals (prefix) && mobile.length () == 11)
            return true;

        if ("050".equals (prefix) && mobile.length () == 11)
            return true;

        return Arrays.asList ("011", "016", "017", "018", "019").contains (prefix);

    }

    public static boolean simplePhoneNumberCheck (String mobile)
    {
        if (StrLib.isEmptyStr (mobile))
            return false;

        mobile = StrLib.onlyDigit (mobile);

        if (mobile.length () == 8 && (mobile.startsWith ("15") || mobile.startsWith ("18")))
            return true;

        if (mobile.length () < 9)
            return false;

        String prefix = mobile.substring (0, 3);

        if ("010".equals (prefix))
            return mobile.length () == 11;

        if ("050".equals (prefix) && mobile.length () == 11)
            return true;

        return mobile.startsWith ("0");

    }

    public  static String makeCommaWon(long price){
        String a_str = String.valueOf(price);
        if(a_str.equals("-")){
            return "-";
        }

        String temp  = "";

        if (a_str.length() == 0)
            return "";

        if(a_str.contains(",")){
            temp = a_str.replace(",", "");
        }else{
            temp = a_str;
        }

        long value = Long.parseLong(temp);
        DecimalFormat format = new DecimalFormat("###,###");
        return format.format(value);
    }

    /**
     * 숫자 null 체크  0이면 true 리턴
     * @param no 비교값
     * @return boolean
     */
    public static boolean isEmptyNum(int no)
    {
        if(no == 0)
            return true;

        return false;
    }

    /**
     * 숫자 null 체크  0이면 true 리턴
     * @param no 비교값
     * @return boolean
     */
    public static boolean isEmptyNum(Integer no)
    {
        if(no == null)
            return true;
        else
            return no == 0;

    }


    /**
     * 스트링 null 체크 null 이면 "" 을 리턴.
     * @param str
     * @return
     */
    public static String notNull (String str)
    {
        if (str == null)
            return "";

        return str;
    }

    /**
     *  String 바인딩 1개 바인딩
     *  StrLib.getStrFormatOne (R.L("RESOURCE_9000"), String.valueOf (mailCnt)));
     * @param templete
     * @param data
     * @return
     */
    static public String getStrFormatOne (String templete, String data)
    {
        MessageFormat messageFormat = new MessageFormat (templete);

        String [] datas = new String [1];

        datas[0] = data;

        return messageFormat.format (datas);
    }

    /**
     *   String 바인딩 2개 바인딩
     *	 StrLib.getStrFormatOne (R.L("RESOURCE_9000"), String.valueOf (mailCnt)));
     * @param templete
     * @param data
     * @param date2
     * @return
     */
    static public String getStrFormatTwo (String templete, String data, String date2)
    {
        MessageFormat messageFormat = new MessageFormat (templete);

        String [] datas = new String [2];
        datas[0] = String.valueOf (data);
        datas[1] = String.valueOf (date2);

        return messageFormat.format (datas);
    }

    /**
     * 전부 str :aacccc delim: a =>[ , , cccc]
     * @param str
     * @param delim
     * @return
     */
    public static Vector<String> getTokens (String str, String delim)
    {
        StringTokenizer st 		= new StringTokenizer (str, delim, true);
        Vector<String> 	vTokens = new Vector<String> ();

        String prevToken = null;
        String curToken = null;

        while (st.hasMoreTokens ())
        {
            curToken = st.nextToken ();

            if (curToken.equals (delim))
            {
                if (prevToken == null || prevToken.equals (delim))
                {
                    vTokens.addElement ("");
                }
            }
            else
            {
                vTokens.addElement (curToken);
            }

            prevToken = curToken;
        }

        if (prevToken.equals (delim))
        {
            vTokens.addElement ("");
        }

        return vTokens;
    }

    /**
     * 바이트를 체크한다. 기준보다 크면 false, 작거나 같으면 true
     *
     * @param txt 체크할 text
     * @param standardByte 기준 바이트 수
     * @return
     */
    public static boolean byteCheck(String txt, int standardByte) {
        if (StrLib.isEmptyStr(txt)) { return true; }

        // 바이트 체크 (영문 1, 한글 2, 특문 1)
        int en = 0;
        int ko = 0;
        int etc = 0;

        char[] txtChar = txt.toCharArray();
        for (int j = 0; j < txtChar.length; j++) {
            if (txtChar[j] >= 'A' && txtChar[j] <= 'z') {
                en++;
            } else if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
                ko++;
                ko++;
            } else {
                etc++;
            }
        }

        int txtByte = en + ko + etc;
        if (txtByte > standardByte) {
            return false;
        } else {
            return true;
        }

    }

    public static int byteCheckLength(String txt) {

        // 바이트 체크 (영문 1, 한글 2, 특문 1)
        int en = 0;
        int ko = 0;
        int etc = 0;

        char[] txtChar = txt.toCharArray();
        for (int j = 0; j < txtChar.length; j++) {
            if (txtChar[j] >= 'A' && txtChar[j] <= 'z') {
                en++;
            } else if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
                ko++;
                ko++;
            } else {
                etc++;
            }
        }

        int txtByte = en + ko + etc;
        return txtByte;

    }

    public static String subStr (String str, int len, String tail)
    {
        try
        {
            if (str == null)
                return "";

            byte[] arrStr = str.getBytes ("UTF8");
            byte[] retStr;

            if (arrStr.length <= len)
                return (str);

            int i = 0;
            double more = 0;

            for (i = 0; i < len; i++)
            {
                if ((int) arrStr[i] > 127 || (int) arrStr[i] < 0)
                {
                    if (i != len - 2)
                    {
                        i += 2;
                        more += 0.9;
                    }
                    else
                        break;
                    if (more >= 4)
                    {
                        len += Math.round (more);
                        more = 0;
                    }
                }
                if (len >= arrStr.length)
                    return (str);
            }

            retStr = new byte[i];
            for (int j = 0; j < i; j++)
                retStr[j] = arrStr[j];

            return (new String (retStr, "UTF8") + tail);
        }
        catch (Exception e)
        {
            e.printStackTrace ();
            System.out.println (e.getMessage ());
        }

        return ("");
    }

    /**
     * 엔터,<,>," " = 변환
     * @param str
     * @return
     */
    public static String printComment (String str)
    {
        str = str.replaceAll ("<","&lt;");
        str = str.replaceAll (">", "&gt;");
        str = str.replaceAll ("\n", "<br>");
        str = str.replaceAll (" ", "&nbsp;");

        return str;
    }

    /**
     *  [ " ] => [ 특수문자 " 로 변환 ]
     * @param str
     * @return
     */
    public static String encodeInputText (String str)
    {
        str = str.replace ("\"", "&quot;");

        return str;
    }

    /**
     *   [ ' ] => [ 특수문자 ' 로 변환 ]
     * @param str
     * @return
     */
    public static String encodeInputText2 (String str)
    {
        str = str.replace ("\'", "&#39");

        return str;
    }

    /**
     *  [ " ] => [ 특수문자 " 로 변환 ]
     * @param str
     * @return
     */
    public static String encodeInputTextToNumber (String str)
    {
        str = str.replace ("\"", "\"");

        return str;
    }

    /**
     * Url 인코드
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode (String url) throws UnsupportedEncodingException
    {
        return URLEncoder.encode (url, "UTF-8");
    }

    /**
     * 동 주소를 출력한다.
     * @param dong
     * @return
     */
    public static String printDongAddr (String dong)
    {
        String [] d = dong.split (" ");

        if (d.length <= 2)
            return dong;

        StringBuffer sb = new StringBuffer ();

        for (int i = 2; i < d.length; i++)
        {
            if (i > 2)
                sb.append (" ");

            sb.append (d[i]);
        }

        return sb.toString ();
    }

    public static boolean isNumeric (String str)
    {
        try
        {
            Double.parseDouble (str);
        }
        catch (NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    /**
     * 전화번호를 나눈다.
     * @param phone
     * @return
     */
    static public String [] getPhoneSplit (String phone)
    {
        if (phone == null)
            return null;

        String [] phones = null;

        if (phone.length () == 11)
        {
            phones = new String [3];

            phones[0] = phone.substring (0, 3);
            phones[1] = phone.substring (3, 7);
            phones[2] = phone.substring (7, 11);
        }
        else if (phone.length () == 10)
        {
            phones = new String [3];

            if (phone.startsWith ("02"))
            {
                phones[0] = phone.substring (0, 2);
                phones[1] = phone.substring (2, 6);
                phones[2] = phone.substring (6, 10);
            }
            else
            {
                phones[0] = phone.substring (0, 3);
                phones[1] = phone.substring (3, 6);
                phones[2] = phone.substring (6, 10);
            }
        }

        return phones;
    }

    /**
     * 전화번호
     * @param num
     * @param mask
     * @return
     */
    public static String phoneNumberHyphenAdd(String num, String mask) {

        String formatNum = "";
        if (StrLib.isEmptyStr(num)) return formatNum;
        num = num.replaceAll("-","");

        if (num.length() == 11) {
            if (mask.equals("Y")) {
                formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
            }else{
                formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
            }
        }else if(num.length()==8){
            formatNum = num.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
        }else{
            if(num.indexOf("02")==0){
                if(mask.equals("Y")){
                    formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-****-$3");
                }else{
                    formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-$2-$3");
                }
            }else{
                if(mask.equals("Y")){
                    formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
                }else{
                    formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
                }
            }
        }
        return formatNum;
    }

    public static String phoneNumberAdd(String num, String mask) {

        String formatNum = "";
        if (StrLib.isEmptyStr(num)) return formatNum;
        num = num.replaceAll("-","");

        if(num.startsWith("1"))
        {
            num = "0" + num;
        }

        String regex = "^(010|011|016|017|018|019)(?:\\d{3}|\\d{4})\\d{4}$";

        if(Pattern.matches(regex, num))
        {
            if (num.length() == 11) {
                if (mask.equals("Y")) {
                    formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-****-$3");
                }else{
                    formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1$2$3");
                }
            }else if(num.length()==8){
                formatNum = num.replaceAll("(\\d{4})(\\d{4})", "$1$2");
            }else{
                if(num.indexOf("02")==0){
                    if(mask.equals("Y")){
                        formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1****$3");
                    }else{
                        formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1$2$3");
                    }
                }else{
                    if(mask.equals("Y")){
                        formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1****$3");
                    }else{
                        formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1$2$3");
                    }
                }
            }
        }
        else
        {
            formatNum = "";
        }


        return formatNum;
    }

    static public boolean isFirstChar (char c)
    {
        if (c == 'ㄱ')
            return true;
        else if (c == 'ㄴ')
            return true;
        else if (c == 'ㄷ')
            return true;
        else if (c == 'ㄹ')
            return true;
        else if (c == 'ㅁ')
            return true;
        else if (c == 'ㅂ')
            return true;
        else if (c == 'ㅅ')
            return true;
        else if (c == 'ㅇ')
            return true;
        else if (c == 'ㅈ')
            return true;
        else if (c == 'ㅊ')
            return true;
        else if (c == 'ㅋ')
            return true;
        else if (c == 'ㅌ')
            return true;
        else if (c == 'ㅍ')
            return true;
        else if (c == 'ㅎ')
            return true;

        return false;
    }

    /**
     * \n 만 <br> 로 바꾼다.
     * @param str
     * @return
     */
    public static String nl2br (String str)
    {
        str = str.replaceAll ("\n", "<br>");

        return str;
    }

    public static String generateToken ()
    {
        SecureRandom random = new SecureRandom();
        return new BigInteger (130, random).toString(32);
    }

    public static String generateApiToken ()
    {
        SecureRandom random = new SecureRandom();
        return new BigInteger (70, random).toString(32) + StrLib.getRandomCode(20);
    }

    public static String getRndNum (int loopCount)
    {
        String str = "";
        int d;

        for (int i = 1; i <= loopCount; i++)
        {
            Random r = new Random();

            d = r.nextInt (9);

            str = str + Integer.toString(d);
        }

        return str;
    }

    public static String getRandomCode(int len)
    {
        Random random =new Random();
        char alphaNum[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
        String str = "";
        for(int j = 0; j < len ; j++)
            str = str + alphaNum[ Math.abs(random.nextInt()) % 36 ];

        return str;
    }

    /**
     * 모바일에 있는 특수 문자를 삭제한다.
     * @param mobile 핸드폰 번호
     * @return 특수 문자 삭제된 핸드폰 번호
     */
    public static String getRemoveMobileChar (String mobile)
    {
        mobile = mobile.replaceAll ("-", "");
        mobile = mobile.replaceAll (" ", "");

        return mobile;
    }

    /**
     * 특수문자 제거 하기
     * @param str
     * @return
     */
    public static String removeSpecialChar(String str){
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        str =str.replaceAll(match, "");
        return str;
    }

    /**
     * 랜덤 숫자 생성
     *
     * @param len
     * @return
     */
    public static String getRandomNumerCode(int len) {
        Random random = new Random();
        char alphaNum[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String str = "";
        for (int j = 0; j < len; j++)
            str = str + alphaNum[Math.abs(random.nextInt()) % 10];

        return str;
    }

    /**
     * String to Int
     * @param val
     * @return
     */
    public static int parseInt (String val)
    {
        if(val == null){
            val = "0";
        }
        val = val.replace (",", "");

        if (val.length () == 0)
            val = "0";

        return Integer.parseInt(val);
    }


    public static boolean stringLength(String message, int length) {
        if(message.length() > length)
            return false;
        else
            return true;
    }
}
