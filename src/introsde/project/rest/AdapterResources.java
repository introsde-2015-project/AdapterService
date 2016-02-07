package introsde.project.rest;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/")
public class AdapterResources {
	
	AdapterModel adapterModel = new AdapterModel();
	
	
	//Getting the list of person in the LocalDatabase Service
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/music/sleep")
    public Response getSleepMusic() {
        Response track = adapterModel.getSleepMusic();
        return track;
    }
    
	//Getting the list of person in the LocalDatabase Service
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/music/run")
    public Response getRunningMusic() {
        Response track = adapterModel.getRunningMusic();
        return track;
    }
    
    //Getting the list of person in the LocalDatabase Service
    
    //Getting the person information in the LocalDatabase Service
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/joke")
    // ADD NAME
    public Response getJoke(@QueryParam("firstname") String firstname, @QueryParam("lastname") String lastname) {
    	Response joke = adapterModel.getJoke(firstname, lastname);
        return joke;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/recipe")
    public Response getRecipe() {
    	Response recipe = adapterModel.getRecipe();
        return recipe;
    }
	
}