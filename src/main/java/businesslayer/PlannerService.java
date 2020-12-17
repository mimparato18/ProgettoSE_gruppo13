/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import dataaccesslayer.*;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;

/**
 *
 * @author marku
 */
public class PlannerService {

    private Planner planner;
    private MaintenanceActivityDaoImpl activityDao;
    private TypologyDaoImpl typologyDao;
    private SiteDaoImpl siteDao;
    private UserDaoImpl userDao;
    private MaintenanceProcedureDaoImpl procedureDao;
    private MaintainerCompetenceDaoImpl maintainerDao;
    private MaintainerAvailabilityDaoImpl availDao;

    public PlannerService(Planner planner) {
        activityDao = new MaintenanceActivityDaoImpl();
        typologyDao = new TypologyDaoImpl();
        siteDao = new SiteDaoImpl();
        userDao = new UserDaoImpl();
        procedureDao = new MaintenanceProcedureDaoImpl();
        maintainerDao = new MaintainerCompetenceDaoImpl();
        availDao = new MaintainerAvailabilityDaoImpl();
        this.planner = planner;
    }

    //Activities
    /**
    * Add an activity in the database calling the corresponding DAO method
    *
    * @param    site            site composed by branchoffice and department
    * @param    typology        typology of maintenance activity
    * @param    description     desciption of the maintenance activity
    * @param    hours           hours estimated for the activity
    * @param    minutes         minutes estimated for the activity
    * @param    interruptible   describes if the activity can be interruptible
    * @param    materials       materials required by activity
    * @param    week            number of week for which the activity is planned
    * @param    workspaceNotes  notes for the activity
    * @return true if the insert was successful, false otherwise  
     */
    public boolean addActivity(String site, String typology, String description,
            int hours, int minutes, boolean interruptible, String materials, String week, String workspaceNotes) {

        return activityDao.insertActivity(planner.createActivity(new Site(site.split(" - ")[0], site.split(" - ")[1]), typology, description,
                (hours * 60 + minutes), interruptible, materials, Integer.parseInt(week), workspaceNotes));
    }

    /**
    * Update an activity in the database calling the corresponding DAO method
    *
    * @param    id                  id of the activity             
    * @param    site                site composed by branchoffice and department
    * @param    typology            typology of maintenance activity
    * @param    description         desciption of the maintenance activity
    * @param    interventionTime    hours estimated for the activity
    * @param    interruptible       describes if the activity can be interruptible
    * @param    materials           materials required by activity
    * @param    week                number of week for which the activity is planned
    * @param    workspaceNotes      notes for the activity  
    * @return true if the update was successful, false otherwise  
     */
    public boolean updateActivity(int id, Site site, String typology, String description,
            int interventionTime, boolean interruptible, String materials, int week, String workspaceNotes) {

        return activityDao.updateActivity(planner.createActivity(id, site, typology, description,
                interventionTime, interruptible, materials, week, workspaceNotes));
    }

    /**
    * Remove an activity from the database calling the corresponding DAO method
    *
    * @param  id    id of the activity to delete
    * @return true if the deletion was successful, false otherwise  
     */
    public boolean deleteActivity(int id) {

        return activityDao.deleteActivity(id);
    }

    /**
    * Get all the activities calling the corresponding DAO method
    *
    * 
    * @return ArrayList of MaintenanceActivity
     */
    public ArrayList<MaintenanceActivity> viewActivities() {
        ArrayList<MaintenanceActivity> list = activityDao.getAllActivities();
        for (var item : list) {
            if (item.getProcedure() != null) {
                item.getProcedure().setCompetencies(procedureDao.getCompetenciesByName(item.getProcedure().getName()));
            }
        }
        return list;
    }

    /**
    * Get all the activities in a specified week calling the corresponding DAO method
    *
    * @param  week  the week in which the maintenance activity needs to be performed
    * @return ArrayList of MaintenanceActivity with the same week
     */
    public ArrayList<MaintenanceActivity> getActivitiesByWeek(int week) {

        ArrayList<MaintenanceActivity> list = activityDao.getActivitiesByWeek(week);
        for (var item : list) {
            if (item.getProcedure() != null) {
                item.getProcedure().setCompetencies(procedureDao.getCompetenciesByName(item.getProcedure().getName()));
            }
        }
        return list;
    }

