package com.bank.dto;

import com.bank.policy.operation.OperationStatus;
import com.bank.policy.rights.Right;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Set;


@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class OperationMetaAggregator {

    private String operationName;
    private LocalDate operationStartTime;
    private LocalDate operationEndTime;
    private Account targetAccountEntity;
    private Account sourceAccountEntity;
    private Set<Right> usedRights;
    private Operator operator;
    private OperationStatus operationStatus;

    public OperationMetaAggregator(String operationName, LocalDate operationStartTime,
                                   LocalDate operationEndTime, Account targetAccountEntity,
                                   Account sourceAccountEntity, Set<Right> usedRights,
                                   Operator operator, OperationStatus operationStatus) {
        this.operationName = operationName;
        this.operationStartTime = operationStartTime;
        this.operationEndTime = operationEndTime;
        this.targetAccountEntity = targetAccountEntity;
        this.sourceAccountEntity = sourceAccountEntity;
        this.usedRights = usedRights;
        this.operator = operator;
        this.operationStatus = operationStatus;
    }

}
