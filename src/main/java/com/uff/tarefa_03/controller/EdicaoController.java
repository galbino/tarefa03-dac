package com.uff.tarefa_03.controller;

import com.uff.tarefa_03.model.Edicao;
import com.uff.tarefa_03.model.Evento;
import com.uff.tarefa_03.service.EdicaoService;
import com.uff.tarefa_03.service.impl.EdicaoServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/edicao")
@Produces("application/json")
public class EdicaoController {
    public EdicaoService edicaoService;

    public EdicaoController() {
        edicaoService = new EdicaoServiceImpl();
    }

    @GET
    public List<Edicao> listEdicoes(@QueryParam("cidade") String cidade, @QueryParam("dataInicio") Date dataInicio){
        List<Edicao> resp = edicaoService.listarEdicoes(cidade, dataInicio);
        for (Edicao edicao :
                resp) {
            Map<String, Object> attributes = translateEdicao(edicao);
        }
        return resp;
    }
    @GET
    @Path("{id}")
    public Edicao getEdicao(@PathParam("id") Integer id){
        Edicao resp = edicaoService.buscarEdicao(id);
        Map<String, Object> attributes = translateEdicao(resp);

        return resp;
    }

    @DELETE
    @Path("{id}")
    public Response deleteEdicao(@PathParam("id") Integer id){
        edicaoService.removerEdicao(id);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Edicao updateEdicao(@PathParam("id") Integer id, Edicao edicao){
        Edicao edicaoUpd = edicaoService.updateEdicao(id, edicao);
        return edicaoUpd;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEdicao(Edicao edicao){
        edicaoService.salvarEdicao(edicao);
        return Response.ok().build();
    }

    public Map<String, Object> translateEdicao(Edicao resp){
        Map<String, Object> attributes = new HashMap<>();
        if (resp != null) {
            attributes.put("id", resp.getId());
            attributes.put("numero", resp.getNumero());
            attributes.put("ano", resp.getAno());
            attributes.put("dataInicio", resp.getDataInicio());
            attributes.put("dataFim", resp.getDataFim());
            attributes.put("cidadeSede", resp.getCidadeSede());
            attributes.put("pais", resp.getPais());
            resp.getEvento().setEdicoes(null);
            attributes.put("evento", resp.getEvento());
        }
        return attributes;
    }
}
