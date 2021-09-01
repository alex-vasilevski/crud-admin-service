package com.bank.operation;

import com.bank.dto.Account;
import com.bank.dto.OperationMetaAggregator;
import com.bank.dto.Operator;
import com.bank.dto.Total;
import com.bank.policy.account.AccountStatus;
import com.bank.policy.operation.OperationStatus;
import com.bank.policy.rights.Right;
import com.bank.service.CrudService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WithdrawMoney implements Operation{

    private OperationMetaAggregator template;
    private OperationMetaAggregator operationDetails;
    private Map<String, CrudService> crudServiceMapping;

    private String operationName;
    private LocalDate operationStart;
    private LocalDate operationEndTime;
    private Account targetAccount;
    private Total total;
    private Account sourceAccount;
    private Set<Right> usedRights;
    private Operator operator;
    private OperationStatus operationStatus;

    public WithdrawMoney(OperationMetaAggregator operationDetails) {
        this.operationDetails = operationDetails;
        String opName = getClass().getSimpleName();
        Set<Right> rights = new HashSet<>();
        rights.add(Right.USER);
        this.template = new OperationMetaAggregator(opName, null, null,
                null, null, null, rights, null, null );
    }

    @Override
    public OperationMetaAggregator perform(Map<String, CrudService> crudServiceMapping) {
        LocalDate begin = LocalDate.now();
        Account targetAccount = operationDetails.getTargetAccount();


            Total bill = operationMetaAggregator.getTotal();




        return null;
    }
}
