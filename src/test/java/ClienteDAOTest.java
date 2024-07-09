import br.com.danielsilva.dao.ClienteDAO;
import br.com.danielsilva.dao.IClienteDAO;
import br.com.danielsilva.domain.Cliente;
import exceptions.DAOException;
import exceptions.MaisDeUmRegistroException;
import exceptions.TableException;
import exceptions.TipoChaveNaoEncontradaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class ClienteDAOTest {

    private IClienteDAO clienteDao;

    public ClienteDAOTest() {
        clienteDao = new ClienteDAO();
    }

    @After
    public void end() throws DAOException {
        Collection<Cliente> list = clienteDao.buscarTodos();
        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli.getCpf());
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    @Test
    public void pesquisarCliente() throws MaisDeUmRegistroException, TableException, TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(3L);
        cliente.setNome("");
        cliente.setCidade("");
        cliente.setEnd("");
        cliente.setReferencia("");
        cliente.setEstado("");
        cliente.setNumero(10);
        cliente.setTel(8L);
//        clienteDao.cadastrar(cliente);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
        System.out.println(clienteConsultado.getNome());

//        clienteDao.excluir(cliente.getCpf());
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(7L);
        cliente.setNome("Jessabel");
        cliente.setCidade("Nilo");
        cliente.setEnd("End");
        cliente.setReferencia("Perto da lanchonete PointCerto"); // adicionei uma nova propriedade.
        cliente.setEstado("RJ");
        cliente.setNumero(2);
        cliente.setTel(9L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);

//        clienteDao.excluir(cliente.getCpf());
    }


    @Test
    public void excluirCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(12L);
        cliente.setNome("Davi");
        cliente.setCidade("");
        cliente.setEnd("");
        cliente.setEstado("");
        cliente.setNumero(9);
        cliente.setTel(9L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

//        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
//        Assert.assertNotNull(clienteConsultado);

        clienteDao.excluir(cliente.getCpf());
//        clienteConsultado = clienteDao.consultar(cliente.getCpf());
//        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(1234567L);
        cliente.setNome("");
        cliente.setCidade("");
        cliente.setEnd("");
        cliente.setEstado("");
        cliente.setNumero(0);
        cliente.setTel(119L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);

        clienteConsultado.setNome("");
        clienteDao.alterar(clienteConsultado);

        Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCpf());
        Assert.assertNotNull(clienteAlterado);
        Assert.assertEquals("", clienteAlterado.getNome());

        clienteDao.excluir(cliente.getCpf());
        clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNull(clienteConsultado);
    }

    @Test
    public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
        Cliente cliente = new Cliente();
        cliente.setCpf(56565656565L);
        cliente.setNome("");
        cliente.setCidade("");
        cliente.setEnd("");
        cliente.setEstado("");
        cliente.setNumero(2);
        cliente.setTel(11L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        Assert.assertTrue(retorno);

        Cliente cliente1 = new Cliente();
        cliente1.setCpf(779L);
        cliente1.setNome("");
        cliente1.setCidade("");
        cliente1.setEnd("");
        cliente1.setEstado("Rj");
        cliente1.setNumero(10);
        cliente1.setTel(178888L);
        Boolean retorno1 = clienteDao.cadastrar(cliente1);
        Assert.assertTrue(retorno1);

        Collection<Cliente> list = clienteDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);

        list.forEach(cli -> {
            try {
                clienteDao.excluir(cli.getCpf());
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        Collection<Cliente> list1 = clienteDao.buscarTodos();
        assertTrue(list1 != null);
        assertTrue(list1.size() == 0);
    }

}