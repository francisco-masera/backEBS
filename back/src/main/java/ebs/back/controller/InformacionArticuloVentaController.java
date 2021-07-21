package ebs.back.controller;

import ebs.back.entity.*;
import ebs.back.entity.wrapper.ArticuloVentaWrapper;
import ebs.back.service.InformacionArticuloVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequestMapping(path = "buensabor/informacionArticulo")
public class InformacionArticuloVentaController
        extends BaseController<InformacionArticuloVenta, InformacionArticuloVentaService> {

    @Autowired
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate();

    @PostMapping("/uploadImg")
    @Transactional
    public boolean uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("name") String nombre) throws Exception {
        try {
            if (file == null || file.isEmpty()) {
                throw new Exception("El archivo está corrupto o no puede leerse.");
            }

            String upload_folder = ".//src//main//resources//static//images//productos//";
            byte[] filesBytes = file.getBytes();
            Path path = Paths.get(upload_folder + nombre);
            Files.write(path, filesBytes);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
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
            List<InformacionArticuloVenta> filtrados = new ArrayList<>();
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getManufacturadosByFiltro")

                    .returningResultSet("manufacturados", (rs, rowNum) -> new ArticuloManufacturado(rs.getLong(1),
                            rs.getString(2), rs.getFloat(4),
                            rs.getString(3), null, null, rs.getInt(7),
                            rs.getBoolean(5), rs.getBoolean(8),
                            rs.getBoolean(9), rs.getString(6), false, null, null));

            Map<String, Object> out = jdbcCall.execute(terminos);
            List<InformacionArticuloVenta> articulos;

            articulos = (List<InformacionArticuloVenta>) out.get("manufacturados");

            if (articulos != null && !articulos.isEmpty()) {

                for (InformacionArticuloVenta articulo : articulos) {
                    if (articulo instanceof ArticuloManufacturado) {
                        ((ArticuloManufacturado) articulo).setRecetas(this.getRecetasXManufacturado(articulo.getId()));
                        ArticuloManufacturado manufacturado = (ArticuloManufacturado) articulo;
                        if (this.getEstadoStockManufacturado(manufacturado.getRecetas()))
                            filtrados.add(manufacturado);
                    }

                }

            }
            jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("getInsumosVentaByFiltro")
                    .returningResultSet("insumos", (rs, rowNum) -> new InformacionInsumoVenta(rs.getLong(1), rs.getString(2), rs.getFloat(4),
                            rs.getString(3), null, null, new Insumo(rs.getLong(8), "", rs.getString(5),
                            false, false, null, null, null, null, null)));

            out = jdbcCall.execute(terminos);

            filtrados.addAll((List<InformacionArticuloVenta>) out.get("insumos"));

            return filtrados;

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
        List<InformacionArticuloVenta> articulos;

        if (tipo == 0) {
            sql = "SELECT am.denominacion, ia.idArticuloVenta, ia.descripcion, ia.imagen, "
                    + "ia.precioVenta, r.idRubroManufacturado, r.denominacion "
                    + "FROM ArticuloManufacturado am INNER JOIN InformacionArticuloVenta ia "
                    + "ON am.idArticuloManufacturado = ia.idArticuloVenta "
                    + "INNER JOIN RubroManufacturado r ON r.idRubroManufacturado = am.idRubro "
                    + "WHERE am.baja = 0 AND r.baja = 0 AND r.idRubroManufacturado = ?";
            articulos = this.jdbcTemplate.query(sql, new Object[]{categoria},
                    (rs, rowNum) -> new ArticuloManufacturado(rs.getLong(2), rs.getString(3), rs.getFloat(5),
                            rs.getString(4), null, null, 0, true, true, true, rs.getString(1), true,
                            new RubroManufacturado(rs.getLong(6), rs.getString(7), false, null), null));

            articulos = this.setUpManufacturados(articulos);
            return articulos;

        } else if (tipo == 2 && categoria == 1) {
            sql = "SELECT am.denominacion, ia.idArticuloVenta, ia.descripcion, ia.imagen, ia.precioVenta, "
                    + "r.idRubroManufacturado, r.denominacion "
                    + "FROM ArticuloManufacturado am INNER JOIN InformacionArticuloVenta ia "
                    + "ON am.idArticuloManufacturado = ia.idArticuloVenta "
                    + "INNER JOIN RubroManufacturado r ON r.idRubroManufacturado = am.idRubro WHERE aptoCeliaco = ?";
            articulos = this.jdbcTemplate.query(sql, new Object[]{true},
                    (rs, rowNum) -> new ArticuloManufacturado(rs.getLong(2), rs.getString(3), rs.getFloat(5),
                            rs.getString(4), null, null, 0, true, true, true, rs.getString(1), true,
                            new RubroManufacturado(rs.getLong(6), rs.getString(7), false, null), null));

            articulos = this.setUpManufacturados(articulos);
            return articulos;

        } else if (tipo == 2 && categoria == 2) {
            sql = "SELECT am.denominacion, ia.idArticuloVenta, ia.descripcion, ia.imagen, ia.precioVenta, "
                    + "r.idRubroManufacturado, r.denominacion, re.cantidadInsumo "
                    + "FROM ArticuloManufacturado am INNER JOIN InformacionArticuloVenta ia "
                    + "ON am.idArticuloManufacturado = ia.idArticuloVenta "
                    + "INNER JOIN RubroManufacturado r ON r.idRubroManufacturado = am.idRubro "
                    + "INNER JOIN Receta re ON am.idArticuloManufacturado = re.idManufacturado "
                    + "WHERE vegano = ? OR vegetariano = ?";
            articulos = this.jdbcTemplate.query(sql, new Object[]{true, true},
                    (rs, rowNum) -> new ArticuloManufacturado(rs.getLong(2), rs.getString(3), rs.getFloat(5),
                            rs.getString(4), null, null, 0, true, true, true, rs.getString(1), true,
                            new RubroManufacturado(rs.getLong(6), rs.getString(7), false, null),
                            getRecetasXManufacturado(rs.getLong(2))));

            articulos = this.setUpManufacturados(articulos);
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
                            null, null, new Insumo(rs.getLong("i.idInsumo"), "", rs.getString("i.denominacion"),
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
        if (recetas.size() == 0)
            return false;
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
     * @return
     */
    private List<InformacionArticuloVenta> setUpManufacturados(List<InformacionArticuloVenta> articulos) {
        // Dejamos de lado los artículos de tipo Insumo
        List<ArticuloManufacturado> manufacturados = new ArrayList<>();
        for (InformacionArticuloVenta art : articulos) {
            if (art instanceof ArticuloManufacturado) {
                ((ArticuloManufacturado) art).setRecetas(this.getRecetasXManufacturado(art.getId()));
                if (((ArticuloManufacturado) art).getRecetas().size() > 0)
                    manufacturados.add((ArticuloManufacturado) art);
            }
        }

        // Obtenemos el estaado del stock, dado por el stock actual de cada insumo usado
        // para fabricarlo

        return manufacturados.stream()
                .filter(m -> getEstadoStockManufacturado(m.getRecetas())).collect(Collectors.toList());

    }

    private Boolean existe(String sql, Long id) {
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class) == 1;
        } catch (Exception ex) {
            return false;
        }
    }

    @GetMapping("/getDetalle/{id}")
    @Procedure
    public Object getDetalle(@PathVariable long id) throws Exception {
        try {

            if (existe("Select Count(idArticuloManufacturado) From ArticuloManufacturado Where idArticuloManufacturado = ?", id))
                return this.jdbcTemplate.queryForObject("Select * from ArticuloManufacturado AM INNER JOIN " +
                                "InformacionArticuloVenta IAV ON AM.idArticuloManufacturado = IAV.idArticuloVenta WHERE IAV.idArticuloVenta = ?",
                        new Object[]{id}, (rs, rowNum) -> new ArticuloVentaWrapper(
                                rs.getLong("idArticuloVenta"), rs.getBoolean("aptoCeliaco"), rs.getBoolean("vegano"),
                                rs.getBoolean("vegetariano"), rs.getString("denominacion"), rs.getFloat("precioVenta"),
                                false,
                                rs.getString("descripcion"),
                                rs.getString("imagen")
                        ));

            else if (existe("Select Count(idInsumoVenta) From informacionarticuloventa_insumo Where idInsumoVenta = ?", id))
                return this.jdbcTemplate.queryForObject("Select * from informacionarticuloventa_insumo IAVI INNER JOIN " +
                                "InformacionArticuloVenta IAV ON IAVI.idInsumoVenta = IAV.idArticuloVenta INNER JOIN " +
                                "Insumo i ON i.idInsumo = IAVI.idInsumo WHERE idArticuloVenta=?",
                        new Object[]{id}, (rs, rowNum) -> new ArticuloVentaWrapper(
                                rs.getLong("idArticuloVenta"), false, false,
                                false, rs.getString("denominacion"), rs.getFloat("precioVenta"),
                                true,
                                rs.getString("descripcion"),
                                rs.getString("imagen")

                        ));
            else
                throw new Exception("Error interno del servidor.");
        } catch (Exception ex) {
            throw ex;
        }
    }

    private List<Long> getIdInsumosById(Long id) {
        return jdbcTemplate.query("SELECT idInsumo FROM Receta Where idManufacturado = " + id,
                (rs, rowNum) -> rs.getLong(1));
    }

    private boolean estadoCritico(float actual, float minimo) {
        return !(actual > (minimo + (minimo * 0.1)));
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

    private int getEstadoStock(Long id) {
        try {
            Stock stock = this.jdbcTemplate.queryForObject(
                    "SELECT actual, maximo, minimo FROM Stock s INNER JOIN Insumo i ON s.idStock = i.idStock WHERE i.idInsumo = "
                            + id,
                    (rs, rowNumber) -> {
                        Stock stock1 = new Stock();
                        stock1.setActual(rs.getFloat("actual"));
                        stock1.setMaximo(rs.getFloat("maximo"));
                        stock1.setMinimo(rs.getFloat("minimo"));
                        return stock1;
                    });
            return establecerEstadoStock(stock.getActual(), stock.getMaximo(), stock.getMinimo());
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    private boolean getEstadoStockManufacturado(Long id) {
        List<Integer> estados = getIdInsumosById(id).stream().map(i -> {
            try {
                return this.getEstadoStock(i);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }).collect(Collectors.toList());

        return estados.stream().allMatch(e -> e != 2);
    }

    @GetMapping("/conStock")
    public List<InformacionArticuloVenta> getConStock() {
        List<InformacionArticuloVenta> articulos = this.jdbcTemplate.query(
                "SELECT a.aptoCeliaco, a.baja, a.denominacion, a.tiempoCocina, a.vegano, a.vegetariano, "
                        + "ia.idArticuloVenta, ia.descripcion, ia.imagen, ia.precioVenta, r.idRubroManufacturado, r.denominacion, r.baja "
                        + "FROM ArticuloManufacturado a INNER JOIN InformacionArticuloVenta ia "
                        + "ON ia.idArticuloVenta = a.idArticuloManufacturado "
                        + "INNER JOIN RubroManufacturado r ON a.idRubro = r.idRubroManufacturado WHERE a.Baja = 0 AND r.baja = 0 "
                        + "ORDER BY a.denominacion",

                (rs, rowNum) -> new ArticuloManufacturado(rs.getLong(7), rs.getString(8), rs.getFloat(10),
                        rs.getString(9), null, null, rs.getInt(4), rs.getBoolean(1), rs.getBoolean(5), rs.getBoolean(6),
                        rs.getString(3), rs.getBoolean(2),
                        new RubroManufacturado(rs.getLong(11), rs.getString(12), rs.getBoolean(13), null), null));

        articulos = articulos.stream().filter(a -> this.getEstadoStockManufacturado(a.getId())).collect(Collectors.toList());

        articulos.addAll(getInsumosConStock());

        return articulos;
    }


    @GetMapping("/insumosConStock")
    public List<InformacionArticuloVenta> getInsumosConStock() {
        List<Insumo> insumos = this.jdbcTemplate.query(
                "SELECT i.idInsumo, i.unidadMedida, i.denominacion, i.baja, "
                        + "s.idStock, s.actual, r.idRubroInsumo, r.denominacion "
                        + "FROM Insumo i INNER JOIN Stock s ON i.idStock = s.idStock "
                        + "INNER JOIN RubroInsumo r ON r.idRubroInsumo = i.idRubro "
                        + "WHERE i.esInsumo = 0 AND i.Baja = 0 AND s.actual > 0 ORDER BY i.denominacion",
                (rs, rowNum) -> new Insumo(rs.getLong(1), rs.getString(2), rs.getString(3), false,
                        rs.getBoolean(4), new Stock(rs.getLong(5), rs.getFloat(6), 0.0F, 0.0F, null),
                        new RubroInsumo(rs.getLong(7), rs.getString(8), null), null, null, null));

        List<InformacionArticuloVenta> insumosVenta = new ArrayList<>();
        for (Insumo insumo : insumos) {

            InformacionInsumoVenta informacion = this.jdbcTemplate.queryForObject(
                    "SELECT ia.idArticuloVenta, ia.imagen, ia.descripcion, ia.precioVenta  "
                            + "FROM Insumo i INNER JOIN informacionarticuloventa_insumo ii ON i.idInsumo = ii.idInsumo "
                            + "INNER JOIN InformacionArticuloVenta ia ON ia.idArticuloVenta = ii.idInsumoVenta "
                            + "WHERE i.esInsumo = 0 AND i.Baja = 0 AND i.idInsumo = ?",
                    new Object[]{insumo.getIdInsumo()}, (rs, rowNum) -> new InformacionInsumoVenta(rs.getLong(1),
                            rs.getString(3), rs.getFloat(4), rs.getString(2), null, null, insumo));
            informacion.setInsumo(insumo);
            insumosVenta.add(informacion);
        }
        return insumosVenta;
    }

    @GetMapping("/masVendidos")
    public List<ArticuloVentaWrapper> getMasVendidos(@RequestParam int yMax, @RequestParam int mMax, @RequestParam int dMax,
                                                     @RequestParam int yMin, @RequestParam int mMin, @RequestParam int dMin) throws Exception {
        try {
            java.sql.Timestamp maxFecha = Timestamp.valueOf(LocalDateTime.of(yMax, mMax, dMax, 0, 0, 0));
            java.sql.Timestamp minFecha = Timestamp.valueOf(LocalDateTime.of(yMin, mMin, dMin, 0, 0, 0));
            if (minFecha.after(maxFecha)) {
                throw new Exception("La fecha mínima no puede ser mayor a la máxima.");
            }
            List<ArticuloVentaWrapper> ventas = this.jdbcTemplate.query(
                    "SELECT a.precioVenta, m.denominacion, COUNT(idArticuloVenta) AS totalVentas FROM Factura F " +
                            "INNER JOIN Pedido P on F.idPedido = P.idPedido INNER JOIN DetallePedido DP on P.idPedido = DP.idPedido" +
                            " inner join InformacionArticuloVenta a on DP.idArticulo = a.idArticuloVenta" +
                            " INNER join ArticuloManufacturado m on m.idArticuloManufacturado = a.idArticuloVenta " +
                            "WHERE F.fechaHora BETWEEN ? AND ? GROUP BY a.idArticuloVenta ORDER BY totalVentas DESC LIMIT 50",

                    (rs, rowNum) -> new ArticuloVentaWrapper(rs.getString("denominacion"), rs.getFloat("precioVenta"),
                            rs.getLong("totalVentas")), minFecha, maxFecha);
            ventas.addAll(this.jdbcTemplate.query(
                    "SELECT a.precioVenta,  i.denominacion, COUNT(DP.idArticulo)AS totalVentas FROM Factura F" +
                            " INNER JOIN Pedido P on F.idPedido = P.idPedido INNER JOIN DetallePedido DP on P.idPedido = DP.idPedido " +
                            "inner join InformacionArticuloVenta a on DP.idArticulo = a.idArticuloVenta INNER join informacionarticuloventa_insumo ii " +
                            "INNER JOIN insumo i on ii.idInsumo = i.idInsumo WHERE f.fechaHora BETWEEN ? AND ?" +
                            "GROUP BY a.idArticuloVenta ORDER BY totalVentas DESC LIMIT 50",

                    (rs, rowNum) -> new ArticuloVentaWrapper(rs.getString("denominacion"), rs.getFloat("precioVenta"),
                            rs.getLong("totalVentas")), minFecha, maxFecha));

            return ventas;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }
}
