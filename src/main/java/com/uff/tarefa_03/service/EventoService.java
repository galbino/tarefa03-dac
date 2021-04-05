package com.uff.tarefa_03.service;
import com.uff.tarefa_03.model.Evento;

import java.util.List;

public interface EventoService {
    Evento salvarEvento(Evento evento);
    Evento buscarEvento(Integer id);
    boolean removerEvento(Integer id);
    List<Evento> listarEventos();
    Evento updateEvento(Integer id, Evento evento);
}
