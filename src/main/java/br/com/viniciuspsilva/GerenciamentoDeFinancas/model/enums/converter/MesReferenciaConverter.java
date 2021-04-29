package br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.converter;

import br.com.viniciuspsilva.GerenciamentoDeFinancas.model.enums.MesReferencia;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class MesReferenciaConverter implements AttributeConverter<MesReferencia, String> {
    @Override
    public String convertToDatabaseColumn(MesReferencia mesReferencia) {

        if (Objects.isNull(mesReferencia)) {
            return null;
        }

        return mesReferencia.getCodigo();
    }

    @Override
    public MesReferencia convertToEntityAttribute(String codigo) {

        if (Objects.isNull(codigo)) {
            return null;
        }

        return Stream.of(MesReferencia.values())
                .filter(mes -> mes.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
