package generic;

import br.com.danielsilva.dao.IProdutoDAO;
import br.com.danielsilva.domain.Produto;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

    public ProdutoService(IProdutoDAO dao) {
        super(dao);
    }

}