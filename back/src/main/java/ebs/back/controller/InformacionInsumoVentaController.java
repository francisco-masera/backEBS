package ebs.back.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.InformacionInsumoVenta;
import ebs.back.service.InformacionInsumoVentaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/insumoVenta")
public class InformacionInsumoVentaController
		extends BaseController<InformacionInsumoVenta, InformacionInsumoVentaService> {

}
