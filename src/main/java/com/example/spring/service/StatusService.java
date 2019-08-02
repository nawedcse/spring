package com.example.spring.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.example.spring.dto.Status;
import com.example.spring.filter.StatusFilter;

@Path("/statuses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface StatusService {

	@GET
	@Path("/")
	List<Status> findAll(@BeanParam @Valid StatusFilter filter);

	@GET
    @Path("/{id : \\d+}")
    Status findById(@PathParam("id") @NotNull Long id);

    @DELETE
    @Path("/{id : \\d+}")
    void deleteById(@PathParam("id") @NotNull Long id);

    @POST
    @Path("/")
    Long create(@NotNull Status status);

    @PUT
    @Path("/{id : \\d+}")
    void update(@PathParam("id") @NotNull Long id, @NotNull Status status);

    @GET
    @Path("/count")
    int count(@BeanParam @Valid StatusFilter filter);

    @POST
    @Path("/{id : \\d+}/activate")
    void activate(@NotNull @PathParam("id") Long id);

    @POST
    @Path("/{id : \\d+}/deactivate")
    void deactivate(@NotNull @PathParam("id") Long id);
}
