package ebs.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import ebs.back.entity.DetallePedido;
import ebs.back.service.DetallePedidoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/detallePedido")
public class DetallePedidoController extends BaseController<DetallePedido, DetallePedidoService> {

	@Autowired
	private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@DeleteMapping("/quitarItem/{idArticulo}/{idPedido}")
	public void quitarItem(@PathVariable Long idArticulo, @PathVariable Long idPedido) {
		try {
			jdbcTemplate.update("DELETE  FROM DetallePedido WHERE idPedido = ? AND idArticulo=?", idPedido, idArticulo);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
}
