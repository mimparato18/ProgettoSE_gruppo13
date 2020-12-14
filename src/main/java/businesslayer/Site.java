/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

/**
 *
 * @author MARCO CALABRESE
 */
public class Site {

    private String branchOffice;
    private String department;

    public Site(String branchOffice, String department) {
        this.branchOffice = branchOffice;
        this.department = department;
    }

    public String getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(String branchOffice) {
        this.branchOffice = branchOffice;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
