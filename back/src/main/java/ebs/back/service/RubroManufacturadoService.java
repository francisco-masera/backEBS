package ebs.back.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import ebs.back.entity.RubroManufacturado;
import ebs.back.repository.RubroManufacturadoRepository;

@Service
public class RubroManufacturadoService extends BaseService<RubroManufacturado, RubroManufacturadoRepository> {

	@Autowired
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	@Override
	public List<RubroManufacturado> getAll(int page, int size) {
		List<RubroManufacturado> rubros = this.jdbcTemplate.query(
				"SELECT * FROM RubroManufacturado where baja=0",

				new RowMapper<RubroManufacturado>() {
					@Override
					public RubroManufacturado mapRow(ResultSet rs, int rowNum) throws SQLException {
						RubroManufacturado rubro = new RubroManufacturado();
						rubro.setId(rs.getLong(1));
						rubro.setBaja(rs.getBoolean(2));
						rubro.setDenominacion(rs.getString(3));

						return rubro;
					}
				});

		return rubros;
	}

	@Override
	public boolean delete(Long id) throws Exception {
		try {

			RubroManufacturado entity = new RubroManufacturado();
			if (repository.existsById(id)) {
				Optional<RubroManufacturado> entityOptional = repository.findById(id);
				entity = entityOptional.get();
				entity.setBaja(true);
				entity = repository.save(entity);	
			}
			return entity.isBaja();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
}
