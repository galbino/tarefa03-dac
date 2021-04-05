package com.uff.tarefa_03.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.uff.tarefa_03.model.Edicao;
import com.uff.tarefa_03.model.Evento;
import com.uff.tarefa_03.service.EventoService;
import com.uff.tarefa_03.service.impl.EventoServiceImpl;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/evento")
@Produces(MediaType.APPLICATION_JSON)
public class EventoController {
    public EventoService eventoService;

    public EventoController() {
        eventoService = new EventoServiceImpl();
    }

    @GET
    public List<Evento> listEventos(){
        List<Evento> resp = eventoService.listarEventos();
        for (Evento evento :
                resp) {
            Map<String, Object> attributes = translateEvento(evento);
        }
        return resp;
    }
    @GET
    @Path("{id}")
    public Evento getEvento(@PathParam("id") Integer id){
        Evento resp = eventoService.buscarEvento(id);
        if (resp != null) {
            Map<String, Object> attributes = translateEvento(resp);
            return resp;
        }
        return null;
    }

    @DELETE
    @Path("{id}")
    public Response deleteEvento(@PathParam("id") Integer id){
        boolean result = eventoService.removerEvento(id);
        if (result){
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEvento(@PathParam("id") Integer id, Evento evento){
        eventoService.updateEvento(id, evento);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEvento(Evento evento){
        eventoService.salvarEvento(evento);
        return Response.ok().build();
    }

    public Map<String, Object> translateEvento(Evento resp){
        Map<String, Object> attributes = new HashMap<>();
        if (resp != null) {
            attributes.put("id", resp.getId());
            attributes.put("area", resp.getArea());
            for (Edicao edicao : resp.getEdicoes()) {
                edicao.setEvento(null);
            }
            attributes.put("edicoes", resp.getEdicoes());
            attributes.put("instituicao", resp.getInstituicao());
            attributes.put("sigla", resp.getSigla());
            attributes.put("nome", resp.getNome());
        }
        return attributes;
    }
}
