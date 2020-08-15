package si.medius.studentskanaloga.beans;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class SolverBeanTest {

    @Inject
    SolverBean solverBean;

    @Test
    void isSolvableFalse() {
        Assertions.assertFalse(solverBean.isSolvable("1001000100011010100010000"));
    }

    @Test
    void isSolvableTrue() {
        Assertions.assertTrue(solverBean.isSolvable("1111000101010001011000111"));
    }
}