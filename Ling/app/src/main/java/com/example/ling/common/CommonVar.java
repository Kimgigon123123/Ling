package com.example.ling.common;

import com.example.ling.login.Ling_MemberVO;

import java.util.HashMap;

public class CommonVar {
    //안드로이드는 스프링과 다름. (Session)
    //static변수를 활용.
    public static Ling_MemberVO loginInfo; //<-- 여기에 로그인이 정상적으로 되면 정보를 저장.
    public static HashMap<String , String> idMap = new HashMap<>();
}
