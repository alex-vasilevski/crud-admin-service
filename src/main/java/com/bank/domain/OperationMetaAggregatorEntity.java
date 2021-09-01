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
    private Long targetAccountEntityId;
    private TotalEntity totalEntity;
    private AccountEntity sourceAccountEntityId;
    private Set<Right> usedRights;
    private OperatorEntity operatorEntity;
    private OperationStatus operationStatus;

    public OperationMetaAggregatorEntity(String operationName, LocalDate operationStartTime,
                                         LocalDate operationEndTime, Long targetAccountEntityId,
                                         TotalEntity totalEntity, AccountEntity sourceAccountEntityId,
                                         Set<Right> usedRights, OperatorEntity operatorEntity,
                                         OperationStatus operationStatus) {
        this.operationName = operationName;
        this.operationStartTime = operationStartTime;
        this.operationEndTime = operationEndTime;
        this.targetAccountEntityId = targetAccountEntityId;
        this.totalEntity = totalEntity;
        this.sourceAccountEntityId = sourceAccountEntityId;
        this.usedRights = usedRights;
        this.operatorEntity = operatorEntity;
        this.operationStatus = operationStatus;
    }
}
