package pl.com.clockworkgnome.pragmaticunittesting.second;

import java.util.ArrayList;
import java.util.List;

public class Criteria {
    private List<Criterion> criterions = new ArrayList<>();

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public void add(Criterion criterion) {
        criterions.add(criterion);
    }
}
