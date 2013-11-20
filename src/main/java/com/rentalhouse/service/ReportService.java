package com.rentalhouse.service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.pentaho.reporting.engine.classic.core.DataFactory;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfReportUtil;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.HtmlReportUtil;
import org.pentaho.reporting.engine.classic.core.modules.output.table.xls.ExcelReportUtil;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;
import org.springframework.stereotype.Service;

@Service("reportService")
public class ReportService extends AbstractReportGenerator{
	private MasterReport masterReport;
	private ResourceManager resourceManager;
	private Log log = LogFactoryImpl.getLog(getClass());
	
	public ReportService() {					
		super();
		resourceManager = new ResourceManager();
		resourceManager.registerDefaults();	
	}

	public MasterReport getMasterReport() {
		return masterReport;		
	}
	
	public void setReportDefinition(MasterReport masterReport){
		this.masterReport = masterReport;
	}
	
	@Override
	public MasterReport getReportDefinition() {
		return this.masterReport;
	}

	@Override
	public DataFactory getDataFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getReportParameters() {
		// TODO Auto-generated method stub
		return null;
	}		
	
	public void createReport(String reportName){						
		File pdfFile = new File(reportName + OutputType.PDF.getExtension());
		File xlsFile = new File(reportName + OutputType.EXCEL.getExtension());
		File htmlFile = new File(reportName + OutputType.HTML.getExtension());
		try {			
			Resource res = resourceManager.createDirectly(
					new URL("file:"+ reportName +".prpt"), MasterReport.class);
			log.info(res.getSource().getIdentifierAsString());
			log.info(res.getSource());
			MasterReport report = (MasterReport) res.getResource();
			setReportDefinition(report);			
			generateReport(OutputType.PDF, pdfFile);	
			generateReport(OutputType.EXCEL, xlsFile);	
			generateReport(OutputType.HTML, htmlFile);				
		} catch (Exception e) {
			log.error(e);
		}		
	}
	public void createReport(String reportName, OutputType outputType, HttpServletResponse response){	
		ResourceManager manager = new ResourceManager();
		manager.registerDefaults();		
		try {			
			Resource res = manager.createDirectly(new URL("file:" + reportName +".prpt"), MasterReport.class);
			log.info(res.getSource().getIdentifierAsString());
			log.info(res.getSource());	
			MasterReport report = (MasterReport) res.getResource();
			
			if (OutputType.PDF.equals(outputType)){
				PdfReportUtil.createPDF(report, response.getOutputStream());				
			} else if (OutputType.EXCEL.equals(outputType)){
				ExcelReportUtil.createXLS(report, response.getOutputStream());			
			} else if (OutputType.HTML.equals(outputType)){
				HtmlReportUtil.createStreamHTML(report, response.getOutputStream());
			}			
			response.setContentType(outputType.getContentType());
//			PdfReportUtil.createPDF(report, outputStream);  con esto solo funciona!!
//			setReportDefinition(report);			
//			generateReport(outputType, response.getOutputStream());		
		} catch (Exception e) {
			log.error(e);
		}		
	}

}
