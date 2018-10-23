package com.chs.core.app;

import com.chs.core.utils.WishPreference;

/**
 * 作者：chs
 * 时间：2018-10-22 15:59
 * 描述：登陆管理类
 */
public class AccountManager {

   private enum SingTag{
       SING_TAG
   }

   //保存用户的登陆状态
   public static void setSingState(boolean state){
       WishPreference.setAppFlag(SingTag.SING_TAG.name(),state);
   }

   public static boolean isSign(){
       return WishPreference.getAppFlag(SingTag.SING_TAG.name());
   }

   public static void checkAccount(IUserChecker checker){
       if(isSign()){
           checker.onSignIn();
       }else {
           checker.onNotSignIn();
       }
   }
}
