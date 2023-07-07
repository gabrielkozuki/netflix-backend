
package patterns;

import modelos.Erro;

public class ErroBuilder {
    private Erro erro;

    public ErroBuilder() {
        erro = new Erro();
    }

    public ErroBuilder descricao(String descricao) {
        erro.setDescricao(descricao);
        return this;
    }

    public ErroBuilder codigo(String codigo) {
        erro.setCodigo(codigo);
        return this;
    }

    public Erro build() {
        return erro;
    }
}
