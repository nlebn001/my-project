package kz.gamma.my.project.model;

import kz.gamma.my.project.service.AllRandomMethods;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CaOrganization {

    public Long id;
    public boolean isNotResident;
    public boolean ieMark;
    public String orgName;
    public String orgAddress;
    @Size(min = 12, max = 12)
    public String iin;
    public String bin;
    public String vatin;            // ID NDS
    public String stateRegCertNum;
    public LocalDate issueDate;
    public Long idIssuer;
    public String firstHeadFullName;
    public String firstHeadRole;
    public String phoneNumber;

    public List<User> userList;

    public CaOrganization(List<User> userList){

            this.id = AllRandomMethods.makeRandomLong(8);
            this.isNotResident = AllRandomMethods.makeRandomBoolean();
            this.ieMark= AllRandomMethods.makeRandomBoolean();
            this.orgName = AllRandomMethods.makeRandomString(8);
            this.orgAddress= AllRandomMethods.makeRandomString(8);
            this.iin = AllRandomMethods.makeRandomString(12);
            this.bin= AllRandomMethods.makeRandomString(8);
            this.vatin= AllRandomMethods.makeRandomString(8);            // ID NDS
            this.stateRegCertNum= AllRandomMethods.makeRandomString(8);
            this.issueDate = AllRandomMethods.makeRandomLocalDate();
            this.idIssuer= AllRandomMethods.makeRandomLong(8);
            this.firstHeadFullName= AllRandomMethods.makeRandomString(8);
            this.firstHeadRole= AllRandomMethods.makeRandomString(8);
            this.phoneNumber= AllRandomMethods.makeRandomString(8);

            this.userList = userList;

    }

    public Long getId() {
        return id;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getIin() {
        return iin;
    }

    public List<User> getUserList() {
        return userList;
    }

    @Override
    public String toString() {

        String resident = "";
        if(isNotResident == true) {
            resident = "нет";
        }else
            resident = "есть";

        String ie = "";
        if(ieMark == true) {
            ie = "да";
        }else
            ie = "нет";

        return " ID Организации: " +id + " || Название Организации: "+ orgName + " || Резидент: "+ resident+ " || Признак ИП: "+ ie + "\n"+
                " || Сотрудники: " + userList+ "\n";

    }


}
