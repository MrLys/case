package com.example.ContractSystem.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class Costumer {
    private long costumerNumber;
    // Assuming more than one contract per costumer.
    Map<Long, Contract> contracts;

    public Costumer(long costumerNumber) {
        this.costumerNumber = costumerNumber;
        this.contracts = new HashMap<>();
    }

    public long getCostumerNumber() {
        return costumerNumber;
    }

    public void setCostumerNumber(long costumerNumber) {
        this.costumerNumber = costumerNumber;
    }



    public Map<Long, Contract> getContracts() {
        return contracts;
    }

    public void setContracts(Map<Long, Contract> contracts) {
        this.contracts = contracts;
    }

    public void addContract(Contract contract) {this.contracts.put(contract.getContractNumber(),contract); }
    public Contract getContract(long id) { return this.contracts.get(id); }
}
