package com.app.simon.simplereflection;

import android.util.Log;

/**
 * desc:
 * date: 2018/2/9
 *
 * @author xw
 */
public class Person {
    /** TAG */
    public static final String TAG = Person.class.getSimpleName();

    /**
     * name : 大雄
     * address : 软件园
     * phone : 151********
     */

    private String name;
    public String test;
    private String address;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    private void show() {
        Log.i(TAG, "执行了show方法");
    }

    private String showWithStr(String msg) {
        Log.i(TAG, "执行了showWithStr方法：msg");
        return msg;
    }
}
