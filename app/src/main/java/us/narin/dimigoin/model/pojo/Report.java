package us.narin.dimigoin.model.pojo;

/**
 * Created by Seungwoo on 2015. 12. 24..
 */
public class Report {

    String reportSubject;
    String reportTeacher;
    String reportDeadDate;
    Boolean reportIsSubmit;

    public Report(String reportSubject, String reportTeacher, String reportDeadDate, Boolean reportIsSubmit){
        this.reportSubject = reportSubject;
        this.reportTeacher = reportTeacher;
        this.reportDeadDate = reportDeadDate;
        this.reportIsSubmit = reportIsSubmit;
    }

    public String getReportSubject() {
        return reportSubject;
    }

    public String getReportDeadDate() {
        return reportDeadDate;
    }

    public String getReportTeacher() {
        return reportTeacher;
    }

    public Boolean getReportIsSubmit() {
        return reportIsSubmit;
    }
}
