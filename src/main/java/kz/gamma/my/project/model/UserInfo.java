package kz.gamma.my.project.model;

import kz.gamma.my.project.service.AllRandomMethods;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;


public class UserInfo {

    public Long id;
    public Long userId;
    public Long editorUserId;
    //@NotBlank
    public String firstName;        //4
    public String middleName;       //5
    //@NotBlank
    public String lastName;         //3
    public LocalDate birthday;      // 7
    public String address;          //15
    //@NotNull
    @Email
    public String email;             //16
    public String cn;
    public boolean isNotResident;       // 1
    public Long resCountryId;
    @Size(min = 12, max = 12)
    public String iin;                  // 2
    public String idNumber;             // 8
    public LocalDate idIssueDate;       // 10
    public Long idIssuer;               //9
    public Long cityId;                 // 12
    public String phone;                // 14
    /* getting this property from front-end */
    public String phoneMobile;
    public Long bankId;
    public String account;
    public Date created;


    public UserInfo() {
        this.id = AllRandomMethods.makeRandomLong(8);
        this.userId = AllRandomMethods.makeRandomLong(8);
        this.editorUserId = AllRandomMethods.makeRandomLong(8);
        this.firstName = AllRandomMethods.makeRandomString(8);
        this.middleName = AllRandomMethods.makeRandomString(8);
        this.lastName = AllRandomMethods.makeRandomString(8);
        this.birthday = AllRandomMethods.makeRandomLocalDate();
        this.address = AllRandomMethods.makeRandomString(8);
        this.email = AllRandomMethods.makeRandomString(8);
        this.cn = AllRandomMethods.makeRandomString(8);
        this.isNotResident = AllRandomMethods.makeRandomBoolean();
        this.resCountryId = AllRandomMethods.makeRandomLong(8);
        this.iin = AllRandomMethods.makeRandomString(8);
        this.idNumber = AllRandomMethods.makeRandomString(8);
        this.idIssueDate = AllRandomMethods.makeRandomLocalDate();
        this.idIssuer = AllRandomMethods.makeRandomLong(8);
        this.cityId = AllRandomMethods.makeRandomLong(8);
        this.phone = AllRandomMethods.makeRandomString(8);
        this.phoneMobile = AllRandomMethods.makeRandomString(8);
        this.bankId = AllRandomMethods.makeRandomLong(8);
        this.account = AllRandomMethods.makeRandomString(8);
        this.created = AllRandomMethods.makeRandomDate();
    }

    public Long getId() {
        return id;
    }
    public Long getUserId() {return userId;}
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }
}