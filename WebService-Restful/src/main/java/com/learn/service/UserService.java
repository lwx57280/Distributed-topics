package com.learn.service;

import com.learn.Response;
import com.learn.domain.User;

import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@WebService
@Path(value = "/users")
public interface UserService {
    // http://ip:port/users
    @GET
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML})
    List<User> getUsers();

    @DELETE
    @Path("{id}")   // http://ip:port/users/10
    @Produces(MediaType.APPLICATION_JSON)
    Response delete(@PathParam("id") int id);

    @POST
    @Path("/add")
        // http://ip:port/users/1
    Response insert(User user);

    @PUT
    @Path("/update")
    Response updateUser(User user);

    @GET
    @Path("{id}")
    User getUser(@PathParam("id") int id);

}
