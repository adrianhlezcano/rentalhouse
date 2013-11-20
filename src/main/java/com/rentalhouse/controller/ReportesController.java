package com.rentalhouse.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rentalhouse.form.ReportForm;
import com.rentalhouse.form.ReportListForm;
import com.rentalhouse.service.AbstractReportGenerator.OutputType;
import com.rentalhouse.service.ReportService;

@Controller()
@RequestMapping("/admin/reportes")
public class ReportesController {
	@Autowired
	@Qualifier("reportService")
	private ReportService reportService;
	@Autowired
	private ServletContext context;
	private Log _log = LogFactoryImpl.getLog(getClass());
	
	@RequestMapping(method=RequestMethod.GET)
	public String show(Model model){
		model.addAttribute("reportList", Arrays.asList(ReportListForm.values()));
		model.addAttribute("reportTypeList", Arrays.asList(OutputType.values()));
		model.addAttribute("reports", "");
		return "admin/reportes";
	}	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> processReport(
			@RequestBody ReportForm form ){
		String reportName = form.getReportName();
		List<ReportForm> reportFormList = Collections.emptyList();
		Map<String, Object> returnValue = new HashMap<String, Object>();
		_log.info(String.format("Generating report: name[%s]", reportName));
		try {			
			String reportPathDir = context.getRealPath("/resources/reports/" + reportName );
			reportService.createReport(reportPathDir);			
			Set<String> reportPaths = context.getResourcePaths("/resources/reports/");			
			reportFormList = new ArrayList<ReportForm>();		
			
			for (String reportPath : reportPaths) {
				String ext = reportPath.substring(reportPath.indexOf(".") + 1);
				
				if (reportPath.contains(reportName) && !ext.equals("prpt")){
					String rptName = reportName.concat("." + ext);
					reportFormList.add(new ReportForm(rptName, ext, reportPath));
				}				
			}				
			returnValue.put("status", "OK");
			returnValue.put("reports", reportFormList);
		} catch (Exception e) {
			returnValue.put("status", "NOK");
			_log.error(e);
		}		
		return returnValue;
	}

}
