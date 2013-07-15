package com.flickr.getPermissions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photosets.Photoset;
import com.flickr4java.flickr.photosets.Photosets;
import com.flickr4java.flickr.photosets.PhotosetsInterface;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.flickr4java.flickr.uploader.Uploader;

/**
 * Servlet implementation class UploadPhoto
 */
@WebServlet("/UploadPhoto")
public class UploadPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public static void main(String args[]) throws IOException{
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		File f = new File("/var/lib/openshift/51e38b2fe0b8cd50a100002b/app-root/data/temp.txt");

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
		String nextFolder = request.getParameter("folder");
		Verifier v = new Verifier(verifier);
		Token accessToken = new Token(bw.readLine(), bw.readLine());
		Auth auth;
		try {
			auth = authInterface.checkToken(accessToken);
			flickr.setAuth(auth);
			RequestContext.getRequestContext().setAuth(auth);

			Uploader uploader = flickr.getUploader();
			File upload = new File("/var/lib/openshift/51e38b2fe0b8cd50a100002b/app-root/data/UploadStuff");
			File foo[]= upload.listFiles();
			for (File imageFile : foo){
				//File imageFile = new File("/home/romil/Pictures/test.png");
				InputStream in1 = null;
				in1 = new FileInputStream(imageFile);
				UploadMetaData metaData = new UploadMetaData();
				metaData.setPublicFlag(true);
				String photoId = uploader.upload(in1, metaData);

				PhotosetsInterface iface = flickr.getPhotosetsInterface();
				Photosets photosets = iface.getList(auth.getUser().getId());
				ArrayList<Photoset> p = (ArrayList<Photoset>) photosets.getPhotosets();
				Photoset curr = null;
				for(Photoset pp : p){
					if(!pp.getTitle().contains("home->")){
						continue;
					}
					if(pp.getTitle().equals(nextFolder.replaceAll("/", "->"))){
						curr=pp;
					}
				}


				if(curr==null){
					PhotosetsInterface pp = flickr.getPhotosetsInterface();
					pp.create(nextFolder.replaceAll("/", "->"), "FlickrDesktop", photoId);
				}
				else{
					PhotosetsInterface pp = flickr.getPhotosetsInterface();
					PhotosInterface pi = flickr.getPhotosInterface();
					pp.addPhoto(curr.getId(), photoId);
				}
				imageFile.delete();
			}
		} catch (FlickrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("http://server-zerovelocity.rhcloud.com/FlickDesk/getFolder?next="+nextFolder.replaceAll("/", "->"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
