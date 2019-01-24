package com.example.ContractSystem.service;


import com.example.ContractSystem.model.Contract;
import com.example.ContractSystem.model.Costumer;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class ContractService {

    static List<Contract> contracts = new ArrayList<>();

    public static Contract newContract(String name, Costumer costumer) {
        Random r = new Random();
        long id = Math.abs(r.nextLong());
        Contract contract = findContractByID(id);
        if (contract == null) {
            contract = new Contract(id, name);
            contract.setCostumerNumber(costumer.getCostumerNumber());
            contracts.add(contract);
            return contract;
        }
        return null;

    }
    public static Contract findContractByID(long id){
        for (Contract contract : contracts) {
            if( contract.getContractNumber() == id ) {
                return contract;
            }
        }

        return null;
    }
    // This is a part of the mock "Fagsystem"

    private static Map<Long, Costumer> costumers = new HashMap<>();

    public static Costumer getCostumerByID(long id) {
        return costumers.get(id);
    }

    public static List<Costumer> getCostumersByName(String name) {
        // not going to fill.
        return null;
    }

    private static void addCostumer(Costumer costumer) {
        costumers.put(costumer.getCostumerNumber(),costumer);
    }

    public static Costumer newCostumer(long id){
        Costumer costumer = getCostumerByID(id);
        System.out.println(costumer == null);
        if (costumer == null){
            costumer = new Costumer(id);
            addCostumer(costumer);
        }
        return costumer;
    }

    public static void updateCostumer(Costumer costumer){
        costumers.remove(costumer.getCostumerNumber());
        costumers.put(costumer.getCostumerNumber(),costumer);
    }


    public static String updateContract(Contract contract, Costumer costumer) {
        costumer.addContract(contract);
        updateCostumer(costumer);
        Random r = new Random();
        if ( r.nextInt(10) > 5) {
            contract.setStatus("Avtale sendt");
        }
        return contract.getStatus();
    }
}
