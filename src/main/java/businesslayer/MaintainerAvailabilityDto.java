/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

/**
 *
 * @author marku
 */
public class MaintainerAvailabilityDto {
    private String maintainer;
    private String skills;
    private String availMon;
    private String availTue;
    private String availWed;
    private String availThu;
    private String availFri;
    private String availSat;
    private String availSun;

    public MaintainerAvailabilityDto(String maintainer, String skills, String availMon, String availTue, String availWed, String availThu, String availFri, String availSat, String availSun) {
        this.maintainer = maintainer;
        this.skills = skills;
        this.availMon = availMon;
        this.availTue = availTue;
        this.availWed = availWed;
        this.availThu = availThu;
        this.availFri = availFri;
        this.availSat = availSat;
        this.availSun = availSun;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getAvailMon() {
        return availMon;
    }

    public void setAvailMon(String availMon) {
        this.availMon = availMon;
    }

    public String getAvailTue() {
        return availTue;
    }

    public void setAvailTue(String availTue) {
        this.availTue = availTue;
    }

    public String getAvailWed() {
        return availWed;
    }

    public void setAvailWeb(String availWed) {
        this.availWed = availWed;
    }

    public String getAvailThu() {
        return availThu;
    }

    public void setAvailThu(String availThu) {
        this.availThu = availThu;
    }

    public String getAvailFri() {
        return availFri;
    }

    public void setAvailFri(String availFri) {
        this.availFri = availFri;
    }

    public String getAvailSat() {
        return availSat;
    }

    public void setAvailSat(String availSat) {
        this.availSat = availSat;
    }

    public String getAvailSun() {
        return availSun;
    }

    public void setAvailSun(String availSun) {
        this.availSun = availSun;
    }
    
    
  
}
