package com.shaah.vouch_status.common.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.shaah.vouch_status.common.utils.CommonUtils;

/**
 * Servlet implementation class UploadFile
 */

@MultipartConfig
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

	    // Create path components to save the file
	    final String path = CommonUtils.UPLOAD_PATH;
	    final Part filePart = request.getPart("file");
	    final String fileName = getFileName(filePart);

	    OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();

	    try {
	        
	    	out = new FileOutputStream(new File(path + File.separator+ fileName));
	        filecontent = filePart.getInputStream();
//	        		uploadFile(filecontent,out);
	        getStringFromInputStream(filecontent);
//	        writer.println("New file " + fileName + " created at " + path);
	        
	        
	        response.sendRedirect("/common/result.jsp");
	        
//	        LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
//	                new Object[]{fileName, path});
	    } catch (FileNotFoundException fne) {
	        writer.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        writer.println("<br/> ERROR: " + fne.getMessage());

//	        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
//	                new Object[]{fne.getMessage()});
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	        if (writer != null) {
	            writer.close();
	        }
	    }
	}

	private void uploadFile(InputStream filecontent,OutputStream out) {
		// TODO Auto-generated method stub
		

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        try {
				while ((read = filecontent.read(bytes)) != -1) {
					System.out.println(">>>>"+new String(bytes));
				    out.write(bytes, 0, read);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private String getFileName(final Part part) {
//	    final String partHeader = part.getHeader("content-disposition");
//	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	
	
	
	
	// convert InputStream to String
		private  void getStringFromInputStream(InputStream is) {
	 
			BufferedReader br = null;
//			StringBuilder sb = new StringBuilder();
	 
			String line;
			try {
	 
				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
//					sb.append(line);
					System.out.println(">>>>"+line);
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	 
//			System.out.println(">>>>>>>>>>>>>>>>"+sb.toString()+"<<<<<<<<<<<<<");
	 
		}
	 
	
	
	
	
	
	
	

}
