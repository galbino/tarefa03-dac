package com.uff.tarefa_03.service.impl;

import com.uff.tarefa_03.model.Edicao;
import com.uff.tarefa_03.service.EdicaoService;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

public class EdicaoServiceImpl implements EdicaoService {
    private EntityManager em;

    public Edicao salvarEdicao(Edicao edicao){
        em = JPAUtil.getEM();
        em.getTransaction().begin();
        em.persist(edicao);
        em.getTransaction().commit();
        em.close();
        return edicao;
    }

    public Edicao buscarEdicao(Integer id){
        em = JPAUtil.getEM();
        Edicao edicao = em.find(Edicao.class, id);
        if (edicao != null) {
            em.refresh(edicao);
            return edicao;
        }
        return null;
    }

    public boolean removerEdicao(Integer id){
        em = JPAUtil.getEM();
        Edicao edicao = em.find(Edicao.class, id);
        if (edicao != null){
            em.getTransaction().begin();
            em.remove(edicao);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        return false;
    }

    public List<Edicao> listarEdicoes(String cidade, Date dataInicio){
        em = JPAUtil.getEM();
        String query = "SELECT a FROM Edicao a";
        String complement = "";
        if (cidade != null){
            complement += " where a.cidadeSede = '" + cidade + "'";
        }
        if (dataInicio != null){
            if (!complement.equals("")) {
                complement += " and a.dataInicio >= '" + dataInicio + "'";
            } else {
                complement += " where a.dataInicio >= '" + dataInicio + "'";
            }
        }
        if (!complement.equals("")){
            query += complement;
        }
        List<Edicao> edicaoList = em.createQuery(query, Edicao.class).getResultList();
        for (Edicao edicao :
                edicaoList) {
            em.refresh(edicao);
        }
        return edicaoList;
    }
    public Edicao updateEdicao(Integer id, Edicao edicao) {
        Edicao oldEdicao = this.buscarEdicao(id);
        em = JPAUtil.getEM();
        em.getTransaction().begin();
        if (edicao.getEvento() != null) oldEdicao.setEvento(edicao.getEvento());
        if (edicao.getCidadeSede() != null) oldEdicao.setCidadeSede(edicao.getCidadeSede());
        if (edicao.getDataFim() != null) oldEdicao.setDataFim(edicao.getDataFim());
        if (edicao.getDataInicio() != null) oldEdicao.setDataInicio(edicao.getDataInicio());
        if (edicao.getCidadeSede() != null) oldEdicao.setCidadeSede(edicao.getCidadeSede());
        if (edicao.getAno() != null) oldEdicao.setAno(edicao.getAno());
        if (edicao.getNumero() != null) oldEdicao.setNumero(edicao.getNumero());
        if (edicao.getPais() != null) oldEdicao.setPais(edicao.getPais());
        em.merge(oldEdicao);
        em.getTransaction().commit();
        em.close();
        return oldEdicao;
    }
}
