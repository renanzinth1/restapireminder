package br.com.reminder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.reminder.model.Lembrete;

public interface ILembrete extends JpaRepository<Lembrete, Long> {

}
