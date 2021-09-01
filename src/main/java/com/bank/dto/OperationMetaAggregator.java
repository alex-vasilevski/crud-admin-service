package com.bank.dto;

import com.bank.policy.operation.OperationStatus;
import com.bank.policy.rights.Right;
import lombok.AllArgsConstructor;
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
    private LocalDate operationStart;
    private LocalDate operationEndTime;
    private Account targetAccount;
    private Total total;
    private Account sourceAccount;
    private Set<Right> usedRights;
    private Operator operator;
    private OperationStatus operationStatus;

    public OperationMetaAggregator(String operationName, LocalDate operationStartTime,
                                   LocalDate operationEndTime, Account targetAccount,
                                   Total total, Account sourceAccount, Set<Right> usedRights,
                                   Operator operator, OperationStatus operationStatus) {
        this.operationName = operationName;
        this.operationStart = operationStartTime;
        this.operationEndTime = operationEndTime;
        this.targetAccount = targetAccount;
        this.total = total;
        this.sourceAccount = sourceAccount;
        this.usedRights = usedRights;
        this.operator = operator;
        this.operationStatus = operationStatus;
    }
}
