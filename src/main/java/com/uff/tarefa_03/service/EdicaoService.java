package com.uff.tarefa_03.service;
import com.uff.tarefa_03.model.Edicao;

import java.sql.Date;
import java.util.List;

public interface EdicaoService {
    Edicao salvarEdicao(Edicao edicao);
    Edicao buscarEdicao(Integer id);
    boolean removerEdicao(Integer id);
    List<Edicao> listarEdicoes(String cidade, Date dataInicio);
    Edicao updateEdicao(Integer id, Edicao edicao);
}
