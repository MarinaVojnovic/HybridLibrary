package rs.hybridit.common;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider implements Serializable {

	private static final long serialVersionUID = -3301695478208950415L;

	public LocalDate now() {
		return LocalDate.now();
	}

}
