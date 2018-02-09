package com.app.simon.simplereflection;

/**
 * desc: 公司
 * date: 2018/2/9
 *
 * @author xw
 */
public class Company {

    /**
     * name : **科技有限公司
     * code : LLST19746376721
     * owner : {"name":"大雄","address":"软件园","phone":"151********"}
     */

    private String name;
    private String code;
    private Person owner;

    Company() {

    }

    private Company(String name) {
        this.name = name;
    }

    private Company(String name, String code, Person owner) {
        this.name = name;
        this.code = code;
        this.owner = owner;
    }

    /** 默认数据 */
    private String data = "Default Data";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", owner=" + owner +
                '}';
    }
}
