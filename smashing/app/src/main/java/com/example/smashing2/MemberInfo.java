package com.example.smashing2;

import android.widget.EditText;

public class MemberInfo {
    private String Name;
    private String Birth1;
    private String Birth2;
    private String Birth3;
    private String Phone1;
    private String Phone2;
    private String Phone3;
    private String Add;

    public MemberInfo(String Name, String Birth1, String Birth2, String Birth3, String Phone1,
                      String Phone2, String Phone3, String Add) {
        this.Name = Name;
        this.Birth1 = Birth1;
        this.Birth2 = Birth2;
        this.Birth3 = Birth3;
        this.Phone1 = Phone1;
        this.Phone2 = Phone2;
        this.Phone3 = Phone3;
        this.Add = Add;
    }
    //이름
    public String getName() {
        return this.Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    //생년
    public String getBirth1() {
        return this.Birth1;
    }
    public void setBirth1(String Birth1){
        this.Birth1 = Birth1;
    }
    //월
    public String getBirth2() {
        return this.Birth2;
    }
    public void setBirth2(String Birth2){
        this.Birth2 = Birth2;
    }
    //일
    public String getBirth3() {
        return this.Birth3;
    }
    public void setBirth3(String Birth3){
        this.Birth3 = Birth3;
    }
    //폰1
    public String getPhone1() {
        return this.Phone1;
    }
    public void setPhone1(String Phone1){
        this.Phone1 = Phone1;
    }
    //폰2
    public String getPhone2() {
        return this.Phone2;
    }
    public void setPhone2(String Phone2){
        this.Phone2 = Phone2;
    }
    //폰3
    public String getPhone3() {
        return this.Phone3;
    }
    public void setPhone3(String Phone3){
        this.Phone3 = Phone3;
    }
    //주소
    public String getAdd() {
        return this.Add;
    }
    public void setAdd(String Add){
        this.Add = Add;
    }
}
