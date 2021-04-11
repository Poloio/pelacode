package instituto;

import instituto.enums.InternPosition;

import java.time.LocalDate;
import java.time.Period;

public interface Intern {

    LocalDate getStartDate();
    LocalDate getEndDate();
    InternPosition getPosition();
    void setPosition(InternPosition position);

    Period getTotalTime();
    Period getRemainingTime();
    void renovateInternship();

    void tradePositionsWith(InternImp other);
}
