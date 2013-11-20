package com.rentalhouse.form;


public class ReportForm {	
	private String reportName;
	private String format;
	private String reportPath;
	
	public ReportForm() {
		// TODO Auto-generated constructor stub
	}	

	public ReportForm(String reportName, String format, String reportPath) {
		super();
		this.reportName = reportName;
		this.format = format;
		this.reportPath = reportPath;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getReportPath() {
		return reportPath;
	}
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
}