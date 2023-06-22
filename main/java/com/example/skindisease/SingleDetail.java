package com.example.skindisease;

public class SingleDetail {
    String Name;
    String Description;
    String Symptoms;
    String Causes;
    String Treatement;

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setSymptoms(String symptoms) {
        Symptoms = symptoms;
    }

    public void setCauses(String causes) {
        Causes = causes;
    }

    public void setTreatement(String treatement) {
        Treatement = treatement;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getSymptoms() {
        return Symptoms;
    }

    public String getCauses() {
        return Causes;
    }

    public String getTreatement() {
        return Treatement;
    }

    public SingleDetail(String name, String description, String symptoms, String causes, String treatement) {
        Name = name;
        Description = description;
        Symptoms = symptoms;
        Causes = causes;
        Treatement = treatement;
    }


}
