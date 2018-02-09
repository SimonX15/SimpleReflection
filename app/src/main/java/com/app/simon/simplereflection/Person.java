package com.app.simon.simplereflection;

/**
 * desc:
 * date: 2018/2/9
 *
 * @author xw
 */
public class Person {

    /**
     * name : 大雄
     * address : 软件园
     * phone : 151********
     */

    private String name;
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
}
