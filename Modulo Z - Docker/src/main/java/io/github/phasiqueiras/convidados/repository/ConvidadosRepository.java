package io.github.phasiqueiras.convidados.repository;

import io.github.phasiqueiras.convidados.model.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvidadosRepository extends JpaRepository<Convidado, String> {
}
