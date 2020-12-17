/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StubClass;

import businesslayer.MaintenanceActivity;
import businesslayer.Procedure;
import businesslayer.Site;
import dataaccesslayer.MaintenanceActivityDao;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class MaintenanceActivityDaoImplStub implements MaintenanceActivityDao {

    static int id = 1;
    static ArrayList<String> allMaintenanceActivity;
    //id-materials-typology-estimatedTime-activityDescription-branchOffice-department-procedureName-week-interruptible-workspacenotes

    public MaintenanceActivityDaoImplStub() {
        allMaintenanceActivity = new ArrayList<>();
    }

    public boolean isActivity(int id) {
        int checkID = 0;

        for (int i = 0; i < allMaintenanceActivity.size(); i++) {
            String string = allMaintenanceActivity.get(i);
            String[] parts = string.split("-");
            checkID = Integer.parseInt(parts[0]);
        }
        return (checkID != 0);
    }

    @Override
    public boolean insertActivity(MaintenanceActivity activity) {

        String act = id + "-" + activity.getMaterials() + "-" + activity.getTypology() + "-" + activity.getInterventionTime() + "-"
                + activity.getDescription() + "-" + activity.getSite().getBranchOffice() + "-" + activity.getSite().getDepartment()
                + "-" + activity.getWeek() + "-" + activity.isInterruptible() + "-" + activity.getWorkspaceNotes();
        allMaintenanceActivity.add(act);
        id++;
        return true;

    }

    @Override
    public boolean updateActivity(MaintenanceActivity activity) {

        if (this.isActivity(activity.getId())) {
            for (int i = 0; i < allMaintenanceActivity.size(); i++) {
                String string = allMaintenanceActivity.get(i);
                String[] parts = string.split("-");
                if (activity.getId() == Integer.parseInt(parts[0])) {
                    String act = activity.getId() + "-" + activity.getMaterials() + "-" + activity.getTypology() + "-" + activity.getInterventionTime() + "-"
                            + activity.getDescription() + "-" + activity.getSite().getBranchOffice() + "-" + activity.getSite().getDepartment()
                            + "-" + activity.getWeek() + "-" + activity.isInterruptible() + "-" + activity.getWorkspaceNotes();
                    allMaintenanceActivity.remove(i);
                    allMaintenanceActivity.add(i, act);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteActivity(int id) {

        if (this.isActivity(id)) {
            for (int i = 0; i < allMaintenanceActivity.size(); i++) {
                String string = allMaintenanceActivity.get(i);
                String[] parts = string.split("-");
                if (id == Integer.parseInt(parts[0])) {
                    allMaintenanceActivity.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<MaintenanceActivity> getAllActivities() {
        String materials, typology, activityDescription, branchOffice, department, workspaceNotes, procedureName;
        int idCurrent, interventionTime, week;
        boolean interruptible;
        ArrayList<MaintenanceActivity> activitiesList = new ArrayList<>();

        for (int i = 0; i < allMaintenanceActivity.size(); i++) {
            String string = allMaintenanceActivity.get(i);
            String[] parts = string.split("-");

            idCurrent = Integer.parseInt(parts[0]);
            materials = parts[1];
            typology = parts[2];
            interventionTime = Integer.parseInt(parts[3]);
            activityDescription = parts[4];
            branchOffice = parts[5];
            department = parts[6];
            week = Integer.parseInt(parts[7]);
            interruptible = Boolean.parseBoolean(parts[8]);
            workspaceNotes = parts[9];
            procedureName = null;//parts[10];

            Site site = new Site(branchOffice, department);

            if (procedureName != null) {
                activitiesList.add(new MaintenanceActivity(idCurrent, site, typology, activityDescription, interventionTime, interruptible, materials, new Procedure(procedureName, null), week, workspaceNotes));
            } else {
                activitiesList.add(new MaintenanceActivity(idCurrent, site, typology, activityDescription, interventionTime, interruptible, materials, week, workspaceNotes));
            }
        }
        return activitiesList;
    }

    @Override
    public MaintenanceActivity getActivityById(int selectedId) {
        String materials, typology, activityDescription, branchOffice, department, workspaceNotes, procedureName;
        int idCurrent, interventionTime, week;
        boolean interruptible;
        MaintenanceActivity activity = null;

        for (int i = 0; i < allMaintenanceActivity.size(); i++) {
            String string = allMaintenanceActivity.get(i);
            String[] parts = string.split("-");
            if (selectedId == Integer.parseInt(parts[0])) {
                idCurrent = Integer.parseInt(parts[0]);
                materials = parts[1];
                typology = parts[2];
                interventionTime = Integer.parseInt(parts[3]);
                activityDescription = parts[4];
                branchOffice = parts[5];
                department = parts[6];
                procedureName = parts[7];
                week = Integer.parseInt(parts[8]);
                interruptible = Boolean.parseBoolean(parts[9]);
                workspaceNotes = parts[10];

                Site site = new Site(branchOffice, department);

                if (procedureName != null) {
                    activity = new MaintenanceActivity(idCurrent, site, typology, activityDescription, interventionTime, interruptible, materials, new Procedure(procedureName, null), week, workspaceNotes);
                } else {
                    activity = new MaintenanceActivity(idCurrent, site, typology, activityDescription, interventionTime, interruptible, materials, week, workspaceNotes);
                }
            }
        }
        return activity;
    }

    @Override
    public ArrayList<MaintenanceActivity> getActivitiesByWeek(int selectedWeek) {
        String materials, typology, activityDescription, branchOffice, department, workspaceNotes, procedureName;
        int idCurrent, interventionTime, week;
        boolean interruptible;
        ArrayList<MaintenanceActivity> activitiesList = new ArrayList<>();

        for (int i = 0; i < allMaintenanceActivity.size(); i++) {
            String string = allMaintenanceActivity.get(i);
            String[] parts = string.split("-");
            if (selectedWeek == Integer.parseInt(parts[8])) {
                idCurrent = Integer.parseInt(parts[0]);
                materials = parts[1];
                typology = parts[2];
                interventionTime = Integer.parseInt(parts[3]);
                activityDescription = parts[4];
                branchOffice = parts[5];
                department = parts[6];
                procedureName = parts[7];
                week = Integer.parseInt(parts[8]);
                interruptible = Boolean.parseBoolean(parts[9]);
                workspaceNotes = parts[10];

                Site site = new Site(branchOffice, department);

                if (procedureName != null) {
                    activitiesList.add(new MaintenanceActivity(idCurrent, site, typology, activityDescription, interventionTime, interruptible, materials, new Procedure(procedureName, null), week, workspaceNotes));
                } else {
                    activitiesList.add(new MaintenanceActivity(idCurrent, site, typology, activityDescription, interventionTime, interruptible, materials, week, workspaceNotes));
                }
            }
        }
        return activitiesList;
    }

    @Override
    public int nextId() {
        return id;
    }

}
