package com.flickr.getPermissions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.model.Token;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;

/**
 * Servlet implementation class requestPermissions
 */
@WebServlet("/requestPermissions")
public class requestPermissions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public requestPermissions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public static void main(String arg[]){
    	Flickr flickr = new Flickr("5f0a1d8f31426f811498e2ec5295c705", "86bbe3f2c143379c", new REST());
		Flickr.debugStream = false;
		AuthInterface authInterface = flickr.getAuthInterface();

		//Scanner scanner = new Scanner(System.in);

		Token token = authInterface.getRequestToken("http://server-zerovelocity.rhcloud.com/FlickDesk/getPermissions");
		System.out.println("token: " + token.getToken() + " "+token.getSecret());

		String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Flickr flickr = new Flickr("5f0a1d8f31426f811498e2ec5295c705", "86bbe3f2c143379c", new REST());
		Flickr.debugStream = false;
		AuthInterface authInterface = flickr.getAuthInterface();

		//Scanner scanner = new Scanner(System.in);

		Token token = authInterface.getRequestToken("http://server-zerovelocity.rhcloud.com/FlickDesk/getPermissions");
		System.out.println("token: " + token.getToken() + " "+token.getSecret());

		String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);
		
		File f = new File("/var/lib/openshift/51e38b2fe0b8cd50a100002b/app-root/data/temp.txt");
		
		FileWriter fi = new FileWriter(f);
		
		BufferedWriter bw = new BufferedWriter(fi);
		
		bw.append(token.getToken()+"\n");
		bw.append(token.getSecret());
		bw.flush();
		bw.close();
		System.out.println(url);
		response.sendRedirect(url);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
