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
public class MaintenanceActivity {

    private int id;
    private Site site;
    private String typology;
    private String description;
    private int interventionTime;
    private boolean interruptible;
    private String materials;
    private int week;
    private String workspaceNotes;
    private Procedure procedure;

    //constructor used by the business layer, without the id (the id is incremented automatically in the database)
    public MaintenanceActivity(Site site, String typology, String description, int interventionTime,
            boolean interruptible, String materials, int week, String workspaceNotes) {

        this.site = site;
        this.typology = typology;
        this.description = description;
        this.interventionTime = interventionTime;
        this.interruptible = interruptible;
        this.materials = materials;
        this.week = week;
        this.workspaceNotes = workspaceNotes;
    }

    //constructor used by the data access layer, for activity with procedure associated
    public MaintenanceActivity(int id, Site site, String typology, String description, int interventionTime,
            boolean interruptible, String materials, Procedure procedure, int week, String workspaceNotes) {

        this.id = id;
        this.site = site;
        this.typology = typology;
        this.description = description;
        this.interventionTime = interventionTime;
        this.interruptible = interruptible;
        this.materials = materials;
        this.procedure = procedure;
        this.week = week;
        this.workspaceNotes = workspaceNotes;
    }

    //constructor used by the data access layer, for activity without procedure associated
    public MaintenanceActivity(int id, Site site, String typology, String description, int interventionTime,
            boolean interruptible, String materials, int week, String workspaceNotes) {

        this.id = id;
        this.site = site;
        this.typology = typology;
        this.description = description;
        this.interventionTime = interventionTime;
        this.interruptible = interruptible;
        this.materials = materials;
        this.week = week;
        this.workspaceNotes = workspaceNotes;
    }

    public int getId() {
        return id;
    }

    public Site getSite() {
        return site;
    }

    public String getTypology() {
        return typology;
    }

    public String getDescription() {
        return description;
    }

    public int getInterventionTime() {
        return interventionTime;
    }

    public boolean isInterruptible() {
        return interruptible;
    }

    public String getMaterials() {
        return materials;
    }

    public int getWeek() {
        return week;
    }

    public String getWorkspaceNotes() {
        return workspaceNotes;
    }

    public void setWorkspaceNotes(String workspaceNotes) {
        this.workspaceNotes = workspaceNotes;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

}
