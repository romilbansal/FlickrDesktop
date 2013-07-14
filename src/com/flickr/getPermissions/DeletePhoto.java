package com.flickr.getPermissions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.model.Token;
import org.scribe.model.Verifier;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photosets.PhotosetsInterface;

/**
 * Servlet implementation class DeletePhoto
 */
@WebServlet("/DeletePhoto")
public class DeletePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @throws FlickrException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public static void main(String args[]) throws IOException, FlickrException{
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		File f = new File("/home/romil/temp.txt");

		FileReader fi = new FileReader(f);

		BufferedReader bw = new BufferedReader(fi);

		String toke = bw.readLine();
		String secret = bw.readLine();
		String verifier = bw.readLine();
		Flickr flickr = new Flickr("5f0a1d8f31426f811498e2ec5295c705", "86bbe3f2c143379c", new REST());
		Flickr.debugStream = false;
		AuthInterface authInterface = flickr.getAuthInterface();
		Token token = new Token(toke, secret);
		System.out.println(verifier);
		String nextFolder = request.getParameter("curr");
		Verifier v = new Verifier(verifier);
		Token accessToken = new Token(bw.readLine(), bw.readLine());
		String url = request.getParameter("url");
		String id = url.split("/")[url.split("/").length-1];
		id = id.split("_")[0];
		Auth auth;
		try {
			auth = authInterface.checkToken(accessToken);
		flickr.setAuth(auth);
		RequestContext.getRequestContext().setAuth(auth);
		PhotosInterface pi = flickr.getPhotosInterface();
		pi.delete(id);
		} catch (FlickrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("http://localhost:8080/FlickrDemo/getFolder?next="+nextFolder);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
