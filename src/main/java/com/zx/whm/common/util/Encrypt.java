package com.zx.whm.common.util;


/**
 * <p>Title: 简单的加解密算法</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author zrsnake
 * @version 1.0
 */

public class Encrypt{

  //加密种子
  public static final int  pass1 = 0x0a;
  public static final int  pass2 = 0x01;

  public static String DoEncrypt(String str)
  {
          int i;
          int tmpch;
          StringBuffer enStrBuff = new StringBuffer();
          for(i = 0;i <str.length();i++)
          {
            tmpch = (int)str.charAt(i);

            tmpch = tmpch^pass2;
            tmpch = tmpch^pass1;

            enStrBuff.append(Integer.toHexString(tmpch));
          }

          return enStrBuff.toString().toUpperCase();
  }

  public static String DoDecrypt(String str)
  {

          int tmpch;
          String deStr = str.toLowerCase();
          StringBuffer deStrBuff = new StringBuffer();
          for (int i=0;i<deStr.length();i+=2)
           {
             String subStr  = deStr.substring(i,i+2);
             tmpch = Integer.parseInt(subStr,16);
             tmpch = tmpch^pass2;
             tmpch = tmpch^pass1;
             deStrBuff.append((char)tmpch);
           }
          return deStrBuff.toString();
  }


  public static void main(String[] args)
  {
       String source = "656E7C68796639";
       String s = source;//DoEncrypt(source);
       System.out.println("de="+s);
        
       source = DoDecrypt(s);
       System.out.println("en="+source);
  }
}
