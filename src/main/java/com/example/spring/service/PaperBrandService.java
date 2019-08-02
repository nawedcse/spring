/*
 *
 */
package com.example.spring.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.spring.dto.PaperBrand;
import com.example.spring.fetch.PaperBrandFetchOption;
import com.example.spring.filter.PaperBrandFilter;


/**
 * The Interface PaperBrandService.
 *
 * @author n.alam
 */
@Path("/paperBrands")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PaperBrandService {

    /**
     * Find all.
     *
     * @param filter the filter
     * @return the list
     */
    @GET
    @Path("/findAll")
    List<PaperBrand> findAll(@BeanParam @Valid PaperBrandFilter filter, @BeanParam PaperBrandFetchOption fetchOption);

    /**
     * Find by id.
     *
     * @param brandId the brand id
     * @return the paper brand
     */
    @GET
    @Path("/{brandId : \\d+}")
    PaperBrand findById(@NotNull @PathParam("brandId") Long brandId, PaperBrandFetchOption fetchOption);

    /**
     * Creates the.
     *
     * @param paperBrand the paper brand
     * @return the long
     */
    @POST
    @Path("/")
    Long create(PaperBrand paperBrand);

    /**
     * Update.
     *
     * @param brandId the brand id
     * @param paperBrand the paper brand
     */
    @PUT
    @Path("/{brandId : \\d+}")
    void update(@NotNull @PathParam("brandId") Long brandId, PaperBrand paperBrand);

    /**
     * Delete by id.
     *
     * @param brandId the brand id
     */
    @DELETE
    @Path("/{brandId : \\d+}")
    void deleteById(@PathParam("brandId") @NotNull Long brandId);

    /**
     * Activate.
     *
     * @param brandId the brand id
     */
    @POST
    @Path("/{brandId : \\d+}/activate")
    void activate(@NotNull @PathParam("brandId") Long brandId);

    /**
     * Deactivate.
     *
     * @param brandId the brand id
     */
    @POST
    @Path("/{brandId : \\d+}/deactivate")
    void deactivate(@NotNull @PathParam("brandId") Long brandId);

    /**
     * Count.
     *
     * @param filter the filter
     * @return the int
     */
    @GET
    @Path("/count")
    int count(@BeanParam PaperBrandFilter filter);
}
