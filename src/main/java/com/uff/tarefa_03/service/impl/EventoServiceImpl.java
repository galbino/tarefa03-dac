package com.uff.tarefa_03.service.impl;

import com.uff.tarefa_03.model.Evento;
import com.uff.tarefa_03.service.EventoService;

import javax.persistence.EntityManager;
import java.util.List;

public class EventoServiceImpl implements EventoService {
    private EntityManager em;

    @Override
    public Evento salvarEvento(Evento evento){
        em = JPAUtil.getEM();
        em.getTransaction().begin();
        em.persist(evento);
        em.getTransaction().commit();
        return evento;
    }
    @Override
    public Evento buscarEvento(Integer id){
        em = JPAUtil.getEM();
        Evento evento = em.find(Evento.class, id);
        em.refresh(evento);
        return evento;
    }
    @Override
    public boolean removerEvento(Integer id){
        em = JPAUtil.getEM();
        Evento evento = em.find(Evento.class, id);
        if (evento != null){
            em.getTransaction().begin();
            em.remove(evento);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        return false;
    }
    @Override
    public List<Evento> listarEventos(){
        em = JPAUtil.getEM();
        List<Evento> eventoList = em.createQuery("SELECT a FROM Evento a", Evento.class).getResultList();
        for (Evento evento :
                eventoList) {
            em.refresh(evento);
        }
        em.close();
        return eventoList;
    }

    public Evento updateEvento(Integer id, Evento evento) {
        Evento oldEvent = this.buscarEvento(id);
        em = JPAUtil.getEM();
        em.getTransaction().begin();
        if (evento.getArea() != null) oldEvent.setArea(evento.getArea());
        if (evento.getEdicoes() != null) oldEvent.setEdicoes(evento.getEdicoes());
        if (evento.getNome() != null) oldEvent.setNome(evento.getNome());
        if (evento.getInstituicao() != null) oldEvent.setInstituicao(evento.getInstituicao());
        if (evento.getSigla() != null) oldEvent.setSigla(evento.getSigla());
        em.merge(oldEvent);
        em.getTransaction().commit();
        em.close();
        return oldEvent;
    }
}
