package instituto;

import instituto.enums.InternPosition;

import java.time.LocalDate;
import java.time.Period;

public class InternImp extends TeacherImp implements Intern {

    private LocalDate startDate;
    private LocalDate endDate;
    private InternPosition position;

    public InternImp(String fName, String lName, int age, LocalDate startDate, LocalDate endDate, InternPosition position) {
        super(fName, lName, age);
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
    }

    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public InternPosition getPosition() {
        return position;
    }

    @Override
    public void setPosition(InternPosition position) {
        this.position = position;
    }

    @Override
    public Period getTotalTime() {
        return Period.between(startDate, endDate);
    }

    @Override
    public Period getRemainingTime() {
        LocalDate today = LocalDate.now();
        Period res = null;

        if (today.isAfter(endDate)) {
            res = Period.ZERO;
        } else {
            res = Period.between(LocalDate.now(),endDate);
        }
        return res;
    }

    @Override
    public void renovateInternship() {
        this.endDate = endDate.plusYears(1);
    }

    @Override
    public void tradePositionsWith(InternImp other) {
        InternPosition aux = this.position;
        this.position = other.getPosition();
        other.setPosition(aux);
    }
}
