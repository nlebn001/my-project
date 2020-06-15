package kz.gamma.my.project.model;

import kz.gamma.my.project.service.AllRandomMethods;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class User {

    public Long id;
    //todo change to login critical
    public String name;
    public String password;
    //todo kajetsya suda nado postavit not null
    public Long roleId;
    public String cmpUserId;
    public Long editorUserId;
    public String dn;
    public String oldDn;
    public Long organizationId;
    @NotNull
    public Long caOrganizationId;
    public boolean isBlocked;
    public boolean isConfirmed = true;
    /* Confirmation by sms*/
    public boolean isSmsConfirmed = true;
    public Boolean isRemoved = false;
    //no need for this field
    //public boolean isNotify;
    public Date created;
    public Date updated;
    /* Generated sms-code for user*/
    public String smsCode;
    /* Date of when user attempted to confirm his sms-code */
    public Date smsTryConfirmDate;
    /* Trial number of attempts*/
    public int smsTryNumber;
    /* Subscription to notifications*/
    public boolean isSubscribed = false;
    /* Subscription to notifications and confirm by user*/
    public boolean isSubscribedConfirm = false;
    /* Email token generated for user*/
    public String emailToken;
    /* Creation date of user token */
    public Date emailTokenDate;
    /* authentication step */
    public int authStep;

    public User(){

        this.id = AllRandomMethods.makeRandomLong(8);
        this.name = AllRandomMethods.makeRandomString(8);
        this.password = AllRandomMethods.makeRandomString(8);
        this.roleId = AllRandomMethods.makeRandomLong(8);
        this.cmpUserId = AllRandomMethods.makeRandomString(8);
        this.editorUserId = AllRandomMethods.makeRandomLong(8);
        this.dn = AllRandomMethods.makeRandomString(8);
        this.oldDn = AllRandomMethods.makeRandomString(8);
        this.organizationId = AllRandomMethods.makeRandomLong(8);
        this.caOrganizationId=AllRandomMethods.makeRandomLong(8);
        this.isBlocked = AllRandomMethods.makeRandomBoolean();
        this.isConfirmed = AllRandomMethods.makeRandomBoolean();
        this.isSmsConfirmed = AllRandomMethods.makeRandomBoolean();
        this.isRemoved = AllRandomMethods.makeRandomBOOLEAN();
        this.created = AllRandomMethods.makeRandomDate();
        this.updated=AllRandomMethods.makeRandomDate();
        this.smsCode=AllRandomMethods.makeRandomString(8);
        this.smsTryConfirmDate = AllRandomMethods.makeRandomDate();
        this.smsTryNumber = AllRandomMethods.makeRandomInt(4);
        this.isSubscribed = AllRandomMethods.makeRandomBoolean();
        this.isSubscribedConfirm = AllRandomMethods.makeRandomBoolean();
        this.emailToken = AllRandomMethods.makeRandomString(6);
        this.emailTokenDate = AllRandomMethods.makeRandomDate();
        this.authStep = AllRandomMethods.makeRandomInt(4);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        String blocked = "";
        if(isBlocked == true) {
            blocked = "заблокирован";
        }else
            blocked = "в порядке";

        return "{{ "+" ID Пользователя: " +id + "|| Логин: "+ name+ "|| Пароль: "+ password+ " || Был создан: "+ created+"|| Статус блокировки " + blocked +" }}"+"\n";
    }
}
