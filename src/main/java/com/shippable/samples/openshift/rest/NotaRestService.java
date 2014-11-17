package com.shippable.samples.openshift.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.shippable.samples.openshift.model.Nota;

@Path("/notas")
@Stateless
public class NotaRestService {
  @Inject
  private EntityManager em;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Nota> listScores() {
    return em.createQuery("select s from Nota s order by s.timestamp").getResultList();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes("application/x-www-form-urlencoded")

  public Nota addScore(@FormParam("nota") String nota) {
    Nota newNota = new Nota();
    newNota.setNota(nota);
    em.persist(newNota);
    return newNota;
  }
}
