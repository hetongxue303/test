package pool;

import java.io.Serializable;

/**
 * @author 何同学
 */
public class User implements Serializable {

    public String val;
    private Integer id;
    private String name;
    private Integer age;
    private Boolean isDelete;

    public User() {
    }

    public User(String val) {
        this.val = val;
    }

    public User(Integer id, String name, Integer age, Boolean isDelete) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isDelete = isDelete;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "User{" + "val='" + val + '\'' + ", id=" + id + ", name='" + name + '\'' + ", age=" + age + ", isDelete=" + isDelete + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}