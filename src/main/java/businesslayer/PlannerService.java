/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import dataaccesslayer.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;

/**
 *
 * @author marku
 */
public class PlannerService {

    private Planner planner;
    private MaintenanceActivityDaoImpl activityDao = new MaintenanceActivityDaoImpl();
    private TypologyDaoImpl typologyDao = new TypologyDaoImpl();
    private SiteDaoImpl siteDao = new SiteDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();
    private MaintenanceProcedureDaoImpl procedureDao = new MaintenanceProcedureDaoImpl();
    private MaintainerCompetenceDaoImpl maintainerDao = new MaintainerCompetenceDaoImpl();
    private MaintainerAvailabilityDaoImpl availDao = new MaintainerAvailabilityDaoImpl();

    public PlannerService(Planner planner) {
        this.planner = planner;
    }

    //Activities
    public boolean addActivity(String site, String typology, String description,
            int hours, int minutes, boolean interruptible, String materials, String week, String workspaceNotes) {

        return activityDao.insertActivity(planner.createActivity(new Site(site.split(" - ")[0], site.split(" - ")[1]), typology, description,
                (hours * 60 + minutes), interruptible, materials, Integer.parseInt(week), workspaceNotes));
    }

    public boolean updateActivity(int id, Site site, String typology, String description,
            int interventionTime, boolean interruptible, String materials, int week, String workspaceNotes) {

        return activityDao.updateActivity(planner.createActivity(id, site, typology, description,
                interventionTime, interruptible, materials, week, workspaceNotes));
    }

    public boolean deleteActivity(int id) {

        return activityDao.deleteActivity(id);
    }

    public ArrayList<MaintenanceActivity> viewActivities() {
        ArrayList<MaintenanceActivity> list = activityDao.getAllActivities();
        for (var item : list) {
            if (item.getProcedure() != null) {
                item.getProcedure().setCompetencies(procedureDao.getCompetenciesByName(item.getProcedure().getName()));
            }
        }
        return list;
    }

    public ArrayList<MaintenanceActivity> getActivitiesByWeek(int week) {

        ArrayList<MaintenanceActivity> list = activityDao.getActivitiesByWeek(week);
        for (var item : list) {
            if (item.getProcedure() != null) {
                item.getProcedure().setCompetencies(procedureDao.getCompetenciesByName(item.getProcedure().getName()));
            }
        }
        return list;
    }

    public String getNextId() {

        try {
            return Integer.toString(activityDao.nextId());
        } catch (InterruptedException | SQLException ex) {
            return "err";
        }

    }

    //Sites
    public ArrayList<String> getAllSites() {
        ArrayList<Site> sites = siteDao.getAllSites();
        ArrayList<String> returnSites = new ArrayList<>();
        if (sites != null) {
            for (Site s : sites) {
                returnSites.add(s.getBranchOffice() + " - " + s.getDepartment());
            }
            return returnSites;
        }
        return null;
    }

    //Typologies 
    public ArrayList<String> getAllTypologies() {
        return typologyDao.getAllTypologies();
    }

    //Maintainer availabilities
    public ArrayList<MaintainerAvailabilityDto> getAvailabilityByWeek(int activityId, int week) {

        ArrayList<MaintainerAvailabilityDto> availabilities = new ArrayList<>();
        ArrayList<User> maintainers = userDao.getAllMaintainers();
        ArrayList<MaintainerAvailability> availMaint;

        String matchedSkills;
        try {
            for (var user : maintainers) {
                String mon = "100%", tue = "100%", wed = "100%", thu = "100%", fri = "100%", sat = "100%", sun = "100%";
                availMaint = availDao.getMaintainerAvailabilitiesByWeek(week, user.getUsername());
                Procedure procedure = (activityDao.getActivityById(activityId).getProcedure());
                if (activityDao.getActivityById(activityId).getProcedure() != null) {
                    matchedSkills = checkCompetencies(procedureDao.getCompetenciesByName(procedure.getName()), maintainerDao.getMaintainerCompetence(user.getUsername()));
                } else {
                    matchedSkills = "N/A";
                }
                if (availMaint != null) {
                    //Skills of the maintainers that match the required skills 

                    for (var item : availMaint) {
                        switch (item.getDay()) {
                            case (1):
                                mon = getAvailabilityPercentage(item.getHours());
                                break;
                            case (2):
                                tue = getAvailabilityPercentage(item.getHours());
                                break;
                            case (3):
                                wed = getAvailabilityPercentage(item.getHours());
                                break;
                            case (4):
                                thu = getAvailabilityPercentage(item.getHours());
                                break;
                            case (5):
                                fri = getAvailabilityPercentage(item.getHours());
                                break;
                            case (6):
                                sat = getAvailabilityPercentage(item.getHours());
                                break;
                            case (7):
                                sun = getAvailabilityPercentage(item.getHours());
                                break;
                            default:
                                break;
                        }

                    }
                }

                availabilities.add(new MaintainerAvailabilityDto(user.getUsername(), matchedSkills, mon, tue, wed, thu, fri, sat, sun));
            }
        } catch (SQLException ex) {
            return null;
        }
        return availabilities;
    }

    private String checkCompetencies(ArrayList<String> compAct, ArrayList<String> compMaint) {

        int matchSkills;
        int totalSkills = compAct.size();

        if (compAct.retainAll(compMaint)) {
            matchSkills = compAct.size();
        } else {
            matchSkills = totalSkills;
        }

        return (matchSkills + "/" + totalSkills);
    }

    private String getAvailabilityPercentage(int[] hours) {
        int sum = 0;
        for (var hour : hours) {
            sum += hour;
        }
        return String.valueOf(sum * 100 / 420) + "%";
    }
    
    public ArrayList<String> getAvailabilityByWeekAndDay(int week, String username, String dayname){
        ArrayList<String> availabilities = new ArrayList<>(); 
        MaintainerAvailability ava = availDao.getMaintainerAvailabilitiesByWeekAndDay(week, username, getNumberOfDay(dayname));
        if(ava == null){
            for(int i = 0; i<7;i++)
                availabilities.add("60 min");
            return availabilities;
        }else{
                for(int i = 0; i<7;i++)
                availabilities.add(ava.getHours()[i]+" min");
            return availabilities;
                }
            
        
    }
    
    private int getNumberOfDay(String dayname){
        int day = 0;
        switch(dayname){
            case("Monday"):
                day = 1;
                break;
            case("Tuesday"):
                day = 2;
                break;
            case("Wednesday"):
                day = 3;
                break;
            case("Thursday"):
                day = 4;
                break;
            case("Friday"):
                day = 5;
                break;
            case("Saturday"):
                day = 6;
                break;
            case("Sunday"):
                day = 7;
                break;
            default:
                break;
        }
        return day;
    }
    
    public int getDayOfMonth(String dayname, int week){
        int day = getNumberOfDay(dayname);
        int totalDays = day + (week-1)*7;
        LocalDateTime start = LocalDateTime.of(LocalDate.of(Year.now().getValue(), 1, 1), LocalTime.of(8, 00));
        return start.plusDays(totalDays).getDayOfMonth();
    }
}
