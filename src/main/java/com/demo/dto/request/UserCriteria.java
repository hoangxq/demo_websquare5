package com.demo.dto.request;

import com.demo.entity.Gender;
import com.demo.entity.User;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class UserCriteria {
    private String name;
    private Integer team;
    private String phone;
    private Gender gender;
    private Date birthDateFrom;
    private Date birthDateTo;

    public Specification<User> toSpecification() {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotBlank(name) && StringUtils.isNotEmpty(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (Objects.nonNull(team)) {
                predicates.add(criteriaBuilder.equal(root.get("team").get("id"), team));
            }
            if (StringUtils.isNotBlank(phone) && StringUtils.isNotEmpty(phone)) {
                predicates.add(criteriaBuilder.like(root.get("phone"), "%" + phone + "%"));
            }
            if (Objects.nonNull(gender)) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
            }
            if (Objects.nonNull(birthDateFrom)) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthDate"), birthDateFrom));
            }
            if (Objects.nonNull(birthDateTo)) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("birthDate"), birthDateTo));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        });
    }
}
