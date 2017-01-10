package org.nanwarin.scrapdata;


import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ScrapCalPolyPomonaClassSchedule {

	public static void main(String[] args){
		
		Document searchResult = null;

		try {
			searchResult = getSearchResult();
			
		} catch (IOException e) {
			System.out.println("Error Search Classes"+ e);
			e.printStackTrace();
		}
		
		if(searchResult != null){
			translateData(searchResult);
		}

	}
	
	public static Document getSearchResult() throws IOException{
		Document result = null;
		String cppClassURL = "http://schedule.cpp.edu/";
		Connection.Response cppClass = Jsoup.connect(cppClassURL).method(Connection.Method.GET).execute();
		
		Document cppClassDoc = cppClass.parse();
		
		//Hidden Inputs
        Element viewState = cppClassDoc.select("input[name=__VIEWSTATE]").first();
        Element eventTarget = cppClassDoc.select("input[name=__EVENTTARGET]").first();
        Element eventArgument = cppClassDoc.select("input[name=__EVENTARGUMENT]").first();
        
        Element viewStateGenerator = cppClassDoc.select("input[name=__VIEWSTATEGENERATOR]").first();
        Element viewStateEncrypted = cppClassDoc.select("input[name=__VIEWSTATEENCRYPTED]").first();
        Element eventValidation = cppClassDoc.select("input[name=__EVENTVALIDATION]").first();
     
		
		Document document = Jsoup.connect(cppClassURL + "index.aspx")
							.data("cookieexists", "false")
							.data("__VIEWSTATE", viewState.attr("value"))
							.data("__EVENTTARGET", eventTarget.attr("value"))
							.data("__EVENTARGUMENT", eventArgument.attr("value"))
							.data("__VIEWSTATEGENERATOR", viewStateGenerator.attr("value"))
							.data("__VIEWSTATEENCRYPTED", viewStateEncrypted.attr("value"))
							.data("__EVENTVALIDATION", eventValidation.attr("value"))
							.data("ctl00$ContentPlaceHolder1$TermDDL", "2171") //Term
							.data("ctl00$ContentPlaceHolder1$ClassSubject", "CS") // Class Subject
							.data("ctl00$ContentPlaceHolder1$CatalogNumber", "") // Catalog Number
							.data("ctl00$ContentPlaceHolder1$CatalogNumberCHK", "") // Checkbox --> need to review this again
							.data("ctl00$ContentPlaceHolder1$Description", "") //Title
							.data("ctl00$ContentPlaceHolder1$CourseComponentDDL", "Any Component") // Course Component
							.data("ctl00$ContentPlaceHolder1$CourseAttributeDDL", "Any Attribute") // Course Attribute
							.data("ctl00$ContentPlaceHolder1$CourseCareerDDL", "Any Career") //Course Career
							.data("ctl00$ContentPlaceHolder1$InstModesDDL", "Any Mode") // Instruction Mode
							.data("ctl00$ContentPlaceHolder1$SessionDDL", "Any Session") //Session
							.data("ctl00$ContentPlaceHolder1$ClassDays$0", "checked") //Mon Class
							.data("ctl00$ContentPlaceHolder1$ClassDays$1", "checked") //Tue Class
							.data("ctl00$ContentPlaceHolder1$ClassDays$2", "checked") //Wed Class
							.data("ctl00$ContentPlaceHolder1$ClassDays$3", "checked") //Thur Class
							.data("ctl00$ContentPlaceHolder1$ClassDays$4", "checked") //Fri Class
							.data("ctl00$ContentPlaceHolder1$ClassDays$5", "checked") //Sat Class
							.data("ctl00$ContentPlaceHolder1$ClassDays$6", "checked") //Sun Class
							.data("ctl00$ContentPlaceHolder1$ClassDays$7", "checked") //TBD Class
							.data("ctl00$ContentPlaceHolder1$StartTime", "ANY") // Class strat time
							.data("ctl00$ContentPlaceHolder1$EndTime", "ANY") // Class end time		
							.data("ctl00$ContentPlaceHolder1$Instructor", "") // Instructor
							.data("ctl00$ContentPlaceHolder1$InstructorCHK", "") // Match Instructor
							.data("ctl00$ContentPlaceHolder1$StaffCHK", "") //Staff only
							.data("ctl00$ContentPlaceHolder1$SearchButton", "Search") //Search button
							.cookies(cppClass.cookies())
							.followRedirects(true)
							.post();
	
		System.out.println("Log -- document: " + document.toString());
		return result;
	}
	
	public static void translateData(Document doc){
		
	}
	
	
}
