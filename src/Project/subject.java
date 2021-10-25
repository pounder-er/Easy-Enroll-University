/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.time.LocalTime;

/**
 *
 * @author chain
 */
public class subject {

    private final String subjectname;
    private final int credit;
    private final String daylec;
    private final String daylab;
    private final Integer Glec;
    private final Integer Glab;
    private final LocalTime timelec1;
    private final LocalTime timelec2;
    private final LocalTime timelab1;
    private final LocalTime timelab2;
    private final String professor;

    public subject(String subjectname, int credit, String daylec, String daylab, Integer Glec, Integer Glab, LocalTime timelec1, LocalTime timelec2, LocalTime timelab1, LocalTime timelab2, String professor) {
        this.subjectname = subjectname;
        this.credit = credit;
        this.daylec = daylec;
        this.daylab = daylab;
        this.Glec = Glec;
        this.Glab = Glab;
        this.timelec1 = timelec1;
        this.timelec2 = timelec2;
        this.timelab1 = timelab1;
        this.timelab2 = timelab2;
        this.professor = professor;
    }

    public String getProfessor() {
        return professor;
    }
    
    public void print(){
        System.out.println(this.subjectname + " " + this.credit + " " + this.daylec + " " + this.timelec1 + " " + this.timelec2 + " " + this.daylab + " " + this.timelab1 + " " + this.timelab2 );
    }

    public String getSubjectname() {
        return subjectname;
    }

    public String getDaylec() {
        return daylec;
    }

    public String getDaylab() {
        return daylab;
    }

    public Integer getGlec() {
        return Glec;
    }

    public Integer getGlab() {
        return Glab;
    }

    public int getCredit() {
        return credit;
    }

    public LocalTime getTimelec1() {
        return timelec1;
    }

    public LocalTime getTimelec2() {
        return timelec2;
    }

    public LocalTime getTimelab1() {
        return timelab1;
    }

    public LocalTime getTimelab2() {
        return timelab2;
    }

}
