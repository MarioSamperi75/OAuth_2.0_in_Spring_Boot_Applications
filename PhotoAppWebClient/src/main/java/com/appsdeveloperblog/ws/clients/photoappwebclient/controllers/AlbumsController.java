package com.appsdeveloperblog.ws.clients.photoappwebclient.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.appsdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;

@Controller
public class AlbumsController {
	
	@GetMapping("/albums")
	public String getAlbums (Model model) {		
		
		AlbumRest album = new AlbumRest();
		album.setAlbumId("albumOne");
		album.setAlbumTitle("Album One Title");
		album.setAlbumUrl("http://localhost:8082/albums/1");
		
		AlbumRest album2 = new AlbumRest();
		album2.setAlbumId("albumTwo");
		album2.setAlbumTitle("Album Two Title");
		album2.setAlbumUrl("http://localhost:8082/albums/2");
		
		List returnValue = Arrays.asList(album, album2 );
		
		//to have the list available in the html page we have to add it to the model 
		model.addAttribute("albums", returnValue);
		
		
		
		
		
		
		// we return the name of the view
		return "albums";
	}

}
