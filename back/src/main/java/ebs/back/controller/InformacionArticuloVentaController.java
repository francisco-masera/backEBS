package ebs.back.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ebs.back.entity.ArticuloManufacturado;
import ebs.back.entity.InformacionArticuloVenta;
import ebs.back.entity.InformacionInsumoVenta;
import ebs.back.entity.Insumo;
import ebs.back.entity.Receta;
import ebs.back.entity.RubroManufacturado;
import ebs.back.entity.Stock;
import ebs.back.service.InformacionArticuloVentaService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/informacionArticulo")
public class InformacionArticuloVentaController
		extends BaseController<InformacionArticuloVenta, InformacionArticuloVentaService> {

	@Autowired
	private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@PostMapping("/uploadImg")
	@Transactional
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes)
			throws IOException {
		if (file == null || file.isEmpty()) {
			attributes.addFlashAttribute("message", "Por favor seleccione un archivo");
			return "redirect:status";
		}

		String upload_folder = ".//src//main//resources//static//images//productos//";
		byte[] filesBytes = file.getBytes();
		Path path = Paths.get(upload_folder + file.getOriginalFilename());
		Files.write(path, filesBytes);

		return "redirect:/status";
	}

	/**
	 * Filtrados por texto libre (input type text)
	 * 
	 * @param terminos
	 * @return
	 */
	@PostMapping("/filtrados")
	@Procedure
	public List<InformacionArticuloVenta> getProductoVentaByFiltros(@RequestParam String terminos) {
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getManufacturadosByFiltro")

					.returningResultSet("manufacturados", (rs, rowNum) -> new ArticuloManufacturado(rs.getLong(1), rs.getString(2), rs.getFloat(4),
							rs.getString(3), null, null, rs.getInt(7), rs.getBoolean(5), rs.getBoolean(8),
							rs.getBoolean(9), rs.getString(6), false, null, null));

			Map<String, Object> out = jdbcCall.execute(terminos);
			List<InformacionArticuloVenta> articulos;

			articulos = (List<InformacionArticuloVenta>) out.get("manufacturados");

			if (articulos != null && !articulos.isEmpty()) {

				for (InformacionArticuloVenta articulo : articulos) {
					if (articulo instanceof ArticuloManufacturado) {
						((ArticuloManufacturado) articulo).setRecetas(this.getRecetasXManufacturado(articulo.getId()));
						ArticuloManufacturado manufacturado = (ArticuloManufacturado) articulo;
						if (!this.getEstadoStockManufacturado(manufacturado.getRecetas()))
							articulos.remove(manufacturado);
					}

				}

			}
			jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getInsumosVentaByFiltro")
					.returningResultSet("insumos", (rs, rowNum) -> new InformacionInsumoVenta(rs.getLong(1), rs.getString(2), rs.getFloat(4),
							rs.getString(3), null, null, new Insumo(rs.getLong(8), "", rs.getString(5), false,
									false, false, null, null, null, null, null)));

			out = jdbcCall.execute(terminos);

			articulos.addAll((List<InformacionArticuloVenta>) out.get("insumos"));

			return articulos;

		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * @param tipo
	 * @param categoria
	 * @return
	 */
	@PostMapping("/articulosByCategoria")
	@Procedure
	public List<InformacionArticuloVenta> getProductoVentaByCategoria(@RequestParam int tipo,
			@RequestParam int categoria) {
		String sql;
		List<InformacionArticuloVenta> articulos = new ArrayList<>();
		List<ArticuloManufacturado> manufacturados = new ArrayList<>();
		if (tipo == 0) {
			sql = "SELECT am.denominacion, ia.idArticuloVenta, ia.descripcion, ia.imagen, "
					+ "ia.precioVenta, r.idRubroManufacturado, r.denominacion "
					+ "FROM ArticuloManufacturado am INNER JOIN InformacionArticuloVenta ia "
					+ "ON am.idArticuloManufacturado = ia.idArticuloVenta "
					+ "INNER JOIN RubroManufacturado r ON r.idRubroManufacturado = am.idRubro "
					+ "WHERE am.baja = 0 AND r.baja = 0 AND r.idRubroManufacturado = ?";
			articulos = this.jdbcTemplate.query(sql, new Object[] { categoria },
					(rs, rowNum) -> new ArticuloManufacturado(rs.getLong(2), rs.getString(3), rs.getFloat(5),
							rs.getString(4), null, null, 0, true, true, true, rs.getString(1), true,
							new RubroManufacturado(rs.getLong(6), rs.getString(7), false, null), null));

			articulos.addAll(this.setUpManufacturados(articulos, manufacturados));
			return articulos;

		} else if (tipo == 2 && categoria == 1) {
			sql = "SELECT am.denominacion, ia.idArticuloVenta, ia.descripcion, ia.imagen, ia.precioVenta, "
					+ "r.idRubroManufacturado, r.denominacion "
					+ "FROM ArticuloManufacturado am INNER JOIN InformacionArticuloVenta ia "
					+ "ON am.idArticuloManufacturado = ia.idArticuloVenta "
					+ "INNER JOIN RubroManufacturado r ON r.idRubroManufacturado = am.idRubro WHERE aptoCeliaco = ?";
			articulos = this.jdbcTemplate.query(sql, new Object[] { true },
					(rs, rowNum) -> new ArticuloManufacturado(rs.getLong(2), rs.getString(3), rs.getFloat(5),
							rs.getString(4), null, null, 0, true, true, true, rs.getString(1), true,
							new RubroManufacturado(rs.getLong(6), rs.getString(7), false, null), null));

			articulos.addAll(this.setUpManufacturados(articulos, manufacturados));
			return articulos;

		} else if (tipo == 2 && categoria == 2) {
			sql = "SELECT am.denominacion, ia.idArticuloVenta, ia.descripcion, ia.imagen, ia.precioVenta, "
					+ "r.idRubroManufacturado, r.denominacion, re.cantidadInsumo "
					+ "FROM ArticuloManufacturado am INNER JOIN InformacionArticuloVenta ia "
					+ "ON am.idArticuloManufacturado = ia.idArticuloVenta "
					+ "INNER JOIN RubroManufacturado r ON r.idRubroManufacturado = am.idRubro "
					+ "INNER JOIN Receta re ON am.idArticuloManufacturado = re.idManufacturado "
					+ "WHERE vegano = ? OR vegetariano = ?";
			articulos = this.jdbcTemplate.query(sql, new Object[] { true, true },
					(rs, rowNum) -> new ArticuloManufacturado(rs.getLong(2), rs.getString(3), rs.getFloat(5),
							rs.getString(4), null, null, 0, true, true, true, rs.getString(1), true,
							new RubroManufacturado(rs.getLong(6), rs.getString(7), false, null),
							getRecetasXManufacturado(rs.getLong(2))));

			articulos.addAll(this.setUpManufacturados(articulos, manufacturados));
			return articulos;

		} else if (tipo == 1) {
			sql = "SELECT DISTINCT ia.idArticuloVenta, ia.descripcion, ia.precioVenta, ia.imagen, i.idInsumo, i.denominacion "
					+ "FROM  Insumo i INNER JOIN informacionarticuloventa_insumo ii ON i.idInsumo = ii.idInsumo "
					+ "INNER JOIN InformacionArticuloVenta ia ON ii.idInsumoVenta = ia.idArticuloVenta "
					+ "INNER JOIN RubroInsumo r INNER JOIN Stock s ON s.idStock = i.idStock WHERE "
					+ "i.baja = 0 AND s.actual > 0 AND r.idRubroInsumo LIKE '1%'";
			return this.jdbcTemplate.query(sql,
					(rs, rowNum) -> new InformacionInsumoVenta(rs.getLong("ia.idArticuloVenta"),
							rs.getString("ia.descripcion"), rs.getFloat("ia.precioVenta"), rs.getString("ia.imagen"),
							null, null, new Insumo(rs.getLong("i.idInsumo"), "", rs.getString("i.denominacion"), false,
									false, false, null, null, null, null, null)));
		}
		return null;
	}

	/**
	 * Verificamos si todos los insumos tienen stock suficiente para fabricar un
	 * manufacturado
	 * 
	 * @param recetas
	 * @return
	 */
	private boolean getEstadoStockManufacturado(List<Receta> recetas) {

		for (Receta receta : recetas) {
			if (receta.getInsumo().getStock().getActual() < receta.getCantidadInsumo())
				return false;
		}
		return true;

	}

	private List<Receta> getRecetasXManufacturado(Long id) {

		return this.jdbcTemplate.query(
				"SELECT r.cantidadInsumo, i.idInsumo, i.denominacion, i.unidadMedida, s.actual "
						+ "FROM Stock s INNER JOIN Insumo i ON s.idStock = i.idStock "
						+ "INNER JOIN Receta r ON i.idInsumo = r.idInsumo WHERE r.idManufacturado = " + id,

				new RowMapper<Receta>() {
					@Override
					public Receta mapRow(ResultSet rs, int rowNum) throws SQLException {
						Receta receta = new Receta();
						receta.setCantidadInsumo(rs.getFloat("r.cantidadInsumo"));

						Insumo insumo = new Insumo();
						insumo.setIdInsumo(rs.getLong("i.idInsumo"));
						insumo.setDenominacion(rs.getString("i.denominacion"));
						insumo.setUnidadMedida(rs.getString("i.unidadMedida"));

						Stock stock = new Stock();
						stock.setActual(rs.getFloat("s.actual"));

						insumo.setStock(stock);
						receta.setInsumo(insumo);

						return receta;
					}
				});
	}

	/**
	 * Filtra manufacturados de una lista de artículos, quitando de la misma los que
	 * no tienen stock suficiente para ser fabricados
	 * 
	 * @param articulos
	 * @param manufacturados
	 * @return
	 */
	private List<ArticuloManufacturado> setUpManufacturados(List<InformacionArticuloVenta> articulos,
			List<ArticuloManufacturado> manufacturados) {
		// Dejamos de lado los artículos de tipo Insumo
		for (InformacionArticuloVenta art : articulos) {
			if (art instanceof ArticuloManufacturado) {
				((ArticuloManufacturado) art).setRecetas(this.getRecetasXManufacturado(art.getId()));
				manufacturados.add((ArticuloManufacturado) art);
			}
		}

		// Obtenemos el estaado del stock, dado por el stock actual de cada insumo usado
		// para fabricarlo

		return manufacturados.stream()
				.filter(m -> getEstadoStockManufacturado(m.getRecetas())).collect(Collectors.toList());

	}

}
