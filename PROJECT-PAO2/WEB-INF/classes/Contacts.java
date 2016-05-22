import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacts extends HttpServlet {
  
	private static ObservableList<Contact> list;

  public void init() throws ServletException
  {
      // Do required initialization
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException

  {
	  //For Add Contact
	String firstn=request.getParameter("addfirstn");
	String lastn=request.getParameter("addlastn");
	String mobile=request.getParameter("addphone");
	String fix=request.getParameter("addfix");
	String email=request.getParameter("addemail");
	String county=request.getParameter("addcounty");
	String city=request.getParameter("addcity");
	String address=request.getParameter("addaddress");
	String zipcode=request.getParameter("addzipcode");
	
	
		// For Edit Contact
	String firstn2=request.getParameter("editfirstn");
	String lastn2=request.getParameter("editlastn");
	String mobile2=request.getParameter("editphone");
	String fix2=request.getParameter("editfix");
	String email2=request.getParameter("editemail");
	String county2=request.getParameter("editcounty");
	String city2=request.getParameter("editcity");
	String address2=request.getParameter("editaddress");
	String zipcode2=request.getParameter("editzipcode");
	String old_phone=request.getParameter("oldphone");
	
	//For search
	String search=request.getParameter("search-contacts");
	 
	if (firstn!=null) {
	try{
		ConnectDB.addContact(firstn,lastn,mobile,fix,email,county,city,address,zipcode);
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  
	
	if (firstn2!=null) {
	try{
		ConnectDB.editContact(firstn2,lastn2,mobile2,fix2,email2,county2,city2,address2,zipcode2,old_phone);
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  
	
	
	int op_del=-1;
	
try {
		op_del=Integer.parseInt(request.getParameter("idel"));
} catch (NumberFormatException e) {
  // do something
}	  

		int currx=0;
		try {
		currx=Integer.parseInt(request.getParameter("curr"));
} catch (NumberFormatException e) {
  // do something
}
	  //DataBase 
		try {
			new ConnectDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		char current='1';
		
		//Lista DB
		
	try{
	list=FXCollections.observableArrayList(ConnectDB.getList());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      PrintWriter out = response.getWriter();
	out.println("<html>");
	out.println("<head>");
	out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js'></script>");
	out.println("<script src='http://code.jquery.com/ui/1.9.2/jquery-ui.js'></script>");
	//out.println("<script type='text/javascript' src='js/list.js'></script>");
	out.println("<script type='text/javascript' src='js/show-contact.js'></script>");
	out.println("<link rel='stylesheet' href='style.css'>");
	out.println("<title>Contacts</title>");
	out.println("</head>");
	out.println("<body>");
    out.println("<div class='pane-left'>");
	
	
	if (search!=null) {
		list=FXCollections.observableArrayList(sortContacts(search));
		if (list.isEmpty())
			 out.println("<script>alert('Nu s-a gasit niciun rezultat! Incercati din nou cu alt sir de caractere.'); window.location='http://localhost:8080/PROJECT-PAO2/Contacts';</script>");
		else out.println("<script>alert('S-au gasit "+list.size()+" contacte.');</script>");
	}
	
	
	if (!list.isEmpty()) {
	
	out.println("<div class='search'>");
	out.println("<form>");
	out.println("<input class='search' id='search-contacts' type='text' name='search-contacts' placeholder='Search All Contacts'>");
	out.println("</form>");
	out.println("</div>");
	out.println("<div id='list1'>");
	
	for (int i=0;i<list.size();i++) {
		if (current=='1') {
		current=list.get(i).getLastn().charAt(0);
	out.println("<div class='ioslist-group-container'>");
	out.println("<div class='ioslist-group-header'>"+current+"</div>");
	out.println("<ul>");
		} else if (current!=list.get(i).getLastn().charAt(0)) {
			out.println("</ul>");
			out.println("</div>");
			current=list.get(i).getLastn().charAt(0);
			out.println("<div class='ioslist-group-container'>");
			out.println("<div class='ioslist-group-header'>"+current+"</div>");
			out.println("<ul>");
		}
	out.println("<a href='#'><li class='list' id='"+i+"'>"+list.get(i).getLastn()+" "+list.get(i).getFirstn()+"</li></a>");

	}
	out.println("</ul>");
	out.println("</div>");
	out.println("</div>");
	out.println("</div>");
	out.println("<div class='pane-right'>");
	
	//Statement 1
	out.println("<div class='pane-right-top' id='pane-right-top1' name='pane-right-top1'>");
	out.println("<label id='add' name='add' class='add'>Add</label>");
	out.println("<label id='edit' name='edit' class='edit'>Edit</label>");
	out.println("<label id='del' name='del' class='del'>Delete</label>");
	out.println("</div>");
	
	//Statement 2
	out.println("<div class='pane-right-top' id='pane-right-top2' name='pane-right-top2'>");
	out.println("<label id='done' name='done' class='done'>Done</label>");
	out.println("<label id='cancel' name='cancel' class='cancel'>Cancel</label>");
	out.println("</div>");
	
	//Statement 3
	out.println("<div class='pane-right-top' id='pane-right-top3' name='pane-right-top3'>");
	out.println("<label id='done2' name='done2' class='done2'>Done</label>");
	out.println("<label id='cancel' name='cancel' class='cancel'>Cancel</label>");
	out.println("</div>");
	
	
	//Contact details
	out.println("<div class='pane-right-bottom' id='details' name='details'>");
	
	
	//Contact name
	out.println("<div class='result'>");
	out.println("<img src='http://localhost:8080/PROJECT-PAO2/img/pic.png' style='margin-right:10px;vertical-align:middle' />");
	out.println("<span id='name'>");
	out.println(list.get(currx).getLastn()+" "+list.get(currx).getFirstn());
	out.println("</span>");
	
	//Contact phone
	out.println("<div class='info'>");
	out.println("<label class='label-info'>Mobile:</label>");
	out.println("<span id='mobile'>");
	out.println(list.get(currx).getPhone());
	out.println("</span>");
	out.println("</div>");
	
	//Contact fix
	out.println("<div class='info'>");
	out.println("<label class='label-info'>Fix:</label>");
	out.println("<span id='fix'>");
	out.println(list.get(currx).getFix());
	out.println("</span>");
	out.println("</div>");

	//Contact email
	out.println("<div class='info'>");
	out.println("<label class='label-info'>Email:</label>");
	out.println("<span id='email'>");
	out.println(list.get(currx).getMail());
	out.println("</span>");
	out.println("</div>");
	
	//Contact county
	out.println("<div class='info'>");
	out.println("<label class='label-info'>County:</label>");
	out.println("<span id='county'>");
	out.println(list.get(currx).getRegion());
	out.println("</span>");
	out.println("</div>");
	
	//Contact city
	out.println("<div class='info'>");
	out.println("<label class='label-info'>City:</label>");
	out.println("<span id='city'>");
	out.println(list.get(currx).getCity());
	out.println("</span>");
	out.println("</div>");
	
	//Contact address
	out.println("<div class='info'>");
	out.println("<label class='label-info'>Address:</label>");
	out.println("<span id='address'>");
	out.println(list.get(currx).getAddress());
	out.println("</span>");
	out.println("</div>");
	
	//Contact zipcode
	out.println("<div class='info'>");
	out.println("<label class='label-info'>Zipcode:</label>");
	out.println("<span id='zipcode'>");
	out.println(list.get(currx).getZipcode());
	out.println("</span>");
	out.println("</div>");
	
	
	out.println("</div>");
		out.println("</div>");
	
	
	
	//Contact Add
	out.println("<div class='pane-right-bottom' id='contact_add' name='contact_add'><form id='form_add'>");
	
	//Contact first name
	out.println("<div class='info'>");
	out.println("<label class='label-input'>First:</label>");
	out.println("<input aria-label='First Name' class='field' type='text' name='addfirstn' id='addfirstn' value='' spellcheck='false' maxlength='100' placeholder='First Name'>");
	out.println("</div>");
	
	//Contact last name
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Last:</label>");
	out.println("<input aria-label='Last Name' class='field' type='text' name='addlastn' id='addlastn' value='' spellcheck='false' maxlength='100' placeholder='Last Name'>");
	out.println("</div>");
	
	//Contact phone
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Mobile:</label>");
	out.println("<input aria-label='Phone' class='field' type='text' name='addphone' id='addphone' value='' spellcheck='false' maxlength='100' placeholder='Phone'>");
	out.println("</div>");
	
	//Contact fix
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Fix:</label>");
	out.println("<input aria-label='Fix' class='field' type='text' name='addfix' id='addfix' value='' spellcheck='false' maxlength='100' placeholder='Fix'>");
	out.println("</div>");

	//Contact email
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Email:</label>");
	out.println("<input aria-label='Email' class='field' type='text' name='addemail' id='addemail' value='' spellcheck='false' maxlength='100' placeholder='Email'>");
	out.println("</div>");
	
	//Contact county
	out.println("<div class='info'>");
	out.println("<label class='label-input'>County:</label>");
	out.println("<input aria-label='County' class='field' type='text' name='addcounty' id='addcounty' value='' spellcheck='false' maxlength='100' placeholder='County'>");
	out.println("</div>");
	
	//Contact city
	out.println("<div class='info'>");
	out.println("<label class='label-input'>City:</label>");
	out.println("<input aria-label='City' class='field' type='text' name='addcity' id='addcity' value='' spellcheck='false' maxlength='100' placeholder='City'>");
	out.println("</div>");
	
	//Contact address
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Address:</label>");
	out.println("<input aria-label='Address' class='field' type='text' name='addaddress' id='addaddress' value='' spellcheck='false' maxlength='100' placeholder='Address'>");
	out.println("</div>");
	
	//Contact zipcode
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Zipcode:</label>");
	out.println("<input aria-label='ZipCode' class='field' type='text' name='addzipcode' id='addzipcode' value='' spellcheck='false' maxlength='100' placeholder='Zipcode'>");
	out.println("</div>");
	
	out.println("</form></div>");
	
		
	//Contact Edit
	out.println("<div class='pane-right-bottom' id='contact_edit' name='contact_edit'><form id='form_edit'>");
	
	//Contact first name
	out.println("<div class='info'>");
	out.println("<label class='label-input'>First:</label>");
	out.println("<span id='mobile'>");
	out.println("<input aria-label='First Name' class='field' type='text' name='editfirstn' id='editfirstn' value='"+list.get(currx).getFirstn()+"' spellcheck='false' maxlength='100'>");
	out.println("</span>");
	out.println("</div>");
	
	//Contact last name
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Last:</label>");
	out.println("<span id='mobile'>");
	out.println("<input aria-label='Last Name' class='field' type='text' name='editlastn' id='editlastn' value='"+list.get(currx).getLastn()+"' spellcheck='false' maxlength='100'>");
	out.println("</span>");
	out.println("</div>");
	
	//Contact phone
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Mobile:</label>");
	out.println("<span id='mobile'>");
	out.println("<input aria-label='Phone' class='field' type='text' name='editphone' id='editphone' value='"+list.get(currx).getPhone()+"' spellcheck='false' maxlength='100'>");
	out.println("</span>");
	out.println("</div>");
	
	//Contact fix
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Fix:</label>");
	out.println("<span id='fix'>");
	out.println("<input aria-label='Fix' class='field' type='text' name='editfix' id='editfix' value='"+list.get(currx).getFix()+"' spellcheck='false' maxlength='100'>");
	out.println("</span>");
	out.println("</div>");

	//Contact email
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Email:</label>");
	out.println("<span id='email'>");
	out.println("<input aria-label='Email' class='field' type='text' name='editemail' id='editemail' value='"+list.get(currx).getMail()+"' spellcheck='false' maxlength='100'>");
	out.println("</span>");
	out.println("</div>");
	
	//Contact county
	out.println("<div class='info'>");
	out.println("<label class='label-input'>County:</label>");
	out.println("<span id='county'>");
	out.println("<input aria-label='County' class='field' type='text' name='editcounty' id='editcounty' value='"+list.get(currx).getRegion()+"' spellcheck='false' maxlength='100'>");
	out.println("</span>");
	out.println("</div>");
	
	//Contact city
	out.println("<div class='info'>");
	out.println("<label class='label-input'>City:</label>");
	out.println("<span id='city'>");
	out.println("<input aria-label='City' class='field' type='text' name='editcity' id='editcity' value='"+list.get(currx).getCity()+"' spellcheck='false' maxlength='100'>");
	out.println("</span>");
	out.println("</div>");
	
	//Contact address
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Address:</label>");
	out.println("<span id='address'>");
	out.println("<input aria-label='Address' class='field' type='text' name='editaddress' id='editaddress' value='"+list.get(currx).getAddress()+"' spellcheck='false' maxlength='100'>");
	out.println("</span>");
	out.println("</div>");
	
	//Contact zipcode
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Zipcode:</label>");
	out.println("<span id='zipcode'>");
	out.println("<input aria-label='ZipCode' class='field' type='text' name='editzipcode' id='editzipcode' value='"+list.get(currx).getZipcode()+"' spellcheck='false' maxlength='100'>");
	out.println("</span>");
	out.println("</div>");
	
	//Old phone
	out.println("<input aria-label='ZipCode' class='field' type='text' name='oldphone' id='oldphone' value='' spellcheck='false' maxlength='100' style='visibility: hidden;' >");
	
	out.println("</form></div>");
	
	
	
	//Delete Contact
	
		if (op_del!=-1) {
			try{
		ConnectDB.deleteContact(list.get(op_del).getPhone());
		out.println("<script>	window.location='http://localhost:8080/PROJECT-PAO2/Contacts';</script>");
			} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		}
	
	
	out.println("<form id='form'><input id='curr' name='curr' type='number' style='visibility: hidden;' value='"+currx+"' /></form>");
	out.println("<form id='formdel'><input id='idel' name='idel' type='number' value='0' style='visibility: hidden;' /></form>");
	out.println("</div>");
	out.println("</div>");
	out.println("<script src='js/jquery.min.js'></script>");
	out.println("<script src='js/jquery.ioslist.js'></script>");
	out.println("<script>");
	out.println("$(function(){");
	out.println("$('#list1').ioslist();");
	out.println("});");
	out.println(" </script>");
	
	} else
	{
	out.println("<div id='list1'>");
	out.println("<div class='ioslist-group-container'><ul style='list-style-type:none;'><li><center>No contacts</center></li>");
	out.println("</ul>");
	out.println("</div>");
	out.println("</div>");
	out.println("</div>");
	
	out.println("<div class='pane-right'>");
	
	//Statement 1
	out.println("<div class='pane-right-top' id='pane-right-top1' name='pane-right-top1'>");
	out.println("<label id='add' name='add' class='add'>Add</label>");
	out.println("</div>");
	
	//Statement 2
	out.println("<div class='pane-right-top' id='pane-right-top2' name='pane-right-top2'>");
	out.println("<label id='done' name='done' class='done'>Done</label>");
	out.println("<label id='cancel' name='cancel' class='cancel'>Cancel</label>");
	out.println("</div>");
	
	
	
	//Contact details
	out.println("<div class='pane-right-bottom' id='details' name='details'></div>");
	
	
	
	
	
	
	//Contact Add
	out.println("<div class='pane-right-bottom' id='contact_add' name='contact_add'><form id='form_add'>");
	
	//Contact first name
	out.println("<div class='info'>");
	out.println("<label class='label-input'>First:</label>");
	out.println("<input aria-label='First Name' class='field' type='text' name='addfirstn' id='addfirstn' value='' spellcheck='false' maxlength='100'>");
	out.println("</div>");
	
	//Contact last name
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Last:</label>");
	out.println("<input aria-label='Last Name' class='field' type='text' name='addlastn' id='addlastn' value='' spellcheck='false' maxlength='100'>");
	out.println("</div>");
	
	//Contact phone
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Mobile:</label>");
	out.println("<input aria-label='Phone' class='field' type='text' name='addphone' id='addphone' value='' spellcheck='false' maxlength='100'>");
	out.println("</div>");
	
	//Contact fix
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Fix:</label>");
	out.println("<input aria-label='Fix' class='field' type='text' name='addfix' id='addfix' value='' spellcheck='false' maxlength='100'>");
	out.println("</div>");

	//Contact email
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Email:</label>");
	out.println("<input aria-label='Email' class='field' type='text' name='addemail' id='addemail' value='' spellcheck='false' maxlength='100'>");
	out.println("</div>");
	
	//Contact county
	out.println("<div class='info'>");
	out.println("<label class='label-input'>County:</label>");
	out.println("<input aria-label='County' class='field' type='text' name='addcounty' id='addcounty' value='' spellcheck='false' maxlength='100'>");
	out.println("</div>");
	
	//Contact city
	out.println("<div class='info'>");
	out.println("<label class='label-input'>City:</label>");
	out.println("<input aria-label='City' class='field' type='text' name='addcity' id='addcity' value='' spellcheck='false' maxlength='100'>");
	out.println("</div>");
	
	//Contact address
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Address:</label>");
	out.println("<input aria-label='Address' class='field' type='text' name='addaddress' id='addaddress' value='' spellcheck='false' maxlength='100'>");
	out.println("</div>");
	
	//Contact zipcode
	out.println("<div class='info'>");
	out.println("<label class='label-input'>Zipcode:</label>");
	out.println("<input aria-label='ZipCode' class='field' type='text' name='addzipcode' id='addzipcode' value='' spellcheck='false' maxlength='100'>");
	out.println("</div>");
	
	out.println("</form></div>");
	
	
	out.println("</div>");
	out.println("</div>");
	}
	out.println("</body>");
	out.println("</html>");
  }
  
  public void destroy()
  {
      // do nothing.
  }
	public static List<Contact> sortContacts(String value) {
		FilteredList<Contact> filteredData = new FilteredList<>(list, p -> true);
		filteredData.setPredicate(contact -> {
			// If filter text is empty, display all persons.
			if (value == null || value.isEmpty()) {
				return true;
			}
			
			// Compare first name and last name of every person with filter text.
			String lowerCaseFilter = value.toLowerCase();
			if (contact.getFirstn().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; 
			} else if (contact.getLastn().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; 
			} else if ((contact.getLastn()+" "+contact.getFirstn()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if ((contact.getFirstn()+" "+contact.getLastn()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if ((contact.getFirstn()+" "+contact.getMail()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; 
			} else if ((contact.getLastn()+" "+contact.getMail()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; 
			} else if ((contact.getLastn()+" "+contact.getFirstn()+" "+contact.getMail()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if ((contact.getFirstn()+" "+contact.getLastn()+" "+contact.getMail()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if ((contact.getFirstn().toLowerCase()+" "+contact.getPhone()).indexOf(lowerCaseFilter) != -1) {
				return true; 
			} else if ((contact.getLastn().toLowerCase()+" "+contact.getPhone()).indexOf(lowerCaseFilter) != -1) {
				return true; 
			} else if ((contact.getLastn()+" "+contact.getFirstn()+" "+contact.getPhone()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if ((contact.getFirstn()+" "+contact.getLastn()+" "+contact.getPhone()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (contact.getFix().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (contact.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (contact.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (contact.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (contact.getRegion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (contact.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			} else if (contact.getZipcode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true;
			}
			return false;
			});
		SortedList<Contact> sortedData = new SortedList<>(filteredData);
		return sortedData;
}            

}