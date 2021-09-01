package com.bank.dto;

import com.bank.policy.operation.OperationStatus;
import com.bank.policy.rights.Right;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class OperationMetaAggregator {

    private String operationName;
    private LocalDate operationStart;
    private LocalDate operationEndTime;
    private Long targetAccountId;
    private Total totalBid;
    private Account sourceAccountId;
    private Set<Right> usedRights;
    private Operator operator;
    private OperationStatus operationStatus;

    public OperationMetaAggregator(String operationName, LocalDate operationStart, LocalDate operationEndTime, Long targetAccountId, Total totalBid, Account sourceAccountId, Set<Right> usedRights, Operator operator, OperationStatus operationStatus) {
        this.operationName = operationName;
        this.operationStart = operationStart;
        this.operationEndTime = operationEndTime;
        this.targetAccountId = targetAccountId;
        this.totalBid = totalBid;
        this.sourceAccountId = sourceAccountId;
        this.usedRights = usedRights;
        this.operator = operator;
        this.operationStatus = operationStatus;
    }
}
