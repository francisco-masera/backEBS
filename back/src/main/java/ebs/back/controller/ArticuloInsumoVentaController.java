package ebs.back.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.ArticuloInsumoVenta;
import ebs.back.service.ArticuloInsumoVentaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/insumoVenta")
public class ArticuloInsumoVentaController extends BaseController<ArticuloInsumoVenta, ArticuloInsumoVentaService> {

}
