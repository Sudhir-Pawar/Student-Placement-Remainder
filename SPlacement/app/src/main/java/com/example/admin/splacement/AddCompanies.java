package com.example.admin.splacement;

import android.net.Uri;

/**
 * Created by Admin on 27-09-2019.
 */

public class AddCompanies {

    private String logo;
    private String companyname,companydescription,currentreq;
    private String year1,year2,year3,year4;

    public AddCompanies(String logo, String cname,String cdescription,String curntreq,String...year){
        this.logo=logo;
        this.companyname=cname;
        this.companydescription=cdescription;
        this.currentreq=curntreq;
        this.year1=year[0];
        this.year2=year[1];
        this.year3=year[2];
        this.year4=year[3];
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompanydescription() {
        return companydescription;
    }

    public void setCompanydescription(String companydescription) {
        this.companydescription = companydescription;
    }

    public String getCurrentreq() {
        return currentreq;
    }

    public void setCurrentreq(String currentreq) {
        this.currentreq = currentreq;
    }

    public String getYear1() {
        return year1;
    }

    public void setYear1(String year1) {
        this.year1 = year1;
    }

    public String getYear2() {
        return year2;
    }

    public void setYear2(String year2) {
        this.year2 = year2;
    }

    public String getYear3() {
        return year3;
    }

    public void setYear3(String year3) {
        this.year3 = year3;
    }

    public String getYear4() {
        return year4;
    }

    public void setYear4(String year4) {
        this.year4 = year4;
    }
}
