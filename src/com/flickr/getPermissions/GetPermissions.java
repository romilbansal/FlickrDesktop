package com.flickr.getPermissions;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FlickrApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.Response;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;

/**
 * Servlet implementation class GetPermissions
 */
@WebServlet("/GetPermissions")
public class GetPermissions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPermissions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws FlickrException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public static void main(String[] args)
    {
    	String tok = "72157634624508602-b0e29aa8979329f3";
		String verifier = "59b3db714dc28442";
		 Verifier v = new Verifier(verifier);
		 OAuthService service = new ServiceBuilder().provider(FlickrApi.class).apiKey("5f0a1d8f31426f811498e2ec5295c705").apiSecret("86bbe3f2c143379c").build();
		 
		 Token token = new Token(tok, "86bbe3f2c143379c");
		 Token accessToken = service.getAccessToken(token, v);

			// This token can be used until the user revokes it.
			System.out.println("Token: " + accessToken.getToken());
			System.exit(0);
    	
    	/*
      // Replace these with your own api key and secret
      String apiKey = "5f0a1d8f31426f811498e2ec5295c705";
      String apiSecret = "86bbe3f2c143379c";
      OAuthService service = new ServiceBuilder().provider(FlickrApi.class).apiKey(apiKey).apiSecret(apiSecret).build();
      Scanner in = new Scanner(System.in);

      System.out.println("=== Flickr's OAuth Workflow ===");
      System.out.println();

      // Obtain the Request Token
      System.out.println("Fetching the Request Token...");
      Token requestToken = service.getRequestToken();
      System.out.println("Got the Request Token!");
      System.out.println();

      System.out.println("Now go and authorize Scribe here:");
      String authorizationUrl = service.getAuthorizationUrl(requestToken);
      System.out.println(authorizationUrl + "&perms=read");
      System.out.println("And paste the verifier here");
      System.out.print(">>");
      Verifier verifier = new Verifier(in.nextLine());
      System.out.println();

      // Trade the Request Token and Verfier for the Access Token
      System.out.println("Trading the Request Token for an Access Token...");
      Token accessToken = service.getAccessToken(requestToken, verifier);
      System.out.println("Got the Access Token!");
      System.out.println("(if your curious it looks like this: " + accessToken + " )");
      System.out.println();*/

     }
    
    public static void main(String args) throws FlickrException{
    	String tok = "72157634624508602-b0e29aa8979329f3";
		String verifier = "59b3db714dc28442";
		 Verifier v = new Verifier(verifier);
		 OAuthService service = new ServiceBuilder().provider(FlickrApi.class).apiKey("5f0a1d8f31426f811498e2ec5295c705").apiSecret("86bbe3f2c143379c").build();
		 
		 Token token = new Token(tok, "86bbe3f2c143379c");
		 Token accessToken = service.getAccessToken(token, v);

			// This token can be used until the user revokes it.
			System.out.println("Token: " + accessToken.getToken());

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s = request.getParameter("oauth_verifier");
		String tok = request.getParameter("oauth_token");
		Flickr flickr = new Flickr("5f0a1d8f31426f811498e2ec5295c705", "86bbe3f2c143379c", new REST());
		AuthInterface authInterface = flickr.getAuthInterface();
		Token token = authInterface.getRequestToken();
		Token requestToken = authInterface.getAccessToken(token, new Verifier(tok));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
