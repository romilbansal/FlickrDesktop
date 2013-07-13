package com.flickr.getPermissions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

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
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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
	 * @throws IOException 
	 * @throws FlickrException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	public static void main(String[] args) throws IOException, FlickrException
	{

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		File f = new File("/home/romil/temp.txt");

		FileReader fi = new FileReader(f);

		BufferedReader bw = new BufferedReader(fi);

		String toke = bw.readLine();
		String secret = bw.readLine();
		Flickr flickr = new Flickr("5f0a1d8f31426f811498e2ec5295c705", "86bbe3f2c143379c", new REST());
		Flickr.debugStream = false;
		AuthInterface authInterface = flickr.getAuthInterface();
		Token token = new Token(toke, secret);
		String verifier = request.getParameter("oauth_verifier");

		Verifier v = new Verifier(verifier);

		Token accessToken = authInterface.getAccessToken(token, v);
		//System.out.println("Authentication success");

		try {
			Auth auth = authInterface.checkToken(accessToken);
			flickr.setAuth(auth);
			String user = auth.getUser().getRealName();
			PhotosetsInterface iface = flickr.getPhotosetsInterface();
			Photosets photosets = iface.getList(auth.getUser().getId());
			ArrayList<Photoset> p = (ArrayList<Photoset>) photosets.getPhotosets();
			HashSet<String> set = new HashSet<String>();
			ArrayList<String> photo = new ArrayList<String>();
			Photoset curr = null;
			for(Photoset pp : p){
				set.add(pp.getTitle().split("->")[0]);
				if(pp.getTitle().split("->").length==1){
					curr=pp;
				}
			}
			if(set.size()>0){
				PhotoList<Photo> photos = iface.getPhotos(curr.getId(), 500, 1);
				for(Photo ph:photos){
					photo.add(ph.getLargeUrl());
				}
			}
			else{

			}
			JsonObject jo = new JsonObject();
			jo.addProperty("user", user);
			jo.addProperty("id", auth.getUser().getId());
			JsonArray ja = new JsonArray();
			for(String s : set){
				JsonObject j = new JsonObject();
				j.addProperty("Name", s);
				j.addProperty("Next", "home->"+s);
				ja.add(j);
			}
			jo.add("folders", ja);
			JsonArray ja2 = new JsonArray();
			for(String s : photo){
				JsonObject j = new JsonObject();
				j.addProperty("Name", s);
				ja.add(j);
			}
			jo.add("photos", ja2);
			response.sendRedirect("http://localhost:8080/userimage.html?j="+jo.toString());
		} catch (FlickrException e) {
			// TODO Auto-geneated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
