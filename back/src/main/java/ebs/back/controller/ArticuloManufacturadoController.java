package ebs.back.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ebs.back.entity.ArticuloManufacturado;
import ebs.back.entity.Insumo;
import ebs.back.entity.Receta;
import ebs.back.entity.RubroManufacturado;
import ebs.back.entity.Stock;
import ebs.back.entity.wrapper.ArticuloManufacturadoWrapper;
import ebs.back.service.ArticuloManufacturadoService;

/**
 * @author franc
 *
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(path = "buensabor/manufacturado")
public class ArticuloManufacturadoController
		extends BaseController<ArticuloManufacturado, ArticuloManufacturadoService> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@GetMapping("/conStock")
	public List<ArticuloManufacturado> getConStock() throws SQLException {
		List<ArticuloManufacturado> articulos = this.jdbcTemplate.query(
				"SELECT a.aptoCeliaco, a.baja, a.denominacion, a.tiempoCocina, a.vegano, a.vegetariano, "
						+ "ia.idArticuloVenta, ia.descripcion, ia.imagen, ia.precioVenta, r.idRubroManufacturado, r.denominacion, r.baja "
						+ "FROM ArticuloManufacturado a INNER JOIN informacionarticuloventa ia "
						+ "ON ia.idArticuloVenta = a.idArticuloManufacturado "
						+ "INNER JOIN rubromanufacturado r ON a.idRubro = r.idRubroManufacturado WHERE a.Baja = 0 AND r.baja = 0 "
						+ "ORDER BY a.denominacion",

				(rs, rowNum) -> new ArticuloManufacturado(rs.getLong(7), rs.getString(8), rs.getFloat(10),
						rs.getString(9), null, null, rs.getInt(4), rs.getBoolean(1), rs.getBoolean(5), rs.getBoolean(6),
						rs.getString(3), rs.getBoolean(2),
						new RubroManufacturado(rs.getLong(11), rs.getString(12), rs.getBoolean(13), null), null));

		return articulos.stream().filter(a -> this.getEstadoStockManufacturado(a.getId())).collect(Collectors.toList());
	}

	private List<Long> getIdInsumosById(Long id) {
		return jdbcTemplate.query("SELECT idInsumo FROM Receta Where idManufacturado = " + id,
				(rs, rowNum) -> rs.getLong(1));
	}

	private boolean getEstadoStockManufacturado(Long id) {
		List<Integer> estados = getIdInsumosById(id).stream().map(i -> {
			try {
				return this.getEstadoStock(i);
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}).collect(Collectors.toList());

		return estados.stream().allMatch(e -> e != 2);
	}

	private int establecerEstadoStock(float actual, float maximo, float minimo) {
		if (actual >= maximo)
			return 1;
		else if (actual < minimo)
			return 2;
		else if (estadoCritico(actual, minimo))
			return 3;
		return 4;
	}

	private boolean estadoCritico(float actual, float minimo) {
		return !(actual > (minimo + (minimo * 0.1)));
	}

	private int getEstadoStock(Long id) throws SQLException {
		try {
			Stock stock = this.jdbcTemplate.queryForObject(
					"SELECT actual, maximo, minimo FROM stock s INNER JOIN insumo i ON s.idStock = i.idStock WHERE i.idInsumo = "
							+ id,
					new RowMapper<Stock>() {
						public Stock mapRow(ResultSet rs, int rowNumber) throws SQLException {
							Stock stock = new Stock();
							stock.setActual(rs.getFloat("actual"));
							stock.setMaximo(rs.getFloat("maximo"));
							stock.setMinimo(rs.getFloat("minimo"));
							return stock;
						}
					});
			return establecerEstadoStock(stock.getActual(), stock.getMaximo(), stock.getMinimo());
		} catch (EmptyResultDataAccessException e) {
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 
	 * @param id
	 * @return List<Receta>: Todas las recetas de un producto manufacturado
	 */
	@GetMapping("/recetasManufacturado/{id}")
	public List<Receta> getRecetasXManufacturado(@PathVariable Long id) {
		List<Receta> recetas = this.jdbcTemplate.query(
				"SELECT r.cantidadInsumo, i.idInsumo, i.denominacion, i.unidadMedida, s.actual "
						+ "FROM stock s INNER JOIN insumo i ON s.idStock = i.idStock INNER JOIN receta r ON i.idInsumo = r.idInsumo WHERE r.idManufacturado = "
						+ id,

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

		return recetas;
	}

	/**
	 * 
	 * @param idInsumo
	 * @return Float: Precio unitario más actual de un insumo
	 */
	private Float getPrecioUnitario(Long idInsumo) {
		return this.jdbcTemplate
				.queryForObject("SELECT precioUnitario FROM historialcompraaproveedores WHERE idInsumo = " + idInsumo
						+ " ORDER BY fechaCompra DESC LIMIT 1", Float.class);
	}

	/**
	 * 
	 * @param idsInsumosStr
	 * @param cantInsumo
	 * @return El costo de producción de un producto manufacturado
	 */
	@GetMapping("/costo")
	public Float getCosto(@RequestParam String idsInsumosStr, @RequestParam String cantidadInsumos) {
		List<String> idsAuxList = Arrays.asList(idsInsumosStr.split(","));
		List<String> cantInsumosAuxList = Arrays.asList(cantidadInsumos.split(","));
		List<Long> idsInsumos = idsAuxList.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Float> cantInsumoList = cantInsumosAuxList.stream().map(Float::parseFloat).collect(Collectors.toList());

		List<Float> costosInsumo = idsInsumos.stream().map(id -> this.getPrecioUnitario(id))
				.collect(Collectors.toList());
		Float sumatoria = 0.0F;
		for (int i = 0; i < costosInsumo.size(); i++) {
			sumatoria += cantInsumoList.get(i) * costosInsumo.get(i);
		}

		return sumatoria;

	}

	/**
	 * 
	 * @param idsManufacturadosStr
	 * @return List<Float>: El costo de cada uno de los productos manufacturados
	 */
	@GetMapping("/costos")
	public List<Float> getCostos(@RequestParam String idsManufacturadosStr) {
		List<String> idsAuxList = Arrays.asList(idsManufacturadosStr.split(","));
		List<Long> idsManufacturados = idsAuxList.stream().map(Long::parseLong).collect(Collectors.toList());
		List<Float> costosManufacturados = new ArrayList<>();
		List<ArticuloManufacturadoWrapper> manufacturados = idsManufacturados.stream()
				.map(id -> this.convertirManufacturado(id)).collect(Collectors.toList());
		manufacturados.forEach(
				manufacturado -> manufacturado.setRecetas(this.getRecetasXManufacturado(manufacturado.getId())));
		for (ArticuloManufacturadoWrapper manufacturado : manufacturados) {
			if (!manufacturado.getRecetas().isEmpty())
				costosManufacturados.add(this.auxGetCostos(manufacturado.getRecetas()));
			else
				costosManufacturados.add(null);

		}
		return costosManufacturados;
	}

	/**
	 * 
	 * @param recetasSugeridas
	 * @return Float: El costo de un producto manufacturado
	 */
	private Float auxGetCostos(List<Receta> recetas) {
		String idsInsumos = this.crearStrIdsInsumos(recetas);
		String cantidades = this.crearStrCantidades(recetas);
		return this.getCosto(idsInsumos, cantidades);

	}

	/**
	 * 
	 * @param recetas
	 * @return String: Cantidades de cada insumo para fabricar un producto
	 *         manufacturado, concatenadas con comas
	 */
	private String crearStrCantidades(List<Receta> recetas) {
		List<String> cantidades = new ArrayList<>();
		recetas.forEach(receta -> cantidades.add(String.valueOf(receta.getCantidadInsumo())));
		String s = String.join(",", cantidades);
		return s;
	}

	/**
	 * 
	 * @param recetas
	 * @return String: Id de cada insumo necesario para fabricar un producto
	 *         manufacturado, concatenados con comas
	 */
	private String crearStrIdsInsumos(List<Receta> recetas) {
		List<String> idsInsumos = new ArrayList<>();
		recetas.forEach(receta -> idsInsumos.add(receta.getInsumo().getIdInsumo().toString()));
		String s = String.join(",", idsInsumos);
		return s;
	}

	/**
	 * 
	 * @param idManufacturado
	 * @return {@link ArticuloManufacturadoWrapper}: Transforma el responseBody del
	 *         getOne de un producto manufacturado a su clase envolvente
	 */
	private ArticuloManufacturadoWrapper convertirManufacturado(Long idManufacturado) {
		ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		Object response = this.getOne(idManufacturado).getBody();

		String responseJson = "";
		try {
			responseJson = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		ArticuloManufacturadoWrapper manufacturadoWrapper = new ArticuloManufacturadoWrapper();
		try {
			manufacturadoWrapper = mapper.readValue(responseJson, ArticuloManufacturadoWrapper.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return manufacturadoWrapper;
	}

	/**
	 * 
	 * @param file
	 * @return status
	 * @throws IOException
	 */
	@PostMapping("/uploadImg")
	@Transactional
	public boolean uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		try {
			String upload_folder = ".//src//main//resources//static//images//productos//";
			byte[] filesBytes = file.getBytes();
			Path path = Paths.get(upload_folder + file.getOriginalFilename());
			Files.write(path, filesBytes);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
