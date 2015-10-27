package CONTROLLER;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CONTROLLER_Import extends HttpServlet {
	private HttpSession session;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

	        if (isMultipart) {
	        	// Create a factory for disk-based file items
	        	FileItemFactory factory = new DiskFileItemFactory();

	        	// Create a new file upload handler
	        	ServletFileUpload upload = new ServletFileUpload(factory);
	 
	            try {
	            	// Parse the request
	            	@SuppressWarnings("rawtypes")
					List /* FileItem */ items = upload.parseRequest(request);
	                @SuppressWarnings("rawtypes")
					Iterator iterator = items.iterator();
	                while (iterator.hasNext()) {
	                    FileItem item = (FileItem) iterator.next();
	                    if (!item.isFormField()) {
	                        String fileName = item.getName();	 
	                        File path = new File(CONTROLLER_Statments.upload.path.toString());
	                        if (!path.exists()) {
	                            @SuppressWarnings("unused")
								boolean status = path.mkdirs();
	                        }
	                		session = request.getSession(true);
	                		session.setAttribute(CONTROLLER_Statments.session.filename.toString(), fileName);
	                        File uploadedFile = new File(path + "/" + fileName);
	                        item.write(uploadedFile);
	                    }
	                    
	            		session.setAttribute("site",CONTROLLER_Statments.caller.CONTROLLER_Import.toString()); 

	                    
	                    RequestDispatcher rd = request.getRequestDispatcher(CONTROLLER_Statments.redirect.Controller.toString());
	            		rd.include(request, response);
	            		
	            		response.sendRedirect(CONTROLLER_Statments.redirect.login_success.toString());

	                }
	            } catch (FileUploadException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

}