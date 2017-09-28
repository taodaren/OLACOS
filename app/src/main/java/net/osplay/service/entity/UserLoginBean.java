package net.osplay.service.entity;

import net.osplay.data.bean.Account;

import java.util.List;

/**
 * 登录
 * {
 "ok": "true",
 "member": [
 {
 "SCHOOL": "",
 "ISDELETE": "0",
 "SHENHE": "0",
 "PHONE": "17853100903",
 "CREATEDATE": "2017-09-21 17:07:21",
 "STU_PATH": "",
 "CARD_PATH": "null",
 "CN": "啊啊啊",
 "NICK_NAME": "雪颜er",
 "CARD": "371122198910161212",
 "CARD_F_PATH": "/upLoadResource/e86b6c543eb74840adde38a13d7698e9/CBD鸟瞰_最新_看图王.jpg",
 "PASSWORD": "Y9sVVJ8RW/Y7lzgKZG1yUAb7WchTLa+cPequLEzbInrB12KF",
 "FANS_COUNT": "3",
 "CARD_B_PATH": "/upLoadResource/36188d22ece84b47960d9eeb2f2768b6/1496453261173.jpg",
 "ID": "3f68feea89784e20873f1de2e694880c",
 "POINTS": "0",
 "FOCUS_COUNT": "1"
 }
 ]
 }
 */

public class UserLoginBean {

    private String ok;
    private List<Account> member;

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public List<Account> getMember() {
        return member;
    }

    public void setMember(List<Account> member) {
        this.member = member;
    }


}
