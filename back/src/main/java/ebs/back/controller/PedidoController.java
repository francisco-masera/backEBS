package ebs.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.Pedido;
import ebs.back.service.PedidoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping(path = "buensabor/pedido")
public class PedidoController extends BaseController<Pedido, PedidoService> {

    @Autowired
    private JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @RequestMapping(value = "/pedidoEntregado/{id}/{estado}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE })

    public int updatePedidoEntregado(@PathVariable Long id, @PathVariable String estado) {
        int estadoPedido;
        System.out.print(estado);   
        System.out.print("Update pedido set estado = " + "'" + estado + "'" + "where idPedido = " + id); 
        return estadoPedido = jdbcTemplate.update("Update pedido set estado = " + "'" + estado + "'" + "where idPedido = " + id);
    }

}
