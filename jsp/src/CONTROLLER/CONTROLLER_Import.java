package CONTROLLER;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class CONTROLLER_Import extends HttpServlet {
	 /**
	 * 
	 */
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
	                        File path = new File("/home/dominik/Downloads/uploads");
	                        if (!path.exists()) {
	                            @SuppressWarnings("unused")
								boolean status = path.mkdirs();
	                        }
	 
	                        File uploadedFile = new File(path + "/" + fileName);
	                        System.out.println(uploadedFile.getAbsolutePath());
	                        item.write(uploadedFile);
	                    }
	                }
	            } catch (FileUploadException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

}