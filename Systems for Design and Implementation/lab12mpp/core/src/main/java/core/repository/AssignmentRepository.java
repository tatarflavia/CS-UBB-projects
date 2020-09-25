package core.repository;


import core.model.Grade;
import core.model.Pair;

public interface AssignmentRepository extends BaseEntityRepository<Pair<Long,Long>, Grade> {
}
