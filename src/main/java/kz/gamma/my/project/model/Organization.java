package kz.gamma.my.project.model;



import com.sun.org.apache.xpath.internal.operations.Or;
import kz.gamma.my.project.service.AllRandomMethods;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Organization {


    public Long id;
    public Long caOrganizationId;
    public boolean isNotResident;
    public boolean ieMark;
    public String orgName;
    public String orgAddress;

    public String bin;
    public String vatin;            // ID NDS
    public String stateRegCertNum;
    public LocalDate issueDate;
    public Long idIssuer;
    public String firstHeadFullName;
    public String firstHeadRole;
    public String phoneNumber;



    public Organization(){
        this.id = AllRandomMethods.makeRandomLong(8);
        this.caOrganizationId= AllRandomMethods.makeRandomLong(8);
        this.isNotResident = AllRandomMethods.makeRandomBoolean();
        this.ieMark= AllRandomMethods.makeRandomBoolean();
        this.orgName = AllRandomMethods.makeRandomString(8);
        this.orgAddress= AllRandomMethods.makeRandomString(8);

        this.bin= AllRandomMethods.makeRandomString(8);
        this.vatin= AllRandomMethods.makeRandomString(8);            // ID NDS
        this.stateRegCertNum= AllRandomMethods.makeRandomString(8);
        this.issueDate = AllRandomMethods.makeRandomLocalDate();
        this.idIssuer= AllRandomMethods.makeRandomLong(8);
        this.firstHeadFullName= AllRandomMethods.makeRandomString(8);
        this.firstHeadRole= AllRandomMethods.makeRandomString(8);
        this.phoneNumber= AllRandomMethods.makeRandomString(8);
    }

    public Long getId() {
        return id;
    }

    public Long getCaOrganizationId() {
        return caOrganizationId;
    }


}
