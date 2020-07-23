package ebs.back.entity.wrapper;

import java.util.List;

import ebs.back.entity.SugerenciaChef;

public class SugerenciaChefWrapper {
	private List<SugerenciaChef> sugerencias;

	public SugerenciaChefWrapper() {
	}

	public List<SugerenciaChef> getSugerencias() {
		return sugerencias;
	}

	public void setSugerencias(List<SugerenciaChef> sugerencias) {
		this.sugerencias = sugerencias;
	}

	public void addSugerencia(SugerenciaChef sugerencia) {
		this.sugerencias.add(sugerencia);
	}

}
