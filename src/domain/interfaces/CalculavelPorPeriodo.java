package domain.interfaces;

import org.joda.time.DateTime;

public interface CalculavelPorPeriodo {

	DateTime getInicio();

	DateTime getFim();

	Double getValorDiaria();

}