    /**
    * Get the id of the next activity to create
    *
    *   
    * @return a string representing the id of the activity during its creation
     */
    public String getNextId() {

        try {
            return Integer.toString(activityDao.nextId());
        } catch (InterruptedException | SQLException ex) {
            return "err";
        }

    }

    //Sites
    /**
    * Get a list of all the sites in the database
    *
    * 
    * @return ArrayList of all sites represented by a string
     */
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
    /**
    * Get a list of all the maintenance typologies in the database
    *
    * @return ArrayList of all typologies
     */
    public ArrayList<String> getAllTypologies() {
        return typologyDao.getAllTypologies();
    }

    //Maintainer availabilities
    /**
    * Get a list of maintainers, their availability during the week and the numbers 
    * of skills the maintainer has compared to the required competencies
    *
    * @param    activityId  id of the selected activity, used to obtain its competencies
    * @param    week        week in which the activity needs to be performed
    * @return an ArrayList of MaintainerAvailabilityDto, containing all the maintainers and their availability during the week
     */
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
                    matchedSkills = "N/A";  //if the DAO returns null, it means that no procedure is associated with the activity. 
                }                           //
                                          
                                           
                if (availMaint != null) {
                    /*
                        if the DAO returns null, it means that the tuple wasn't found. The idea is that the db table is empty
                        at the start and is filled when the activity starts to be assigned. So the null from the DAO means 
                        that all the maintainers are available
                    */
                    
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

    /**
    * Get how many skills the maintainer has compared to the required ones
    *
    * @param  compAct       ArrayList of competencies required by the maintenance activity
    * @param  compMaint     ArrayList of competencies possessed by the maintainer
    * @return   a String representing how many required competencies the maintainer has
     */
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

    /**
    * Get the percentage of availability in a day
    *
    * @param  hours     array of integer representing the availability inside the 7 work hours
    * @return the percentage of availability of a maintainer in one day
     */
    private String getAvailabilityPercentage(int[] hours) {
        int sum = 0;
        for (var hour : hours) {
            sum += hour;
        }
        return String.valueOf(sum * 100 / 420) + "%";
    }

    /**
    * Get a list of the availability for the specified maintainer during 
    * the specified week and day
    *
    * @param  week      week in which the selected activity needs to be performed 
    * @param  username  username of the maintainer to which i want to assign the activity
    * @param  dayname   name of the day in which the activity needs to be performed
    * @return an ArrayList of the availability in the work hours for the maintainer in the specified week and day
     */
    public ArrayList<String> getAvailabilityByWeekAndDay(int week, String username, String dayname) {
        ArrayList<String> availabilities = new ArrayList<>();
        MaintainerAvailability ava = availDao.getMaintainerAvailabilitiesByWeekAndDay(week, username, getNumberOfDay(dayname));
        if (ava == null) {
            for (int i = 0; i < 7; i++) {
                availabilities.add("60 min");
            }
            return availabilities;
        } else {
            for (int i = 0; i < 7; i++) {
                availabilities.add(ava.getHours()[i] + " min");
            }
            return availabilities;
        }

    }

    /**
    * Simple function to translate the name of a day into a int representing its position in the week 
    *
    * @param  dayname name of the weekday
    * @return the position of the day inside the week
     */
    private int getNumberOfDay(String dayname) {
        int day = 0;
        switch (dayname) {
            case ("Monday"):
                day = 1;
                break;
            case ("Tuesday"):
                day = 2;
                break;
            case ("Wednesday"):
                day = 3;
                break;
            case ("Thursday"):
                day = 4;
                break;
            case ("Friday"):
                day = 5;
                break;
            case ("Saturday"):
                day = 6;
                break;
            case ("Sunday"):
                day = 7;
                break;
            default:
                break;
        }
        return day;
    }

    /**
    * Get the number of day in month based on the week and weekday
    *
    * @param    dayname      name of the day of the week
    * @param    week         week which the activity needs to be performed
    * @return   the number of day in month
     */
    public int getDayOfMonth(String dayname, int week) {
        int day = getNumberOfDay(dayname);
        int totalDays = day + (week - 1) * 7;
        LocalDateTime start = LocalDateTime.of(LocalDate.of(Year.now().getValue(), 1, 1), LocalTime.of(8, 00));
        return start.plusDays(totalDays).getDayOfMonth();
    }
}
