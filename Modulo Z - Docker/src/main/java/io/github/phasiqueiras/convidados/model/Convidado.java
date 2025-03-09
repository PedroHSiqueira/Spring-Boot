package io.github.phasiqueiras.convidados.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Convidado {
    private String nome;
    private String cpf;
}
