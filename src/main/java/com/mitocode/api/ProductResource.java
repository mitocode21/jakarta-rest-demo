package com.mitocode.api;

import com.mitocode.model.Product;
import com.mitocode.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
public class ProductResource {

    @Inject
    private ProductService service;

    @GET
    @Produces("application/json")
    public Response getProducts() {
        List<Product> list = service.listAll();
        return Response.ok().status(Response.Status.OK).entity(list).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") Integer id) {
        Product p = service.findById(id);
        return Response.ok().entity(p).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        Product p = service.save(product);
        return Response.ok().status(Response.Status.CREATED).entity(p).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Integer id, Product product) {
        if (service.findById(id) != null) {
            product.setId(id);
            Product p = service.update(product);
            return Response.ok().status(Response.Status.OK).entity(p).build();
        } else {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Integer id) {
        if (service.findById(id) != null) {
            service.delete(id);
            return Response.ok().status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok().status(Response.Status.NOT_FOUND).build();
        }
    }
}