package ebs.back.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebs.back.entity.ArticuloManufacturado;
import ebs.back.service.ArticuloManufacturadoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/manufacturado")
public class ArticuloManufacturadoController
		extends BaseController<ArticuloManufacturado, ArticuloManufacturadoService> {

}
