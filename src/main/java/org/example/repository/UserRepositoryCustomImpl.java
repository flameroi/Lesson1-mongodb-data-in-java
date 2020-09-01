package org.example.repository;

import org.example.docs.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

@Repository
public class UserRepositoryCustomImpl implements UserReposCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    public long getMaxEmpNo() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "id"));
        query.limit(1);

        Users maxObject = mongoTemplate.findOne(query, Users.class);
        if (maxObject == null) {
            return 0L;
        }
        return maxObject.getId();
    }

    @Override
    public long updateUser(String empNo, String fullName) {
        Query query = new Query(Criteria.where("empNo").is(empNo));
        Update update = new Update();
        update.set("fullName", fullName);

        UpdateResult result = this.mongoTemplate.updateFirst(query, update, Users.class);

        if (result != null) {
            return result.getModifiedCount();
        }
        return 0;
    }
}
