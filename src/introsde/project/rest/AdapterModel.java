package introsde.project.rest;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONObject;


public class AdapterModel {
	
	// Configure client
    ClientConfig clientConfig = new ClientConfig();
    Client client = ClientBuilder.newClient(clientConfig);
    WebTarget musicService = client.target(getMusicApiURI());
    WebTarget jokeService = client.target(getJokeApiURI());
    WebTarget recipeService = client.target(getRecipeApiURI());
    String acceptType = "application/json";
	
    
    
    public Response getSleepMusic() {
    	String[] queryParam1 = { "q", "album:sleep music"};
    	String[] queryParam2 = { "type", "track"};
    	Response result = musicService.path("/v1/search")
    			.queryParam(queryParam1[0],queryParam1[1])
    			.queryParam(queryParam2[0],queryParam2[1])
    			.request().accept(acceptType).get();
    	
    	String resultString = result.readEntity(String.class);
    	JSONObject resultObj = new JSONObject(resultString);
    	JSONArray tracks = resultObj.getJSONObject("tracks").getJSONArray("items");
    	
    	int randomInt = (int) (Math.random()*(tracks.length()-1));
    	
    	JSONObject track = tracks.getJSONObject(randomInt);
    	track.put("mediaType", "sleepMusic");
    	
    	return Response.ok(track.toString()).build();
    }
    
    public Response getRunningMusic() {
    	String[] queryParam1 = { "q", "album:running music"};
    	String[] queryParam2 = { "type", "track"};
    	Response result = musicService.path("/v1/search")
    			.queryParam(queryParam1[0],queryParam1[1])
    			.queryParam(queryParam2[0],queryParam2[1])
    			.request().accept(acceptType).get();
    	
    	String resultString = result.readEntity(String.class);
    	JSONObject resultObj = new JSONObject(resultString);
    	JSONArray tracks = resultObj.getJSONObject("tracks").getJSONArray("items");
    	
    	int randomInt = (int) (Math.random()*(tracks.length()-1));
    	
    	JSONObject track = tracks.getJSONObject(randomInt);
    	track.put("mediaType", "runningMusic");
    	
    	return Response.ok(track.toString()).build();
    }
    
    public Response getJoke(String firstname, String lastname) {
    	Response result = null;
    	if (firstname != null && lastname != null) {
        	String[] queryParam1 = { "firstName", firstname};
        	String[] queryParam2 = { "lastName", lastname};
        	result = jokeService.path("/jokes/random")
        			.queryParam(queryParam1[0],queryParam1[1])
        			.queryParam(queryParam2[0],queryParam2[1])
        			.request().accept(acceptType).get();
    	} else {
    		result = jokeService.path("/jokes/random")
        			.request().accept(acceptType).get();
    	}

    	String resultString = result.readEntity(String.class);
    	JSONObject resultObj = new JSONObject(resultString);
    	JSONObject joke = resultObj.getJSONObject("value");
    	joke.put("mediaType", "joke");
    	
    	return Response.ok(joke.toString()).build();
    }
    
    public Response getRecipe() {
    	String recipe_api_id = System.getenv("RECIPE_API_ID");
    	String recipe_api_key = System.getenv("RECIPE_API_KEY");
    	//String recipe_api_id = introsde.project.ApiKeys.recipe_api_id;
    	//String recipe_api_key = introsde.project.ApiKeys.recipe_api_key;
    	
    	String[] queryParam1 = { "q", "dinner tonight"};
    	String[] queryParam2 = { "diet", "low-fat"};
    	String[] queryParam3 = { "calories", "lte 600"};
    	String[] queryParam4 = { "app_id", recipe_api_id};
    	String[] queryParam5 = { "app_key", recipe_api_key};
    	
    	Response result = recipeService.path("/search")
    			.queryParam(queryParam1[0],queryParam1[1])
    			.queryParam(queryParam2[0],queryParam2[1])
    			.queryParam(queryParam3[0],queryParam3[1])
    			.queryParam(queryParam4[0],queryParam4[1])
    			.queryParam(queryParam5[0],queryParam5[1])
    			.request().accept(acceptType).get();
    	
    	String resultString = result.readEntity(String.class);
    	JSONObject resultObj = new JSONObject(resultString);
    	JSONArray recipes = resultObj.getJSONArray("hits");
    	
    	int randomInt = (int) (Math.random()*(recipes.length()-1));
    	
    	JSONObject recipe = recipes.getJSONObject(randomInt).getJSONObject("recipe");
    	recipe.put("mediaType", "recipe");
    	
    	return Response.ok(recipe.toString()).build();
    	
    }
    

    private static URI getMusicApiURI() {
        return UriBuilder.fromUri(
                "https://api.spotify.com").build();
    }

    private static URI getJokeApiURI() {
        return UriBuilder.fromUri(
                "http://api.icndb.com").build();
    }
    
    private static URI getRecipeApiURI() {
        return UriBuilder.fromUri(
        		"https://api.edamam.com").build();
    }

}