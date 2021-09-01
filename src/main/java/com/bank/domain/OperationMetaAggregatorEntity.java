package com.bank.domain;

import com.bank.domain.operators.OperatorEntity;
import com.bank.policy.operation.OperationStatus;
import com.bank.policy.rights.Right;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class OperationMetaAggregatorEntity {

    @Id
    private Long id;
    private String operationName;
    private LocalDate operationStartTime;
    private LocalDate operationEndTime;
    private AccountEntity targetAccountEntity;
    private AccountEntity sourceAccountEntity;
    private Set<Right> usedRights;
    private OperatorEntity operatorEntity;
    private OperationStatus operationStatus;

    public OperationMetaAggregatorEntity(String operationName, LocalDate operationStartTime,
                                         LocalDate operationEndTime, AccountEntity targetAccountEntity,
                                         AccountEntity sourceAccountEntity, Set<Right> usedRights,
                                         OperatorEntity operatorEntity, OperationStatus operationStatus) {
        this.operationName = operationName;
        this.operationStartTime = operationStartTime;
        this.operationEndTime = operationEndTime;
        this.targetAccountEntity = targetAccountEntity;
        this.sourceAccountEntity = sourceAccountEntity;
        this.usedRights = usedRights;
        this.operatorEntity = operatorEntity;
        this.operationStatus = operationStatus;
    }

}
